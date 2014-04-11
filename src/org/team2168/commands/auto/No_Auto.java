package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.AutoDriveXDistance;

public class No_Auto extends AutoCommandGroup {
	public static final String name = "No Auto - This just sits";

	public No_Auto() {
		super(name);

	}
	
	public int numBalls() {
		return 0;
	}
}
