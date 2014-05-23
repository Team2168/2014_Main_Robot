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
	 * Drive the intake's motors.
	 * @param speed value from 1.0 to -1.0, negative brings ball into robot.
	 */
	public void driveIntake(double speed) {
		intakeMotorController.set(speed);
	}
	
	/**
	 * Drive the intake motors to bring a ball into the robot.
	 * @param speed A negative value, -1.0 to 0.0
	 */
	public void driveIntakeIn(double speed) {
		if (speed > 0.0)
			speed = -speed;

		driveIntake(speed);
	}

	/**
	 * Drive the intake motors to take a ball out of the robot.
	 * @param speed A positive value, 1.0 to 0.0
	 */
	public void driveIntakeOut (double speed) {
		driveIntake(Math.abs(speed));
	}

	public void stopMotors(){
		driveIntake(0);
	}
}
