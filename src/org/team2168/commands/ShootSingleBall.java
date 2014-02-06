package org.team2168.commands;

import org.team2168.subsystems.*;

public class ShootSingleBall extends CommandBase{

	
	CatapultWinch catapultWinch = new CatapultWinch();

	protected void initialize() {
		// TODO Auto-generated method stub
		
	}


	protected void execute() {
		// TODO Auto-generated method stub
		catapultWinch.setCatapult(-1);
		
		while(!catapultWinch.isCatapultSet())
		{	
		}
		
		catapultWinch.closeLatch();
		catapultWinch.setCatapult(0);
		
		catapultWinch.openLatch();
		
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
