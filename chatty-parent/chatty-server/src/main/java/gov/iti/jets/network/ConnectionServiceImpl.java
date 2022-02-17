package gov.iti.jets.network;

import gov.iti.jets.commons.callback.Client;
import gov.iti.jets.commons.remoteinterfaces.ConnectionService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ConnectionServiceImpl extends UnicastRemoteObject implements ConnectionService {

    Clients clients = Clients.getInstance();
    protected ConnectionServiceImpl() throws RemoteException {
    }

    @Override
    public void registerClient(String phoneNumber, Client client) throws RemoteException {
        clients.addClient(phoneNumber,client);
        //client.loadUserModel(client.);
    }

    @Override
    public void unregisterClient(String phoneNumber) throws RemoteException {
        clients.removeClient(phoneNumber);
    }
}
