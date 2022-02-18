package gov.iti.jets.services;

import gov.iti.jets.commons.dtos.AddGroupChatDto;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface AddGroupChatDao {
    boolean addGroup(AddGroupChatDto addGroupChatDto)throws NotBoundException, RemoteException;
}
