package org.team2168.commands.winch;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Reload extends CommandGroup{
	
	public Reload(double windWinchSpeed) {
		addSequential(new RetractWinchMotor(windWinchSpeed));
		addSequential(new RetractWinchDogGear());
		
	}
}
