package gov.iti.jets.repository;

import gov.iti.jets.commons.dtos.AddContactDto;

import gov.iti.jets.repository.entities.UserEntity;

import java.util.Optional;

public interface UserRepository {
    boolean isFoundByPhoneNumberAndPassword(String phoneNumber,String password);
    boolean addContacts(AddContactDto addContactDto);

    boolean addUser(UserEntity userEntity) ;
    Optional<UserEntity> getByPhoneNumber(String phoneNumber);
    boolean isFoundByPhoneNumber(String phoneNumber);

    boolean isFoundByEmail(String email);
}
