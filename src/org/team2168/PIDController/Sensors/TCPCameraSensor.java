package org.team2168.PIDController.Sensors;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.ServerSocketConnection;
import javax.microedition.io.StreamConnection;
import org.team2168.utils.Util;

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
	private String[] dataReceived;
	private StringBuffer sb = new StringBuffer();
	private volatile boolean sendEnable;
	private volatile boolean recvEnable;

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

	/**
	 * 
	 * @param port
	 *            which is to be used to Listen for incoming TCP connections on
	 *            the FRC bot.
	 */
	public TCPCameraSensor(int port, long requestPeriod) {
		this.requestPeriod = requestPeriod;

		// initialize data messageOut
		dataReceived = new String[3];
		dataReceived[0] = "0";
		dataReceived[1] = "0";
		dataReceived[2] = "0";

		// setup socket to listen on
		this.port = port;
		addressIn = "socket://:" + port;




	}

	public void start() {
		t3 = new Thread(new Runnable() {

			public void run() {

				try {
					
					// Opens a socket to listen for incoming connections
					try {
						conn = (ServerSocketConnection) Connector.open(addressIn);


					} catch (IOException e) {
						e.printStackTrace();
					}
					

					
					// wait for a client to connect, this blocks until a connect
					// is made
					System.out.println("Listening on: " + conn.getLocalAddress()
							+ " on port: " + conn.getLocalPort());
					sc = conn.acceptAndOpen();
					System.out.println("Client Connected");
					
					// make this true if you want to send data to the beaglebone as well
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
							System.out.println(sb.toString());

							// split data into array
							dataReceived = Util.split(sb.toString(), ","); // splits

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

					while (recvEnable) 
					{							
							
							messageOut = "something to send to bone " + count + " \n";
							
							System.out.println("Sending : "+ messageOut); 

							buf = messageOut.getBytes("US_ASCII");

							count++;
							
							try {
								os.write(buf);
							} catch (IOException e) {
								// e.printStackTrace();
								System.out.println("Appears Client Closed "
										+ "the Connection");

								stopThreads();
								
								//close streams
								os.close();
								sc.close();
								conn.close();
								


								//restart server
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

}