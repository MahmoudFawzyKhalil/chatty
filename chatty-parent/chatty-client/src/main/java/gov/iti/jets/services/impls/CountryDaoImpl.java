package gov.iti.jets.services.impls;

import gov.iti.jets.commons.dtos.CountryDto;
import gov.iti.jets.commons.remoteinterfaces.CountryService;
import gov.iti.jets.services.CountryDao;
import gov.iti.jets.services.util.ServiceFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class CountryDaoImpl implements CountryDao {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public List<CountryDto> getAll() throws NotBoundException, RemoteException {
        CountryService countryService = serviceFactory.getCountryService();
        return countryService.getAll();
    }
}
