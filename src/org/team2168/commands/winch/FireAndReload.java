package org.team2168.commands.winch;

import org.team2168.RobotMap;
import org.team2168.commands.intake.IntakeDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FireAndReload extends CommandGroup {

	public FireAndReload() {
		//fire the ball
		addSequential(new Fire());

		//wait for the catapult to reach its fired position
		addSequential(new WaitUntilFired());
		
		//engage the winch drum
		addSequential(new RetractWinchDogGear());
		
		//retract the catapult all the way
		addSequential(new RetractWinchUntilLowered(
				RobotMap.retractWinchSpeed.getDouble()));
	}
}
