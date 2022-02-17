package gov.iti.jets.presentation.util;

import gov.iti.jets.presentation.models.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ModelFactory {
    private static ModelFactory modelFactory = new ModelFactory();

    private UserModel userModel = new UserModel();
    private UserModel mockUserModel = new UserModel();
    private GroupChatModel createGroupChatModel ;

    private ModelFactory() {
        populateMockUserModel();
    }

    public static ModelFactory getInstance() {
        return modelFactory;
    }

    public UserModel getUserModel() {
        return mockUserModel;
    }

    private void populateMockUserModel() {

        mockUserModel.setPhoneNumber( "11111111111" );
        mockUserModel.setDisplayName( "johnson123" );
        mockUserModel.setGender( "F" );
        mockUserModel.setEmail( "johnson123@gmaiil.com" );
        mockUserModel.setBio( "I want to die." );
        mockUserModel.setBirthDate( LocalDate.now() );
        mockUserModel.setCountry( new CountryModel( 22, "Egypt" ) );
        mockUserModel.setCurrentStatus( UserStatusModel.AVAILABLE );
        mockUserModel.setCurrentlyChattingWith( "hamada" );
        ContactModel cm1 = new ContactModel("12345678910", "salma77", UserStatusModel.AWAY );
        cm1.getMesssages().add( new MessageModel( "salma77", LocalDateTime.now(), "Hello it's me salma", "", "", false ) );
        cm1.getMesssages().add( new MessageModel( "You", LocalDateTime.now(), "Hello it's me johnson", "", "-fx-background-color: orange;", true ) );
        cm1.getMesssages().add( new MessageModel( "You", LocalDateTime.now(), "Hello it's me beeb", "-fx-fill: red; -fx-underline: true; -fx-font-family: 'Comic Sans MS'", "", true ) );
        cm1.getMesssages().add( new MessageModel( "You", LocalDateTime.now(), "Hello it's me johnson", "-fx-font-size: 24;", "", true ) );
        cm1.getMesssages().add( new MessageModel( "You", LocalDateTime.now(), "Hello it's me johnson", "", "", true ) );
        cm1.getMesssages().add( new MessageModel( "You", LocalDateTime.now(), "Hello it's me johnson", "", "", true ) );
        cm1.getMesssages().add( new MessageModel( "You", LocalDateTime.now(), "Hello it's me johnson", "", "", true ) );
        cm1.getMesssages().add( new MessageModel( "You", LocalDateTime.now(), "Hello it's me johnson", "", "", true ) );
        ContactModel cm2 = new ContactModel("56789101132", "mohamed11", UserStatusModel.AVAILABLE );
        cm2.getMesssages().add( new MessageModel( "christine33", LocalDateTime.now(), "ana christine 3aml eh", "", "", false ) );
        cm2.getMesssages().add( new MessageModel( "You", LocalDateTime.now(), "ezayek ya christine", "", "", true ) );
        mockUserModel.getContacts().addAll( cm1, cm2 );

        GroupChatModel gcm1 = new GroupChatModel( 55, "jets" );
        gcm1.getMesssages().add( new MessageModel( "hamada91", LocalDateTime.now(), "a group chat message walla eh eh", "", "", false ) );
        gcm1.getMesssages().add( new MessageModel( "You", LocalDateTime.now(), "ana mahmoud 3aml eh", "", "", true ) );
        mockUserModel.getGroupChats().add( gcm1 );

        mockUserModel.getInvitations().add( new InvitationModel( new ContactModel("12345678910", "bob521", UserStatusModel.AWAY ) ) );
    }

    public GroupChatModel getCreateGroupChatModel() {
        if(createGroupChatModel==null){
            createGroupChatModel=new GroupChatModel();
            createGroupChatModel.setGroupMembersList(getUserModel().contactsProperty());
        }
        return createGroupChatModel;
    }

}
