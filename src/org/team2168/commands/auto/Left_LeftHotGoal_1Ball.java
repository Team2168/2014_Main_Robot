package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.Sleep;
import org.team2168.commands.drivetrain.AutoDriveXDistance;
import org.team2168.commands.drivetrain.StupidDriveFwd;
import org.team2168.commands.intake.IntakeDown;
import org.team2168.commands.intake.IntakeDriveMotor;
import org.team2168.commands.intake.IntakeUp;
import org.team2168.commands.tapper.DisengageTappers;
import org.team2168.commands.tapper.EngageTappers;
import org.team2168.commands.tusks.TusksLongShotPosition;
import org.team2168.commands.vision.WaitForLeftHot;
import org.team2168.commands.winch.Fire;
import org.team2168.commands.winch.Reload;
import org.team2168.commands.winch.WaitUntilFired;

public class Left_LeftHotGoal_1Ball extends AutoCommandGroup {
	public static final String name = "1 Ball - Robot on Left - Wait for Left Hot";

	public Left_LeftHotGoal_1Ball() {
super(name);
		
		// set tusks to long shot
		addParallel(new TusksLongShotPosition());

		// move intake out of way, spin wheel to keep ball in robot
		addParallel(new IntakeDriveMotor(
				RobotMap.intakeWheelSpeedWhenLowering.getDouble()));
		addSequential(new IntakeDown(), 1.5);
		addParallel(new IntakeDriveMotor(0.0));
		addSequential(new EngageTappers());
		
		addSequential(new WaitForLeftHot(), 6 - RobotMap.autoBallSettleTime.getDouble());


		// Drive forward to inscrease likelyhood of shoot and gain 5 pts
		//addSequential(new AutoDriveXDistance(RobotMap.autoDriveDistance.getDouble()));
		
		// fire
		addSequential(new Fire());
		addSequential(new WaitUntilFired());
		
		//drive fwd for pts
		
		//stop gap to get 5pts in auto, this doesn't necessarily drive straight!
		//REMOVE when we figure out why drive straight isn't working
		addSequential(new AutoDriveXDistance(RobotMap.autoDriveDistance.getDouble()));
		
		//Get ready for the match 
		addParallel(new DisengageTappers());
//		addParallel(new IntakeUp());
		addParallel(new Reload());
	}
	
	public int numBalls() {
		return 1;
	}
}
