package org.team2168.commands.drivetrain;

import org.team2168.RobotMap;
import org.team2168.commands.CommandBase;
import org.team2168.subsystems.Drivetrain;


public class StupidDriveFwd extends CommandBase {
	private double destSpeed = 0.0;
	
	public StupidDriveFwd(double speed) {
		requires(drivetrain);
		
		destSpeed = speed;
	}

	protected void initialize() {
		drivetrain.drive(0.0, 0.0);
	}

	/**
	 * Drive straight until we are at our destination.
	 * This only travels forwards right now.
	 */	
	protected void execute() {
		drivetrain.drive(destSpeed, destSpeed);
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		//make sure we are stopped for good measure
		drivetrain.drive(0, 0);
	}

	protected void interrupted() {
		//Clear the current command to motor controllers if we're interrupted.
		drivetrain.drive(0, 0);
	}
}
