package org.team2168.commands;

public class MoveForwardXDistance extends CommandBase{

	double distance;
	double endDistance;
	boolean finished;

	public MoveForwardXDistance(double distance)
	{
		this.distance = distance;
		endDistance = drivetrain.getAveragedEncoderDistance() + distance;
		finished = false;
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
			//TODO:this method can only take in values from 1 to -1
			//drivetrain.drive(6,6);
		}
		else if (currentDistance > endDistance)
		{
			//TODO:this method can only take in values from 1 to -1
			//drivetrain.drive(-6,-6);
		}

	}

	protected void initialize()
	{
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