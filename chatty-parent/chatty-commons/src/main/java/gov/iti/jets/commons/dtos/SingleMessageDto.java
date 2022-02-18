package gov.iti.jets.commons.dtos;

import gov.iti.jets.commons.util.ValidationUtil;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SingleMessageDto implements Serializable {

    @NotNull
    @Size(max = 11, min = 11)
    private String receiverPhoneNumber;

    @NotNull
    @Size(max = 11, min = 11)
    private String senderPhoneNumber;

    @NotNull
    private String messageBody;

    private LocalDateTime timeStamp;

    private String cssTextStyleString;

    private String cssBubbleStyleString;

    public SingleMessageDto() {
    }

    public SingleMessageDto(String receiverPhoneNumber, String senderPhoneNumber, String messageBody, LocalDateTime timeStamp, String cssTextStyleString, String cssBubbleStyleString) {
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.senderPhoneNumber = senderPhoneNumber;
        this.messageBody = messageBody;
        this.timeStamp = timeStamp;
        this.cssTextStyleString = cssTextStyleString;
        this.cssBubbleStyleString = cssBubbleStyleString;
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

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getCssTextStyleString() {
        return cssTextStyleString;
    }

    public void setCssTextStyleString(String cssTextStyleString) {
        this.cssTextStyleString = cssTextStyleString;
    }

    public String getCssBubbleStyleString() {
        return cssBubbleStyleString;
    }

    public void setCssBubbleStyleString(String cssBubbleStyleString) {
        this.cssBubbleStyleString = cssBubbleStyleString;
    }
}
