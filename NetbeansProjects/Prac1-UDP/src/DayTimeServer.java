//Title:        DayTimeServer
//Description:  UDP (Date+Time) Client-Server.
import java.net.*;
import java.io.*;
import java.util.Date;

public class DayTimeServer {

    protected static int defaultPort = 3003;
    protected static int bufLen = 8192;

    public static void main(String[] args) {

        int port = defaultPort;
        int len = bufLen;
        String str;

        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        try {             // construct socket to listen to client connections
            DatagramSocket serverSoc = new DatagramSocket(port);
            System.out.println("\nDayTime Server ready for requests on port#"
                    + port + ".\n");
            byte[] buf = new byte[len];
            DatagramPacket request = new DatagramPacket(buf, buf.length);

            while (true) {        //receive time service request
                serverSoc.receive(request);
                System.out.println("Client: " + request.getAddress()
                        + ", on port#" + request.getPort()
                        + " requested Time-Service ...\n");
                Date now = new Date();
                str = now.toString();
                byte[] data = new byte[str.length()];
                data = str.getBytes();    //send TimeOfTheDay
                DatagramPacket reply = new DatagramPacket(data, data.length,
                        request.getAddress(), request.getPort());
                serverSoc.send(reply);
            }
        } catch (UnknownHostException e) {
            System.err.println(e);
        } catch (SocketException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}
