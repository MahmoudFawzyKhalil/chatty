package gov.iti.jets.presentation.models;

import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.util.Callback;

public class ContactModel {
    /*
    ContactModel
    --
    -contactId: IntegerProperty
    -phoneNumber: StringProperty
    -displayName: StringProperty
    -profilePicture: ObjectProperty<Image>
    -currentStatus: ObjectProperty<UserStatusModel>

     */

    private IntegerProperty contactId = new SimpleIntegerProperty();
    private StringProperty phoneNumber = new SimpleStringProperty();
    private StringProperty displayName = new SimpleStringProperty();
    private ObjectProperty<Image> profilePicture = new SimpleObjectProperty<>();
    private ObjectProperty<UserStatusModel> currentStatus = new SimpleObjectProperty<>();

    public ContactModel(){

    }

    public ContactModel(int contactId, String phoneNumber, String displayName, Image profilePicture, UserStatusModel currentStatus) {
        this.contactId.set(contactId);
        this.phoneNumber.set(phoneNumber);
        this.displayName.set(displayName);
        this.profilePicture.set(profilePicture);
        this.currentStatus.set(currentStatus);
    }

    public static Callback<ContactModel, Observable[]> extractor() {
        return new Callback<ContactModel, Observable[]>() {
            @Override
            public Observable[] call(ContactModel param) {
                return new Observable[]{param.phoneNumber,param.displayName,param.profilePicture,param.currentStatus};
            }
        };
    }

    public int getContactId() {
        return contactId.get();
    }

    public IntegerProperty contactIdProperty() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId.set(contactId);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getDisplayName() {
        return displayName.get();
    }

    public StringProperty displayNameProperty() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName.set(displayName);
    }

    public Image getProfilePicture() {
        return profilePicture.get();
    }

    public ObjectProperty<Image> profilePictureProperty() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture.set(profilePicture);
    }

    public UserStatusModel getCurrentStatus() {
        return currentStatus.get();
    }

    public ObjectProperty<UserStatusModel> currentStatusProperty() {
        return currentStatus;
    }

    public void setCurrentStatus(UserStatusModel currentStatus) {
        this.currentStatus.set(currentStatus);
    }
}
