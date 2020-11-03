import common.ChatIF;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

import client.*;
import common.*;

public class ServerConsole implements ChatIF {
	final public static int DEFAULT_PORT = 5555;
	
	/* instances  variables */
	EchoServer server;
	Scanner fromConsole;

	/* constructors */
	public ServerConsole(int port){
		server = new EchoServer(port);
		fromConsole = new Scanner(System.in); 
	}

	/* instances methods */
	@Override
	public void display(String message) {
		System.out.println("SERVER MSG>" + message);
	}

	public void accept() 
	{
		try
		{
			String message;
			while (true) 
			{
				message = fromConsole.nextLine();
				if(message.charAt(0) == '#' && message.length() != 1){
					System.out.println("To do : switch case");
				}
				else {
					server.handleMessageFromServerUI(message);
				}
			} 
		}
		catch (Exception ex) 
		{
			System.out.println("Unexpected error while reading from server console!");
		}
	}

	/* class methods */
	public static void main(String[] args) {
	    int port = 0;

	    try
	    {
	      port = Integer.parseInt(args[0]);
	    }
	    catch(ArrayIndexOutOfBoundsException e)
	    {
	      port = DEFAULT_PORT;
	    }
	    ServerConsole serverCon = new ServerConsole(port);
	    serverCon.accept();
	}
}
