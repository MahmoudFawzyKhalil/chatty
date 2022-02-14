package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationTwoController implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private UserModel userModel;

    @FXML
    private TextField bioTextField;

    @FXML
    private DatePicker birthDateDatePicker;

    @FXML
    private ChoiceBox<String> countryChoiceBox;

    @FXML
    private TextField emailTextField;

    @FXML
    private ChoiceBox<String> genderChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userModel = modelFactory.getUserModel();
        emailTextField.textProperty().bindBidirectional(userModel.emailProperty());
        countryChoiceBox.valueProperty().bindBidirectional(userModel.getCountry().countryNameProperty());
        genderChoiceBox.valueProperty().bindBidirectional(userModel.genderProperty());
        birthDateDatePicker.valueProperty().bindBidirectional(userModel.birthDateProperty());
        bioTextField.textProperty().bindBidirectional(userModel.bioProperty());
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
