package gov.iti.jets.presentation.util;

import gov.iti.jets.presentation.models.*;

import java.time.LocalDate;

public class ModelFactory {
    private static ModelFactory modelFactory = new ModelFactory();

    private UserModel userModel = new UserModel();
    private UserModel mockUserModel = new UserModel();

    private ModelFactory(){

    }

    public static ModelFactory getInstance() {
        return modelFactory;
    }

    public UserModel getUserModel(){
        return userModel;
    }

    public UserModel getMockUserModel(){
        mockUserModel = new UserModel();

        mockUserModel.setPhoneNumber("01117950455");
        mockUserModel.setDisplayName("johnson123");
        mockUserModel.setGender("F");
        mockUserModel.setProfilePicture(null);
        mockUserModel.setEmail("johnson123@gmaiil.com");
        mockUserModel.setBio("I want to die.");
        mockUserModel.setBirthDate(LocalDate.now());
        mockUserModel.setCountry(new CountryModel(22, "Egypt"));
        mockUserModel.setCurrentStatus(UserStatusModel.AVAILABLE);

        ContactModel cm1 = new ContactModel(33, "12345678910", "salma77", null, UserStatusModel.AWAY);
        cm1.getMesssages().add(new MessageModel("salma77", null, LocalDate.now(), "Hello it's me salma", "", "", false));
        cm1.getMesssages().add(new MessageModel("You", null, LocalDate.now(), "Hello it's me johnson", "", "", true));
        ContactModel cm2 = new ContactModel(33, "12345678910", "salma77", null, UserStatusModel.AWAY);
        cm2.getMesssages().add(new MessageModel("christine33", null, LocalDate.now(), "ana christine 3aml eh", "", "", false));
        cm2.getMesssages().add(new MessageModel("You", null, LocalDate.now(), "ezayek ya christine", "", "", true));
        mockUserModel.getContacts().addAll(cm1, cm2);

        GroupChatModel gcm1 = new GroupChatModel(55, "jets", null);
        gcm1.getMesssages().add(new MessageModel("hamada91", null, LocalDate.now(), "a group chat message walla eh eh", "", "", false));
        gcm1.getMesssages().add(new MessageModel("You", null, LocalDate.now(), "ana mahmoud 3aml eh", "", "", true));
        mockUserModel.getGroupChats().add(gcm1);

        mockUserModel.getInvitations().add(new InvitationModel(new ContactModel(55, "12345678910", "bob521", null, UserStatusModel.AWAY)));

        return mockUserModel;
    }
}
