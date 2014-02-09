package org.team2168.commands;

public class MoveForwardXDistance extends CommandBase{
	double distance;
	double endDistance;
	boolean finished;
	double angle;
	
	/**
	 * Move the drivetrain forward the specified distance.
	 * @param distance in inches
	 */
	public MoveForwardXDistance(double distance) {
		requires(drivetrain);
		this.distance = distance;
	}

	protected void end() {
		drivetrain.drive(0, 0);
	}

	protected void execute() {
		//TODO set the margin of error
		
		double rightSpeed = 0.3;
		double leftSpeed = 0.3;
		double currentDistance = drivetrain.getAveragedEncoderDistance();
		
		//make the left/right motors go less fast, to correct angle
		//if the current angle is less than the angle we want to be at, by more than 0.2 degrees
		if (drivetrain.getGyroAngle() < angle) //&& (Math.abs(drivetrain.getGyroAngle() - angle) > 0.2))
		{
			//speed = (-1/450)x + 1, where x is the difference between the
			//  current angle and the angle we want to be heading
			if (currentDistance < endDistance) {
				rightSpeed = ((-1.0/45.0) * Math.abs(drivetrain.getGyroAngle() - angle)) + rightSpeed;
			}
			else {
				leftSpeed = ((-1.0/45.0) * Math.abs(drivetrain.getGyroAngle() - angle)) + leftSpeed;
			}
		}
		else if (drivetrain.getGyroAngle() > angle) //&& (Math.abs(drivetrain.getGyroAngle() - angle) > 0.2))
		{
			if (currentDistance < endDistance) {
				leftSpeed = ((-1.0/45.0) * Math.abs(drivetrain.getGyroAngle() - angle)) + leftSpeed;
			}
			else {
				rightSpeed = ((-1.0/45.0) * Math.abs(drivetrain.getGyroAngle() - angle)) + rightSpeed;
			}
		}

		//check if the robot is within the margin of error (1 inch)
		if (Math.abs(endDistance - currentDistance) < 1) {
			//we're there, stop
			drivetrain.drive(0,0);
			finished = true;
		} else if(currentDistance < endDistance) {
			//Drive forward 
			drivetrain.drive(rightSpeed,leftSpeed);
		} else {
			//Drive backwards
			drivetrain.drive(-rightSpeed,-leftSpeed);
		}
		System.out.println("Right Speed: " + rightSpeed + 
				" Left Speed: " + leftSpeed + 
				" Angle = " + drivetrain.getGyroAngle() + 
				" Distance = " + currentDistance +
				" 'angle' =" + angle);
	}

	protected void initialize() {
		finished = false;
		drivetrain.drive(0, 0);
		drivetrain.resetEncoders();
		endDistance = drivetrain.getAveragedEncoderDistance() + distance;
		angle = drivetrain.getGyroAngle();
	}

	protected void interrupted() {
		drivetrain.drive(0, 0);
	}

	protected boolean isFinished() {
		return finished;
	}

}
