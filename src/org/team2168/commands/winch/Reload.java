package org.team2168.commands.winch;

import org.team2168.RobotMap;
import org.team2168.commands.tapper.DisengageTappers;
import org.team2168.commands.tusks.TusksTrussShotPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Reload extends CommandGroup {

	public Reload() {
		//engage the winch drum
		addSequential(new RetractWinchDogGear());
		
		//Retract the tusks in preparation for intaking next ball
		addSequential(new TusksTrussShotPosition());
		
		//addParallel(new DisengageTappers());
		
		//retract the catapult all the way
		addSequential(new RetractWinchUntilLowered(
				RobotMap.retractWinchSpeed.getDouble()));
	}
}
