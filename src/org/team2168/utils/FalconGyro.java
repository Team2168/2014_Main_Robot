package org.team2168.utils;

/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import edu.wpi.first.wpilibj.AccumulatorResult;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.communication.UsageReporting;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.parsing.ISensor;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.util.BoundaryException;

/**
 * Use a rate gyro to return the robots heading relative to a starting position.
 * The Gyro class tracks the robots heading based on the starting position. As
 * the robot rotates the new heading is computed by integrating the rate of
 * rotation returned by the sensor. When the class is instantiated, it does a
 * short calibration routine where it samples the gyro while at rest to
 * determine the default offset. This is subtracted from each sample to
 * determine the heading.
 * 
 * Modified to allow external calls in to initialize the gyro, ala cheezygyro.
 * This allows a the robot to re-zero the gyro if it was drifting before the
 * match has started. This could happen if the robot was being moved around
 * shortly after power on (during the first gyro init).
 * 
 * Also modified to prevent this class from blocking execution while
 * initializing the gyro.
 */
public class FalconGyro extends SensorBase implements PIDSource, ISensor,
		LiveWindowSendable, Runnable {

	static final int kOversampleBits = 10;
	static final int kAverageBits = 0;
	static final double kSamplesPerSecond = 50.0;
	static final double kCalibrationSampleTime = 5.0;
	static final double kDefaultVoltsPerDegreePerSecond = 0.007;
	FalconAnalogChannel m_analog;
	double m_voltsPerDegreePerSecond;
	double m_offset;
	int m_center;
	boolean m_channelAllocated;
	AccumulatorResult result;
	private PIDSourceParameter m_pidSource;
	private long sleepTime = 0;
	private long updatePeriod = 20; // ms
	private boolean reInit = false, lastReInit = false;
	private int initState = 0;
	private boolean calibratedOnce = false;
	private double lastTime = 0.0;
	private static boolean matchStarted = false;
	private int accumulatorCenter = 0;

	/**
	 * Gyro constructor given a slot and a channel. .
	 * 
	 * @param slot
	 *            The cRIO slot for the analog module the gyro is connected to.
	 * @param channel
	 *            The analog channel the gyro is connected to.
	 */
	public FalconGyro(int slot, int channel) {
		m_analog = new FalconAnalogChannel(slot, channel);
		m_channelAllocated = true;
		setInit(true);
	}

	/**
	 * Gyro constructor with only a channel.
	 * 
	 * Use the default analog module slot.
	 * 
	 * @param channel
	 *            The analog channel the gyro is connected to.
	 */
	public FalconGyro(int channel) {
		m_analog = new FalconAnalogChannel(channel);
		m_channelAllocated = true;
		setInit(true);
	}

	/**
	 * Gyro constructor with a precreated analog channel object. Use this
	 * constructor when the analog channel needs to be shared. There is no
	 * reference counting when an AnalogChannel is passed to the gyro.
	 * 
	 * @param channel
	 *            The AnalogChannel object that the gyro is connected to.
	 */
	public FalconGyro(FalconAnalogChannel channel) {
		m_analog = channel;
		if (m_analog == null) {
			System.err
					.println("Analog channel supplied to Gyro constructor is null");
		} else {
			m_channelAllocated = false;
			setInit(true);
		}
	}

	/**
	 * Reset the gyro. Resets the gyro to a heading of zero. This can be used if
	 * there is significant drift in the gyro and it needs to be recalibrated
	 * after it has been running.
	 */
	public void reset() {
		if (m_analog != null) {
			m_analog.resetAccumulator();
		}
	}

	/**
	 * Delete (free) the accumulator and the analog components used for the
	 * gyro.
	 */
	public void free() {
		if (m_analog != null && m_channelAllocated) {
			m_analog.free();
		}
		m_analog = null;
	}

	/**
	 * Return the actual angle in degrees that the robot is currently facing.
	 * 
	 * The angle is based on the current accumulator value corrected by the
	 * oversampling rate, the gyro type and the A/D calibration values. The
	 * angle is continuous, that is can go beyond 360 degrees. This make
	 * algorithms that wouldn't want to see a discontinuity in the gyro output
	 * as it sweeps past 0 on the second time around.
	 * 
	 * @return the current heading of the robot in degrees. This heading is
	 *         based on integration of the returned rate from the gyro.
	 */
	public double getAngle() {
		if (m_analog == null) {
			return 0.0;
		} else {
			m_analog.getAccumulatorOutput(result);

			long value = result.value - (long) (result.count * m_offset);

			double scaledValue = value
					* 1e-9
					* m_analog.getLSBWeight()
					* (1 << m_analog.getAverageBits())
					/ (m_analog.getModule().getSampleRate() * m_voltsPerDegreePerSecond);

			return scaledValue;
		}
	}

	/**
	 * Return the rate of rotation of the gyro
	 * 
	 * The rate is based on the most recent reading of the gyro analog value
	 * 
	 * @return the current rate in degrees per second
	 */
	public double getRate() {
		if (m_analog == null) {
			return 0.0;
		} else {
			return (m_analog.getAverageValue() - ((double) m_center + m_offset))
					* 1e-9
					* m_analog.getLSBWeight()
					/ ((1 << m_analog.getOversampleBits()) * m_voltsPerDegreePerSecond);
		}
	}

	/**
	 * Set the gyro type based on the sensitivity. This takes the number of
	 * volts/degree/second sensitivity of the gyro and uses it in subsequent
	 * calculations to allow the code to work with multiple gyros.
	 * 
	 * @param voltsPerDegreePerSecond
	 *            The type of gyro specified as the voltage that represents one
	 *            degree/second.
	 */
	public void setSensitivity(double voltsPerDegreePerSecond) {
		m_voltsPerDegreePerSecond = voltsPerDegreePerSecond;
	}

	/**
	 * Set which parameter of the encoder you are using as a process control
	 * variable. The Gyro class supports the rate and angle parameters
	 * 
	 * @param pidSource
	 *            An enum to select the parameter.
	 */
	public void setPIDSourceParameter(PIDSourceParameter pidSource) {
		BoundaryException.assertWithinBounds(pidSource.value, 1, 2);
		m_pidSource = pidSource;
	}

	/**
	 * Get the angle of the gyro for use with PIDControllers
	 * 
	 * @return the current angle according to the gyro
	 */
	public double pidGet() {
		switch (m_pidSource.value) {
		case 1:// PIDSourceParameter.kRate_val:
			return getRate();
		case 2:// PIDSourceParameter.kAngle_val:
			return getAngle();
		default:
			return 0.0;
		}
	}

	/**
	 * Let the gyro code know when the match has started. This information is
	 * used to prematurely cancel gyro initialization sequences so that auto
	 * mode and field communications are not affected.
	 * 
	 * @param started
	 *            true if the match has started.
	 */
	public static synchronized void setMatchStarted(boolean started) {
		matchStarted = started;
	}

	/**
	 * Get the state of the match. See setMatchStarted();
	 * 
	 * @return true if the match has started
	 */
	private static synchronized boolean isMatchStarted() {
		return matchStarted;
	}

	/**
	 * Calibrate the gyro by sampling its analog output for a period of time to
	 * calculate the zero rate value for subsequent measurements.
	 */
	public void init() {
		// Call thread safe method to start gyro initialization
		setInit(true);
	}

	/**
	 * Thread safe method for modifying the state of the reInit value
	 * 
	 * @param initVal
	 *            true to start initializing the gyro.
	 */
	private synchronized void setInit(boolean initVal) {
		reInit = initVal;
	}

	/**
	 * Status of gyro initialization process.
	 * 
	 * @return true if actively initializing the gyro.
	 */
	public synchronized boolean isInitializing() {
		return reInit;
	}

	/**
	 * A separate thread to run the gyro initialization code in. This is to
	 * prevent the gyro from blocking execution of the main robot code.
	 * 
	 * Threading initGyro allows gyro calibration to take place preceeding a
	 * match, without having to worry about the gyro init breaking field
	 * communications or eating precious time in auto mode.
	 */
	public void run() {
		while (true) {
			if (isMatchStarted() && !isInitializing()) {
				// If the match has started, and we're not actively calibrating
				// the gyro, sleep for a long time.
				sleepTime = 5000;
			} else if (isInitializing()) {
				// We're actively recalibrating the gyro. Frequently update this
				// thread to check that a match hasn't started.
				sleepTime = updatePeriod / 3;

				if (!lastReInit) {
					// First time through, start calibration sequence
					initState = 0;

					// Store out the accumulatorCenter in the event we need to
					// cancel the cal process.
					accumulatorCenter = m_analog.getAccumulatorCenter();
				}

				if (isMatchStarted()) {
					// The match has started. Cancel calibration process.
					initState = 999; // skip over state machine.
					setInit(false);

					// Restore previous accumulatorCenter
					m_analog.setAccumulatorCenter(accumulatorCenter);

					// TODO: see if we can use the partially accumulated data
					// instead of just canceling the operation completely
					// TODO: verify gyro can still be read while accumulator
					// is active.
				}

				// Run through gyro calibration state machine
				switch (initState) {
				case 0:
					result = new AccumulatorResult();
					if (m_analog == null) {
						System.out.println("Null m_analog");
					}
					m_voltsPerDegreePerSecond = kDefaultVoltsPerDegreePerSecond;
					m_analog.setAverageBits(kAverageBits);
					m_analog.setOversampleBits(kOversampleBits);
					double sampleRate = kSamplesPerSecond
							* (1 << (kAverageBits + kOversampleBits));
					m_analog.getModule().setSampleRate(sampleRate);

					lastTime = Timer.getFPGATimestamp();
					initState++;
					break;
				case 1:
					// Stock code waits one second. Not really sure why...
					// TODO: see if we can get rid of this delay
					if ((Timer.getFPGATimestamp() - lastTime) >= 1.0) {
						// we've waited one second, proceed to next step
						// Start accumulating to determine rate gyro
						// returns when not moving.
						m_analog.initAccumulator();
						lastTime = Timer.getFPGATimestamp();
						initState++;
					}
					break;
				case 2:
					// Delay for accumulator period
					if ((Timer.getFPGATimestamp() - lastTime) >= kCalibrationSampleTime) {
						initState++;
					} else {
						break;
					}
				case 3:
					m_analog.getAccumulatorOutput(result);
					m_center = (int) ((double) result.value
							/ (double) result.count + .5);
					m_offset = ((double) result.value / (double) result.count)
							- (double) m_center;
					m_analog.setAccumulatorCenter(m_center);
					// TODO: compute / parameterize this
					m_analog.setAccumulatorDeadband(0);

					m_analog.resetAccumulator();
					setPIDSourceParameter(PIDSourceParameter.kAngle);
					if (!calibratedOnce) {
						UsageReporting.report(
								UsageReporting.kResourceType_Gyro,
								m_analog.getChannel(),
								m_analog.getModuleNumber() - 1);
						LiveWindow.addSensor("Gyro",
								m_analog.getModuleNumber(),
								m_analog.getChannel(), this);
						calibratedOnce = true;
					}
					// Done calibrating the gyro
					setInit(false);
					break;
				default: // We should only get here if the cal. is canceled!
					break;
				}

			} else {
				// The match hasn't started yet...
				// Periodically check to see if we need to calibrate the gyro.
				sleepTime = updatePeriod;
			}

			// Update state variable(s)
			lastReInit = isInitializing();

			// Sleep until next time.
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Live Window code, only does anything if live window is activated.
	 */
	public String getSmartDashboardType() {
		return "Gyro";
	}

	private ITable m_table;

	/**
	 * {@inheritDoc}
	 */
	public void initTable(ITable subtable) {
		m_table = subtable;
		updateTable();
	}

	/**
	 * {@inheritDoc}
	 */
	public ITable getTable() {
		return m_table;
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateTable() {
		if (m_table != null) {
			m_table.putNumber("Value", getAngle());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void startLiveWindowMode() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void stopLiveWindowMode() {
	}
}
