package gov.iti.jets.network;

import gov.iti.jets.commons.dtos.RegisterDto;
import gov.iti.jets.commons.remoteinterfaces.RegisterService;
import gov.iti.jets.repository.UserRepository;
import gov.iti.jets.repository.entities.UserEntity;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.repository.util.mappers.UserMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RegisterServiceImpl extends UnicastRemoteObject implements RegisterService {

    private RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private UserRepository userRepository = repositoryFactory.getUserRepository();

    protected RegisterServiceImpl() throws RemoteException {
    }

    @Override
    public boolean register(RegisterDto registerDto) throws RemoteException {
        UserEntity userEntity = UserMapper.INSTANCE.registerDtoToEntity(registerDto);
        return userRepository.addUser(userEntity);
    }
}
