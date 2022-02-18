package gov.iti.jets.repository.entities;

public class ContactEntity {

    private String phoneNumber;
    private String displayName;
    private String profilePicture;
    private UserStatusEntity currentStatus;

    public ContactEntity() {
    }

    public ContactEntity(String phoneNumber, String displayName, String profilePicture, UserStatusEntity currentStatus) {
        this.phoneNumber = phoneNumber;
        this.displayName = displayName;
        this.profilePicture = profilePicture;
        this.currentStatus = currentStatus;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public UserStatusEntity getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(UserStatusEntity currentStatus) {
        this.currentStatus = currentStatus;
    }
}
