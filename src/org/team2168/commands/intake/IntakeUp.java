
package org.team2168.commands.intake;

import org.team2168.commands.CommandBase;

/**
 * A command to raise the intake.
 *
 * @author James
 */
public class IntakeUp extends CommandBase {
	/**
	 * Creates a new IntakeRaise command.
	 */
	public IntakeUp() {
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
        return intakePosition.isIntakeUp();
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
