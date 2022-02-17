package gov.iti.jets.presentation.models.mappers;

import gov.iti.jets.commons.dtos.CountryDto;
import gov.iti.jets.presentation.models.CountryModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper( CountryMapper.class );
    CountryModel dtoToModel( CountryDto dto );
}
