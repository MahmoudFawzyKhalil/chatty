package gov.iti.jets.network;

import gov.iti.jets.commons.dtos.RegisterDto;
import gov.iti.jets.commons.remoteinterfaces.RegisterService;
import gov.iti.jets.repository.UserRepository;
import gov.iti.jets.repository.impls.UserRepositoryImpl;

import java.rmi.RemoteException;

public class RegisterServiceImpl implements RegisterService {

    private UserRepository userRepository = new UserRepositoryImpl();
    @Override
    public boolean register(RegisterDto registerDto) throws RemoteException {
        return false;
    }
}
