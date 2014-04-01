package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.intake.IntakeWithJoystick;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeRollers extends Subsystem{
	private static IntakeRollers instance = null;
	private Talon intakeMotorController;

	/**
	 * A private constructor to prevent multiple instances from being created.
	 */
	private IntakeRollers(){
		intakeMotorController = new Talon(RobotMap.intakeMotor.getInt());
	}
	
	/**
	 * @return the instance of this subsystem.
	 */
	public static IntakeRollers getInstance() {
		if (instance == null) {
			instance = new IntakeRollers();
		}
		return instance;
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new IntakeWithJoystick());
	}
	
	/**
	 * Drive the intakes motors.
	 * @param speed value from 1.0 to -1.0, positive brings ball into robot.
	 */
	public void driveIntake(double speed)  //TODO: CHECK SIGN, seems like positive doesn't take the ball into the robot
	{
		intakeMotorController.set(speed);
	}
	
	public void stopMotors(){
		driveIntake(0);
	}
}
