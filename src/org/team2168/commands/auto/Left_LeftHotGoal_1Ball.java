package org.team2168.commands.auto;

import org.team2168.RobotMap;
import org.team2168.commands.Sleep;
import org.team2168.commands.drivetrain.AutoDriveXDistance;
import org.team2168.commands.intake.IntakeDown;
import org.team2168.commands.intake.IntakeDriveMotor;
import org.team2168.commands.tusks.TusksLongShotPosition;
import org.team2168.commands.vision.WaitForLeftHot;
import org.team2168.commands.winch.Fire;
import org.team2168.commands.winch.Reload;
import org.team2168.commands.winch.WaitUntilFired;

public class Left_LeftHotGoal_1Ball extends AutoCommandGroup {
	public static final String name = "1 Ball, Wait for Left Hot";

	public Left_LeftHotGoal_1Ball() {
		super(name);
		// wait for hot goal, assume camera is facing right hot goal
		addParallel(new TusksLongShotPosition());
		addParallel(new IntakeDriveMotor(-0.15));

		addSequential(new IntakeDown(), 1);
		addParallel(new IntakeDriveMotor(0.0));
		addSequential(new Sleep(), RobotMap.autoBallSettleTime.getDouble());
		
		addSequential(new WaitForLeftHot(), 6 - RobotMap.autoBallSettleTime.getDouble());

		// lets see if this works
		//addSequential(new WaitForChildren());

		// Drive forward to inscrease likelyhood of shoot and gain 5 pts
		//addSequential(new AutoDriveXDistance(RobotMap.autoDriveDistance.getDouble()));
		
		// fire
		addSequential(new Fire());
		addSequential(new WaitUntilFired());
		
		addParallel(new AutoDriveXDistance(RobotMap.autoDriveDistance.getDouble()));
		addParallel(new Reload());
	}
	
	public int numBalls() {
		return 1;
	}
}
