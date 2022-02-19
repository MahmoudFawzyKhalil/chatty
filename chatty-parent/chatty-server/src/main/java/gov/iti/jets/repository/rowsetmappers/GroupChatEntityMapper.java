package gov.iti.jets.repository.rowsetmappers;

import gov.iti.jets.repository.entities.GroupChatEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupChatEntityMapper implements RowMapper<GroupChatEntity> {
    @Override
    public GroupChatEntity map(ResultSet resultSet) throws SQLException {
        GroupChatEntity groupChatEntity = new GroupChatEntity();
        groupChatEntity.setGroupChatId(resultSet.getInt("group_chat_id"));
        groupChatEntity.setGroupChatName(resultSet.getString("group_chat_name"));
        groupChatEntity.setGroupChatPicture(resultSet.getString("picture"));
        groupChatEntity.setGroupMembersList(new ArrayList<>());
        return groupChatEntity;
    }
}
