package org.team2168.commands;

public class RotateDrivetrain extends CommandBase{
	double endAngle;
	boolean finished;
	
	public RotateDrivetrain (double angle){
		endAngle = drivetrain.getGyroAngle() + angle;
	}

	protected void end() {
		// TODO Auto-generated method stub
		
	}
	
	protected void execute() {
		
		double currentAngle = drivetrain.getGyroAngle();
		
		if (Math.abs(endAngle - currentAngle) < 1) {
			drivetrain.drive(0,0);
			finished = true;
		}
		if(currentAngle < endAngle)
		{
			drivetrain.driveRight(-1);
			drivetrain.driveLeft(1);
		}
		else if (currentAngle > endAngle)
		{
			drivetrain.driveRight(1);
			drivetrain.driveLeft(-1);
		}
		
		drivetrain.drive(0,0);
	}

	protected void initialize() {
		drivetrain.drive(0,0);
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	protected boolean isFinished() {
		return finished;
	}

}
