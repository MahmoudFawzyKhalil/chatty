package gov.iti.jets.repository;

import gov.iti.jets.commons.dtos.AddContactDto;

public interface UserRepository {
    boolean isFoundByPhoneNumberAndPassword(String phoneNumber,String password);
    boolean addContacts(AddContactDto addContactDto);

}
