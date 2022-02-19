package gov.iti.jets.services.impls;

import gov.iti.jets.commons.dtos.AddGroupChatDto;
import gov.iti.jets.commons.remoteinterfaces.AddGroupChatService;
import gov.iti.jets.services.AddGroupChatDao;
import gov.iti.jets.services.util.ServiceFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class AddGroupChatDaoImpl implements AddGroupChatDao {
    @Override
    public boolean addGroup(AddGroupChatDto addGroupChatDto) throws NotBoundException, RemoteException {
        AddGroupChatService addGroupChatService = ServiceFactory.getInstance().getAddGroupService();
        return addGroupChatService.addGroup(addGroupChatDto);
    }
}
