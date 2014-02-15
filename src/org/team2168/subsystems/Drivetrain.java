package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.PIDController.Sensors.AverageEncoder;
import org.team2168.commands.DrivetrainWithJoystick;
import org.team2168.utils.FalconGyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	private static Drivetrain instance = null;
	private static final boolean INVERT_LEFT = true;
	private static final boolean INVERT_RIGHT = false;
	private static Talon rightMotor, rightMotor2;
	private static Talon leftMotor, leftMotor2;
	private static FalconGyro gyro;
	private static AverageEncoder driveTrainEncoderLeft,
			driveTrainEncoderRight;
	private double lastLeftSpeed = 0.0;
	private double lastRightSpeed = 0.0;

	/**
	 * A private constructor to prevent multiple instances from being created.
	 */
	private Drivetrain() {
		rightMotor = new Talon(RobotMap.rightDriveMotor.getInt());
		leftMotor = new Talon(RobotMap.leftDriveMotor.getInt());
		rightMotor2 = new Talon(RobotMap.rightDriveMotor2.getInt());
		leftMotor2 = new Talon(RobotMap.leftDriveMotor2.getInt());
		gyro = new FalconGyro(RobotMap.gyroPort.getInt());
		gyro.setSensitivity(0.0070);

		driveTrainEncoderRight = new AverageEncoder(
				RobotMap.driveTrainEncoderRightA.getInt(),
				RobotMap.driveTrainEncoderRightB.getInt(),
				RobotMap.driveEncoderPulsePerRot,
				RobotMap.driveEncoderDistPerTick,
				RobotMap.rightDriveTrainEncoderReverse,
				RobotMap.driveEncodingType, RobotMap.driveSpeedReturnType,
				RobotMap.drivePosReturnType, RobotMap.driveAvgEncoderVal);
		// Set min period and rate before reported stopped
		driveTrainEncoderRight.setMaxPeriod(RobotMap.driveEncoderMinPeriod);
		driveTrainEncoderRight.setMinRate(RobotMap.driveEncoderMinRate);
		driveTrainEncoderRight.start();

		driveTrainEncoderLeft = new AverageEncoder(
				RobotMap.driveTrainEncoderLeftA.getInt(),
				RobotMap.driveTrainEncoderLeftB.getInt(),
				RobotMap.driveEncoderPulsePerRot,
				RobotMap.driveEncoderDistPerTick,
				RobotMap.leftDriveTrainEncoderReverse,
				RobotMap.driveEncodingType, RobotMap.driveSpeedReturnType,
				RobotMap.drivePosReturnType, RobotMap.driveAvgEncoderVal);
		// Set min period and rate before reported stopped
		driveTrainEncoderLeft.setMaxPeriod(RobotMap.driveEncoderMinPeriod);
		driveTrainEncoderLeft.setMinRate(RobotMap.driveEncoderMinRate);
		driveTrainEncoderLeft.start();
	}

	/**
	 * 
	 * @return the instance of this subsystem.
	 */
	public static Drivetrain getInstance() {
		if (instance == null) {
			instance = new Drivetrain();
		}
		return instance;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DrivetrainWithJoystick());
	}

	public void driveLeft(double speed) {
		if (INVERT_LEFT)
			speed = -speed;

		lastLeftSpeed = rateLimit(speed, lastLeftSpeed,
				RobotMap.driveRateLimit.getDouble());

		leftMotor.set(lastLeftSpeed);
		leftMotor2.set(lastLeftSpeed);
	}

	public void driveRight(double speed) {
		if (INVERT_RIGHT)
			speed = -speed;

		lastRightSpeed = rateLimit(speed, lastRightSpeed,
				RobotMap.driveRateLimit.getDouble());

		rightMotor.set(lastRightSpeed);
		rightMotor2.set(lastRightSpeed);
	}

	/**
	 * A method to drive the motors on the drivetrain with.
	 * 
	 * @param rightSpeed
	 *            the speed to drive the right motor at
	 * @param leftSpeed
	 *            the speed to drive the left motor at
	 */
	public void drive(double rightSpeed, double leftSpeed) {
		this.driveRight(rightSpeed);
		this.driveLeft(leftSpeed);
	}

	/**
	 * Gets the distance the right encoder has turned
	 * 
	 * @return distance in inches
	 */
	public double getRightEncoderDistance() {
		return driveTrainEncoderRight.getDistance();
	}

	/**
	 * Gets the distance the left encoder has turned
	 * 
	 * @return distance in inches
	 */
	public double getLeftEncoderDistance() {
		return driveTrainEncoderLeft.getDistance();
	}

	/**
	 * Averages the distance of the two encoders to get the distance the robot
	 * has traveled
	 * 
	 * @return distance in inches
	 */
	public double getAveragedEncoderDistance() {
		// System.out.println("  l: " + getLeftEncoderDistance() + "  r: " +
		// getRightEncoderDistance());
		return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2;
	}

	/**
	 * resets right encoder to 0
	 * 
	 */
	public void resetRightEncoder() {
		driveTrainEncoderRight.reset();
	}

	/**
	 * resets left encoder to 0
	 * 
	 */
	public void resetLeftEncoder() {
		driveTrainEncoderLeft.reset();
	}

	public void resetEncoders() {
		resetLeftEncoder();
		resetRightEncoder();
	}

	/**
	 * Get the current angle of the gyro.
	 * 
	 * @return the angle of the gyro, in degrees.
	 */
	public double getGyroAngle() {
		return gyro.getAngle();
	}

	/**
	 * Re-initialize the gyro. This should not be called during a match.
	 */
	public void reinitGyro() {
		gyro.initGyro();
	}

	/**
	 * Set the current robot heading to 0.0
	 */
	public void resetGyro() {
		// double before = getGyroAngle();
		gyro.reset();
		// System.out.println("Gyro Before: " + before + " After: " +
		// getGyroAngle());
	}

	/**
	 * A simple rate limiter.
	 * http://www.chiefdelphi.com/forums/showpost.php?p=1212189&postcount=3
	 * 
	 * @param input
	 *            the input value (speed from command/joystick)
	 * @param speed
	 *            the speed currently being traveled at
	 * @param limit
	 *            the rate limit
	 * @return the new output speed (rate limited)
	 */
	public static double rateLimit(double input, double speed, double limit) {
		return rateLimit(input, speed, limit, limit);
	}

	/**
	 * A simple rate limiter.
	 * http://www.chiefdelphi.com/forums/showpost.php?p=1212189&postcount=3
	 * 
	 * @param input
	 *            the input value (speed from command/joystick)
	 * @param speed
	 *            the speed currently being traveled at
	 * @param posRateLimit
	 *            the rate limit for accelerating
	 * @param negRateLimit
	 *            the rate limit for decelerating
	 * @return the new output speed (rate limited)
	 */
	public static double rateLimit(double input, double speed,
			double posRateLimit, double negRateLimit) {
		if (input > 0) {
			if (input > (speed + posRateLimit)) {
				// Accelerating positively
				speed = speed + posRateLimit;
			} else if (input < (speed - negRateLimit)) {
				// Decelerating positively
				speed = speed - negRateLimit;
			} else {
				speed = input;
			}
		} else {
			if (input < (speed - posRateLimit)) {
				// Accelerating negatively
				speed = speed - posRateLimit;
			} else if (input > (speed + negRateLimit)) {
				// Decelerating negatively
				speed = speed + negRateLimit;
			} else {
				speed = input;
			}
		}
		return speed;
	}
}
