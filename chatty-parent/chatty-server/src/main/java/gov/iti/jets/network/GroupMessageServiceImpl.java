package gov.iti.jets.network;

import gov.iti.jets.commons.callback.Client;
import gov.iti.jets.commons.dtos.GroupChatDto;
import gov.iti.jets.commons.dtos.GroupMessageDto;
import gov.iti.jets.commons.remoteinterfaces.GroupMessageService;
import gov.iti.jets.repository.GroupChatRepository;
import gov.iti.jets.repository.entities.GroupChatEntity;
import gov.iti.jets.repository.entities.GroupMessageEntity;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.repository.util.mappers.GroupChatMapper;
import gov.iti.jets.repository.util.mappers.GroupMessageMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;

public class GroupMessageServiceImpl extends UnicastRemoteObject implements GroupMessageService {

    private Clients clients = Clients.getInstance();
    private RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();

    protected GroupMessageServiceImpl() throws RemoteException {
    }

    @Override
    public void sendGroupMessage(GroupMessageDto groupMessageDto) throws RemoteException {
        int groupId = groupMessageDto.getGroupChatId();
        Optional<Client> client  = clients.getClient(groupMessageDto.getSenderPhoneNumber());

        if (groupId != -1) {
                clients.sendMessageToOnlineClientsOfAGroupChat(groupMessageDto);
        }
        GroupMessageEntity groupMessageEntity = GroupMessageMapper.INSTANCE.dtoToEntity(groupMessageDto);
        repositoryFactory.getGroupMessageRepository().insertMessage(groupMessageEntity);

    }

}
