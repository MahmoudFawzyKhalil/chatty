package gov.iti.jets.services.util;

import gov.iti.jets.services.*;
import gov.iti.jets.services.impls.*;

public class ServiceFactory {
    private static final ServiceFactory serviceFactory = new ServiceFactory();
    
    private static final LoginService loginService = new LoginServiceImpl();

    private ServiceFactory(){

    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public LoginService getLoginService(){
        return loginService;
    }
}
