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
			new Constant("leftDriveMotor", 1);
	
	//DIO Channels////////////////////////////////////////////////////
	public static final Constant driveTrainEncoderRightA = new Constant("driveTrainEncoderRightA",1);
	public static final Constant driveTrainEncoderRightB = new Constant("driveTrainEncoderRightB",2);
	public static final Constant driveTrainEncoderLeftA = new Constant("driveTrainEncoderLeftA",3);
	public static final Constant driveTrainEncoderLeftB = new Constant("driveTrainEncoderLeftB",4);
	
	
	//Relay Output Channels///////////////////////////////////////////
	
	//Solenoid Channels(third slot on cRio)///////////////////////////
	public static final Constant intakeUpPort = new Constant("intakeUpPort", 1);
	public static final Constant intakeDownPort = new Constant("intakeDownPort", 2);
	public static final Constant catUpPort1 = new Constant("catUpPort1", 3);
	public static final Constant catDownPort1 = new Constant("catDownPort1", 4);
	public static final Constant catUpPort2 = new Constant("catUpPort2", 5);
	public static final Constant catDownPort2 = new Constant("catDownPort2", 6);
	
	//Solenoid Channels(fourth slot on cRio)//////////////////////////
	
	//Analog Input Channels///////////////////////////////////////////
	public static final Constant gyroPort = new Constant("gyroPort", 1);


	/****************************************************************
     *                    Drivetrain Parameters                     *
     ****************************************************************/
	//add them here
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

	/**
	 * Prevent instantiation of this class, as it should only be used
	 * statically.
	 */
	private RobotMap() {
	}
}
