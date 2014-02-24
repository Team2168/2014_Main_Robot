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
	public static final Constant leftDriveMotor =
			new Constant("leftDriveMotor", 2);
	public static final Constant winchMotor = new Constant("winchMotor", 3);
	public static final Constant intakeMotor = new Constant("intakeMotor", 4);
	public static final Constant visionServo = new Constant("visionServo", 10);
	
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
	public static final Constant winchEncoderA = 
			new Constant("winchEncoderA", 6);
	public static final Constant winchEncoderB = 
			new Constant("winchEncoderB", 7);
	public static final Constant intakeSensor =
			new Constant("intakeSensor", 8);
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
	public static final Constant winchExtPort = new Constant("winchExtPort", 3);
	public static final Constant winchRetPort = new Constant("winchRetPort", 4);
	public static final Constant catExtPort1 = new Constant("catExtPort1", 5);
	public static final Constant catRetPort1 = new Constant("catRetPort1", 6);
	public static final Constant catExtPort2 = new Constant("catExtPort2", 7);
	public static final Constant catRetPort2 = new Constant("catRetPort2", 8);
	
	//Solenoid Channels(fourth slot on cRio)//////////////////////////
	
	
	//Analog Input Channels///////////////////////////////////////////
	public static final Constant gyroPort = new Constant("gyroPort", 1);
	public static final Constant ballSensorPort =
			new Constant("ballSensorPort", 2);
	public static final Constant potentiometerPort =
			new Constant("potentiometerPort", 3);
	
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
	 public static final Constant wheelDiameterWinch = 
			 new Constant("wheelDiameterWinch", 2);
	 public static final Constant catapultWinchUp =	
			 new Constant("catapultWinchUp", 4.0);
	 public static final Constant catapultWinchDown =
			 new Constant("catapultWinchDown", 1.0);
	
	private static final int winchPulsePerRotation = 256; //encoder ticks per rotation
	private static final double winchGearRatio = 1.0/1.0; //ratio between wheel over encoder
	private static final double winchStrapThickness = 3.0/32.0; //thickness of the strap that winds the winch up
	public static final int winchEncoderPulsePerRot = (int) (winchPulsePerRotation*winchGearRatio); //pulse per rotation * gear ratio
	public static final double winchEncoderDistPerTick = (Math.PI * wheelDiameterWinch.getDouble())/winchEncoderPulsePerRot;
	public static final CounterBase.EncodingType winchEncodingType = CounterBase.EncodingType.k4X; //count rising and falling edges on both channels
	public static final AverageEncoder.PositionReturnType winchPosReturnType = AverageEncoder.PositionReturnType.INCH;
	public static final AverageEncoder.SpeedReturnType winchSpeedReturnType = AverageEncoder.SpeedReturnType.RPM;
	public static final double winchEncoderMinRate = 0.13; 
	public static final int winchEncoderMinPeriod = 10;
	public static final boolean winchEncoderReverse = true;
	public static final int winchAvgEncoderVal = 5;

	/****************************************************************
     *                  Operator Interface Parameters               *
     ****************************************************************/
	public static final Constant minDriveSpeed =
			new Constant("minDriveSpeed", 0.11);
	public static final Constant flashlightOnTime =
			new Constant("flashlightOnTime", 5.0);
	
	
	
	/****************************************************************
     *                  Autonomous Parameters               *
     ****************************************************************/

	public static final Constant RotationAngleToHot =
			new Constant("RotationAngleToHot", 20.0);
	
	public static final Constant CameraSteadyStateSecs =
			new Constant("RotationAngleToHot", 1.1);

	public static final Constant VisionTimeOutSecs =
			new Constant("VisionTimeOutSecs", 1.5);

	
	
	
	
	/****************************************************************
     *                  Networking Paramters                        *
     ****************************************************************/

	

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
