package gov.iti.jets.commons.remoteinterfaces;

import gov.iti.jets.commons.dtos.CountryDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CountryService extends Remote {
    List<CountryDto> getAll() throws RemoteException;
}
