package gov.iti.jets.repository;

import gov.iti.jets.repository.entities.ContactEntity;

import java.util.List;
import java.util.Optional;

public interface InvitationsRepository {

    Optional<List<ContactEntity>> getInvitations(String phoneNumber);
}
