package gov.iti.jets.presentation.util.cellfactories;

import gov.iti.jets.presentation.customcontrols.GroupChatMenuItem;
import gov.iti.jets.presentation.models.GroupChatModel;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class GroupChatMenuItemCellFactory implements Callback<ListView<GroupChatModel>, ListCell<GroupChatModel>> {

    private final UserModel userModel = ModelFactory.getInstance().getUserModel();

    @Override
    public ListCell<GroupChatModel> call( ListView<GroupChatModel> param ) {
        ListCell<GroupChatModel> cell = new ListCell<>() {
            @Override
            public void updateItem( GroupChatModel groupChatModel, boolean empty ) {
                super.updateItem( groupChatModel, empty );
                if (empty) {
                    setText( null );
                    setGraphic( null );
                } else if (groupChatModel != null) {
                    setText( null );
                    setGraphic( new GroupChatMenuItem( groupChatModel ) );
                } else {
                    setText( "null" );
                    setGraphic( null );
                }
            }
        };

        cell.setOnMouseClicked( e -> {
            if (cell.getItem() == null) return;
            GroupChatModel groupChatModel = cell.getItem();
            userModel.setChattingInGroup(true);
            userModel.setCurrentlyChattingWith( groupChatModel.getGroupChatId() + "" );
        } );

        return cell;
    }
}

