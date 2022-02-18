package gov.iti.jets.repository;

import gov.iti.jets.repository.entities.ContactEntity;
import gov.iti.jets.repository.entities.InvitationEntity;

import java.util.List;

public interface InvitationsRepository {

    List<InvitationEntity> getInvitations(String phoneNumber);
}
