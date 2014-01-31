
package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.utils.MomentaryDoubleSolenoid;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakePosition extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	MomentaryDoubleSolenoid actuator;
	
	public IntakePosition() {
		actuator = new MomentaryDoubleSolenoid(RobotMap.intakeExtPort.getInt(),
				RobotMap.intakeRetPort.getInt());
		
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

