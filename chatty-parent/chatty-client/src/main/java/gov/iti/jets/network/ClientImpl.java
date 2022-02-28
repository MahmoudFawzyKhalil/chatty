package gov.iti.jets.network;

import gov.iti.jets.commons.callback.Client;
import gov.iti.jets.commons.dtos.*;
import gov.iti.jets.commons.util.mappers.ImageMapper;
import gov.iti.jets.presentation.models.*;
import gov.iti.jets.commons.enums.StatusNotificationType;
import gov.iti.jets.presentation.models.*;
import gov.iti.jets.presentation.models.mappers.*;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.ChatBotService;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ClientImpl extends UnicastRemoteObject implements Client {

    private final transient UserModel userModel = ModelFactory.getInstance().getUserModel();
    private final transient ChatBotService chatBotService = ServiceFactory.getInstance().getChatBotService();
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


    @Override
    public void loadSingleMessages(Map<String, List<SingleMessageDto>> messagesMap) throws RemoteException {

        messagesMap.forEach((k, v) -> {
            Optional<ContactModel> optionalContactModel = userModel.getContacts().stream()
                    .filter(cm -> cm.getPhoneNumber().equals(k))
                    .findFirst();

            List<MessageModel> messageModelList = new ArrayList<>();
            for(SingleMessageDto messageDto : v){
                MessageModel messageModel = SingleMessageMapper.INSTANCE.dtoToModel(messageDto);
                if(messageDto.getSenderPhoneNumber().equals(userModel.getPhoneNumber())){
                    messageModel.setSentByMe(true);
                    messageModel.setSenderName(userModel.getDisplayName());
                }
                else{
                    messageModel.setSenderName(optionalContactModel.get().getDisplayName());
                }
                messageModelList.add(messageModel);
            }
            if(!optionalContactModel.isEmpty()){
                ObservableList<MessageModel> messageModels = FXCollections.observableArrayList(messageModelList);
                Platform.runLater(()->{
                        optionalContactModel.get().setMesssages(messageModels);
                });
            }
        });
    }

    @Override
    public void loadGroupMessages(Map<Integer, List<GroupMessageDto>> messagesMap) throws RemoteException {

        messagesMap.forEach((k, v) -> {
            Optional<GroupChatModel> optionalGroupChatModel = userModel.getGroupChats().stream()
                    .filter(cm -> cm.getGroupChatId()==(k))
                    .findFirst();


            List<MessageModel> messageModelList = new ArrayList<>();
            ObservableList<ContactModel> contactModelList;

            for(GroupMessageDto messageDto : v){
                MessageModel messageModel = GroupMessageMapper.INSTANCE.dtoToModel(messageDto);
                if(messageDto.getSenderPhoneNumber().equals(userModel.getPhoneNumber())){
                    messageModel.setSentByMe(true);
                    messageModel.setSenderName(userModel.getDisplayName());
                }
                else{
                    messageModel.setSenderName(optionalGroupChatModel.get().getGroupMembersList().get(k).getDisplayName());
                }
                messageModelList.add(messageModel);
            }
            if(!optionalGroupChatModel.isEmpty()){
                ObservableList<MessageModel> messageModels = FXCollections.observableArrayList(messageModelList);
                Platform.runLater(()->{
                    optionalGroupChatModel.get().setMesssages(messageModels);
                });
            }
        });
    }

    @Override
    public void receiveSingleMessage( SingleMessageDto singleMessageDto ) throws RemoteException , NotBoundException {
        Optional<ContactModel> optionalContactModel = userModel.getContacts().stream()
                .filter( cm -> cm.getPhoneNumber().equals( singleMessageDto.getSenderPhoneNumber() ) )
                .findFirst();

        MessageModel messageModel = SingleMessageMapper.INSTANCE.dtoToModel(singleMessageDto);

        if (optionalContactModel.isPresent()) {
            messageModel.setSenderName( optionalContactModel.get().getDisplayName() );
            Platform.runLater( () -> {
                messageModel.senderProfilePictureProperty().bind(optionalContactModel.get().profilePictureProperty());
                optionalContactModel.get().getMesssages().add( messageModel );

            } );

            if (userModel.getIsUsingChatBot() && !singleMessageDto.isSentByChatBot()){
                MessageModel reply = chatBotService.formulateAndSendChatBotReply(singleMessageDto);
                Platform.runLater( () -> {
                    optionalContactModel.get().getMesssages().add( reply );
                });
            }
        }

    }

    @Override
    public void receiveGroupMessage(GroupMessageDto groupMessageDto) throws RemoteException {

        if (userModel.getPhoneNumber().equals( groupMessageDto.getSenderPhoneNumber() )){
            return;
        }

        MessageModel messageModel = GroupMessageMapper.INSTANCE.dtoToModel( groupMessageDto );

        var groupChatModelOptional = userModel.getGroupChats()
                .stream()
                .filter( gc -> gc.getGroupChatId() == groupMessageDto.getGroupChatId() )
                .findFirst();

        if (groupChatModelOptional.isPresent()){

            GroupChatModel groupChatModel = groupChatModelOptional.get();

            var senderOptional = groupChatModel
                    .getGroupMembersList()
                    .stream()
                    .filter( gm -> gm.getPhoneNumber().equals( groupMessageDto.getSenderPhoneNumber() ) )
                    .findFirst();


            if (senderOptional.isPresent()){
                ContactModel sender = senderOptional.get();
                messageModel.senderNameProperty().bind( sender.displayNameProperty() );
                messageModel.senderProfilePictureProperty().bind( sender.profilePictureProperty() );
            }

            Platform.runLater( () -> {
                groupChatModel.getMesssages().add( messageModel );
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
        Platform.runLater(() -> {
            ContactModel contactModel = ContactMapper.INSTANCE.contactDtoToModel(contactDto);
            userModel.getContacts().add(contactModel);

            userModel.getInvitations().removeIf( invitationModel -> invitationModel.getContactModel().getPhoneNumber().equals( contactModel.getPhoneNumber() ) );
        });
    }

    @Override
    public void addInvitation( InvitationDto receiverInvitationDto ) throws RemoteException {
        Platform.runLater(() -> {
            InvitationModel invitationModel = InvitationMapper.INSTANCE.dtoToModel(receiverInvitationDto);
            userModel.getInvitations().add(invitationModel);
        });
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

    @Override
    public void notifyContactPicChange(UpdateProfilePicDto updateProfilePicDto)  {
        Optional<ContactModel>changedContactModel=userModel.getContacts().stream().filter(c->c.getPhoneNumber().equals(updateProfilePicDto.getPhoneNumber())).findFirst();
        changedContactModel.ifPresent(c->{
            Platform.runLater(()->{
                c.setProfilePicture(ImageMapper.getInstance().encodedStringToImage(updateProfilePicDto.getPictureBase46()));
            });
        });
    }


}
