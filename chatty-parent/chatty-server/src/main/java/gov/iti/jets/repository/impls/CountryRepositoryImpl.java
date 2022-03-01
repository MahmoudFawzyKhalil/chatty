package gov.iti.jets.repository.impls;

import gov.iti.jets.repository.CountryRepository;
import gov.iti.jets.repository.entities.CountryEntity;
import gov.iti.jets.repository.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryRepositoryImpl implements CountryRepository {
    @Override
    public Optional<CountryEntity> getById(int id) {
        Optional<CountryEntity> optionalCountryEntity = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("select country_id,country_name from countries where country_id = ?");
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    CountryEntity countryEntity = new CountryEntity(resultSet.getInt("country_id"), resultSet.getString("country_name"));
                    optionalCountryEntity = Optional.of(countryEntity);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalCountryEntity;
    }

    @Override
    public List<CountryEntity> getAll() {
        List<CountryEntity> countryEntities = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select country_id,country_name from countries")) {
            while (resultSet.next()) {
                CountryEntity countryEntity = new CountryEntity(resultSet.getInt("country_id"), resultSet.getString("country_name"));
                countryEntities.add(countryEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countryEntities;
    }
}
