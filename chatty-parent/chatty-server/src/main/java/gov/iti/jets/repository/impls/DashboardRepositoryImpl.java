package gov.iti.jets.repository.impls;

import gov.iti.jets.repository.DashboardRepository;
import gov.iti.jets.repository.entities.ContactEntity;
import gov.iti.jets.repository.entities.CountryEntity;
import gov.iti.jets.repository.entities.InvitationEntity;
import gov.iti.jets.repository.entities.DashboardEntity;
import gov.iti.jets.repository.util.ConnectionPool;

import java.sql.*;
import java.util.Optional;

public class DashboardRepositoryImpl implements DashboardRepository {
    DashboardEntity dashboardEntity = new DashboardEntity();
    int femaleUsers;
    int maleUsers;
    int usersbyCountry;

    @Override
    public int getFemaleUsersNumber() {

        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select count(phone_number) from users where gender = 'F'")) {
            resultSet.next();
            femaleUsers = resultSet.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        ;
        dashboardEntity.setFemaleUsers(femaleUsers);
        System.out.println("number of female users is : " + femaleUsers);
        return femaleUsers;
    }

    @Override
    public int getMaleUsersNumber() {
//        try (Connection connection = ConnectionPool.getConnection();
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery( "select count(phone_number) from users where gender = 'M'")) {
//            while(resultSet());
//            //usersbyCountry = resultSet.getInt(1);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } ;
//        dashboardEntity.setMaleUsers(maleUsers);
//        System.out.println("number of male users is : "+maleUsers);
        return usersbyCountry;
    }



    @Override
    public int getOnlineUsersNumber() {
        return 0;
    }

    @Override
    public int getOfflineUsersNumber() {
        return 0;
    }

    @Override
    public int getUserNumberByCountry() {
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery( "select count(phone_number), country_name from users, countries where users.country_id = countries.country_id group by users.country_id;")) {
            resultSet.next();
            usersbyCountry = resultSet.getInt(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } ;
        dashboardEntity.setMaleUsers(maleUsers);
        System.out.println("number of male users is : "+maleUsers);
        return maleUsers;
    }
}
