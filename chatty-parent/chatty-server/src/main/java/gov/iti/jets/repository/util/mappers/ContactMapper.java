package gov.iti.jets.repository.util.mappers;

import gov.iti.jets.commons.dtos.ContactDto;
import gov.iti.jets.repository.entities.ContactEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UserStatusMapper.class})
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactEntity contactDtoToEntity(ContactDto contactDto);
    ContactDto contactEntityToDto(ContactEntity contactEntity);
}
