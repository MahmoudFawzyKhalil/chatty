package gov.iti.jets.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class InvitationController implements Initializable {
    @FXML
    private Label friendNameLabel;

    @FXML
    private Label friendNameLabel2;

    @FXML
    private Label friendPhoneNumberLabel;

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

    }
}
