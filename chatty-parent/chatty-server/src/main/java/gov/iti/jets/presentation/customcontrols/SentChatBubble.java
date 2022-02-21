package gov.iti.jets.presentation.customcontrols;

import javafx.scene.layout.HBox;

public class SentChatBubble extends HBox  {
/*
    private final UserModel userModel = ModelFactory.getInstance().getUserModel();

    @FXML
    private VBox messageBackgroundVbox;

    @FXML
    private TextFlow textFlow;

    @FXML
    private Text messageText;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label timeStampLabel;

    @FXML
    private Circle userProfilePicCircle;

    private MessageModel messageModel;

    public SentChatBubble( MessageModel messageModel ) {
        this.messageModel = messageModel;

        FXMLLoader loader = new FXMLLoader( getClass().getResource( "/views/chat/SentChatBubble.fxml" ) );
        loader.setController( this );
        loader.setRoot( this );

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        bindUserProfilePicCircle();
        bindMessageTextAndBackgroundProperties();
        bindUserNameLabel();
        bindTimeStampLabel();
    }

    private void bindUserProfilePicCircle() {
        userProfilePicCircle.setFill( new ImagePattern( userModel.getProfilePicture() ) );
        userModel.profilePictureProperty().addListener( e -> {
            userProfilePicCircle.setFill( new ImagePattern( userModel.getProfilePicture() ) );
        } );
    }

    private void bindMessageTextAndBackgroundProperties() {
        messageBackgroundVbox.setStyle( messageModel.getCssBubbleStyleString() );
        messageText.setText( messageModel.getMessageBody() );
        messageText.setStyle( messageModel.getCssTextStyleString() );
    }

    private void bindUserNameLabel() {
        userNameLabel.textProperty().bind( messageModel.senderNameProperty() );
    }

    private void bindTimeStampLabel() {
        timeStampLabel.setText( messageModel.getTimeStamp().format( DateTimeFormatter.ofLocalizedDateTime( FormatStyle.MEDIUM ) ) );
    }

    public TextFlow getTextFlow() {
        return textFlow;
    }*/
}
