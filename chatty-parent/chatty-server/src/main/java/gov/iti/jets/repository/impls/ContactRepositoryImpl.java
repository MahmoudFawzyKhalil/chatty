package gov.iti.jets.repository.impls;

import gov.iti.jets.repository.ContactRepository;
import gov.iti.jets.repository.UserStatusRepository;
import gov.iti.jets.repository.entities.ContactEntity;
import gov.iti.jets.repository.entities.UserStatusEntity;
import gov.iti.jets.repository.util.ConnectionPool;
import gov.iti.jets.repository.util.RepositoryFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ContactRepositoryImpl implements ContactRepository {

    @Override
    public Optional<ContactEntity> getContact(String phoneNumber) {
        UserStatusRepository userStatusRepository = RepositoryFactory.getInstance().getUserStatusRepository();
        Optional<ContactEntity> optionalContactEntity = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("select phone_number, display_name, picture, user_status_id from users where phone_number = ?");
        ) {
            statement.setString(1, phoneNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ContactEntity contactEntity = new ContactEntity();
                    contactEntity.setPhoneNumber(resultSet.getString("phone_number"));
                    contactEntity.setDisplayName(resultSet.getString("display_name"));
                    contactEntity.setProfilePicture(resultSet.getString("picture"));
                    Optional<UserStatusEntity> userStatus = userStatusRepository.getStatus(resultSet.getInt("user_status_id"));
                    if(!userStatus.isEmpty()){
                        contactEntity.setCurrentStatus(userStatus.get());
                    }

                    optionalContactEntity = Optional.of(contactEntity);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalContactEntity;
    }
}
