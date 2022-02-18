package gov.iti.jets.repository;

import gov.iti.jets.repository.entities.ContactEntity;

import java.util.List;

public interface InvitationsRepository {

    // TODO  MAKE IT A InvitationEntity
    List<ContactEntity> getInvitations( String phoneNumber);
}
