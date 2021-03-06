package gov.iti.jets.commons.callback;

import gov.iti.jets.commons.dtos.*;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface Client extends Remote {
    void loadUserModel(UserDto userDto) throws RemoteException;

    void receiveSingleMessage(SingleMessageDto singleMessageDto) throws RemoteException, NotBoundException;

    void receiveGroupMessage(GroupMessageDto groupMessageDto) throws RemoteException;

    void addGroupChat(GroupChatDto groupChatDto) throws RemoteException;

    void addContact(ContactDto contactDto) throws RemoteException;

    void addInvitation(InvitationDto senderInvitationDto) throws RemoteException;

    void notifyOfStatusUpdate(StatusNotificationDto dto) throws RemoteException;

    void loadSingleMessages(Map<String, List<SingleMessageDto>> messagesMap) throws RemoteException;

    void notifyOfServerShutDown() throws RemoteException;

    void receiveAnnouncement(AnnouncementDto announcementDto) throws RemoteException;

    void notifyContactPicChange(UpdateProfilePicDto updateProfilePicDto) throws RemoteException;

    void notifyContactProfileChange(UpdateProfileDto updateProfileDto) throws RemoteException;

    void receiveFileTransferPermission(FileTransferPermissionDto fileTransferPermissionDto) throws RemoteException;

    void receiveFileTransferResponse(FileTransferResponseDto fileTransferResponseDto) throws RemoteException;

    boolean receiveVoiceChatPermission(VoiceChatDto voiceChatDto) throws RemoteException;

    boolean startVoiceChat(VoiceChatDto voiceChatDto) throws RemoteException;

    void closeVoiceChat(VoiceChatDto voiceChatDto) throws RemoteException;

    void loadGroupMessages(Map<Integer, List<GroupMessageDto>> messagesMap) throws RemoteException;
}
