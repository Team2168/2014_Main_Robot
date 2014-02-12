package org.team2168.commands;

public class TusksLongShotPosition extends CommandBase {

	/**
	 * Prevents the tusks from trying to change all at the same time
	 */
	public TusksLongShotPosition() {
		requires(catapultTusks);
	}

	protected void initialize() {
	}

	protected void execute() {
		catapultTusks.longRangeShot();
	}

	protected void interrupted() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}
}
