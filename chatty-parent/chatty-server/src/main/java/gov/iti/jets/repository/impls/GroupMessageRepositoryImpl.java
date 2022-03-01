package gov.iti.jets.repository.impls;

import gov.iti.jets.repository.GroupChatRepository;
import gov.iti.jets.repository.GroupMessageRepository;
import gov.iti.jets.repository.entities.GroupChatEntity;
import gov.iti.jets.repository.entities.GroupMessageEntity;
import gov.iti.jets.repository.rowsetmappers.GroupMessageEntityMapper;
import gov.iti.jets.repository.rowsetmappers.RowMapper;
import gov.iti.jets.repository.util.ConnectionPool;
import gov.iti.jets.repository.util.RepositoryFactory;

import java.sql.*;
import java.util.*;

public class GroupMessageRepositoryImpl implements GroupMessageRepository {

    private final RowMapper<GroupMessageEntity> groupMessageMapper = new GroupMessageEntityMapper();

    @Override
    public boolean insertMessage(GroupMessageEntity groupMessageEntity) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into group_chat_messages (sender_phone_number, group_chat_id, message_body, css_bubble_style, css_text_style, time_stamp) values(?, ?, ?, ?, ?, ?) ")) {
            preparedStatement.setString(1, groupMessageEntity.getSenderPhoneNumber());
            preparedStatement.setInt(2, groupMessageEntity.getGroupChatId());
            preparedStatement.setString(3, groupMessageEntity.getMessageBody());
            preparedStatement.setString(4, groupMessageEntity.getCssBubbleStyleString());
            preparedStatement.setString(5, groupMessageEntity.getCssTextStyleString());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(groupMessageEntity.getTimeStamp()));
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<GroupMessageEntity> getGroupMessagesList(int groupChatId) {
        List<GroupMessageEntity> messageList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("select sender_phone_number, group_chat_id, message_body, css_bubble_style, css_text_style, time_stamp from group_chat_messages where group_chat_id = ?");
        ) {
            statement.setInt(1,groupChatId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    GroupMessageEntity groupMessageEntity = groupMessageMapper.map(resultSet);
                    messageList.add(groupMessageEntity);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return messageList;
    }

    @Override
    public Map<Integer, List<GroupMessageEntity>> getGroupMessagesMap(String phoneNumber) {
        GroupChatRepository groupChatRepository = RepositoryFactory.getInstance().getGroupChatRepository();
        Map<Integer, List<GroupMessageEntity>> messagesMap = new HashMap<>();
        Optional<GroupChatEntity> optionalGroupChatEntity;

        List<GroupChatEntity> groupChatEntityList = groupChatRepository.getGroupChats(phoneNumber);
        for(GroupChatEntity groupChatEntity: groupChatEntityList){
            messagesMap.put(groupChatEntity.getGroupChatId(), getGroupMessagesList(groupChatEntity.getGroupChatId()));
        }
        return messagesMap;
    }
}