package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem{
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    
	}
	
	Talon intakeMotorController;
	
	// TODO put in correct inputs
	public Intake(){
		intakeMotorController = new Talon(RobotMap.intakeMotorController);
	}
	
	void intakeMotorControl(double speed)
	{
		intakeMotorController.set(speed);
	}
	
	void stopMotors(){
		intakeMotorController.set(0);
	}

	
}
