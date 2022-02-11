package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.customcontrols.InvitationItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class InvitationController implements Initializable {


    @FXML
    private VBox invitationsVbox;

    @FXML
    private Label numberOfRequestsLabel;

    @FXML
    void onAcceptButtonAction(ActionEvent event) {

    }

    @FXML
    void onRefuseButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createAndAddInvitationItem();
    }

    private void createAndAddInvitationItem(){
        var invitation = new InvitationItem();
        invitationsVbox.getChildren().add(invitation);
    }
}
