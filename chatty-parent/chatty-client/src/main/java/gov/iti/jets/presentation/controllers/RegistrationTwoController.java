package gov.iti.jets.presentation.controllers;

import gov.iti.jets.commons.dtos.CountryDto;
import gov.iti.jets.presentation.erros.ErrorMessages;
import gov.iti.jets.presentation.models.CountryModel;
import gov.iti.jets.presentation.models.RegisterModel;
import gov.iti.jets.presentation.models.mappers.CountryMapper;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presentation.util.UiValidator;
import gov.iti.jets.services.CountryDao;
import gov.iti.jets.services.RegisterDao;
import gov.iti.jets.services.util.DaoFactory;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import net.synedra.validatorfx.Validator;

import java.net.URL;
import java.rmi.ConnectException;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RegistrationTwoController implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private final RegisterModel registerModel = modelFactory.getRegisterModel();
    private final CountryDao countryDao = DaoFactory.getInstance().getCountryDao();
    private final RegisterDao registerDao = DaoFactory.getInstance().getRegisterDao();

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

    @FXML
    private Button nextButton;

    @FXML
    private Button previousButton;


    private ObservableList<CountryModel> countryModels;


    private final Validator validator = UiValidator.getInstance().createValidator();


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
        validateEmailTextField();
        validateCountryComboBox();
        validateBirthDateDatePicker();
        addEnableButtonValidationListener();
        setDatePickerDefaults();

    }

    private void setDatePickerDefaults() {
        birthDateDatePicker.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate now = LocalDate.now();
                long diff = ChronoUnit.YEARS.between(date, now);
                this.setDisable(empty || diff < 10 );
            }
        });
    }

    private void loadCountries() {
        try {
            List<CountryDto> countryDtos = countryDao.getAll();
            List<CountryModel> countries = CountryMapper.INSTANCE.dtoListToModel(countryDtos);
            this.countryModels = countries.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
        } catch (NoSuchObjectException | NotBoundException | ConnectException c) {
            ServiceFactory.getInstance().shutdown();
            StageCoordinator.getInstance().showErrorNotification("Failed to connect to server. Please try again later.");
            ModelFactory.getInstance().clearUserModel();
            ModelFactory.getInstance().clearUserModel();
            StageCoordinator.getInstance().switchToConnectToServer();
        } catch (RemoteException e) {
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

    private void validateEmailTextField() {
        validator.createCheck()
                .dependsOn("email", emailTextField.textProperty())
                .withMethod(c -> {
                    String email = c.get("email");
                    if (!UiValidator.EMAIL_PATTERN.matcher(email).matches()) {
                        c.error("Please enter a valid email.");
                        nextButton.setDisable(true);
                    }
                })
                .decorates(emailTextField)
                .immediate();
    }

    private void validateBirthDateDatePicker() {
        validator.createCheck()
                .dependsOn("birthDate", birthDateDatePicker.valueProperty())
                .withMethod(c -> {
                    LocalDate birthDate = c.get("birthDate");
                    if (birthDate == null) {
                        c.error("Please enter your birth date.");
                        nextButton.setDisable(true);
                    }
                })
                .decorates(birthDateDatePicker)
                .immediate();
    }

    private void validateCountryComboBox() {
        validator.createCheck()
                .dependsOn("country", countryComboBox.valueProperty())
                .withMethod(c -> {
                    CountryModel country = c.get("country");
                    if (country.getCountryId() == RegisterModel.DEFAULT_COUNTRY_MODEL.getCountryId()) {
                        c.error("Please choose country.");
                        nextButton.setDisable(true);
                    }
                })
                .decorates(countryComboBox)
                .immediate();
    }

    private void addEnableButtonValidationListener() {
        validator.containsErrorsProperty().addListener(e -> {
            if (!validator.containsErrors()) {
                nextButton.setDisable(false);
            }
        });
    }

    @FXML
    void onPreviousButtonAction(ActionEvent event) {
        stageCoordinator.switchToRegisterSceneOne();
    }

    @FXML
    void onNextButtonAction(ActionEvent event) {
        if (!isEmailFound()) {
            stageCoordinator.switchToRegisterSceneThree();
        } else {
            stageCoordinator.showErrorNotification(ErrorMessages.EMAIL_FOUND);
        }
    }

    boolean isEmailFound() {
        try {
            return registerDao.isFoundBefore(registerModel.getEmail());
        } catch (NoSuchObjectException | NotBoundException | ConnectException c) {
            ServiceFactory.getInstance().shutdown();
            StageCoordinator.getInstance().showErrorNotification("Failed to connect to server. Please try again later.");
            ModelFactory.getInstance().clearUserModel();
            ModelFactory.getInstance().clearUserModel();
            StageCoordinator.getInstance().switchToConnectToServer();
        } catch (RemoteException e) {
            stageCoordinator.showErrorNotification(ErrorMessages.FAILED_TO_CONNECT);
        }
        return false;
    }


}
