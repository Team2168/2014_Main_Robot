package org.team2168.commands.intake;

import org.team2168.commands.tapper.EngageTappers;
import org.team2168.commands.winch.WaitUntilBallSettled;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeSingleBallAndSettle extends CommandGroup {

	public IntakeSingleBallAndSettle() {
		addSequential(new IntakeSingleBall());
		addSequential(new EngageTappers());
		addSequential(new WaitUntilBallSettled());
	}
}
