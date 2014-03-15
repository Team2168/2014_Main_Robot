package org.team2168.commands.tapper;

import org.team2168.RobotMap;
import org.team2168.commands.CommandBase;

/**
 * Control the servo ball tappers with a joystick 
 */
public class DisengageTappers extends CommandBase {

	public DisengageTappers() {
		requires(servoTapper);
	}

	/**
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
	}

	/**
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		servoTapper.setLeftAngle(RobotMap.ballTapperDisengageAngle.getDouble());
		servoTapper.setRightAngle(RobotMap.ballTapperDisengageAngle.getDouble());
	}

	/**
	 * The command has completed when both tappers are at the angle specified.
	 */
	protected boolean isFinished() {
		return (servoTapper.getLeftAngle() == RobotMap.ballTapperDisengageAngle.getDouble())
				&& (servoTapper.getRightAngle() == RobotMap.ballTapperDisengageAngle.getDouble());
	}

	/**
	 * Called once after isFinished returns true
	 */
	protected void end() {
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run.
	 */
	protected void interrupted() {
	}
}
