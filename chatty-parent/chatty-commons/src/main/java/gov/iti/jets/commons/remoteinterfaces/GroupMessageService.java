package gov.iti.jets.commons.remoteinterfaces;

import gov.iti.jets.commons.dtos.GroupMessageDto;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GroupMessageService extends Remote {
    void sendGroupMessage(GroupMessageDto groupMessageDto) throws RemoteException;
}
