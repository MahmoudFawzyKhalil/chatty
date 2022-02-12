package gov.iti.jets.presentation.models;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;

public class GroupChatModel {
    private StringProperty groupChatName = new SimpleStringProperty();

    public GroupChatModel(String groupChatName) {
        this.groupChatName.set(groupChatName);
    }

    public static Callback<GroupChatModel, Observable[]> extractor() {
        return new Callback<GroupChatModel, Observable[]>() {
            @Override
            public Observable[] call(GroupChatModel param) {
                return new Observable[]{param.groupChatName};
            }
        };
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
