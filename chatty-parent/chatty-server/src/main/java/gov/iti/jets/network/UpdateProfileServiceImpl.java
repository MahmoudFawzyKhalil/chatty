package gov.iti.jets.network;

import gov.iti.jets.commons.dtos.UpdateProfileDto;
import gov.iti.jets.commons.dtos.UpdateProfilePicDto;
import gov.iti.jets.commons.remoteinterfaces.UpdateProfileService;
import gov.iti.jets.repository.ContactRepository;
import gov.iti.jets.repository.UserRepository;
import gov.iti.jets.repository.entities.ContactEntity;
import gov.iti.jets.repository.entities.UserEntity;
import gov.iti.jets.repository.util.ImageDbUtil;
import gov.iti.jets.repository.util.ImageDecoder;
import gov.iti.jets.repository.util.ImageDecoderImpl;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.repository.util.mappers.UserMapper;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateProfileServiceImpl extends UnicastRemoteObject implements UpdateProfileService {
    private final transient Clients clients = Clients.getInstance();
    private final transient UserRepository userRepository = RepositoryFactory.getInstance().getUserRepository();
    private final transient ContactRepository contactRepository = RepositoryFactory.getInstance().getContactRepository();
    private final transient ImageDecoder imageDecoder = new ImageDecoderImpl();
    private final ImageDbUtil imageDbUtil = ImageDbUtil.getInstance();


    protected UpdateProfileServiceImpl() throws RemoteException {
    }

    @Override
    public boolean updateProfile(UpdateProfileDto updateProfileDto) throws RemoteException{
        UserEntity userEntity = UserMapper.INSTANCE.updateProfileDtoToEntity(updateProfileDto);
        boolean isUpdated = userRepository.update(userEntity);
        if(isUpdated){
            List<ContactEntity> contacts = contactRepository.getUserContacts(updateProfileDto.getPhoneNumber());
            List<String> phoneNumbers = contacts.stream().map(ContactEntity::getPhoneNumber).collect(Collectors.toList());
            clients.notifyContactProfileChange(phoneNumbers, updateProfileDto);
        }
        return isUpdated;
    }

    @Override
    public boolean updateProfilePicture(UpdateProfilePicDto updateProfilePicDto) throws RemoteException {
        String picName = updateProfilePicDto.getPhoneNumber() + ".bmp";
        String picURL = imageDbUtil.getProfileDbPath() + picName;
        try {
            imageDecoder.save(updateProfilePicDto.getPictureBase46(), picURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean isUpdated = userRepository.updatePicture(picURL, updateProfilePicDto.getPhoneNumber());
        if (isUpdated) {
            List<ContactEntity> contacts = contactRepository.getUserContacts(updateProfilePicDto.getPhoneNumber());
            List<String> phoneNumbers = contacts.stream().map(ContactEntity::getPhoneNumber).collect(Collectors.toList());
            clients.notifyContactPicChange(phoneNumbers, updateProfilePicDto);
        }
        return isUpdated;
    }
}
