package gov.iti.jets.repository.impls;

import gov.iti.jets.repository.GroupChatRepository;
import gov.iti.jets.repository.entities.ContactEntity;
import gov.iti.jets.repository.entities.GroupChatEntity;
import gov.iti.jets.repository.rowsetmappers.ContactEntityMapper;
import gov.iti.jets.repository.rowsetmappers.GroupChatEntityMapper;
import gov.iti.jets.repository.rowsetmappers.RowMapper;
import gov.iti.jets.repository.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GroupChatRepositoryImpl implements GroupChatRepository {
    private final RowMapper<GroupChatEntity> groupEntityRowMapper = new GroupChatEntityMapper();
    private final RowMapper<ContactEntity> contactEntityRowMapper = new ContactEntityMapper();

    @Override
    public Optional<GroupChatEntity> getById(int id) {
        String query = "select group_chat_id,group_chat_name,picture from group_chats where group_chat_id = ?";
        Optional<GroupChatEntity> optionalGroupChatEntity = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    GroupChatEntity groupChatEntity = groupEntityRowMapper.map(resultSet);
                    optionalGroupChatEntity = Optional.of(groupChatEntity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (optionalGroupChatEntity.isPresent()) {
            List<ContactEntity> contacts = getGroupMemberList(id);
            optionalGroupChatEntity.get().setGroupMembersList(contacts);
        }

        return optionalGroupChatEntity;
    }

    @Override
    public List<ContactEntity> getGroupMemberList(int id) {
        String query = "select users.phone_number,users.display_name,users.picture,user_status.user_status_id,user_status.user_status_name from " +
                "group_chats_users inner join users " +
                "on group_chats_users.user_phone_number = users.phone_number " +
                "inner join user_status " +
                "on users.user_status_id = user_status.user_status_id " +
                "where group_chat_id = ?";

        List<ContactEntity> groupMembersList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ContactEntity contactEntity = contactEntityRowMapper.map(resultSet);
                groupMembersList.add(contactEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupMembersList;
    }

    @Override
    public List<GroupChatEntity> getGroupChats(String phoneNumber) {
        List<Integer> joinedGroups = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("select group_chat_id from group_chats_users where user_phone_number = ?");
        ) {
            statement.setString(1, phoneNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    joinedGroups.add(resultSet.getInt("group_chat_id"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        List<GroupChatEntity> groupChatList = new ArrayList<>();
        for (int groupId : joinedGroups) {
            Optional<GroupChatEntity> groupChatEntityOptional = getById(groupId);
            groupChatEntityOptional.ifPresent(groupChatList::add);
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
        String query = "insert into group_chats (group_chat_name) values (?) ";
        int addedGroupId = -1;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, groupChatEntity.getGroupChatName());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 1) {

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        addedGroupId = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (addedGroupId != -1) {
            if (!addMembers(addedGroupId, groupChatEntity.getGroupMembersList())) {
                deleteGroup(addedGroupId);
                addedGroupId = -1;
            }
        }


        return addedGroupId;
    }

    @Override
    public boolean addMembers(int id, List<ContactEntity> groupMembersList) {
        String query = "insert into group_chats_users (group_chat_id, user_phone_number) values(?, ?) ";
        if (isFoundById(id)) {
            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
                boolean isAllAdded = countNotAdded == 0;
                return isAllAdded;

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
