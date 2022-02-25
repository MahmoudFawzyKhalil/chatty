package gov.iti.jets.services.util;

import java.io.IOException;
import java.net.*;

public class ServerDiscoveryUtil {

    private static ServerDiscoveryUtil INSTANCE = new ServerDiscoveryUtil();
    private String serverIp;

    public static ServerDiscoveryUtil getInstance() {
        return INSTANCE;
    }

    private ServerDiscoveryUtil() {
        try {
            serverIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void startDiscoveryListener() {
        new Thread( () -> {
            DatagramSocket datagramSocket = null;
            try {
                datagramSocket = new DatagramSocket( 4444 );
            } catch (SocketException e) {
                e.printStackTrace();
            }

            while (true) {

                DatagramPacket receivedPacket = new DatagramPacket( new byte[3000], 3000 );

                try {
                    datagramSocket.receive( receivedPacket );

                    System.err.println( new String( receivedPacket.getData() ).trim() );

                    DatagramSocket responderSocket = new DatagramSocket();

                    DatagramPacket responderPacket = new DatagramPacket( serverIp.getBytes(),
                            serverIp.getBytes().length, receivedPacket.getSocketAddress() );

                    System.err.println( receivedPacket.getSocketAddress() );

                    responderSocket.send( responderPacket );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } ).start();
    }
}