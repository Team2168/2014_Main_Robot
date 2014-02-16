package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.flashlight.FlashlightOff;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the flashlight. This will be turned on and off to help aid
 * lining up our alliance partners with our truss shots.
 */
public class Flashlight extends Subsystem {
	private static Flashlight instance = null;
	private Relay lightRelay;

	/**
	 * A private constructor to prevent multiple instances from being created.
	 */
	private Flashlight() {
		lightRelay = new Relay(RobotMap.flashlightRelay.getInt());
		lightRelay.setDirection(Relay.Direction.kForward);
	}

	/**
	 * @return the instance of this subsystem.
	 */
	public static Flashlight getInstance() {
		if (instance == null) {
			instance = new Flashlight();
		}
		return instance;
	}

	/**
	 * Turn the light off by default.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new FlashlightOff());
	}

	/**
	 * Turns the flashlight on.
	 */
	public void turnOn() {
		lightRelay.set(Relay.Value.kForward);
	}

	/**
	 * Turns the flashlight off.
	 */
	public void turnOff() {
		lightRelay.set(Relay.Value.kOff);
	}
}
