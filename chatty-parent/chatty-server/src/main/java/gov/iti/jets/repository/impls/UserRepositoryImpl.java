package gov.iti.jets.repository.impls;

import gov.iti.jets.commons.dtos.AddContactDto;
import gov.iti.jets.repository.UserRepository;
import gov.iti.jets.repository.util.ConnectionPool;

import java.sql.*;

public class UserRepositoryImpl implements UserRepository {
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
            PreparedStatement preparedStatement = connection.prepareStatement("insert into invitations values(?,?)")) {
            connection.setAutoCommit(false);
            for(String contacteePhoneNumber: addContactDto.getPhoneNumbers()) {
                preparedStatement.setString(1, addContactDto.getPhoneNumber());
                preparedStatement.setString(2, contacteePhoneNumber);
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
}
