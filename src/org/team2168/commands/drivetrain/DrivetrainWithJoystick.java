
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
    	//TODO: one of these will need to be negated
    	drivetrain.drive(oi.getBaseDriverRightStick(),
    			oi.getBaseDriverLeftStick());
    	
    	System.out.println("Left Encoder: "  + Drivetrain.getInstance().getLeftEncoderDistance());
    	System.out.println("Right Encoder: "  + Drivetrain.getInstance().getRightEncoderDistance());
    	System.out.println("GYRO Angle: "  + Drivetrain.getInstance().getGyroAngle());
    	System.out.println("");
    	
    
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
