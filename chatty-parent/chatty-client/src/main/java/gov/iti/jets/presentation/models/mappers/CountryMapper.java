package gov.iti.jets.presentation.models.mappers;

import gov.iti.jets.commons.dtos.CountryDto;
import gov.iti.jets.presentation.models.CountryModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CountryMapper {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    CountryModel countryDtoToModel(CountryDto userDto);

    List<CountryDto> modelListToDto(List<CountryModel> countryModels);

    List<CountryModel> dtoListToModel(List<CountryDto> countryDtos);

}