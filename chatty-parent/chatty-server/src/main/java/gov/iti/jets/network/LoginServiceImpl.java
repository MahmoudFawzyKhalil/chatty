package gov.iti.jets.network;


import gov.iti.jets.commons.remoteinterfaces.LoginService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginServiceImpl extends UnicastRemoteObject implements LoginService {

    protected LoginServiceImpl() throws RemoteException {
    }

    @Override
    public boolean login() throws RemoteException {
        return false;
    }
}
