package gov.iti.jets.presentation.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserStatusModel {
    private IntegerProperty userStatusId = new SimpleIntegerProperty();
    private StringProperty userStatusName = new SimpleStringProperty();

    public static final UserStatusModel AVAILABLE = new UserStatusModel(1, "Available");
    public static final UserStatusModel AWAY = new UserStatusModel(2, "Away");
    public static final UserStatusModel BUSY = new UserStatusModel(3, "Busy");

    public UserStatusModel(int userStatusId, String userStatusName){
        this.userStatusId.set(userStatusId);
        this.userStatusName.set(userStatusName);
    }

    public String getUserStatusName() {
        return userStatusName.get();
    }

    public StringProperty userStatusNameProperty() {
        return userStatusName;
    }

    public void setUserStatusName(String userStatusName) {
        this.userStatusName.set(userStatusName);
    }

    public int getUserStatusId() {
        return userStatusId.get();
    }

    public IntegerProperty userStatusIdProperty() {
        return userStatusId;
    }

    public void setUserStatusId(int userStatusId) {
        this.userStatusId.set(userStatusId);
    }

    @Override
    public String toString() {
        return "UserStatusModel{" +
                "userStatusId=" + userStatusId +
                ", userStatusName=" + userStatusName +
                '}';
    }
}
