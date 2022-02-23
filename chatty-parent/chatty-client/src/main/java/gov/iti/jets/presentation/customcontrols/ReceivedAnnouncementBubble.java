package gov.iti.jets.presentation.customcontrols;

import gov.iti.jets.commons.dtos.AnnouncementDto;
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
import java.util.ResourceBundle;

public class ReceivedAnnouncementBubble extends HBox implements Initializable {

    @FXML
    private VBox messageBackgroundVbox;

    @FXML
    private Text messageText;

    @FXML
    private TextFlow textFlow;

    @FXML
    private Label senderNameLabel;

    @FXML
    private Label timeStampLabel;

    @FXML
    private Circle senderProfilePicCircle;

    private AnnouncementDto announcementDto;

    public ReceivedAnnouncementBubble( AnnouncementDto announcementDto ) {
        this.announcementDto = announcementDto;

        FXMLLoader loader = new FXMLLoader( getClass().getResource( "/views/chat/ReceivedChatBubble.fxml" ) );
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
        senderProfilePicCircle.setVisible( false );
        messageText.setText( announcementDto.getMessageBody() );
        senderNameLabel.setText( announcementDto.getAdminName() );
        timeStampLabel.setText( announcementDto.getTimeStamp().toString() );

        messageText.setStyle( announcementDto.getCssTextStyleString() );
        messageBackgroundVbox.setStyle( announcementDto.getCssBubbleStyleString() );
    }
}
