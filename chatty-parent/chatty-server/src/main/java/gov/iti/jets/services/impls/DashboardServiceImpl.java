package gov.iti.jets.services.impls;

import gov.iti.jets.network.Clients;
import gov.iti.jets.repository.DashboardRepository;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.services.DashboardService;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class DashboardServiceImpl implements DashboardService {
    private final DashboardRepository dashboardRepository = RepositoryFactory.getInstance().getDashboardRepository();
    private final Clients clients = Clients.getInstance();

    public List<XYChart.Series<String, Number>> getOnlineUsersBarChartData(){
        XYChart.Series<String,Number> onlineUsersData = new XYChart.Series<>();
        onlineUsersData.setName("Online Users");
        onlineUsersData.getData().add(new XYChart.Data<>("Online", clients.getCountOnlineUsers()));

        XYChart.Series<String,Number> offlineUsersData = new XYChart.Series<>();
        offlineUsersData.setName("Offline Users");
        offlineUsersData.getData().add(new XYChart.Data<>("Offline", (dashboardRepository.getAllUsersNumber()-clients.getCountOnlineUsers())));

        return new ArrayList<>(List.of(onlineUsersData, offlineUsersData));
    }

    @Override
    public List<XYChart.Series<String, Number>> getGendersBarChartData() {
        XYChart.Series<String,Number> maleUserData = new XYChart.Series<>();
        maleUserData.setName("Male Users");
        maleUserData.getData().add(new XYChart.Data<>("Male", dashboardRepository.getMaleUsersNumber()));

        XYChart.Series<String,Number> femaleUserData = new XYChart.Series<>();
        femaleUserData.setName("Female Users");
        femaleUserData.getData().add(new XYChart.Data<>("Female", dashboardRepository.getFemaleUsersNumber()));

        return new ArrayList<>(List.of(maleUserData, femaleUserData));
    }

    @Override
    public List<XYChart.Series<String, Number>> getCountryBarChartData() {
        List<XYChart.Series<String, Number>> data = new ArrayList<>();

        for(String key: dashboardRepository.getUserNumberByCountry().keySet()){
            XYChart.Series<String,Number> countryData = new XYChart.Series<>();
            countryData.setName(key);
            countryData.getData().add(new XYChart.Data<>(key,dashboardRepository.getUserNumberByCountry().get(key)));
            data.add( countryData );
        }

        return data;
    }
}
