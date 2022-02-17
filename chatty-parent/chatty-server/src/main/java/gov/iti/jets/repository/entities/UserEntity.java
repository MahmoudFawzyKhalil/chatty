package gov.iti.jets.repository.entities;

import java.time.LocalDate;

public class UserEntity {
    private String phoneNumber;
    private String password;
    private String displayName;
    private String gender;
    private String email;
    private String bio;
    private int countryId;
    private String userPicture;
    private LocalDate birthDate;
    private int userStatusId;

    public UserEntity() {
    }

    public UserEntity(String phoneNumber, String password, String displayName, String gender, String email, String bio, int countryId, String userPicture, LocalDate birthDate, int userStatusId) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.displayName = displayName;
        this.gender = gender;
        this.email = email;
        this.bio = bio;
        this.countryId = countryId;
        this.userPicture = userPicture;
        this.birthDate = birthDate;
        this.userStatusId = userStatusId;
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

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
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

    public int getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(int userStatusId) {
        this.userStatusId = userStatusId;
    }
}
