package gov.iti.jets;

import gov.iti.jets.network.RmiManager;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.repository.util.ConnectionPool;
import gov.iti.jets.services.ServerNotificationsService;
import gov.iti.jets.services.SingleInstanceUtil;
import gov.iti.jets.services.util.ServerDiscoveryUtil;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ChattyServerApp extends Application {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private RmiManager rmiManager = RmiManager.getInstance();
    private ServerNotificationsService serverNotificationsService = ServiceFactory.getInstance().getServerNotificationsService();
    private static Logger logger = LoggerFactory.getLogger( ChattyServerApp.class );

    @Override
    public void start(Stage primaryStage) {
        stageCoordinator.initStage(primaryStage);
        stageCoordinator.switchToMainServerScene();
        primaryStage.show();
        ServerDiscoveryUtil.getInstance().startDiscoveryListener();
    }

    @Override
    public void stop() throws Exception {
        ConnectionPool.getInstance().cleanup();
        serverNotificationsService.notifyClientsOfServerShutDown();
        rmiManager.stop();
    }

    public static void main(String[] args) {
        if (SingleInstanceUtil.getInstance().isAppActive()){
            logger.error( "An instance of chatty server is already running." );
            System.exit( 1 );
        }
        Application.launch();
    }
}