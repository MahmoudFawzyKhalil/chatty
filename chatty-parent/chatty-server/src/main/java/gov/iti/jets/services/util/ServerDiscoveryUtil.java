package gov.iti.jets.services.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;

public class ServerDiscoveryUtil {

    private static ServerDiscoveryUtil INSTANCE = new ServerDiscoveryUtil();
    private String serverIp;
    private Logger logger= LoggerFactory.getLogger(ServerDiscoveryUtil.class);

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
        Thread discoveryThread = new Thread( () -> {
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


                    DatagramSocket responderSocket = new DatagramSocket();

                    DatagramPacket responderPacket = new DatagramPacket( serverIp.getBytes(),
                            serverIp.getBytes().length, receivedPacket.getSocketAddress() );

                    logger.info( receivedPacket.getSocketAddress().toString() );

                    responderSocket.send( responderPacket );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } );

        discoveryThread.setDaemon( true );
        discoveryThread.start();
    }
}
