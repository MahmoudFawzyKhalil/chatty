package gov.iti.jets.network;

import gov.iti.jets.commons.callback.Client;
import gov.iti.jets.commons.dtos.*;
import gov.iti.jets.commons.enums.StatusNotificationType;
import gov.iti.jets.commons.util.mappers.ImageMapper;
import gov.iti.jets.presentation.models.*;
import gov.iti.jets.presentation.models.mappers.*;
import gov.iti.jets.presentation.util.ExecutorUtil;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.ChatBotService;
import gov.iti.jets.services.FileTransferDao;
import gov.iti.jets.services.util.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ClientImpl extends UnicastRemoteObject implements Client {

    private final transient UserModel userModel = ModelFactory.getInstance().getUserModel();
    private final transient FileTransferOperationAvailabilityModel fileTransferOperationAvailabilityModel = ModelFactory.getInstance().getFileTransferOperationAvailabilityModel();
    private final transient ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final transient FileTransferDao fileTransferDao = DaoFactory.getInstance().getFileTransferDao();
    private final transient ChatBotService chatBotService = serviceFactory.getChatBotService();
    private final transient StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final transient ExecutorUtil executorUtil = ExecutorUtil.getInstance();
    public transient FileTransferReceivingTask fileTransferReceivingTask;
    public transient FileTransferTask fileTransferTask;
    private static ClientImpl INSTANCE;
    private transient static Logger logger = LoggerFactory.getLogger(ClientImpl.class);
    private transient String currentDirectory = System.getProperty("user.dir");

    static {
        try {
            INSTANCE = new ClientImpl();
        } catch (RemoteException e) {
            logger.error("Failed to export ClientImpl");
            e.printStackTrace();
        }
    }

    private ClientImpl() throws RemoteException {
    }

    public static ClientImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void loadUserModel(UserDto userDto) throws RemoteException {
        userModel.setPhoneNumber(userDto.getPhoneNumber());
        userModel.setDisplayName(userDto.getDisplayName());
        userModel.setGender(userDto.getGender());
        userModel.setEmail(userDto.getEmail());
        userModel.setBio(userDto.getBio());
        userModel.setBirthDate(userDto.getBirthDate());
        userModel.setCountry(CountryMapper.INSTANCE.countryDtoToModel(userDto.getCountry()));
        userModel.setCurrentStatus(UserStatusMapper.INSTANCE.dtoToModel(userDto.getCurrentStatus()));

        userModel.setProfilePicture(ImageMapper.getInstance().encodedStringToImage(userDto.getProfilePicture()));


        for (ContactDto contactDto : userDto.getContactsList()) {
            userModel.getContacts().add(ContactMapper.INSTANCE.contactDtoToModel(contactDto));
        }

        for (GroupChatDto groupChatDto : userDto.getGroupChatList()) {
            userModel.getGroupChats().add(GroupChatMapper.INSTANCE.dtoToModel(groupChatDto));
        }

        for (InvitationDto invitationDto : userDto.getInvitationsList()) {
            userModel.getInvitations().add(InvitationMapper.INSTANCE.dtoToModel(invitationDto));
        }
    }

    @Override
    public void loadSingleMessages(Map<String, List<SingleMessageDto>> messagesMap) throws RemoteException {

        messagesMap.forEach((k, v) -> {
            Optional<ContactModel> optionalContactModel = userModel.getContacts().stream()
                    .filter(cm -> cm.getPhoneNumber().equals(k))
                    .findFirst();

            List<MessageModel> messageModelList = new ArrayList<>();
            for (SingleMessageDto messageDto : v) {
                MessageModel messageModel = SingleMessageMapper.INSTANCE.dtoToModel(messageDto);
                if (messageDto.getSenderPhoneNumber().equals(userModel.getPhoneNumber())) {
                    messageModel.setSentByMe(true);
                    messageModel.setSenderName(userModel.getDisplayName());
                } else {
                    messageModel.setSenderName(optionalContactModel.get().getDisplayName());
                }
                messageModelList.add(messageModel);
            }
            if (!optionalContactModel.isEmpty()) {
                ObservableList<MessageModel> messageModels = FXCollections.observableArrayList(messageModelList);
                Platform.runLater(() -> {
                    optionalContactModel.get().setMesssages(messageModels);
                });
            }
        });
    }

    @Override
    public void notifyOfServerShutDown() throws RemoteException {
        Platform.runLater(() -> {
            stageCoordinator.showErrorNotification("Server has shutdown. Please contact your server administrator");
            ModelFactory.getInstance().clearUserModel();
            stageCoordinator.switchToConnectToServer();
        });
        serviceFactory.shutdown();
    }

    @Override
    public void receiveAnnouncement(AnnouncementDto announcementDto) throws RemoteException {
        Platform.runLater(() -> {
            stageCoordinator.showAdminNotification(announcementDto);
        });
    }

    @Override
    public void receiveSingleMessage(SingleMessageDto singleMessageDto) throws RemoteException, NotBoundException {
        Optional<ContactModel> optionalContactModel = userModel.getContacts().stream()
                .filter(cm -> cm.getPhoneNumber().equals(singleMessageDto.getSenderPhoneNumber()))
                .findFirst();

        MessageModel messageModel = SingleMessageMapper.INSTANCE.dtoToModel(singleMessageDto);

        if (optionalContactModel.isPresent()) {
            messageModel.setSenderName(optionalContactModel.get().getDisplayName());
            Platform.runLater(() -> {
                messageModel.senderProfilePictureProperty().bind(optionalContactModel.get().profilePictureProperty());
                optionalContactModel.get().getMesssages().add(messageModel);
                stageCoordinator.showMessageNotification(messageModel.getSenderName(), messageModel.getMessageBody());
            });

            if (userModel.getIsUsingChatBot() && !singleMessageDto.isSentByChatBot()) {
                MessageModel reply = chatBotService.formulateAndSendChatBotReply(singleMessageDto);
                Platform.runLater(() -> {
                    optionalContactModel.get().getMesssages().add(reply);
                });
            }
        }

    }

    @Override
    public void receiveGroupMessage(GroupMessageDto groupMessageDto) throws RemoteException {

        if (userModel.getPhoneNumber().equals(groupMessageDto.getSenderPhoneNumber())) {
            return;
        }

        MessageModel messageModel = GroupMessageMapper.INSTANCE.dtoToModel(groupMessageDto);

        var groupChatModelOptional = userModel.getGroupChats()
                .stream()
                .filter(gc -> gc.getGroupChatId() == groupMessageDto.getGroupChatId())
                .findFirst();

        if (groupChatModelOptional.isPresent()) {

            GroupChatModel groupChatModel = groupChatModelOptional.get();

            var senderOptional = groupChatModel
                    .getGroupMembersList()
                    .stream()
                    .filter(gm -> gm.getPhoneNumber().equals(groupMessageDto.getSenderPhoneNumber()))
                    .findFirst();


            if (senderOptional.isPresent()) {
                ContactModel sender = senderOptional.get();
                messageModel.senderNameProperty().bind(sender.displayNameProperty());
                messageModel.senderProfilePictureProperty().bind(sender.profilePictureProperty());
            }

            Platform.runLater(() -> {
                groupChatModel.getMesssages().add(messageModel);
                stageCoordinator.showMessageNotification( groupChatModel.getGroupChatName(), messageModel.getMessageBody() );
            });
        }

    }

    @Override
    public void addGroupChat(GroupChatDto groupChatDto) throws RemoteException {
        GroupChatModel groupChatModel = GroupChatMapper.INSTANCE.dtoToModel(groupChatDto);
        Platform.runLater(() -> {
            userModel.getGroupChats().add(groupChatModel);
        });
    }

    @Override
    public void addContact(ContactDto contactDto) throws RemoteException {
        Platform.runLater(() -> {
            ContactModel contactModel = ContactMapper.INSTANCE.contactDtoToModel(contactDto);
            userModel.getContacts().add(contactModel);

            userModel.getInvitations().removeIf(invitationModel -> invitationModel.getContactModel().getPhoneNumber().equals(contactModel.getPhoneNumber()));
        });
    }

    @Override
    public void addInvitation(InvitationDto receiverInvitationDto) throws RemoteException {
        Platform.runLater(() -> {
            InvitationModel invitationModel = InvitationMapper.INSTANCE.dtoToModel(receiverInvitationDto);
            userModel.getInvitations().add(invitationModel);
            stageCoordinator.showMessageNotification("New friend request!",
                    receiverInvitationDto.getContactDto().getDisplayName() + " wants to be your friend!");
        });
    }

    @Override
    public void notifyOfStatusUpdate(StatusNotificationDto dto) throws RemoteException {
        String contactToUpdate = dto.getContactWhoseStatusHasChangedPhoneNumber();
        StatusNotificationType newStatus = dto.getNewStatus();

        userModel.getContacts().stream().filter(cm -> cm.getPhoneNumber().equals(contactToUpdate))
                .findFirst()
                .ifPresent(contactModel -> {
                    Platform.runLater(() -> {
                        contactModel.setCurrentStatus(UserStatusModel.getStatusModelFromNotificationType(newStatus));
                        stageCoordinator.showMessageNotification(contactModel.getDisplayName() + " is now " + newStatus.name(), "");
                    });
                });
    }

    @Override
    public void notifyContactPicChange(UpdateProfilePicDto updateProfilePicDto) {
        Optional<ContactModel> changedContactModel = userModel.getContacts().stream().filter(c -> c.getPhoneNumber().equals(updateProfilePicDto.getPhoneNumber())).findFirst();
        changedContactModel.ifPresent(c -> {
            Platform.runLater(() -> {
                c.setProfilePicture(ImageMapper.getInstance().encodedStringToImage(updateProfilePicDto.getPictureBase46()));
            });
        });
    }

    @Override
    public void notifyContactProfileChange(UpdateProfileDto updateProfileDto) {
        Optional<ContactModel> changedContactModel = userModel.getContacts().stream().filter(c -> c.getPhoneNumber().equals(updateProfileDto.getPhoneNumber())).findFirst();
        changedContactModel.ifPresent(c -> {
            Platform.runLater(() -> {
                c.setDisplayName(updateProfileDto.getDisplayName());
            });
        });
    }

    @Override
    public void receiveFileTransferPermission(FileTransferPermissionDto fileTransferPermissionDto) {

        FileTransferResponseDto fileTransferResponseDto = new FileTransferResponseDto();
        Optional<ContactModel> optionalContactModel = userModel.getContacts().stream()
                .filter(cm -> cm.getPhoneNumber().equals(fileTransferPermissionDto.getSenderPhoneNumber()))
                .findFirst();

        if (!optionalContactModel.isEmpty()) {

            if (!fileTransferOperationAvailabilityModel.isAvailable()) {
                fileTransferResponseDto.setAccepted(false);
                try {
                    fileTransferResponseDto.setSenderPhoneNumber(optionalContactModel.get().getPhoneNumber());
                    fileTransferResponseDto.setReceiverPhoneNumber(userModel.getPhoneNumber());
                    fileTransferDao.sendFileTransferResponse(fileTransferResponseDto);
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (NotBoundException e) {
                    e.printStackTrace();
                }
                return;
            }


            final FutureTask<Boolean> query = new FutureTask(new FileTransferAcceptanceCallable(fileTransferPermissionDto, optionalContactModel.get()));
            Platform.runLater(query);
            try {
                if (!query.get()) {
                    fileTransferResponseDto.setSenderPhoneNumber(optionalContactModel.get().getPhoneNumber());
                    fileTransferResponseDto.setReceiverPhoneNumber(userModel.getPhoneNumber());
                    fileTransferResponseDto.setAccepted(false);
                    fileTransferDao.sendFileTransferResponse(fileTransferResponseDto);
                    return;
                } else {
                    fileTransferResponseDto.setAccepted(true);
                    fileTransferResponseDto.setReceiverIp(InetAddress.getLocalHost().getHostAddress());
                    fileTransferResponseDto.setSenderPhoneNumber(optionalContactModel.get().getPhoneNumber());
                    fileTransferResponseDto.setReceiverPhoneNumber(userModel.getPhoneNumber());
                    fileTransferResponseDto.setFile(fileTransferPermissionDto.getFile());
                    fileTransferDao.sendFileTransferResponse(fileTransferResponseDto);
                    FileModel fileModel = createFileModel(fileTransferPermissionDto.getFile());
                    Platform.runLater(() -> {
                        userModel.getFileTransferList().add(fileModel);
                    });
                    fileTransferReceivingTask = new FileTransferReceivingTask(fileModel);
                    executorUtil.execute(fileTransferReceivingTask);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void receiveFileTransferResponse(FileTransferResponseDto fileTransferResponseDto) throws RemoteException {
        Optional<ContactModel> optionalContactModel = userModel.getContacts().stream()
                .filter(cm -> cm.getPhoneNumber().equals(fileTransferResponseDto.getReceiverPhoneNumber()))
                .findFirst();
        if (!optionalContactModel.isEmpty()) {
            if (!fileTransferResponseDto.isAccepted()) {
                Platform.runLater(() -> {
                    stageCoordinator.showMessageNotification("File Transfer Response",
                            optionalContactModel.get().getDisplayName() + " did not accept file transfer.");
                });
                return;
            }
        }
        Platform.runLater(() -> {
            stageCoordinator.showMessageNotification("File Transfer Response",
                    optionalContactModel.get().getDisplayName() + " accepts file transfer.");
        });

        FileModel fileModel = createFileModel(fileTransferResponseDto.getFile());
        Platform.runLater(() -> {
            userModel.getFileTransferList().add(fileModel);
        });
        fileTransferTask = new FileTransferTask(fileModel, fileTransferResponseDto.getReceiverIp());
        executorUtil.execute(fileTransferTask);
    }


    private FileModel createFileModel(File file) {
        FileModel fileModel = new FileModel();
        fileModel.setFile(file);
        fileModel.setFileSize(file.length());
        fileModel.setFileName(file.getName());
        fileModel.setNumberOfBytesSoFar(0);
        fileModel.setSenderName(userModel.getDisplayName());
        fileModel.setIsCanceled(false);
        return fileModel;
    }

    @Override
    public boolean receiveVoiceChatPermission(VoiceChatDto voiceChatDto) throws RemoteException {
        VoiceChatModel voiceChatModel = ModelFactory.getInstance().getVoiceChatModel();
        if (voiceChatModel.isInCall())
            return false;

        Optional<ContactModel> optionalContactModel = userModel.getContacts().stream()
                .filter(cm -> cm.getPhoneNumber().equals(voiceChatDto.getCallerPhoneNumber()))
                .findFirst();
        if (optionalContactModel.isPresent()) {

            Platform.runLater(() -> {
                ContactModel contactModel = optionalContactModel.get();

                voiceChatModel.setAll(contactModel, voiceChatDto.getCallerIp());
                voiceChatModel.setInCall(true);
                stageCoordinator.showVoiceChatAcceptance();
            });
        }
        return true;
    }

    @Override
    public boolean startVoiceChat(VoiceChatDto voiceChatDto) throws RemoteException {

        VoiceChatModel voiceChatModel = ModelFactory.getInstance().getVoiceChatModel();

        voiceChatModel.setContactIp(voiceChatDto.getCallerIp());

        Platform.runLater(() -> {
            voiceChatModel.setInCall(true);
            stageCoordinator.closeVoiceChatRinging();
            stageCoordinator.showVoiceChatCallStage();
        });
        return true;
    }

    @Override
    public void closeVoiceChat(VoiceChatDto voiceChatDto) throws RemoteException {
        Platform.runLater(()->{
            ModelFactory.getInstance().getVoiceChatModel().setInCall(false);
        });
    }
}