package gov.iti.jets.repository;

import gov.iti.jets.commons.dtos.AddContactDto;

import gov.iti.jets.repository.entities.UserEntity;

import java.util.Optional;

public interface UserRepository {
    boolean isFoundByPhoneNumberAndPassword(String phoneNumber,String password);
    boolean addContacts(AddContactDto addContactDto);
    boolean addUser(UserEntity userEntity) ;
    boolean isFoundByPhoneNumber(String phoneNumber);
    boolean isFoundByEmail(String email);
    Optional<UserEntity> getUserByPhoneNumber(String phoneNumber);
    boolean update(UserEntity userEntity);
}
