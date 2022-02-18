package gov.iti.jets.commons.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

public class UpdateProfileDto implements Serializable {
    @NotNull
    @Size(min = 11, max = 11)
    private String phoneNumber;
    @NotNull
    @Size(min = 3, max = 20)
    private String displayName;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Past
    private LocalDate birthDate;
    @Size(min = 0, max = 100)
    private String bio;
    private String profilePicture;

    public UpdateProfileDto(String phoneNumber, String displayName, String email, LocalDate birthDate, String bio, String profilePicture) {
        this.phoneNumber = phoneNumber;
        this.displayName = displayName;
        this.email = email;
        this.birthDate = birthDate;
        this.bio = bio;
        this.profilePicture = profilePicture;
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