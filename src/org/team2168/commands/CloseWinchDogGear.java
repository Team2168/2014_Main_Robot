
package org.team2168.commands;

/**
 *
 * @author bradmiller
 */
public class CloseWinchDogGear extends CommandBase {
	
	/**
	 * Creates a new CloseWinchDogGear command.
	 */
    public CloseWinchDogGear() {
    	requires (catapultWinch);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	catapultWinch.closeDogGear();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
