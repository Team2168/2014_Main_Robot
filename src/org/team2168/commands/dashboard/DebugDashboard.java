package org.team2168.commands.dashboard;

import org.team2168.commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DebugDashboard extends CommandBase {

    public DebugDashboard() {
      
    	SmartDashboard.putData(drivetrain);
        SmartDashboard.putData(catapultTusks);
        SmartDashboard.putData(catapultWinch);
        SmartDashboard.putData(intakePosition);
        SmartDashboard.putData(vision);
    
        SmartDashboard.putString("compTime", "");
        
    }
    

    protected void initialize() {
    }

    protected void execute() {
    	
    	
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}