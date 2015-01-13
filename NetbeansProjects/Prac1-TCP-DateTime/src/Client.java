/*
 Program: 	Client
 Use:		to send strings to a server and wait for a reply
 containing the length of the String.
 */
import java.net.*;	// for network
import java.io.*;	// for streams

public class Client implements Constants {

    static public void main(String[] argv) {
        InetAddress serverAddr = null;
        int serverPort = SERVERPORT;
        Socket socket = null;	// the socket
        PrintWriter toServer = null;	// stream to server
        BufferedReader fromServer = null;	// stream from server
        BufferedReader kbd = null;	// stream from the keyboard

        try // to initialise host address and port number
        {
            if (argv.length == 2) {
                serverAddr = InetAddress.getByName(argv[0]);
                serverPort = Integer.parseInt(argv[1]);
            } else {
                serverAddr = InetAddress.getLocalHost();
            }
        } catch (UnknownHostException ue) {
            System.err.println("Error: Unknown host");
            System.exit(0);
        }

        try // to set up socket and streams
        {
            kbd = new BufferedReader(new InputStreamReader(System.in));

            socket = new Socket(serverAddr, serverPort);

            // Create streams and enable autoflushing on output.
            toServer = new PrintWriter(socket.getOutputStream(), true);
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Client using " + socket.toString());

        } catch (IOException e) {
            System.out.println("ERROR in initialisation: " + e);
            System.exit(1);
        }

        try // to do communication
        {
            while (true) // loop forever
            {
				// read request from kbd and write to socket.

                System.out.print("> ");
                String request = kbd.readLine().trim();
                if (request.equals(QUIT)) {
                    break;
                }

                toServer.println(request);

                String reply = fromServer.readLine();
                //int response = Integer.parseInt(reply);
                //System.out.println("Server responded with " + response);
                System.out.println("Server responded with " + reply);
            }
            System.out.println("Terminating session with server");
            toServer.println(QUIT);
        } catch (IOException ex) {
            System.out.println("ERROR with connection, stopping");

        } finally {
            // close all streams and socket	
            try {
                toServer.close();
                fromServer.close();
                socket.close();

            } catch (IOException e) {
            }
        }
    }
}
