package gov.iti.jets.services.callback;

import gov.iti.jets.commons.callback.Client;
import gov.iti.jets.commons.dtos.ContactDto;
import gov.iti.jets.commons.dtos.InvitationDto;
import gov.iti.jets.commons.dtos.MessageDto;
import gov.iti.jets.commons.dtos.UserDto;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.models.mappers.UserMapper;
import gov.iti.jets.presentation.util.ModelFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ClientImpl extends UnicastRemoteObject implements Client {

    private static final transient ModelFactory mddelFactory = ModelFactory.getInstance();
    private transient UserModel userModel = mddelFactory.getUserModel();

    protected ClientImpl() throws RemoteException {
    }

    @Override
    public void loadUserModel(UserDto userDto) throws RemoteException {
        userModel = UserMapper.INSTANCE.userDtoToModel(userDto);
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

    @Override
    public String getPhoneNumber() throws RemoteException {
        return userModel.getPhoneNumber();
    }
}
