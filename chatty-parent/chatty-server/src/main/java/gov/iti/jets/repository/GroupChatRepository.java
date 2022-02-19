package gov.iti.jets.repository;

import gov.iti.jets.repository.entities.ContactEntity;
import gov.iti.jets.repository.entities.GroupChatEntity;

import java.util.List;
import java.util.Optional;

public interface GroupChatRepository {
    Optional<GroupChatEntity> getById(int id);

    List<ContactEntity> getGroupMemberList(int id);

    List<GroupChatEntity> getGroupChats(String phoneNumber);

    int addGroup(GroupChatEntity groupChatEntity);

    boolean addMembers(int id, List<ContactEntity> groupMembersList);

    boolean deleteGroup(int id);

    boolean isFoundById(int id);
}
