package gov.iti.jets.commons.dtos;

import gov.iti.jets.commons.util.ValidationUtil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDto implements Serializable {
    @NotNull
    @Size(min = 11, max = 11)
    private String phoneNumber;

    @NotNull
    @Size(min = 3, max = 20)
    private String displayName;

    @NotNull
    private String gender;

    @Email
    private String email;

    @Past
    private LocalDate birthDate;

    @NotNull
    private CountryDto country;

    @NotNull
    private UserStatusDto currentStatus;

    private String profilePicture;
    private String bio;
    private List<ContactDto> contactsList = new ArrayList<>();
    private List<GroupChatDto> groupChatList = new ArrayList<>();
    private List<InvitationDto> invitationsList = new ArrayList<>();

    public UserDto( String phoneNumber, String displayName, String gender, String profilePicture, String email, String bio,
                    LocalDate birthDate, CountryDto country, UserStatusDto currentStatus,
                    List<ContactDto> contactsList, List<GroupChatDto> groupChatList, List<InvitationDto> invitationsList ) {
        this.phoneNumber = phoneNumber;
        this.displayName = displayName;
        this.gender = gender;
        this.profilePicture = profilePicture;
        this.email = email;
        this.bio = bio;
        this.birthDate = birthDate;
        this.country = country;
        this.currentStatus = currentStatus;
        this.contactsList = contactsList;
        this.groupChatList = groupChatList;
        this.invitationsList = invitationsList;

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

    public String getGender() {
        return gender;
    }

    public void setGender( String gender ) {
        this.gender = gender;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture( String profilePicture ) {
        this.profilePicture = profilePicture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio( String bio ) {
        this.bio = bio;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate( LocalDate birthDate ) {
        this.birthDate = birthDate;
    }

    public CountryDto getCountry() {
        return country;
    }

    public void setCountry( CountryDto country ) {
        this.country = country;
    }

    public UserStatusDto getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus( UserStatusDto currentStatus ) {
        this.currentStatus = currentStatus;
    }

    public List<ContactDto> getContactsList() {
        return contactsList;
    }

    public void setContactsList( List<ContactDto> contactsList ) {
        this.contactsList = contactsList;
    }

    public List<GroupChatDto> getGroupChatList() {
        return groupChatList;
    }

    public void setGroupChatList( List<GroupChatDto> groupChatList ) {
        this.groupChatList = groupChatList;
    }

    public List<InvitationDto> getInvitationsList() {
        return invitationsList;
    }

    public void setInvitationsList( List<InvitationDto> invitationsList ) {
        this.invitationsList = invitationsList;
    }


    @Override
    public String toString() {
        return "UserDto{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", displayName='" + displayName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", country=" + country +
                ", currentStatus=" + currentStatus +
                ", profilePicture='" + profilePicture + '\'' +
                ", bio='" + bio + '\'' +
                ", contactsList=" + contactsList +
                ", groupChatList=" + groupChatList +
                ", invitationsList=" + invitationsList +
                '}';
    }
}
