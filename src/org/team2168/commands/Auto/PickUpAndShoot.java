package org.team2168.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2168.commands.drivetrain.*;
import org.team2168.commands.intake.*;
import org.team2168.commands.winch.ShootBall;
import org.team2168.commands.catapult.*;

public class PickUpAndShoot extends CommandGroup {
	
	//TODO Get actual drive distance, intake speed, and winch speed 
	public PickUpAndShoot(double windWinchSpeed) {
		addSequential(new IntakeDown());
		addSequential(new IntakeDriveMotor(5));
		addSequential(new AutoDriveXDistance(-48));
		//there needs to be some way to turn off the intake after
		//a ball is in right here
		addSequential(new ShootBall(windWinchSpeed));
		
	}

}