package gov.iti.jets.presentation.controllers;

import gov.iti.jets.commons.dtos.SingleMessageDto;
import gov.iti.jets.presentation.models.ContactModel;
import gov.iti.jets.presentation.models.GroupChatModel;
import gov.iti.jets.presentation.models.MessageModel;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.models.mappers.SingleMessageMapper;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.PaneCoordinator;
import gov.iti.jets.presentation.util.cellfactories.ChatBubbleCellFactory;
import gov.iti.jets.presentation.util.cellfactories.NoSelectionModel;
import gov.iti.jets.services.SingleMessageDao;
import gov.iti.jets.services.util.DaoFactory;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    private final UserModel userModel = ModelFactory.getInstance().getUserModel();
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
    private ChoiceBox<String> fontFamilyChoiceBox;

    @FXML
    private ChoiceBox<String> fontSizeChoiceBox;

    @FXML
    private ColorPicker messageBackgroundColorPicker;

    @FXML
    private ColorPicker messageTextColorPicker;


    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        preventRightClickOnTextStyleButton();
        addCurrentlyChattingWithListener();
        setUpListViewProperties();
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
    void onSendMessageButtonAction( ActionEvent event ) throws NotBoundException, RemoteException {

        /*
         * TODO
         *  Refactor this to use proper methods and to get the properties form the textStyleProperties
         *  This is part of the sendSingleMessage use case
         * */
        singleMessageDao.sendMessage(createMessageDto());
        MessageModel messageModel = SingleMessageMapper.INSTANCE.dtoToModel(createMessageDto());
        messageModel.setSentByMe(true);

        if (groupChatModel == null && contactModel == null) {
            return;
        }

        if (contactModel != null) {
            contactModel.getMesssages().add( messageModel);
            scrollChatMessagesListViewToLastMessage();
        } else {
            groupChatModel.getMesssages().add( new MessageModel( "You", LocalDateTime.now(),
                    "Hello from send message button.",
                    "", "", true ) );
            scrollChatMessagesListViewToLastMessage();
        }
        scrollChatMessagesListViewToLastMessage();
    }

    SingleMessageDto createMessageDto(){
        SingleMessageDto singleMessageDto = new SingleMessageDto();
        singleMessageDto.setSenderName(userModel.getDisplayName());
        singleMessageDto.setMessageBody("Hello this is a test single message");
        singleMessageDto.setSenderPhoneNumber(userModel.getPhoneNumber());
        singleMessageDto.setReceiverPhoneNumber(userModel.getCurrentlyChattingWith());
        singleMessageDto.setTimeStamp(LocalDateTime.now());
        singleMessageDto.setCssTextStyleString("");
        singleMessageDto.setCssBubbleStyleString("");

        return singleMessageDto;
    }

    @FXML
    void onAttachFileButtonAction( ActionEvent event ) {

    }

    @FXML
    void onBoldToggleButtonAction( ActionEvent event ) {
        /*
         * Use an ArrayList<String> to store the text styles in them then concatenate it into one string
         * to store in the MessageModel's cssStyleString properties + dtos' styleStringProperties
         * */
    }

    @FXML
    void onItalicToggleButtonAction( ActionEvent event ) {

    }

    @FXML
    void onUnderlineToggleButtonAction( ActionEvent event ) {

    }

    @FXML
    void onTextStyleButtonAction( ActionEvent event ) {
        showTextStyleContextMenu();
    }

    private void showTextStyleContextMenu() {
        textStyleContextMenu.show( textStyleButton, Side.RIGHT, 0, 0 );
    }
}
