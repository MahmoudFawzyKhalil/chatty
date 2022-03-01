package gov.iti.jets.repository.rowsetmappers;

import gov.iti.jets.repository.entities.GroupMessageEntity;
import gov.iti.jets.repository.entities.SingleMessageEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMessageEntityMapper implements RowMapper<GroupMessageEntity>{
    @Override
    public GroupMessageEntity map(ResultSet resultSet) throws SQLException {
        GroupMessageEntity groupMessageEntity = new GroupMessageEntity();
        groupMessageEntity.setSenderPhoneNumber(resultSet.getString("sender_phone_number"));
        groupMessageEntity.setGroupChatId(resultSet.getInt("group_chat_id"));
        groupMessageEntity.setMessageBody(resultSet.getString("message_body"));
        groupMessageEntity.setCssBubbleStyleString(resultSet.getString("css_bubble_style"));
        groupMessageEntity.setCssTextStyleString(resultSet.getString("css_text_style"));
        groupMessageEntity.setTimeStamp(resultSet.getTimestamp("time_stamp").toLocalDateTime());
        return groupMessageEntity;
    }
}