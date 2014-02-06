
package org.team2168.subsystems;

import org.team2168.RobotMap;
import org.team2168.commands.FlashlightOff;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the flashlight. This will be turned on and off to help aid
 * lining up our alliance partners with our truss shots.
 */
public class Flashlight extends Subsystem {
    Relay lightRelay;

    public Flashlight() {
    	lightRelay = new Relay(RobotMap.flashlightRelay.getInt());
    	lightRelay.setDirection(Relay.Direction.kForward);
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

