package gov.iti.jets.network;


import gov.iti.jets.commons.dtos.LoginDto;
import gov.iti.jets.commons.remoteinterfaces.LoginService;
import gov.iti.jets.repository.UserRepository;
import gov.iti.jets.repository.util.RepositoryFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class LoginServiceImpl extends UnicastRemoteObject implements LoginService {
    private transient RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private transient UserRepository userRepository = repositoryFactory.getUserRepository();
    private transient BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    protected LoginServiceImpl() throws RemoteException {
    }

    @Override
    public boolean login(LoginDto loginDto) throws RemoteException {
        boolean found = userRepository.isFoundByPhoneNumber(loginDto.getPhoneNumber());
        if (found) {
            String hashedPassword = userRepository.getPasswordByPhoneNumber(loginDto.getPhoneNumber());
            return encoder.matches(loginDto.getPassword(), hashedPassword);
        }
        return false;
    }
}
