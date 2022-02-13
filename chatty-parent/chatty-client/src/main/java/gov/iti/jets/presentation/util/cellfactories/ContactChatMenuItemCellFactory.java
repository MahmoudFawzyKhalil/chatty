package gov.iti.jets.presentation.util.cellfactories;

import gov.iti.jets.presentation.customcontrols.ContactChatMenuItem;
import gov.iti.jets.presentation.models.ContactModel;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ContactChatMenuItemCellFactory implements Callback<ListView<ContactModel>, ListCell<ContactModel>> {

        @Override
        public ListCell<ContactModel> call(ListView<ContactModel> param) {
            ListCell<ContactModel> cell = new ListCell<>(){
                @Override
                public void updateItem(ContactModel contact, boolean empty) {
                    super.updateItem(contact, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else if (contact != null) {
                        setText(null);
                        setGraphic(new ContactChatMenuItem()); //contact argument
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
