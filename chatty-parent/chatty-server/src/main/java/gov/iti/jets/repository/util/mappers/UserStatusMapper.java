package gov.iti.jets.repository.util.mappers;

import gov.iti.jets.commons.dtos.UserStatusDto;
import gov.iti.jets.repository.entities.UserStatusEntity;
import org.mapstruct.factory.Mappers;

public interface UserStatusMapper {
    UserStatusMapper INSTANCE = Mappers.getMapper(UserStatusMapper.class);
    UserStatusDto userStatuesEntityToDto (UserStatusEntity userStatusEntity);
    UserStatusEntity userStatusDtoToEntity (UserStatusDto userStatusDto);
}
