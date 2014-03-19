package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.Sleep;
import org.team2168.commands.drivetrain.AutoDriveXDistance;
import org.team2168.commands.intake.IntakeDown;
import org.team2168.commands.intake.IntakeDriveMotor;
import org.team2168.commands.intake.IntakeSingleBallAndSettle;
import org.team2168.commands.tusks.TusksLongShotPosition;
import org.team2168.commands.vision.WaitForLeftHot;
import org.team2168.commands.vision.WaitForRightHot;
import org.team2168.commands.winch.Fire;
import org.team2168.commands.winch.FireAndReload;
import org.team2168.commands.winch.Reload;
import org.team2168.commands.winch.WaitUntilFired;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitForChildren;

public class Right_RightHotGoal_2Ball extends CommandGroup {


	public Right_RightHotGoal_2Ball() {

		// set tusks to long shot
		addParallel(new TusksLongShotPosition());

		//move intake out of way, spin wheel to keep ball in robot
		addParallel(new IntakeDriveMotor(-0.25));
		addSequential(new IntakeDown(), 1);
		addParallel(new IntakeDriveMotor(0.0));
		
		//wait for ball to settle
		addSequential(new Sleep(), RobotMap.autoBallSettleTime.getDouble());

		//First ball
		addSequential(new FireAndReload());

		//intake second ball
		addSequential(new IntakeSingleBallAndSettle(),3);

		//set tusks
		addParallel(new TusksLongShotPosition());
		
		//wait for ball to settle
		addSequential(new Sleep(), RobotMap.autoBallSettleTime.getDouble());
		
		//Second Ball
		addSequential(new Fire());
	}
}
