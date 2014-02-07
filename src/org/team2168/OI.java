package org.team2168;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
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
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    
     //Create mapping for buttons on joystick
/**	public static final double minDriveSpeed = 0.222;
	static double JoyStickScale[][] = {
		{1.00,1.00},
		{0.90,0.68},
		{0.50,0.32},
		{0.06,minDriveSpeed},
		{0.06,0.00},
		{0.00,0.00},
		{-0.06,0.00},
		{-0.06,-minDriveSpeed},
		{-0.50,-0.32},
		{-0.90,-.68},
		{-1.00,-1.00},
		};*/
	
	public static final int     rightJoyAxis = 5;
	public static final int     leftJoyAxis  = 2;
	public static final int     triggerAxis  = 3;
	
	public Joystick baseDriver = new Joystick(1);	
	
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
    
/**    public double getbaseDriverLeftAxis() {
    	double leftSpeed = interpolate(baseDriver.getRawAxis(leftJoyAxis));
    	
    	return -leftSpeed;
    }
    
    public double getbaseDriverRightAxis() {
    	double rightSpeed = interpolate(baseDriver.getRawAxis(rightJoyAxis));
    	
    	return -rightSpeed;
    }*/
    

/**	private double interpolate(double input) {
		double retVal = 0.0;
		boolean done = false;
		double m, b;
		
		
		if (input > 1.0) {
				input = 1.0;
		} else if (input < -1.0) {
				input = -1.0;
		}
		
		
		
		for (int i = 1; !done && i < JoyStickScale.length; i++){
			if (input >= JoyStickScale[i][0]) {
				m = (JoyStickScale[i][1] - JoyStickScale[i-1][1])/(JoyStickScale[i][0]- JoyStickScale[i-1][0]);
				b = JoyStickScale[i][1] - (m * JoyStickScale[i][0]);
				retVal = m * input + b;
				
				done = true;
			}
		}
		
		return retVal;
		
	}*/
}