package org.team2168.commands.winch;

import org.team2168.commands.CommandBase;

public class RetractWinchMotor extends CommandBase{

	double speed;
	
	public RetractWinchMotor(double speed) {
		requires(catapultWinch);
		this.speed = speed;
	}
	
	protected void initialize() {
		
	}

	protected void execute() {
		catapultWinch.driveWinch(speed);
	}

	protected boolean isFinished() {
		return catapultWinch.isCatapultRetracted();
	}

	protected void end() {
		catapultWinch.driveWinch(0);;
	}

	protected void interrupted() {
		catapultWinch.driveWinch(0);;
	}

}
