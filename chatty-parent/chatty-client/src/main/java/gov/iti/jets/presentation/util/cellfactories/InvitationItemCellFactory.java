package gov.iti.jets.presentation.util.cellfactories;

import gov.iti.jets.presentation.customcontrols.GroupMemberItem;
import gov.iti.jets.presentation.customcontrols.InvitationItem;
import gov.iti.jets.presentation.models.ContactModel;
import gov.iti.jets.presentation.models.InvitationModel;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class InvitationItemCellFactory implements Callback<ListView<InvitationModel>, ListCell<InvitationModel>> {

    @Override
    public ListCell<InvitationModel> call(ListView<InvitationModel> param) {
        ListCell<InvitationModel> cell = new ListCell<>(){
            @Override
            public void updateItem(InvitationModel invitation, boolean empty) {
                super.updateItem(invitation, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else if (invitation != null) {
                    setText(null);
                    setGraphic(new InvitationItem(invitation)); //contact argument
                } else {
                    setText("null");
                    setGraphic(null);
                }
            }
        };

        cell.setOnMouseClicked(e -> System.out.println(cell.getItem()));

        return cell;
    }
}

