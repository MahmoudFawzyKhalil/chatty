package gov.iti.jets.services;

import gov.iti.jets.commons.dtos.LoginDto;
import gov.iti.jets.presentation.datasaved.LoginData;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Optional;

public interface LoginDao {
    boolean isAuthenticated(LoginDto loginDto) throws NotBoundException, RemoteException;
    Optional<LoginData> getLoginDate();
    void save(LoginData loginData);
}
