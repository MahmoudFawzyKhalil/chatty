package gov.iti.jets.presentation.models.mappers;

import gov.iti.jets.commons.dtos.RegisterDto;
import gov.iti.jets.commons.util.mappers.ImageMapper;
import gov.iti.jets.presentation.models.RegisterModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ImageMapper.class)
public interface RegisterMapper {
    RegisterMapper INSTANCE = Mappers.getMapper(RegisterMapper.class);

    RegisterDto registerModelToDto(RegisterModel registerModel);
}
