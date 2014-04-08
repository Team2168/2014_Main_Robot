/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team2168;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.team2168.commands.CommandBase;
import org.team2168.commands.TeleopDefaults;
import org.team2168.commands.auto.*;
import org.team2168.subsystems.Vision;
import org.team2168.subsystems.Winch;
import org.team2168.subsystems.Drivetrain;
import org.team2168.utils.ConsolePrinter;
import org.team2168.utils.ConstantsBase;
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
	private static boolean matchStarted = false;

	private ArduinoInterface arduino = ArduinoInterface.getInstance();

	SendableChooser autoChooser;
	private static AutoCommandGroup autonomousCommand;
	private static Command teleopInitCommand;

	DriverStationLCD lcd;
	ConsolePrinter printer;


	
	/**
	 * This method is run when the robot is first powered on.
	 */
	public void robotInit() {
		compressor = new Compressor(RobotMap.pressureSwitch.getInt(),
				RobotMap.compressorRelay.getInt());
		
		printer = new ConsolePrinter(200);

		// Initialize all subsystems
		CommandBase.init();

		lcd = DriverStationLCD.getInstance();
		
		//Initialize auto mode chooser
		autoSelectInit();

		printer.startThread();
		
		//Console Message so we know robot finished loading
		System.out.println("****Robot Done Loading****");
	}

	/**
	 * This method is run once each time the robot is disabled.
	 */
	public void disabledInit() {
		//prevent null case if entering telop during testing
		autonomousCommand = (AutoCommandGroup) autoChooser.getSelected();
		teleopInitCommand = new TeleopDefaults();
		
		Winch.getInstance().resetWinchEncoder();
		Drivetrain.getInstance().resetEncoders();

		ConstantsBase.readConstantsFromFile();
	}

	/**
	 * This method is run repeatedly while the robot is disabled.
	 */
	public void disabledPeriodic() {
		autonomousCommand = (AutoCommandGroup) autoChooser.getSelected();
		lcd.println(DriverStationLCD.Line.kUser2, 1, autonomousCommand.getName());
		lcd.updateLCD();

		//Kill all active commands
		Scheduler.getInstance().removeAll();
		Scheduler.getInstance().disable();
		
		//Check to see if the gyro is drifting, if it is re-initialize it.
		//Thanks FRC254.
		double curAngle = CommandBase.drivetrain.getGyroAngle();
		if (gyroDriftDetector
				.update(Math.abs(curAngle - lastAngle) > (.75 / 50.0))
				&& gyroReinits < 3 && !matchStarted) {
			gyroReinits++;
			System.out.println("!!! Sensed drift, about to auto-reinit gyro ("+ gyroReinits + ")");
			CommandBase.drivetrain.reinitGyro();
			CommandBase.drivetrain.resetGyro();
			gyroDriftDetector.reset();
			curAngle = CommandBase.drivetrain.getGyroAngle();
			System.out.println("Finished auto-reinit gyro");
		}
		lastAngle = curAngle;
		
		setArduinoAutonomousStatuses();
	}

	/**
	 * This method is run once each time the robot enters autonomous mode.
	 */
	public void autonomousInit() {
		Scheduler.getInstance().enable();

		//Run the selected auto mode
		autonomousCommand.start();

		//prevent gyro from initializing between auto and teleop
		matchStarted = true;
		
		//No compressor for auto mode, lower battery load
		//compressor.start();
	}

	/**
	 * This method is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
		setArduinoAutonomousStatuses();
	}

	/**
	 * This method is run once each time the robot enters teleoperated mode.
	 */
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		
		//don't try to cancel a command if it isn't initialized yet
		if(autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		Scheduler.getInstance().enable();

		if(DriverStation.getInstance().isFMSAttached())
			teleopInitCommand.start();

		compressor.start();
		
		//Turn all Arduino pins when we leave auto mode.
		arduino.reset();
	}

	/**
	 * This method is called periodically during teleoperated mode.
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
	
	private void setArduinoAutonomousStatuses() {
		//Set Hot Goal bits for arduino lights
		if(!Vision.getInstance().getCamLeftHot() &&
		   !Vision.getInstance().getCamRightHot()) {
			//neither goal is hot... error
			arduino.set(0, true);
			arduino.set(1, true);
		} else {
			arduino.set(0, Vision.getInstance().getCamLeftHot());
			arduino.set(1, Vision.getInstance().getCamRightHot());
		}
		
		//Let the arduino know the number of balls we're going to shoot in auto.		
		if(autonomousCommand.numBalls() == 2) {
			arduino.set(2, true);
		} else {
			arduino.set(2, false);
		}
 	}

	private void autoSelectInit() {
		//NOTE: ONLY ADD AutoCommandGroup objects to this chooser!
		autoChooser = new SendableChooser();
		autoChooser.addDefault(Right_RightHotGoal_2Ball.name, new Right_RightHotGoal_2Ball());
		autoChooser.addObject(ShootStraight_DrvFwd.name, new ShootStraight_DrvFwd());
		autoChooser.addObject(Left_LeftHotGoal_1Ball.name, new Left_LeftHotGoal_1Ball());
		autoChooser.addObject(Right_RightHotGoal_1Ball.name, new Right_RightHotGoal_1Ball());
		autoChooser.addObject(NoBall_DrvFwd.name, new NoBall_DrvFwd());
		autoChooser.addObject("Center_RotHotGoal_1Ball", new Center_RotHotGoal_1Ball(RobotMap.VisionTimeOutSecs.getDouble()));
		autoChooser.addObject("Center_RotDrvFwdHotGoal_1Ball", new Center_RotDrvFwdHotGoal_1Ball(RobotMap.VisionTimeOutSecs.getDouble()));
		autoChooser.addObject("ShootStraight_2BallDrvFwd", new ShootStraight_2Ball_DrvFwd());
		SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);
	}

	public static String getAutoName()
	{
		if(autonomousCommand != null)
			return autonomousCommand.getName();
		else
			return "None";
		
	}
}
