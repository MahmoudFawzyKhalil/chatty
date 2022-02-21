package gov.iti.jets.services.impls;

import gov.iti.jets.commons.dtos.GroupMessageDto;
import gov.iti.jets.commons.remoteinterfaces.GroupMessageService;
import gov.iti.jets.services.GroupMessageDao;
import gov.iti.jets.services.util.ServiceFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class GroupMessageDaoImpl implements GroupMessageDao {

    @Override
    public void sendGroupMessage(GroupMessageDto groupMessageDto) throws NotBoundException, RemoteException {
        GroupMessageService groupMessageService = ServiceFactory.getInstance().getGroupMessageService();
        groupMessageService.sendGroupMessage(groupMessageDto);
    }
}
