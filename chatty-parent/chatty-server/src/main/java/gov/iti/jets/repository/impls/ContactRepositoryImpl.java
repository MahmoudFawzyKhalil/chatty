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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactRepositoryImpl implements ContactRepository {

    @Override
    public Optional<ContactEntity> getContact(String phoneNumber) {
        UserStatusRepository userStatusRepository = RepositoryFactory.getInstance().getUserStatusRepository();
        Optional<ContactEntity> optionalContactEntity = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
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

    @Override
    public List<ContactEntity> getUserContacts(String phoneNumber) {
        List<ContactEntity> contactList = new ArrayList<>();
        Optional<ContactEntity> optionalContactEntity;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement1 = connection.prepareStatement("select contact_phone_number from contacts where contactee_phone_number = ?");
             PreparedStatement statement2 = connection.prepareStatement("select contactee_phone_number from contacts where contact_phone_number = ?");
        ) {
            statement1.setString(1, phoneNumber);
            statement2.setString(1, phoneNumber);
            try (ResultSet resultSet = statement1.executeQuery()) {
                while(resultSet.next()) {
                    optionalContactEntity = getContact(resultSet.getString("contact_phone_number"));
                    if(!optionalContactEntity.isEmpty()){
                        contactList.add(optionalContactEntity.get());
                    }
                }
            }
            try (ResultSet resultSet = statement2.executeQuery()) {
                while(resultSet.next()) {
                    optionalContactEntity = getContact(resultSet.getString("contactee_phone_number"));
                    if(!optionalContactEntity.isEmpty()){
                        contactList.add(optionalContactEntity.get());
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactList;
    }
}
