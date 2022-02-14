package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.ContactModel;
import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddGroupChatOneController implements Initializable {

    StageCoordinator stageCoordinator = StageCoordinator.getInstance();


    @FXML
    private ListView<ContactModel> contactsListView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void onCancelHyperLinkAction(ActionEvent event) {
        stageCoordinator.closeAddGroupChatStage();
    }

    @FXML
    void onNextButtonAction(ActionEvent event) {
        stageCoordinator.switchToAddGroupChatTwo();
    }
}
