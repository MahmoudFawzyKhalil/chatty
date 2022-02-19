package gov.iti.jets.repository.rowsetmappers;

import gov.iti.jets.repository.entities.ContactEntity;
import gov.iti.jets.repository.entities.UserStatusEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactEntityMapper implements RowMapper<ContactEntity>{
    @Override
    public ContactEntity map(ResultSet resultSet) throws SQLException {

        ContactEntity contactEntity=new ContactEntity();
        contactEntity.setPhoneNumber(resultSet.getString("phone_number"));
        contactEntity.setDisplayName(resultSet.getString("display_name"));
        contactEntity.setProfilePicture(resultSet.getString("picture"));
        UserStatusEntity userStatusEntity=new UserStatusEntity();
        userStatusEntity.setStatusId(resultSet.getInt("user_status_id"));
        userStatusEntity.setStatusName(resultSet.getString("user_status_name"));
        contactEntity.setCurrentStatus(userStatusEntity);
        return contactEntity;
    }
}
