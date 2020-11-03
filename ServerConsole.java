import common.ChatIF;

import java.util.Scanner;

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
		System.out.println(message);
	}

	/* on attend des clients puis quand il y en a
	   on prend leur message
	 *  */
	public void accept()
	{
		try
		{
			server.listen();
			
			String message;
			while (true){
				message = fromConsole.nextLine();
				if(message.charAt(0) == '#' && message.length() != 1){
					System.out.println("To do : switch case");
				}
				else {
					server.handleMessageFromServerUI("SERVERMSG>" + message, this);
				}
			} 
		}
		catch (Exception ex) 
		{
			System.out.println("Unexpected error while reading from server console!");
		}
	}
}
