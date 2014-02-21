package org.team2168.commands.catapult;

import org.team2168.commands.CommandBase;
import org.team2168.subsystems.IntakePosition;

/**
 * 
 * @author KALIber10
 */
public class OpenWinchDogGear extends CommandBase {

	/**
	 * Creates a new OpenWinchDogGear command.
	 */
	public OpenWinchDogGear() {
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
		catapultWinch.openDogGear();
	}

	/**
	 * Make this return true when this Command no longer needs to run execute()
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
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
	}
}
