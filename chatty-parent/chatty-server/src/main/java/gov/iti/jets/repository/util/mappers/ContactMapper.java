package gov.iti.jets.repository.util.mappers;

import gov.iti.jets.commons.dtos.ContactDto;
import gov.iti.jets.commons.util.mappers.ImageMapper;
import gov.iti.jets.repository.entities.ContactEntity;
import javafx.scene.image.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UserStatusMapper.class})
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactEntity contactDtoToEntity(ContactDto contactDto);
    @Mapping(source = "profilePicture", target = "profilePicture", qualifiedByName = "contactPicDto")
    ContactDto contactEntityToDto(ContactEntity contactEntity);

    @Named("contactPicDto")
    default String userPictureContactEntityToDto(String picPath) {

        if (picPath == null || picPath.isEmpty()) {
            return "";
        } else {
            Image image = new Image(picPath);
            return ImageMapper.getInstance().imageToEncodedString(image);
        }
    }
}
