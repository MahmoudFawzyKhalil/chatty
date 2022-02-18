package gov.iti.jets.repository;

import gov.iti.jets.repository.entities.ContactEntity;

import java.util.Optional;

public interface ContactRepository {
    Optional<ContactEntity> getContact(String phoneNumber);
}
