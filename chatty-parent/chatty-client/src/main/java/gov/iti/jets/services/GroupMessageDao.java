package gov.iti.jets.services;

import gov.iti.jets.commons.dtos.GroupMessageDto;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface GroupMessageDao {
    void sendGroupMessage(GroupMessageDto groupMessageDto) throws NotBoundException, RemoteException;
}
