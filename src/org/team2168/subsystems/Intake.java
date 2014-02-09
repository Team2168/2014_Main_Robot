package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem{
	private static Intake instance = null;
	private Talon intakeMotorController;

	/**
	 * A private constructor to prevent multiple instances from being created.
	 */
	private Intake(){
		intakeMotorController = new Talon(RobotMap.intakeMotor.getInt());
	}
	
	/**
	 * @return the instance of this subsystem.
	 */
	public static Intake getInstance() {
		if (instance == null) {
			instance = new Intake();
		}
		return instance;
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
