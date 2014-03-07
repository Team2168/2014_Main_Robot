package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.Sleep;
import org.team2168.commands.drivetrain.AutoDriveXDistance;
import org.team2168.commands.intake.IntakeDown;
import org.team2168.commands.tusks.TusksLongShotPosition;
import org.team2168.commands.winch.Fire;
import org.team2168.commands.winch.Reload;
import org.team2168.commands.winch.WaitUntilFired;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitForChildren;

public class ShootStraight_DrvFwd extends CommandGroup {


	public ShootStraight_DrvFwd() {
		addParallel(new TusksLongShotPosition());
		addParallel(new IntakeDown());

		addSequential(new Sleep(), RobotMap.autoDelayBeforeStart.getDouble());
		// lets see if this works
		addSequential(new WaitForChildren());

		// fire, then drive and reload
		addSequential(new Fire());
		addSequential(new WaitUntilFired());
		
		addParallel(new AutoDriveXDistance(RobotMap.autoDriveDistance.getDouble()));
		addParallel(new Reload());
	}
}
