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
import org.team2168.commands.winch.Reload;
import org.team2168.commands.winch.WaitUntilFired;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitForChildren;

public class ShootStraight_DrvFwd extends CommandGroup {
	public static final String name = "1 Ball, Shoot Straight Drive Fwd";

	public ShootStraight_DrvFwd() {
		super(name);
		//Extend the tusks and drive wheels to prevent the ball from
		//  falling backwards.
		addParallel(new IntakeDriveMotor(-0.15));
		
		//once the intake is down, stop driving the intake motors
		addSequential(new IntakeDown(), RobotMap.intakeLowerTimeout.getDouble());
		addSequential(new TusksLongShotPosition());
		
		//TODO: replace with parallel wait for children when there's time to test that
		addSequential(new Sleep(), RobotMap.autoDelayBeforeStart.getDouble());
		
		addParallel(new IntakeDriveMotor(0.0));
		
		//ball settle
		addSequential(new Sleep(), RobotMap.autoDelayBeforeStart.getDouble());
		
		addSequential(new AutoDriveXDistance(RobotMap.autoDriveDistance.getDouble()/2));
		
		// fire, then drive and reload
		addSequential(new Fire());
		addSequential(new WaitUntilFired());
		
		addSequential(new AutoDriveXDistance(RobotMap.autoDriveDistance.getDouble()/2));
		
		addSequential(new TusksTrussShotPosition());
		
		addParallel(new Reload());
	}
}
