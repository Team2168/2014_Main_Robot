package org.team2168.commands;

import org.team2168.subsystems.*;
import java.lang.Math;

public class MoveForwardXDistance extends CommandBase{
	double distance;
	double endDistance;
	boolean finished;
	
	public MoveForwardXDistance(double distance)
	{
		this.distance = distance;
		endDistance = drivetrain.getAveragedEncoderDistance() + distance;
		drivetrain = new Drivetrain();
	}

	protected void end() {
		drivetrain.drive(0, 0);
	}
	
	protected void execute() {
		//TODO set the margin of error 
		double currentDistance = drivetrain.getAveragedEncoderDistance();
		
		//check if the robot is within the margin of error (1)
		if (Math.abs(endDistance - currentDistance) < 1) {
			drivetrain.drive(0,0);
			finished = true;
		}
		if(currentDistance < endDistance)
		{
			drivetrain.drive(1,1);
		}
		else if (currentDistance > endDistance)
		{
			drivetrain.drive(-1,-1);
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
