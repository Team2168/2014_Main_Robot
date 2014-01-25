package org.team2168.subsystems;

import java.io.*;
import java.net.*;

public class Vision {
	
	void getTCPPacket(String ip, int port) throws IOException
	{
	
		while(true)
		{
			Socket clientSocket = new Socket(ip, port);
			//Send to server
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			outToServer.writeBytes("1");	
			String sentence;
			String modifiedSentence;
			//Recieve from server
			BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			sentence = inFromUser.readLine();
			modifiedSentence = inFromServer.readLine();
			System.out.println("FROM SERVER: " + modifiedSentence);
			clientSocket.close();
		}
	}
	
}
