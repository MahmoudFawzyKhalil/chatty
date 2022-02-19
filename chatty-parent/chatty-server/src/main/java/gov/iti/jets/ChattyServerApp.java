package gov.iti.jets;

import gov.iti.jets.network.RmiManager;
import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.*;


public class ChattyServerApp extends Application {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private RmiManager rmiManager = RmiManager.getInstance();

    @Override
    public void init(){
        new Thread(() -> {
            try {
                DatagramSocket datagramSocket = new DatagramSocket(4444);

                while (true) {

                    DatagramPacket receivedPacket = new DatagramPacket( new byte[2048], 2048 );

                    datagramSocket.receive( receivedPacket );

                    System.err.println(new String(receivedPacket.getData()).trim());

                    DatagramSocket responderSocket = new DatagramSocket();

                    String serverIp = InetAddress.getLocalHost().getHostAddress();
                    DatagramPacket responderPacket = new DatagramPacket( serverIp.getBytes(),
                            serverIp.getBytes().length, receivedPacket.getSocketAddress() );

                    System.err.println(receivedPacket.getSocketAddress());

//                    NetworkInterface.getNetworkInterfaces()

                    responderSocket.send( responderPacket );

                    Thread.sleep( 1000 );
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void start(Stage primaryStage){
        // stageCoordinator.initStage(primaryStage);
        // stageCoordinator.switchToLoginScene();
        primaryStage.setMinWidth(940);
        primaryStage.setMinHeight(500);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        rmiManager.close();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}