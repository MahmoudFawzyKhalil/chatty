package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateProfileController implements Initializable{

    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final ModelFactory modelFactory = ModelFactory.getInstance();
    private UserModel userModel;

    @FXML
    private TextField bioTextField;

    @FXML
    private DatePicker birthDateDatePicker;

    @FXML
    private TextField emailTextField;

    @FXML
    private ChoiceBox<String> countryChoiceBox;

    @FXML
    private ChoiceBox<String> genderChoiceBox;

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
        userModel = modelFactory.getUserModel();
       //TODO
        // bindProfilePicCircle();
        nameTextField.textProperty().bind(userModel.displayNameProperty());
        emailTextField.textProperty().bind(userModel.emailProperty());
        bioTextField.textProperty().bind(userModel.bioProperty());
        countryChoiceBox.valueProperty().bind(userModel.getCountry().countryNameProperty());
        genderChoiceBox.valueProperty().bind(userModel.genderProperty());
        birthDateDatePicker.valueProperty().bindBidirectional(userModel.birthDateProperty());
    }

    @FXML
    void onUpdateButtonAction(ActionEvent event) {

    }

    @FXML
    void onUploadPictureHyperLinkAction(ActionEvent event) {

    }
    /* TODO
    private void bindProfilePicCircle() {
        profilePictureCircle.setFill( new ImagePattern( userModel.getProfilePicture() ) );
        userModel.profilePictureProperty().addListener( e -> {
            profilePictureCircle.setFill( new ImagePattern( userModel.getProfilePicture() ));
        } );
    }*/
}

