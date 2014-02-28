
package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.utils.MomentaryDoubleSolenoid;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakePosition extends Subsystem {
	private static IntakePosition instance = null;
	MomentaryDoubleSolenoid actuator;
	private static DigitalInput intakeLimitSwitch;
	
	/**
	 * A private constructor to prevent multiple instances from being created.
	 */
	private IntakePosition() {
		actuator = new MomentaryDoubleSolenoid(RobotMap.intakeExtPort.getInt(),
				RobotMap.intakeRetPort.getInt());
		intakeLimitSwitch = new DigitalInput(RobotMap.intakeDownLimitSwitch.getInt());
	}
	
	/**
	 * @return the instance of this subsystem.
	 */
	public static IntakePosition getInstance() {
		if (instance == null) {
			instance = new IntakePosition();
		}
		return instance;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Raise the intake.
     */
    public void intakeUp() {
    	actuator.set(DoubleSolenoid.Value.kForward);
    }

    public boolean isIntakeUp() {
    	return actuator.get() == DoubleSolenoid.Value.kForward;
    }
    
    
    /**
     * Lower the intake.
     */
    public void intakeDown() {
    	actuator.set(DoubleSolenoid.Value.kReverse);
    }
    

    
    /**
     * Find whether or not the intake is up or down
     * @return true if the intake is completely lowered.
     */
    public boolean isIntakeDown() {
    	//TODO: verify that we return TRUE when the switch is pressed
    	return intakeLimitSwitch.get() && (actuator.get() == DoubleSolenoid.Value.kReverse);
    }
    
    public boolean getIntakeLimitSwitch(){
    	return intakeLimitSwitch.get();
}
}

