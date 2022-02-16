package gov.iti.jets.repository.util;

import gov.iti.jets.repository.UserRepository;
import gov.iti.jets.repository.impls.UserRepositoryImpl;

public class RepositoryFactory {
    private static final RepositoryFactory repositoryFactory=new RepositoryFactory();
    private UserRepository userRepository=new UserRepositoryImpl();
    private  RepositoryFactory(){

    }
    public static RepositoryFactory getInstance(){
        return repositoryFactory;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
