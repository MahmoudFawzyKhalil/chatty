package gov.iti.jets.presentation.util.cellfactories;

import gov.iti.jets.presentation.customcontrols.ContactChatMenuItem;
import gov.iti.jets.presentation.models.ContactModel;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ContactChatMenuItemCellFactory implements Callback<ListView<ContactModel>, ListCell<ContactModel>> {

    private final UserModel userModel = ModelFactory.getInstance().getUserModel();
    @Override
    public ListCell<ContactModel> call( ListView<ContactModel> param ) {
        ListCell<ContactModel> cell = new ListCell<>() {
            @Override
            public void updateItem( ContactModel contactModel, boolean empty ) {
                super.updateItem( contactModel, empty );
                if (empty) {
                    setText( null );
                    setGraphic( null );
                } else if (contactModel != null) {
                    setText( null );
                    setGraphic( new ContactChatMenuItem( contactModel ) ); //contact argument
                } else {
                    setText( "null" );
                    setGraphic( null );
                }
            }
        };

        cell.setOnMouseClicked( e -> {
            if (cell.getItem() == null) return;
            ContactModel contactModel = cell.getItem();
            userModel.setCurrentlyChattingWith( contactModel.getPhoneNumber() );
        } );

        return cell;
    }
}

