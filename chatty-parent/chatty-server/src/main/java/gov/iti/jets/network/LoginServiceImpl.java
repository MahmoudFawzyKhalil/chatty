package gov.iti.jets.network;


import gov.iti.jets.commons.dtos.LoginDto;
import gov.iti.jets.commons.remoteinterfaces.LoginService;
import gov.iti.jets.repository.UserRepository;
import gov.iti.jets.repository.impls.UserRepositoryImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginServiceImpl extends UnicastRemoteObject implements LoginService {
    private UserRepository userRepository = new UserRepositoryImpl();

    protected LoginServiceImpl() throws RemoteException {
    }

    @Override
    public boolean login(LoginDto loginDto) throws RemoteException {
        return userRepository.isFoundByPhoneNumberAndPassword(loginDto.getPhoneNumber(), loginDto.getPassword());
    }
}
