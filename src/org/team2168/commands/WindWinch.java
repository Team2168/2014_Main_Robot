package org.team2168.commands;

public class WindWinch extends CommandBase{

	boolean finished;
	
	public WindWinch() {
		requires(catapultWinch);
	}
	
	protected void initialize() {
		finished = false;
	}

	protected void execute() {
		catapultWinch.setCatapult(0.3);
		if (catapultWinch.isCatapultSet()) {
			finished = true;
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		catapultWinch.setCatapult(0);
	}

	protected void interrupted() {
		catapultWinch.setCatapult(0);
		catapultWinch.open();
	}

}
