package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.PIDController.Sensors.TCPCameraSensor;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem {

	TCPCameraSensor cam = new TCPCameraSensor(1111, 1000);
	private volatile String[] dataReceived;
	private static Vision instance = null;
	private static Servo vision_servo;

	public Vision() {
		vision_servo = new Servo(RobotMap.visionServo.getInt());
		cam.start();
	}

	public static Vision getInstance() {
		if (instance == null) {
			instance = new Vision();
		}
		return instance;
	}

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public void setAngle(double angle) {
		vision_servo.setAngle(angle);
	}

}
