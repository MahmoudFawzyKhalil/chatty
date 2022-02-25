package gov.iti.jets.presentation.models;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class ServerModel {
    private ListProperty<MessageModel> announcements = new SimpleListProperty<>( FXCollections.observableArrayList( MessageModel.extractor() ) );
    private ListProperty<XYChart.Series<String, Number>> onlineUsersBarChartData = new SimpleListProperty<>( FXCollections.observableArrayList() );
    private ListProperty<XYChart.Series<String, Number>> gendersBarChartData = new SimpleListProperty<>( FXCollections.observableArrayList() );
    private ListProperty<XYChart.Series<String, Number>> countryBarChartData = new SimpleListProperty<>( FXCollections.observableArrayList() );


    public ServerModel(){

    }

    public ObservableList<MessageModel> getAnnouncements() {
        return announcements.get();
    }

    public ListProperty<MessageModel> announcementsProperty() {
        return announcements;
    }

    public void setAnnouncements( ObservableList<MessageModel> announcements ) {
        this.announcements.set( announcements );
    }

    public ObservableList<XYChart.Series<String, Number>> getOnlineUsersBarChartData() {
        return onlineUsersBarChartData.get();
    }

    public ListProperty<XYChart.Series<String, Number>> onlineUsersBarChartDataProperty() {
        return onlineUsersBarChartData;
    }

    public void setOnlineUsersBarChartData( ObservableList<XYChart.Series<String, Number>> onlineUsersBarChartData ) {
        this.onlineUsersBarChartData.set( onlineUsersBarChartData );
    }

    public ObservableList<XYChart.Series<String, Number>> getGendersBarChartData() {
        return gendersBarChartData.get();
    }

    public ListProperty<XYChart.Series<String, Number>> gendersBarChartDataProperty() {
        return gendersBarChartData;
    }

    public void setGendersBarChartData( ObservableList<XYChart.Series<String, Number>> gendersBarChartData ) {
        this.gendersBarChartData.set( gendersBarChartData );
    }

    public ObservableList<XYChart.Series<String, Number>> getCountryBarChartData() {
        return countryBarChartData.get();
    }

    public ListProperty<XYChart.Series<String, Number>> countryBarChartDataProperty() {
        return countryBarChartData;
    }

    public void setCountryBarChartData( ObservableList<XYChart.Series<String, Number>> countryBarChartData ) {
        this.countryBarChartData.set( countryBarChartData );
    }
}
