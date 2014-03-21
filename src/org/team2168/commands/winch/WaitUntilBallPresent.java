package org.team2168.commands.winch;

import org.team2168.commands.CommandBase;

/**
 * Wait until a ball is detected within the the intake or on top of the catapult. 
 * @author James
 */
public class WaitUntilBallPresent extends CommandBase {

	/**
	 * Creates a new command.
	 */
	public WaitUntilBallPresent() {
		requires(catapultWinch);
	}

	/**
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		catapultWinch.resetBallPresent();
		catapultWinch.resetBallPresentOnWinch();
	}

	/**
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
	}

	/**
	 * Wait until a ball is present within the intake
	 * @return true when a ball is detected
	 */
	protected boolean isFinished() {
		return catapultWinch.isBallPresent()
				|| catapultWinch.isBallPresentOnWinch();
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
