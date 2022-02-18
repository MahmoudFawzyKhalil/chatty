package gov.iti.jets.services;

import gov.iti.jets.commons.dtos.SingleMessageDto;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface SingleMessageDao {
    void sendMessage(SingleMessageDto singleMessageDto) throws NotBoundException, RemoteException;
}
