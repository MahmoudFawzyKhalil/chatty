package gov.iti.jets.commons.remoteinterfaces;

import gov.iti.jets.commons.dtos.SingleMessageDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SingleMessageService extends Remote {
    void sendMessage(SingleMessageDto singleMessageDto) throws RemoteException;
}
