
package org.team2168.commands.winch;

import org.team2168.commands.CommandBase;

/**
 *
 * @author bradmiller
 */
public class WinchWithJoystick extends CommandBase {

    public WinchWithJoystick() {
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
    	//TODO: Test this! Set a valid operating speed range.
    	double speed = oi.getOperatorLeftStick();
    	//We only want to drive the winch in one direction.
    	//Down on the stick will drive the winch down.
    	if (speed < -0.05 && !catapultWinch.isCatapultRetracted()) {
    		speed = -speed;
    	} else {
    		speed = 0.0;
    	}
    	
    	catapultWinch.driveWinch(speed);
    }

    /**
     * Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        return false;
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
