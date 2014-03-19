package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.PIDController.sensors.AverageEncoder;
import org.team2168.commands.winch.WinchWithJoystick;
import org.team2168.utils.Debouncer;
import org.team2168.utils.MomentaryDoubleSolenoid;
import org.team2168.utils.Util;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *This subsystem moves the catapult to the set position and detects if it is 
 *at the set position.
 */
public class Winch extends Subsystem {
	private static Winch instance = null;
	private static Talon winchMotor;
	private static DigitalInput winchInputSwitch;
	private static MomentaryDoubleSolenoid winchSolenoid;
	private static AverageEncoder winchEncoder;
	private static AnalogChannel winchPotentiometer;
	private static AnalogChannel winchBallSensor;
	private static AnalogChannel intakeBallSensor;
	private static Debouncer ballPresent, ballNotPresent;
	private static Debouncer ballSettled;

	/**
	 * A private constructor to prevent multiple instances from being created.
	 */
	private Winch(){
		winchMotor = new Talon(RobotMap.winchMotor.getInt());
		winchInputSwitch = new DigitalInput(RobotMap.winchLimitSwitch.getInt());
		winchSolenoid = new MomentaryDoubleSolenoid(
				RobotMap.winchExtPort.getInt(),RobotMap.winchRetPort.getInt());
		winchBallSensor = new AnalogChannel(RobotMap.winchBallSensorPort.getInt());
		intakeBallSensor = new AnalogChannel(RobotMap.intakeBallSensorPort.getInt());
		ballPresent = new Debouncer(RobotMap.ballPresentTime.getDouble());
		ballNotPresent = new Debouncer(RobotMap.ballPresentTime.getDouble());
		ballSettled = new Debouncer(RobotMap.ballSettleTime.getDouble());
		
		winchPotentiometer =
				new AnalogChannel(RobotMap.potentiometerPort.getInt());
		winchEncoder = new AverageEncoder(
				RobotMap.winchEncoderA.getInt(),
				RobotMap.winchEncoderB.getInt(),
				RobotMap.winchEncoderPulsePerRot,
				RobotMap.winchEncoderDistPerTick,
				RobotMap.winchEncoderReverse,
				RobotMap.winchEncodingType, RobotMap.winchSpeedReturnType,
				RobotMap.winchPosReturnType, RobotMap.winchAvgEncoderVal);
		winchEncoder.start();
	}
	
	/**
	 * @return the instance of this subsystem.
	 */
	public static Winch getInstance() {
		if (instance == null) {
			instance = new Winch();
		}
		return instance;
	}
	
	/**
	 * Sets the default command for the subsystem.
	 */
    public void initDefaultCommand() {
    	setDefaultCommand(new WinchWithJoystick());
    }
    
    /**
     * This method drives the catapult winch motors. A positive value will
     * drive the winch downward.
     * 
     * @param winchSpeed 0.0 to 1.0
     */
    public void driveWinch(double winchSpeed){
    	//Only allow the winch to drive in one direction.
    	if (winchSpeed < 0.0) {
    		winchSpeed = 0.0;
    	}
    	winchMotor.set(winchSpeed);
    }
    /**
     * This method closes the dog gear to keep the catapult in place.
     */
    public void retractDogGear(){
    	winchSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    /**
     * This method releases (FIRES) the dog gear on the catapult.
     */
    public void extendDogGear(){
    	winchSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public boolean isDogGearExtended(){
    	return winchSolenoid.get()==DoubleSolenoid.Value.kReverse;
    }
    
    public boolean isDogGearRetracted(){
    	return winchSolenoid.get()==DoubleSolenoid.Value.kForward;
    } 
    
    
    /**
     * This method checks if the catapult has reached the set position.
     * @return true when the winch is lowered all the way
     */
    public boolean isCatapultRetracted(){
    	//NOTE the digital inputs are TRUE when floating, so we need to negate
    	//  the returned value.
    	//Conductors should be hooked up to the normally open (NO) and
    	//  common (C) connections on the limit switch.
       	return !winchInputSwitch.get();
    }
    
    /**
     * Gets the speed of the winch.
     * @return speed of winch in RPM
     */
    public double getWinchSpeed(){
    	return winchEncoder.getRate();
    }
	/**
	 * Gets the distance the left encoder has turned
	 * 
	 * @return distance in inches
	 */
	public double getWinchEncoderDistance() {
		return winchEncoder.getDistance();
	}
    
    /**
     * Reset winch encoder
     */
    public void resetWinchEncoder(){
    	winchEncoder.reset();
    }
    
    /**
     * Get the raw voltage of the catapult
     * @return voltage of Potentiometer
     */
    public double getWinchPotentiometerVoltage(){
    	return winchPotentiometer.getVoltage();
    }

	public double getCatapultAngle() {
		double m = Util.slope(RobotMap.catapultLowerVoltage.getDouble(),
                              RobotMap.catapultLowerAngle.getDouble(),
                              RobotMap.catapultRaiseVoltage.getDouble(),
                              RobotMap.catapultRaiseAngle.getDouble());
		double b = Util.intercept(m, RobotMap.catapultLowerVoltage.getDouble(),
                                  RobotMap.catapultLowerAngle.getDouble());
		return m * getWinchPotentiometerVoltage() + b;
	}
	
    
	/**
	 * Get the winch's ball sensor voltage.
	 * @return The voltage read from the sensor. 0.0 to 5.0
	 */
	public double getWinchBallSensorVoltage() {
		return winchBallSensor.getVoltage();
	}
	
	/**
	 * Get the intake's ball sensor voltage.
	 * @return The voltage read from the sensor. 0.0 to 5.0
	 */
	public double getIntakeBallSensorVoltage() {
		return intakeBallSensor.getVoltage();
	}
	
	/**
	 * Check if ball is present in the intake.
	 * @return true if present for a duration
	 */
	public boolean isBallPresent() {
		return ballPresent.update(getIntakeBallSensorVoltage()
				>= RobotMap.ballPresentVoltage.getDouble());
	}
	
	/**
	 * Check if ball is not present.
	 * @return true if not present for a duration
	 */
	public boolean isBallNotPresent() {
		return ballNotPresent.update(!(getIntakeBallSensorVoltage()
				>= RobotMap.ballPresentVoltage.getDouble()));
	}
	
	/**
	 * Check if ball has settled on the catapult.
	 * @return true if ball is settled.
	 */
	public boolean isBallSettled() {
		return ballSettled.update(getWinchBallSensorVoltage()
				>= RobotMap.ballSettledVoltage.getDouble());
	}
	
	/**
	 * Called once prior to checking if the ball is present.
	 */
	public void resetBallPresent() {
		ballPresent.reset();
	}
	
	/**
	 * Called once prior to checking if the ball is not present.
	 */
	public void resetBallNotPresent() {
		ballNotPresent.reset();
	}
	
	/**
	 * Called once prior to checking if the ball is settled.
	 */
	public void resetBallSettled() {
		ballSettled.reset();
	}
}

