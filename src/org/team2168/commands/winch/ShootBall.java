package org.team2168.commands.winch;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2168.commands.catapult.*;
import org.team2168.commands.CommandBase;

public class ShootBall extends CommandGroup{

	public ShootBall(double windWinchSpeed) {
		if (!CommandBase.catapultWinch.isCatapultRetracted()) {
			addSequential(new Reload(windWinchSpeed));
		}
		
		addSequential(new ExtendWinchDogGear());
		addSequential(new Reload(windWinchSpeed));
		
	}
}