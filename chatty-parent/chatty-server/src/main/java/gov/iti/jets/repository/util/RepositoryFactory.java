package gov.iti.jets.repository.util;

import gov.iti.jets.repository.*;
import gov.iti.jets.repository.impls.*;

public class RepositoryFactory {
    private static final RepositoryFactory repositoryFactory = new RepositoryFactory();
    private final CountryRepository countryRepository = new CountryRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final UserStatusRepository userStatusRepository = new UserStatusRepositoryImpl();
    private final InvitationsRepository invitationsRepository = new InvitationsRepositoryImpl();
    private final ContactRepository contactRepository = new ContactRepositoryImpl();
    private final GroupChatRepository groupChatRepository = new GroupChatRepositoryImpl();

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

    public  ContactRepository getContactRepository(){
        return contactRepository;
    }

    public GroupChatRepository getGroupChatRepository(){ return groupChatRepository;}
}
