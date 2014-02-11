package org.team2168.commands;

import org.team2168.subsystems.Drivetrain;

public class TusksLongShotPosition extends CommandBase {
	
	/**
	 * Prevents the tusks from trying to change all at the same time
	 */
	public TusksLongShotPosition() {
		requires(catapultTusks); 
	}
	
	protected void end() {
		// TODO Auto-generated method stub
	}

	protected void execute() {
		// TODO Auto-generated method stub
		catapultTusks.longRangeShot();

	}

	protected void initialize() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
