package org.team2168.commands.tusks;

import org.team2168.commands.CommandBase;

/**
 * The second step in the move sequence to the intermediate tusk position.
 * @author James
 */
public class TusksShortShotPosition_Step2 extends CommandBase {

	/**
	 * Prevents the tusks from trying to change all at the same time
	 */
	public TusksShortShotPosition_Step2() {
		requires(catapultTusks);
	}

	protected void initialize() {
	}

	protected void execute() {
		catapultTusks.shortRangeShot();
	}

	protected void interrupted() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}
}
