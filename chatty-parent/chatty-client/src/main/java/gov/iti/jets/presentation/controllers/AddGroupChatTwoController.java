package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.ContactModel;
import gov.iti.jets.presentation.models.GroupChatModel;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AddGroupChatTwoController implements Initializable {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private ModelFactory modelFactory = ModelFactory.getInstance();
    private UserModel userModel = modelFactory.getUserModel();
    private GroupChatModel createGroupChatModel = modelFactory.getCreateGroupChatModel();

    @FXML
    private TextField groupNameTextField;

    @FXML
    private Circle groupPictureCircle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createGroupChatModel.groupChatNameProperty().bind(groupNameTextField.textProperty());
        createGroupChatModel.groupChatPictureProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null)
                groupPictureCircle.setFill(new ImagePattern(newVal));
        });
        System.out.println(createGroupChatModel.getGroupMembersList().size());
    }

    @FXML
    void onCancelHyperLinkAction(ActionEvent event) {
        userModel.clearSelectedContacts();
        createGroupChatModel.clear();
        stageCoordinator.closeAddGroupChatStage();
    }

    @FXML
    void onCreateButtonAction(ActionEvent event) {

    }

    @FXML
    void onUploadPictureHyperLinkAction(ActionEvent event) {

    }


}