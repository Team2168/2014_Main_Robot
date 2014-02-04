package org.team2168.commands;

public class RotateDrivetrain extends CommandBase{
	double startAngle;
	double angle;
	double endAngle;
	
	public RotateDrivetrain (double angle){
		this.angle = angle;
	}

	protected void end() {
		// TODO Auto-generated method stub
		
	}
	
	protected void execute() {
		//TODO: NO while loops inside of a command.
		//  The execute method needs to run and complete quickly. 
		//  It might take multiple calls of the execute() method for
		//  the command to be finished.
//		if(startAngle < endAngle)
//		{
//			while(startAngle < endAngle)
//			{
//				
//				drivetrain.driveRight(6);
//				
//				startAngle = drivetrain.getGyroAngle();
//			}
//		} else {
//			while(startAngle > endAngle)
//			{
//				drivetrain.driveLeft(6);	
//				startAngle = drivetrain.getGyroAngle();
//			}
//		}
//		
		drivetrain.drive(0,0);		
	}

	protected void initialize() {
		startAngle = drivetrain.getGyroAngle();
		endAngle = startAngle + angle;
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	protected boolean isFinished() {
		// TODO This needs to return true when it's compelte
		return false;
	}

}
