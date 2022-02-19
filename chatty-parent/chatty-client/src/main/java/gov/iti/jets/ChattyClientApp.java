package gov.iti.jets;

import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class ChattyClientApp extends Application {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();

    @Override
    public void init(){
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


    @Override
    public void start( Stage primaryStage ) {
        stageCoordinator.initStage( primaryStage );
        stageCoordinator.switchToLoginScene();

        primaryStage.setWidth( 960 );
        primaryStage.setHeight( 530 );
        primaryStage.setMinWidth( 960 );
        primaryStage.setMinHeight( 530 );
        primaryStage.show();
    }

    public static void main( String[] args ) {
        Application.launch();
    }
}