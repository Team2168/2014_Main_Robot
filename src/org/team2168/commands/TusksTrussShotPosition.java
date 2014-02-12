package org.team2168.commands;

public class TusksTrussShotPosition extends CommandBase {

	/**
	 * Prevents the tusks from trying to change all at the same time
	 */
	public TusksTrussShotPosition() {
		requires(catapultTusks);
	}

	protected void initialize() {
	}

	protected void execute() {
		catapultTusks.trussShot();
	}

	protected void interrupted() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}
}
