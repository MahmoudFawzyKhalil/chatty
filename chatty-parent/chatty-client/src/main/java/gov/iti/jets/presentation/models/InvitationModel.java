package gov.iti.jets.presentation.models;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.Callback;

public class InvitationModel {
    private ObjectProperty<ContactModel>  contactModel = new SimpleObjectProperty<>();
    // + Other stuff
    public InvitationModel(ContactModel contactModel) {
        this.contactModel.set(contactModel);
    }

    public static Callback<InvitationModel, Observable[]> extractor() {
        return new Callback<InvitationModel, Observable[]>() {
            @Override
            public Observable[] call(InvitationModel param) {
                return new Observable[]{param.contactModel};
            }
        };
    }

    public ContactModel getContactModel() {
        return contactModel.get();
    }

    public ObjectProperty<ContactModel> contactModelProperty() {
        return contactModel;
    }

    public void setContactModel(ContactModel contactModel) {
        this.contactModel.set(contactModel);
    }

    @Override
    public String toString() {
        return "InvitationModel{" +
                "contactModel=" + contactModel +
                '}';
    }
}
