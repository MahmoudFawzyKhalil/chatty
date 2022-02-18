package gov.iti.jets.commons.remoteinterfaces;

import gov.iti.jets.commons.callback.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConnectionService extends Remote {
    //TODO FailedToRegisterClientException
    void registerClient(String phoneNumber, Client client) throws RemoteException;
    void unregisterClient(String phoneNumber) throws RemoteException;
}
