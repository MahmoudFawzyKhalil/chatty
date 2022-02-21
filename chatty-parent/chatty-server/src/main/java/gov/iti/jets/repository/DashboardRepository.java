package gov.iti.jets.repository;

public interface DashboardRepository {
    int getFemaleUsersNumber();
    int getMaleUsersNumber();
    int getOnlineUsersNumber();
    int getOfflineUsersNumber();
    int getUserNumberByCountry();
}
