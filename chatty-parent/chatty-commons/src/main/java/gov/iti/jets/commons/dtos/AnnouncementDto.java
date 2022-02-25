package gov.iti.jets.commons.dtos;

import gov.iti.jets.commons.util.ValidationUtil;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AnnouncementDto implements Serializable {

    @NotNull
    private String adminName = "Admin";

    @NotNull
    private String messageBody;

    @NotNull
    private LocalDateTime timeStamp;

    @NotNull
    private String cssTextStyleString;

    @NotNull
    private String cssBubbleStyleString;

    public AnnouncementDto(String messageBody, LocalDateTime timeStamp, String cssTextStyleString, String cssBubbleStyleString ) {
        this.messageBody = messageBody;
        this.timeStamp = timeStamp;
        this.cssTextStyleString = cssTextStyleString;
        this.cssBubbleStyleString = cssBubbleStyleString;

        ValidationUtil.getInstance().validate( this );
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName( String adminName ) {
        this.adminName = adminName;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody( String messageBody ) {
        this.messageBody = messageBody;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp( LocalDateTime timeStamp ) {
        this.timeStamp = timeStamp;
    }

    public String getCssTextStyleString() {
        return cssTextStyleString;
    }

    public void setCssTextStyleString( String cssTextStyleString ) {
        this.cssTextStyleString = cssTextStyleString;
    }

    public String getCssBubbleStyleString() {
        return cssBubbleStyleString;
    }

    public void setCssBubbleStyleString( String cssBubbleStyleString ) {
        this.cssBubbleStyleString = cssBubbleStyleString;
    }
}
