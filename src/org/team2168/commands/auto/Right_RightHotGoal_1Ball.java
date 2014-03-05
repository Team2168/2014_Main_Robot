package org.team2168.commands.auto;

import org.team2168.commands.intake.IntakeDown;
import org.team2168.commands.tusks.TusksLongShotPosition;
import org.team2168.commands.vision.WaitForRightHot;
import org.team2168.commands.winch.FireAndReload;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitForChildren;

public class Right_RightHotGoal_1Ball extends CommandGroup {


	public Right_RightHotGoal_1Ball() {

		// wait for hot goal, assume camera is facing right hot goal
		addParallel(new TusksLongShotPosition());
		addParallel(new IntakeDown());

		addSequential(new WaitForRightHot(), 6);

		// lets see if this works
		addSequential(new WaitForChildren());

		// Drive forward to inscrease likelyhood of shoot and gain 5 pts
		//addSequential(new AutoDriveXDistance(RobotMap.autoDriveDistance.getDouble()));
		

		// fire
		addSequential(new FireAndReload());
	}
}
