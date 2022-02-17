package gov.iti.jets.services.callback;

import gov.iti.jets.commons.callback.Client;
import gov.iti.jets.commons.dtos.ContactDto;
import gov.iti.jets.commons.dtos.InvitationDto;
import gov.iti.jets.commons.dtos.MessageDto;
import gov.iti.jets.commons.dtos.UserDto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ClientImpl extends UnicastRemoteObject implements Client {

    protected ClientImpl() throws RemoteException {
    }

    @Override
    public void loadUserModel(UserDto userDto) throws RemoteException {

    }

    @Override
    public void receiveSingleMessage(MessageDto messageDto) throws RemoteException {

    }

    @Override
    public void receiveInvitation(InvitationDto invitationDto) throws RemoteException {

    }

    @Override
    public void updateContactList(List<ContactDto> dtos) throws RemoteException {

    }
}
