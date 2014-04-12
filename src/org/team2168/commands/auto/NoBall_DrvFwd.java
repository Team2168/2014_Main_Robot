package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.AutoDriveXDistance;
import org.team2168.commands.drivetrain.StupidDriveFwd;

public class NoBall_DrvFwd extends AutoCommandGroup {
	public static final String name = "No Ball, Drive Forward";

	public NoBall_DrvFwd() {
		super(name);
		
		//stop gap to get 5pts in auto, this doesn't necessarily drive straight!
		//REMOVE when we figure out why drive straight isn't working
		addSequential(new StupidDriveFwd(0.25), 1.5);
	}
	
	public int numBalls() {
		return 0;
	}
}
