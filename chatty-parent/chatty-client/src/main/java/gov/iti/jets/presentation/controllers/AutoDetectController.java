package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.erros.ErrorMessages;
import gov.iti.jets.presentation.util.MyExecutor;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.util.ClientDiscoveryUtil;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AutoDetectController implements Initializable {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Future<String> future = MyExecutor.getInstance().submit(ClientDiscoveryUtil.getInstance());
        MyExecutor.getInstance().execute(() -> {
            try {
                String host = future.get();
                if (!host.isEmpty()) {
                    Platform.runLater(() -> {
                        try {
                            ServiceFactory.getInstance().setRegistry(host);
                        } catch (RemoteException e) {
                            stageCoordinator.showErrorNotification(ErrorMessages.NOT_VALID_IP);
                        }
                        stageCoordinator.switchToLoginScene();
                        stageCoordinator.closeAutoDetectStage();
                    });

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void onCancelButtonAction(ActionEvent event) {
        ClientDiscoveryUtil.getInstance().stop();
        stageCoordinator.closeAutoDetectStage();
    }

}

