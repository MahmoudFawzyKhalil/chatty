package gov.iti.jets.commons.remoteinterfaces;

import gov.iti.jets.commons.dtos.LoginDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginService extends Remote {
    boolean login(LoginDto loginDto) throws RemoteException;
}
