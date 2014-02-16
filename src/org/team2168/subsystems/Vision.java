package org.team2168.subsystems;

import org.team2168.PIDController.Sensors.TCPCameraSensor;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem{
	
	TCPCameraSensor cam = new TCPCameraSensor(1111, 1000);
	private volatile String[] dataReceived;
	private static Vision instance = null;
	
	public Vision()
	{
		
		
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
	
	
}
