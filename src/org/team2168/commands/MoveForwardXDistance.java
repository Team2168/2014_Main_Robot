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
		this.distance = distance;
		endDistance = drivetrain.getAveragedEncoderDistance() + distance;
		angle = drivetrain.getGyroAngle();
	}

	protected void end() {
		drivetrain.drive(0, 0);
	}

	protected void execute() {
		//TODO set the margin of error
		
		double rightSpeed = 1;
		double leftSpeed = 1;
		double currentDistance = drivetrain.getAveragedEncoderDistance();

		//make the left/right motors go less fast, to correct angle
		//if the current angle is less than the angle we want to be at, by more than 10 degrees
		if (drivetrain.getGyroAngle() < angle && !(Math.abs(drivetrain.getGyroAngle() - angle) < 10))
		{
			//speed = (-1/450)x + 1, where x is the difference between the
			//  current angle and the angle we want to be heading
			rightSpeed = ((-1/450) * Math.abs(drivetrain.getGyroAngle() - angle)) + 1;
		}
		else if (drivetrain.getGyroAngle() > angle && !(Math.abs(drivetrain.getGyroAngle() - angle) < 10))
		{
			leftSpeed = ((-1/450) * Math.abs(drivetrain.getGyroAngle() - angle)) + 1;
		}

		//check if the robot is within the margin of error (1 inch)
		if (Math.abs(endDistance - currentDistance) < 1) {
			//we're there, stop
			drivetrain.drive(0,0);
			finished = true;
		} else if(currentDistance < endDistance) {
			//Drive forward 
			drivetrain.drive(leftSpeed,rightSpeed);
		} else {
			//Drive backwards
			drivetrain.drive(-leftSpeed,-rightSpeed);
		}
	}

	protected void initialize() {
		finished = false;
		drivetrain.drive(0, 0);
	}

	protected void interrupted() {
		drivetrain.drive(0, 0);
	}

	protected boolean isFinished() {
		return finished;
	}

}
