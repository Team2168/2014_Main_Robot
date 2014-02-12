package org.team2168.subsystems;

import org.team2168.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogChannel;


public class BallSensor extends Subsystem {
	
	private AnalogChannel ballSensorVoltage;
	
	/**
	 * Gets the voltage of the sensor
	 */
	private BallSensor(){
		ballSensorVoltage = new AnalogChannel(RobotMap.ballSensorPort.getInt());
	}
	
	public double getBallSensorVoltage() {
				return ballSensorVoltage.getVoltage();
	}
	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
