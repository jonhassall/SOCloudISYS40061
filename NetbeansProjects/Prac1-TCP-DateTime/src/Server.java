/*
 Program: 	Server
 Use:		a concurrent server to work with the Client.
 */
import java.net.*;	// for network 
import java.io.*;	// for streams

public class Server implements Constants {

    public static void main(String args[]) {
        int well_known_port = SERVERPORT; // default port value
        ServerSocket serverSock = null;
        ClientThread thread = null;

        try { // to get a port number
            if (args.length > 0) {
                well_known_port = Integer.parseInt(args[0]);
            }
        } catch (NumberFormatException e) {
        } // do nothing accept default.

        try {
            serverSock = new ServerSocket(well_known_port, 10);

            // information to log file (ie screen)
            System.out.println("ServerSocket " + serverSock.toString());
            System.out.println("Entering server loop");

            while (true) // Main Server loop
            {
                Socket clientSocket = serverSock.accept();
                thread = new ClientThread(clientSocket);
                thread.start();
            }

        } catch (Exception e) {
            System.err.println("Socket Error!!!.");
            System.exit(1);
        } finally {
            try {
                serverSock.close();
            } catch (IOException e) {
            }
        }
    }
}
