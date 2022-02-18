package gov.iti.jets.repository.util.mappers;

import gov.iti.jets.commons.dtos.RegisterDto;
import gov.iti.jets.commons.dtos.UpdateProfileDto;
import gov.iti.jets.repository.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity registerDtoToEntity(RegisterDto userDto);

    UserEntity updateProfileDtoToEntity(UpdateProfileDto updateProfileDto);
}
