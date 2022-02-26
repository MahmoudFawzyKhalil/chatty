package gov.iti.jets.presentation.controllers;

import gov.iti.jets.commons.dtos.AnnouncementDto;
import gov.iti.jets.presentation.models.MessageModel;
import gov.iti.jets.presentation.models.ServerModel;
import gov.iti.jets.presentation.util.ChatBubbleCellFactory;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.NoSelectionModel;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.ServerNotificationsService;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ChatController  implements Initializable {

    @FXML
    private ToggleButton boldToggleButton;
    @FXML
    private ToggleButton italicToggleButton;
    @FXML
    private ToggleButton underlineToggleButton;
    @FXML
    private BorderPane centerBorderPane;
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
    private ComboBox<String> fontFamilyComboBox;
    @FXML
    private ComboBox<String> fontSizeComboBox;
    @FXML
    private ColorPicker messageBackgroundColorPicker;
    @FXML
    private ColorPicker messageTextColorPicker;
    @FXML
    private Circle textBackgroundIndicatorCircle;

    private ObservableMap<String, String> messageStyleMap = FXCollections.observableHashMap();
    private String currentMessageTextStyleString = "";
    private String currentMessageBubbleStyleString = "";
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ServerNotificationsService serverNotificationsService = serviceFactory.getServerNotificationsService();
    private final ServerModel serverModel = ModelFactory.getInstance().getServerModel();
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();

    public void initialize( URL location, ResourceBundle resources ) {
        bindAnnouncementsListView();
        preventRightClickOnTextStyleButton();
        setUpListViewProperties();
        populateFontComboBoxes();
        initMessageStyleMap();
        addMessageStyleMapListener();
        addFontComboBoxListeners();
        handleEnterKeyPressOnChatTextArea();
    }

    private void bindAnnouncementsListView() {
        chatMessagesListView.itemsProperty().bind( serverModel.announcementsProperty() );
    }

    private void addFontComboBoxListeners() {
        fontSizeComboBox.valueProperty().addListener( ( observable, oldValue, newValue ) -> {
            messageStyleMap.put( "font-size", newValue );
        } );

        fontFamilyComboBox.valueProperty().addListener( ( observable, oldValue, newValue ) -> {
            messageStyleMap.put( "font-family", "'" + newValue + "'" );
        } );
    }

    private void initMessageStyleMap() {
        messageStyleMap.put( "bold", "" );
        messageStyleMap.put( "underline", "" );
        messageStyleMap.put( "italic", "" );
        messageStyleMap.put( "font-family", "" );
        messageStyleMap.put( "font-size", "" );
        messageStyleMap.put( "font-color", "" );
        messageStyleMap.put( "background-color", "" );
    }

    private void addMessageStyleMapListener() {
        messageStyleMap.addListener( new MapChangeListener<String, String>() {

            @Override
            public void onChanged( Change<? extends String, ? extends String> change ) {
                String bold = messageStyleMap.get( "bold" ).isEmpty() ? "" : "-fx-font-weight: " + messageStyleMap.get( "bold" ) + "; ";
                String underline = messageStyleMap.get( "underline" ).isEmpty() ? "" : "-fx-underline: " + messageStyleMap.get( "underline" ) + "; ";
                String italic = messageStyleMap.get( "italic" ).isEmpty() ? "" : "-fx-font-style: " + messageStyleMap.get( "italic" ) + "; ";
                String fontFamily = messageStyleMap.get( "font-family" ).isEmpty() ? "" : "-fx-font-family: " + messageStyleMap.get( "font-family" ) + "; ";
                String fontSize = messageStyleMap.get( "font-size" ).isEmpty() ? "" : "-fx-font-size: " + messageStyleMap.get( "font-size" ) + "; ";
                String textAreaFontColor = messageStyleMap.get( "font-color" ).isEmpty() ? "" : "-fx-text-fill: " + messageStyleMap.get( "font-color" ) + "; ";
                String messageFontColor = messageStyleMap.get( "font-color" ).isEmpty() ? "" : "-fx-fill: " + messageStyleMap.get( "font-color" ) + "; ";
                String messageBackgroundColor = messageStyleMap.get( "background-color" ).isEmpty() ? "" : "-fx-background-color: " + messageStyleMap.get( "background-color" ) + "; ";
                String indicatorBackgroundColor = messageStyleMap.get( "background-color" ).isEmpty() ? "" : "-fx-fill: " + messageStyleMap.get( "background-color" ) + "; ";

                String textAreaStyleString = bold + underline + italic + fontFamily + fontSize + textAreaFontColor + messageFontColor;
                currentMessageTextStyleString = bold + underline + italic + fontFamily + fontSize + messageFontColor;
                currentMessageBubbleStyleString = messageBackgroundColor;

                chatTextArea.setStyle( textAreaStyleString );
                textBackgroundIndicatorCircle.setStyle( indicatorBackgroundColor );
            }
        } );
    }

    private void preventRightClickOnTextStyleButton() {
        textStyleButton.addEventFilter( ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume );
    }


    private void setUpListViewProperties() {
        chatMessagesListView.setCellFactory( new ChatBubbleCellFactory() );
        chatMessagesListView.setSelectionModel( new NoSelectionModel<>() );
    }

    private void populateFontComboBoxes() {
        ObservableList<String> sizes = FXCollections.observableArrayList();
        sizes.addAll( "10", "12", "14", "16", "18", "20", "22", "24" );
        fontSizeComboBox.itemsProperty().set( sizes );
        fontSizeComboBox.getSelectionModel().select( "12" );

        ObservableList<String> fonts = FXCollections.observableArrayList( Font.getFontNames() );
        fontFamilyComboBox.itemsProperty().set( fonts );
        fontFamilyComboBox.getSelectionModel().select( "Helvetica" );
    }

    private void handleEnterKeyPressOnChatTextArea() {
        chatTextArea.addEventFilter( KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER && keyEvent.isShiftDown()) {
                chatTextArea.appendText( "\n" );
                return;
            }

            if (keyEvent.getCode() == KeyCode.ENTER) {
                keyEvent.consume();
                if (chatTextArea.getText().isEmpty()) return;
                onSendMessageButtonAction( new ActionEvent() );
            }
        } );
    }

    private void scrollChatMessagesListViewToLastMessage() {
        chatMessagesListView.scrollTo( chatMessagesListView.getItems().size() - 1 );
    }


    @FXML
    void onSendMessageButtonAction( ActionEvent event ) {

        if (chatTextArea.getText().length() > 700){
            stageCoordinator.showErrorNotification( "Can't send an announcement longer than 700 characters." );
            chatTextArea.setText( "" );
            return;
        }

        if (chatTextArea.getText().isEmpty() || chatTextArea.getText().isBlank()){
            chatTextArea.setText( "" );
            return;
        }

        AnnouncementDto announcementDto = createAnnouncementDto();

        serverNotificationsService.sendAnnouncementToClients( announcementDto );

        MessageModel messageModel = new MessageModel( announcementDto.getAdminName(),
                announcementDto.getTimeStamp(),
                announcementDto.getMessageBody(),
                announcementDto.getCssTextStyleString(),
                announcementDto.getCssBubbleStyleString(),
                true);

        serverModel.getAnnouncements().add( messageModel );

        chatTextArea.setText( "" );
        scrollChatMessagesListViewToLastMessage();
    }

    private AnnouncementDto createAnnouncementDto(){
        return new AnnouncementDto( chatTextArea.getText(),
                LocalDateTime.now(),
                this.currentMessageTextStyleString,
                this.currentMessageBubbleStyleString);
    }

    @FXML
    void onBoldToggleButtonAction( ActionEvent event ) {
        if (boldToggleButton.isSelected()) {
            messageStyleMap.put( "bold", "bold" );
        } else {
            messageStyleMap.put( "bold", "" );
        }
    }

    @FXML
    void onItalicToggleButtonAction( ActionEvent event ) {
        if (italicToggleButton.isSelected()) {
            messageStyleMap.put( "italic", "italic" );
        } else {
            messageStyleMap.put( "italic", "" );
        }
    }

    @FXML
    void onUnderlineToggleButtonAction( ActionEvent event ) {
        if (underlineToggleButton.isSelected()) {
            messageStyleMap.put( "underline", "true" );
        } else {
            messageStyleMap.put( "underline", "" );
        }
    }

    @FXML
    void onTextStyleButtonAction( ActionEvent event ) {
        showTextStyleContextMenu();
    }

    private void showTextStyleContextMenu() {
        textStyleContextMenu.show( textStyleButton, Side.RIGHT, 0, 0 );
    }

    public void onMessageTextColorPickerAction( ActionEvent actionEvent ) {
        String colorString = "#" + messageTextColorPicker.getValue().toString().substring( 2, 8 );
        messageStyleMap.put( "font-color", colorString );
    }

    public void onMessageBakckgroundColorPickerAction( ActionEvent actionEvent ) {
        String colorString = "#" + messageBackgroundColorPicker.getValue().toString().substring( 2, 8 );
        messageStyleMap.put( "background-color", colorString );
    }
}
