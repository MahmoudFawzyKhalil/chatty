package gov.iti.jets.network;

import gov.iti.jets.commons.dtos.AddContactDto;
import gov.iti.jets.commons.dtos.LoginDto;
import gov.iti.jets.commons.remoteinterfaces.AddContactService;
import gov.iti.jets.commons.remoteinterfaces.LoginService;
import gov.iti.jets.repository.UserRepository;
import gov.iti.jets.repository.util.RepositoryFactory;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AddContactServiceImpl extends UnicastRemoteObject implements AddContactService {
    private RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private UserRepository userRepository = repositoryFactory.getUserRepository();

    protected AddContactServiceImpl() throws RemoteException {
    }

    @Override
    public boolean addContacts(AddContactDto addContactDto) throws RemoteException {
        return userRepository.addContacts(addContactDto);
    }
}
