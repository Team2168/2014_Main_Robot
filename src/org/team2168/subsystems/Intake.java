package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem{
	Talon intakeMotorController;

	public Intake(){
		intakeMotorController = new Talon(RobotMap.intakeMotor.getInt());
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    
	}
	
	public void intakeMotorControl(double speed)
	{
		intakeMotorController.set(speed);
	}
	
	public void stopMotors(){
		intakeMotorController.set(0);
	}
}
