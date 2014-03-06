package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.PIDController.sensors.TCPCameraSensor;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Vision extends Subsystem{
	
	TCPCameraSensor cam;

	private volatile String[] dataReceived;
	private static Vision instance = null;
	
	private int LeftOrRightHot;

	private static Servo vision_servo;

	
	/**
	 * Default constructor for vision target. TCP Listens on Port 1111;
	 */
	public Vision()
	{
		cam = new TCPCameraSensor(1111, 200);
		vision_servo = new Servo(RobotMap.visionServo.getInt());
		//initialize data variable
		dataReceived = new String[cam.getMessageLength()];
		
		for(int i =0; i<cam.getMessageLength(); i++)
			dataReceived[i] = "0";
		
		LeftOrRightHot = 0;

		cam.start();
	}
	/**
	 * @return singleton
	 */
	public static Vision getInstance() {
		if (instance == null) {
			instance = new Vision();
		}
		return instance;
	}



	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	/**
	 * 
	 * @param angle sets servo to proper angle
	 */
	public void setAngle(double angle) {
		vision_servo.setAngle(angle);
	}


	/**
	 * @return true when the beaglebone reports it know the match has started, false otherwise
	 */
	public boolean isMatchStart()
	{
		return cam.isMatchStart();	
	}
	
	/**
	 * 
	 * @return true if the beaglebone detected a left hot target after match start
	 */
	public boolean isLeftHot()
	{
		if (cam.isMatchStart() && (cam.LeftOrRightHot() == -1))
			return true;
		else 
			return false;
		
	}
	
	/**
	 * 
	 * @return true if the beaglebone detected a right hot target after match start
	 */
	public boolean isRightHot()
	{
		if (cam.isMatchStart() && (cam.LeftOrRightHot() == 1))
			return true;
		else 
			return false;
				
	}
	
	/**
	 * 
	 * @return true if the beaglebone sees a hot target in its current view.  This ignores match start.
	 */
	public boolean isHotinView()
	{
		return cam.isHotInView();
				
	}
	
	public boolean isValidFrame()
	{
		return cam.isValidFrame();
				
	}
/**
 * 
 * @param LorR make 1 for Right, -1 for left, 0 otherwise
 */
	public void setLeftOrRightHot(int LorR)
	{
		LeftOrRightHot = LorR;
	}
	
	public int getLeftOrRightHot()
	{
		return LeftOrRightHot;
	}
	
	public int getCamLeftOrRightHot()
	{
		return cam.LeftOrRightHot();
	}
	
	public double getDistance()
	{
		return cam.getDitance();
	}
}