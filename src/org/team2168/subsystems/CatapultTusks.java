
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
	
    // TUSK ACTUATOR OPERATION
	//        --------------------------------
	//  ------|------|     --||--------|     |
	//        -//------------//-----------//--
	//         C              B           A
	//
	// To achieve the three positions on this actuator the following states must
	// be established. Truth table:
	//
	// POSITION      C           B          A                 NAME
	//   Extended    Vent        Pressure   Vent              Long Range
	//   Retracted   Pressure    Vent       Vent              Truss Shot
	//   Middle      Pressure    Vent       Pressure          Short Range
	
	//Actuator Ports B and C
	private static MomentaryDoubleSolenoid tuskSolenoid1;
	//Actuator Port A
	private static MomentaryDoubleSolenoid tuskSolenoid2;

	/**
	 * A private constructor to prevent multiple instances from being created.
	 */
	private CatapultTusks() {
		tuskSolenoid1 = new MomentaryDoubleSolenoid(RobotMap.catExtPort1.getInt(),
				RobotMap.catRetPort1.getInt());
		tuskSolenoid2 = new MomentaryDoubleSolenoid(RobotMap.catExtPort2.getInt(),
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
	 * This method is for shooting close to the goal by changing the position 
	 * of the tusks.
	 */
	public void longRangeShot(){
		tuskSolenoid1.set(DoubleSolenoid.Value.kReverse);
		tuskSolenoid2.set(DoubleSolenoid.Value.kReverse);
	}

	/**
	 * This method is for shooting over the truss by changing the position of
	 * the tusks.
	 */
	public void trussShot() {
		tuskSolenoid1.set(DoubleSolenoid.Value.kForward);
		tuskSolenoid2.set(DoubleSolenoid.Value.kReverse);
	}

	/**
	 * This method is for shooting long range by changing the position of
	 * the tusks.
	 */
	public void shortRangeShot(){
		tuskSolenoid1.set(DoubleSolenoid.Value.kReverse);
		tuskSolenoid2.set(DoubleSolenoid.Value.kForward);
	}
}

