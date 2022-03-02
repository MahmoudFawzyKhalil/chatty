package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.erros.ErrorMessages;
import gov.iti.jets.presentation.util.ExecutorUtil;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presentation.util.UiValidator;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.synedra.validatorfx.Validator;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class ConnectServerController implements Initializable {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private Validator validator = UiValidator.getInstance().createValidator();
    @FXML
    private TextField serverIpTextField;

    @FXML
    private Button ipConnectButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validateServerIpTextField();
        addEnableButtonValidationListener();
    }

    private void validateServerIpTextField() {
        validator.createCheck()
                .dependsOn("host", serverIpTextField.textProperty())
                .withMethod(c -> {
                    String host = c.get("host");
                    if (!UiValidator.IP_PATTERN.matcher(host).matches()) {
                        c.error("Please enter a valid ip");
                        ipConnectButton.setDisable(true);
                    }
                })
                .decorates(serverIpTextField)
                .immediate();
    }

    private void addEnableButtonValidationListener() {
        validator.containsErrorsProperty().addListener(e -> {
            if (!validator.containsErrors()) {
                ipConnectButton.setDisable(false);
            }
        });
    }

    @FXML
    void onAutoDetectButtonAction(ActionEvent event) {
        stageCoordinator.showAutoDetectStage();
    }

    @FXML
    void onLocalConnectButtonAction(ActionEvent event) {
        ExecutorUtil.getInstance().execute(() -> {
            try {
                Platform.runLater(stageCoordinator::showTryToConnectSplashStage);
                ServiceFactory.getInstance().setRegistry(serverIpTextField.getText());
                ServiceFactory.getInstance().getLoginService();
                Platform.runLater(() -> {
                    stageCoordinator.switchToLoginScene();
                });

            } catch (RemoteException | NotBoundException e) {
                Platform.runLater(() -> {
                    ServiceFactory.getInstance().shutdown();
                    stageCoordinator.showErrorNotification(ErrorMessages.NOT_VALID_IP);
                });
            } finally {
                Platform.runLater(stageCoordinator::closeTryToConnectSplashStageStage);
            }
        });

    }
}
