package gov.iti.jets;

import gov.iti.jets.network.ClientImpl;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.ExecutorUtil;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.util.DaoFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import gov.iti.jets.services.util.ClientDiscoveryUtil;

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
            ExecutorUtil.getInstance().shutDown();
            if(ClientImpl.getInstance().fileTransferTask!=null){
                System.out.println("main method");
                ClientImpl.getInstance().fileTransferTask.close();
            }
            if(ClientImpl.getInstance().fileTransferReceivingTask!=null){

                ClientImpl.getInstance().fileTransferReceivingTask.close();
            }
        } catch (NotBoundException | RemoteException e) {
            logger.info("No Connection");
        }

        System.exit( 0 );
    }
}