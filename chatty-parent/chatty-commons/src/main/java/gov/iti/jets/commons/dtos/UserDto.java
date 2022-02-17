package gov.iti.jets.commons.dtos;

import java.io.Serializable;
import java.time.LocalDate;

public class UserDto implements Serializable {
    /*
     private StringProperty phoneNumber = new SimpleStringProperty();
    private StringProperty displayName = new SimpleStringProperty();


    private StringProperty gender = new SimpleStringProperty();
    private ObjectProperty<Image> profilePicture = new SimpleObjectProperty<>( new Image( getClass().getResource( "/images/user.png" ).toString() ) );
    private StringProperty email = new SimpleStringProperty();
    private StringProperty bio = new SimpleStringProperty();
    private ObjectProperty<LocalDate> birthDate = new SimpleObjectProperty<>();
    private ObjectProperty<CountryModel> country = new SimpleObjectProperty<>();
    private ObjectProperty<UserStatusModel> currentStatus = new SimpleObjectProperty<>();
    private ListProperty<ContactModel> contacts = new SimpleListProperty<>( FXCollections.observableArrayList( ContactModel.extractor() ) );
    private ListProperty<GroupChatModel> groupChats = new SimpleListProperty<>( FXCollections.observableArrayList( GroupChatModel.extractor() ) );
    private ListProperty<InvitationModel> invitations = new SimpleListProperty<>( FXCollections.observableArrayList( InvitationModel.extractor() ) );
    private final StringProperty currentlyChattingWith = new SimpleStringProperty();
     */
    private String phoneNumber;
    private String displayName;
    private String gender;
    private String profilePicture;
    private String email;
    private String bio;
    private LocalDate birthDate;

}
