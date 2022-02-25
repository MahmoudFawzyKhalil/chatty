package gov.iti.jets.presentation.customcontrols;

import gov.iti.jets.presentation.models.MessageModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

public class SentChatBubble extends HBox implements Initializable {
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
        hideUserProfilePicCircle();
        bindMessageTextAndBackgroundProperties();
        bindUserNameLabel();
        bindTimeStampLabel();
    }

    private void hideUserProfilePicCircle() {
        userProfilePicCircle.setVisible( false );
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
    }
}
