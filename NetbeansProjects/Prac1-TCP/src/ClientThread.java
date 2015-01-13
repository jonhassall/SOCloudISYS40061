/*
	class:	 	ClientThread
	Use:		to handle client communication
*/

import java.net.*;	// for network 
import java.io.*;		// for streams

public class ClientThread extends Thread implements Constants
{
	Socket 		clientSocket = null; 
	PrintWriter		out = null;
	BufferedReader	in = null;
	
	public ClientThread(Socket clientSocket)
	{
		this.clientSocket = clientSocket;
	}

	public void run()
	{
		try // to communicate with client
		{
			out	= new PrintWriter(clientSocket.getOutputStream(),true) ;
			in  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())) ;

			System.out.println("Connected to " + clientSocket);
			while (true)
			{
				String msg = in.readLine();
				if (msg.equals(QUIT)) break;
				out.println(msg.length());
                                System.out.println("String was entered: " + msg + " Length: " + msg.length());
			}
			System.err.println("Ending Connection with " + clientSocket);
		}
		catch (EOFException e)
		{ // socket unexpectedly closed
			System.err.println("Connection to client unexpectedly closed");
		}
		catch (IOException e)
		{ // error reading client input
			System.err.println("Error reading from socket " + clientSocket);
		}	
		finally
		{
			try
			{
				if (in != null) in.close();
				if (out != null) out.close();
				clientSocket.close();
			}
			catch (IOException e) {}
		}						
	}
}
