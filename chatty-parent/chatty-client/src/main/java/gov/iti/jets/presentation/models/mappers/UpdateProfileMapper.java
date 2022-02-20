package gov.iti.jets.presentation.models.mappers;

import gov.iti.jets.commons.dtos.UpdateProfileDto;
import gov.iti.jets.commons.util.mappers.ImageMapper;
import gov.iti.jets.presentation.models.UpdateProfileModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ImageMapper.class)
public interface UpdateProfileMapper {
    UpdateProfileMapper INSTANCE = Mappers.getMapper(UpdateProfileMapper.class);

    UpdateProfileDto updateProfileModelToDto(UpdateProfileModel updateProfileModel);
}
