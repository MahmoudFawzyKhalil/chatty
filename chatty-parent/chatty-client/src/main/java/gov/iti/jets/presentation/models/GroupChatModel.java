package gov.iti.jets.presentation.models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class GroupChatModel {
    private IntegerProperty groupChatId;
    private StringProperty groupChatName;
    private ObjectProperty<Image> groupChatPicture;
    private ListProperty<ContactModel> groupMembersList;

    public GroupChatModel() {

    }

    public GroupChatModel(int groupChatId, String groupChatName, Image groupChatPicture) {
        this.groupChatId = new SimpleIntegerProperty(groupChatId);
        this.groupChatName = new SimpleStringProperty(groupChatName);
        this.groupChatPicture = new SimpleObjectProperty<>(groupChatPicture);
        this.groupMembersList = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public int getGroupChatId() {
        return groupChatId.get();
    }

    public IntegerProperty groupChatIdProperty() {
        return groupChatId;
    }

    public String getGroupChatName() {
        return groupChatName.get();
    }

    public StringProperty groupChatNameProperty() {
        return groupChatName;
    }

    public Image getGroupChatPicture() {
        return groupChatPicture.get();
    }

    public ObjectProperty<Image> groupChatPictureProperty() {
        return groupChatPicture;
    }

    public ObservableList<ContactModel> getGroupMembersList() {
        return groupMembersList.get();
    }

    public ListProperty<ContactModel> groupMembersListProperty() {
        return groupMembersList;
    }

    public void setGroupChatId(int groupChatId) {
        this.groupChatId.set(groupChatId);
    }

    public void setGroupChatName(String groupChatName) {
        this.groupChatName.set(groupChatName);
    }

    public void setGroupChatPicture(Image groupChatPicture) {
        this.groupChatPicture.set(groupChatPicture);
    }

    public void setGroupMembersList(ObservableList<ContactModel> groupMembersList) {
        this.groupMembersList.set(groupMembersList);
    }
}