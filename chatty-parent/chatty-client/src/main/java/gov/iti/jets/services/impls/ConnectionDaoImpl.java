package gov.iti.jets.services.impls;

import gov.iti.jets.commons.callback.Client;
import gov.iti.jets.commons.dtos.StatusNotificationDto;
import gov.iti.jets.commons.remoteinterfaces.ConnectionService;
import gov.iti.jets.services.ConnectionDao;
import gov.iti.jets.services.util.ServiceFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class ConnectionDaoImpl implements ConnectionDao {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public void registerClient( String phoneNumber, Client client ) throws NotBoundException, RemoteException {
        ConnectionService connectionService = serviceFactory.getConnectionService();
        connectionService.registerClient( phoneNumber, client );
    }

    @Override
    public void unregisterClient( String phoneNumber ) throws NotBoundException, RemoteException {
        ConnectionService connectionService = serviceFactory.getConnectionService();
        if (connectionService != null){
            connectionService.unregisterClient( phoneNumber );
        }
    }

    @Override
    public void notifyOthersOfStatusUpdate( StatusNotificationDto statusNotificationDto, List<String> contactsToNotifyPhoneNumbers ) throws NotBoundException, RemoteException {
        ConnectionService connectionService = serviceFactory.getConnectionService();
        connectionService.notifyOthersOfStatusUpdate( statusNotificationDto, contactsToNotifyPhoneNumbers );
    }

    @Override
    public List<String> getOfflineContacts( List<String> contactsPhoneNumbers ) throws NotBoundException, RemoteException {
        ConnectionService connectionService = serviceFactory.getConnectionService();
        return connectionService.getOfflineContacts( contactsPhoneNumbers );
    }

    @Override
    public void registerGroups(List<Integer> groupIds, Client client) throws NotBoundException,RemoteException{
        ConnectionService connectionService = serviceFactory.getConnectionService();
        connectionService.registerGroups(groupIds, client);
    }

}
