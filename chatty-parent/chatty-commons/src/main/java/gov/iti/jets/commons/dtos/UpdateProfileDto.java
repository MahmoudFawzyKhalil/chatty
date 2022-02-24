package gov.iti.jets.commons.dtos;

import gov.iti.jets.commons.util.ValidationUtil;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class UpdateProfileDto implements Serializable {
    @NotNull
    @Size(min = 11, max = 11)
    private String phoneNumber;
    @NotNull
    @Size(min = 3, max = 20)
    private String displayName;

    @Size(min = 0, max = 100)
    private String bio;

    public UpdateProfileDto(String phoneNumber, String displayName, String bio) {
        this.phoneNumber = phoneNumber;
        this.displayName = displayName;
        this.bio = bio;

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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}