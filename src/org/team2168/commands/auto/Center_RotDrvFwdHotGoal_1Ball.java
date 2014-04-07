package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.Sleep;
import org.team2168.commands.drivetrain.AutoDriveXDistance;
import org.team2168.commands.drivetrain.RotateDrivetrainRelative;
import org.team2168.commands.intake.IntakeDown;
import org.team2168.commands.tusks.TusksLongShotPosition;
import org.team2168.commands.vision.WaitForFirstHot;
import org.team2168.commands.winch.FireAndReload;

import edu.wpi.first.wpilibj.command.WaitForChildren;

public class Center_RotDrvFwdHotGoal_1Ball extends AutoCommandGroup {

	public Center_RotDrvFwdHotGoal_1Ball() {
		this(1.5); // default 1.5 second delay
	}

	public Center_RotDrvFwdHotGoal_1Ball(double firstHotGoalTimeOut) {
		// wait for hot goal, assume camera is facing right hot goal
		addParallel(new TusksLongShotPosition());
		addParallel(new IntakeDown());

		addSequential(new WaitForFirstHot(), firstHotGoalTimeOut);

		// lets see if this works
		addSequential(new WaitForChildren());

		// Rotate DriveTrain based on angle from camera. Drive to the goal the
		//  camera thinks is NOT hot, since it will take more than 5sec to get
		//  to position to fire the ball.
		addSequential(new RotateDrivetrainRelative(0.0, true, true));
		
		addSequential(new Sleep(), 1.0);

		// Drive forward to inscrease likelyhood of shoot and gain 5 pts, should run 55 inches forward
		addSequential(new AutoDriveXDistance(RobotMap.autoDriveDistance.getDouble()));

		//Let the ball settle before a shot
		addSequential(new Sleep(), 2.0);
		
		// fire
		addSequential(new FireAndReload());
	}
	
	public int numBalls() {
		return 1;
	}
}
