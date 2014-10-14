package org.team2168.subsystems;

import org.team2168.RobotMap;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the mechanism that keeps the balls from moving around.
 */
public class SolenoidBallTapper extends Subsystem {
	private static SolenoidBallTapper instance = null;
	private Relay tapperRelay;

	/**
	 * A private constructor to prevent multiple instances from being created.
	 */
	private SolenoidBallTapper() {
		tapperRelay = new Relay(RobotMap.ballTappersRelay.getInt());
	}

	/**
	 * @return the instance of this subsystem.
	 */
	public static SolenoidBallTapper getInstance() {
		if (instance == null) {
			instance = new SolenoidBallTapper();
		}
		return instance;
	}

	/**
	 * Set the default command
	 */
	public void initDefaultCommand() {
		//setDefaultCommand(new FlashlightOff());
	}

	/**
	 * Engage the tapper (stop ball motion)
	 */
	public void engage() {
		tapperRelay.set(Relay.Value.kForward);
	}

	/**
	 * Disengage the tapper (allow intake/firing)
	 */
	public void disengage() {
		tapperRelay.set(Relay.Value.kReverse);
	}
	
	/**
	 * Is the tapper engaged.
	 * @return true if engaged
	 */
	public boolean isEngaged() {
		return tapperRelay.get() == Relay.Value.kForward;
	}
	
	/**
	 * Is the tapper disengaged.
	 * @return true if disengaged
	 */
	public boolean isDisengaged() {
		return tapperRelay.get() == Relay.Value.kReverse;
	}
}
