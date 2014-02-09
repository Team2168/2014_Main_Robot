
package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.utils.MomentaryDoubleSolenoid;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This subsystem controls the exit angle of the ball when it is fired from 
 * the catapult.
 */
public class CatapultTusks extends Subsystem {
	private static CatapultTusks instance = null;
	private static MomentaryDoubleSolenoid solenoid1;
	private static MomentaryDoubleSolenoid solenoid2;

	/**
	 * A private constructor to prevent multiple instances from being created.
	 */
	private CatapultTusks() {
		solenoid1 = new MomentaryDoubleSolenoid(RobotMap.catExtPort1.getInt(),
				RobotMap.catRetPort1.getInt());
		solenoid2 = new MomentaryDoubleSolenoid(RobotMap.catExtPort2.getInt(),
				RobotMap.catRetPort2.getInt());
	}
	
	/**
	 * 
	 * @return the instance of this subsystem.
	 */
	public static CatapultTusks getInstance() {
		if (instance == null) {
			instance = new CatapultTusks();
		}
		return instance;
	}
    
	/**
	 * Sets the default command for the subsystem
	 */
	public void initDefaultCommand() {
    }
	
	/**
	 * This method is for shooting over the truss by changing the position of
	 * the tusks.
	 */
	public void trussShot() {
		solenoid1.set(DoubleSolenoid.Value.kReverse);
		solenoid2.set(DoubleSolenoid.Value.kReverse);
	}
	
	/**
	 * This method is for shooting close to the goal by changing the position 
	 * of the tusks.
	 */
	public void longRangeShot(){
		solenoid1.set(DoubleSolenoid.Value.kForward);
		solenoid2.set(DoubleSolenoid.Value.kForward);
	}
	
	/**
	 * This method is for shooting long range by changing the position of
	 * the tusks.
	 */
	public void shortRangeShot(){
		solenoid1.set(DoubleSolenoid.Value.kForward);
		solenoid2.set(DoubleSolenoid.Value.kReverse);
	}
	/**
	 * For manually extending solenoid 1.
	 */
	public void extendSolenoid1(){
		solenoid1.set(DoubleSolenoid.Value.kForward);
	}
	
	/**
	 * For manually retracting solenoid 1.
	 */
	public void retractSolenoid1(){
		solenoid1.set(DoubleSolenoid.Value.kReverse);
	}
	
	/**
	 * For manually extending solenoid 2.
	 */
	public void extendSolenoid2(){
		solenoid2.set(DoubleSolenoid.Value.kForward);
	}
	
	/**
	 * For manually retracting solenoid 2.
	 */
	public void retractSolenoid2(){
		solenoid2.set(DoubleSolenoid.Value.kReverse);
	}
}

