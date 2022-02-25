package gov.iti.jets.repository.impls;

import gov.iti.jets.repository.UserStatusRepository;
import gov.iti.jets.repository.entities.UserStatusEntity;
import gov.iti.jets.repository.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserStatusRepositoryImpl implements UserStatusRepository {
    @Override
    public Optional<UserStatusEntity> getStatus(int id) {
        Optional<UserStatusEntity> optionalUserStatusEntity = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("select user_status_id,user_status_name from user_status where user_status_id = ?");
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    UserStatusEntity userStatusEntity = new UserStatusEntity(resultSet.getInt("user_status_id"), resultSet.getString("user_status_name"));
                    optionalUserStatusEntity = Optional.of(userStatusEntity);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalUserStatusEntity;
    }
}
