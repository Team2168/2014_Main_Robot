package org.team2168.commands.Vision;

import org.team2168.commands.CommandBase;
import org.team2168.subsystems.Vision;

/**
 * A command to 
 *
 * @author Kevin
 */
public class WaitForLeftHot extends CommandBase {
	
	private boolean stop;
	
	
	/**
	 * Creates a new IntakeRaise command.
	 */
	public WaitForLeftHot() {
    	requires(vision);
    	stop = false;
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
    	
    	//if left is hot and we didnt time out
    	if (Vision.getInstance().isLeftHot() && !this.isTimedOut())
    	{
    		Vision.getInstance().setLeftOrRightHot(-1);
    		stop = true;
    	}
    	else if (this.isTimedOut())// we timed out, assume other side is hot
    	{
    		Vision.getInstance().setLeftOrRightHot(1);
    		stop = true;    		
    	}
    	else // keep on trying
    		stop = false;
    
    	
    }

    /**
     * Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        return stop;
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