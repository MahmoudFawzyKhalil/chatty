package gov.iti.jets.commons.dtos;

import gov.iti.jets.commons.util.ValidationUtil;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class ContactDto implements Serializable {

    @NotNull
    @Size(min = 11, max = 11)
    private String phoneNumber;

    @NotNull

    private String displayName;
    private String profilePicture;
    private UserStatusDto currentStatus;

    public ContactDto( String phoneNumber, String displayName, String profilePicture, UserStatusDto currentStatus ) {
        this.phoneNumber = phoneNumber;
        this.displayName = displayName;
        this.profilePicture = profilePicture;
        this.currentStatus = currentStatus;

        ValidationUtil.getInstance().validate( this );
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber( String phoneNumber ) {
        this.phoneNumber = phoneNumber;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName( String displayName ) {
        this.displayName = displayName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture( String profilePicture ) {
        this.profilePicture = profilePicture;
    }

    public UserStatusDto getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus( UserStatusDto currentStatus ) {
        this.currentStatus = currentStatus;
    }
}
