package gov.iti.jets.repository.util.mappers;

import gov.iti.jets.commons.dtos.SingleMessageDto;
import gov.iti.jets.repository.entities.SingleMessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SingleMessageMapper {
    SingleMessageMapper INSTANCE = Mappers.getMapper(SingleMessageMapper.class);

    SingleMessageEntity dtoToEntity(SingleMessageDto singleMessageDto);
    SingleMessageDto entityToDto(SingleMessageEntity singleMessageEntity);

    List<SingleMessageEntity> dtoListToEntityList (List<SingleMessageDto> singleMessageDtoList);
    List<SingleMessageDto> entityListToDtoList(List<SingleMessageEntity> singleMessageEntityList);

}
