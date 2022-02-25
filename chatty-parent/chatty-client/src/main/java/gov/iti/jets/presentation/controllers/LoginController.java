package gov.iti.jets.presentation.controllers;

import gov.iti.jets.commons.dtos.LoginDto;
import gov.iti.jets.commons.dtos.StatusNotificationDto;
import gov.iti.jets.commons.enums.StatusNotificationType;
import gov.iti.jets.network.ClientImpl;
import gov.iti.jets.presentation.models.ContactModel;
import gov.iti.jets.presentation.models.GroupChatModel;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.models.UserStatusModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presentation.util.UiValidator;
import gov.iti.jets.services.ConnectionDao;
import gov.iti.jets.services.LoginDao;
import gov.iti.jets.services.util.DaoFactory;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.synedra.validatorfx.Validator;

import java.net.URL;
import java.rmi.ConnectException;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class LoginController implements Initializable {

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private final LoginDao loginDao = daoFactory.getLoginService();
    private final ConnectionDao connectionDao = daoFactory.getConnectionService();
    private final ClientImpl client = ClientImpl.getInstance();
    private List<Integer> groupIdsList = new ArrayList<>();
    private UserModel userModel = modelFactory.getUserModel();

    private Validator validator = UiValidator.getInstance().createValidator();

    @FXML
    private Hyperlink createAccountHyperLink;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField phoneNumberTextField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validatePhoneNumberTextField();
        validatePasswordTextField();
        addEnableButtonValidationListener();
    }

    private void validatePasswordTextField() {
        validator.createCheck()
                .dependsOn("password", passwordTextField.textProperty())
                .withMethod(c -> {
                    String password = c.get("password");
                    if (password.length() < 8 || password.length() > 20) {
                        c.error("Please enter a valid password between 8 and 20 characters long.");
                        loginButton.setDisable(true);
                    }
                })
                .decorates(passwordTextField)
                .immediate();
    }

    private void validatePhoneNumberTextField() {
        validator.createCheck()
                .dependsOn("phoneNumber", phoneNumberTextField.textProperty())
                .withMethod(c -> {
                    String phoneNumber = c.get("phoneNumber");
                    if (!UiValidator.PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches()) {
                        c.error("Please enter a valid 11 digit phone number.");
                        loginButton.setDisable(true);
                    }
                })
                .decorates(phoneNumberTextField)
                .immediate();
    }

    private void addEnableButtonValidationListener() {
        validator.containsErrorsProperty().addListener(e -> {
            if (!validator.containsErrors()) {
                loginButton.setDisable(false);
            }
        });
    }

    @FXML
    void onCreateAccountHyperLinkAction(ActionEvent event) {
        stageCoordinator.switchToRegisterSceneOne();
    }

    @FXML
    void onLoginButtonAction(ActionEvent event) {
        LoginDto loginDto = new LoginDto(phoneNumberTextField.getText(), passwordTextField.getText());
        try {
            boolean isAuthenticated = loginDao.isAuthenticated(loginDto);
            if (isAuthenticated) {
                connectionDao.registerClient(phoneNumberTextField.getText(), client);
                connectionDao.registerGroups(getGroupIdsList(userModel.getGroupChats()), client);

                var notificationDto = new StatusNotificationDto(userModel.getPhoneNumber(),
                        StatusNotificationType.valueOf(userModel.getCurrentStatus().getUserStatusName()));
                List<String> contactsPhoneNumbers = userModel.getContacts()
                        .stream()
                        .map(ContactModel::getPhoneNumber)
                        .collect(Collectors.toList());

                connectionDao.notifyOthersOfStatusUpdate(notificationDto, contactsPhoneNumbers);

                List<String> offlineContactsPhoneNumbers = connectionDao.getOfflineContacts(contactsPhoneNumbers);

                offlineContactsPhoneNumbers.forEach(phoneNumber -> {
                    userModel.getContacts()
                            .stream()
                            .filter(cm -> cm.getPhoneNumber().equals(phoneNumber))
                            .findFirst()
                            .ifPresent(contactModel -> {
                                Platform.runLater(() -> {
                                    contactModel.setCurrentStatus(UserStatusModel.OFFLINE);
                                });
                            });
                });
                passwordTextField.clear();
                ModelFactory.getInstance().getUpdateProfileModel().resetData();
                stageCoordinator.switchToMainScene();

            } else {
                stageCoordinator.showErrorNotification("Invalid phone number or password.");
            }
        } catch (NoSuchObjectException | NotBoundException | ConnectException c) {
            ServiceFactory.getInstance().shutdown();
            stageCoordinator.showErrorNotification("Failed to connect to server. Please try again later.");
            ModelFactory.getInstance().clearUserModel();
            modelFactory.clearUserModel();
            stageCoordinator.switchToConnectToServer();
        } catch (RemoteException e) {
            stageCoordinator.showErrorNotification("Failed to connect to server. Please try again later.");
            e.printStackTrace();
        }
    }

    private List<Integer> getGroupIdsList(ObservableList<GroupChatModel> groupChatModels) {
        for (GroupChatModel groupChatModel : groupChatModels) {
            groupIdsList.add(groupChatModel.getGroupChatId());
        }
        return groupIdsList;
    }
}
