package gov.iti.jets.services.impls;

import gov.iti.jets.commons.callback.Client;
import gov.iti.jets.commons.remoteinterfaces.ConnectionService;
import gov.iti.jets.services.ConnectionDao;
import gov.iti.jets.services.util.ServiceFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ConnectionDaoImpl implements ConnectionDao {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    @Override
    public void registerClient(String phoneNumber, Client client) throws NotBoundException,RemoteException {
        ConnectionService connectionService = serviceFactory.getConnectionService();
        connectionService.registerClient(phoneNumber,client);
    }

    @Override
    public void unregisterClient(String phoneNumber) throws NotBoundException,RemoteException{
        ConnectionService connectionService = serviceFactory.getConnectionService();
        connectionService.unregisterClient(phoneNumber);
    }
}
