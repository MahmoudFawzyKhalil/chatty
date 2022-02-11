package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.customcontrols.GroupMemberItem;
import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class AddGroupChatController implements Initializable {

    StageCoordinator stageCoordinator=StageCoordinator.getInstance();

    @FXML
    private CheckBox contactNameTextField;

    @FXML
    private VBox contactsVBox;

    @FXML
    private Button createButton;

    @FXML
    private TextField groupNameTextField;

    @FXML
    private Circle groupPictureCircle;

    @FXML
    private Hyperlink uploadPictureHyperLink;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactsVBox.getChildren().add(new GroupMemberItem());
    }

    @FXML
    void onCancelHyperLinkAction(ActionEvent event) {
        stageCoordinator.closeGroupContactScene();
    }

    @FXML
    void onNextButtonAction(ActionEvent event) {

    }

    @FXML
    void onCreateButtonAction(ActionEvent event) {

    }

    @FXML
    void onUploadPictureHyperLinkAction(ActionEvent event) {

    }
}
