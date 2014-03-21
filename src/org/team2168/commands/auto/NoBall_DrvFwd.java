package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.AutoDriveXDistance;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class NoBall_DrvFwd extends CommandGroup {
	public static final String name = "No Ball, Drive Forward";

	public NoBall_DrvFwd() {
		super(name);
		
		// Drive forward to inscrease likelyhood of shoot and gain 5 pts
		addSequential(new AutoDriveXDistance(RobotMap.autoDriveDistance.getDouble()));

	}
}
