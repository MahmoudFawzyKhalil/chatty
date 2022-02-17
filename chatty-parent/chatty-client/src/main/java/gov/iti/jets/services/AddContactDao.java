package gov.iti.jets.services;

import gov.iti.jets.commons.dtos.ContactDto;
import gov.iti.jets.commons.dtos.LoginDto;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface AddContactDao {
    boolean addContacts(ContactDto ContactDto) throws NotBoundException, RemoteException;
}
