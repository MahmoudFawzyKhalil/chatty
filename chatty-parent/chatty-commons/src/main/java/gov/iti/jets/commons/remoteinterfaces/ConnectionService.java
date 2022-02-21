package gov.iti.jets.commons.remoteinterfaces;

import gov.iti.jets.commons.callback.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ConnectionService extends Remote {
    //TODO FailedToRegisterClientException
    void registerClient(String phoneNumber, Client client) throws RemoteException;
    void unregisterClient(String phoneNumber) throws RemoteException;

    void registerGroups(List<Integer> groupIds, Client client) throws RemoteException;

}
