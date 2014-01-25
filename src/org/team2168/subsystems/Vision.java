package org.team2168.subsystems;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Vision {
	
	static ArrayList<String> data = new ArrayList<String>();
	
	void getTCPPacket(String ip, int port) throws IOException
	{
		
			//TODO Need to edit to 
	
			Socket clientSocket = new Socket(ip, port);
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			outToServer.writeBytes("1");	
			String sentence;
			String modifiedSentence;
			BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			sentence = inFromUser.readLine();
			modifiedSentence = inFromServer.readLine();
			System.out.println("FROM SERVER: " + modifiedSentence);
			
			for(String d : modifiedSentence.split(","))
			{
				data.add(d);
			}
			clientSocket.close();
	
	}
	
}
