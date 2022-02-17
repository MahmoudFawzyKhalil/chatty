package gov.iti.jets.repository.entities;

public class GroupChatEntity {

    private int groupChatId;
    private String groupChatName;
    private String groupChatPicture;

    public GroupChatEntity(int groupChatId, String groupChatName, String groupChatPicture) {
        this.groupChatId = groupChatId;
        this.groupChatName = groupChatName;
        this.groupChatPicture = groupChatPicture;
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
}
