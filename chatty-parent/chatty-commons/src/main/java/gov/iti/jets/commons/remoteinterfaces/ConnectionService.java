package gov.iti.jets.commons.remoteinterfaces;

import gov.iti.jets.commons.callback.Client;
import gov.iti.jets.commons.dtos.StatusNotificationDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ConnectionService extends Remote {
    //TODO FailedToRegisterClientException
    void registerClient(String phoneNumber, Client client) throws RemoteException;
    void unregisterClient(String phoneNumber) throws RemoteException;
    void notifyOthersOfStatusUpdate( StatusNotificationDto statusNotificationDto, List<String> contactsToNotifyPhoneNumbers ) throws  RemoteException;
}
