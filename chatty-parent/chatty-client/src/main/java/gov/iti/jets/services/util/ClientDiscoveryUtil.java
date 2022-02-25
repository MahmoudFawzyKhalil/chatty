package gov.iti.jets.services.util;

import java.io.IOException;
import java.net.*;

public class ClientDiscoveryUtil {
    private static ClientDiscoveryUtil INSTANCE = new ClientDiscoveryUtil();

    public static ClientDiscoveryUtil getInstance() {
        return INSTANCE;
    }

    private ClientDiscoveryUtil(){

    }

    private String serverIp = "";

    public String getServerIp() {
        return serverIp;
    }

    public void startDiscoverySeeker() {
        new Thread(() -> {

            DatagramSocket datagramSocket = null;
            try {
                datagramSocket = new DatagramSocket(7777);
            } catch (SocketException e) {
                e.printStackTrace();
            }

                while (true) {
                    try {
                        datagramSocket.setBroadcast( true );
                    String message = "Attempting to discover a server.";

                    DatagramPacket sentPacket = new DatagramPacket( message.getBytes(), message.getBytes().length );

                    sentPacket.setAddress( InetAddress.getByName( "255.255.255.255" ) );
                    sentPacket.setPort( 4444 );

                    datagramSocket.send( sentPacket );

                    DatagramPacket receivedPacket = new DatagramPacket( new byte[3000], 3000 );
                    datagramSocket.setSoTimeout( 1000 );
                    datagramSocket.receive( receivedPacket );

                    serverIp = new String( receivedPacket.getData() ).trim();
                    System.err.println( serverIp );

                    if (!serverIp.isEmpty()) {
                        break;
                    }

                }catch (SocketException e) {
                        e.printStackTrace();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }).start();
    }
}
