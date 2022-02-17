package gov.iti.jets.commons.dtos;

import gov.iti.jets.commons.util.ValidationUtil;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

public class ContactDto implements Serializable {

    @NotNull
    @Size(min = 11, max = 11)
    private String phoneNumber;

    private List<@NotNull @Pattern(regexp = "[0-9]{11}") String> phoneNumbers;

    public ContactDto(String phoneNumber,List<String> phoneNumbers) {
        this.phoneNumber = phoneNumber;
        this.phoneNumber = phoneNumber;

        ValidationUtil.getInstance().validate(this);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
