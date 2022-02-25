package gov.iti.jets;

import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.MyExecutor;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.util.ClientDiscoveryUtil;
import gov.iti.jets.services.util.DaoFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class ChattyClientApp extends Application {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private Logger logger = LoggerFactory.getLogger(ChattyClientApp.class);

    @Override
    public void start(Stage primaryStage) {
        stageCoordinator.initStage(primaryStage);
        stageCoordinator.switchToConnectToServer();

        primaryStage.setWidth(960);
        primaryStage.setHeight(530);
        primaryStage.setMinWidth(960);
        primaryStage.setMinHeight(530);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void stop() throws Exception {
        super.stop();

        try {
            DaoFactory.getInstance().getConnectionService().unregisterClient(ModelFactory.getInstance().getUserModel().getPhoneNumber());
            ClientDiscoveryUtil.getInstance().stop();
            MyExecutor.getInstance().shutDown();
        } catch (NotBoundException | RemoteException e) {
            logger.info("No Connection");
        }

        System.exit( 0 );
    }
}