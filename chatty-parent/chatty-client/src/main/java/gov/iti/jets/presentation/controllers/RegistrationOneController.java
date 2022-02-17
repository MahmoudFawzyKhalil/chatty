package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.erros.ErrorMessages;
import gov.iti.jets.presentation.models.RegisterModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presentation.util.UiValidator;
import gov.iti.jets.services.RegisterDao;
import gov.iti.jets.services.util.DaoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.synedra.validatorfx.Validator;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class RegistrationOneController implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private final RegisterDao registerDao = DaoFactory.getInstance().getRegisterDao();
    private final RegisterModel registerModel = modelFactory.getRegisterModel();

    private Validator validator = UiValidator.getInstance().createValidator();

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private Button registerButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        phoneNumberTextField.textProperty().bindBidirectional(registerModel.phoneNumberProperty());
        nameTextField.textProperty().bindBidirectional(registerModel.displayNameProperty());
        passwordTextField.textProperty().bindBidirectional(registerModel.passwordProperty());
        confirmPasswordTextField.textProperty().bindBidirectional(registerModel.confirmPasswordProperty());
        validatePhoneNumberTextField();
        validateNameTextField();
        validatePasswordTextField();
        //validateConfirmPasswordTextField();
        addEnableButtonValidationListener();
    }

    @FXML
    void onNextButtonAction(ActionEvent event) {
        if (!isPhoneNumberFound()) {
            stageCoordinator.switchToRegisterSceneTwo();
        } else {
            stageCoordinator.showErrorNotification(ErrorMessages.PHONE_NUMBER_FOUND);
        }
    }


    @FXML
    void onLoginHyperLinkAction(ActionEvent event) {
        registerModel.clear();
        stageCoordinator.switchToLoginScene();
    }

    boolean isPhoneNumberFound() {
        try {
            return registerDao.validatePhoneNumber(registerModel.getPhoneNumber());
        } catch (NotBoundException | RemoteException e) {
            stageCoordinator.showErrorNotification(ErrorMessages.FAILED_TO_CONNECT);
        }
        return false;
    }


    private void validatePhoneNumberTextField() {
        validator.createCheck()
                .dependsOn("phoneNumber", phoneNumberTextField.textProperty())
                .withMethod(c -> {
                    String phoneNumber = c.get("phoneNumber");
                    if (!UiValidator.PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches()) {
                        c.error("Please enter a valid 11 digit phone number.");
                        registerButton.setDisable(true);
                    }
                })
                .decorates(phoneNumberTextField)
                .immediate();
    }

    private void validateNameTextField() {
        validator.createCheck()
                .dependsOn("userName", nameTextField.textProperty())
                .withMethod(c -> {
                    String userName = c.get("userName");
                    if (!UiValidator.USER_NAME_PATTERN.matcher(userName).matches() || userName.length() > 12 || userName.length() < 3) {
                        c.error("Please enter a valid name between 3 and 12 characters long.");
                        registerButton.setDisable(true);
                    }
                })
                .decorates(nameTextField)
                .immediate();
    }

    private void validatePasswordTextField() {
        validator.createCheck()
                .dependsOn("password", passwordTextField.textProperty())
                .withMethod(c -> {
                    String password = c.get("password");
                    if (password.length() < 8 || password.length() > 20) {
                        c.error("Please enter a valid password between 8 and 20 characters long.");
                        registerButton.setDisable(true);
                    }
                })
                .decorates(passwordTextField)
                .immediate();
    }

    private void validateConfirmPasswordTextField() {
        validator.createCheck()
                .dependsOn("password", passwordTextField.textProperty())
                .dependsOn("confirmPassword", confirmPasswordTextField.textProperty())
                .withMethod(c -> {
                    String password = c.get("password");
                    String confirmPassword = c.get("confirmPassword");
                    if (!confirmPassword.equals(password)) {
                        c.error("Passwords must match.");
                        registerButton.setDisable(true);
                    }
                })
                .decorates(confirmPasswordTextField)
                .immediate();
    }

    private void addEnableButtonValidationListener() {
        validator.containsErrorsProperty().addListener(e -> {
            if (!validator.containsErrors()) {
                registerButton.setDisable(false);
            }
        });
    }
}
