package gov.iti.jets.presentation.models;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServerModel {
    private ListProperty<MessageModel> announcements = new SimpleListProperty<>( FXCollections.observableArrayList( MessageModel.extractor() ) );

    public ServerModel(){

    }

    public ObservableList<MessageModel> getAnnouncements() {
        return announcements.get();
    }

    public ListProperty<MessageModel> announcementsProperty() {
        return announcements;
    }

    public void setAnnouncements( ObservableList<MessageModel> announcements ) {
        this.announcements.set( announcements );
    }
}
