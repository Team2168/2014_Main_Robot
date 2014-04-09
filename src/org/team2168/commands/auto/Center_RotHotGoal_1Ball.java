package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.AutoDriveXDistance;
import org.team2168.commands.drivetrain.RotateDrivetrainRelative;
import org.team2168.commands.intake.IntakeDown;
import org.team2168.commands.intake.IntakeDriveMotor;
import org.team2168.commands.intake.IntakeUp;
import org.team2168.commands.tapper.DisengageTappers;
import org.team2168.commands.tapper.EngageTappers;
import org.team2168.commands.tusks.TusksLongShotPosition;
import org.team2168.commands.vision.WaitForFirstHot;
import org.team2168.commands.winch.Fire;
import org.team2168.commands.winch.FireAndReload;
import org.team2168.commands.winch.Reload;
import org.team2168.commands.winch.WaitUntilBallSettled;
import org.team2168.commands.winch.WaitUntilFired;

/**
 * This command is for Auto
 * It performs a two ball two hot atonomous mode
 * The command assumes the Robot is placed in the center of the field facing fwd and the Vision camera is
 * facing the right hot goal target.
 * 
 * The robot will rotate to the Goal that is not hot, and 
 * @author kevin
 *
 */
public class Center_RotHotGoal_1Ball extends AutoCommandGroup{
	
	public static final String name = "1 Ball - Robot In Center - 1 Hot";

	public Center_RotHotGoal_1Ball() {
		super(name);
		
		// set tusks to long shot
		addParallel(new TusksLongShotPosition());

		// move intake out of way, spin wheel to keep ball in robot
		addParallel(new IntakeDriveMotor(
				RobotMap.intakeWheelSpeedWhenLowering.getDouble()));
		addSequential(new IntakeDown(), 1.5);
		addParallel(new IntakeDriveMotor(0.0));
		addSequential(new EngageTappers());
		
		//wait for hot goal and rotate to other goal.
		// Rotate DriveTrain = +/- min rotation angle, this will get angle
		//   from camera once executed
		addSequential(new WaitForFirstHot(),3);
		addSequential(new RotateDrivetrainRelative(0.0,true,true));
		
		// wait for ball to settle
		addSequential(new WaitUntilBallSettled(), 2.0);


		// fire
		addSequential(new Fire());
		addSequential(new WaitUntilFired());
		
		//drive fwd for pts
		addSequential(new AutoDriveXDistance(RobotMap.autoDriveDistance.getDouble()));
		
		//Get ready for the match 
		addParallel(new DisengageTappers());
		addParallel(new IntakeUp());
		addParallel(new Reload());
	}
	
	public int numBalls() {
		return 1;
	}
}
