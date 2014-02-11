
package org.team2168.commands;

/**
 * A command to raise the intake.
 *
 * @author James
 */
public class IntakeRaise extends CommandBase {
	/**
	 * Creates a new IntakeRaise command.
	 */
	public IntakeRaise() {
    	requires(intakePosition);
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
    	intakePosition.intakeUp();
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
