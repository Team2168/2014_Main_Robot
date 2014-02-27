package org.team2168.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.team2168.commands.drivetrain.*;
import org.team2168.commands.intake.*;
import org.team2168.commands.winch.ShootBall;
import org.team2168.commands.winch.WaitForBall;
import org.team2168.commands.catapult.*;

public class PickUpAndShoot extends CommandGroup {
	
	//TODO Get actual drive distance, intake speed, and winch speed 
	public PickUpAndShoot(double windWinchSpeed, double waitForBallTimeout) {
		addSequential(new IntakeDown());
		addSequential(new IntakeDriveMotor(5));
		addSequential(new AutoDriveXDistance(-48));
		addSequential(new WaitForBall(), waitForBallTimeout);
		addSequential(new ShootBall(windWinchSpeed));
		
	}

}