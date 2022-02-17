package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.CountryModel;
import gov.iti.jets.presentation.models.RegisterModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationTwoController implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private final RegisterModel registerModel = modelFactory.getRegisterModel();

    @FXML
    private TextField bioTextField;

    @FXML
    private DatePicker birthDateDatePicker;

    @FXML
    private ChoiceBox<CountryModel> countryChoiceBox;

    @FXML
    private TextField emailTextField;

    @FXML
    private ChoiceBox<String> genderChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<CountryModel> countryModels = FXCollections.observableArrayList();
        CountryModel countryModel = new CountryModel(1, "egy");
        countryModels.add(countryModel);
        setCountryChoiceBoxConverter();
        countryChoiceBox.setItems(countryModels);
        emailTextField.textProperty().bindBidirectional(registerModel.emailProperty());
        countryChoiceBox.valueProperty().bindBidirectional(registerModel.countryProperty());
        genderChoiceBox.valueProperty().bindBidirectional(registerModel.genderProperty());
        birthDateDatePicker.valueProperty().bindBidirectional(registerModel.birthDateProperty());
        bioTextField.textProperty().bindBidirectional(registerModel.bioProperty());
    }

    @FXML
    void onPreviousButtonAction(ActionEvent event) {
        stageCoordinator.switchToRegisterSceneOne();
    }

    @FXML
    void onNextButtonAction(ActionEvent event) {
        stageCoordinator.switchToRegisterSceneThree();
    }

    private void setCountryChoiceBoxConverter() {
        countryChoiceBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(CountryModel country) {
                if (country == null) {
                    return null;
                } else {
                    return country.getCountryName();
                }
            }

            @Override
            public CountryModel fromString(String id) {
                return null;
            }
        });
    }
}
