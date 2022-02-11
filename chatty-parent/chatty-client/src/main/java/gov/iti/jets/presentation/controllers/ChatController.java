package gov.iti.jets.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {


    @FXML
    private VBox chatMessagesVbox;

    @FXML
    private TextArea chatTextArea;

    @FXML
    private Label contactOrGroupNameLabel;

    @FXML
    private Circle contactOrGroupPicCircle;

    @FXML
    private Circle contactStatusCircle;

    @FXML
    private ChoiceBox<String> fontFamilyChoiceBox;

    @FXML
    private ChoiceBox<String> fontSizeChoiceBox;

    @FXML
    private ColorPicker messageBackgroundColorPicker;

    @FXML
    private ColorPicker messageTextColorPicker;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    @FXML
    void onAttachFileButtonAction(ActionEvent event) {

    }

    @FXML
    void onBoldToggleButtonAction(ActionEvent event) {

    }

    @FXML
    void onItalicToggleButtonAction(ActionEvent event) {

    }

    @FXML
    void onSendMessageButtonAction(ActionEvent event) {

    }

    @FXML
    void onTextStyleButtonAction(ActionEvent event) {

    }

    @FXML
    void onUnderlineToggleButtonAction(ActionEvent event) {

    }

}
