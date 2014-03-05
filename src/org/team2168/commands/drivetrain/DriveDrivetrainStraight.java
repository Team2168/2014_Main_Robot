package org.team2168.commands.drivetrain;

import org.team2168.RobotMap;
import org.team2168.commands.CommandBase;
import org.team2168.subsystems.Drivetrain;


public class DriveDrivetrainStraight extends CommandBase {
	private static final double TARGET_SPEED             = 0.85;
	private static final double TURN_SCALER              = 1.10;
	private static final double STRAIGHT_ANGLE_TOLERANCE =  1.0; //acceptable angular error in degrees
	
	private double destDistance; //The goal distance in inches
	private static final double rateLimit = 0.15;
	private double currentLeftSpeed, currentRightSpeed;
	private boolean finished;
	private double direction = 1.0;
	private boolean runForever = false;
	
	/**
	 * Drive the drivetrain straight.
	 * 
	 * @param distance The distance to drive straight in inches
	 */
	public DriveDrivetrainStraight(double distance) {
		this(distance, false);
	}
	
	/**
	 * Drive the drivetrain straight.
	 * This constructor can be used (if passed true) to hold the robot in
	 * position indefinitely.
	 * 
	 * @param runForever If true, the command will not complete when the
	 *   destination is reached
	 */
	public DriveDrivetrainStraight(boolean runForever) {
		//TODO: Make this work regardless of direction of travel
		//  (only holds in one direction right now)
		this(0.0, runForever);
	}
	
	/**
	 * Drive the drivetrain straight.
	 * 
	 * @param distance The distance to drive straight in inches
	 * @param runForever If true, the command will not complete when the
	 *   destination is reached
	 */
	public DriveDrivetrainStraight(double distance, boolean runForever) {
		requires(drivetrain);
		if (distance == 0.0) {
			finished = true;
		}else if(distance < 0) {
			//Driving reverse
			direction = -1.0;
		} else {
			//Driving forward
			direction = 1.0;
		}
		destDistance = distance;
		this.runForever = runForever;  
	}
		
	protected void initialize() {
		finished = false;
		currentLeftSpeed = currentRightSpeed = RobotMap.minDriveSpeed.getDouble();
		drivetrain.drive(0.0, 0.0);
		drivetrain.resetEncoders();
		drivetrain.resetGyro();
	}

	/**
	 * Drive straight until we are at our destination.
	 * This only travels forwards right now.
	 */	
	protected void execute() {
		System.out.println("Drive drivetain");
		
		double newLeftSpeed = 0, newRightSpeed = 0, angle = 0;
		double speedModifierL = 1, speedModifierR = 1;

		//if we aren't there yet, set speed to max speed
		if(!finished && (((direction >= 0) && drivetrain.getAveragedEncoderDistance() < destDistance)
				|| ((direction < 0)&& drivetrain.getAveragedEncoderDistance() > destDistance))) {
			newLeftSpeed = newRightSpeed = TARGET_SPEED;
			
			//TODO: Replace with speed controller
			//Simple rate limiter
			newLeftSpeed = Drivetrain.rateLimit(newLeftSpeed, currentLeftSpeed, rateLimit);
			newRightSpeed = Drivetrain.rateLimit(newRightSpeed, currentRightSpeed, rateLimit);
			
			//Add in turn based on gyro offset (+/-1 deg deadband)
			angle = drivetrain.getGyroAngle();			//assuming clockwise is positive, 10% increment in speed
			if (angle > STRAIGHT_ANGLE_TOLERANCE){	//can make modifier use function(angular displacement)
				if(direction < 0) {
					//increase left speed to turn to the right - when going backward
					speedModifierL = speedModifierL * TURN_SCALER;
				} else {
					//increase right speed to turn to the left - when going forward
					speedModifierR = speedModifierR * TURN_SCALER;
				}
			} else if (angle < -STRAIGHT_ANGLE_TOLERANCE) {
				if(direction < 0) {
					//increase right speed to turn to the left - when going backward
					speedModifierR = speedModifierR * TURN_SCALER;
				} else {
					//increase left speed to turn to the right - when going forward
					speedModifierL = speedModifierL * TURN_SCALER;
				}
			} else {
				//Continue driving straight
				speedModifierR = 1;
				speedModifierL = 1;
			}
			
			//output to motors
			drivetrain.drive((speedModifierR * newRightSpeed * direction),
					(speedModifierL * newLeftSpeed * direction));
			
			currentLeftSpeed = newLeftSpeed;
			currentRightSpeed = newRightSpeed;
		} else {
			//we are there, stop
			drivetrain.drive(0.0, 0.0);
			finished = (true && !runForever);
		}
	}
	
	protected boolean isFinished() {
		return finished;
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
