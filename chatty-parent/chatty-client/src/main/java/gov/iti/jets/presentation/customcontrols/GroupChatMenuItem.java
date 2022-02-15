package gov.iti.jets.presentation.customcontrols;

import gov.iti.jets.presentation.models.GroupChatModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupChatMenuItem extends HBox implements Initializable {
    @FXML
    private Label groupChatNameLabel;

    @FXML
    private Circle groupChatPhotoCircle;

    GroupChatModel groupChatModel;

    public GroupChatMenuItem ( GroupChatModel groupChatModel ){
        this.groupChatModel = groupChatModel;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main/GroupChatMenuItem.fxml"));
        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindGroupChatPhotoCircle();
        bindGroupChatNameLabel();
    }

    private void bindGroupChatNameLabel() {
        groupChatNameLabel.textProperty().bind( groupChatModel.groupChatNameProperty() );
    }

    private void bindGroupChatPhotoCircle() {
        groupChatPhotoCircle.setFill( new ImagePattern( groupChatModel.getGroupChatPicture() ) );
        groupChatModel.groupChatPictureProperty().addListener( e -> {
            groupChatPhotoCircle.setFill( new ImagePattern( groupChatModel.getGroupChatPicture() ) );
        } );
    }
}
