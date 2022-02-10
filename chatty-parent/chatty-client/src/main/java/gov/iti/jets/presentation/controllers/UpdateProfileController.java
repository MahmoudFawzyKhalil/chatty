package gov.iti.jets.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

public class UpdateProfileController {

    @FXML
    private TextField bioTextField;

    @FXML
    private DatePicker birthDateDatePicker;

    @FXML
    private ComboBox<?> countryComboBox;

    @FXML
    private TextField emailTextField;

    @FXML
    private ComboBox<?> genderComboBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private Circle profilePictureCirclr;

    @FXML
    private Button updateButton;

    @FXML
    private Hyperlink uploadPictureHyperLink;

    @FXML
    void onUpdateButtonAction(ActionEvent event) {

    }

    @FXML
    void onUploadPictureHyperLinkAction(ActionEvent event) {

    }

}

