package gov.iti.jets.network;

import gov.iti.jets.commons.dtos.GroupChatDto;
import gov.iti.jets.commons.dtos.GroupMessageDto;
import gov.iti.jets.commons.remoteinterfaces.GroupMessageService;
import gov.iti.jets.repository.GroupChatRepository;
import gov.iti.jets.repository.entities.GroupChatEntity;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.repository.util.mappers.GroupChatMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;

public class GroupMessageServiceImpl extends UnicastRemoteObject implements GroupMessageService {

    private Clients clients = Clients.getInstance();

    protected GroupMessageServiceImpl() throws RemoteException {
    }

    @Override
    public void sendGroupMessage(GroupMessageDto groupMessageDto) throws RemoteException {
        int groupId = groupMessageDto.getGroupChatId();
        if (groupId != -1) {
                clients.sendMessageToOnlineClientsOfAGroupChat(groupMessageDto);
        }

    }

}
