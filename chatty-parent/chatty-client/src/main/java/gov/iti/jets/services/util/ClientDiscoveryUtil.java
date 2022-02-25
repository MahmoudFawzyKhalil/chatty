package gov.iti.jets.services.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;
import java.rmi.NotBoundException;
import java.util.concurrent.Callable;

public class ClientDiscoveryUtil implements Callable<String> {
    private static ClientDiscoveryUtil INSTANCE = new ClientDiscoveryUtil();
    private boolean isStopped = false;
    private boolean isWorking = false;
    private Logger logger = LoggerFactory.getLogger(ClientDiscoveryUtil.class);

    public static ClientDiscoveryUtil getInstance() {
        return INSTANCE;
    }

    DatagramSocket datagramSocket = null;

    private ClientDiscoveryUtil() {
        try {
            datagramSocket = new DatagramSocket(7777);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String call() throws Exception {
        if (isWorking)
            return "";
        isStopped = false;
        isWorking = true;
        String serverIp = "";

        if (datagramSocket.isClosed()) {
            try {
                datagramSocket = new DatagramSocket(7777);
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }


        while (!isStopped) {
            try {
                datagramSocket.setBroadcast(true);
                String message = "Attempting to discover a server.";

                DatagramPacket sentPacket = new DatagramPacket(message.getBytes(), message.getBytes().length);

                sentPacket.setAddress(InetAddress.getByName("255.255.255.255"));
                sentPacket.setPort(4444);

                datagramSocket.send(sentPacket);

                DatagramPacket receivedPacket = new DatagramPacket(new byte[3000], 3000);
                datagramSocket.setSoTimeout(1000);
                datagramSocket.receive(receivedPacket);

                serverIp = new String(receivedPacket.getData()).trim();

                if (!serverIp.isEmpty()) {
                    try {
                        ServiceFactory.getInstance().setRegistry(serverIp);
                        ServiceFactory.getInstance().getLoginService();
                        break;
                    } catch (NotBoundException | java.rmi.ConnectException c) {
                        logger.info("Server found put not working ...");
                        serverIp = "";
                    }
                }

            } catch (SocketTimeoutException e) {
                logger.info("Waiting ...");
            } catch (SocketException | UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stop();
        datagramSocket.close();
        return serverIp;
    }

    public void stop() {
        if (datagramSocket.isConnected()) {
            datagramSocket.close();
        }
        this.isStopped = true;
        isWorking = false;
    }
}
