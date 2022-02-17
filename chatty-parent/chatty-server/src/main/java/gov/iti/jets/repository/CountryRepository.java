package gov.iti.jets.repository;

import gov.iti.jets.repository.entities.CountryEntity;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {
    Optional<CountryEntity> getById(int id);
    List<CountryEntity> getAll();
}
