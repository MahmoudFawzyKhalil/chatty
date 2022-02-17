package gov.iti.jets.commons.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class UserDto implements Serializable {
    /* ListProperty<ContactModel> contacts = new SimpleListProperty<>( FXCollections.observableArrayList( ContactModel.extractor() ) );
    private ListProperty<GroupChatModel> groupChats = new SimpleListProperty<>( FXCollections.observableArrayList( GroupChatModel.extractor() ) );
    private ListProperty<InvitationModel> invitations = new SimpleListProperty<>( FXCollections.observableArrayList( InvitationModel.extractor() ) );
    private final StringProperty currentlyChattingWith = new SimpleStringProperty();
     */
    @NotNull
    @Size(min = 11, max = 11)
    private String phoneNumber;

    @NotNull
    @Size(min=3,max=20)
    private String displayName;

    @NotNull
    private String gender;
    //TODO
    //may be null
    private String profilePicture;

    @Email
    private String email;

    private String bio;

    @Past
    private LocalDate birthDate;
    @NotNull
    private String country;
    //TODO
    //may the user doesn't have any contatct or groups
    private List<ContactDto> contactsList;
    private List<GroupChatDto> groupChatList;

    public UserDto(String phoneNumber, String displayName, String gender, String profilePicture, String email, String bio, LocalDate birthDate, String country, List<ContactDto> contactsList, List<GroupChatDto> groupChatList) {
        this.phoneNumber = phoneNumber;
        this.displayName = displayName;
        this.gender = gender;
        this.profilePicture = profilePicture;
        this.email = email;
        this.bio = bio;
        this.birthDate = birthDate;
        this.country = country;
        this.contactsList = contactsList;
        this.groupChatList = groupChatList;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<ContactDto> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<ContactDto> contactsList) {
        this.contactsList = contactsList;
    }

    public List<GroupChatDto> getGroupChatList() {
        return groupChatList;
    }

    public void setGroupChatList(List<GroupChatDto> groupChatList) {
        this.groupChatList = groupChatList;
    }
}
