package org.team2168.commands.catapult;

import org.team2168.commands.CommandBase;

/**
 * The first step in the move sequence to the intermediate tusk position.
 * @author James
 */
public class TusksShortShotPosition_Step1 extends CommandBase {

	/**
	 * Prevents the tusks from trying to change all at the same time
	 * @param delay time to run this command for in seconds 
	 */
	public TusksShortShotPosition_Step1(double delay) {
		requires(catapultTusks);
		setTimeout(delay);
	}

	protected void initialize() {
	}

	protected void execute() {
		catapultTusks.longRangeShot();
	}

	protected void interrupted() {
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
	}
}
