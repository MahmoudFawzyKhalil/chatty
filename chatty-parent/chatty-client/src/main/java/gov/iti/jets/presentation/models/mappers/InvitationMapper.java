package gov.iti.jets.presentation.models.mappers;

import gov.iti.jets.commons.dtos.InvitationDto;
import gov.iti.jets.presentation.models.InvitationModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ContactMapper.class)
public interface InvitationMapper {
    InvitationMapper INSTANCE = Mappers.getMapper( InvitationMapper.class );

    @Mapping( target = "contactModel", source = "contactDto")
    InvitationModel dtoToModel( InvitationDto dto );
}
