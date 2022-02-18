package gov.iti.jets.presentation.controllers;

import gov.iti.jets.commons.dtos.UpdateProfileDto;
import gov.iti.jets.presentation.erros.ErrorMessages;
import gov.iti.jets.presentation.models.UpdateProfileModel;
import gov.iti.jets.presentation.models.mappers.UpdateProfileMapper;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.UpdateProfileDao;
import gov.iti.jets.services.util.DaoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class UpdateProfileController implements Initializable {

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private final UpdateProfileDao updateProfileDao = daoFactory.getUpdateProfileDao();
    private UpdateProfileModel updateProfileModel = ModelFactory.getInstance().getUpdateProfileModel();

    @FXML
    private TextField bioTextField;


    @FXML
    private TextField nameTextField;

    @FXML
    private Circle profilePictureCircle;

    @FXML
    private Button updateButton;

    @FXML
    private Hyperlink uploadPictureHyperLink;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameTextField.textProperty().bindBidirectional(updateProfileModel.displayNameProperty());
        bioTextField.textProperty().bindBidirectional(updateProfileModel.bioProperty());
        addListenerProfilePictureCircle();
    }

    private void addListenerProfilePictureCircle() {
        profilePictureCircle.setFill(new ImagePattern(updateProfileModel.getProfilePicture()));
        updateProfileModel.profilePictureProperty().addListener(e -> {
            profilePictureCircle.setFill(new ImagePattern(updateProfileModel.getProfilePicture()));
        });
    }

    @FXML
    void onUpdateButtonAction(ActionEvent event) {
        try {
            UpdateProfileDto updateProfileDto = UpdateProfileMapper.INSTANCE.updateProfileModelToDto(updateProfileModel);
            if (updateProfileDao.update(updateProfileDto)) {
                updateProfileModel.updateUserModel();
                stageCoordinator.showMessageNotification("Success", "Updated Successfully");
            } else {
                stageCoordinator.showErrorNotification(ErrorMessages.FAILED_Update);
            }

        } catch (NotBoundException | RemoteException e) {
            stageCoordinator.showErrorNotification(ErrorMessages.FAILED_TO_CONNECT);
        }
    }

    @FXML
    void onUploadPictureHyperLinkAction(ActionEvent event) {

    }

    /*private void bindProfilePicCircle() {
        profilePictureCircle.setFill(new ImagePattern(userModel.getProfilePicture()));
        userModel.profilePictureProperty().addListener(e -> {
            profilePictureCircle.setFill(new ImagePattern(userModel.getProfilePicture()));
        });
    }*/
}

