package gov.iti.jets.network;

import gov.iti.jets.commons.dtos.AddGroupChatDto;
import gov.iti.jets.commons.dtos.GroupChatDto;
import gov.iti.jets.commons.remoteinterfaces.AddGroupChatService;
import gov.iti.jets.repository.GroupChatRepository;
import gov.iti.jets.repository.entities.GroupChatEntity;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.repository.util.mappers.GroupChatMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AddGroupChatChatServiceImpl extends UnicastRemoteObject implements AddGroupChatService {
    private Clients clients = Clients.getInstance();
    private GroupChatRepository groupChatRepository = RepositoryFactory.getInstance().getGroupChatRepository();

    protected AddGroupChatChatServiceImpl() throws RemoteException {
    }

    @Override
    public boolean addGroup(AddGroupChatDto addGroupChatDto) throws RemoteException {
        /*TODO
         * get contacts
         * */
        GroupChatEntity groupChatEntity = GroupChatMapper.INSTANCE.addGroupChatDtoToEntity(addGroupChatDto);
        int groupId = groupChatRepository.addGroup(groupChatEntity);
        if (groupId != -1) {
            groupChatEntity.setGroupChatId(groupId);
            GroupChatDto groupChatDto = GroupChatMapper.INSTANCE.groupChatEntityToDto(groupChatEntity);
            clients.addGroup(groupChatDto);
        }
        return groupId != -1;
    }
}
