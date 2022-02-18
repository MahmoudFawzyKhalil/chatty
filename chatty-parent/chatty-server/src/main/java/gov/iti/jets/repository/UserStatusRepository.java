package gov.iti.jets.repository;

import gov.iti.jets.repository.entities.UserStatusEntity;

import java.util.Optional;

public interface UserStatusRepository {
    Optional<UserStatusEntity> getStatus(int id);
}
