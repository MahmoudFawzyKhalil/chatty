package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.ContactModel;
import gov.iti.jets.presentation.models.GroupChatModel;
import gov.iti.jets.presentation.util.PaneCoordinator;
import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private  final PaneCoordinator paneCoordinator = PaneCoordinator.getInstance();


    @FXML
    private ListView<ContactModel> contactChatsListView;

    @FXML
    private ListView<GroupChatModel> groupChatsListView;

    @FXML
    private Circle userProfilePicCircle;

    @FXML
    private Circle userStatusCircle;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createAndAddGroupChatMenuItem();
        createAndAddContactChatMenuItem();
    }

    @FXML
    void onAddContactButtonAction(ActionEvent event) {
        stageCoordinator.showAddContactStage();
    }

    @FXML
    void onAddGroupButtonAction(ActionEvent event) {
        stageCoordinator.showAddGroupStage();
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
        paneCoordinator.switchToInvitationPane();
    }

    @FXML
    void onSignOutButtonAction(ActionEvent event) {
        stageCoordinator.clearSceneStagePaneMaps();
        stageCoordinator.switchToLoginScene();
    }

    @FXML
    void onUserProfilePicCircleMouseClicked(MouseEvent event) {
        if (event.getButton().equals(MouseButton.SECONDARY)){
            return;
        }
        paneCoordinator.switchToUpdateProfilePane();
    }

    private void createAndAddContactChatMenuItem() {

    }

    private void createAndAddGroupChatMenuItem() {

    }
}
