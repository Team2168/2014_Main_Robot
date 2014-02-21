package org.team2168.commands.flashlight;

import org.team2168.commands.CommandBase;

/**
 * Turn the flashlight on.
 * 
 * This command doesn't complete. If it is going to be used in a sequence,
 * specify a timeout!
 */
public class FlashlightOn extends CommandBase {

	public FlashlightOn() {
		requires(flashlight);
	}
	
	/**
	 * Create a copy of the command which will timeout.
	 * @param timeout the time in seconds after which the command should stop
	 *        (if it hasn't already).
	 */
	public FlashlightOn(double timeout) {
		this(); //Call default  constructor.
		setTimeout(timeout);
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
		flashlight.turnOn();
	}

	/**
	 * This command will never complete unless a timeout period is specified at
	 * instantiation.
	 * It will repeatedly set the state of the spike relay output.
	 */
	protected boolean isFinished() {
		return isTimedOut();
	}

	/**
	 * Called once after isFinished returns true
	 */
	protected void end() {
		flashlight.turnOff();
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run.
	 */
	protected void interrupted() {
	}
}
