package org.team2168.commands;

public class RotateDrivetrain extends CommandBase{
	private double endAngle;
	private boolean finished = false;
	
	public RotateDrivetrain (double angle){
		requires(drivetrain);
		drivetrain.resetGyro();
		endAngle = angle;
	}

	protected void initialize() {
		//System.out.println("init gyro. angle = " + endAngle);
		drivetrain.resetGyro();
		finished = false;
		drivetrain.drive(0, 0);
	}

	protected void execute() {
		double currentAngle = drivetrain.getGyroAngle();
		System.out.println("End Angle: " + endAngle + " Current Angle: " + currentAngle);
		
		if (endAngle < 0 && currentAngle < endAngle ||
				endAngle > 0 && currentAngle > endAngle) 
		{
			//We are done
			drivetrain.drive(0,0);
			finished = true;
		}
		else if(currentAngle < endAngle)
		{
			//Turn to the right
			drivetrain.driveRight(-0.2);
			drivetrain.driveLeft(0.2);
		} 
		else 
		{
			//Turn to the left
			drivetrain.driveRight(0.2);
			drivetrain.driveLeft(-0.2);
		}
	}

	protected void interrupted() {
		drivetrain.drive(0, 0);
	}

	protected boolean isFinished() {
		return finished;
	}
	
	protected void end() {
		drivetrain.drive(0, 0);
	}

}
