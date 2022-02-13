package gov.iti.jets.presentation.util.cellfactories;

import gov.iti.jets.presentation.customcontrols.ReceivedChatBubble;
import gov.iti.jets.presentation.customcontrols.SentChatBubble;
import gov.iti.jets.presentation.models.MessageModel;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ChatBubbleCellFactory implements Callback<ListView<MessageModel>, ListCell<MessageModel>> {

    @Override
    public ListCell<MessageModel> call(ListView<MessageModel> param) {
        ListCell<MessageModel> cell = new ListCell<>() {
            @Override
            public void updateItem(MessageModel message, boolean empty) {
                super.updateItem(message, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else if (message != null) {
                    setText(null);
                    if (message.isSentByMe()) {
                        setGraphic(new SentChatBubble());
                    } else {
                        setGraphic(new ReceivedChatBubble());
                    }
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
