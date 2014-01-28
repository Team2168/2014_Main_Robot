
package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.utils.MomentaryDoubleSolenoid;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CatapultTusks extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	MomentaryDoubleSolenoid solenoid1;
	MomentaryDoubleSolenoid solenoid2;
	
	public CatapultTusks() {
		solenoid1 = new MomentaryDoubleSolenoid(RobotMap.catUpPort1.getInt(),
				RobotMap.catDownPort1.getInt());
		solenoid2 = new MomentaryDoubleSolenoid(RobotMap.catUpPort2.getInt(),
				RobotMap.catDownPort2.getInt());
	}
    
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	public void trussShot() {
		solenoid1.set(DoubleSolenoid.Value.kReverse);
		solenoid2.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void longRangeShot(){
		solenoid1.set(DoubleSolenoid.Value.kForward);
		solenoid2.set(DoubleSolenoid.Value.kForward);
	}
	
	public void shortRangeShot(){
		solenoid1.set(DoubleSolenoid.Value.kForward);
		solenoid2.set(DoubleSolenoid.Value.kReverse);
	}
}

