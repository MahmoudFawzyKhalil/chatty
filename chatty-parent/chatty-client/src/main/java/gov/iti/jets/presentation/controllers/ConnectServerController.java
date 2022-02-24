package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ConnectServerController implements Initializable {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    @FXML
    private TextField serverIpTextField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void onAutoDetectButtonAction(ActionEvent event) {
        stageCoordinator.switchToLoginScene();
    }

    @FXML
    void onLocalConnectButtonAction(ActionEvent event) {

    }
}
