package org.team2168.commands.intake;

import org.team2168.commands.winch.WaitUntilBallNotPresent;
import org.team2168.commands.winch.WaitUntilBallPresent;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeSingleBall extends CommandGroup {

	public IntakeSingleBall() {
		//Drive the motors to acquire ball
		addParallel(new IntakeDriveMotor(-1.0));
		
		//wait until you see a ball
		addSequential(new WaitUntilBallPresent());
		
		//wait until you don't see a ball
		addSequential(new WaitUntilBallNotPresent());
		
		//stop intake motors
		addParallel(new IntakeDriveMotor(0.0));
	}
}
