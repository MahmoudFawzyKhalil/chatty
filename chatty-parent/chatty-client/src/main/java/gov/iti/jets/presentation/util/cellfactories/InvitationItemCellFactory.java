package gov.iti.jets.presentation.util.cellfactories;

import gov.iti.jets.commons.dtos.InvitationDecisionDto;
import gov.iti.jets.presentation.customcontrols.InvitationItem;
import gov.iti.jets.presentation.erros.ErrorMessages;
import gov.iti.jets.presentation.models.InvitationModel;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.services.InvitationDecisionDao;
import gov.iti.jets.services.util.DaoFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class InvitationItemCellFactory implements Callback<ListView<InvitationModel>, ListCell<InvitationModel>> {
    private UserModel userModel = ModelFactory.getInstance().getUserModel();

    @Override
    public ListCell<InvitationModel> call(ListView<InvitationModel> param) {

        InvitationDecisionDao dao = DaoFactory.getInstance().getInvitationDecisionDao();

        return new ListCell<>(){
            @Override
            public void updateItem(InvitationModel invitationModel, boolean empty) {
                super.updateItem(invitationModel, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else if (invitationModel != null) {
                    setText(null);

                    InvitationItem invitationItem = new InvitationItem(invitationModel);

                    // Handle accept button
                    invitationItem.getAcceptButton().addEventHandler(ActionEvent.ACTION, e -> {
                        try {
                            boolean succeeded = dao.acceptInvite(
                                    new InvitationDecisionDto(userModel.getPhoneNumber(),
                                            invitationModel.getContactModel().getPhoneNumber()));
                            if (succeeded){
                                StageCoordinator.getInstance().showMessageNotification("Friend request accepted", "");
                                userModel.getInvitations().removeIf( im -> im.getContactModel().getPhoneNumber()
                                        .equals(invitationModel.getContactModel().getPhoneNumber()));
                            }
                        } catch (NotBoundException | RemoteException ex) {
                            StageCoordinator.getInstance().showErrorNotification(ErrorMessages.FAILED_TO_CONNECT);
                            ex.printStackTrace();
                        }
                    });

                    // Handle refuse button
                    invitationItem.getRefuseButton().addEventHandler(ActionEvent.ACTION, e -> {
                        try {
                            boolean succeeded = dao.refuseInvite(
                                    new InvitationDecisionDto(userModel.getPhoneNumber(),
                                            invitationModel.getContactModel().getPhoneNumber()));

                            if (succeeded){
                                StageCoordinator.getInstance().showMessageNotification("Friend request refused", "");
                                userModel.getInvitations().removeIf( im -> im.getContactModel().getPhoneNumber()
                                        .equals(invitationModel.getContactModel().getPhoneNumber()));
                            }
                        } catch (NotBoundException | RemoteException ex) {
                            StageCoordinator.getInstance().showErrorNotification(ErrorMessages.FAILED_TO_CONNECT);
                            ex.printStackTrace();
                        }
                    });


                    setGraphic(invitationItem);
                } else {
                    setText("null");
                    setGraphic(null);
                }
            }
        };
    }
}

