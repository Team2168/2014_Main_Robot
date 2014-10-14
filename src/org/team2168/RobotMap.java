package org.team2168;

import org.team2168.PIDController.sensors.AverageEncoder;
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
	
	public static final Constant debug = 
			new Constant("debug", 1);
	
	
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
	public static final Constant rightTapperServo =
			new Constant("rightTapperServo", 8);
	public static final Constant leftTapperServo =
			new Constant("leftTapperServo", 9);
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
	public static final Constant intakeDownLimitSwitch =
			new Constant("intakeDownLimitSwitch", 8);
	public static final Constant arduinoPin1 =
			new Constant("arduinoPin1", 10);
	public static final Constant arduinoPin2 =
			new Constant("arduinoPin2", 11);
	public static final Constant arduinoPin3 =
			new Constant("arduinoPin3", 12);
	public static final Constant arduinoPin4 =
			new Constant("arduinoPin4", 13);
	public static final Constant pressureSwitch =
			new Constant("pressureSwitch", 14);
	
	
	//Relay Output Channels///////////////////////////////////////////
	public static final Constant flashlightRelay = new Constant("flashlightRelay", 1);
	public static final Constant compressorRelay = new Constant("compressorRelay", 2);
	public static final Constant ballTappersRelay = new Constant("ballTappersRelay", 3);


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
	
	public static final Constant potentiometerPort =
			new Constant("potentiometerPort", 3);
	public static final Constant winchBallSensorPort =
			new Constant("winchBallSensorPort", 4);
	public static final Constant intakeBallSensorPort =
			new Constant("intakeBallSensorPort", 5);
	
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
	
	public static final Constant rotateDriveKP = 
			new Constant("rotateDriveKP", 0.06);
	public static final Constant rotateDriveMaxSpeed = 
			new Constant("rotateDriveMaxSpeed", 0.8);

	
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
	public static final Constant tuskIntermediatePositionDelay =
			new Constant("tuskIntermediatePositionDelay", 0.1);
	public static final Constant wheelDiameterWinch =
			new Constant("wheelDiameterWinch", 2);
	public static final Constant catapultWinchUp =
			new Constant("catapultWinchUp", 4.0);
	public static final Constant catapultWinchDown =
			new Constant("catapultWinchDown", 1.0);
	public static final Constant retractWinchSpeed =
			new Constant("retractWinchSpeed", 1.0);

	public static final Constant catapultRaiseAngle =
			new Constant("catapultRaiseAngle", 45.0);
	public static final Constant catapultRaiseVoltage =
			new Constant("catapultRaiseVoltage", 2.843); //practice bot 10/11/2014
			//new Constant("catapultRaiseVoltage", 4.221); //comp bot
	public static final Constant catapultLowerAngle =
			new Constant("catapultLowerAngle", -23.0); //practice bot
			//new Constant("catapultLowerAngle", -26.0); //comp bot?
	public static final Constant catapultLowerVoltage =
			new Constant("catapultLowerVoltage", 3.789); //practice bot. 10/11/2014
			//new Constant("catapultLowerVoltage", 3.209); //comp bot
	public static final Constant catapultWaitUntilFiredAngle =
			new Constant("catapultWaitUntilFiredAngle", 30.0);
	//TODO: Determine appropriate threshold voltage values for ball presence
	public static final Constant ballPresentOnWinchVoltage =
			new Constant("ballPresentOnWinchVoltage", 0.75);
	public static final Constant ballPresentOnWinchTime =
			new Constant("ballPresentOnWinchTime", 0.2);
	public static final Constant ballSettledVoltage =
			new Constant("ballSettledVoltage", 1.6);
	public static final Constant ballSettleTime =
			new Constant("ballSettleTime", 0.6);
	public static final Constant ballPresentVoltage =
			new Constant("ballPresentVoltage", 0.9);
	public static final Constant ballPresentTime =
			new Constant("ballPresentTime", 0.1);
	
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
     *                       Intake Parameters                      *
     ****************************************************************/
	public static final Constant intakeLowerTimeout =
			new Constant("intakeLowerTimeout", 2.5);
	public static final Constant intakeWheelVoltage =
			new Constant("intakeWheelVoltage", 0.7);
	
	public static final Constant ballTapperEngageAngle =
			//new Constant("ballTapperEngageAngle", 55.0); //practice bot
			new Constant("ballTapperEngageAngle", 70.0);
	public static final Constant ballTapperShotAngle =
			//new Constant("ballTapperShotAngle", 36.0); //practice bot
			new Constant("ballTapperShotAngle", 60.0); //comp bot
	public static final Constant ballTapperDisengageAngle =
			new Constant("ballTapperDisengageAngle", 0.0);
	public static final Constant intakeWheelSpeedWhenLowering =
			new Constant("intakeWheelSpeedWhenLowering", -0.3);

	/****************************************************************
     *                  Operator Interface Parameters               *
     ****************************************************************/
	public static final Constant minDriveSpeed =
			new Constant("minDriveSpeed", 0.2);
	public static final Constant flashlightOnTime =
			new Constant("flashlightOnTime", 15.0);

	
	/****************************************************************
     *                  Autonomous Parameters               *
     ****************************************************************/
	public static final Constant rotationAngleToHot =
			new Constant("rotationAngleToHot", 8.5);
	public static final Constant CameraSteadyStateSecs =
			new Constant("CameraSteadyStateSecs", 1.1);
	public static final Constant VisionTimeOutSecs =
			new Constant("VisionTimeOutSecs", 1.5);
	public static final Constant autoDriveDistance = 
			new Constant("autoDriveDistance", 40);
	public static final Constant autoDelayBeforeStart =
			new Constant("autoDelayBeforeStart", 2.0);
	public static final Constant autoBallSettleTime = 
			new Constant("autoBallSettleTime", 2.0);
	public static final Constant autoIntakeRunTime = 
			new Constant("autoIntakeRunTime", 1.0);
	public static final Constant autoNormalSpeed =
			new Constant("autoNormalSpeed", 0.25);
	public static final Constant autoFastSpeed =
			new Constant("autoFastSpeed", 0.6);


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
