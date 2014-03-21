package org.team2168.commands;

import org.team2168.commands.intake.IntakeUp;
import org.team2168.commands.tapper.DisengageTappers;
import org.team2168.commands.winch.Reload;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Set the default (safe) positions for items going into telop mode. 
 * @author James
 */
public class TeleopDefaults extends CommandGroup {

	public TeleopDefaults() {
		addSequential(new DisengageTappers());
		addSequential(new Reload(), 5.0);
		addSequential(new IntakeUp());
	}
}
