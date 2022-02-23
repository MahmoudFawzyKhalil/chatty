package gov.iti.jets.repository;

import gov.iti.jets.repository.entities.SingleMessageEntity;

import java.util.List;
import java.util.Map;

public interface SingleMessageRepository {
    boolean insertMessage(SingleMessageEntity singleMessageEntity);
    List<SingleMessageEntity> getMessagesList ();
    Map<String, List<SingleMessageEntity>> getMessage(String userPhoneNumber) ;
}
