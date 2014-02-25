package org.team2168.commands.catapult;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A command to move the tusks to the intermediate position.
 * First retract the 2nd stage of the cylinder, then extend to the intermediate
 * position.
 * 
 * @author James
 */
public class TusksShortShotPosition extends CommandGroup {
	public TusksShortShotPosition() {
		//Move the tusks to the extended position.
		addSequential(new TusksShortShotPosition_Step1(
				RobotMap.tuskIntermediatePositionDelay.getDouble()));
		//Then back into the intermediate position. 
		addSequential(new TusksShortShotPosition_Step2());
	}
}