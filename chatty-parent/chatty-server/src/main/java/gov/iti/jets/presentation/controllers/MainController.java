package gov.iti.jets.presentation.controllers;

import gov.iti.jets.network.Clients;
import gov.iti.jets.network.RmiManager;
import gov.iti.jets.presentation.models.ServerModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.PaneCoordinator;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.repository.DashboardRepository;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.services.DashboardService;
import gov.iti.jets.services.ServerNotificationsService;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.application.Platform;
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
import java.util.Timer;
import java.util.TimerTask;

public class MainController implements Initializable {

    private final RmiManager rmiManager = RmiManager.getInstance();
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ServerNotificationsService serverNotificationsService = serviceFactory.getServerNotificationsService();
    private final PaneCoordinator paneCoordinator = PaneCoordinator.getInstance();
    private final Clients clients = Clients.getInstance();
    private final DashboardRepository dashboardRepository = RepositoryFactory.getInstance().getDashboardRepository();
    private final DashboardService dashboardService = serviceFactory.getDashboardService();
    private final ServerModel serverModel = ModelFactory.getInstance().getServerModel();

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
        initDashboardPaneInPaneCoordinator();
        bindChartsDataProperties();
        loadChartsData();
        scheduleLoadingChartsDataPeriodically();
    }

    private void initDashboardPaneInPaneCoordinator() {
        paneCoordinator.initDashboardPane(dashboardPane);
    }

    private void bindChartsDataProperties() {
        onlineUsersBarChart.dataProperty().bind( serverModel.onlineUsersBarChartDataProperty() );
        gendersBarChart.dataProperty().bind( serverModel.gendersBarChartDataProperty() );
        countryBarChart.dataProperty().bind( serverModel.countryBarChartDataProperty() );
    }

    private void loadChartsData() {
        serverModel.getOnlineUsersBarChartData().clear();
        serverModel.getOnlineUsersBarChartData().addAll( dashboardService.getOnlineUsersBarChartData() );

        serverModel.getGendersBarChartData().clear();
        serverModel.getGendersBarChartData().addAll( dashboardService.getGendersBarChartData() );

        serverModel.getCountryBarChartData().clear();
        serverModel.getCountryBarChartData().addAll( dashboardService.getCountryBarChartData() );
    }

    private void scheduleLoadingChartsDataPeriodically() {
        long delay = 5L * 60L * 1000L;
        long period = 5L * 60L * 1000L;

        Timer timer = new Timer(true);

        TimerTask reloadChartsDataTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater( MainController.this::loadChartsData );
            }
        };

        timer.scheduleAtFixedRate(reloadChartsDataTask, delay, period);
    }

    @FXML
    void onAnnouncementsButtonAction(ActionEvent event) {
        paneCoordinator.switchToChatPane();
    }

    @FXML
    void onChartsButtonAction(ActionEvent event) {
        paneCoordinator.switchToDashboardPane();
    }

    @FXML
    void onRefreshButtonAction(ActionEvent event) {
        loadChartsData();
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

