package gov.iti.jets.presentation.util.cellfactories;

import gov.iti.jets.presentation.customcontrols.GroupChatMenuItem;
import gov.iti.jets.presentation.models.GroupChatModel;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class GroupChatMenuItemCellFactory implements Callback<ListView<GroupChatModel>, ListCell<GroupChatModel>> {
    @Override
    public ListCell<GroupChatModel> call(ListView<GroupChatModel> param) {
        ListCell<GroupChatModel> cell = new ListCell<>(){
            @Override
            public void updateItem(GroupChatModel groupChat, boolean empty) {
                super.updateItem(groupChat, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else if (groupChat != null) {
                    setText(null);
                    setGraphic(new GroupChatMenuItem());
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

