package org.team2168.commands.drivetrain;

import org.team2168.RobotMap;
import org.team2168.commands.CommandBase;
import org.team2168.subsystems.Vision;
import org.team2168.utils.Util;

public class RotateDrivetrainRelative extends CommandBase
{
	private double endAngle = 0.0;
	private double startAngle = 0.0;
	private double commandedAngle = 0.0;
	private boolean finished = false;
	private boolean negate = false;
	private boolean getAngleFromCam = false;

	public static final double kP = 0.01;
	
	/**
	 * 
	 * @param angle angle (degrees) to rotate to relative to current position
	 */
	public RotateDrivetrainRelative(double angle)
	{
		this(angle, false, false);
	}
	
	public RotateDrivetrainRelative(double angle, boolean getAngleFromVision) {
		this(angle, getAngleFromVision, false);
	}
	
	
	/**
	 * 
	 * @param angle, angle to rotate relative to current position, if known at
	 *   instantiation time., set next param to false, to rotate to this angle.
	 * @param getAngleFromVision, if you would like this command to rotate to
	 *   the relative angle provided by the camera at the moment the command
	 *   is ran, set this boolean to true, the first parameter angle is ignored,
	 *   and the current output of the Vision subsystem is used instead.
	 * @param negate cause the robot to rotate in the direction opposite of what
	 *   is specified in the angle parameter, or from camera. This is a hack for
	 *   commands that take too long to prep for a shot. 
	 */

	public RotateDrivetrainRelative(double angle, boolean getAngleFromVision,
			boolean negate)
	{
		requires(drivetrain);

		this.getAngleFromCam = getAngleFromVision;
		this.commandedAngle = angle;
		this.negate = negate;
	}

	/**
	 * this method gets ran once everytime the command is started
	 */
	protected void initialize()
	{
		finished = false;
		
		if (getAngleFromCam) {
			commandedAngle = Vision.getInstance().getLeftOrRightHot()
					* RobotMap.rotationAngleToHot.getDouble();
		}

		if (negate) {
			commandedAngle = -commandedAngle;
		}
		
		drivetrain.drive(0, 0);
		
		//find current angle and calculate offset
		startAngle = drivetrain.getGyroAngle();
		endAngle = startAngle + commandedAngle;
	}

	/**
	 * this method is called periodically until the command finishes
	 */
	protected void execute()
	{
		double currentAngle = drivetrain.getGyroAngle();


		//Use proportional controller to achieve better response of rotation command
		
		// calculate current error
		double error = endAngle - currentAngle;

		// if the Kp returns more than 1, we limit the speed
		double speed = Util.limit(RobotMap.rotateDriveKP.getDouble() * error,
				RobotMap.rotateDriveMaxSpeed.getDouble());

		speed = Math.abs(speed);

		// if command is lower than minspeed needed to move drivetrain, we up it
		// to the min speed
		if (speed <= RobotMap.minDriveSpeed.getDouble())
			speed = RobotMap.minDriveSpeed.getDouble();

		// if you are within your goal angle. Stop
		if (endAngle < startAngle && currentAngle < endAngle
				|| endAngle > startAngle && currentAngle > endAngle)
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
		}
		else
		{
			// Turn to the left
			drivetrain.driveRight(speed);
			drivetrain.driveLeft(-speed);
		}
	}

	protected void interrupted()
	{
		drivetrain.drive(0, 0);
	}

	protected boolean isFinished()
	{
		return finished;
	}

	protected void end()
	{
		drivetrain.drive(0, 0);
	}

}
