package gov.iti.jets.commons.remoteinterfaces;

import gov.iti.jets.commons.dtos.RegisterDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RegisterService extends Remote {
    boolean register(RegisterDto registerDto) throws RemoteException;
    boolean isFoundByPhoneNumber(String phoneNumber) throws  RemoteException;
    boolean isFoundByEmail(String email) throws  RemoteException;
}
