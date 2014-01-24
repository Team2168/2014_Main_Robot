package org.team2168.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    Talon rightMotor;
    Talon leftMotor;

    public void Dirvetrain() {
    	rightMotor = new Talon(1);
    	leftMotor = new Talon(2);
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
}