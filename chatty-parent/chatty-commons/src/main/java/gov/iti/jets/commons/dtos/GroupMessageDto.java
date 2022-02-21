package gov.iti.jets.commons.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;

public class GroupMessageDto implements Serializable {

    @NotNull
    private Integer groupChatId;

    @NotNull
    @Size(max = 11, min = 11)
    private String senderPhoneNumber;

    @NotNull
    private String messageBody;

    @NotNull
    private LocalDateTime timeStamp;

    @NotNull
    private String cssTextStyleString;

    @NotNull
    private String cssBubbleStyleString;

    public GroupMessageDto() {
    }

    public GroupMessageDto(Integer groupChatId, String senderPhoneNumber, String messageBody, LocalDateTime timeStamp, String cssTextStyleString, String cssBubbleStyleString) {
        this.groupChatId = groupChatId;
        this.senderPhoneNumber = senderPhoneNumber;
        this.messageBody = messageBody;
        this.timeStamp = timeStamp;
        this.cssTextStyleString = cssTextStyleString;
        this.cssBubbleStyleString = cssBubbleStyleString;
    }

    public Integer getGroupChatId() {
        return groupChatId;
    }

    public void setGroupChatId(Integer groupChatId) {
        this.groupChatId = groupChatId;
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
