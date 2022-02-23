package gov.iti.jets;

import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.util.DaoFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class ChattyClientApp extends Application {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();

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

    @Override
    public void stop() throws Exception {
        super.stop();
        try {
            System.exit( 0 );
            DaoFactory.getInstance().getConnectionService().unregisterClient( ModelFactory.getInstance().getUserModel().getPhoneNumber() );
        } catch (NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }
}