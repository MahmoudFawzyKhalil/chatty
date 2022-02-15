package gov.iti.jets.commons.dtos;

import gov.iti.jets.commons.util.ValidationUtil;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginDto {

    private final ValidationUtil validationUtil = ValidationUtil.getInstance();
    @NotNull
    @Size(min = 11, max = 11)
    String phoneNumber;

    @NotNull
    @Size(min = 8, max = 20)
    String password;

    public LoginDto( String phoneNumber, String password ) {
        this.phoneNumber = phoneNumber;
        this.password = password;

        validationUtil.validate( this );
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber( String phoneNumber ) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }
}
