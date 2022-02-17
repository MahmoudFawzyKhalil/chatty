package gov.iti.jets.presentation.models.mappers;

import gov.iti.jets.commons.dtos.UserDto;
import gov.iti.jets.presentation.models.UserModel;
import org.mapstruct.factory.Mappers;

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserModel userDtoToModel(UserDto userDto );
}
