package gov.iti.jets.repository;

import gov.iti.jets.repository.entities.ContactEntity;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {
    Optional<ContactEntity> getContact(String phoneNumber);
    Optional<List<ContactEntity>> getUserContacts(String phoneNumber);
}
