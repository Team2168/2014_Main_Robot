package org.team2168.commands.winch;

import org.team2168.commands.CommandBase;

public class WinchDriveMotor extends CommandBase{

	double speed;
	
	public WinchDriveMotor(double speed) {
		requires(catapultWinch);
		this.speed = speed;
	}
	
	protected void initialize() {
		
	}

	protected void execute() {
		catapultWinch.setCatapult(speed);
	}

	protected boolean isFinished() {
		return catapultWinch.isCatapultSet();
	}

	protected void end() {
		catapultWinch.setCatapult(0);
	}

	protected void interrupted() {
		catapultWinch.setCatapult(0);
	}

}
