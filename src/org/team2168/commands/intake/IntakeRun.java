
package org.team2168.commands.intake;

import org.team2168.commands.CommandBase;

/**
 * A command to run the intake motors at the specified speed.
 *
 * @author James
 */
public class IntakeRun extends CommandBase {
	double speed = 0.0;
	
	/**
	 * Creates a new RunIntake command.
	 * @param speed the speed to run the intake motors at (1.0 to -1.0)
	 *   positive rolls balls into the robot.
	 */
	public IntakeRun(double speed) {
    	requires(intakeRollers);
    	this.speed = speed;
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
    	intakeRollers.intakeMotorControl(speed);
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
