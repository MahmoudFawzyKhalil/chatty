package gov.iti.jets.presentation.models.mappers;

import gov.iti.jets.commons.dtos.SingleMessageDto;
import gov.iti.jets.presentation.models.MessageModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SingleMessageMapper {

    SingleMessageMapper INSTANCE = Mappers.getMapper(SingleMessageMapper.class);
    MessageModel dtoToModel(SingleMessageDto singleMessageDto);
}
