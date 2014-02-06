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
	public static final Constant intakeMotor =
			new Constant("intakeMotor", 4);
	
	//DIO Channels////////////////////////////////////////////////////
	public static final Constant driveTrainEncoderRightA =
			new Constant("driveTrainEncoderRightA",1);
	public static final Constant driveTrainEncoderRightB =
			new Constant("driveTrainEncoderRightB",2);
	public static final Constant driveTrainEncoderLeftA =
			new Constant("driveTrainEncoderLeftA",3);
	public static final Constant driveTrainEncoderLeftB =
			new Constant("driveTrainEncoderLeftB",4);
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
	public static final Constant wheelDiameterDrivetrain = 
			new Constant("wheelDiameterDriveTrain",3);
	public static final Constant ticksPerRevolutionDrivetrain = 
			new Constant("ticksPerRevolutionDrivetrain",256);
	public static final Constant drivetrainGearRatio = 
			new Constant("drivetrainGearRatio",(27/24));
	
	/****************************************************************
     *                      Catapult Parameters                     *
     ****************************************************************/
	public static final Constant wheelRadius = new Constant("wheelRadius", 2);
	
	static {
		// Set any overridden constants from the file on startup.
		readConstantsFromFile();
	}

	
	/****************************************************************
	 * 						Control Layout							*
	 ***************************************************************/
	public static final int rightJoyAxis = 5;
	public static final int leftJoyAxis = 2;
	
	/**
	 * Prevent instantiation of this class, as it should only be used
	 * statically.
	 */
	private RobotMap() {
	}
}
