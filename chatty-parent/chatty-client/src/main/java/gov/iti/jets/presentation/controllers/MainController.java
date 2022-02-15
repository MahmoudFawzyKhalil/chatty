package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.ContactModel;
import gov.iti.jets.presentation.models.GroupChatModel;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.PaneCoordinator;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presentation.util.StatusColors;
import gov.iti.jets.presentation.util.cellfactories.ContactChatMenuItemCellFactory;
import gov.iti.jets.presentation.util.cellfactories.GroupChatMenuItemCellFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final PaneCoordinator paneCoordinator = PaneCoordinator.getInstance();
    private final UserModel userModel = ModelFactory.getInstance().getUserModel();

    @FXML
    private ListView<ContactModel> contactChatsListView;

    @FXML
    private ListView<GroupChatModel> groupChatsListView;

    @FXML
    private Circle userProfilePicCircle;

    @FXML
    private Circle userStatusCircle;

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
    }

    @FXML
    void onAwayStatusMenuItemAction( ActionEvent event ) {
        userStatusCircle.setFill( StatusColors.AWAY_STATUS_COLOR );
    }

    @FXML
    void onBusyStatusMenuItemAction( ActionEvent event ) {
        userStatusCircle.setFill( StatusColors.BUSY_STATUS_COLOR );
    }

    @FXML
    void onChatBotButtonAction( ActionEvent event ) {
        paneCoordinator.switchToChatPane();
    }

    @FXML
    void onInvitationsButtonAction( ActionEvent event ) {
        paneCoordinator.switchToInvitationPane();
    }

    @FXML
    void onSignOutButtonAction( ActionEvent event ) {
        stageCoordinator.clearSceneStagePaneMaps();
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
