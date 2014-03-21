package org.team2168.commands.intake;

import org.team2168.commands.tapper.DisengageTappers;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This command raises the intake and moves any components which could be
 *   damaged by this action out of the way.
 * 
 * @author James
 */
public class IntakeUp_Safe extends CommandGroup {

	public IntakeUp_Safe() {
		addSequential(new DisengageTappers());
		addSequential(new IntakeUp());
	}
}
