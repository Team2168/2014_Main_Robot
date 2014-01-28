package org.team2168.subsystems

import edu.wpi.first.wpilibj.Talon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem{
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    
	}
	
	Talon rightMotorController;
	Talon leftMotorController;
	Talon centerMotorController;
	
	
	// TODO put in correct inputs
	public Intake(){
		rightMotorController=new Talon(1);
		leftMotorController=new Talon (2);
		centerMotorController=new Talon (3);
	}
	
	void intakeMotorControl(double speed)
	{
		rightMotorController.set(speed);
		leftMotorController.set(speed);
		centerMotorController.set(speed);
	}
	
	void stopMotors(){
		rightMotorController.stopMotor();
		leftMotorController.stopMotor();
		centerMotorController.stopMotor();
	}

	
}
