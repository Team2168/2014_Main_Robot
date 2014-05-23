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
	private static final boolean INVERT_LEFT = true;
	private static final boolean INVERT_RIGHT = false;

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
		setAngle(angle, INVERT_LEFT, leftTapper,
				RobotMap.servoMinAngleLeft.getDouble(),
				RobotMap.servoMaxAngleLeft.getDouble());
	}

	/**
	 * Set the angular position of the left tapper. Where zero is fully retracted.
	 * This one moves clockwise.
	 * @param angle 0 - 170 degrees
	 */
	public void setRightAngle(double angle) {
		setAngle(angle, INVERT_RIGHT, rightTapper,
				RobotMap.servoMinAngleRight.getDouble(),
				RobotMap.servoMaxAngleRight.getDouble());
	}
	
	/**
	 * Set the angular position of a servo.
	 * 
	 * @param angle in degrees (0 - 170) to set the servo to.
	 * @param invert clockwise or counterclockwise rotation. Clockwise if false.
	 * @param servo the Servo object to set the angle of.
	 * @param minAngle the minimum allowable angle for the servo to travel to,
	 *   not to be lower than 0.0.
	 * @param maxAngle the maximum allowable angle for the servo to travel to,
	 *   not to exceed 170.0.
	 */
	private void setAngle(double angle, boolean invert, Servo servo,
			double minAngle, double maxAngle) {

		//The Servo class has hard coded ranges of travel (0 - 170).
		//TODO: modify the servo class to allow you to set min/max angles.
		//  There's no good reason to not have this supported.
		if(minAngle < 0) {
			minAngle = 0.0;
		}
		
		if(maxAngle > 170.0){
			maxAngle = 170.0;
		}
		
		//Get angle in range
		if(angle > maxAngle) {
			angle = maxAngle;
		} else if(angle < minAngle) {
			angle = minAngle;
		}
		
		if(invert) {
			angle = maxAngle - angle;
		}
		
		servo.setAngle(angle);
	}

	/**
	 * Get the angle of the left tapper. 
	 * @return the angle of the servo (degrees). Angle greater than zero
	 *   is counterclockwise rotation.
	 */
	public double getLeftAngle() {
		return getAngle(INVERT_LEFT, leftTapper,
				RobotMap.servoMaxAngleLeft.getDouble());
	}
	
	/**
	 * Get the angle of the right tapper. 
	 * @return the angle of the servo (degrees). Angle greater than zero
	 *   is clockwise rotation.
	 */
	public double getRightAngle() {
		return getAngle(INVERT_RIGHT, rightTapper,
				RobotMap.servoMaxAngleRight.getDouble());
	}
	
	/**
	 * Get the angle of a servo.
	 * @param inverted specifies if positive angles should be
	 *   clockwise (false) or counterclockwise (true).
	 * @param servo The Servo object to get the angle of. 
	 * @param maxAngle The maximum angle the servo can travel.
	 * @return the angle of the zero (degrees).
	 */
	private double getAngle(boolean inverted, Servo servo, double maxAngle) {
		double angle = servo.getAngle();
		
		if(inverted) {
			angle = -angle + maxAngle;
		}
		
		return angle;
	}
}
