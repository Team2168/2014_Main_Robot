package org.team2168.commands.intake;

import org.team2168.RobotMap;
import org.team2168.commands.tapper.DisengageTappers;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This command lowers the intake while running the intake motors. This prevents
 * the ball from being dragged off of the catapult while the intake is lowered.
 * 
 * @author James
 */
public class IntakeDownAndDriveMotors extends CommandGroup {

	public IntakeDownAndDriveMotors() {
		addSequential(new DisengageTappers());
		
		// move intake out of way, spin wheel to keep ball in robot
		addParallel(new IntakeDriveMotor(
				RobotMap.intakeWheelSpeedWhenLowering.getDouble()));
		addSequential(new IntakeDown(), 2);
		addSequential(new IntakeDriveMotor(0.0), 0.1);
	}
}
