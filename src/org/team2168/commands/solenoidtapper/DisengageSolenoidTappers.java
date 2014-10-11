package org.team2168.commands.solenoidtapper;

import org.team2168.commands.CommandBase;

/**
 * Control the solenoid ball tappers with a joystick 
 */
public class DisengageSolenoidTappers extends CommandBase {

	public DisengageSolenoidTappers() {
		requires(solenoidTapper);
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
		solenoidTapper.disengage();
	}

	/**
	 * The command has completed when both tappers are at the angle specified.
	 */
	protected boolean isFinished() {
		return true;
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
