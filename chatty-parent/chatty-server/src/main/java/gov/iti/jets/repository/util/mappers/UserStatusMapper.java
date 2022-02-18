package gov.iti.jets.repository.util.mappers;

import gov.iti.jets.commons.dtos.UserStatusDto;
import gov.iti.jets.repository.entities.UserStatusEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
 public  interface UserStatusMapper {
    UserStatusMapper INSTANCE = Mappers.getMapper(UserStatusMapper.class);

    @Mapping( target = "userStatusId", source = "statusId")
    @Mapping( target = "userStatusName", source = "statusName")
    UserStatusDto userStatusEntityToDto(UserStatusEntity userStatusEntity);
}
