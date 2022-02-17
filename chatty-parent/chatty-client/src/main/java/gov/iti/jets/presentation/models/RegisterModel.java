package gov.iti.jets.presentation.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.time.LocalDate;

public class RegisterModel {
    public static final CountryModel DEFAULT_COUNTRY_MODEL = new CountryModel(0, "Choose Country");
    private final Image defaultImage = new Image(getClass().getResource("/images/user.png").toString());
    private StringProperty phoneNumber = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private StringProperty confirmPassword = new SimpleStringProperty();
    private StringProperty displayName = new SimpleStringProperty();
    private StringProperty gender = new SimpleStringProperty("Male");
    private ObjectProperty<Image> profilePicture = new SimpleObjectProperty<>(defaultImage);
    private StringProperty email = new SimpleStringProperty();
    private StringProperty bio = new SimpleStringProperty();
    private ObjectProperty<LocalDate> birthDate = new SimpleObjectProperty<>();
    private ObjectProperty<CountryModel> country = new SimpleObjectProperty<>();

    public RegisterModel() {
        clear();
    }

    public RegisterModel(StringProperty phoneNumber, StringProperty password, StringProperty displayName, StringProperty gender, ObjectProperty<Image> profilePicture, StringProperty email, StringProperty bio, ObjectProperty<LocalDate> birthDate, ObjectProperty<CountryModel> country) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.displayName = displayName;
        this.gender = gender;
        this.profilePicture = profilePicture;
        this.email = email;
        this.bio = bio;
        this.birthDate = birthDate;
        this.country = country;
    }

    public void clear() {
        gender.set("Male");
        country.set(DEFAULT_COUNTRY_MODEL);
        phoneNumber.set("");
        password.set("");
        confirmPassword.set("");
        displayName.set("");
        gender.set("");
        profilePicture.set(defaultImage);
        email.set("");
        bio.set("");
    }

    public String getConfirmPassword() {
        return confirmPassword.get();
    }

    public StringProperty confirmPasswordProperty() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword.set(confirmPassword);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
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
}
