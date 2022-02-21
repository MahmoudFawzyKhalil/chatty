package gov.iti.jets.presentation.controllers;

//import gov.iti.jets.presentation.models.ContactModel;
//import gov.iti.jets.presentation.models.GroupChatModel;
//import gov.iti.jets.presentation.models.UserModel;
//import gov.iti.jets.presentation.util.ModelFactory;
//import gov.iti.jets.presentation.util.PaneCoordinator;
//import gov.iti.jets.presentation.util.StageCoordinator;
//import gov.iti.jets.presentation.util.StatusColors;
//import gov.iti.jets.presentation.util.cellfactories.ContactChatMenuItemCellFactory;
//import gov.iti.jets.presentation.util.cellfactories.GroupChatMenuItemCellFactory;
//import gov.iti.jets.services.ConnectionDao;
//import gov.iti.jets.services.util.DaoFactory;
import gov.iti.jets.repository.DashboardRepository;
import gov.iti.jets.repository.entities.DashboardEntity;
import gov.iti.jets.repository.impls.DashboardRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private ListView<?> contactChatsListView;

    @FXML
    private ListView<?> groupChatsListView;

    @FXML
    private BarChart<String, Number> onlineUsersBarChart;

    @FXML
    private BarChart<String, Number> gendersBarChart;

    @FXML
    private PieChart countryPieChart;

    @FXML
    private Circle userProfilePicCircle;

    @FXML
    private Circle userStatusCircle;

    @FXML
    void onAddContactButtonAction(ActionEvent event) {

    }

    @FXML
    void onAddGroupButtonAction(ActionEvent event) {

    }

    @FXML
    void onAvailableStatusMenuItemAction(ActionEvent event) {

    }

    @FXML
    void onAwayStatusMenuItemAction(ActionEvent event) {

    }

    @FXML
    void onBusyStatusMenuItemAction(ActionEvent event) {

    }

    @FXML
    void onChatBotButtonAction(ActionEvent event) {

    }

    @FXML
    void onInvitationsButtonAction(ActionEvent event) {

    }

    @FXML
    void onSignOutButtonAction(ActionEvent event) {

    }

    @FXML
    void onUserProfilePicCircleMouseClicked(MouseEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series<String,Number> onlineUserBarChart = new XYChart.Series<>();
        onlineUserBarChart.setName("Online Users");
        onlineUserBarChart.getData().add(new XYChart.Data<>("Online", 120));

        XYChart.Series<String,Number> offlineUserBarChart = new XYChart.Series<>();
        offlineUserBarChart.setName("Offline Users");
        offlineUserBarChart.getData().add(new XYChart.Data<>("Offline", 50));
        onlineUsersBarChart.getData().addAll(onlineUserBarChart,offlineUserBarChart);

        XYChart.Series<String,Number> maleUserBarChart = new XYChart.Series<>();
        maleUserBarChart.setName("Male Users");
        maleUserBarChart.getData().add(new XYChart.Data<>("Male", 90));

        XYChart.Series<String,Number> femaleUserBarChart = new XYChart.Series<>();
        femaleUserBarChart.setName("Female Users");
        femaleUserBarChart.getData().add(new XYChart.Data<>("Female", 140));
        gendersBarChart.getData().addAll(maleUserBarChart,femaleUserBarChart);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Egypt",30),
                new PieChart.Data("US",20),
                new PieChart.Data("UK",70),
                new PieChart.Data("China",35));
        countryPieChart.setData(pieChartData);

        System.out.println();
    }

//    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
//    private final PaneCoordinator paneCoordinator = PaneCoordinator.getInstance();
//    private final UserModel userModel = ModelFactory.getInstance().getUserModel();
//    private final ConnectionDao connectionDao = DaoFactory.getInstance().getConnectionService();


//    @FXML
//    private Circle userProfilePicCircle;
//
//    @FXML
//    private Circle userStatusCircle;
//
//    @Override
//    public void initialize( URL location, ResourceBundle resources ) {
//        bindProfilePicCircle();
//        bindContactChatsListView();
//        bindGroupChatsListView();
//    }

//    private void bindProfilePicCircle() {
//        userProfilePicCircle.setFill( new ImagePattern( userModel.getProfilePicture() ) );
//        userModel.profilePictureProperty().addListener( e -> {
//            userProfilePicCircle.setFill( new ImagePattern( userModel.getProfilePicture() ) );
//        } );
//    }
//
//    private void bindContactChatsListView() {
//        contactChatsListView.itemsProperty().bind( userModel.contactsProperty() );
//        contactChatsListView.setCellFactory( new ContactChatMenuItemCellFactory() );
//    }
//
//    private void bindGroupChatsListView() {
//        groupChatsListView.setCellFactory( new GroupChatMenuItemCellFactory() );
//        groupChatsListView.itemsProperty().bind( userModel.groupChatsProperty() );
//    }
//
//    @FXML
//    void onAddContactButtonAction( ActionEvent event ) {
//        stageCoordinator.showAddContactStage();
//    }
//
//    @FXML
//    void onAddGroupButtonAction( ActionEvent event ) {
//        stageCoordinator.showAddGroupStage();
//    }
//
//    @FXML
//    void onAvailableStatusMenuItemAction( ActionEvent event ) {
//        userStatusCircle.setFill( StatusColors.AVAILABLE_STATUS_COLOR );
//    }
//
//    @FXML
//    void onAwayStatusMenuItemAction( ActionEvent event ) {
//        userStatusCircle.setFill( StatusColors.AWAY_STATUS_COLOR );
//    }
//
//    @FXML
//    void onBusyStatusMenuItemAction( ActionEvent event ) {
//        userStatusCircle.setFill( StatusColors.BUSY_STATUS_COLOR );
//    }
//
//    @FXML
//    void onChatBotButtonAction( ActionEvent event ) {
//        paneCoordinator.switchToChatPane();
//    }
//
//    @FXML
//    void onInvitationsButtonAction( ActionEvent event ) {
//        paneCoordinator.switchToInvitationPane();
//    }
//
//    @FXML
//    void onSignOutButtonAction( ActionEvent event ) {
////        stageCoordinator.clearSceneStagePaneMaps();
//
//        try {
//            connectionDao.unregisterClient( userModel.getPhoneNumber() );
//        } catch (NotBoundException | RemoteException e) {
//            e.printStackTrace();
//        }
//
//        ModelFactory.getInstance().clearUserModel();
//        stageCoordinator.switchToLoginScene();
//    }
//
//    @FXML
//    void onUserProfilePicCircleMouseClicked( MouseEvent event ) {
//
//        if (event.getButton().equals( MouseButton.SECONDARY )) {
//            return;
//        }
//
//        paneCoordinator.switchToUpdateProfilePane();
//    }
}
