package gov.iti.jets.presentation.models;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.util.Callback;

import java.time.LocalDateTime;

public class MessageModel {
    private StringProperty senderName = new SimpleStringProperty();
    private ObjectProperty<Image> senderProfilePicture = new SimpleObjectProperty<>( new Image( getClass().getResource( "/images/user.png" ).toString() ) );
    private LocalDateTime timeStamp;
    private String messageBody;
    private String cssTextStyleString;
    private String cssBubbleStyleString;
    private boolean isSentByMe;

    public MessageModel( String senderName, LocalDateTime timeStamp, String messageBody, String cssTextStyleString, String cssBubbleStyleString, boolean isSentByMe ) {
        this.senderName.set( senderName );
        this.timeStamp = timeStamp;
        this.messageBody = messageBody;
        this.isSentByMe = isSentByMe;
        this.cssTextStyleString = cssTextStyleString;
        this.cssBubbleStyleString = cssBubbleStyleString;
    }

    public static Callback<MessageModel, Observable[]> extractor() {
        return new Callback<MessageModel, Observable[]>() {
            @Override
            public Observable[] call( MessageModel param ) {
                return new Observable[]{param.senderName, param.senderProfilePicture};
            }
        };
    }

    public boolean isSentByMe() {
        return isSentByMe;
    }

    public void setSentByMe( boolean sentByMe ) {
        isSentByMe = sentByMe;
    }

    public String getSenderName() {
        return senderName.get();
    }

    public StringProperty senderNameProperty() {
        return senderName;
    }

    public void setSenderName( String senderName ) {
        this.senderName.set( senderName );
    }

    public Image getSenderProfilePicture() {
        return senderProfilePicture.get();
    }

    public ObjectProperty<Image> senderProfilePictureProperty() {
        return senderProfilePicture;
    }

    public void setSenderProfilePicture( Image senderProfilePicture ) {
        this.senderProfilePicture.set( senderProfilePicture );
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody( String messageBody ) {
        this.messageBody = messageBody;
    }

    public String getCssBubbleStyleString() {
        return cssBubbleStyleString;
    }

    public void setCssBubbleStyleString( String cssBubbleStyleString ) {
        this.cssBubbleStyleString = cssBubbleStyleString;
    }

    public String getCssTextStyleString() {
        return cssTextStyleString;
    }

    public void setCssTextStyleString( String cssTextStyleString ) {
        this.cssTextStyleString = cssTextStyleString;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp( LocalDateTime timeStamp ) {
        this.timeStamp = timeStamp;
    }
}