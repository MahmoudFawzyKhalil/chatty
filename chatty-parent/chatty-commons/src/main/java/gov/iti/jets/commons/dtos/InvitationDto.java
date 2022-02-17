package gov.iti.jets.commons.dtos;

import gov.iti.jets.commons.util.ValidationUtil;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class InvitationDto implements Serializable {

    @NotNull
    private ContactDto contactDto;

    public InvitationDto( ContactDto contactDto ) {
        this.contactDto = contactDto;

        ValidationUtil.getInstance().validate( this );
    }

    public ContactDto getContactDto() {
        return contactDto;
    }

    public void setContactDto( ContactDto contactDto ) {
        this.contactDto = contactDto;
    }
}
