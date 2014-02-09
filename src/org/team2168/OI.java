package org.team2168;

import org.team2168.commands.RotateDrivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// Another type of button you can create is a DigitalIOButton, which is
	// a button or switch hooked up to the cypress module. These are useful if
	// you want to build a customized operator interface.
	// Button button = new DigitalIOButton(1);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	// // TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	// Create mapping for buttons on joystick
	private Joystick baseDriver = new Joystick(1);
	private Joystick operator   = new Joystick(2);
	
	public static final int rightJoyAxis = 5;
	public static final int leftJoyAxis  = 2;
	public static final int triggerAxis  = 3;

	public Button driveButtonA = new JoystickButton(baseDriver, 1),
			driveButtonB = new JoystickButton(baseDriver, 2),
			driveButtonX = new JoystickButton(baseDriver, 3),
			driveButtonY = new JoystickButton(baseDriver, 4),
			driveButtonLeftBumper = new JoystickButton(baseDriver, 5),
			driveButtonRightBumper = new JoystickButton(baseDriver, 6),
			driveButtonReset = new JoystickButton(baseDriver, 7),
			driveButtonStart = new JoystickButton(baseDriver, 8),
			driveButtonLeftStick = new JoystickButton(baseDriver, 9),
			driveButtonRightStick = new JoystickButton(baseDriver, 10);
	
	// minSpeed needs to be tweaked based on the particular drivetrain.
	// It is the speed at which the drivetrain barely starts moving
	public static final double minDriveSpeed = 0.11;
	static double joystickScale[][] = {
		/* Joystick Input, Scaled Output */
		{ 1.00, 1.00 },
		{ 0.90, 0.68 },
		{ 0.50, 0.32 },
		{ 0.06, minDriveSpeed },
		{ 0.06, 0.00 },
		{ 0.00, 0.00 },
		{ -0.06, 0.00 },
		{ -0.06, -minDriveSpeed },
		{ -0.50, -0.32 },
		{ -0.90, -0.68 },
		{ -1.00, -1.00 } };

	public OI() {
		// DRIVER CONTROLLER BUTTON MAP ////////////////////////
		//TODO: remove this assignment, was for testing commands
		driveButtonA.whenPressed(new RotateDrivetrain(90));
		driveButtonB.whenPressed(new RotateDrivetrain(-90));
		
		// OPERATOR CONTROLLER BUTTON MAP //////////////////////
		
	}

	/**
	 * Get the left joystick y-axis value. Positive is pushing up on the stick.
	 * 
	 * @return the base driver's left joystick value (1.0 to -1.0)
	 */
	public double getBaseDriverLeftStick() {
		return interpolate(-(baseDriver.getRawAxis(leftJoyAxis)));
	}
	
	/**
	 * Get the right joystick y-axis value. Positive is pushing up on the stick.
	 * 
	 * @return the base driver's right joystick value (1.0 to -1.0)
	 */
	public double getBaseDriverRightStick() {
		return interpolate(-(baseDriver.getRawAxis(rightJoyAxis)));
	}
	
	/**
	 * Get the left joystick y-axis value. Positive is pushing up on the stick.
	 * 
	 * @return the operator's left joystick value (1.0 to -1.0)
	 */
	public double getOperatorLeftStick() {
		return -operator.getRawAxis(leftJoyAxis);
	}
	
	/**
	 * Get the right joystick y-axis value. Positive is pushing up on the stick.
	 * 
	 * @return the operator's right joystick value (1.0 to -1.0)
	 */
	public double getOperatorRightStick() {
		return -operator.getRawAxis(rightJoyAxis);
	}
	
	/**
	 * Electronic braking - aka "Falcon Claw"
	 * The more the "brake" is pulled, the slower output speed
	 * 
	 * @param inputSpeed The input value to scale back based on brake input. (1 to -1)
	 * @param brake The brake input value. (0 to -1)
	 * @return The adjusted value.
	 */
	private double falconClaw(double inputSpeed, double brake) {
		return ((1 - ((-minDriveSpeed + 1) * Math.abs(brake))) * inputSpeed);
	}

	/**
	 * A function to modify the joystick values using linear interpolation.
	 * The objective is augment the joystick value going to the motor controllers
	 *   to widen the region of "fine" control while still allowing full speed.
	 * 
	 * @param input The value to augment.
	 * @return The adjusted value.
	 */
	private double interpolate(double input) {
		double retVal = 0.0;
		boolean done = false;
		double m, b;

		//make sure input is between 1.0 and -1.0
		if (input > 1.0) {
			input = 1.0;
		} else if (input < -1.0) {
			input = -1.0;
		}

		//Find the two points in our array, between which the input falls. 
		//We will start at i = 1 since we can't have a point fall outside our array.
		for (int i = 1; !done && i < joystickScale.length; i++) {
			if (input >= joystickScale[i][0]) {
				//We found where the point falls in out array, between index i and i-1
				//Calculate the equation for the line. y=mx+b
				m = (joystickScale[i][1] - joystickScale[i-1][1])/(joystickScale[i][0] - joystickScale[i-1][0]);
				b = joystickScale[i][1] - (m * joystickScale[i][0]);
				retVal = m * input + b;

				//we're finished, don't continue to loop
				done = true;
			}
		}

		return retVal;
	}
}
