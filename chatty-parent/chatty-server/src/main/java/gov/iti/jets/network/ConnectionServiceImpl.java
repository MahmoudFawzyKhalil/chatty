package gov.iti.jets.network;

import gov.iti.jets.commons.callback.Client;
import gov.iti.jets.commons.dtos.*;
import gov.iti.jets.commons.remoteinterfaces.ConnectionService;
import gov.iti.jets.repository.entities.UserEntity;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.repository.util.mappers.UserMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConnectionServiceImpl extends UnicastRemoteObject implements ConnectionService {

    private static final RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private Clients clients = Clients.getInstance();
    protected ConnectionServiceImpl() throws RemoteException {
    }

    @Override
    public void registerClient(String phoneNumber, Client client) throws RemoteException {
        clients.addClient(phoneNumber,client);

        Optional<UserEntity> userEntity = repositoryFactory.getUserRepository().getUserByPhoneNumber(phoneNumber);
        UserDto userDto = UserMapper.INSTANCE.userEntityToDto(userEntity.get());

        System.out.println("hi "+userDto);
        client.loadUserModel(userDto);
    }

    @Override
    public void unregisterClient(String phoneNumber) throws RemoteException {
        clients.removeClient(phoneNumber);
    }
    private UserDto testUserDto() {

        List<ContactDto> contactsList = new ArrayList<>();
        contactsList.add( new ContactDto( "01117950455", "Reem", "", new UserStatusDto( 3, "Busy" ) ) );

        List<GroupChatDto> groupChatList = new ArrayList<>();
        groupChatList.add( new GroupChatDto( 5, "Mariam", "", new ArrayList<>() ) );

        List<InvitationDto> invitationsList = new ArrayList<>();
        invitationsList.add( new InvitationDto( new ContactDto( "56565656565", "shaksho22", "", new UserStatusDto( 1, "Available" ) ) ) );

        UserDto userDto = new UserDto( "07775000000", "Salma", "F", null,
                "mahmoud@gmail.com", "I like cookies.", LocalDate.of( 1998, 1, 21 ),
                new CountryDto( 1, "Egypt" ), new UserStatusDto( 1, "Available" ),
                contactsList, groupChatList, invitationsList);

        return userDto;
    }
}
