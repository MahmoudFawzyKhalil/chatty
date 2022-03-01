package gov.iti.jets.services.util;
import gov.iti.jets.commons.dtos.FileTransferPermissionDto;
import gov.iti.jets.presentation.models.ContactModel;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.StageStyle;

import java.util.Optional;
import java.util.concurrent.Callable;

public class FileTransferAcceptanceCallable implements Callable<Boolean> {
    private FileTransferPermissionDto fileTransferPermissionDto;

    private ContactModel contactModel;

    public FileTransferAcceptanceCallable(FileTransferPermissionDto fileTransferPermissionDto, ContactModel contactModel) {
        this.fileTransferPermissionDto = fileTransferPermissionDto;
        this.contactModel = contactModel;
    }
    @Override
    public Boolean call() throws Exception {
        Alert confirmationDialog = creatDialog();
        confirmationDialog.setContentText(contactModel.getDisplayName() + " wants to send you a file " +
                fileTransferPermissionDto.getFileName());
        Optional<ButtonType> answer = confirmationDialog.showAndWait();
        if (answer.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    private Alert creatDialog(){
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setHeaderText("File Transfer Confirmation");

        SVGPath svgPath = new SVGPath();
        svgPath.setFill(Color.web("#EEEEEE"));
        svgPath.setContent("M15.75,9.563V0H1.688A1.683,1.683,0,0,0,0,1.688V34.313A1.683,1.683,0,0,0,1.688,36H25.313A1.683,1.683,0,0,0,27,34.313V11.25H17.438A1.692,1.692,0,0,1,15.75,9.563Zm4.5,16.594a.846.846,0,0,1-.844.844H7.594a.846.846,0,0,1-.844-.844v-.562a.846.846,0,0,1,.844-.844H19.406a.846.846,0,0,1,.844.844Zm0-4.5a.846.846,0,0,1-.844.844H7.594a.846.846,0,0,1-.844-.844v-.562a.846.846,0,0,1,.844-.844H19.406a.846.846,0,0,1,.844.844Zm0-5.062v.563a.846.846,0,0,1-.844.844H7.594a.846.846,0,0,1-.844-.844v-.562a.846.846,0,0,1,.844-.844H19.406A.846.846,0,0,1,20.25,16.594ZM27,8.571V9H18V0h.429a1.686,1.686,0,0,1,1.2.492l6.884,6.891A1.682,1.682,0,0,1,27,8.571Z");
        svgPath.setScaleX(0.8);
        svgPath.setScaleY(0.8);
        confirmationDialog.setGraphic(svgPath);
        confirmationDialog.initStyle(StageStyle.TRANSPARENT);

        DialogPane dialog = confirmationDialog.getDialogPane();
        dialog.getStylesheets().add(getClass().getResource("/styles/alertStyle.css").toString());
        dialog.getStyleClass().add("dialog-pain");

        return confirmationDialog;
    }
}
