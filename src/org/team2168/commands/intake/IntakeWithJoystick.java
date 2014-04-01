package org.team2168.commands.intake;
import org.team2168.commands.CommandBase;

public class IntakeWithJoystick extends CommandBase{
	
	public IntakeWithJoystick() {
		requires(intakeRollers);
	}
	
	protected void initialize() {
	}

	protected void execute() {
		intakeRollers.driveIntake(oi.getOperatorRightStick() + oi.testController.getRightStickRaw_Y());
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void end() {
		// TODO Auto-generated method stub
		
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
}
