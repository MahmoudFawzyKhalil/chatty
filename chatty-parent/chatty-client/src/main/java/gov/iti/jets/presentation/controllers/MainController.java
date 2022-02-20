package gov.iti.jets.presentation.controllers;

import gov.iti.jets.commons.dtos.StatusNotificationDto;
import gov.iti.jets.commons.enums.StatusNotificationType;
import gov.iti.jets.presentation.models.ContactModel;
import gov.iti.jets.presentation.models.GroupChatModel;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.PaneCoordinator;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presentation.util.StatusColors;
import gov.iti.jets.presentation.util.cellfactories.ContactChatMenuItemCellFactory;
import gov.iti.jets.presentation.util.cellfactories.GroupChatMenuItemCellFactory;
import gov.iti.jets.services.ConnectionDao;
import gov.iti.jets.services.util.DaoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController implements Initializable {

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final PaneCoordinator paneCoordinator = PaneCoordinator.getInstance();
    private final UserModel userModel = ModelFactory.getInstance().getUserModel();
    private final ConnectionDao connectionDao = DaoFactory.getInstance().getConnectionService();

    @FXML
    private ListView<ContactModel> contactChatsListView;

    @FXML
    private ListView<GroupChatModel> groupChatsListView;

    @FXML
    private Circle userProfilePicCircle;

    @FXML
    private Circle userStatusCircle;

    @FXML
    private ToggleButton chatBotToggleButton;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        bindProfilePicCircle();
        bindContactChatsListView();
        bindGroupChatsListView();
    }

    private void bindProfilePicCircle() {
        userProfilePicCircle.setFill( new ImagePattern( userModel.getProfilePicture() ) );
        userModel.profilePictureProperty().addListener( e -> {
            userProfilePicCircle.setFill( new ImagePattern( userModel.getProfilePicture() ) );
        } );
    }

    private void bindContactChatsListView() {
        contactChatsListView.itemsProperty().bind( userModel.contactsProperty() );
        contactChatsListView.setCellFactory( new ContactChatMenuItemCellFactory() );
    }

    private void bindGroupChatsListView() {
        groupChatsListView.setCellFactory( new GroupChatMenuItemCellFactory() );
        groupChatsListView.itemsProperty().bind( userModel.groupChatsProperty() );
    }

    @FXML
    void onAddContactButtonAction( ActionEvent event ) {
        stageCoordinator.showAddContactStage();
    }

    @FXML
    void onAddGroupButtonAction( ActionEvent event ) {
        stageCoordinator.showAddGroupStage();
    }

    @FXML
    void onAvailableStatusMenuItemAction( ActionEvent event ) {
        userStatusCircle.setFill( StatusColors.AVAILABLE_STATUS_COLOR );
        notifyOthersOfStatusUpdate(StatusNotificationType.Available);
    }

    @FXML
    void onBusyStatusMenuItemAction( ActionEvent event ) {
        userStatusCircle.setFill( StatusColors.BUSY_STATUS_COLOR );
        notifyOthersOfStatusUpdate(StatusNotificationType.Busy);
    }

    @FXML
    void onAwayStatusMenuItemAction( ActionEvent event ) {
        userStatusCircle.setFill( StatusColors.AWAY_STATUS_COLOR );
        notifyOthersOfStatusUpdate(StatusNotificationType.Away);
    }

    private void notifyOthersOfStatusUpdate(StatusNotificationType type) {
        try {
            connectionDao.notifyOthersOfStatusUpdate( createStatusNotificationDto(type), createContactsToNotifyList() );
        } catch (NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }

    private List<String> createContactsToNotifyList() {
        return userModel.getContacts()
                .stream()
                .map( ContactModel::getPhoneNumber )
                .collect( Collectors.toList());
    }

    private StatusNotificationDto createStatusNotificationDto(StatusNotificationType type) {
        return new StatusNotificationDto( userModel.getPhoneNumber(), type );
    }

    @FXML
    void onChatBotButtonAction( ActionEvent event ) {
        if (chatBotToggleButton.isSelected()){
            userModel.setIsUsingChatBot( true );
            stageCoordinator.showMessageNotification( "ChatBot is now active", "Hello, world!" );
            System.err.println("IS USING CHATBOT: " + userModel.getIsUsingChatBot());
        } else {
            userModel.setIsUsingChatBot( false );
            stageCoordinator.showMessageNotification( "ChatBot is now turned off", "Goodbye, cruel world!" );
            System.err.println("IS USING CHATBOT: " + userModel.getIsUsingChatBot());
        }
    }

    @FXML
    void onInvitationsButtonAction( ActionEvent event ) {
        paneCoordinator.switchToInvitationPane();
    }

    @FXML
    void onSignOutButtonAction( ActionEvent event ) {
        try {
            connectionDao.unregisterClient( userModel.getPhoneNumber() );
        } catch (NotBoundException | RemoteException e) {
            e.printStackTrace();
        }

        ModelFactory.getInstance().clearUserModel();
        stageCoordinator.switchToLoginScene();
    }

    @FXML
    void onUserProfilePicCircleMouseClicked( MouseEvent event ) {

        if (event.getButton().equals( MouseButton.SECONDARY )) {
            return;
        }

        paneCoordinator.switchToUpdateProfilePane();
    }
}
