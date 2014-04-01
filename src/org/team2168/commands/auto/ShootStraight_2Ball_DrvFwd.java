package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.Sleep;
import org.team2168.commands.drivetrain.AutoDriveXDistance;
import org.team2168.commands.intake.IntakeDown;
import org.team2168.commands.intake.IntakeDriveMotor;
import org.team2168.commands.tusks.TusksLongShotPosition;
import org.team2168.commands.tusks.TusksShortShotPosition;
import org.team2168.commands.tusks.TusksTrussShotPosition;
import org.team2168.commands.winch.Fire;
import org.team2168.commands.winch.FireAndReload;
import org.team2168.commands.winch.Reload;
import org.team2168.commands.winch.WaitUntilFired;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitForChildren;

public class ShootStraight_2Ball_DrvFwd extends CommandGroup {


	public ShootStraight_2Ball_DrvFwd() {
		//Extend the tusks and drive wheels to prevent the ball from
		//  falling backwards.
		addParallel(new IntakeDriveMotor(-0.15));
		
		//once the intake is down, stop driving the intake motors
		addSequential(new IntakeDown(), RobotMap.intakeLowerTimeout.getDouble());
		addSequential(new TusksLongShotPosition());
		//Stop intaking
		addParallel(new IntakeDriveMotor(0.0));
		
		//ball settle
		addSequential(new Sleep(), RobotMap.autoBallSettleTime.getDouble());
		addSequential(new FireAndReload());
		
		//Move tusks in so we can intake ball
		addSequential(new TusksTrussShotPosition());
		
		//Run the intake to pick up another ball.
		addParallel(new IntakeDriveMotor(-1.0));
		addSequential(new Sleep(), RobotMap.autoIntakeRunTime.getDouble());
		
		addSequential(new TusksLongShotPosition());
		addParallel(new IntakeDriveMotor(0.0));
		
		//Ball settle
		addSequential(new Sleep(), RobotMap.autoBallSettleTime.getDouble());
		
		// fire, then drive and reload
		addSequential(new Fire());
		addSequential(new WaitUntilFired());
		
		addSequential(new AutoDriveXDistance(RobotMap.autoDriveDistance.getDouble()));
		
		addSequential(new TusksTrussShotPosition());
		
		addParallel(new Reload());
		
		addSequential(new AutoDriveXDistance(80));
		

	}
}
