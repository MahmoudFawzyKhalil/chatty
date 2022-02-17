package gov.iti.jets.services.util;

import gov.iti.jets.commons.remoteinterfaces.ConnectionService;
import gov.iti.jets.commons.remoteinterfaces.LoginService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServiceFactory {
    private static final ServiceFactory registryFactory = new ServiceFactory();
    private Registry registry;

    private static LoginService loginService;
    private static ConnectionService connectionService;

    public static ServiceFactory getInstance() {
        return registryFactory;
    }

    private ServiceFactory() {
        try {
            this.registry = LocateRegistry.getRegistry(1099);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public LoginService getLoginService() throws NotBoundException, RemoteException {
        if (loginService == null) {
            loginService = (LoginService) registry.lookup("LoginService");

        }
        return loginService;
    }

    public ConnectionService getConnectionService() throws NotBoundException, RemoteException {
        if(connectionService == null){
            connectionService = (ConnectionService) registry.lookup("ConnectionService");
        }
        return connectionService;
    }
}
