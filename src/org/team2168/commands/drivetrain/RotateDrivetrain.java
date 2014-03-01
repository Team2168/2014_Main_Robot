package org.team2168.commands.drivetrain;

import org.team2168.RobotMap;
import org.team2168.commands.CommandBase;
import org.team2168.utils.Util;

public class RotateDrivetrain extends CommandBase {
	private double endAngle = 0.0;
	private double startAngle = 0.0;
	private double commandedAngle = 0.0;
	private boolean finished = false;
	
	private static double maxSpeed;
	public static final double kP = 0.01;

	public RotateDrivetrain(double angle) {
		requires(drivetrain);

		commandedAngle = angle;
	}

	protected void initialize() {
		// System.out.println("init gyro. angle = " + endAngle);
		finished = false;
		// drivetrain.resetGyro();

		drivetrain.drive(0, 0);
		startAngle = drivetrain.getGyroAngle();
		endAngle = startAngle + commandedAngle;
		// System.out.println("\n\nStarting rotate to " + commandedAngle +
		// ". S: " + startAngle + "  e: " + endAngle);
	}

	protected void execute() 
	{
		double currentAngle = drivetrain.getGyroAngle();
//		System.out.println("End Angle: " + endAngle + " Current Angle: "
//				+ currentAngle);

		//error
		double error = endAngle - currentAngle;

		
		//if the Kp returns more than 1, we limit the speed
		double speed = Util.limit(RobotMap.rotateDriveKP.getDouble()*error, RobotMap.rotateDriveMaxSpeed.getDouble());

		speed = Math.abs(speed);
		
		//if 
		if (speed <= RobotMap.minDriveSpeed.getDouble())
			speed = RobotMap.minDriveSpeed.getDouble();
		
		
		//if you are within your goal angle. Stop
		if (endAngle < startAngle && currentAngle < endAngle || endAngle > startAngle && currentAngle > endAngle) 
		{
			// We are done
			drivetrain.drive(0, 0);
			finished = true;
		} 
		else if (currentAngle < endAngle) 
		{
			// Turn to the right
			drivetrain.driveRight(-speed);
			drivetrain.driveLeft(speed);
		} else 
		{
			// Turn to the left
			drivetrain.driveRight(speed);
			drivetrain.driveLeft(-speed);
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
