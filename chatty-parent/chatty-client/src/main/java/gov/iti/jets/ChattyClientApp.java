package gov.iti.jets;

import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.network.ClientImpl;
import gov.iti.jets.services.util.DaoFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.rmi.RemoteException;


public class ChattyClientApp extends Application {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public void start( Stage primaryStage ) {
        stageCoordinator.initStage( primaryStage );
        stageCoordinator.switchToLoginScene();

        primaryStage.setWidth( 960 );
        primaryStage.setHeight( 530 );
        primaryStage.setMinWidth( 960 );
        primaryStage.setMinHeight( 530 );
        primaryStage.show();

        try {
            ClientImpl.getInstance().loadUserModel( null );
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args ) {
        Application.launch();
    }
}