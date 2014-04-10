package org.team2168.commands;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StopCompressor extends Command {

	public StopCompressor() {
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.stopCompressor();
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
