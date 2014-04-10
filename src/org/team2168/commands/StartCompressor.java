package org.team2168.commands;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StartCompressor extends Command {

	public StartCompressor() {
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.startCompressor();
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}

}
