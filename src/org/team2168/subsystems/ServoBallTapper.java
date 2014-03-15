package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.tapper.ServoTapperWithJoystick;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the mechanism that keeps the balls from moving around.
 */
public class ServoBallTapper extends Subsystem {
	private static ServoBallTapper instance = null;
	private Servo leftTapper, rightTapper;

	/**
	 * A private constructor to prevent multiple instances from being created.
	 */
	private ServoBallTapper() {
		leftTapper = new Servo(RobotMap.leftTapperServo.getInt());
		leftTapper.setAngle(180);
		rightTapper = new Servo(RobotMap.rightTapperServo.getInt());
		rightTapper.setAngle(0);
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
		setDefaultCommand(new ServoTapperWithJoystick());
	}
	
	/**
	 * Set the angular position of the left tapper
	 * @param angle 0 - 180 degrees
	 */
	public void setLeftAngle(double angle) {
		if(angle > 180) {
			angle = 180;
		}
		if(angle < 0) {
			angle = 0;
		}
		//run the left side in reverse
		angle = 180 - angle;
		leftTapper.setAngle(angle);
	}

	public void setRightAngle(double angle) {
		if(angle > 180) {
			angle = 180;
		}
		if(angle < 0) {
			angle = 0;
		}
		rightTapper.setAngle(angle);
	}
	
	/**
	 * Engage the tapper (stop ball motion)
	 */
//	public void engage() {
//		
//	}

	/**
	 * Disengage the tapper (allow intake/firing)
	 */
//	public void disengage() {
//		tapperRelay.set(Relay.Value.kReverse);
//	}
	
//	/**
//	 * Is the tapper engaged.
//	 * @return true if engaged
//	 */
//	public boolean isEngaged() {
//		return tapperRelay.get() == Relay.Value.kForward;
//	}
//	
//	/**
//	 * Is the tapper disengaged.
//	 * @return true if disengaged
//	 */
//	public boolean isDisengaged() {
//		return tapperRelay.get() == Relay.Value.kReverse;
//	}
}
