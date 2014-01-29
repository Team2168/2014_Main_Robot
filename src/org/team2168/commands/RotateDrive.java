package org.team2168.commands;

import org.team2168.RobotMap;
import org.team2168.subsystems.*;

import edu.wpi.first.wpilibj.Gyro;

public class RotateDrivetrain extends CommandBase{

	Gyro rotateGyro;
	double startAngle;
	double angle;
	double endAngle;
	Drivetrain driveTrain;
	
	public RotateDrivetrain (double angle){
		this.angle = angle;
	}

	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
		if(startAngle < endAngle)
		{
			while(startAngle < endAngle)
			{
				
				driveTrain.drive(0, 6);
				
				startAngle = rotateGyro.getAngle();
			}
		}else
		{
			while(startAngle > endAngle)
			{
				driveTrain.drive(6, 0);		
				startAngle = rotateGyro.getAngle();
			}
		}
		
		driveTrain.drive(0, 0);
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		rotateGyro = new Gyro(RobotMap.rotateGyro);
		startAngle = rotateGyro.getAngle();
		endAngle = startAngle + angle;
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
