package gov.iti.jets.presentation.models.mappers;

import gov.iti.jets.commons.dtos.GroupMessageDto;
import gov.iti.jets.presentation.models.MessageModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GroupMessageMapper {

    GroupMessageMapper INSTANCE = Mappers.getMapper(GroupMessageMapper.class);
    MessageModel dtoToModel(GroupMessageDto groupMessageDto);
    List<MessageModel> dtoListToModelList(List<GroupMessageDto> groupMessageDtoList);
}