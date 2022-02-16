package gov.iti.jets;

import gov.iti.jets.network.RmiManager;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.application.Application;
import javafx.stage.Stage;


public class ChattyServerApp extends Application {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private RmiManager rmiManager = RmiManager.getInstance();

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
        rmiManager.closing();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}