package gov.iti.jets.repository.util;

import gov.iti.jets.repository.CountryRepository;
import gov.iti.jets.repository.InvitationsRepository;
import gov.iti.jets.repository.UserRepository;
import gov.iti.jets.repository.UserStatusRepository;
import gov.iti.jets.repository.impls.CountryRepositoryImpl;
import gov.iti.jets.repository.impls.InvitationsRepositoryImpl;
import gov.iti.jets.repository.impls.UserRepositoryImpl;
import gov.iti.jets.repository.impls.UserStatusRepositoryImpl;

public class RepositoryFactory {
    private static final RepositoryFactory repositoryFactory = new RepositoryFactory();
    private final CountryRepository countryRepository = new CountryRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final UserStatusRepository userStatusRepository = new UserStatusRepositoryImpl();
    private final InvitationsRepository invitationsRepository = new InvitationsRepositoryImpl();

    private  RepositoryFactory(){

    }

    public static RepositoryFactory getInstance() {
        return repositoryFactory;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public CountryRepository getCountryRepository() {
        return countryRepository;
    }

    public  UserStatusRepository getUserStatusRepository(){ return userStatusRepository;}

    public  InvitationsRepository getInvitationsRepository(){ return invitationsRepository;}
}
