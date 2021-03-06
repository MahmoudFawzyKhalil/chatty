package gov.iti.jets.presentation.models;

import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.util.Callback;

public class ContactModel {
    private StringProperty phoneNumber = new SimpleStringProperty();
    private StringProperty displayName = new SimpleStringProperty();
    private BooleanProperty selected = new SimpleBooleanProperty( false );
    private ObjectProperty<Image> profilePicture = new SimpleObjectProperty<>();
    private ObjectProperty<UserStatusModel> currentStatus = new SimpleObjectProperty<>();
    private ListProperty<MessageModel> messsages = new SimpleListProperty<>( FXCollections.observableArrayList( MessageModel.extractor() ) );

    public ContactModel() {
        profilePicture.set( UserModel.DEFAULT_IMAGE );
    }

    public ContactModel( String phoneNumber, String displayName, UserStatusModel currentStatus ) {
        this();
        this.phoneNumber.set( phoneNumber );
        this.displayName.set( displayName );
        this.currentStatus.set( currentStatus );
    }

    public static Callback<ContactModel, Observable[]> extractor() {
        return new Callback<ContactModel, Observable[]>() {
            @Override
            public Observable[] call( ContactModel param ) {
                return new Observable[]{param.phoneNumber, param.displayName, param.profilePicture, param.currentStatus};
            }
        };
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber( String phoneNumber ) {
        this.phoneNumber.set( phoneNumber );
    }

    public String getDisplayName() {
        return displayName.get();
    }

    public StringProperty displayNameProperty() {
        return displayName;
    }

    public void setDisplayName( String displayName ) {
        this.displayName.set( displayName );
    }

    public Image getProfilePicture() {
        return profilePicture.get();
    }

    public ObjectProperty<Image> profilePictureProperty() {
        return profilePicture;
    }

    public void setProfilePicture( Image profilePicture ) {
        this.profilePicture.set( profilePicture );
    }

    public UserStatusModel getCurrentStatus() {
        return currentStatus.get();
    }

    public ObjectProperty<UserStatusModel> currentStatusProperty() {
        return currentStatus;
    }

    public void setCurrentStatus( UserStatusModel currentStatus ) {
        this.currentStatus.set( currentStatus );
    }

    public ObservableList<MessageModel> getMesssages() {
        return messsages.get();
    }

    public ListProperty<MessageModel> messsagesProperty() {
        return messsages;
    }

    public void setMesssages( ObservableList<MessageModel> messsages ) {
        this.messsages.set( messsages );
    }

    public boolean isSelected() {
        return selected.get();
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected( boolean selected ) {
        this.selected.set( selected );
    }

    @Override
    public String toString() {
        return "ContactModel{" +
                "phoneNumber=" + phoneNumber +
                ", displayName=" + displayName +
                ", selected=" + selected +
                ", profilePicture=" + profilePicture +
                ", currentStatus=" + currentStatus +
                ", messsages=" + messsages +
                '}';
    }
}
