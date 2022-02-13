package gov.iti.jets.presentation.models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.time.LocalDate;

public class UserModel {


    private IntegerProperty userId = new SimpleIntegerProperty();
    private StringProperty phoneNumber = new SimpleStringProperty();
    private StringProperty displayName = new SimpleStringProperty();
    private StringProperty gender = new SimpleStringProperty();
    private ObjectProperty<Image> profilePicture = new SimpleObjectProperty<>();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty bio = new SimpleStringProperty();
    private ObjectProperty<LocalDate> birthDate = new SimpleObjectProperty<>();
    private ObjectProperty<CountryModel> country = new SimpleObjectProperty<>();
    private ObjectProperty<UserStatusModel> currentStatus = new SimpleObjectProperty<>();
    private ListProperty<ContactModel> contacts = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ListProperty<GroupChatModel> groupChats = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ListProperty<InvitationModel> invitations = new SimpleListProperty<>(FXCollections.observableArrayList());

    public  UserModel(){

    }

    public UserModel(String phoneNumber, String displayName, String gender, String email,
                     String bio, LocalDate birthDate, CountryModel country, UserStatusModel currentStatus) {

        this.phoneNumber.set(phoneNumber);
        this.displayName.set(displayName);
        this.gender.set(gender);
        this.email.set(email);
        this.bio.set(bio);
        this.birthDate.set(birthDate);
        this.country.set(country);
        this.currentStatus.set(currentStatus);
    }

    public int getUserId() {
        return userId.get();
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getDisplayName() {
        return displayName.get();
    }

    public StringProperty displayNameProperty() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName.set(displayName);
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public Image getProfilePicture() {
        return profilePicture.get();
    }

    public ObjectProperty<Image> profilePictureProperty() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture.set(profilePicture);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getBio() {
        return bio.get();
    }

    public StringProperty bioProperty() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio.set(bio);
    }

    public LocalDate getBirthDate() {
        return birthDate.get();
    }

    public ObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate.set(birthDate);
    }

    public CountryModel getCountry() {
        return country.get();
    }

    public ObjectProperty<CountryModel> countryProperty() {
        return country;
    }

    public void setCountry(CountryModel country) {
        this.country.set(country);
    }

    public UserStatusModel getCurrentStatus() {
        return currentStatus.get();
    }

    public ObjectProperty<UserStatusModel> currentStatusProperty() {
        return currentStatus;
    }

    public void setCurrentStatus(UserStatusModel currentStatus) {
        this.currentStatus.set(currentStatus);
    }

    public ObservableList<ContactModel> getContacts() {
        return contacts.get();
    }

    public ListProperty<ContactModel> contactsProperty() {
        return contacts;
    }

    public void setContacts(ObservableList<ContactModel> contacts) {
        this.contacts.set(contacts);
    }

    public ObservableList<GroupChatModel> getGroupChats() {
        return groupChats.get();
    }

    public ListProperty<GroupChatModel> groupChatsProperty() {
        return groupChats;
    }

    public void setGroupChats(ObservableList<GroupChatModel> groupChats) {
        this.groupChats.set(groupChats);
    }

    public ObservableList<InvitationModel> getInvitations() {
        return invitations.get();
    }

    public ListProperty<InvitationModel> invitationsProperty() {
        return invitations;
    }

    public void setInvitations(ObservableList<InvitationModel> invitations) {
        this.invitations.set(invitations);
    }
}
