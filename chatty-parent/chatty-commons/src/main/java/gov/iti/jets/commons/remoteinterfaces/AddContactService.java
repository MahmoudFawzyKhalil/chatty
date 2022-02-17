package gov.iti.jets.commons.remoteinterfaces;

import gov.iti.jets.commons.dtos.ContactDto;
import gov.iti.jets.commons.dtos.LoginDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AddContactService extends Remote {
    boolean addContacts(ContactDto ContactDto) throws RemoteException;
}
