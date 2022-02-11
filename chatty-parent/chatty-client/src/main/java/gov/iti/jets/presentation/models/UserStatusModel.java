package gov.iti.jets.presentation.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserStatusModel {
    private IntegerProperty userStatusId = new SimpleIntegerProperty();
    private StringProperty userStatusName = new SimpleStringProperty();

    public UserStatusModel(int userStatusId, String userStatusName){
        this.userStatusId = new SimpleIntegerProperty(userStatusId);
        this.userStatusName = new SimpleStringProperty(userStatusName);
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
}
