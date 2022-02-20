package gov.iti.jets.repository.impls;

import gov.iti.jets.repository.SingleMessageRepository;
import gov.iti.jets.repository.entities.ContactEntity;
import gov.iti.jets.repository.entities.SingleMessageEntity;
import gov.iti.jets.repository.rowsetmappers.RowMapper;
import gov.iti.jets.repository.rowsetmappers.SingleMessageEntityMapper;
import gov.iti.jets.repository.util.ConnectionPool;

import java.sql.*;
import java.util.*;

public class SingleMessageRepositoryImpl implements SingleMessageRepository {

    private final RowMapper<SingleMessageEntity> singleMessageMapper = new SingleMessageEntityMapper();

    @Override
    public boolean insertMessage(SingleMessageEntity singleMessageEntity) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into single_messages (sender_phone_number, receiver_phone_number, message_body, css_bubble_style, css_text_style, time_stamp) values(?, ?, ?, ?, ?, ?) ")) {
            preparedStatement.setString(1, singleMessageEntity.getSenderPhoneNumber());
            preparedStatement.setString(2, singleMessageEntity.getReceiverPhoneNumber());
            preparedStatement.setString(3, singleMessageEntity.getMessageBody());
            preparedStatement.setString(4, singleMessageEntity.getCssBubbleStyleString());
            preparedStatement.setString(5, singleMessageEntity.getCssTextStyleString());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(singleMessageEntity.getTimeStamp()));
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
    public List<SingleMessageEntity> getMessagesList() {
        List<SingleMessageEntity> messageList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("select sender_phone_number, receiver_phone_number, message_body, css_bubble_style, css_text_style, time_stamp from single_messages");
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    SingleMessageEntity singleMessageEntity = singleMessageMapper.map(resultSet);
                    messageList.add(singleMessageEntity);
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
    public Map<String, List<SingleMessageEntity>> getMessage(String userPhoneNumber) {
        Map<String, List<SingleMessageEntity>> messagesMap = new HashMap<>();
        Optional<ContactEntity> optionalContactEntity;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement1 = connection.prepareStatement("select sender_phone_number, receiver_phone_number, message_body, css_bubble_style, css_text_style, time_stamp from single_messages where sender_phone_number = ? ");
             PreparedStatement statement2 = connection.prepareStatement("select sender_phone_number, receiver_phone_number, message_body, css_bubble_style, css_text_style, time_stamp from single_messages where receiver_phone_number = ?");
        ) {
            statement1.setString(1, userPhoneNumber);
            statement2.setString(1, userPhoneNumber);
            try (ResultSet resultSet = statement1.executeQuery()) {
                while (resultSet.next()) {
                    SingleMessageEntity singleMessageEntity = singleMessageMapper.map(resultSet);
                    if (messagesMap.containsKey(singleMessageEntity.getReceiverPhoneNumber())) {
                        List<SingleMessageEntity> list = messagesMap.get(singleMessageEntity.getReceiverPhoneNumber());
                        list.add(singleMessageEntity);
                    } else {
                        List<SingleMessageEntity> list = new ArrayList<>();
                        list.add(singleMessageEntity);
                        messagesMap.put(singleMessageEntity.getReceiverPhoneNumber(), list);
                    }
                }
            }

            try (ResultSet resultSet = statement2.executeQuery()) {
                while (resultSet.next()) {
                    SingleMessageEntity singleMessageEntity = singleMessageMapper.map(resultSet);
                    if (messagesMap.containsKey(singleMessageEntity.getSenderPhoneNumber())) {
                        List<SingleMessageEntity> list = messagesMap.get(singleMessageEntity.getSenderPhoneNumber());
                        list.add(singleMessageEntity);
                    } else {
                        List<SingleMessageEntity> list = new ArrayList<>();
                        list.add(singleMessageEntity);
                        messagesMap.put(singleMessageEntity.getSenderPhoneNumber(), list);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messagesMap;
    }
}