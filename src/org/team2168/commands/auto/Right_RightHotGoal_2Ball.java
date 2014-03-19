package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.AutoDriveXDistance;
import org.team2168.commands.intake.IntakeDown;
import org.team2168.commands.intake.IntakeDriveMotor;
import org.team2168.commands.intake.IntakeSingleBall;
import org.team2168.commands.intake.IntakeUp;
import org.team2168.commands.tapper.DisengageTappers;
import org.team2168.commands.tapper.EngageTappers;
import org.team2168.commands.tusks.TusksLongShotPosition;
import org.team2168.commands.winch.Fire;
import org.team2168.commands.winch.FireAndReload;
import org.team2168.commands.winch.Reload;
import org.team2168.commands.winch.WaitUntilBallSettled;
import org.team2168.commands.winch.WaitUntilFired;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Right_RightHotGoal_2Ball extends CommandGroup {

	public Right_RightHotGoal_2Ball() {
		// set tusks to long shot
		addParallel(new TusksLongShotPosition());

		// move intake out of way, spin wheel to keep ball in robot
		addParallel(new IntakeDriveMotor(-0.25));
		addSequential(new IntakeDown(), 1);
		addParallel(new IntakeDriveMotor(0.0));
		addSequential(new EngageTappers());
		
		// wait for ball to settle
		addSequential(new WaitUntilBallSettled(),
				RobotMap.autoBallSettleTime.getDouble());

		// First ball
		addSequential(new FireAndReload());

		// intake second ball
		addSequential(new IntakeSingleBall(), 3.0);
		addSequential(new EngageTappers());

		// set tusks
		addParallel(new TusksLongShotPosition());

		// wait for ball to settle
		addSequential(new WaitUntilBallSettled(),
				RobotMap.autoBallSettleTime.getDouble());

		// Second Ball
		addSequential(new Fire());
		addSequential(new WaitUntilFired());
		
		//drive fwd for pts
		addSequential(new AutoDriveXDistance(RobotMap.autoDriveDistance.getDouble()));
		
		//Get ready for the match 
		addParallel(new DisengageTappers());
		addParallel(new IntakeUp());
		addParallel(new Reload());
	}
}
