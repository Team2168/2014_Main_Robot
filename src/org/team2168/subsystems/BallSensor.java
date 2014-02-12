package org.team2168.subsystems;

import org.team2168.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogChannel;

public class BallSensor extends Subsystem {

	private AnalogChannel ballSensorVoltage;
	private static BallSensor instance = null;

	/**
	 * Gets the voltage of the sensor
	 */
	private BallSensor() {
		ballSensorVoltage = new AnalogChannel(RobotMap.ballSensorPort.getInt());
	}
	
	/**
	 * Returns the static instance of this subsystem.
	 * @return the instance of this subsystem.
	 */
	public static BallSensor getInstance() {
		if (instance == null) {
			instance = new BallSensor();
		}
		return instance;
	}

	/**
	 * Get the ball sensor voltage.
	 * @return The voltage read from the ball sensor. 0.0 to 5.0
	 */
	public double getBallSensorVoltage() {
		return ballSensorVoltage.getVoltage();
	}

	protected void initDefaultCommand() {
		//No default command
	}

}
