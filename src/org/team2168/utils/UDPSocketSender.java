package org.team2168.utils;

import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;
import javax.microedition.io.DatagramConnection;

public class UDPSocketSender {
	int port = 4444;
	int portNumber = 41234;
	String message;
	byte[] buf;
	Datagram packetout;
	String[] arr;

	boolean enable;

	// A datagram connection.
	private DatagramConnection conn;
	private DatagramConnection serverIP;
	// A datagram which holds sent and received data.
	private Datagram dgramIn;
	private Datagram dgramOut;
	// The address of the Network Time Protocol (NTP) daemon
	// process on a particular server. NTP uses the UDP
	// protocol.
	private String addressOut = "datagram://10.21.68.4:1140";
	private String addressIn = "datagram://:1130";

	private Object lock1 = new Object();
	private Object lock2 = new Object();
	// Create a socket to listen on the port.
	Datagram dsocket;

	Thread t1;
	Thread t2;

	public UDPSocketSender() {
		arr = new String[3];
		arr[0] = "0";
		arr[1] = "0";
		arr[2] = "0";

		// Open a client connection.
		try {
			conn = (DatagramConnection) Connector.open(addressIn);
		} catch (IOException e) {
			e.printStackTrace();

		}

		try {
			serverIP = (DatagramConnection) Connector.open(addressOut);
		} catch (IOException e) {
			e.printStackTrace();
		}

		enable = false;

	}

	public void listener() {

		t1 = new Thread(new Runnable() {

			public void run() {

				// Create a buffer to read datagrams into. If a
				// packet is larger than this buffer, the
				// excess will simply be discarded!

				int maxLength;
				try {
					maxLength = conn.getMaximumLength();
					dgramIn = conn.newDatagram(maxLength);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				// Ensure that the read/write pointer is at the
				// beginning of the buffer, so data will overwrite
				// any unitialized buffer memory.
				dgramIn.reset();

				System.out.println("Listening on: " + dgramIn.getAddress());

				// Now loop forever, waiting to receive packets and printing
				// them.
				while (true) {
					// Wait to receive a datagram
					try {
						conn.receive(dgramIn);
						message = dgramIn.readUTF();
					} catch (IOException e) {
						e.printStackTrace();
					}

					synchronized (lock2) {
						arr = Util.split(message, ",");
						System.out.println(arr[0] + "," + arr[1]);
					}

					synchronized (lock1) {
						if (strToBool(arr[2])) {
							enable = true;
						} else {
							enable = false;
						}
					}
					// Reset the length of the packet before reusing it.
					dgramIn.reset();
				}
			}
		}

		);

		t1.start();

	}

	public void sender() {

		t2 = new Thread(new Runnable() {

			public void run() {

				int i = 0;

				while (true) {
					if (enable) {

						synchronized (lock2) {
							message = "{\"S\":"
									+ ((Math.sin(i) + 1) * (Double
											.valueOf(arr[0])).doubleValue())
									+ ", \"V\":"
									+ ((Math.cos(i) + 1) * (Double
											.valueOf(arr[1])).doubleValue())
									+ ", \"C\":" + Math.cos(i) + ", \"D P\":"
									+ .1234567890123456789012345678901234567890
									+ "}";
						}
						buf = message.getBytes();

						try {
							dgramOut = serverIP.newDatagram(buf, buf.length,
									addressOut);

							serverIP.send(dgramOut);
						} catch (IOException e) {
							e.printStackTrace();
						}
						System.out.println(i + " sent " + message + "@"
								+ buf.length);

						i++;
					}
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

			}
		}

		);
		t2.start();
	}

	public static boolean strToBool(String s) {
		// don't hard code your parameter.
		return s.equalsIgnoreCase("true");
	}
}

// String Message;
//
// Message = "{"
// +", \"_expected Period", this.period
// +", \"_execcution Time\":" + this.executionTime
// +", \"_output\":" + this.co
// +", \"_error\":" + this.err
// +", \"_Prop Term\":" + this.prop
// +", \"_Integ Term\":" + this.integ
// +", \"_Deriv Term\":" + this.deriv
// +", \"_Error Sum\":" + this.errsum
// + ", \"_CO Unsaturated\":" + this.coNotSaturated
// + ", \"_P_Used\":" + this.p
// + ", \"_I_Used\":" + this.i
// + ", \"_D_Used\":" + this.d
//
// + ", \"_Encoder Rate\":" + this.cp
// + ", \"_setPoint\":" + this.sp
//
// + ", \"_max Pos Output\":" + this.maxPosOutput
// + ", \"_max Neg Output\":" + this.maxNegOutput
// + ", \"_min Pos Output\":" + this.minPosOutput
// + ", \"_min Neg Output\":" + this.minNegOutput
//
// + ", \"_deriv Filter Constant\":" + this.r
// + ", \"_acceptable Err\":" + this.acceptErrorDiff
//
//
// + ", \"_PID Enabled\":" + this.enable
// + ", \"_debug Enabled\":" + this.debugEnabled
// + ", \"_deriv Filter Enabled\":" + this.enDerivFilter
// + ", \"_Gain Sched Enabled\":" + this.enGainSched
// + ", \"_is Finished\":" + this.isFinished
//
// + "}";//end of string
