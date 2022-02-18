package gov.iti.jets;

import gov.iti.jets.network.RmiManager;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.repository.entities.UserEntity;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Optional;


public class ChattyServerApp extends Application {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private RmiManager rmiManager = RmiManager.getInstance();

    @Override
    public void start(Stage primaryStage){
        // stageCoordinator.initStage(primaryStage);
        // stageCoordinator.switchToLoginScene();
        primaryStage.setMinWidth(940);
        primaryStage.setMinHeight(500);
        primaryStage.show();

        Optional<UserEntity> user = RepositoryFactory.getInstance().getUserRepository().getUserByPhoneNumber("11111111111");
        if(!user.isEmpty()){
            System.out.println(user.get().getDisplayName());
            System.out.println(user.get().getCurrentStatus().getStatusName());
            System.out.println(user.get().getDisplayName());
        }
    }

    @Override
    public void stop() throws Exception {
        rmiManager.close();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}