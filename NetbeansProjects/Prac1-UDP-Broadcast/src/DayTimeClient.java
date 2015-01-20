//Title:        DayTimeServer
//Description:  UDP (Date+Time) Client-Server.
import java.net.*;
import java.io.*;

public class DayTimeClient {

    protected static int bufLen = 8192;

    public static void main(String[] args) {

        String hostname = "localhost";
        int port = 3003;
        int len = bufLen;

        if (args.length >= 2) { //reading server's name & port# from command-line
            hostname = args[0];
            port = Integer.parseInt(args[1]);
        }

        long runningAverage = 0;
        
        for (int i = 0; i < 2500; i++) {
            try {
                // sending "empty" request for timeOfTheDay to server
                DatagramSocket clientSoc = new DatagramSocket();
                InetAddress serverAdr = InetAddress.getByName(hostname);
                DatagramPacket request = new DatagramPacket(new byte[256], 1, serverAdr, port);

                long startTimeNano;
                long endTimeNano;
                long differenceTimeNano;

                startTimeNano = System.nanoTime();

                clientSoc.send(request);

                // wait for server's response
                DatagramPacket reply = new DatagramPacket(new byte[len], len);
                clientSoc.receive(reply);

                endTimeNano = System.nanoTime();
                differenceTimeNano = endTimeNano - startTimeNano;

                // convert datagram to a string and display
                //System.out.println(new String(reply.getData(), 0, 0, reply.getLength()));

                System.out.println("Delay: " + differenceTimeNano + " nanoseconds");
                
                //Calculate running average
                //RTTi+1 = 0.25 x RTTi + 0.75 x Srtt
                runningAverage = (long) (0.25 * runningAverage + (long) 0.75 + differenceTimeNano);
                
                System.out.println("Running average: " + runningAverage + " nanoseconds");
                
            } catch (UnknownHostException e) {
                System.err.println(e);
            } catch (SocketException e) {
                System.err.println(e);
            } catch (IOException e) {
                System.err.println(e);
            }
        }

    }
}
