package org.team2168.utils;

import edu.wpi.first.wpilibj.Encoder;

/**
* Allows for a encoder to be measured relative to a certain encoder point.
*
* Original source from 254's 2013 robot code:
*   https://github.com/Team254/FRC-2013/blob/master/src/com/team254/lib/util/RelativeEncoder.java
*
* @author tom@team254.com (Tom Bottiglieri)
*/
public class RelativeEncoder {
  private Encoder encoder;
  int val;

  public RelativeEncoder(Encoder e) {
    this.encoder = e;
    val = 0;
  }

  public void reset() {
    val = encoder.get();
  }

  public void resetAbsolute() {
    encoder.reset();
    reset();
  }

  public int get() {
    return encoder.get() - val;
  }

  public void start() {
    encoder.start();
  }
}
