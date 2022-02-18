package gov.iti.jets.repository.util.mappers;

import gov.iti.jets.commons.dtos.InvitationDto;
import gov.iti.jets.repository.entities.InvitationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ContactMapper.class})
public interface InvitationMapper {
    InvitationMapper INSTANCE = Mappers.getMapper(InvitationMapper.class);

    @Mapping( target = "contactEntity", source = "contactDto")
    InvitationEntity invitationDtoToEntity(InvitationDto invitationDto);

    @Mapping( target = "contactDto", source = "contactEntity")
    InvitationDto invitationEntityToDto(InvitationEntity invitationEntity);
}
