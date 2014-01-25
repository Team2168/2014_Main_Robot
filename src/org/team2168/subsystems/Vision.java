package org.team2168.subsystems;

import java.io.*;
import java.net.*;
import java.util.Vector;
import edu.wpi.first.wpilibj.command.Subsystem

public class Vision extends Subsystem{

    /**
     *
     * Gets the TCP Packet from the BeagleBone
     *
     * @param ip - IP of server
     * @param port - Port of server
     * @return Vector<String>
     * @throws IOException
     */
	Vector<String> getTCPPacket(String ip, int port) throws IOException
	{

        Vector<String> retVec = new Vector<String>();


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
			retVec.add(d);
            System.out.println(d);
		}
			
		clientSocket.close(); //Close the connection

        return retVec;
	
	}

    /**
     * Interpertes the TCPPacket Vector to figure out where the HotTarget is.
     * @param TCPPacket
     * @return String
     */
    String isHotTarget(Vector<String> TCPPacket)
    {

        boolean hotGoal_m;

        //When 2 hotGoalSide is left
        //When 1 hotGoalSide is right
        //When 0 no hot goal was found.
        int hotGoalSide_m;

        //Interpert the HOT Goal from the Vector of Strings.
        int hotGoal = Integer.parseInt(TCPPacket[0]);
        if(hostGoal) //If hotGoal has a value of 1 then there is a hot goal.
        {
            hotGoal_m = true;
            //Find out what side the HOT Goal is.

            int hotGoalSide = Integer.parseInt(TCPPacket[1]);
            //If hotGoalSide has a value of 1 it is the left side. If hotGoalSide has a value of 0 is it the right side
            if(hotGoalSide)
            {
                hotGoalSide_m = 2;
            }
            else
            {
                hotGoalSide_m = 1;
            }
        }

        //Return values in a string for calling user.

        String retString;

        if(hotGoal_m)
        {
            //Because there is a hot goal use the switch case command to find out what side it is.
            switch(hotGoalSide_m)
                {
                    case 1 : retString = "right";
                    break;

                    case 2 : retString = "left";
                    break;
                }
        }
        else
        {
            retString =  "null";
        }

        return retString;
    }
	
}
