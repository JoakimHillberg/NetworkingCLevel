import java.net.*;
import java.io.*;

public class MulticastChatClient {
    public static void main(String[] args) {
        try {
            // Default port number we are going to use
            int portNumber = 5000;
            if (args.length >= 1) {
                portNumber = Integer.parseInt(args[0]);
            }

            // Create a MulticastSocket
            MulticastSocket chatMulticastSocket = new MulticastSocket(portNumber);

            // Determine the IP address of a host, given the host name
            InetAddress group = InetAddress.getByName("225.4.5.6");

            // Joins a multicast group
            chatMulticastSocket.joinGroup(group);

            // Prompt a user to enter a message
            String msg = "";
            System.out.println("Type a message for the server:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            msg = br.readLine();

            // Send the message to Multicast address
            DatagramPacket data = new DatagramPacket(msg.getBytes(),0,msg.length(),group,portNumber);
            chatMulticastSocket.send(data);

            // Close the socket
            chatMulticastSocket.close();
        } catch (IOException ie) {
            System.out.println("I/O error " + ie);
        }
    }
}