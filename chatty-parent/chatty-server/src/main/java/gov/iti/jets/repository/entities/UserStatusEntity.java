package gov.iti.jets.repository.entities;

public class UserStatusEntity {
    private int userStatusId;
    private String userStatusName;

    public UserStatusEntity(int userStatusId, String userStatusName) {
        this.userStatusId = userStatusId;
        this.userStatusName = userStatusName;
    }

    public int getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(int userStatusId) {
        this.userStatusId = userStatusId;
    }

    public String getUserStatusName() {
        return userStatusName;
    }

    public void setUserStatusName(String userStatusName) {
        this.userStatusName = userStatusName;
    }
}
