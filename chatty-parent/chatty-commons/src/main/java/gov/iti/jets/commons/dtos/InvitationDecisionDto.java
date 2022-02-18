package gov.iti.jets.commons.dtos;

import gov.iti.jets.commons.util.ValidationUtil;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class InvitationDecisionDto implements Serializable {

    @NotNull
    private String receiverPhoneNumber;

    @NotNull
    private String senderPhoneNumber;

    public InvitationDecisionDto(String receiverPhoneNumber, String senderPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.senderPhoneNumber = senderPhoneNumber;

        ValidationUtil.getInstance().validate(this);
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public String getSenderPhoneNumber() {
        return senderPhoneNumber;
    }

    public void setSenderPhoneNumber(String senderPhoneNumber) {
        this.senderPhoneNumber = senderPhoneNumber;
    }
}
