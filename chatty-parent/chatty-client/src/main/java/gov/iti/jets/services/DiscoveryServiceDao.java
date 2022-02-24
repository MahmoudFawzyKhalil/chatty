package gov.iti.jets.services;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DiscoveryServiceDao {
    public static void main( String[] args ) {
        new Thread(() -> {
            try {
                DatagramSocket datagramSocket = new DatagramSocket(7777);

                while (true) {
                    datagramSocket.setBroadcast( true );

                    String message = "HELLO_HELLO";

                    DatagramPacket sentPacket = new DatagramPacket( message.getBytes(), message.getBytes().length );

                    sentPacket.setAddress( InetAddress.getByName( "255.255.255.255" ) );
                    sentPacket.setPort( 4444 );

                    datagramSocket.send( sentPacket );

                    datagramSocket.receive( sentPacket );

                    System.err.println(new String(sentPacket.getData()).trim());

                    Thread.sleep( 1000 );
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }).start();


    }
}
