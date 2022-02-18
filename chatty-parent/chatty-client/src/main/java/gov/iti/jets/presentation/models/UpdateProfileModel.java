package gov.iti.jets.presentation.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.time.LocalDate;

public class UpdateProfileModel {
    private StringProperty displayName = new SimpleStringProperty();
    private ObjectProperty<Image> profilePicture = new SimpleObjectProperty<>();
    private StringProperty phoneNumber;
    private StringProperty bio = new SimpleStringProperty();
    private ObjectProperty<LocalDate> birthDate = new SimpleObjectProperty<>();

    private UserModel userModel;

    public UpdateProfileModel(UserModel userModel) {
        this.userModel = userModel;
        this.phoneNumber = userModel.phoneNumberProperty();
        resetData();
    }


    public UpdateProfileModel(StringProperty displayName, ObjectProperty<Image> profilePicture, StringProperty phoneNumber, StringProperty bio, ObjectProperty<LocalDate> birthDate, UserModel userModel) {
        this.displayName = displayName;
        this.profilePicture = profilePicture;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
        this.birthDate = birthDate;
        this.userModel = userModel;
    }

    private void resetData() {
        displayName.set(userModel.getDisplayName());
        birthDate.set(userModel.getBirthDate());
        bio.set(userModel.getBio());
        profilePicture.set(userModel.getProfilePicture());
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
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

    public Image getProfilePicture() {
        return profilePicture.get();
    }

    public ObjectProperty<Image> profilePictureProperty() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture.set(profilePicture);
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
}
