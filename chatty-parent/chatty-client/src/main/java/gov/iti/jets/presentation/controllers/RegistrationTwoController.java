package gov.iti.jets.presentation.controllers;

import gov.iti.jets.commons.dtos.CountryDto;
import gov.iti.jets.presentation.models.CountryModel;
import gov.iti.jets.presentation.models.RegisterModel;
import gov.iti.jets.presentation.models.mappers.CountryMapper;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.CountryDao;
import gov.iti.jets.services.util.DaoFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RegistrationTwoController implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private final RegisterModel registerModel = modelFactory.getRegisterModel();
    private CountryDao countryDao = DaoFactory.getInstance().getCountryDao();

    @FXML
    private TextField bioTextField;

    @FXML
    private DatePicker birthDateDatePicker;


    @FXML
    private ComboBox<CountryModel> countryComboBox;

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private TextField emailTextField;


    private ObservableList<CountryModel> countryModels;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCountries();
        setCountryComboBoxConverter();
        countryComboBox.setItems(countryModels);
        emailTextField.textProperty().bindBidirectional(registerModel.emailProperty());
        countryComboBox.valueProperty().bindBidirectional(registerModel.countryProperty());
        genderComboBox.getItems().addAll("Male", "Female");
        genderComboBox.valueProperty().bindBidirectional(registerModel.genderProperty());
        genderComboBox.getSelectionModel().selectFirst();
        birthDateDatePicker.valueProperty().bindBidirectional(registerModel.birthDateProperty());
        bioTextField.textProperty().bindBidirectional(registerModel.bioProperty());
    }

    private void loadCountries() {
        try {
            List<CountryDto> countryDtos = countryDao.getAll();
            List<CountryModel> countries = CountryMapper.INSTANCE.dtoListToModel(countryDtos);
            this.countryModels = countries.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
        } catch (NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }

    private void setCountryComboBoxConverter() {

        countryComboBox.setConverter(new StringConverter<>() {
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

    @FXML
    void onPreviousButtonAction(ActionEvent event) {
        stageCoordinator.switchToRegisterSceneOne();
    }

    @FXML
    void onNextButtonAction(ActionEvent event) {
        stageCoordinator.switchToRegisterSceneThree();
    }


}
