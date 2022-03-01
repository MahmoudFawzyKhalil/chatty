package gov.iti.jets.repository.util.mappers;

import gov.iti.jets.commons.dtos.GroupMessageDto;
import gov.iti.jets.commons.dtos.SingleMessageDto;
import gov.iti.jets.repository.entities.GroupMessageEntity;
import gov.iti.jets.repository.entities.SingleMessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GroupMessageMapper {
    GroupMessageMapper INSTANCE = Mappers.getMapper(GroupMessageMapper.class);

    GroupMessageEntity dtoToEntity(GroupMessageDto groupMessageDto);
    GroupMessageDto entityToDto(GroupMessageEntity groupMessageEntity);

    List<GroupMessageEntity> dtoListToEntityList (List<GroupMessageDto> groupMessageDtoList);
    List<GroupMessageDto> entityListToDtoList(List<GroupMessageEntity> groupMessageEntityList);
}