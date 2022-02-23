package gov.iti.jets.repository.rowsetmappers;

import gov.iti.jets.repository.entities.SingleMessageEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SingleMessageEntityMapper implements RowMapper<SingleMessageEntity>{
    @Override
    public SingleMessageEntity map(ResultSet resultSet) throws SQLException {
        SingleMessageEntity singleMessageEntity = new SingleMessageEntity();
        singleMessageEntity.setSenderPhoneNumber(resultSet.getString("sender_phone_number"));
        singleMessageEntity.setReceiverPhoneNumber(resultSet.getString("receiver_phone_number"));
        singleMessageEntity.setMessageBody(resultSet.getString("message_body"));
        singleMessageEntity.setCssBubbleStyleString(resultSet.getString("css_bubble_style"));
        singleMessageEntity.setCssTextStyleString(resultSet.getString("css_text_style"));
        singleMessageEntity.setTimeStamp(resultSet.getTimestamp("time_stamp").toLocalDateTime());
        return  singleMessageEntity;
    }
}
