package gov.iti.jets.presentation.util.cellfactories;

import gov.iti.jets.commons.dtos.InvitationDecisionDto;
import gov.iti.jets.presentation.customcontrols.FileTransferItem;
import gov.iti.jets.presentation.customcontrols.InvitationItem;
import gov.iti.jets.presentation.erros.ErrorMessages;
import gov.iti.jets.presentation.models.FileModel;
import gov.iti.jets.presentation.models.GroupChatModel;
import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class FileTransferItemCellFactory implements Callback<ListView<FileModel>, ListCell<FileModel>> {

    @Override
    public ListCell<FileModel> call(ListView<FileModel> param ) {
        ListCell<FileModel> cell = new ListCell<FileModel>() {
            @Override
            public void updateItem( FileModel fileModel, boolean empty ) {
                super.updateItem( fileModel, empty );
                if (empty) {
                    setText( null );
                    setGraphic( null );
                } else if (fileModel != null) {
                    setText( null );
/*
                    FileTransferItem fileTransferItem = new FileTransferItem(fileModel);
                    fileTransferItem.getCancelButton().addEventHandler(ActionEvent.ACTION, e -> {
                            fileModel.setIsCanceled(true);
                    });*/
                    setGraphic( new FileTransferItem(fileModel));
                } else {
                    setText( "null" );
                    setGraphic( null );
                }
            }
        };
        return cell;
    }
}
