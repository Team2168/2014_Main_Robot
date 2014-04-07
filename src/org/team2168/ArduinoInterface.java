package org.team2168;

import org.team2168.utils.BitRelay;

public class ArduinoInterface {
	private static ArduinoInterface instance = null;
	private static BitRelay relay1, relay2;
	private static final int NUM_BITS = 4;
	private static boolean[] bitStates = new boolean[NUM_BITS];
	
	//TRUTH TABLE FOR ARDUINO COMMUNICATIONS
	// HEX      BIT #
	//       3  2  1  0    State Description
	//=======================================
	//  1    X  0  0  1    Hot Goal Left - 1 ball
	//  5    X  1  0  1          "       - 2 ball
	//  2    X  0  1  0    Hot Goal Right - 1 ball
	//  6    X  1  1  0          "        - 2 ball
	//  3    X  0  1  1    Unknown Target - 1 ball
	//  7    X  1  1  1          "        - 2 ball
	//  0    X  X  0  0    Turn off LED strip
	//  8    1  0  0  0    Tusk Extended
	//  4    0  1  0  0    Tusk Intermediate
	//  C    1  1  0  0    Tusk Retracted
	
	/**
	 * Ensure nobody can call the constructor on this class directly.
	 */
	private ArduinoInterface() {
		relay1 = new BitRelay(RobotMap.arduinoRelay1.getInt());
		relay2 = new BitRelay(RobotMap.arduinoRelay2.getInt());
		
		//Initialize outputs to off state.
		reset();
	}
	
	/**
	 * Get the instance of this singleton class.
	 * @return the instance of the arduino interface.
	 */
	public static ArduinoInterface getInstance() {
		if(instance == null) {
			instance = new ArduinoInterface();
		}
		return instance;
	}
	
	/**
	 * Set the value of a particular bit in the arduino interface.
	 * @param bit the bit number in the message to set, zero indexed.
	 * @param val the value to set the specified bit to (true = high).
	 */
	public void set(int bit, boolean val) {
		if((bit < NUM_BITS) && (bit >= 0)) {
			bitStates[bit] = val;
		}
		
		switch(bit) {
		case 0:
			relay1.setForward(val);
			break;
		case 1:
			relay1.setReverse(val);
			break;
		case 2:
			relay2.setForward(val);
			break;
		case 3:
			relay2.setReverse(val);
			break;
		default:
			//invalid bit number, do nothing
			break;
		}
	}
	
	public boolean get(int bit) {
		boolean retVal = false;
		
		if((bit < NUM_BITS) && (bit >= 0)) {
			retVal = bitStates[bit];
		}
		
		return retVal;
	}
	
	/**
	 * Set all output pins to false.
	 */
	public void reset() {
		set(0, false);
		set(1, false);
		set(2, false);
		set(3, false);
	}
}
