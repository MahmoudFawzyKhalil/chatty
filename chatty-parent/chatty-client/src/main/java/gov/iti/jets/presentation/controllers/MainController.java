package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.customcontrols.ContactChatMenuItem;
import gov.iti.jets.presentation.customcontrols.GroupChatMenuItem;
import gov.iti.jets.presentation.util.PaneCoordinator;
import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private  final PaneCoordinator paneCoordinator = PaneCoordinator.getInstance();

    @FXML
    private VBox contactChatsVbox;

    @FXML
    private VBox groupChatsVbox;

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
        var item = new ContactChatMenuItem();
        item.setOnMouseClicked(e -> paneCoordinator.switchToChatPane());
        contactChatsVbox.getChildren().add(item);
    }

    private void createAndAddGroupChatMenuItem() {
        var item = new GroupChatMenuItem();
        item.setOnMouseClicked(e -> paneCoordinator.switchToChatPane());
        groupChatsVbox.getChildren().add(item);
    }
}
