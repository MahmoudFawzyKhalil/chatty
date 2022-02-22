package gov.iti.jets.repository;

import java.util.Map;

public interface DashboardRepository {
    int getFemaleUsersNumber();
    int getMaleUsersNumber();
    int getAllUsersNumber();
    Map<String,Integer> getUserNumberByCountry();
}
