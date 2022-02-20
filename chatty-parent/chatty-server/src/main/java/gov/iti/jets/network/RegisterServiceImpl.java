package gov.iti.jets.network;

import gov.iti.jets.commons.dtos.RegisterDto;
import gov.iti.jets.commons.remoteinterfaces.RegisterService;
import gov.iti.jets.network.util.DecodeImage;
import gov.iti.jets.network.util.DecodeImageImpl;
import gov.iti.jets.repository.UserRepository;
import gov.iti.jets.repository.entities.UserEntity;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.repository.util.mappers.UserMapper;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RegisterServiceImpl extends UnicastRemoteObject implements RegisterService {

    private RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private UserRepository userRepository = repositoryFactory.getUserRepository();
    private DecodeImage decodeImage = new DecodeImageImpl();

    protected RegisterServiceImpl() throws RemoteException {
    }

    @Override
    public boolean register(RegisterDto registerDto) throws RemoteException {
        UserEntity userEntity = UserMapper.INSTANCE.registerDtoToEntity(registerDto);
        try {
            String picURL = "DB/profile-pic/" + registerDto.getPhoneNumber() + ".bmp";
            decodeImage.save(registerDto.getProfilePicture(), picURL);
            userEntity.setUserPicture(picURL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userRepository.addUser(userEntity);
    }

    @Override
    public boolean isFoundByPhoneNumber(String phoneNumber) throws RemoteException {
        return userRepository.isFoundByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean isFoundByEmail(String email) throws RemoteException {
        return userRepository.isFoundByEmail(email);
    }
}
