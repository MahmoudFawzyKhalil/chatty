package gov.iti.jets.network;


import gov.iti.jets.commons.dtos.LoginDto;
import gov.iti.jets.commons.remoteinterfaces.LoginService;
import gov.iti.jets.repository.UserRepository;
import gov.iti.jets.repository.util.RepositoryFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginServiceImpl extends UnicastRemoteObject implements LoginService {
    private RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private UserRepository userRepository = repositoryFactory.getUserRepository();

    protected LoginServiceImpl() throws RemoteException {
    }

    @Override
    public boolean login(LoginDto loginDto) throws RemoteException {
        return userRepository.isFoundByPhoneNumberAndPassword(loginDto.getPhoneNumber(), loginDto.getPassword());
    }
}
