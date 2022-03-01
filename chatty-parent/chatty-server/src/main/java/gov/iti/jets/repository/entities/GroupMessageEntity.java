package gov.iti.jets.repository.entities;

import java.time.LocalDateTime;

public class GroupMessageEntity {

    private int groupChatId;
    private String senderPhoneNumber;
    private String messageBody;
    private LocalDateTime timeStamp;
    private String cssTextStyleString;
    private String cssBubbleStyleString;

    public GroupMessageEntity() {
    }

    public GroupMessageEntity(int groupChatId, String senderPhoneNumber, String messageBody, LocalDateTime timeStamp, String cssTextStyleString, String cssBubbleStyleString) {
        this.groupChatId = groupChatId;
        this.senderPhoneNumber = senderPhoneNumber;
        this.messageBody = messageBody;
        this.timeStamp = timeStamp;
        this.cssTextStyleString = cssTextStyleString;
        this.cssBubbleStyleString = cssBubbleStyleString;
    }

    public int getGroupChatId() {
        return groupChatId;
    }

    public void setGroupChatId(int groupChatId) {
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