package org.team2168.subsystems;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Vision {
	
	static ArrayList<String> data = new ArrayList<String>();
	
	void getTCPPacket(String ip, int port) throws IOException
	{
	
			//TODO decide if the server should be on the beaglebone or the cRIO.
			Socket clientSocket = new Socket(ip, port); //Connect to TCP server
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); //Create an output stream to write to.
			outToServer.writeBytes("1"); //Send a packet "1" to the server
			
			//Because the server is going to be 1:1 (every packet sent we will recieve one packet)
			
			String sentence;
			String modifiedSentence;
			
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //this will read the packet from the server. If no packets are sent then it will never get past this line of code.
			modifiedSentence = inFromServer.readLine(); //Convert it to a workable string
			System.out.println("FROM SERVER: " + modifiedSentence); //Print out to console
			
			//TODO change to vector
			
			for(String d : modifiedSentence.split(","))
			{
				data.add(d);
			}
			
			clientSocket.close(); //Close the connection
			
	
	}
	
}
