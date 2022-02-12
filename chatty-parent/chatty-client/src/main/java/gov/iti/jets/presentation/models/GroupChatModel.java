package gov.iti.jets.presentation.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GroupChatModel {
    private StringProperty groupChatName = new SimpleStringProperty();

    public GroupChatModel(String groupChatName) {
        this.groupChatName.set(groupChatName);
    }

    public String getGroupChatName() {
        return groupChatName.get();
    }

    public StringProperty groupChatNameProperty() {
        return groupChatName;
    }

    public void setGroupChatName(String groupChatName) {
        this.groupChatName.set(groupChatName);
    }
}
