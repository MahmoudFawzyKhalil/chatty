package gov.iti.jets.presentation.controllers;

import gov.iti.jets.network.RmiManager;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.ServerNotificationsService;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class MainServerController implements Initializable {

    private final RmiManager rmiManager = RmiManager.getInstance();
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ServerNotificationsService serverNotificationsService = serviceFactory.getServerNotificationsService();

    @FXML
    private ToggleButton startStopServiceToggleButton;
    @FXML
    private TextArea announcementTextArea;
    @FXML
    private Button sendMessageButton;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

    }

    public void onServiceToggleButtonAction( ActionEvent actionEvent ) {
        if(startStopServiceToggleButton.isSelected()){
            startServices();
        } else {
            stopServices();
        }
    }

    private void startServices() {
        try {
            rmiManager.start();
            stageCoordinator.showMessageNotification( "Successfully started services.",
                    "It's time to start receiving requests!" );
        } catch (RemoteException e) {
            stageCoordinator.showErrorNotification( "Failed to start services. Please check the logs." );
            e.printStackTrace();
        }
    }

    private void stopServices() {
        try {
            serverNotificationsService.notifyClientsOfServerShutDown();
            rmiManager.stop();
            stageCoordinator.showMessageNotification( "Successfully stopped services.",
                    "Going to bed." );
        } catch (RemoteException | NotBoundException e) {
            stageCoordinator.showErrorNotification( "Failed to stop services. Please check the logs." );
            e.printStackTrace();
        }
    }

    public void onSendMessageButtonAction( ActionEvent actionEvent ) {
        // TODO
        //  This method should send announcements to all online users
    }
}
