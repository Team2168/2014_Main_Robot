package org.team2168.commands.winch;

import org.team2168.commands.CommandBase;

/**
 * Wait until a ball is not detected.
 * @author James
 */
public class WaitUntilBallNotPresent extends CommandBase {

	/**
	 * Creates a new command.
	 */
	public WaitUntilBallNotPresent() {
		requires(catapultWinch);
	}

	/**
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		catapultWinch.resetBallNotPresent();
	}

	/**
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
	}

	/**
	 * Wait until a ball is not present in the intake
	 * @return true when a ball is not detected
	 */
	protected boolean isFinished() {
		return catapultWinch.isBallNotPresent();
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
