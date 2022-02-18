package gov.iti.jets.commons.dtos;

import gov.iti.jets.commons.util.ValidationUtil;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class UserStatusDto implements Serializable {


    @NotNull
    private int userStatusId;

    @NotNull
    private String userStatusName;

    public UserStatusDto( int userStatusId, String userStatusName ) {
        this.userStatusId = userStatusId;
        this.userStatusName = userStatusName;

        ValidationUtil.getInstance().validate( this );
    }

    public int getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId( int userStatusId ) {
        this.userStatusId = userStatusId;
    }

    public String getUserStatusName() {
        return userStatusName;
    }

    public void setUserStatusName( String userStatusName ) {
        this.userStatusName = userStatusName;
    }
}
