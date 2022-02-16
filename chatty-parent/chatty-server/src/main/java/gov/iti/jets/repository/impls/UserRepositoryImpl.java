package gov.iti.jets.repository.impls;

import gov.iti.jets.repository.UserRepository;
import gov.iti.jets.repository.entities.UserEntity;
import gov.iti.jets.repository.util.ConnectionPool;

import java.sql.*;
import java.util.Optional;

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
    if(!(isFoundByPhoneNumber(userEntity.getPhoneNumber()))){
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into users (phone_number, display_name,gender, email,picture, bio, user_password, birth_date,country_id, user_status_id) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ")) {
            preparedStatement.setString(1, userEntity.getPhoneNumber());
            preparedStatement.setString(2, userEntity.getDisplayname());
            preparedStatement.setString(3, userEntity.getGender());
            preparedStatement.setString(4, userEntity.getEmail());
            preparedStatement.setString(5, userEntity.getUserpicture());
            preparedStatement.setString(6, userEntity.getBio());
            preparedStatement.setString(7, userEntity.getPassword());
            preparedStatement.setDate(8, Date.valueOf(userEntity.getBirthDate()));
            preparedStatement.setInt(9, userEntity.getCountryId());
            preparedStatement.setInt(10, userEntity.getUserStatusId());
            int resultSet = preparedStatement.executeUpdate();
            if(resultSet == 1){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        return false;
    }

    @Override
    public Optional<UserEntity> getByPhoneNumber(String phoneNumber) {
        Optional<UserEntity> optionalUser = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement("select phone_number, display_name,gender, email,picture, bio, user_password, birth_date,country_id, user_status_id from users where phone_number = ?")) {
            preparedStatement.setString(1, phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                UserEntity userEntity = new UserEntity();
                userEntity.setPhoneNumber(resultSet.getString("phone_number"));
                userEntity.setDisplayname(resultSet.getString("display_name"));
                userEntity.setGender(resultSet.getString("gender"));
                userEntity.setEmail(resultSet.getString("email"));
                userEntity.setUserpicture(resultSet.getString("picture"));
                userEntity.setBio(resultSet.getString("bio"));
                userEntity.setPassword(resultSet.getString("user_password"));
                userEntity.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                userEntity.setCountryId(resultSet.getInt("country_id"));
                userEntity.setUserStatusId(resultSet.getInt("user_status_id"));
                optionalUser = Optional.of(userEntity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalUser;
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
}
