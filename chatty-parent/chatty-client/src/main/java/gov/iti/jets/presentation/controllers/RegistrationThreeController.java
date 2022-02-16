package gov.iti.jets.presentation.controllers;

import gov.iti.jets.commons.dtos.RegisterDto;
import gov.iti.jets.presentation.models.RegisterModel;
import gov.iti.jets.presentation.models.mappers.RegisterMapper;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.RegisterDao;
import gov.iti.jets.services.util.DaoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class RegistrationThreeController implements Initializable {

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    private final RegisterModel registerModel = modelFactory.getRegisterModel();
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private final RegisterDao registerDao = daoFactory.getRegisterDao();

    @FXML
    private Circle profilePictureCircle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindProfilePicCircle();
    }

    @FXML
    void onUploadPictureHyperLinkAction(ActionEvent event) {

    }

    @FXML
    void onFinishButtonAction(ActionEvent event) {
        RegisterDto registerDto = RegisterMapper.INSTANCE.registerModelToDto(registerModel);
        try {
            registerDao.register(registerDto);
            registerModel.clear();
            stageCoordinator.switchToLoginScene();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onPreviousButtonAction(ActionEvent event) {
        stageCoordinator.switchToRegisterSceneTwo();
    }

    private void bindProfilePicCircle() {
        profilePictureCircle.setFill(new ImagePattern(registerModel.getProfilePicture()));
        registerModel.profilePictureProperty().addListener(e -> {
            profilePictureCircle.setFill(new ImagePattern(registerModel.getProfilePicture()));
        });
    }
}
