package gov.iti.jets.repository;

import gov.iti.jets.repository.entities.ContactEntity;
import gov.iti.jets.repository.entities.GroupChatEntity;

import java.util.List;
import java.util.Optional;

public interface GroupChatRepository {
    Optional<GroupChatEntity> getById(int id);
    List<ContactEntity> getGroupMemberList(int id);
}
