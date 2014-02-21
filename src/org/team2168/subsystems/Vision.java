package org.team2168.subsystems;

import org.team2168.PIDController.Sensors.TCPCameraSensor;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem{
	
	TCPCameraSensor cam;
	private volatile String[] dataReceived;
	private static Vision instance = null;
	
	
	/**
	 * Default constructor for vision target. TCP Listens on Port 1111;
	 */
	public Vision()
	{
		cam = new TCPCameraSensor(1111, 1000);
		
		//initialize data variable
		dataReceived = new String[cam.getMessageLength()];
		
		for(int i =0; i<cam.getMessageLength(); i++)
			dataReceived[i] = "0";
		
		cam.start();
	}
	
	
	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public static Vision getInstance() {
		if (instance == null) {
			instance = new Vision();
		}
		return instance;
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
	
	
	
	public double getDistance()
	{
		return cam.getDitance();
	}
	
}
