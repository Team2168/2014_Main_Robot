package org.team2168.commands.drivetrain;

import org.team2168.commands.CommandBase;

public class RotateDrivetrain extends CommandBase {
	private double endAngle = 0.0;
	private double startAngle = 0.0;
	private double commandedAngle = 0.0;
	private boolean finished = false;

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

	protected void execute() {
		double currentAngle = drivetrain.getGyroAngle();
//		System.out.println("End Angle: " + endAngle + " Current Angle: "
//				+ currentAngle);

		if (endAngle < startAngle && currentAngle < endAngle
				|| endAngle > startAngle && currentAngle > endAngle) {
			// We are done
			drivetrain.drive(0, 0);
			finished = true;
		} else if (currentAngle < endAngle) {
			// Turn to the right
			drivetrain.driveRight(-0.2);
			drivetrain.driveLeft(0.2);
		} else {
			// Turn to the left
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
