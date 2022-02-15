package gov.iti.jets.commons.dtos;

import gov.iti.jets.commons.util.ValidationUtil;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ContactDto {

    @NotNull
    @Size(min = 11, max = 11)
    private String phoneNumber;

    @NotNull
    private String displayName;

    public ContactDto( String phoneNumber, String displayName ) {
        this.phoneNumber = phoneNumber;
        this.displayName = displayName;

        ValidationUtil.getInstance().validate( this );
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber( String phoneNumber ) {
        this.phoneNumber = phoneNumber;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName( String displayName ) {
        this.displayName = displayName;
    }
}
