package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.AutoDriveXDistance;

public class NoBall_DrvFwd extends AutoCommandGroup {
	public static final String name = "No Ball, Drive Forward";

	public NoBall_DrvFwd() {
		super(name);
		
		// Drive forward to inscrease likelyhood of shoot and gain 5 pts
		addSequential(new AutoDriveXDistance(RobotMap.autoDriveDistance.getDouble()));
	}
	
	public int numBalls() {
		return 0;
	}
}
