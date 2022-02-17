package gov.iti.jets.network;

import gov.iti.jets.commons.dtos.CountryDto;
import gov.iti.jets.commons.remoteinterfaces.CountryService;
import gov.iti.jets.repository.CountryRepository;
import gov.iti.jets.repository.entities.CountryEntity;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.repository.util.mappers.CountryMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CountryServiceImpl extends UnicastRemoteObject implements CountryService {

    private final CountryRepository countryRepository = RepositoryFactory.getInstance().getCountryRepository();

    protected CountryServiceImpl() throws RemoteException {
    }

    @Override
    public List<CountryDto> getAll() throws RemoteException {
        List<CountryEntity> countryEntities = countryRepository.getAll();
        return CountryMapper.INSTANCE.entityListToDto(countryEntities);
    }
}
