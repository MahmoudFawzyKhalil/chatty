package gov.iti.jets.network;

import gov.iti.jets.commons.dtos.RegisterDto;
import gov.iti.jets.commons.remoteinterfaces.RegisterService;
import gov.iti.jets.repository.UserRepository;
import gov.iti.jets.repository.entities.UserEntity;
import gov.iti.jets.repository.util.ImageDbUtil;
import gov.iti.jets.repository.util.ImageDecoder;
import gov.iti.jets.repository.util.ImageDecoderImpl;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.repository.util.mappers.UserMapper;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RegisterServiceImpl extends UnicastRemoteObject implements RegisterService {

    private RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private UserRepository userRepository = repositoryFactory.getUserRepository();
    private ImageDecoder imageDecoder = new ImageDecoderImpl();
    private final ImageDbUtil imageDbUtil = ImageDbUtil.getInstance();

    protected RegisterServiceImpl() throws RemoteException {
    }

    @Override
    public boolean register(RegisterDto registerDto) throws RemoteException {
        UserEntity userEntity = UserMapper.INSTANCE.registerDtoToEntity(registerDto);
        try {
            String picName = registerDto.getPhoneNumber() + ".bmp";
            String picURL = imageDbUtil.getProfileDbPath() + picName;
            imageDecoder.save(registerDto.getProfilePicture(), picURL);
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
