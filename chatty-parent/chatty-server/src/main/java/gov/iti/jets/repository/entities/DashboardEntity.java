package gov.iti.jets.repository.entities;

public class DashboardEntity {
    int femaleUsers;
    int maleUsers;

    public DashboardEntity() {
    }

    public DashboardEntity(int femaleUsers, int maleUsers) {
        this.femaleUsers = femaleUsers;
        this.maleUsers = maleUsers;
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
}
