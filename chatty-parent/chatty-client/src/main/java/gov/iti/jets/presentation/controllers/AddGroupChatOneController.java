package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.erros.ErrorMessages;
import gov.iti.jets.presentation.models.ContactModel;
import gov.iti.jets.presentation.models.GroupChatModel;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presentation.util.cellfactories.GroupMemberItemCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AddGroupChatOneController implements Initializable {

    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private ModelFactory modelFactory = ModelFactory.getInstance();
    private UserModel userModel = modelFactory.getUserModel();
    private GroupChatModel createGroupChatModel = modelFactory.getCreateGroupChatModel();


    @FXML
    private ListView<ContactModel> contactsListView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        contactsListView.setCellFactory(new GroupMemberItemCellFactory());

        contactsListView.itemsProperty().bind(userModel.contactsProperty());
    }

    @FXML
    void onCancelHyperLinkAction(ActionEvent event) {
        userModel.clearSelectedContacts();
        createGroupChatModel.clear();
        stageCoordinator.closeAddGroupChatStage();
    }

    @FXML
    void onNextButtonAction(ActionEvent event) {
        ObservableList<ContactModel> selected = getSelected();
        /*TODO
         * disable next button
         * set notify on popub not on stage
         * */
        if (selected.size() < 2) {
            stageCoordinator.showMessageNotification("Error", ErrorMessages.ADD_GROUP_SELECT);
        } else {
            createGroupChatModel.setGroupMembersList(selected);
            stageCoordinator.switchToAddGroupChatTwo();
            userModel.clearSelectedContacts();
        }
    }

    private ObservableList<ContactModel> getSelected() {
        return userModel.getContacts().stream()
                .filter(ContactModel::isSelected)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
}
