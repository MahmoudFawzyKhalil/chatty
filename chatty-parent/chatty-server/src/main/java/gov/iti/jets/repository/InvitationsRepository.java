package gov.iti.jets.repository;

import gov.iti.jets.repository.entities.ContactEntity;

import java.util.List;

public interface InvitationsRepository {
    List<ContactEntity> getInvitations(String phoneNumber);
}
