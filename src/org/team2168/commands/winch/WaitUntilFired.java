package org.team2168.commands.winch;

import org.team2168.RobotMap;
import org.team2168.commands.CommandBase;

/**
 * 
 * @author James
 */
public class WaitUntilFired extends CommandBase {

	/**
	 * Creates a new command.
	 */
	public WaitUntilFired() {
		requires(catapultWinch);
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
	}

	/**
	 * Wait until the catapult has swung to the raised position (fired)
	 * @return true when the catapult is raised
	 */
	protected boolean isFinished() {
		return catapultWinch.getCatapultAngle()
				>= RobotMap.catapultWaitUntilFiredAngle.getDouble();
	}

	/**
	 * Called once after isFinished returns true
	 */
	protected void end() {
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
	}
}
