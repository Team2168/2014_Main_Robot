package org.team2168.commands;

public class RotateDrivetrain extends CommandBase{
	double endAngle;
	boolean finished;
	
	public RotateDrivetrain (double angle){
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
		
		if (Math.abs(endAngle - currentAngle) < 0.5) {
			//We are done
			drivetrain.drive(0,0);
			finished = true;
		} if(currentAngle < endAngle) {
			//Turn to the right
			drivetrain.driveRight(-0.2);
			drivetrain.driveLeft(0.2);
		} else {
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
		// TODO Auto-generated method stub
		
	}

}
