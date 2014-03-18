package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the mechanism that keeps the balls from moving around.
 */
public class ServoBallTapper extends Subsystem {
	private static ServoBallTapper instance = null;
	private static Servo leftTapper, rightTapper;
	private static final boolean invertLeft = true;
	private static final boolean invertRight = false;
	
	//These need to match the implementation of angle ranges in the Servo class.
	//TODO: modify the servo class to allow you to set min/max angles.
	//  There's no good reason to not have this supported.
	private static final double MAX_ANGLE = 170.0;
	private static final double MIN_ANGLE= 0.0;

	/**
	 * A private constructor to prevent multiple instances from being created.
	 */
	private ServoBallTapper() {
		leftTapper = new Servo(RobotMap.leftTapperServo.getInt());
		rightTapper = new Servo(RobotMap.rightTapperServo.getInt());
		
		setLeftAngle(0);
		setRightAngle(0);
	}

	/**
	 * @return the instance of this subsystem.
	 */
	public static ServoBallTapper getInstance() {
		if (instance == null) {
			instance = new ServoBallTapper();
		}
		return instance;
	}

	/**
	 * Set the default command
	 */
	public void initDefaultCommand() {
		//setDefaultCommand(new ServoTapperWithJoystick());
	}
	
	/**
	 * Set the angular position of the left tapper. Where zero is fully retracted.
	 * This one moves counter-clockwise.
	 * @param angle 0 - 170 degrees
	 */
	public void setLeftAngle(double angle) {
		setAngle(angle, invertLeft, leftTapper);
	}

	/**
	 * Set the angular position of the left tapper. Where zero is fully retracted.
	 * This one moves clockwise.
	 * @param angle 0 - 170 degrees
	 */
	public void setRightAngle(double angle) {
		setAngle(angle, invertRight, rightTapper);
	}
	
	/**
	 * Set the angular position of a servo.
	 * 
	 * @param angle in degrees (0 - 180) to set the servo to.
	 * @param invert clockwise or counterclockwise rotation. Clockwise if false.
	 * @param servo the Servo object to set the angle of.
	 */
	private void setAngle(double angle, boolean invert, Servo servo) {
		if(angle > MAX_ANGLE) {
			angle = MAX_ANGLE;
		} else if(angle < MIN_ANGLE) {
			angle = MIN_ANGLE;
		}
		
		if(invert) {
			angle = MAX_ANGLE - angle;
		}
		
		servo.setAngle(angle);
	}

	/**
	 * Get the angle of the left tapper. 
	 * @return the angle of the servo (degrees). Angle greater than zero
	 *   is counterclockwise rotation.
	 */
	public double getLeftAngle() {
		return getAngle(invertLeft, leftTapper);
	}
	
	/**
	 * Get the angle of the right tapper. 
	 * @return the angle of the servo (degrees). Angle greater than zero
	 *   is clockwise rotation.
	 */
	public double getRightAngle() {
		return getAngle(invertRight, rightTapper);
	}
	
	/**
	 * Get the angle of a servo.
	 * @param inverted specifies if positive angles should be
	 *   clockwise (false) or counterclockwise (true).
	 * @param servo The Servo object to get the angle of. 
	 * @return the angle of the zero (degrees).
	 */
	private double getAngle(boolean inverted, Servo servo) {
		double angle = servo.getAngle();
		
		if(inverted) {
			angle = -angle + MAX_ANGLE;
		}
		
		return angle;
	}
}
