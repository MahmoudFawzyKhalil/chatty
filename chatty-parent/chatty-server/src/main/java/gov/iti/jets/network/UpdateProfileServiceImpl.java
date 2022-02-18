package gov.iti.jets.network;

import gov.iti.jets.commons.dtos.UpdateProfileDto;
import gov.iti.jets.commons.remoteinterfaces.UpdateProfileService;
import gov.iti.jets.repository.UserRepository;
import gov.iti.jets.repository.entities.UserEntity;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.repository.util.mappers.UserMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UpdateProfileServiceImpl extends UnicastRemoteObject implements UpdateProfileService {
    UserRepository userRepository = RepositoryFactory.getInstance().getUserRepository();

    protected UpdateProfileServiceImpl() throws RemoteException {
    }

    @Override
    public boolean updateProfile(UpdateProfileDto updateProfileDto) {
        UserEntity userEntity= UserMapper.INSTANCE.updateProfileDtoToEntity(updateProfileDto);
        return userRepository.update(userEntity);
    }
}
