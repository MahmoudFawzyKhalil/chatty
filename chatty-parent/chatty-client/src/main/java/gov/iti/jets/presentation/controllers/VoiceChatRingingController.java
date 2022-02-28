package gov.iti.jets.presentation.controllers;

import gov.iti.jets.commons.dtos.VoiceChatDto;
import gov.iti.jets.network.VoiceReceiver;
import gov.iti.jets.network.VoiceSender;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.models.VoiceChatModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.VoiceChatDao;
import gov.iti.jets.services.util.DaoFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class VoiceChatRingingController implements Initializable {

    private final VoiceChatModel voiceChatModel = ModelFactory.getInstance().getVoiceChatModel();
    private VoiceChatDao voiceChatDao = DaoFactory.getInstance().getVoiceChatDao();
    private UserModel userModel = ModelFactory.getInstance().getUserModel();
    @FXML
    private Label callerLabel;
    @FXML
    private Circle profilePictureCircle;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        callerLabel.textProperty().bind(voiceChatModel.callerNameProperty());
        bindCallerProfilePicCircle();
        onClosing();
    }

    private void onClosing() {
        voiceChatModel.inCallProperty().addListener(c -> {
            Platform.runLater(() -> {
                if (!voiceChatModel.isInCall()) {
                    VoiceSender.getInstance().closeCall();
                    VoiceReceiver.getInstance().closeCall();
                    StageCoordinator.getInstance().closeVoiceChatRinging();
                }
            });
        });
    }

    private void bindCallerProfilePicCircle() {
        profilePictureCircle.setFill(new ImagePattern(voiceChatModel.getCallerProfilePic()));
        voiceChatModel.callerProfilePicProperty().addListener(e -> {
            profilePictureCircle.setFill(new ImagePattern(voiceChatModel.getCallerProfilePic()));
        });
    }

    @FXML
    void onCloseAction(ActionEvent event) {

        try {
            VoiceChatDto voiceChatDto = new VoiceChatDto(userModel.getPhoneNumber(),
                    voiceChatModel.getCallerPhoneNumber(),
                    InetAddress.getLocalHost().getHostAddress());
            voiceChatDao.closeVoiceChat(voiceChatDto);
            voiceChatModel.setInCall(false);


        } catch (UnknownHostException | NotBoundException | RemoteException e) {
            e.printStackTrace();
        }

    }
}
