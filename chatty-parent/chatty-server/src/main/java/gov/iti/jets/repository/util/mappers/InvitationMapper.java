package gov.iti.jets.repository.util.mappers;

import gov.iti.jets.commons.dtos.InvitationDto;
import gov.iti.jets.repository.entities.ContactEntity;
import gov.iti.jets.repository.entities.InvitationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ContactEntity.class})
public interface InvitationMapper {
    InvitationMapper INSTANCE = Mappers.getMapper(InvitationMapper.class);

    InvitationEntity invitationDtoToEntity(InvitationDto invitationDto);
    InvitationDto invitationEntityToDto(InvitationEntity invitationEntity);
}
