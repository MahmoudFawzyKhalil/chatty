package gov.iti.jets.repository.impls;

import gov.iti.jets.repository.SingleMessageRepository;
import gov.iti.jets.repository.entities.SingleMessageEntity;
import gov.iti.jets.repository.util.ConnectionPool;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class SingleMessageRepositoryImpl implements SingleMessageRepository {
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
    public Map<String, List<SingleMessageEntity>> getMessage(String userPhoneNumber) {
        return null;
    }
}
