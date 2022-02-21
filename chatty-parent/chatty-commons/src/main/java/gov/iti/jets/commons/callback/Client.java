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

    void addGroupChat(GroupChatDto groupChatDto) throws RemoteException;

    void receiveInvitation(InvitationDto invitationDto) throws RemoteException;

    void updateContactList(List<ContactDto> dtos) throws RemoteException;
    void addContact(ContactDto contactDto) throws RemoteException;
    void addInvitation(InvitationDto senderInvitationDto)throws RemoteException;

    void notifyOfStatusUpdate( StatusNotificationDto dto) throws RemoteException;
    void loadSingleMessages(Map<String,List<SingleMessageDto>> messagesMap)throws RemoteException;

    void notifyOfServerShutDown() throws RemoteException;
}
