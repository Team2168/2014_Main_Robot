package org.team2168.commands.winch;

import org.team2168.commands.CommandBase;

public class WaitForBall extends CommandBase{
	
	private boolean finished;
	
	public WaitForBall() {
		requires(catapultWinch);
	}
	
	protected void initialize() {
		finished = false;
	}

	protected void execute() {
		finished = catapultWinch.isBallPresent();
	}

	protected boolean isFinished() {
		return finished;
	}

	protected void end() {
		// TODO Auto-generated method stub
		
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	

}
