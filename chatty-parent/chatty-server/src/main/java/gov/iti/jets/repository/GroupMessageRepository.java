package gov.iti.jets.repository;

import gov.iti.jets.repository.entities.GroupMessageEntity;
import gov.iti.jets.repository.entities.SingleMessageEntity;

import java.util.List;
import java.util.Map;

public interface GroupMessageRepository {
    boolean insertMessage(GroupMessageEntity groupMessageEntity);
    List<GroupMessageEntity> getGroupMessagesList ();
    Map<Integer, List<GroupMessageEntity>> getMessage(int groupChatId) ;
}
