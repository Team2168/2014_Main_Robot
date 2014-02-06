package org.team2168.commands;

import org.team2168.subsystems.*;
import java.lang.Math;

public class MoveForwardXDistance extends CommandBase{
	double distance;
	double endDistance;
	boolean finished;
	double angle;
	
	public MoveForwardXDistance(double distance)
	{
		this.distance = distance;
		endDistance = drivetrain.getAveragedEncoderDistance() + distance;
		drivetrain = new Drivetrain();
		angle = drivetrain.getGyroAngle();
	}

	protected void end() {
		drivetrain.drive(0, 0);
	}
	
	protected void execute() {
		//TODO set the margin of error
		
		double rightSpeed = 1;
		double leftSpeed = 1;
		double currentDistance = drivetrain.getAveragedEncoderDistance();
		
		//make the left/right motors go less fast, to correct angle
		if (drivetrain.getGyroAngle() < angle)
		{
			rightSpeed = 0.75;
		}
		else if (drivetrain.getGyroAngle() > angle)
		{
			leftSpeed = 0.75;
		}
		
		//check if the robot is within the margin of error (1)
		if (Math.abs(endDistance - currentDistance) < 1)
		{
			drivetrain.drive(0,0);
			finished = true;
		}
		else if(currentDistance < endDistance)
		{
			drivetrain.drive(leftSpeed,rightSpeed);
		}
		else if(currentDistance > endDistance)
		{
			drivetrain.drive(-leftSpeed,-rightSpeed);
		}
	}

	protected void initialize()
	{
		finished = false;
		drivetrain.drive(0, 0);
	}

	protected void interrupted() 
	{
		drivetrain.drive(0, 0);
	}

	protected boolean isFinished() 
	{
		return finished;
	}

}
