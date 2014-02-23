package org.team2168.commands.Vision;

import org.team2168.RobotMap;
import org.team2168.commands.CommandBase;
import org.team2168.subsystems.Vision;

/**
 * A command to 
 *
 * @author Kevin
 */
public class WaitForRightHot extends CommandBase {
	
	private boolean stop;

	
	
	/**
	 * Creates a new IntakeRaise command.
	 */
	public WaitForRightHot() {
    	requires(vision);
    	
    }

    /**
     * Called just before this Command runs the first time
     */
    protected void initialize() {
    	

    	stop = false;
    	
    	//assume leftside is hot
    	Vision.getInstance().setLeftOrRightHot(-1);
    	
    }

    /**
     * Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
    	
    	
    	//if right is hot and we didnt time out
    	if (Vision.getInstance().isRightHot() && (this.timeSinceInitialized() > RobotMap.CameraSteadyStateSecs.getDouble()))
    	{
    		
    			Vision.getInstance().setLeftOrRightHot(1);
    			System.out.println("Right Hot");
    			stop = true;
    
    	}
    	else // keep on trying
    		stop = false;
    	
    	if(this.isTimedOut())
    		System.out.println("Left Hot");
    	
    	
    	
    
    	
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