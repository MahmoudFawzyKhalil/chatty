package gov.iti.jets.repository.util.mappers;

import gov.iti.jets.commons.dtos.CountryDto;
import gov.iti.jets.repository.entities.CountryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CountryMapper {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    CountryEntity countryDtoToEntity(CountryDto userDto);

    List<CountryDto> entityListToDto(List<CountryEntity> countryEntities);

    List<CountryEntity> dtoListToEntity(List<CountryDto> countryDtos);

}
