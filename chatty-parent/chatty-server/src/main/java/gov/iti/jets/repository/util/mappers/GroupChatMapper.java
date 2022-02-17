package gov.iti.jets.repository.util.mappers;

import gov.iti.jets.commons.dtos.GroupChatDto;
import gov.iti.jets.repository.entities.GroupChatEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public class GroupChatMapper {
    GroupChatMapper INSTANCE = Mappers.getMapper(GroupChatMapper.class);

    GroupChatEntity groupChatDtoToEntity(GroupChatDto groupChatDto);
    GroupChatDto groupChatEntityToDto(GroupChatEntity groupChatEntity);

}
