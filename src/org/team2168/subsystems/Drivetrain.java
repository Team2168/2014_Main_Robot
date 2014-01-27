package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.utils.FalconGyro;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    Talon rightMotor;
    Talon leftMotor;
    FalconGyro gyro;

    public void Dirvetrain() {
    	rightMotor = new Talon(RobotMap.rightDriveMotor.getInt());
    	leftMotor = new Talon(RobotMap.leftDriveMotor.getInt());
    	gyro = new FalconGyro(RobotMap.gyroPort.getInt());
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * A method to drive the motors on the drivetrain with.
     * 
     * @param rightSpeed the speed to drive the right motor at 
     * @param leftSpeed the speed to drive the left motor at
     */
    public void drive(double rightSpeed, double leftSpeed) {
    	rightMotor.set(rightSpeed);
    	leftMotor.set(leftSpeed);
    }
    
    /**
     * Get the current angle of the gyro.
     * 
     * @return the angle of the gyro, in degrees.
     */
    public double getGyroAngle() {
    	return gyro.getAngle();
    }

    /**
     * Re-initialize the gyro. This should not be called during a match.
     */
	public void reinitGyro() {
		gyro.initGyro();
	}

	/**
	 * Set the current robot heading to 0.0
	 */
	public void resetGyro() {
		gyro.reset();
	}
}