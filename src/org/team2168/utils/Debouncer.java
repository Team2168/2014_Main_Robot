package org.team2168.utils;

import edu.wpi.first.wpilibj.Timer;

/**
 * Compensates for "jumps" in analog signals sources.
 * 
 * Original source from:
 * https://github.com/Team254/FRC-2013/blob/master/src/com/team254/lib/util/Debouncer.java
 * 
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class Debouncer {
	Timer t = new Timer();
	double time;
	boolean first = true;

	public Debouncer(double time) {
		this.time = time;
	}

	public boolean update(boolean val) {
		if (first) {
			first = false;
			t.start();
		}
		if (!val) {
			t.reset();
		}
		return t.get() > time;
	}

	public void reset() {
		t.reset();
	}
}