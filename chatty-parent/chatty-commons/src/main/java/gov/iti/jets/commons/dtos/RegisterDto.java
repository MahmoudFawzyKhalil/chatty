package gov.iti.jets.commons.dtos;

import gov.iti.jets.commons.util.ValidationUtil;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

public class RegisterDto implements Serializable {
    //phone number ,name, password, email , birthdate, country, gender, bio ,image
    @NotNull
    @Size(min = 11, max = 11)
    private String phoneNumber;
    @NotNull
    @Size(min = 3, max = 20)
    private String displayName;
    @NotNull
    @Size(min = 8, max = 20)
    private String password;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Past
    private LocalDate birthDate;
    @NotNull
    //ask
    private int countryId;
    @NotNull
    private String gender;
    @Size(min = 0, max = 100)
    private String bio;
    //ask
    private String profilePicture;

    public RegisterDto(String phoneNumber, String displayName, String password, String email, LocalDate birthDate, int countryId, String gender, String bio, String profilePicture) {
        this.phoneNumber = phoneNumber;
        this.displayName = displayName;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.countryId = countryId;
        this.gender = gender;
        this.bio = bio;
        this.profilePicture = profilePicture;
        ValidationUtil.getInstance().validate(this);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
