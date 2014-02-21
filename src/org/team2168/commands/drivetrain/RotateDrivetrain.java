package org.team2168.commands.drivetrain;

import org.team2168.OI;
import org.team2168.RobotMap;
import org.team2168.commands.CommandBase;

public class RotateDrivetrain extends CommandBase {
	private double endAngle = 0.0;
	private double startAngle = 0.0;
	private double commandedAngle = 0.0;
	private boolean finished = false;

	public RotateDrivetrain(double angle) {
		requires(drivetrain);

		commandedAngle = angle;
	}

	protected void initialize() {
		// System.out.println("init gyro. angle = " + endAngle);
		finished = false;
		// drivetrain.resetGyro();

		drivetrain.drive(0, 0);
		startAngle = drivetrain.getGyroAngle();
		endAngle = startAngle + commandedAngle;
		// System.out.println("\n\nStarting rotate to " + commandedAngle +
		// ". S: " + startAngle + "  e: " + endAngle);
	}

	protected void execute() {
		double slowSpeed = 1;
		
		double currentAngle = drivetrain.getGyroAngle();
		System.out.println("End Angle: " + endAngle + " Current Angle: " + currentAngle);
		
		//if we are 1/20 of the total distance away from target angle, start slowing down
		if (currentAngle < endAngle && currentAngle >= (endAngle - 15) ||
				currentAngle > endAngle && currentAngle <= (endAngle - 15)) {
			slowSpeed = (endAngle - currentAngle)/15;
			//set slowSpeed so that robot never drives slower than minimum speed
			if (slowSpeed*0.2 < RobotMap.minDriveSpeed.getDouble()) {
				slowSpeed = RobotMap.minDriveSpeed.getDouble()/0.2;
			}
		}
		
		if (endAngle < startAngle && currentAngle < endAngle ||
				endAngle > startAngle && currentAngle > endAngle) 
		{
			//We are done
			drivetrain.drive(0,0);
			finished = true;
		}
		
		else if(currentAngle < endAngle)
		{
			//Turn to the right
			drivetrain.driveRight(slowSpeed*-0.2);
			drivetrain.driveLeft(slowSpeed*0.2);
		} 
		else 
		{
			//Turn to the left
			drivetrain.driveRight(slowSpeed*0.2);
			drivetrain.driveLeft(slowSpeed*-0.2);
		}
	}

	protected void interrupted() {
		drivetrain.drive(0, 0);
	}

	protected boolean isFinished() {
		return finished;
	}

	protected void end() {
		drivetrain.drive(0, 0);
	}

}
