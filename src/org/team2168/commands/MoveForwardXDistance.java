package org.team2168.commands;

import org.team2168.subsystems.*;
import java.lang.Math;

public class MoveForwardXDistance extends CommandBase{

	Drivetrain driveTrain;
	double distance;
	double endDistance;
	boolean finished;
	
	public MoveForwardXDistance(double distance)
	{
		this.distance = distance;
		endDistance = driveTrain.getAveragedEncoderDistance() + distance;
		finished = false;
		driveTrain = new Drivetrain();
	}

	protected void end() {
		driveTrain.drive(0, 0);
	}
	
	protected void execute() {
		//TODO set the margin of error 
		double currentDistance = driveTrain.getAveragedEncoderDistance();
		
		//check if the robot is within the margin of error (1)
		if (Math.abs(endDistance - currentDistance) < 1) {
			driveTrain.drive(0,0);
			finished = true;
		}
		if(currentDistance < endDistance)
		{
				driveTrain.drive(6,6);
		}
		else if (currentDistance > endDistance)
		{
				driveTrain.drive(-6,-6);
		}
		
	}

	protected void initialize()
	{
		driveTrain.drive(0, 0);
	}

	protected void interrupted() 
	{
		driveTrain.drive(0, 0);
	}

	protected boolean isFinished() 
	{
		return finished;
	}

}
