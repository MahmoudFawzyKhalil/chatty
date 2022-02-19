package gov.iti.jets.repository.entities;

public class InvitationEntity {

    private ContactEntity contactEntity;

    public InvitationEntity() {
    }

    public InvitationEntity(ContactEntity contactEntity) {
        this.contactEntity = contactEntity;
    }

    public ContactEntity getContactEntity() {
        return contactEntity;
    }

    public void setContactEntity(ContactEntity contactEntity) {
        this.contactEntity = contactEntity;
    }
}
