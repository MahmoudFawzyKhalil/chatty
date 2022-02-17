package gov.iti.jets.repository.entities;

public class InvitationEntity {

    private String contactNumber;
    private String contacteeNumber;

    public InvitationEntity(String contactNumber, String contacteeNumber) {
        this.contactNumber = contactNumber;
        this.contacteeNumber = contacteeNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContacteeNumber() {
        return contacteeNumber;
    }

    public void setContacteeNumber(String contacteeNumber) {
        this.contacteeNumber = contacteeNumber;
    }
}
