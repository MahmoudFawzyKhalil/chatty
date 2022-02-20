package gov.iti.jets.commons.dtos;

import gov.iti.jets.commons.enums.StatusNotificationType;
import gov.iti.jets.commons.util.ValidationUtil;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class StatusNotificationDto implements Serializable {

    @NotNull
    private String contactWhoseStatusHasChangedPhoneNumber;

    @NotNull
    private StatusNotificationType newStatus;

    public StatusNotificationDto( String contactWhoseStatusHasChangedPhoneNumber,
                                  StatusNotificationType newStatus) {
        this.contactWhoseStatusHasChangedPhoneNumber = contactWhoseStatusHasChangedPhoneNumber;
        this.newStatus = newStatus;

        ValidationUtil.getInstance().validate( this );
    }

    public String getContactWhoseStatusHasChangedPhoneNumber() {
        return contactWhoseStatusHasChangedPhoneNumber;
    }

    public void setContactWhoseStatusHasChangedPhoneNumber( String contactWhoseStatusHasChangedPhoneNumber ) {
        this.contactWhoseStatusHasChangedPhoneNumber = contactWhoseStatusHasChangedPhoneNumber;
    }

    public StatusNotificationType getNewStatus() {
        return newStatus;
    }

    public void setNewStatus( StatusNotificationType newStatus ) {
        this.newStatus = newStatus;
    }
}
