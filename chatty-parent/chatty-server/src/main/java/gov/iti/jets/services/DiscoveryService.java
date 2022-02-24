package gov.iti.jets.services;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DiscoveryService {
    public static void main( String[] args ) {
        new Thread(() -> {
            try {
                DatagramSocket datagramSocket = new DatagramSocket(4444);
                String serverIp = InetAddress.getLocalHost().getHostAddress();

                while (true) {

                    DatagramPacket receivedPacket = new DatagramPacket( new byte[2048], 2048 );

                    datagramSocket.receive( receivedPacket );

                    System.err.println(new String(receivedPacket.getData()).trim());

                    DatagramSocket responderSocket = new DatagramSocket();

                    DatagramPacket responderPacket = new DatagramPacket( serverIp.getBytes(),
                            serverIp.getBytes().length, receivedPacket.getSocketAddress() );

                    System.err.println(receivedPacket.getSocketAddress());

                    responderSocket.send( responderPacket );

                    Thread.sleep( 1000 );
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
