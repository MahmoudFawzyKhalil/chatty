package gov.iti.jets.repository.util.mappers;

import gov.iti.jets.commons.dtos.AddGroupChatDto;
import gov.iti.jets.commons.dtos.GroupChatDto;
import gov.iti.jets.commons.util.mappers.ImageMapper;
import gov.iti.jets.repository.entities.GroupChatEntity;
import javafx.scene.image.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.io.File;

@Mapper(uses = {ContactMapper.class})
public interface GroupChatMapper {
    GroupChatMapper INSTANCE = Mappers.getMapper(GroupChatMapper.class);

    GroupChatEntity groupChatDtoToEntity(GroupChatDto groupChatDto);

    GroupChatEntity addGroupChatDtoToEntity(AddGroupChatDto addGroupChatDto);

    @Mapping(source = "groupChatPicture", target = "groupChatPicture", qualifiedByName = "groupPicDto")
    GroupChatDto groupChatEntityToDto(GroupChatEntity groupChatEntity);

    @Named("groupPicDto")
    default String groupPictureGroupEntityToDto(String groupPicPath) {
        if (groupPicPath == null) {
            return "";
        }
        File file = new File(groupPicPath);
        if (groupPicPath.isEmpty() || !file.exists()) {
            return "";
        } else {
            Image image = new Image(groupPicPath);
            return ImageMapper.getInstance().imageToEncodedString(image);
        }
    }

}
