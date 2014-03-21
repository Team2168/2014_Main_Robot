package org.team2168.commands.intake;

import org.team2168.RobotMap;
import org.team2168.commands.winch.WaitUntilBallNotPresent;
import org.team2168.commands.winch.WaitUntilBallPresent;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeSingleBall extends CommandGroup {

	public IntakeSingleBall() {
		//Drive the motors to acquire ball
		addParallel(new IntakeDriveMotor(
				-RobotMap.intakeWheelVoltage.getDouble()));
		
		//wait until you see a ball
		addSequential(new WaitUntilBallPresent());
		
		//wait until you don't see a ball
		addSequential(new WaitUntilBallNotPresent());
		
		//stop intake motors
		addSequential(new IntakeDriveMotor(0.0), 0.1);
	}
}
