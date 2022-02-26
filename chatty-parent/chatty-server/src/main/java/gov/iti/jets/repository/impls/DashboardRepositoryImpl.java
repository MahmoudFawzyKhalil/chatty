package gov.iti.jets.repository.impls;

import gov.iti.jets.repository.DashboardRepository;
import gov.iti.jets.repository.entities.DashboardEntity;
import gov.iti.jets.repository.util.ConnectionPool;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DashboardRepositoryImpl implements DashboardRepository {
    DashboardEntity dashboardEntity = new DashboardEntity();
    int femaleUsers;
    int maleUsers;
    int allUsers;
    Map<String, Integer> usersbyCountry = new HashMap<>();

    @Override
    public int getFemaleUsersNumber() {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select count(phone_number) from users where gender = 'F'")) {
            resultSet.next();
            femaleUsers = resultSet.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dashboardEntity.setFemaleUsers(femaleUsers);
        return femaleUsers;
    }

    @Override
    public int getMaleUsersNumber() {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select count(phone_number) from users where gender = 'M'")) {
            resultSet.next();
            maleUsers = resultSet.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dashboardEntity.setMaleUsers(maleUsers);
        return maleUsers;
    }


    @Override
    public int getAllUsersNumber() {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select count(phone_number) from users ")) {
            resultSet.next();
            allUsers = resultSet.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dashboardEntity.setAllUsers(allUsers);
        return allUsers;
    }

    @Override
    public Map<String, Integer> getUserNumberByCountry() {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(  "select count(phone_number), country_name from users, countries where users.country_id = countries.country_id group by users.country_id;")
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()){
                    usersbyCountry.put(resultSet.getString(2), resultSet.getInt(1));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dashboardEntity.setUsersByCountry(usersbyCountry);
        return usersbyCountry;
    }
}
