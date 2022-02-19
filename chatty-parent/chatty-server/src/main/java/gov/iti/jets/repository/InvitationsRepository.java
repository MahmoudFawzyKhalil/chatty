package gov.iti.jets.repository;

import gov.iti.jets.repository.entities.ContactEntity;
import gov.iti.jets.repository.entities.InvitationEntity;

import java.util.List;
import java.util.Optional;

public interface InvitationsRepository {
    Optional<InvitationEntity> getInvitation(String sender, String receiver);
    List<InvitationEntity> getInvitations(String phoneNumber);
}
