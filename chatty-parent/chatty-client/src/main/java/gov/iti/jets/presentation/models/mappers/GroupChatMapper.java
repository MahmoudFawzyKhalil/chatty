package gov.iti.jets.presentation.models.mappers;

import gov.iti.jets.commons.dtos.AddGroupChatDto;
import gov.iti.jets.commons.dtos.ContactDto;
import gov.iti.jets.commons.dtos.GroupChatDto;
import gov.iti.jets.commons.util.mappers.ImageMapper;
import gov.iti.jets.presentation.models.ContactModel;
import gov.iti.jets.presentation.models.GroupChatModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ImageMapper.class})
public interface GroupChatMapper {

    GroupChatMapper INSTANCE = Mappers.getMapper(GroupChatMapper.class);

    GroupChatModel dtoToModel(GroupChatDto groupChatDto);

    AddGroupChatDto modelToAddGroupChatDto(GroupChatModel groupChatModel);

    GroupChatModel AddGroupChatDtoToModel(AddGroupChatDto addGroupChatDto);

    default ObservableList<ContactModel> listToObservableList(List<ContactDto> list) {
        ObservableList<ContactModel> observableList = FXCollections.observableArrayList(ContactModel.extractor());

        for (ContactDto contactDto : list) {
            observableList.add(ContactMapper.INSTANCE.contactDtoToModel(contactDto));
        }

        return observableList;
    }
}
