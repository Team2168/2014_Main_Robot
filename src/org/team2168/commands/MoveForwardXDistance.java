package org.team2168.commands;
import org.team2168.subsystems.*;

public class MoveForwardXDistance extends CommandBase{

	Drivetrain driveTrain;
	double feet;
	
	public MoveForwardXDistance(double feet)
	{
		this.feet = feet;
	}

	protected void end() {
		// TODO Auto-generated method stub
	}
	
	protected void execute() {
		
		double startDistance = driveTrain.getAveragedEncoderDistance();
		double endDistance = driveTrain.getAveragedEncoderDistance() + feet;
		
		if(startDistance < endDistance)
		{
			while(startDistance < endDistance)
			{
				driveTrain.driveRight(6);
				driveTrain.driveLeft(6);
				
				startDistance = driveTrain.getAveragedEncoderDistance();
			}
		}else
		{
			while(startDistance > endDistance)
			{
				driveTrain.driveLeft(-6);
				driveTrain.driveRight(-6);
				startDistance = driveTrain.getAveragedEncoderDistance();
			}
		}
		
		driveTrain.drive(0, 0);	
	}

	protected void initialize()
	{
		// TODO Auto-generated method stub
		driveTrain = new Drivetrain();
	}

	protected void interrupted() 
	{
		// TODO Auto-generated method stub
	}

	protected boolean isFinished() 
	{
		// TODO Auto-generated method stub
		return false;
	}

}
