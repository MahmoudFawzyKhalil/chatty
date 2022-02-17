package gov.iti.jets.presentation.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ImageMapper.class)
public interface RegisterMapper {
    RegisterMapper INSTANCE = Mappers.getMapper(RegisterMapper.class);

   // RegisterDto registerModelToDto(RegisterModel registerModel);
}
