package org.team2168.commands;

import org.team2168.subsystems.Intake;
import org.team2168.subsystems.IntakePosition;

public class AquireBall extends CommandBase{

	Intake intake = new Intake();
	IntakePosition intakePosition = new IntakePosition();
	
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	
	protected void execute() {
		// TODO Auto-generated method stub
		
		intakePosition.intakeDown();
		
		intake.intakeMotorControl(-1);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		intake.stopMotors();
		
		intakePosition.intakeUp();
		
	}

	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}