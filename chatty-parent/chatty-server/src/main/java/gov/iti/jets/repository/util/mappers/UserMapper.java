package gov.iti.jets.repository.util.mappers;

import gov.iti.jets.commons.dtos.RegisterDto;
import gov.iti.jets.commons.dtos.UpdateProfileDto;
import gov.iti.jets.commons.dtos.UserDto;
import gov.iti.jets.commons.util.mappers.ImageMapper;
import gov.iti.jets.repository.entities.UserEntity;
import javafx.scene.image.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.io.File;

@Mapper(uses = {CountryMapper.class, ContactMapper.class, GroupChatMapper.class, InvitationMapper.class, UserStatusMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

//    String DEFAULT_PIC_URL = Paths.get("").toAbsolutePath().toString() + "/DB/profile-pic/user.bmp";

    UserEntity userDtoToEntity(UserDto userDto);

    @Mapping(source = "userPicture", target = "profilePicture", qualifiedByName = "userPicDto")
    UserDto userEntityToDto(UserEntity userEntity);

    //    @Mapping(source = "profilePicture",target = "userPicture")
    UserEntity registerDtoToEntity(RegisterDto userDto);

    UserEntity updateProfileDtoToEntity(UpdateProfileDto updateProfileDto);

    @Named("userPicDto")
    default String userPictureUserEntityToDto(String userPicPath) {
        if (userPicPath == null) {
            return "";
        }
        File file = new File(userPicPath);
        if (userPicPath.isEmpty() || !file.exists()) {
            return "";
        } else {
            Image image = new Image(userPicPath);
            return ImageMapper.getInstance().imageToEncodedString(image);
        }
    }
}
