package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.drivetrain.RotateDrivetrainRelative;
import org.team2168.commands.intake.IntakeDown;
import org.team2168.commands.tusks.TusksLongShotPosition;
import org.team2168.commands.vision.WaitForFirstHot;
import org.team2168.commands.winch.FireAndReload;
import org.team2168.subsystems.Vision;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitForChildren;

public class Center_RotHotGoal_1Ball extends CommandGroup {

	public Center_RotHotGoal_1Ball() {

		this(1.5); // default 1.5 second delay
	}

	public Center_RotHotGoal_1Ball(double firstHotGoalTimeOut) {

		// wait for hot goal, assume camera is facing right hot goal
		addParallel(new TusksLongShotPosition());
		addParallel(new IntakeDown());

		addSequential(new WaitForFirstHot(), firstHotGoalTimeOut);

		// lets see if this works
		addSequential(new WaitForChildren());

		// Rotate DriveTrain = +/- min rotation angle, this will get angle from camera once executed
		addSequential(new RotateDrivetrainRelative(0.0,true));

		// fire
		addSequential(new FireAndReload());
	}
}
