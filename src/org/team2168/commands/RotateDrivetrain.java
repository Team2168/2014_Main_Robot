package org.team2168.commands;

import org.team2168.RobotMap;
import org.team2168.subsystems.*;

import edu.wpi.first.wpilibj.Gyro;

public class RotateDrivetrain extends CommandBase{

	double startAngle;
	double angle;
	double endAngle;
	Drivetrain driveTrain;
	
	public RotateDrivetrain (double angle){
		this.angle = angle;
	}

	
	protected void end() {
		// TODO Auto-generated method stub
		
	}
	protected void execute() {
		// TODO Auto-generated method stub
		
		if(startAngle < endAngle)
		{
			while(startAngle < endAngle)
			{
				
				driveTrain.driveRight(6);
				
				startAngle = driveTrain.getGyroAngle();
			}
		}else
		{
			while(startAngle > endAngle)
			{
				driveTrain.driveLeft(6);	
				startAngle = driveTrain.getGyroAngle();
			}
		}
		
		driveTrain.drive(0,0);

		
	}

	protected void initialize() {
		// TODO Auto-generated method stub
		startAngle = driveTrain.getGyroAngle();
		endAngle = startAngle + angle;
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
