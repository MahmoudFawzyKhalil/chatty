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
    @Override
    public int getFemaleUsersNumber() {

        try (Connection connection = ConnectionPool.getConnection();
         PreparedStatement statement = connection.prepareStatement("select count(phone_number) from users where where gender = 'F'");){
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery( "select count(phone_number) from users where where gender = 'F'")) {
//                resultSet.next();
             ResultSet resultSet = statement.executeQuery();
             femaleUsers = resultSet.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } ;
                dashboardEntity.setFemaleUsers(femaleUsers);
        System.out.println("number of female users is : "+femaleUsers);
        return femaleUsers;
    }

    @Override
    public int getMaleUsersNumber() {
        return 0;
    }
}
