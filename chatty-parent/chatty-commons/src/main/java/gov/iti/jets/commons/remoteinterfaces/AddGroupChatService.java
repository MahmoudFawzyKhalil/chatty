package gov.iti.jets.commons.remoteinterfaces;

import gov.iti.jets.commons.dtos.AddGroupChatDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AddGroupChatService extends Remote {
    boolean addGroup(AddGroupChatDto addGroupChatDto) throws RemoteException;
}
