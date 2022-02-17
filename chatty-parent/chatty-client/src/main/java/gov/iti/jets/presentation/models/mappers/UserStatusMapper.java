package gov.iti.jets.presentation.models.mappers;

import gov.iti.jets.commons.dtos.UserStatusDto;
import gov.iti.jets.presentation.models.UserStatusModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserStatusMapper {

    UserStatusMapper INSTANCE = Mappers.getMapper( UserStatusMapper.class );
    UserStatusModel dtoToModel( UserStatusDto dto );
}
