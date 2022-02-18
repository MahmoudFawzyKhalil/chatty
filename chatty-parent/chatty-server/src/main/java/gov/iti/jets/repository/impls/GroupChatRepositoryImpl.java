package gov.iti.jets.repository.impls;

import gov.iti.jets.repository.ContactRepository;
import gov.iti.jets.repository.GroupChatRepository;
import gov.iti.jets.repository.entities.ContactEntity;
import gov.iti.jets.repository.entities.GroupChatEntity;
import gov.iti.jets.repository.util.ConnectionPool;
import gov.iti.jets.repository.util.RepositoryFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupChatRepositoryImpl implements GroupChatRepository {
    @Override
    public Optional<GroupChatEntity> getById(int id) {
        Optional<GroupChatEntity> optionalGroupChatEntity = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("select group_chat_id,group_chat_name,picture from group_chats where group_chat_id = ?");
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    GroupChatEntity groupChatEntity = new GroupChatEntity();
                    groupChatEntity.setGroupChatId(resultSet.getInt("group_chat_id"));
                    groupChatEntity.setGroupChatName(resultSet.getString("group_chat_name"));
                    groupChatEntity.setGroupChatPicture(resultSet.getString("picture"));
                    Optional<List<ContactEntity>> groupMemberList = getGroupMemberList(resultSet.getInt("group_chat_id"));
                    if(!groupMemberList.isEmpty()){
                        groupChatEntity.setGroupMembersList(groupMemberList.get());
                    }
                    optionalGroupChatEntity = Optional.of(groupChatEntity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalGroupChatEntity;
    }

    @Override
    public Optional<List<ContactEntity>> getGroupMemberList(int id) {
        ContactRepository contactRepository = RepositoryFactory.getInstance().getContactRepository();
        Optional<List<ContactEntity>> optionalGroupMembersList = Optional.empty();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("select user_phone_number from group_chats_users where group_chat_id = ?");
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<ContactEntity> groupMemberList = new ArrayList<>();
                Optional<ContactEntity> contactEntity;
                while(resultSet.next()) {
                    contactEntity = contactRepository.getContact(resultSet.getString("user_phone_number"));
                    if (!contactEntity.isEmpty()) {
                        groupMemberList.add(contactEntity.get());
                    }
                }
                optionalGroupMembersList = Optional.of(groupMemberList);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return optionalGroupMembersList;
    }
}
