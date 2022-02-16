package gov.iti.jets.services;

import gov.iti.jets.commons.dtos.LoginDto;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface LoginDao {
    boolean isAuthenticated(LoginDto loginDto) throws NotBoundException, RemoteException;
}
