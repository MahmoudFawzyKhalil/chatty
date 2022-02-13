package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.MessageModel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {


    @FXML
    private ListView<MessageModel> chatMessagesListView;

    @FXML
    private TextArea chatTextArea;

    @FXML
    private Label contactOrGroupNameLabel;

    @FXML
    private Circle contactOrGroupPicCircle;

    @FXML
    private Circle contactStatusCircle;

    @FXML
    private ContextMenu textStyleContextMenu;

    @FXML
    private Button textStyleButton;

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
        textStyleButton.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
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
        textStyleContextMenu.show(textStyleButton, Side.RIGHT, 0, 0);
    }

    @FXML
    void onUnderlineToggleButtonAction(ActionEvent event) {

    }
}
