package org.team2168.commands.winch;

import org.team2168.RobotMap;
import org.team2168.commands.intake.IntakeDown;
import org.team2168.commands.tapper.DisengageTappers;
import org.team2168.commands.tapper.TapperFirePosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Fire extends CommandGroup {

	public Fire() {
		addSequential(new DisengageTappers());
		
		//verify the intake is lowered
		addSequential(new IntakeDown(), RobotMap.intakeLowerTimeout.getDouble());
		
		addSequential(new TapperFirePosition());
		
		//TODO: wait for ball to settle

		//Shoot the ball
		addSequential(new ExtendWinchDogGear());
	}
}
