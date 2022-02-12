package gov.iti.jets.presentation.models;

import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.util.Callback;

public class GroupChatModel {
    private IntegerProperty groupChatId = new SimpleIntegerProperty();
    private StringProperty groupChatName = new SimpleStringProperty();
    private ObjectProperty<Image> groupChatPicture = new SimpleObjectProperty<>();
    private ListProperty<ContactModel> groupMembersList = new SimpleListProperty<>(FXCollections.observableArrayList());

    public GroupChatModel() {

    }

    public GroupChatModel(String groupChatName, Image groupChatPicture) {
        this.groupChatName.set(groupChatName);
        this.groupChatPicture.set(groupChatPicture);
    }

    public GroupChatModel(int groupChatId, String groupChatName, Image groupChatPicture) {
        this.groupChatId.set(groupChatId);
        this.groupChatName.set(groupChatName);
        this.groupChatPicture.set(groupChatPicture);
    }

    public static Callback<GroupChatModel, Observable[]> extractor() {
        return new Callback<GroupChatModel, Observable[]>() {
            @Override
            public Observable[] call(GroupChatModel param) {
                return new Observable[]{param.groupChatName,param.groupChatPicture,param.groupMembersList};
            }
        };
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