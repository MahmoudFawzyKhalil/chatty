package gov.iti.jets.presentation.controllers;

public class ChatController  /*implements Initializable*/ {
/*    private final UserModel userModel = ModelFactory.getInstance().getUserModel();
    public ToggleButton boldToggleButton;
    public ToggleButton italicToggleButton;
    public ToggleButton underlineToggleButton;
    private ContactModel contactModel;
    private GroupChatModel groupChatModel;
    private SingleMessageDao singleMessageDao = DaoFactory.getInstance().getSingleMessageDao();


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

    private String currentMessageTextStyleString;
    private String currentMessageBubbleStyleString;


    public void initialize( URL location, ResourceBundle resources ) {
        preventRightClickOnTextStyleButton();
        addCurrentlyChattingWithListener();
        setUpListViewProperties();
        populateFontComboBoxes();
        initMessageStyleMap();
        addMessageStyleMapListener();
        addFontComboBoxListeners();
        handleEnterKeyPressOnChatTextArea();

        messageStyleMap.put( "italic", "" );
    }

    private void addFontComboBoxListeners() {
        fontSizeComboBox.valueProperty().addListener( ( observable, oldValue, newValue ) -> {
            messageStyleMap.put( "font-size", newValue );
        } );

        fontFamilyComboBox.valueProperty().addListener( ( observable, oldValue, newValue ) -> {
            messageStyleMap.put( "font-family", "'" + newValue + "'" );
        } );
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

    private void initMessageStyleMap() {
        messageStyleMap.put( "bold", "" );
        messageStyleMap.put( "underline", "" );
        messageStyleMap.put( "italic", "" );
        messageStyleMap.put( "font-family", "" );
        messageStyleMap.put( "font-size", "" );
        messageStyleMap.put( "font-color", "" );
        messageStyleMap.put( "background-color", "" );
    }

    private void preventRightClickOnTextStyleButton() {
        textStyleButton.addEventFilter( ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume );
    }

    private void addCurrentlyChattingWithListener() {
        userModel.currentlyChattingWithProperty().addListener( ( observable, old, newval ) -> {
            if (userModel.getCurrentlyChattingWith() == null) {
                return;
            }

            Optional<ContactModel> contactOptional = userModel.getContacts().stream()
                    .filter( cm -> cm.getPhoneNumber().equals( newval ) )
                    .findFirst();
            if (!contactOptional.isEmpty()) {
                contactModel = contactOptional.get();
                groupChatModel = null;
                bindToContactModel();
            } else {
                Optional<GroupChatModel> groupChatOptional = userModel.getGroupChats().stream()
                        .filter( gcm -> (gcm.getGroupChatId() + "").equals( newval ) )
                        .findFirst();
                groupChatModel = groupChatOptional.get();
                contactModel = null;
                bindToGroupChatModel();
            }
        } );
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

    private void bindToContactModel() {
        PaneCoordinator.getInstance().switchToChatPane();
        bindContactNameLabel();
        bindContactPicCircle();
        bindContactChatMessagesListView();
        scrollChatMessagesListViewToLastMessage();
    }

    private void bindContactNameLabel() {
        contactOrGroupNameLabel.textProperty().bind( contactModel.displayNameProperty() );
    }

    private void bindContactPicCircle() {
        contactOrGroupPicCircle.setFill( new ImagePattern( contactModel.getProfilePicture() ) );
        contactModel.profilePictureProperty().addListener( e -> {
            contactOrGroupPicCircle.setFill( new ImagePattern( contactModel.getProfilePicture() ) );
        } );
    }

    private void bindContactChatMessagesListView() {
        chatMessagesListView.itemsProperty().bind( contactModel.messsagesProperty() );
    }

    private void scrollChatMessagesListViewToLastMessage() {
        chatMessagesListView.scrollTo( chatMessagesListView.getItems().size() - 1 );
    }

    private void bindToGroupChatModel() {
        PaneCoordinator.getInstance().switchToChatPane();
        bindGroupNameLabel();
        bindGroupPicCircle();
        bindGroupChatMessagesListView();
        scrollChatMessagesListViewToLastMessage();
    }

    private void bindGroupNameLabel() {
        contactOrGroupNameLabel.textProperty().bind( groupChatModel.groupChatNameProperty() );
    }

    private void bindGroupPicCircle() {
        contactOrGroupPicCircle.setFill( new ImagePattern( groupChatModel.getGroupChatPicture() ) );
        groupChatModel.groupChatPictureProperty().addListener( e -> {
            contactOrGroupPicCircle.setFill( new ImagePattern( groupChatModel.getGroupChatPicture() ) );
        } );
    }

    private void bindGroupChatMessagesListView() {
        chatMessagesListView.itemsProperty().bind( groupChatModel.messsagesProperty() );
    }

    @FXML
    void onSendMessageButtonAction( ActionEvent event ) {

        *//*
         * TODO
         *  Refactor this to use proper methods and to get the properties form the textStyleProperties
         *  This is part of the sendSingleMessage use case
         * *//*
        try {
            singleMessageDao.sendMessage(createMessageDto());
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        MessageModel messageModel = SingleMessageMapper.INSTANCE.dtoToModel(createMessageDto());
        messageModel.setSentByMe(true);
        messageModel.setSenderName(userModel.getDisplayName());

        if (groupChatModel == null && contactModel == null) {
            return;
        }

        if (contactModel != null) {
            contactModel.getMesssages().add(messageModel);
            scrollChatMessagesListViewToLastMessage();
        } else {
            groupChatModel.getMesssages().add( new MessageModel( "You", LocalDateTime.now(),
                    "Hello from send message button.",
                    "", "", true ) );
            scrollChatMessagesListViewToLastMessage();
        }
        scrollChatMessagesListViewToLastMessage();
    }

    private SingleMessageDto createMessageDto(){
        SingleMessageDto singleMessageDto = new SingleMessageDto();
        singleMessageDto.setMessageBody(chatTextArea.getText());
        singleMessageDto.setSenderPhoneNumber(userModel.getPhoneNumber());
        singleMessageDto.setReceiverPhoneNumber(userModel.getCurrentlyChattingWith());
        singleMessageDto.setTimeStamp(LocalDateTime.now());
        singleMessageDto.setCssTextStyleString(currentMessageTextStyleString);
        singleMessageDto.setCssBubbleStyleString(currentMessageBubbleStyleString);
        return singleMessageDto;
    }

    @FXML
    void onAttachFileButtonAction( ActionEvent event ) {

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
    }*/
}
