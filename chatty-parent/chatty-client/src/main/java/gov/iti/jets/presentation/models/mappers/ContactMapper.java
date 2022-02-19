package gov.iti.jets.presentation.models.mappers;

import gov.iti.jets.commons.dtos.ContactDto;
import gov.iti.jets.presentation.models.ContactModel;
import gov.iti.jets.presentation.models.MessageModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ImageMapper.class, UserStatusMapper.class})
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper( ContactMapper.class );

    ContactModel contactDtoToModel( ContactDto contactDto );
}
