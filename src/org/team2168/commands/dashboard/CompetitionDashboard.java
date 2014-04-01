package org.team2168.commands.dashboard;

import org.team2168.commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class CompetitionDashboard extends CommandBase {

    public CompetitionDashboard() {
      
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
    	SmartDashboard.putBoolean("IsCatapultRetrated", 
    			catapultWinch.isCatapultRetracted());
    	SmartDashboard.putBoolean("IsBallPresent", catapultWinch.isBallPresent());
    	SmartDashboard.putBoolean("IntakeUp",intakePosition.isIntakeUp());
    	SmartDashboard.putBoolean("IntakeDown",intakePosition.isIntakeDown());
    	SmartDashboard.getBoolean("DogGearExtended", 
    			catapultWinch.isDogGearExtended());
    	SmartDashboard.getBoolean("DogGearRetracted", 
    			catapultWinch.isDogGearRetracted());
    	
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

