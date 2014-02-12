package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.WinchWithJoystick;
import org.team2168.utils.MomentaryDoubleSolenoid;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *This subsystem moves the catapult to the set position and detects if it is 
 *at the set position.
 */
public class CatapultWinch extends Subsystem {
	private static CatapultWinch instance = null;
	private static Talon winchMotor;
	private static DigitalInput winchInputSwitch;
	private static MomentaryDoubleSolenoid winchSolenoid;

	/**
	 * A private constructor to prevent multiple instances from being created.
	 */
	private CatapultWinch(){
		winchMotor = new Talon(RobotMap.winchDriveMotor.getInt());
		winchInputSwitch = new DigitalInput(RobotMap.winchLimitSwitch.getInt());
		winchSolenoid = new MomentaryDoubleSolenoid(
				RobotMap.winchExtPort.getInt(),RobotMap.winchRetPort.getInt());
	}
	
	/**
	 * @return the instance of this subsystem.
	 */
	public static CatapultWinch getInstance() {
		if (instance == null) {
			instance = new CatapultWinch();
		}
		return instance;
	}
	
	/**
	 * Sets the default command for the subsystem.
	 */
    public void initDefaultCommand() {
    	setDefaultCommand(new WinchWithJoystick());
    }
    
    /**
     * This method sets the motor to move the catapult to the set position.
     * A positive value will drive the winch downward.
     * 
     * @param winchSpeed 1.0 to -1.0
     */
    public void setCatapult(double winchSpeed){
    	winchMotor.set(winchSpeed);
    }
    /**
     * This method releases the latch on the catapult.
     */
    public void open(){
    	winchSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    /**
     * This method checks if the catapult has reached the set position.
     * @return true when the winch is lowered all the way
     */
    public boolean isCatapultSet(){
    	//TODO: Verify that the switch returns true when the switch is pressed.
       	return winchInputSwitch.get();
    }
}

