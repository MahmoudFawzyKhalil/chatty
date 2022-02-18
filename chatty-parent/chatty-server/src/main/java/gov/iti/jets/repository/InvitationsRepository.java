package gov.iti.jets.repository;

import gov.iti.jets.repository.entities.ContactEntity;

import java.util.List;
import java.util.Optional;

public interface InvitationsRepository {

    // TODO  MAKE IT A InvitationEntity
    Optional<List<ContactEntity>> getInvitations( String phoneNumber);
}
