package org.team2168.commands;
import org.team2168.subsystems.*;
import org.team2168.*;
import java.math.*;

public class MoveForwardXDistance extends CommandBase{

	Drivetrain driveTrain;
	double feet;
	
	public MoveForwardXDistance(double feet)
	{
		this.feet = feet;
	}
	
	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
		double startDistance = driveTrain.getEncoderDataDistance();
		double endDistance = driveTrain.getEncoderDataDistance() + feet;
		
		if(startDistance < endDistance)
		{
			while(startDistance < endDistance)
			{
				
				driveTrain.driveRight(6);
				driveTrain.driveLeft(6);
				
				startDistance = driveTrain.getEncoderDataDistance();
				
			}
		}else
		{
			while(startDistance > endDistance)
			{
				driveTrain.driveLeft(-6);
				driveTrain.driveRight(-6);
				startDistance = driveTrain.getEncoderDataDistance();
			}
		}
		
		driveTrain.drive(0, 0);
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		driveTrain = new Drivetrain();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
