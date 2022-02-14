package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class AddGroupChatTwoController implements Initializable {
    private StageCoordinator stageCoordinator=StageCoordinator.getInstance();

    @FXML
    private TextField groupNameTextField;

    @FXML
    private Circle groupPictureCircle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void onCancelHyperLinkAction(ActionEvent event) {
        stageCoordinator.closeAddGroupChatStage();
    }

    @FXML
    void onCreateButtonAction(ActionEvent event) {

    }

    @FXML
    void onUploadPictureHyperLinkAction(ActionEvent event) {

    }


}