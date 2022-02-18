package gov.iti.jets.repository.impls;

import gov.iti.jets.commons.dtos.AddContactDto;
import gov.iti.jets.repository.*;
import gov.iti.jets.repository.entities.*;
import gov.iti.jets.repository.util.ConnectionPool;
import gov.iti.jets.repository.util.RepositoryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private Logger logger = LoggerFactory.getLogger( UserRepositoryImpl.class );

    @Override
    public boolean isFoundByPhoneNumberAndPassword(String phoneNumber, String password) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from users where phone_number = ? and user_password=?")) {
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addContacts(AddContactDto addContactDto) {
        try (Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into invitations (sender, receiver) values(?,?)")) {

            logger.info( "An attempt to add a contact was made: " + addContactDto.toString() );

            connection.setAutoCommit(false);

            for(String receiverPhoneNumber: addContactDto.getPhoneNumbers()) {
                preparedStatement.setString(1, addContactDto.getPhoneNumber());
                preparedStatement.setString(2, receiverPhoneNumber);
                preparedStatement.addBatch();
            }
            int[] numSucceeded = preparedStatement.executeBatch();
            connection.commit();
            if (numSucceeded.length > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addUser(UserEntity userEntity) {
        if (!(isFoundByPhoneNumber(userEntity.getPhoneNumber()))) {
            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("insert into users (phone_number, display_name,gender, email,picture, bio, user_password, birth_date,country_id) values(?, ?, ?, ?, ?, ?, ?, ?, ?) ")) {
                preparedStatement.setString(1, userEntity.getPhoneNumber());
                preparedStatement.setString(2, userEntity.getDisplayName());
                preparedStatement.setString(3, userEntity.getGender());
                preparedStatement.setString(4, userEntity.getEmail());
                preparedStatement.setString(5, userEntity.getUserPicture());
                preparedStatement.setString(6, userEntity.getBio());
                preparedStatement.setString(7, userEntity.getPassword());
                preparedStatement.setDate(8, Date.valueOf(userEntity.getBirthDate()));
                preparedStatement.setInt(9, userEntity.getCountry().getCountryId());
                int resultSet = preparedStatement.executeUpdate();
                if (resultSet == 1) {
                    return true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean isFoundByPhoneNumber(String phoneNumber) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select phone_number from users where phone_number = ?")) {
            preparedStatement.setString(1, phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isFoundByEmail(String email) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select email from users where email = ?")) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(UserEntity userEntity) {
        /*todo
         * set image
         * */
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update users set display_name = ? , bio = ? where phone_number = ?")) {

            preparedStatement.setString(1, userEntity.getDisplayName());
            preparedStatement.setString(2, userEntity.getBio());
            preparedStatement.setString(3, userEntity.getPhoneNumber());
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Optional<UserEntity> getUserByPhoneNumber(String phoneNumber) {
        ContactRepository contactRepository = RepositoryFactory.getInstance().getContactRepository();
        UserStatusRepository userStatusRepository = RepositoryFactory.getInstance().getUserStatusRepository();
        GroupChatRepository groupChatRepository = RepositoryFactory.getInstance().getGroupChatRepository();
        CountryRepository countryRepository = RepositoryFactory.getInstance().getCountryRepository();
        InvitationsRepository invitationsRepository = RepositoryFactory.getInstance().getInvitationsRepository();

        Optional<UserEntity> optionalUserEntity = Optional.empty();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from users where phone_number = ?")) {
            preparedStatement.setString(1, phoneNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if(resultSet.next()) {
                    UserEntity userEntity = new UserEntity();
                    userEntity.setPhoneNumber(resultSet.getString("phone_number"));
                    userEntity.setDisplayName(resultSet.getString("display_name"));
                    userEntity.setGender(resultSet.getString("gender"));
                    userEntity.setEmail(resultSet.getString("email"));
                    userEntity.setUserPicture(resultSet.getString("picture"));
                    userEntity.setBio(resultSet.getString("bio"));
                    userEntity.setPassword(resultSet.getString("user_password"));
                    userEntity.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                    Optional<CountryEntity> countryEntityOptional = countryRepository.getById(resultSet.getInt("country_id"));
                    if (!countryEntityOptional.isEmpty()) {
                        CountryEntity countryEntity = countryEntityOptional.get();
                        userEntity.setCountry(countryEntity);
                    }
                    Optional<UserStatusEntity> userStatusOptional = userStatusRepository.getStatus(resultSet.getInt("user_status_id"));
                    if (!userStatusOptional.isEmpty()) {
                        UserStatusEntity userStatusEntity = userStatusOptional.get();
                        userEntity.setCurrentStatus(userStatusEntity);
                    }
                    Optional<List<ContactEntity>> contactsListOptional = contactRepository.getUserContacts(resultSet.getString("phone_number"));
                    if (!contactsListOptional.isEmpty()) {
                        List<ContactEntity> contactsList = contactsListOptional.get();
                        userEntity.setContactsList(contactsList);
                    }/* TODO
                    Optional<List<InvitationEntity>> invitationsListOptional = invitationsRepository.getInvitations(resultSet.getString("phone_number"));
                    if (!contactsListOptional.isEmpty()) {
                        List<ContactEntity> contactsList = contactsListOptional.get();
                        userEntity.setContactsList(contactsList);
                    }*/
                    optionalUserEntity = Optional.of(userEntity);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return optionalUserEntity;
    }
}