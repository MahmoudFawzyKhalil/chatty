package gov.iti.jets.network;

import gov.iti.jets.commons.callback.Client;
import gov.iti.jets.commons.dtos.*;
import gov.iti.jets.commons.enums.StatusNotificationType;
import gov.iti.jets.presentation.models.*;
import gov.iti.jets.presentation.models.mappers.*;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.application.Platform;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientImpl extends UnicastRemoteObject implements Client {

    private final transient UserModel userModel = ModelFactory.getInstance().getUserModel();
    private final transient StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private static ClientImpl INSTANCE;

    static {
        try {
            INSTANCE = new ClientImpl();
        } catch (RemoteException e) {
            System.err.println( "Failed to export ClientImpl" );
            e.printStackTrace();
        }
    }

    private ClientImpl() throws RemoteException {
    }

    public static ClientImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void loadUserModel( UserDto userDto ) throws RemoteException {
        //userDto = testUserDto();
        userModel.setPhoneNumber( userDto.getPhoneNumber() );
        userModel.setDisplayName( userDto.getDisplayName() );
        userModel.setGender( userDto.getGender() );
        userModel.setEmail( userDto.getEmail() );
        userModel.setBio( userDto.getBio() );
        userModel.setBirthDate( userDto.getBirthDate() );
        userModel.setCountry( CountryMapper.INSTANCE.countryDtoToModel( userDto.getCountry() ) );
        userModel.setCurrentStatus( UserStatusMapper.INSTANCE.dtoToModel( userDto.getCurrentStatus() ) );
        userModel.setProfilePicture( ImageMapper.getInstance().encodedStringToImage( userDto.getProfilePicture() ) );

        for (ContactDto contactDto : userDto.getContactsList()) {
            userModel.getContacts().add( ContactMapper.INSTANCE.contactDtoToModel( contactDto ) );
        }

        for (GroupChatDto groupChatDto : userDto.getGroupChatList()) {
            userModel.getGroupChats().add( GroupChatMapper.INSTANCE.dtoToModel( groupChatDto ) );
        }

        for (InvitationDto invitationDto : userDto.getInvitationsList()) {
            userModel.getInvitations().add( InvitationMapper.INSTANCE.dtoToModel( invitationDto ) );
        }
    }

    private UserDto testUserDto() {

        List<ContactDto> contactsList = new ArrayList<>();
        contactsList.add( new ContactDto( "01117950455", "Hamada", "", new UserStatusDto( 3, "Busy" ) ) );

        List<GroupChatDto> groupChatList = new ArrayList<>();
        groupChatList.add( new GroupChatDto( 5, "HAHA", "", new ArrayList<>() ) );

        List<InvitationDto> invitationsList = new ArrayList<>();
        invitationsList.add( new InvitationDto( new ContactDto( "56565656565", "shaksho22", "", new UserStatusDto( 1, "Available" ) ) ) );

        UserDto userDto = new UserDto( "11111111111", "Mahmoud", "M", null,
                "mahmoud@gmail.com", "I like cookies.", LocalDate.of( 1998, 1, 21 ),
                new CountryDto( 1, "Egypt" ), new UserStatusDto( 1, "Available" ),
                contactsList, groupChatList, invitationsList );

        return userDto;
    }

    @Override
    public void receiveSingleMessage( SingleMessageDto singleMessageDto ) throws RemoteException {
        MessageModel messageModel = SingleMessageMapper.INSTANCE.dtoToModel( singleMessageDto );

        Optional<ContactModel> optionalContactModel = userModel.getContacts().stream()
                .filter( cm -> cm.getPhoneNumber().equals( singleMessageDto.getSenderPhoneNumber() ) )
                .findFirst();

        if (!optionalContactModel.isEmpty()) {
            messageModel.setSenderName( optionalContactModel.get().getDisplayName() );
            Platform.runLater( () -> {
                optionalContactModel.get().getMesssages().add( messageModel );
            } );
        }
    }

    @Override
    public void addGroupChat( GroupChatDto groupChatDto ) throws RemoteException {
        GroupChatModel groupChatModel = GroupChatMapper.INSTANCE.dtoToModel( groupChatDto );
        Platform.runLater( () -> {
            userModel.getGroupChats().add( groupChatModel );
        } );
    }

    @Override
    public void receiveInvitation( InvitationDto invitationDto ) throws RemoteException {

    }

    @Override
    public void updateContactList( List<ContactDto> dtos ) throws RemoteException {

    }

    @Override
    public void addContact( ContactDto contactDto ) throws RemoteException {
        Platform.runLater( () -> {
            ContactModel contactModel = ContactMapper.INSTANCE.contactDtoToModel( contactDto );
            userModel.getContacts().add( contactModel );

            userModel.getInvitations().removeIf( invitationModel -> invitationModel.getContactModel().getPhoneNumber().equals( contactModel.getPhoneNumber() ) );
        } );
    }

    @Override
    public void addInvitation( InvitationDto receiverInvitationDto ) throws RemoteException {
        Platform.runLater( () -> {
            InvitationModel invitationModel = InvitationMapper.INSTANCE.dtoToModel( receiverInvitationDto );
            userModel.getInvitations().add( invitationModel );
        } );
    }

    @Override
    public void notifyOfStatusUpdate( StatusNotificationDto dto ) throws RemoteException {
        String contactToUpdate = dto.getContactWhoseStatusHasChangedPhoneNumber();
        StatusNotificationType newStatus = dto.getNewStatus();

        userModel.getContacts().stream().filter( cm -> cm.getPhoneNumber().equals( contactToUpdate ) )
                .findFirst()
                .ifPresent( contactModel -> {
                    Platform.runLater( () -> {
                        contactModel.setCurrentStatus( UserStatusModel.getStatusModelFromNotificationType( newStatus ) );
                        stageCoordinator.showMessageNotification( contactModel.getDisplayName() + " is now " + newStatus.name(), "" );
                    } );
                } );
    }


}
