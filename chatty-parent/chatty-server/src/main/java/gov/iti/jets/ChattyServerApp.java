package gov.iti.jets;

import gov.iti.jets.network.RmiManager;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.repository.DashboardRepository;
import gov.iti.jets.repository.entities.DashboardEntity;
import gov.iti.jets.repository.impls.DashboardRepositoryImpl;
import gov.iti.jets.repository.util.ConnectionPool;
import javafx.application.Application;
import javafx.stage.Stage;


public class ChattyServerApp extends Application {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private RmiManager rmiManager = RmiManager.getInstance();

    @Override
    public void start(Stage primaryStage){
         stageCoordinator.initStage(primaryStage);
         stageCoordinator.switchToLoginScene();
        primaryStage.setMinWidth(940);
        primaryStage.setMinHeight(500);
        primaryStage.show();
        DashboardRepositoryImpl dashboardRepository = new DashboardRepositoryImpl();

        System.out.println(dashboardRepository.getFemaleUsersNumber());
        System.out.println(dashboardRepository.getMaleUsersNumber());
    }

    @Override
    public void stop() throws Exception {
        ConnectionPool.cleanup();
        rmiManager.close();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}