package org.team2168;

import org.team2168.utils.ConstantsBase;

/**
 * The RobotMap defines constants that are used throughout the robot classes.
 * Things you'll find here:
 *   - which port all the input/output devices are wired to.
 *   - control system parameters
 * 
 * This provides a single, centralized, location to make wiring/parameter
 * modifications.
 */
public class RobotMap extends ConstantsBase {
	/****************************************************************
     *                       CRIO MAP                               *
     ****************************************************************/        
    //PWM Channels////////////////////////////////////////////////////
	public static final Constant rightDriveMotor = 
			new Constant("rightDriveMotor", 1);
	public static final Constant leftDriveMotor =
			new Constant("leftDriveMotor", 2);
	public static final Constant winchDriveMotor = 
			new Constant("winchDriveMotor",3);
	
	//DIO Channels////////////////////////////////////////////////////
	public static final Constant winchLimitSwitch = 
			new Constant("winchLimitSwitch",5);
	
	//Relay Output Channels///////////////////////////////////////////
	
	//Solenoid Channels(third slot on cRio)///////////////////////////
	public static final Constant intakeExtPort = 
			new Constant("intakeExtPort", 1);
	public static final Constant intakeRetPort = 
			new Constant("intakeRetPort", 2);
	public static final Constant catExtPort1 = new Constant("catExtPort1", 3);
	public static final Constant catRetPort1 = new Constant("catRetPort1", 4);
	public static final Constant catExtPort2 = new Constant("catExtPort2", 5);
	public static final Constant catRetPort2 = new Constant("catRetPort2", 6);
	public static final Constant winchExtPort = new Constant("winchExtPort",7);
	public static final Constant winchRetPort = new Constant("winchRetPort",8);
	
	//Solenoid Channels(fourth slot on cRio)//////////////////////////
	
	//Analog Input Channels///////////////////////////////////////////
	public static final Constant gyroPort = new Constant("gyroPort", 1);


	/****************************************************************
     *                    Drivetrain Parameters                     *
     ****************************************************************/
	//add them here
	public static final int wheelRadiusDrivetrain = 2;
	
	/****************************************************************
     *                      Catapult Parameters                     *
     ****************************************************************/
	public static final Constant wheelRadius = new Constant("wheelRadius", 2);
	

	/****************************************************************
	 * 								Gyros
	 ***************************************************************/
	
	//TODO Needs to be changed
	public static final int rotateGyro = 3;
	
	static {
		// Set any overridden constants from the file on startup.
		readConstantsFromFile();
	}

	/**
	 * Prevent instantiation of this class, as it should only be used
	 * statically.
	 */
	private RobotMap() {
	}
}
