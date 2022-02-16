package gov.iti.jets.repository;

import gov.iti.jets.repository.entity.UserEntity;

public interface UserRepository {
    boolean isFoundByPhoneNumberAndPassword(String phoneNumber,String password);
    boolean isRegistered(UserEntity userEntity) ;
}
