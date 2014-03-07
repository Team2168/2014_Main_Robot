package org.team2168.commands.tapper;

import org.team2168.commands.CommandBase;

/**
 * Disengages the ball tapper.
 */
public class DisengageTapper extends CommandBase {

	public DisengageTapper() {
		requires(tapper);
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
		tapper.disengage();
	}

	/**
	 * Check if the command has completed.
	 */
	protected boolean isFinished() {
		return tapper.isDisengaged();
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
