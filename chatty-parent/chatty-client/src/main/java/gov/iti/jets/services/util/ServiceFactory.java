package gov.iti.jets.services.util;

import gov.iti.jets.services.*;
import gov.iti.jets.services.impls.LoginDaoImpl;

public class ServiceFactory {
    private static final ServiceFactory serviceFactory = new ServiceFactory();
    
    private static final LoginDao loginService = new LoginDaoImpl();

    private ServiceFactory(){

    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public LoginDao getLoginService(){
        return loginService;
    }
}
