package gov.iti.jets.repository.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardEntity {
    int femaleUsers;
    int maleUsers;
    int onlineUsers;
    int allUsers;
    Map<String, Integer> usersByCountry = new HashMap<>();

    public DashboardEntity() {
    }

    public DashboardEntity(int femaleUsers, int maleUsers, int onlineUsers, int allUsers, Map<String, Integer> usersByCountry) {
        this.femaleUsers = femaleUsers;
        this.maleUsers = maleUsers;
        this.onlineUsers = onlineUsers;
        this.allUsers = allUsers;
        this.usersByCountry = usersByCountry;
    }

    public int getFemaleUsers() {
        return femaleUsers;
    }

    public void setFemaleUsers(int femaleUsers) {
        this.femaleUsers = femaleUsers;
    }

    public int getMaleUsers() {
        return maleUsers;
    }

    public void setMaleUsers(int maleUsers) {
        this.maleUsers = maleUsers;
    }

    public int getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(int onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    public int getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(int allUsers) {
        this.allUsers = allUsers;
    }

    public Map<String, Integer> getUsersByCountry() {
        return usersByCountry;
    }

    public void setUsersByCountry(Map<String, Integer> usersByCountry) {
        this.usersByCountry = usersByCountry;
    }
}