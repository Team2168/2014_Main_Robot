package org.team2168.commands.Auto;

import org.team2168.commands.drivetrain.AutoDriveXDistance;
import org.team2168.commands.winch.ShootBall;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootAndDrive extends CommandGroup{
	
	//TODO Get actual drive distance and replace windWinchSpeed
	public ShootAndDrive(double windWinchSpeed) {
		addSequential(new ShootBall(windWinchSpeed));
		addSequential(new AutoDriveXDistance(24));
	}

}
