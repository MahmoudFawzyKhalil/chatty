package gov.iti.jets.presentation.models.mappers;

import gov.iti.jets.commons.dtos.ContactDto;
import gov.iti.jets.presentation.models.ContactModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper( ContactMapper.class );

    ContactModel contactDtoToModel( ContactDto contactDto );
}
