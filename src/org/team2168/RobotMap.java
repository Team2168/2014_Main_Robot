package org.team2168;

import org.team2168.PIDController.Sensors.AverageEncoder;
import org.team2168.utils.ConstantsBase;

import edu.wpi.first.wpilibj.CounterBase;

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
	public static final Constant rightDriveMotor2 = 
			new Constant("rightDriveMotor2", 2);
	public static final Constant leftDriveMotor =
			new Constant("leftDriveMotor", 3);
	public static final Constant leftDriveMotor2 =
			new Constant("leftDriveMotor2", 4);
	public static final Constant winchDriveMotor = 
			new Constant("winchDriveMotor", 5);
	public static final Constant intakeMotor =
			new Constant("intakeMotor", 6);
	
	//DIO Channels////////////////////////////////////////////////////
	public static final Constant driveTrainEncoderRightA =
			new Constant("driveTrainEncoderRightA",1);
	public static final Constant driveTrainEncoderRightB =
			new Constant("driveTrainEncoderRightB",2);
	public static final Constant driveTrainEncoderLeftA =
			new Constant("driveTrainEncoderLeftA",3);
	public static final Constant driveTrainEncoderLeftB =
			new Constant("driveTrainEncoderLeftB",4);
	public static final Constant winchEncoderA = 
			new Constant("winchEncoderA", 5);
	public static final Constant winchEncoderB = 
			new Constant("winchEncoderB", 6);
	public static final Constant winchLimitSwitch = 
			new Constant("winchLimitSwitch",5);
	public static final Constant pressureSwitch =
			new Constant("pressureSwitch", 14);
	
	
	//Relay Output Channels///////////////////////////////////////////
	public static final Constant compressorRelay =
			new Constant("compressorRelay", 1);
	public static final Constant flashlightRelay =
			new Constant("flashlightRelay", 2);
	
	//Solenoid Channels(third slot on cRio)///////////////////////////
	public static final Constant intakeExtPort = 
			new Constant("intakeExtPort", 1);
	public static final Constant intakeRetPort = 
			new Constant("intakeRetPort", 2);
	public static final Constant catExtPort1 = new Constant("catExtPort1", 3);
	public static final Constant catRetPort1 = new Constant("catRetPort1", 4);
	public static final Constant catExtPort2 = new Constant("catExtPort2", 5);
	public static final Constant catRetPort2 = new Constant("catRetPort2", 6);
	public static final Constant winchExtPort = new Constant("winchExtPort", 7);
	public static final Constant winchRetPort = new Constant("winchRetPort", 8);
	
	//Solenoid Channels(fourth slot on cRio)//////////////////////////
	
	
	//Analog Input Channels///////////////////////////////////////////
	public static final Constant gyroPort = new Constant("gyroPort", 1);
	public static final Constant ballSensorPort = new Constant("ballSensorPort", 2);
	public static final Constant potentiometerPort = new Constant("potentiometerPort", 3);
	
	/****************************************************************
     *                    Drivetrain Parameters                     *
     ****************************************************************/
	public static final Constant wheelDiameterDrivetrain = 
			new Constant("wheelDiameterDriveTrain", 3.0330);
	public static final Constant ticksPerRevolutionDrivetrain = 
			new Constant("ticksPerRevolutionDrivetrain", 256);
	public static final Constant drivetrainGearRatio = 
			new Constant("drivetrainGearRatio",(24.0/27.0));
	public static final Constant driveRateLimit = 
			new Constant("driveRateLimit", 0.15);
	
	private static final int drivePulsePerRotation = 256; //encoder ticks per rotation
	private static final double driveGearRatio = 24.0/27.0; //ratio between wheel over encoder
	public static final int driveEncoderPulsePerRot = (int) (drivePulsePerRotation*driveGearRatio); //pulse per rotation * gear ratio
	public static final double driveEncoderDistPerTick = (Math.PI * wheelDiameterDrivetrain.getDouble())/driveEncoderPulsePerRot;
	public static final CounterBase.EncodingType driveEncodingType = CounterBase.EncodingType.k4X; //count rising and falling edges on both channels
	public static final AverageEncoder.PositionReturnType drivePosReturnType = AverageEncoder.PositionReturnType.INCH;
	public static final AverageEncoder.SpeedReturnType driveSpeedReturnType = AverageEncoder.SpeedReturnType.RPM;
	public static final int driveEncoderMinRate = 10; 
	public static final int driveEncoderMinPeriod = 10;
	public static final boolean leftDriveTrainEncoderReverse = false;
	public static final boolean rightDriveTrainEncoderReverse = true;
	public static final int driveAvgEncoderVal = 5;
	
	/****************************************************************
     *                      Catapult Parameters                     *
     ****************************************************************/
	//public static final Constant wheelRadius = new Constant("wheelRadius", 2);
	 public static final Constant wheelDiameterWinch = 
			new Constant("wheelDiameterWinch", 2);
	
	private static final int winchPulsePerRotation = 256; //encoder ticks per rotation
	private static final double winchGearRatio = 1/1; //ratio between wheel over encoder
	private static final double winchStrapThickness = 3/32; //thickness of the strap that winds the winch up
	public static final int winchEncoderPulsePerRot = (int) (drivePulsePerRotation*driveGearRatio); //pulse per rotation * gear ratio
	public static final double winchEncoderDistPerTick = (Math.PI * wheelDiameterWinch.getDouble())/winchEncoderPulsePerRot;
	public static final CounterBase.EncodingType winchEncodingType = CounterBase.EncodingType.k4X; //count rising and falling edges on both channels
	public static final AverageEncoder.PositionReturnType winchPosReturnType = AverageEncoder.PositionReturnType.INCH;
	public static final AverageEncoder.SpeedReturnType winchSpeedReturnType = AverageEncoder.SpeedReturnType.RPM;
	public static final int winchEncoderMinRate = 10; 
	public static final int winchEncoderMinPeriod = 10;
	public static final int winchAvgEncoderVal = 5;
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
