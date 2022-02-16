package gov.iti.jets.repository.impls;

import gov.iti.jets.repository.UserRepository;
import gov.iti.jets.repository.entity.UserEntity;
import gov.iti.jets.repository.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {
    /*
    private static final UserRepositoryImpl userRepository = new UserRepositoryImpl();
    private UserRepositoryImpl(){

    }
    public static UserRepositoryImpl getInstance(){
        return userRepository;
    }
    */
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
    public boolean isRegistered(UserEntity userEntity) {

        return false;
    }
}
