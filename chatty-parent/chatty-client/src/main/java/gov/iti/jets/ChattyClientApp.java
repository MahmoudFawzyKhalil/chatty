package gov.iti.jets;

import gov.iti.jets.network.ClientImpl;
import gov.iti.jets.presentation.util.ExecutorUtil;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.ConnectionDao;
import gov.iti.jets.services.util.ClientDiscoveryUtil;
import gov.iti.jets.services.util.DaoFactory;
import gov.iti.jets.services.util.SingleInstanceUtil;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.rmi.NotBoundException;


public class ChattyClientApp extends Application {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private static Logger logger = LoggerFactory.getLogger( ChattyClientApp.class );

    @Override
    public void start( Stage primaryStage ) {
        stageCoordinator.initStage( primaryStage );
        stageCoordinator.switchToConnectToServer();
        primaryStage.show();
    }

    public static void main( String[] args ) {

        if (SingleInstanceUtil.getInstance().isAppActive()) {
            logger.error( "An instance of chatty client is already running." );
            System.exit( 1 );
        }

        Application.launch();
    }

    @Override
    public void stop() {

        try {
            ConnectionDao connectionDao = DaoFactory.getInstance().getConnectionDao();

            if (connectionDao != null) {
                connectionDao.unregisterClient( ModelFactory.getInstance().getUserModel().getPhoneNumber() );
            }

            ClientDiscoveryUtil.getInstance().stop();
            ExecutorUtil.getInstance().shutDown();

            if (ClientImpl.getInstance().fileTransferTask != null) {
                ClientImpl.getInstance().fileTransferTask.close();
            }

            if (ClientImpl.getInstance().fileTransferReceivingTask != null) {
                ClientImpl.getInstance().fileTransferReceivingTask.close();
            }

        } catch (NotBoundException | IOException e) {
            logger.info( "No Connection" );
        }

        System.exit( 0 );
    }
}