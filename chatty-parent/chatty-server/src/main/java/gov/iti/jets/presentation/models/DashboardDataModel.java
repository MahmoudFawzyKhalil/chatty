package gov.iti.jets.presentation.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class DashboardDataModel {
    private IntegerProperty femaleUsers = new SimpleIntegerProperty();

    public int getFemaleUsers() {
        return femaleUsers.get();
    }

    public IntegerProperty femaleUsersProperty() {
        return femaleUsers;
    }

    public void setFemaleUsers(int femaleUsers) {
        this.femaleUsers.set(femaleUsers);
    }

    public DashboardDataModel() {
        this.femaleUsers = femaleUsers;
    }
}
