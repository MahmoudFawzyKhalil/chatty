package gov.iti.jets.commons.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

public class AddGroupChatDto implements Serializable {
    @NotNull
    private String groupChatName;

    @NotEmpty
    @Min(2)
    private List<ContactDto> groupMembersList;

    private String groupChatPicture;

    public AddGroupChatDto(String groupChatName, List<ContactDto> groupMembersList, String groupChatPicture) {
        this.groupChatName = groupChatName;
        this.groupMembersList = groupMembersList;
        this.groupChatPicture = groupChatPicture;
    }

    public String getGroupChatName() {
        return groupChatName;
    }

    public void setGroupChatName(String groupChatName) {
        this.groupChatName = groupChatName;
    }

    public List<ContactDto> getGroupMembersList() {
        return groupMembersList;
    }

    public void setGroupMembersList(List<ContactDto> groupMembersList) {
        this.groupMembersList = groupMembersList;
    }

    public String getGroupChatPicture() {
        return groupChatPicture;
    }

    public void setGroupChatPicture(String groupChatPicture) {
        this.groupChatPicture = groupChatPicture;
    }
}
