package gov.iti.jets.presentation.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class MessageModel {
    private StringProperty senderName = new SimpleStringProperty();
    private ObjectProperty<Image> senderProfilePicture = new SimpleObjectProperty<>();
    private String messageBody;
    private String cssTextStyleString;
    private String cssBubbleStyleString;

    public MessageModel(String senderName, Image senderProfilePicture, String messageBody, String cssTextStyleString, String cssBubbleStyleString) {
        this.senderName.set(senderName);
        this.senderProfilePicture.set(senderProfilePicture);
        this.messageBody = messageBody;
        this.cssTextStyleString = cssTextStyleString;
        this.cssBubbleStyleString = cssBubbleStyleString;
    }


    public String getSenderName() {
        return senderName.get();
    }

    public StringProperty senderNameProperty() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName.set(senderName);
    }

    public Image getSenderProfilePicture() {
        return senderProfilePicture.get();
    }

    public ObjectProperty<Image> senderProfilePictureProperty() {
        return senderProfilePicture;
    }

    public void setSenderProfilePicture(Image senderProfilePicture) {
        this.senderProfilePicture.set(senderProfilePicture);
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getCssBubbleStyleString() {
        return cssBubbleStyleString;
    }

    public void setCssBubbleStyleString(String cssBubbleStyleString) {
        this.cssBubbleStyleString = cssBubbleStyleString;
    }

    public String getCssTextStyleString() {
        return cssTextStyleString;
    }

    public void setCssTextStyleString(String cssTextStyleString) {
        this.cssTextStyleString = cssTextStyleString;
    }
}