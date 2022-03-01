package gov.iti.jets.presentation.models;

import javafx.beans.property.*;
import javafx.scene.image.Image;

public class VoiceChatModel {
    private StringProperty callerPhoneNumber = new SimpleStringProperty();
    private StringProperty callerName = new SimpleStringProperty();
    private ObjectProperty<Image> callerProfilePic = new SimpleObjectProperty<>();
    private BooleanProperty inCall=new SimpleBooleanProperty(false);
    private String contactIp = "";

    public VoiceChatModel(StringProperty callerPhoneNumber, StringProperty callerName, ObjectProperty<Image> callerProfilePic, String contactIp) {
        this.callerPhoneNumber = callerPhoneNumber;
        this.callerName = callerName;
        this.callerProfilePic = callerProfilePic;
        this.contactIp = contactIp;
    }

    public VoiceChatModel(ContactModel contactModel, String contactIp) {
        this.callerPhoneNumber = contactModel.phoneNumberProperty();
        this.callerName = contactModel.displayNameProperty();
        this.callerProfilePic = contactModel.profilePictureProperty();
        this.contactIp = contactIp;
    }

    public VoiceChatModel() {
    }

    public void setAll(ContactModel contactModel, String contactIp) {
        this.callerPhoneNumber = contactModel.phoneNumberProperty();
        this.callerName = contactModel.displayNameProperty();
        this.callerProfilePic = contactModel.profilePictureProperty();
        this.contactIp = contactIp;
    }

    public void setContactModel(ContactModel contactModel) {
        this.callerPhoneNumber = contactModel.phoneNumberProperty();
        this.callerName = contactModel.displayNameProperty();
        this.callerProfilePic = contactModel.profilePictureProperty();
    }

    public String getCallerPhoneNumber() {
        return callerPhoneNumber.get();
    }

    public StringProperty callerPhoneNumberProperty() {
        return callerPhoneNumber;
    }

    public void setCallerPhoneNumber(String callerPhoneNumber) {
        this.callerPhoneNumber.set(callerPhoneNumber);
    }

    public String getCallerName() {
        return callerName.get();
    }

    public StringProperty callerNameProperty() {
        return callerName;
    }

    public void setCallerName(String callerName) {
        this.callerName.set(callerName);
    }

    public Image getCallerProfilePic() {
        return callerProfilePic.get();
    }

    public ObjectProperty<Image> callerProfilePicProperty() {
        return callerProfilePic;
    }

    public void setCallerProfilePic(Image callerProfilePic) {
        this.callerProfilePic.set(callerProfilePic);
    }

    public String getContactIp() {
        return contactIp;
    }

    public void setContactIp(String contactIp) {
        this.contactIp = contactIp;
    }

    public boolean isInCall() {
        return inCall.get();
    }

    public BooleanProperty inCallProperty() {
        return inCall;
    }

    public void setInCall(boolean inCall) {
        this.inCall.set(inCall);
    }
}
