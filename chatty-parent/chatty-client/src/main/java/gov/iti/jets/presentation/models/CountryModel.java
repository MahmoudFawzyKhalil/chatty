package gov.iti.jets.presentation.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CountryModel {

    private IntegerProperty countryId = new SimpleIntegerProperty();
    private StringProperty countryName = new SimpleStringProperty();

    public CountryModel(){

    }
    public CountryModel(int countryId, String countryName){
        this.countryId.set(countryId);
        this.countryName.set(countryName);
    }

    public int getCountryId() {
        return countryId.get();
    }

    public IntegerProperty countryIdProperty() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId.set(countryId);
    }

    public String getCountryName() {
        return countryName.get();
    }

    public StringProperty countryNameProperty() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName.set(countryName);
    }

    @Override
    public String toString() {
        return "CountryModel{" +
                "countryId=" + countryId +
                ", countryName=" + countryName +
                '}';
    }
}
