package gov.iti.jets.repository.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserEntity {
    private String phoneNumber;
    private String password;
    private String displayName;
    private String gender;
    private String email;
    private String bio;
    private CountryEntity country;
    private String userPicture;
    private LocalDate birthDate;
    private UserStatusEntity currentStatus;
    private List<InvitationEntity> invitationsList = new ArrayList<>();
    private List<ContactEntity> contactsList = new ArrayList<>();
    private List<GroupChatEntity> groupChatList = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(String phoneNumber, String password, String displayName, String gender, String email, String bio, CountryEntity country, String userPicture, LocalDate birthDate, UserStatusEntity currentStatus, List<InvitationEntity> invitationsList, List<ContactEntity> contactsList, List<GroupChatEntity> groupChatList) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.displayName = displayName;
        this.gender = gender;
        this.email = email;
        this.bio = bio;
        this.country = country;
        this.userPicture = userPicture;
        this.birthDate = birthDate;
        this.currentStatus = currentStatus;
        this.invitationsList = invitationsList;
        this.contactsList = contactsList;
        this.groupChatList = groupChatList;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public UserStatusEntity getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(UserStatusEntity currentStatus) {
        this.currentStatus = currentStatus;
    }

    public List<InvitationEntity> getInvitationsList() {
        return invitationsList;
    }

    public void setInvitationsList(List<InvitationEntity> invitationsList) {
        this.invitationsList = invitationsList;
    }

    public List<ContactEntity> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<ContactEntity> contactsList) {
        this.contactsList = contactsList;
    }

    public List<GroupChatEntity> getGroupChatList() {
        return groupChatList;
    }

    public void setGroupChatList(List<GroupChatEntity> groupChatList) {
        this.groupChatList = groupChatList;
    }
}

