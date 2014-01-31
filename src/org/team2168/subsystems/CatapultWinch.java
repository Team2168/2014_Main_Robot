
package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.utils.MomentaryDoubleSolenoid;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *This subsystem moves the catapult to the set position and detects if it is 
 *at the set position.
 */
public class CatapultWinch extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Talon winchMotor;
	Encoder winchEncoder;
	DigitalInput winchInputSwitch;
	MomentaryDoubleSolenoid winchSolenoid;

	/**
	 * This is the constructor for the subsystem.
	 */
	public CatapultWinch(){
		winchMotor = new Talon(RobotMap.winchDriveMotor.getInt());
		winchInputSwitch = new DigitalInput(RobotMap.winchLimitSwitch.getInt());
		winchSolenoid = new MomentaryDoubleSolenoid(
				RobotMap.winchExtPort.getInt(),RobotMap.winchRetPort.getInt());
		
		
	}
	
	/**
	 * Sets the default command for the subsystem.
	 */
    public void initDefaultCommand() {

    }
    
    /**
     * This method sets the motor to move the catapult to the set position.
     * @param winchSpeed
     */
    public void setCatapult(double winchSpeed){
    	winchMotor.set(winchSpeed);
    }
    
    /**
     * This method closes the latch to keep the catapult in place.
     */
    public void closeLatch(){
    	winchSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    /**
     * This method releases the latch on the catapult.
     */
    public void openLatch(){
    	winchSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    /**
     * This method checks if the catapult has reached the set position.
     * @return
     */
    public boolean isCatapultSet(){
    	//TODO: Verify that the switch returns true when the switch is pressed.
       	return winchInputSwitch.get();
       	
    }
}

