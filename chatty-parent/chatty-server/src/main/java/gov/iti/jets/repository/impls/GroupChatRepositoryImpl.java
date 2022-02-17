package gov.iti.jets.repository.impls;

import gov.iti.jets.repository.GroupChatRepository;
import gov.iti.jets.repository.entities.GroupChatEntity;

import java.util.List;
import java.util.Optional;

public class GroupChatRepositoryImpl implements GroupChatRepository {
    @Override
    public Optional<GroupChatEntity> getById(int id) {
       return Optional.empty();
    }

    @Override
    public List<String> getAllMembersPhoneNumbers(int id) {
        return null;
    }
}
