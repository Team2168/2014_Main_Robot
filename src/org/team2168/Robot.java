/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team2168;

import org.team2168.PIDController.Sensors.TCPCameraSensor;
import org.team2168.commands.ExampleCommand;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.team2168.commands.CommandBase;
import org.team2168.utils.Debouncer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {


	private int gyroReinits;
	private double lastAngle;
	private Debouncer gyroDriftDetector = new Debouncer(1.0);
	private Compressor compressor;

	TCPCameraSensor cam = new TCPCameraSensor(1111, 1000);

	
	Command autonomousCommand;

	/**
	 * This method is run when the robot is first powered on.
	 */
	public void robotInit() {
		// instantiate the command used for the autonomous period
		autonomousCommand = new ExampleCommand();

		compressor = new Compressor(RobotMap.pressureSwitch.getInt(),
				RobotMap.compressorRelay.getInt());

		// Initialize all subsystems
		CommandBase.init();
		
		cam.start();

		//Console Message so we know robot finished loading
		System.out.println("****Robot Done Loading****");


	}

	/**
	 * This method is run once each time the robot is disabled.
	 */
	public void disabledInit() {


	}

	/**
	 * This method is run repeatedly while the robot is disabled.
	 */
	public void disabledPeriodic() {
		
		
		
		//Check to see if the gyro is drifting, if it is re-initialize it.
		//Thanks FRC254.
		double curAngle = CommandBase.drivetrain.getGyroAngle();
		if (gyroDriftDetector
				.update(Math.abs(curAngle - lastAngle) > (.75 / 50.0))
				&& gyroReinits < 3) {
			gyroReinits++;
			System.out.println("!!! Sensed drift, about to auto-reinit gyro ("
					+ gyroReinits + ")");
			CommandBase.drivetrain.reinitGyro();
			CommandBase.drivetrain.resetGyro();
			gyroDriftDetector.reset();
			curAngle = CommandBase.drivetrain.getGyroAngle();
			System.out.println("Finished auto-reinit gyro");
		}
		lastAngle = curAngle;
	}

	/**
	 * This method is run once each time the robot enters autonomous mode.
	 */
	public void autonomousInit() {
		// schedule the autonomous command (example)
		autonomousCommand.start();
		
		//No compressor for auto mode, lower battery load
		//compressor.start();
	}

	/**
	 * This method is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This method is run once each time the robot enters teleoperated mode.
	 */
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		autonomousCommand.cancel();
		
		compressor.start();
	}

	/**
	 * This methof is called periodically during teleoperated mode.
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This method is called periodically during test mode.
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
