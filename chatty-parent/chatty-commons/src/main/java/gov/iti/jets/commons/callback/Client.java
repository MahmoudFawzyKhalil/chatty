package gov.iti.jets.commons.callback;

import gov.iti.jets.commons.dtos.ContactDto;
import gov.iti.jets.commons.dtos.InvitationDto;
import gov.iti.jets.commons.dtos.SingleMessageDto;
import gov.iti.jets.commons.dtos.UserDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Client extends Remote {
    void loadUserModel(UserDto userDto) throws RemoteException;
    void receiveSingleMessage(SingleMessageDto singleMessageDto) throws RemoteException;
    void receiveInvitation(InvitationDto invitationDto) throws RemoteException;
    void updateContactList(List<ContactDto> dtos) throws RemoteException;
    void addContact(ContactDto contactDto) throws RemoteException;
    void addInvitation(InvitationDto senderInvitationDto)throws RemoteException;
}
