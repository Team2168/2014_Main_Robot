package org.team2168.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public abstract class AutoCommandGroup extends CommandGroup {

	public AutoCommandGroup() {
		super();
	}

	public AutoCommandGroup(String name) {
		super(name);
	}
	
	public abstract int numBalls();
}
