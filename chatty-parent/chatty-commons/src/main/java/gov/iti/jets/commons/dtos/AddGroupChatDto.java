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

    public AddGroupChatDto(String groupChatName, List<ContactDto> groupMembersList) {
        this.groupChatName = groupChatName;
        this.groupMembersList = groupMembersList;
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
}
