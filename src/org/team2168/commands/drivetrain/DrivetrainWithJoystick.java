package org.team2168.commands.drivetrain;

import org.team2168.OI;
import org.team2168.commands.CommandBase;
import org.team2168.subsystems.Drivetrain;

public class DrivetrainWithJoystick extends CommandBase {

	OI oi = new OI();
	
    public DrivetrainWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drivetrain.drive(oi.getBaseDriverRightStick(),
    			oi.getBaseDriverLeftStick());
    
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
