package gov.iti.jets.network;

import gov.iti.jets.commons.dtos.AddContactDto;
import gov.iti.jets.commons.remoteinterfaces.AddContactService;
import gov.iti.jets.repository.UserRepository;
import gov.iti.jets.repository.util.RepositoryFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AddContactServiceImpl extends UnicastRemoteObject implements AddContactService {
    private transient RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private transient UserRepository userRepository = repositoryFactory.getUserRepository();

    public AddContactServiceImpl() throws RemoteException {
    }

    @Override
    public boolean addContacts(AddContactDto addContactDto) throws RemoteException {
        return userRepository.addContacts(addContactDto);
    }
}
