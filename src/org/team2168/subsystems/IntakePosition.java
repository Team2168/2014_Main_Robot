
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
	private static DigitalInput intakeSensor;
	
	/**
	 * A private constructor to prevent multiple instances from being created.
	 */
	private IntakePosition() {
		actuator = new MomentaryDoubleSolenoid(RobotMap.intakeExtPort.getInt(),
				RobotMap.intakeRetPort.getInt());
		intakeSensor = new DigitalInput(RobotMap.intakeSensor.getInt());
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
    	return intakeSensor.get();
    }
}

