package org.team2168.commands.winch;

import org.team2168.commands.tapper.EngageTappers;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutomaticEngageTappers extends CommandGroup {

	public AutomaticEngageTappers() {
		addSequential(new WaitUntilBallPresent());
		addSequential(new WaitUntilBallNotPresent());
		addSequential(new EngageTappers());
	}
}
