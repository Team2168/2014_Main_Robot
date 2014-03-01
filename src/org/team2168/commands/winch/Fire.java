package org.team2168.commands.winch;

import org.team2168.RobotMap;
import org.team2168.commands.intake.IntakeDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Fire extends CommandGroup {

	public Fire() {
		//verify the intake is lowered
		addSequential(new IntakeDown(), RobotMap.intakeLowerTimeout.getDouble());

		//TODO: wait for ball to settle

		//Shoot the ball
		addSequential(new ExtendWinchDogGear());
	}
}
