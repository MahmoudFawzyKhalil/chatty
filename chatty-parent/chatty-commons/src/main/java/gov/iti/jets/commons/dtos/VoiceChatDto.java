package gov.iti.jets.commons.dtos;

import gov.iti.jets.commons.util.ValidationUtil;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class VoiceChatDto implements Serializable {
    @NotNull
    @Size(min = 11, max = 11)
    private String callerPhoneNumber;
    @NotNull
    @Size(min = 11, max = 11)
    private String receiverPhoneNumber;
    @NotNull
    private String callerIp;

    public VoiceChatDto(String callerPhoneNumber, String receiverPhoneNumber, String callerIp) {
        this.callerPhoneNumber = callerPhoneNumber;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.callerIp = callerIp;
        ValidationUtil.getInstance().validate( this );
    }

    public String getCallerPhoneNumber() {
        return callerPhoneNumber;
    }

    public void setCallerPhoneNumber(String callerPhoneNumber) {
        this.callerPhoneNumber = callerPhoneNumber;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public String getCallerIp() {
        return callerIp;
    }

    public void setCallerIp(String callerIp) {
        this.callerIp = callerIp;
    }
}
