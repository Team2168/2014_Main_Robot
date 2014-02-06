package org.team2168.commands;

import org.team2168.subsystems.CatapultWinch;

public class CloseLatch extends CommandBase{

	CatapultWinch catapultWinch = new CatapultWinch();
	
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	protected void execute() {
		// TODO Auto-generated method stub
		catapultWinch.closeLatch();
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void end() {
		// TODO Auto-generated method stub
		
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
}
