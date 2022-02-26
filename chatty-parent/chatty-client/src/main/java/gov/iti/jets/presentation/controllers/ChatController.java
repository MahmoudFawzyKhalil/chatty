package gov.iti.jets.presentation.controllers;

import gov.iti.jets.commons.dtos.FileTransferPermissionDto;
import gov.iti.jets.commons.dtos.FileTransferResponseDto;
import gov.iti.jets.commons.dtos.GroupMessageDto;
import gov.iti.jets.commons.dtos.SingleMessageDto;
import gov.iti.jets.presentation.models.*;
import gov.iti.jets.presentation.models.mappers.GroupMessageMapper;
import gov.iti.jets.presentation.models.mappers.SingleMessageMapper;
import gov.iti.jets.presentation.util.ExecutorUtil;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.PaneCoordinator;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presentation.util.cellfactories.ChatBubbleCellFactory;
import gov.iti.jets.presentation.util.cellfactories.NoSelectionModel;
import gov.iti.jets.services.FileTransferDao;
import gov.iti.jets.services.GroupMessageDao;
import gov.iti.jets.services.SingleMessageDao;
import gov.iti.jets.services.util.DaoFactory;
import gov.iti.jets.services.util.ServiceFactory;
import gov.iti.jets.services.util.FileTransferTask;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.rmi.ConnectException;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final UserModel userModel = ModelFactory.getInstance().getUserModel();
    private final FileTransferOperationAvailabilityModel fileTransferOperationAvailabilityModel = ModelFactory.getInstance().getFileTransferOperationAvailabilityModel();
    public ToggleButton boldToggleButton;
    public ToggleButton italicToggleButton;
    public ToggleButton underlineToggleButton;
    private ContactModel contactModel;
    private GroupChatModel groupChatModel;
    private SingleMessageDao singleMessageDao = DaoFactory.getInstance().getSingleMessageDao();
    private GroupMessageDao groupMessageDao = DaoFactory.getInstance().getGroupMessageDao();
    private FileTransferDao fileTransferDao = DaoFactory.getInstance().getFileTransferDao();
    private FileChooser fileChooser = new FileChooser();
    private File file;
    private ExecutorUtil executorUtil = ExecutorUtil.getInstance();

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


    public void initialize(URL location, ResourceBundle resources) {
        preventRightClickOnTextStyleButton();
        addCurrentlyChattingWithListener();
        setUpListViewProperties();
        populateFontComboBoxes();
        initMessageStyleMap();
        addMessageStyleMapListener();
        addFontComboBoxListeners();
        handleEnterKeyPressOnChatTextArea();
    }

    private void addFontComboBoxListeners() {
        fontSizeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            messageStyleMap.put("font-size", newValue);
        });

        fontFamilyComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            messageStyleMap.put("font-family", "'" + newValue + "'");
        });
    }

    private void addMessageStyleMapListener() {
        messageStyleMap.addListener(new MapChangeListener<String, String>() {

            @Override
            public void onChanged(Change<? extends String, ? extends String> change) {
                String bold = messageStyleMap.get("bold").isEmpty() ? "" : "-fx-font-weight: " + messageStyleMap.get("bold") + "; ";
                String underline = messageStyleMap.get("underline").isEmpty() ? "" : "-fx-underline: " + messageStyleMap.get("underline") + "; ";
                String italic = messageStyleMap.get("italic").isEmpty() ? "" : "-fx-font-style: " + messageStyleMap.get("italic") + "; ";
                String fontFamily = messageStyleMap.get("font-family").isEmpty() ? "" : "-fx-font-family: " + messageStyleMap.get("font-family") + "; ";
                String fontSize = messageStyleMap.get("font-size").isEmpty() ? "" : "-fx-font-size: " + messageStyleMap.get("font-size") + "; ";
                String textAreaFontColor = messageStyleMap.get("font-color").isEmpty() ? "" : "-fx-text-fill: " + messageStyleMap.get("font-color") + "; ";
                String messageFontColor = messageStyleMap.get("font-color").isEmpty() ? "" : "-fx-fill: " + messageStyleMap.get("font-color") + "; ";
                String messageBackgroundColor = messageStyleMap.get("background-color").isEmpty() ? "" : "-fx-background-color: " + messageStyleMap.get("background-color") + "; ";
                String indicatorBackgroundColor = messageStyleMap.get("background-color").isEmpty() ? "" : "-fx-fill: " + messageStyleMap.get("background-color") + "; ";

                String textAreaStyleString = bold + underline + italic + fontFamily + fontSize + textAreaFontColor + messageFontColor;
                currentMessageTextStyleString = bold + underline + italic + fontFamily + fontSize + messageFontColor;
                currentMessageBubbleStyleString = messageBackgroundColor;

                chatTextArea.setStyle(textAreaStyleString);
                textBackgroundIndicatorCircle.setStyle(indicatorBackgroundColor);
            }
        });
    }

    private void initMessageStyleMap() {
        messageStyleMap.put("bold", "");
        messageStyleMap.put("underline", "");
        messageStyleMap.put("italic", "");
        messageStyleMap.put("font-family", "");
        messageStyleMap.put("font-size", "");
        messageStyleMap.put("font-color", "");
        messageStyleMap.put("background-color", "");
    }

    private void preventRightClickOnTextStyleButton() {
        textStyleButton.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
    }

    private void addCurrentlyChattingWithListener() {
        userModel.currentlyChattingWithProperty().addListener((observable, old, newval) -> {
            if (userModel.getCurrentlyChattingWith() == null) {
                return;
            }

            Optional<ContactModel> contactOptional = userModel.getContacts().stream()
                    .filter(cm -> cm.getPhoneNumber().equals(newval))
                    .findFirst();
            if (!contactOptional.isEmpty()) {
                contactModel = contactOptional.get();
                groupChatModel = null;
                bindToContactModel();
            } else {
                Optional<GroupChatModel> groupChatOptional = userModel.getGroupChats().stream()
                        .filter(gcm -> (gcm.getGroupChatId() + "").equals(newval))
                        .findFirst();
                groupChatModel = groupChatOptional.get();
                contactModel = null;
                bindToGroupChatModel();
            }
        });
    }

    private void setUpListViewProperties() {
        chatMessagesListView.setCellFactory(new ChatBubbleCellFactory());
        chatMessagesListView.setSelectionModel(new NoSelectionModel<>());
    }

    private void populateFontComboBoxes() {
        ObservableList<String> sizes = FXCollections.observableArrayList();
        sizes.addAll("10", "12", "14", "16", "18", "20", "22", "24");
        fontSizeComboBox.itemsProperty().set(sizes);
        fontSizeComboBox.getSelectionModel().select("12");

        ObservableList<String> fonts = FXCollections.observableArrayList(Font.getFontNames());
        fontFamilyComboBox.itemsProperty().set(fonts);
        fontFamilyComboBox.getSelectionModel().select("Helvetica");
    }

    private void handleEnterKeyPressOnChatTextArea() {
        chatTextArea.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER && keyEvent.isShiftDown()) {
                chatTextArea.appendText("\n");
                return;
            }

            if (keyEvent.getCode() == KeyCode.ENTER) {
                keyEvent.consume();
                if (chatTextArea.getText().isEmpty()) return;
                onSendMessageButtonAction(new ActionEvent());
            }
        });
    }

    private void bindToContactModel() {
        PaneCoordinator.getInstance().switchToChatPane();
        bindContactNameLabel();
        bindContactPicCircle();
        bindContactChatMessagesListView();
        scrollChatMessagesListViewToLastMessage();
    }

    private void bindContactNameLabel() {
        contactOrGroupNameLabel.textProperty().bind(contactModel.displayNameProperty());
    }

    private void bindContactPicCircle() {
        contactOrGroupPicCircle.setFill(new ImagePattern(contactModel.getProfilePicture()));
        contactModel.profilePictureProperty().addListener(e -> {
            contactOrGroupPicCircle.setFill(new ImagePattern(contactModel.getProfilePicture()));
        });
    }

    private void bindContactChatMessagesListView() {
        chatMessagesListView.itemsProperty().bind(contactModel.messsagesProperty());
    }

    private void scrollChatMessagesListViewToLastMessage() {
        chatMessagesListView.scrollTo(chatMessagesListView.getItems().size() - 1);
    }

    private void bindToGroupChatModel() {
        PaneCoordinator.getInstance().switchToChatPane();
        bindGroupNameLabel();
        bindGroupPicCircle();
        bindGroupChatMessagesListView();
        scrollChatMessagesListViewToLastMessage();
    }

    private void bindGroupNameLabel() {
        contactOrGroupNameLabel.textProperty().bind(groupChatModel.groupChatNameProperty());
    }

    private void bindGroupPicCircle() {
        contactOrGroupPicCircle.setFill(new ImagePattern(groupChatModel.getGroupChatPicture()));
        groupChatModel.groupChatPictureProperty().addListener(e -> {
            contactOrGroupPicCircle.setFill(new ImagePattern(groupChatModel.getGroupChatPicture()));
        });
    }

    private void bindGroupChatMessagesListView() {
        chatMessagesListView.itemsProperty().bind(groupChatModel.messsagesProperty());
    }

    @FXML
    void onSendMessageButtonAction(ActionEvent event) {

        if (chatTextArea.getText().isEmpty() || chatTextArea.getText().isBlank()){
            chatTextArea.setText( "" );
            return;
        }

        if (groupChatModel == null && contactModel == null) {
            return;
        }


        try {
            singleMessageDao.sendMessage(createMessageDto());

        } catch (NoSuchObjectException | NotBoundException | ConnectException c) {
            ServiceFactory.getInstance().shutdown();
            StageCoordinator.getInstance().showErrorNotification("Failed to connect to server. Please try again later.");
            ModelFactory.getInstance().clearUserModel();
            ModelFactory.getInstance().clearUserModel();
            StageCoordinator.getInstance().switchToConnectToServer();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        MessageModel messageModel = SingleMessageMapper.INSTANCE.dtoToModel(createMessageDto());
        messageModel.setSentByMe(true);
        messageModel.setSenderName(userModel.getDisplayName());


        if (contactModel != null) {
            contactModel.getMesssages().add(messageModel);
            chatTextArea.setText( "" );
            scrollChatMessagesListViewToLastMessage();
        } else {
            try {
                groupMessageDao.sendGroupMessage(createGroupMessageDto());
            } catch (NoSuchObjectException | NotBoundException | ConnectException c) {
                ServiceFactory.getInstance().shutdown();
                StageCoordinator.getInstance().showErrorNotification("Failed to connect to server. Please try again later.");
                ModelFactory.getInstance().clearUserModel();
                StageCoordinator.getInstance().switchToConnectToServer();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            MessageModel groupMessageModel = GroupMessageMapper.INSTANCE.dtoToModel(createGroupMessageDto());
            groupMessageModel.setSentByMe(true);
            groupMessageModel.setSenderName(userModel.getDisplayName());

            groupChatModel.getMesssages().add(groupMessageModel);
            scrollChatMessagesListViewToLastMessage();
            chatTextArea.setText( "" );
        }
        scrollChatMessagesListViewToLastMessage();
    }

    private SingleMessageDto createMessageDto() {
        SingleMessageDto singleMessageDto = new SingleMessageDto();
        singleMessageDto.setMessageBody(chatTextArea.getText());
        singleMessageDto.setSenderPhoneNumber(userModel.getPhoneNumber());
        singleMessageDto.setReceiverPhoneNumber(userModel.getCurrentlyChattingWith());
        singleMessageDto.setTimeStamp(LocalDateTime.now());
        singleMessageDto.setCssTextStyleString(currentMessageTextStyleString);
        singleMessageDto.setCssBubbleStyleString(currentMessageBubbleStyleString);
        return singleMessageDto;
    }

    private GroupMessageDto createGroupMessageDto() {
        Integer groupId = Integer.valueOf(userModel.getCurrentlyChattingWith());
        GroupMessageDto groupMessageDto = new GroupMessageDto();
        groupMessageDto.setGroupChatId(groupId);
        groupMessageDto.setSenderPhoneNumber(userModel.getPhoneNumber());
        groupMessageDto.setMessageBody(chatTextArea.getText());
        groupMessageDto.setTimeStamp(LocalDateTime.now());
        groupMessageDto.setCssTextStyleString(currentMessageTextStyleString);
        groupMessageDto.setCssBubbleStyleString(currentMessageBubbleStyleString);
        return groupMessageDto;

    }

    @FXML
    void onAttachFileButtonAction(ActionEvent event) {
        if(!fileTransferOperationAvailabilityModel.isAvailable()){
            stageCoordinator.showMessageNotification("File Transfer Operation Availability",
                    "operation is not available now try later");
            return;
        }
        file = fileChooser.showOpenDialog(null);
        if(file==null){
            return;
        }
        ContactModel contactModel = getContactModel();
        FileTransferPermissionDto fileTransferPermissionDto = createFileTransferDto(file);
        try {
            stageCoordinator.showMessageNotification("File Transfer Permission",
                    "sending file transfer permission to "+ contactModel.getDisplayName());
            boolean result =fileTransferDao.askForPermissionToSendFile(fileTransferPermissionDto);

            if (result == false) {
                stageCoordinator.showMessageNotification("File Transfer Permission","can not send, "+
                        contactModel.getDisplayName() + "is offline now");
            }
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private FileTransferPermissionDto createFileTransferDto(File file){
        FileTransferPermissionDto fileTransferPermissionDto = new FileTransferPermissionDto();
        fileTransferPermissionDto.setFile(file);
        fileTransferPermissionDto.setFileName(file.getName());
        fileTransferPermissionDto.setFileSize(file.length());
        fileTransferPermissionDto.setSenderPhoneNumber(userModel.getPhoneNumber());
        fileTransferPermissionDto.setReceiverPhoneNumber(userModel.getCurrentlyChattingWith());
        return fileTransferPermissionDto;
    }

    private ContactModel getContactModel(){
        Optional<ContactModel> contactModel = userModel.getContacts().stream()
                .filter(cm -> cm.getPhoneNumber() == userModel.getCurrentlyChattingWith()).findFirst();
        return contactModel.get();
    }

    private FileModel createFileModel(File file ){
        FileModel fileModel = new FileModel();
        fileModel.setFile(file);
        fileModel.setFileSize(file.length());
        fileModel.setFileName(file.getName());
        fileModel.setNumberOfBytesSoFar(0);
        fileModel.setSenderName(userModel.getDisplayName());
        fileModel.setIsCanceled(false);
        return fileModel;
    }

    @FXML
    void onBoldToggleButtonAction(ActionEvent event) {
        if (boldToggleButton.isSelected()) {
            messageStyleMap.put("bold", "bold");
        } else {
            messageStyleMap.put("bold", "");
        }
    }

    @FXML
    void onItalicToggleButtonAction(ActionEvent event) {
        if (italicToggleButton.isSelected()) {
            messageStyleMap.put("italic", "italic");
        } else {
            messageStyleMap.put("italic", "");
        }
    }

    @FXML
    void onUnderlineToggleButtonAction(ActionEvent event) {
        if (underlineToggleButton.isSelected()) {
            messageStyleMap.put("underline", "true");
        } else {
            messageStyleMap.put("underline", "");
        }
    }

    @FXML
    void onTextStyleButtonAction(ActionEvent event) {
        showTextStyleContextMenu();
    }

    private void showTextStyleContextMenu() {
        textStyleContextMenu.show(textStyleButton, Side.RIGHT, 0, 0);
    }

    public void onMessageTextColorPickerAction(ActionEvent actionEvent) {
        String colorString = "#" + messageTextColorPicker.getValue().toString().substring(2, 8);
        messageStyleMap.put("font-color", colorString);
    }

    public void onMessageBakckgroundColorPickerAction(ActionEvent actionEvent) {
        String colorString = "#" + messageBackgroundColorPicker.getValue().toString().substring(2, 8);
        messageStyleMap.put("background-color", colorString);
    }

}
