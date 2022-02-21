package gov.iti.jets.commons.dtos;

import java.io.Serializable;

public class UpdateProfilePicDto implements Serializable {
    private String phoneNumber;
    private String pictureBase46;

    public UpdateProfilePicDto(String phoneNumber, String pictureBase46) {
        this.phoneNumber = phoneNumber;
        this.pictureBase46 = pictureBase46;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPictureBase46() {
        return pictureBase46;
    }

    public void setPictureBase46(String pictureBase46) {
        this.pictureBase46 = pictureBase46;
    }
}
