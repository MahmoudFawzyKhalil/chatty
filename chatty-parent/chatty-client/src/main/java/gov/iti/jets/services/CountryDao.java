package gov.iti.jets.services;

import gov.iti.jets.commons.dtos.CountryDto;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public interface CountryDao {
    List<CountryDto>getAll() throws NotBoundException, RemoteException;
}
