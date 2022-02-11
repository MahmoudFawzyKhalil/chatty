package gov.iti.jets.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private HBox contactChatItemHBox;

    @FXML
    private VBox contactChatsVbox;

    @FXML
    private HBox groupChatItemHBox;

    @FXML
    private VBox groupChatsVbox;

    @FXML
    private Circle userProfilePicCircle;

    @FXML
    private Circle userStatusCircle;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

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
}
