package gov.iti.jets.commons.dtos;

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

    @NotNull
    @Size(min=3,max=20)
    private String senderName;

    private LocalDateTime timeStamp;

    private String cssTextStyleString;

    private String cssBubbleStyleString;

    public SingleMessageDto() {
    }

    public SingleMessageDto(String receiverPhoneNumber, String senderPhoneNumber, String messageBody, String senderName, LocalDateTime timeStamp, String cssTextStyleString, String cssBubbleStyleString) {
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.senderPhoneNumber = senderPhoneNumber;
        this.messageBody = messageBody;
        this.senderName = senderName;
        this.timeStamp = timeStamp;
        this.cssTextStyleString = cssTextStyleString;
        this.cssBubbleStyleString = cssBubbleStyleString;
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

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
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
