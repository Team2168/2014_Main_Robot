
package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.utils.MomentaryDoubleSolenoid;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakePosition extends Subsystem {
	private static IntakePosition instance = null;
	MomentaryDoubleSolenoid actuator;
	
	/**
	 * A private constructor to prevent multiple instances from being created.
	 */
	private IntakePosition() {
		actuator = new MomentaryDoubleSolenoid(RobotMap.intakeExtPort.getInt(),
				RobotMap.intakeRetPort.getInt());
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
    
    public void intakeUp() {
    	actuator.set(DoubleSolenoid.Value.kForward);
    }
    
    public void intakeDown() {
    	actuator.set(DoubleSolenoid.Value.kReverse);
    }
}

