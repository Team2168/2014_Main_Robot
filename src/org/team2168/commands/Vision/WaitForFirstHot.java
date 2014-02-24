package org.team2168.commands.Vision;

import org.team2168.RobotMap;
import org.team2168.commands.CommandBase;
import org.team2168.subsystems.Vision;

/**
 * A command waits for a valid frame and determines if the left or right goal is hot.
 * This command returns immediately after received a valid frame from the camera.
 * If the frame contains a hot goal, the command indicates that side is hot, if the
 * frame containes no hot goals, the command indicates the other side is hot.
 * 
 * @author Kevin
 */

public class WaitForFirstHot extends CommandBase {

	private boolean stop;

	/**
	 * Creates a new IntakeRaise command.
	 */
	public WaitForFirstHot() {
		requires(vision);

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

		// did we receive a valid frame?
		if (Vision.getInstance().isValidFrame())
		{
			
			Vision.getInstance().setLeftOrRightHot(Vision.getInstance().getCamLeftOrRightHot());
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