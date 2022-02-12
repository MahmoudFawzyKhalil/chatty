package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.customcontrols.ContactChatMenuItem;
import gov.iti.jets.presentation.customcontrols.GroupChatMenuItem;
import gov.iti.jets.presentation.customcontrols.GroupMemberItem;
import gov.iti.jets.presentation.models.GroupChatModel;
import gov.iti.jets.presentation.util.PaneCoordinator;
import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Callback;

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

    public static GroupChatModel g1;

    @FXML
    private ListView<GroupChatModel> groupsListView;
    private static ObservableList<GroupChatModel> observableList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        createAndAddGroupChatMenuItem();
        createAndAddContactChatMenuItem();
        groupsListView.setCellFactory(new GroupMemberCellFactory());
        observableList = FXCollections.<GroupChatModel>observableArrayList(GroupChatModel.extractor());
        observableList.add(new GroupChatModel("ITI"));
        SimpleListProperty<GroupChatModel> slp = new SimpleListProperty<>(observableList);
        groupsListView.itemsProperty().bind(slp);

        g1 = new GroupChatModel("HELLO");
        slp.add(g1);
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
        observableList.add(new GroupChatModel("ROBOT BUTTON"));
        g1.setGroupChatName("BOII");
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
        var item = new GroupChatMenuItem(new GroupChatModel("Default group"));
        item.setOnMouseClicked(e -> paneCoordinator.switchToChatPane());
        groupChatsVbox.getChildren().add(item);
    }

    private static class GroupMemberCellFactory implements Callback<ListView<GroupChatModel>, ListCell<GroupChatModel>> {
        @Override
        public ListCell<GroupChatModel> call(ListView<GroupChatModel> param) {
            ListCell<GroupChatModel> cell = new ListCell<>(){
                @Override
                public void updateItem(GroupChatModel groupChat, boolean empty) {
                    super.updateItem(groupChat, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else if (groupChat != null) {
                        setText(null);
                        setGraphic(new GroupChatMenuItem(groupChat));
                    } else {
                        setText("null");
                        setGraphic(null);
                    }
                }
            };

            // chatPaneMap is map from GroupChatModel to ChatPane
            // chatPaneMap is map from groupChatID to ChatPane
            // override equals and
            cell.setUserData("chat-id");

            cell.setOnMouseClicked(e -> System.out.println(cell.getItem()));

            return cell;
        }
    }
}
