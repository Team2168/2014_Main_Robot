package org.team2168.commands;

public class RotateDrivetrain extends CommandBase{
	double startAngle;
	double angle;
	double endAngle;
	
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
				
				//driveTrain.drive(0, 6);
				
				startAngle = drivetrain.getGyroAngle();
			}
		}else
		{
			while(startAngle > endAngle)
			{
				//driveTrain.drive(6, 0);		
				startAngle = drivetrain.getGyroAngle();
			}
		}
		
		//driveTrain.drive(0, 0);
		
	}

	protected void initialize() {
		// TODO Auto-generated method stub
		startAngle = drivetrain.getGyroAngle();
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
