package gov.iti.jets.repository.entities;

import java.util.List;

public class GroupChatEntity {

    private int groupChatId;
    private String groupChatName;
    private String groupChatPicture;
    private List<ContactEntity> groupMembersList;

    public GroupChatEntity(){

    }
    public GroupChatEntity(int groupChatId, String groupChatName, String groupChatPicture, List<ContactEntity> groupMembersList) {
        this.groupChatId = groupChatId;
        this.groupChatName = groupChatName;
        this.groupChatPicture = groupChatPicture;
        this.groupMembersList = groupMembersList;
    }

    public int getGroupChatId() {
        return groupChatId;
    }

    public void setGroupChatId(int groupChatId) {
        this.groupChatId = groupChatId;
    }

    public String getGroupChatName() {
        return groupChatName;
    }

    public void setGroupChatName(String groupChatName) {
        this.groupChatName = groupChatName;
    }

    public String getGroupChatPicture() {
        return groupChatPicture;
    }

    public void setGroupChatPicture(String groupChatPicture) {
        this.groupChatPicture = groupChatPicture;
    }

    public List<ContactEntity> getGroupMembersList() {
        return groupMembersList;
    }

    public void setGroupMembersList(List<ContactEntity> groupMembersList) {
        this.groupMembersList = groupMembersList;
    }
}
