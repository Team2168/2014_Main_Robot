package org.team2168.utils;

import java.util.TimerTask;

import org.team2168.RobotMap;
import org.team2168.subsystems.CatapultWinch;
import org.team2168.subsystems.Drivetrain;
import org.team2168.subsystems.IntakePosition;


public class ConsolePrinter {
	// tread executor
	private java.util.Timer executor;
	private long period;
	
	
	public ConsolePrinter(long period) {
		this.period = period;

	}
	
	
	public void startThread() {
		this.executor = new java.util.Timer();
		this.executor.schedule(new ConsolePrintTask(this), 0L, this.period);
	}
	
	public void print()
	{
		
		if(RobotMap.debug.getInt() == 1)
		{
    	System.out.println("Left Encoder: "  + Drivetrain.getInstance().getLeftEncoderDistance());
    	System.out.println("Right Encoder: "  + Drivetrain.getInstance().getRightEncoderDistance());
    	System.out.println("GYRO Angle: "  + Drivetrain.getInstance().getGyroAngle());
    	System.out.println();
    	System.out.println("Winch Distance: " + CatapultWinch.getInstance().getWinchEncoderDistance());
    	System.out.println("Winch Speed : " + CatapultWinch.getInstance().getWinchSpeed());
    	System.out.println();
		System.out.println("Winch Limit :" + CatapultWinch.getInstance().isCatapultRetracted());
		System.out.println("Pot Voltage :" + CatapultWinch.getInstance().getWinchPotentiometerVoltage());
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private class ConsolePrintTask extends TimerTask {
		private ConsolePrinter console;

		private ConsolePrintTask(ConsolePrinter printer) {

			if (printer == null) {
				throw new NullPointerException("printer was null");
			}
			this.console = printer;
		}

		/**
		 * called periodically in its own thread
		 */

		public void run() {
			console.print();

		}
	}
	

}
