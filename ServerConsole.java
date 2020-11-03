import common.ChatIF;

import java.util.Scanner;

public class ServerConsole implements ChatIF {
	/* class  variables */
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

	// quand il y a un message, on le traite
	public void accept()
	{
		try
		{
			server.listen();
			
			String message;
			while (true){
				message = fromConsole.nextLine();
				if(message.charAt(0) == '#' && message.length() != 1){
					String[] comArg = message.substring(1)
        					  .toLowerCase().split(" ");
        	
	    		switch(comArg[0]){
		  	    	case "quit":
		  	    		System.out.println("Shutting down server.");
		  	    		server.quit();
		  	   			break;
		  	   		case "close":
		  	   			System.out.println("Server has stopped listening for new"
		  	   					+ " clients, and has disconnected all clients.");
		  	   			server.close();
		      			break;
		  	    	case "start":
		  	    		if(!server.isListening()){
			  	    		server.listen();
		  	    		}
		  	    		else {
		  	    			System.out.println("Cannot perform this action while listening.");
		  	    		}
		  	    		break;
		  	    	case "stop":
		  	    		if(server.isListening()){
			  	    		server.stopListening();
		  	    		}
		  	    		else {
		  	    			System.out.println("The server is currently not listening.");
		  	    		}
		  	    		break;
		  	    	case "getport":
		  	    		System.out.println("Current server port is " + server.getPort());
		  	    		break;
		  	    	case "setport":
		 	    		if(!server.isListening()) {
		  	    			 System.out.println("Server port set to " + comArg[1]);
		  	    			server.setPort(Integer.parseInt(comArg[1]));
		  	    		}
		  	    		else {	
		  	    			System.out.println("The server has to be offline to perform this action.");
		  	    		}
		  	    		break;
		  	    	default:
		  	    		System.out.println("Unknown : this server command does not exist.");
		  	    		break;
					}
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
