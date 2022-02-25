package gov.iti.jets.services;

import javafx.scene.chart.XYChart;

import java.util.List;

public interface DashboardService {
    public List<XYChart.Series<String, Number>> getOnlineUsersBarChartData();
    public List<XYChart.Series<String, Number>> getGendersBarChartData();
    public List<XYChart.Series<String, Number>> getCountryBarChartData();
}
