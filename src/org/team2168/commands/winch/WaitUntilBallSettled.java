package org.team2168.commands.winch;

import org.team2168.commands.CommandBase;

/**
 * Wait until a ball is detected
 * @author James
 */
public class WaitUntilBallSettled extends CommandBase {

	/**
	 * Creates a new command.
	 */
	public WaitUntilBallSettled() {
		requires(catapultWinch);
	}

	/**
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		catapultWinch.resetBallSettled();
	}

	/**
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
	}

	/**
	 * Wait until a ball is settled within the catapult
	 * @return true when a ball is detected settled
	 */
	protected boolean isFinished() {
		return catapultWinch.isBallSettled();
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
