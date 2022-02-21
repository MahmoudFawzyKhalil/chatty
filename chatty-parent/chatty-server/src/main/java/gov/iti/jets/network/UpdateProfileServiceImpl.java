package gov.iti.jets.network;

import gov.iti.jets.commons.dtos.UpdateProfileDto;
import gov.iti.jets.commons.remoteinterfaces.UpdateProfileService;
import gov.iti.jets.network.util.ImageDecoder;
import gov.iti.jets.network.util.ImageDecoderImpl;
import gov.iti.jets.repository.UserRepository;
import gov.iti.jets.repository.entities.UserEntity;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.repository.util.mappers.UserMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UpdateProfileServiceImpl extends UnicastRemoteObject implements UpdateProfileService {
    UserRepository userRepository = RepositoryFactory.getInstance().getUserRepository();
    private ImageDecoder imageDecoder = new ImageDecoderImpl();


    protected UpdateProfileServiceImpl() throws RemoteException {
    }

    @Override
    public boolean updateProfile(UpdateProfileDto updateProfileDto) {
        UserEntity userEntity = UserMapper.INSTANCE.updateProfileDtoToEntity(updateProfileDto);
        return userRepository.update(userEntity);
    }

    @Override
    public boolean updateProfilePicture(String imageBase64, String phoneNumber) throws RemoteException {
        Path currentRelativePath = Paths.get("");
        String picURL = currentRelativePath.toAbsolutePath().toString() + "/DB/profile-pic/" + phoneNumber + ".bmp";
        try {
            imageDecoder.save(imageBase64, picURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userRepository.updatePicture(picURL, phoneNumber);
    }
}
