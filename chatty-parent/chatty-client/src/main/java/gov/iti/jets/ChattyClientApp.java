package gov.iti.jets;

import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.application.Application;
import javafx.stage.Stage;


public class ChattyClientApp extends Application {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public void start(Stage primaryStage){
         stageCoordinator.initStage(primaryStage);
         stageCoordinator.switchToLoginScene();

        primaryStage.setWidth(960);
        primaryStage.setHeight(530);
        primaryStage.setMinWidth(960);
        primaryStage.setMinHeight(530);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}