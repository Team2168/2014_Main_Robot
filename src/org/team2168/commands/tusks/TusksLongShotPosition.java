package org.team2168.commands.tusks;

import org.team2168.ArduinoInterface;
import org.team2168.commands.CommandBase;

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
		ArduinoInterface.getInstance().set(2, false);
		ArduinoInterface.getInstance().set(3, true);
	}
}
