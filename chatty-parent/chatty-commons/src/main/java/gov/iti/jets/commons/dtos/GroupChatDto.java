package gov.iti.jets.commons.dtos;

import gov.iti.jets.commons.util.ValidationUtil;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

public class GroupChatDto implements Serializable {

    @NotNull
    private int groupChatId;

    @NotNull
    private String groupChatName;

    private String groupChatPicture;
    private List<ContactDto> groupMembersList;

    public GroupChatDto( int groupChatId, String groupChatName, String getGroupChatPicture, List<ContactDto> groupMembersList ) {
        this.groupChatId = groupChatId;
        this.groupChatName = groupChatName;
        this.groupChatPicture = getGroupChatPicture;
        this.groupMembersList = groupMembersList;

        ValidationUtil.getInstance().validate( this );
    }

    public int getGroupChatId() {
        return groupChatId;
    }

    public void setGroupChatId( int groupChatId ) {
        this.groupChatId = groupChatId;
    }

    public String getGroupChatName() {
        return groupChatName;
    }

    public void setGroupChatName( String groupChatName ) {
        this.groupChatName = groupChatName;
    }

    public String getGroupChatPicture() {
        return groupChatPicture;
    }

    public void setGroupChatPicture( String groupChatPicture ) {
        this.groupChatPicture = groupChatPicture;
    }

    public List<ContactDto> getGroupMembersList() {
        return groupMembersList;
    }

    public void setGroupMembersList( List<ContactDto> groupMembersList ) {
        this.groupMembersList = groupMembersList;
    }
}
