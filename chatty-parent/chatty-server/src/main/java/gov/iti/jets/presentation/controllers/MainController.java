package gov.iti.jets.presentation.controllers;

import gov.iti.jets.network.RmiManager;
import gov.iti.jets.presentation.util.PaneCoordinator;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.ServerNotificationsService;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final RmiManager rmiManager = RmiManager.getInstance();
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ServerNotificationsService serverNotificationsService = serviceFactory.getServerNotificationsService();
    private final PaneCoordinator paneCoordinator = PaneCoordinator.getInstance();

    @FXML
    private VBox dashboardPane;

    @FXML
    private Button announcementsButton;

    @FXML
    private Button chartsButton;

    @FXML
    private BarChart<String, Number> countryBarChart;

    @FXML
    private BarChart<String, Number> gendersBarChart;

    @FXML
    private BarChart<String, Number> onlineUsersBarChart;

    @FXML
    private Button refreshButton;

    @FXML
    private ToggleButton serviceButton;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        paneCoordinator.initDashboardPane(dashboardPane);
    }

    @FXML
    void onAnnouncementsButtonAction(ActionEvent event) {
        serverNotificationsService.sendAnnouncementToClients(null); //TODO
    }

    @FXML
    void onChartsButtonAction(ActionEvent event) {
        //TODO charts button action
    }

    @FXML
    void onRefreshButtonAction(ActionEvent event) {
        //TODO refresh data
    }

    @FXML
    void onServiceButtonAction(ActionEvent event) {
        if(serviceButton.isSelected()){
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
}

