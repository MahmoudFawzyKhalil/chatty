package gov.iti.jets.services;

import gov.iti.jets.commons.dtos.AddContactDto;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface AddContactDao {
    boolean addContacts( AddContactDto AddContactDto ) throws NotBoundException, RemoteException;
    boolean doUsersExist( AddContactDto addContactDto ) throws NotBoundException, RemoteException;
    boolean didISendAnInvitationBefore (AddContactDto addContactDto) throws NotBoundException, RemoteException;
}
