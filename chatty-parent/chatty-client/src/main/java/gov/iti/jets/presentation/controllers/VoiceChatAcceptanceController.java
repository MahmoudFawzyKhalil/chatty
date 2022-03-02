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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class VoiceChatAcceptanceController implements Initializable {
    private VoiceChatModel voiceChatModel = ModelFactory.getInstance().getVoiceChatModel();
    private UserModel userModel = ModelFactory.getInstance().getUserModel();
    private VoiceChatDao voiceChatDao = DaoFactory.getInstance().getVoiceChatDao();
    private MediaPlayer mediaPlayer;
    @FXML
    private Label callerLabel;

    @FXML
    private Circle callerProfilePicCircle;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setRinging();
        bindCallerProfilePicCircle();
        callerLabel.textProperty().bind(voiceChatModel.callerNameProperty());
        onClosing();

    }

    private void onClosing() {
        Platform.runLater(() -> {
            voiceChatModel.inCallProperty().addListener(c -> {
                if (!voiceChatModel.isInCall()) {
                    VoiceSender.getInstance().closeCall();
                    VoiceReceiver.getInstance().closeCall();
                    mediaPlayer.stop();
                    StageCoordinator.getInstance().closeVoiceChatAcceptance();
                }
            });
        });

    }

    private void setRinging() {
        Media media = new Media(VoiceChatAcceptanceController.class.getResource("/sounds/ringing.wav").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
    }

    private void bindCallerProfilePicCircle() {
        callerProfilePicCircle.setFill(new ImagePattern(voiceChatModel.getCallerProfilePic()));
        voiceChatModel.callerProfilePicProperty().addListener(e -> {
            callerProfilePicCircle.setFill(new ImagePattern(voiceChatModel.getCallerProfilePic()));
        });
    }

    @FXML
    void onAcceptAction(ActionEvent event) {
        try {
            VoiceChatDto voiceChatDto = new VoiceChatDto(userModel.getPhoneNumber(),
                    voiceChatModel.getCallerPhoneNumber(),
                    InetAddress.getLocalHost().getHostAddress());
            voiceChatModel.setInCall(true);
            voiceChatDao.startVoiceChat(voiceChatDto);
            mediaPlayer.stop();
            StageCoordinator.getInstance().showVoiceChatCallStage();
            StageCoordinator.getInstance().closeVoiceChatAcceptance();

        } catch (UnknownHostException | RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onRejectAction(ActionEvent event) {
        mediaPlayer.stop();
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