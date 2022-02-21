package gov.iti.jets.presentation.util;

public class ChatBubbleCellFactory /*implements Callback<ListView<MessageModel>, ListCell<MessageModel>> */{

/*    @Override
    public ListCell<MessageModel> call(ListView<MessageModel> param) {
        ListCell<MessageModel> cell = new ListCell<>() {
            @Override
            public void updateItem(MessageModel messageModel, boolean empty) {
                super.updateItem(messageModel, empty);
                var parentListViewWidthProperty = this.getListView().widthProperty();
                this.setWrapText( true );
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else if (messageModel != null) {
                    setText(null);
                    if (messageModel.isSentByMe()) {
                        var scb = new SentChatBubble(messageModel);
                        scb.getTextFlow().maxWidthProperty().bind( parentListViewWidthProperty.multiply( 0.7 ) );
                        setGraphic(scb);
                    } else {
                        var scb = new ReceivedChatBubble(messageModel);
                        scb.getTextFlow().maxWidthProperty().bind( parentListViewWidthProperty.multiply( 0.7 ) );
                        setGraphic(scb);
                    }
                } else {
                    setText("null");
                    setGraphic(null);
                }
            }
        };


        return cell;
    }*/
}
