package gov.iti.jets.repository.impls;

import gov.iti.jets.repository.ContactRepository;
import gov.iti.jets.repository.GroupChatRepository;
import gov.iti.jets.repository.entities.ContactEntity;
import gov.iti.jets.repository.entities.GroupChatEntity;
import gov.iti.jets.repository.util.ConnectionPool;
import gov.iti.jets.repository.util.RepositoryFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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
                    List<ContactEntity> groupMemberList = getGroupMemberList(resultSet.getInt("group_chat_id"));
                    groupChatEntity.setGroupMembersList(groupMemberList);
                    optionalGroupChatEntity = Optional.of(groupChatEntity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalGroupChatEntity;
    }

    @Override
    public List<ContactEntity> getGroupMemberList(int id) {
        ContactRepository contactRepository = RepositoryFactory.getInstance().getContactRepository();
        List<ContactEntity> groupMembersList = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("select user_phone_number from group_chats_users where group_chat_id = ?");
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                Optional<ContactEntity> contactEntity;
                while (resultSet.next()) {
                    contactEntity = contactRepository.getContact(resultSet.getString("user_phone_number"));
                    if (!contactEntity.isEmpty()) {
                        groupMembersList.add(contactEntity.get());
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return groupMembersList;
    }

    @Override
    public List<GroupChatEntity> getGroupChats(String phoneNumber) {
        List<GroupChatEntity> groupChatList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("select group_chat_id from group_chats_users where user_phone_number = ?");
        ) {
            statement.setString(1, phoneNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                Optional<GroupChatEntity> groupChatEntity;
                while (resultSet.next()) {
                    groupChatEntity = getById(resultSet.getInt("group_chat_id"));
                    if (!groupChatEntity.isEmpty()) {
                        groupChatList.add(groupChatEntity.get());
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return groupChatList;
    }

    @Override
    public int addGroup(GroupChatEntity groupChatEntity) {
        /*TODO
         *
         * pic
         *
         * */
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into group_chats (group_chat_name) values (?) ", Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, groupChatEntity.getGroupChatName());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 1) {

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int groupId = generatedKeys.getInt(1);
                        if (addMembers(groupId, groupChatEntity.getGroupMembersList())) {
                            return groupId;
                        } else {
                            deleteGroup(groupId);
                            return -1;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean addMembers(int id, List<ContactEntity> groupMembersList) {
        if (isFoundById(id)) {
            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("insert into group_chats_users (group_chat_id, user_phone_number) values(?, ?) ")) {
                connection.setAutoCommit(false);

                for (ContactEntity contactEntity : groupMembersList) {
                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, contactEntity.getPhoneNumber());
                    preparedStatement.addBatch();
                }
                int[] effectedRows = preparedStatement.executeBatch();
                long countNotAdded = Arrays.stream(effectedRows).filter(el -> el != 1).count();
                preparedStatement.clearBatch();
                connection.setAutoCommit(true);
                return countNotAdded == 0;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean deleteGroup(int id) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from group_chats where group_chat_id= ? ");
        ) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isFoundById(int id) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("select group_chat_id from group_chats where group_chat_id = ? ");
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
