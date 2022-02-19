package gov.iti.jets.repository.entities;

public class UserStatusEntity {
    private int statusId;
    private String statusName;

    public UserStatusEntity(int statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }
    public  UserStatusEntity(){

    }
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}

