package org.team2168.PIDController.sensors;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.ServerSocketConnection;
import javax.microedition.io.StreamConnection;
import org.team2168.utils.Util;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * The TCPCameraSensor class is used to grab data from a TCP socket and provide
 * it for use on an FRC robot. The intended use of this class is to retrieve
 * data from a beagleBone running Vision. This class uses 3 threads, one which
 * listens for incoming connections, a second which retrieves data from the
 * BeagleBone, and a thrid which can be used to send data to the beaglebone.
 * 
 * @author HarrilalEngineering
 */

public class TCPCameraSensor {
	private int port;
	private String messageOut;
	private byte[] buf;
	private volatile String[] dataReceived;
	private StringBuffer sb = new StringBuffer();
	private volatile boolean sendEnable;
	private volatile boolean recvEnable;
	
	private volatile boolean clientConnected;

	private DriverStation ds;

	// A TCP Socket Connection
	private ServerSocketConnection conn = null;

	// TCP Socket Stream
	private StreamConnection sc = null;

	// Threads
	private Thread t1;
	private Thread t2;
	private Thread t3;

	// Address Variable
	private String addressIn;

	private long requestPeriod;
	
	private int size;

	/**
	 * 
	 * @param port
	 *            which is to be used to Listen for incoming TCP connections on
	 *            the FRC bot.
	 */
	public TCPCameraSensor(int port, long requestPeriod) {
		this.requestPeriod = requestPeriod;


		size = 6;
		
		// initialize data messageOut 
		dataReceived = new String[size];

		dataReceived[0] = "0";
		dataReceived[1] = "0";
		dataReceived[2] = "0";
		dataReceived[3] = "0";
		dataReceived[4] = "0";
		dataReceived[5] = "0";

		// setup socket to listen on
		this.port = port;
		addressIn = "socket://:" + port;

		ds = DriverStation.getInstance();

	}

	public void start() {
		t3 = new Thread(new Runnable() {

			public void run() {

				try {

					// Opens a socket to listen for incoming connections
					try {
						conn = (ServerSocketConnection) Connector
								.open(addressIn);

					} catch (IOException e) {
						e.printStackTrace();
					}

					// wait for a client to connect, this blocks until a connect
					// is made
					clientConnected = false;
					System.out.println("Listening on: "
							+ conn.getLocalAddress() + " on port: "
							+ conn.getLocalPort());
					sc = conn.acceptAndOpen();
					System.out.println("Client Connected");
					clientConnected = true;
					

					// make this true if you want to send data to the beaglebone
					// as well
					recvEnable = true;

					listener();
					sender();

				} catch (IOException e) {

				}
			}
		}

		);

		t3.start();
	}

	private void listener() {

		t1 = new Thread(new Runnable() {

			public void run() {
				try {
					InputStream is = null;
					is = sc.openInputStream();
					int ch = 0;

					// read data until newline character is reached
					while ((ch = is.read()) != -1) {
						if ((char) ch != '\n')
							sb.append((char) ch);
						else {
							// print data received to the screen

							// split data into array
							dataReceived = Util.split(sb.toString(), ","); // splits

							

						//	System.out.println("Match Start: " + isMatchStart()+", " + "Valid Frame: " + isValidFrame()+", " + "Hot: " + isHotInView()+", " + "LorR: " + LeftOrRightHot()+", " + "dist: " + dataReceived[4]+", " + "Count: " + dataReceived[5]);



							// create new buffer
							sb = new StringBuffer();
						}
					}
				} catch (IOException x) {
					x.printStackTrace();
				}
			}

		}

		);

		t1.start();
	}

	private void sender() {
		t2 = new Thread(new Runnable() {

			public void run() {
				OutputStream os = null;
				int count = 0;
				try {
					os = sc.openOutputStream();

					while (recvEnable) {
						// we want to send if match has started to camera
						int matchStart = 0;

						if (ds.isEnabled())
							matchStart = 1;

						messageOut = String.valueOf(matchStart) + " " + count
								+ " \n";

						//System.out.println("Sending Match Start: " + messageOut);

						buf = messageOut.getBytes("US_ASCII");

						count++;

						try {
							os.write(buf);
						} catch (IOException e) {
							// e.printStackTrace();
							System.out.println("Appears Client Closed "
									+ "the Connection");

							stopThreads();

							// close streams
							os.close();
							sc.close();
							conn.close();

							// restart server
							start();

						}

						try {
							Thread.sleep(requestPeriod);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

		);
		t2.start();
	}


private void stopThreads()
{
	sendEnable = false;
	recvEnable = false;
}

public int getMessageLength()
{
	
	return size;
}

public String[] getMessage()
{

	return dataReceived;
}

//These are specific to the game, modify for each year
public boolean isMatchStart()
{

	int message = Integer.valueOf(dataReceived[0]).intValue();
	
	if (message == 1)
		return true;
	else
		return false;
	
	
}

public boolean isValidFrame()
{

	int message = Integer.valueOf(dataReceived[1]).intValue();
	
	if (message == 1)
		return true;
	else
		return false;
	
	
}

public boolean isHotInView()
{
	int message = Integer.valueOf(dataReceived[2]).intValue();
	
	if (message == 1)
		return true;
	else
		return false;
}

public int LeftOrRightHot()
{
	return Integer.valueOf(dataReceived[5]).intValue();
}


public double getDitance()
{
	double dist = Double.valueOf(dataReceived[6]).doubleValue();
	
	if (Double.isNaN(dist) || Double.isInfinite(dist))
		return 0.0;
	else
		return dist;
	
}

public double getCount()
{
	int count = Integer.valueOf(dataReceived[7]).intValue();
	
	if (Double.isNaN(count) || Double.isInfinite(count))
		return 0;
	else
		return count;
	
}

public boolean isCameraConnected()
{
	int message = Integer.valueOf(dataReceived[3]).intValue();
	
	if (message == 1)
		return true;
	else
		return false;
	
}

public boolean isProcessingTreadRunning()
{
	int message = Integer.valueOf(dataReceived[4]).intValue();
	
	if (message == 1)
		return true;
	else
		return false;

}

public boolean isClientConnected()
{
	return clientConnected;

}

}
