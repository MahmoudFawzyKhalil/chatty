package gov.iti.jets.services;

import gov.iti.jets.commons.dtos.RegisterDto;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface RegisterDao {
    boolean register(RegisterDto registerDto) throws NotBoundException, RemoteException;
}
