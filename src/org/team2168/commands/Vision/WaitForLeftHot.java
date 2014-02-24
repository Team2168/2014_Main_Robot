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

		stop = false;

		// assume leftside is hot, just in case we timeout
		Vision.getInstance().setLeftOrRightHot(-1);

	}

	/**
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {

		// did we receive a valid frame and does it see a left target
		if (Vision.getInstance().isValidFrame() && (Vision.getInstance().getCamLeftOrRightHot() == -1))
		{
			
			Vision.getInstance().setLeftOrRightHot(-1);
			System.out.println(Vision.getInstance().getLeftOrRightHot() + " (1 for right, -1 for left 0 for none)");
			System.out.println("Took " + this.timeSinceInitialized() + " seconds");
			stop = true;

		} else
			// keep on trying
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