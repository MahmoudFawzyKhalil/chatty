package gov.iti.jets.services.util;

import gov.iti.jets.services.*;
import gov.iti.jets.services.impls.*;

public class ServiceFactory {
    private static final ServiceFactory serviceFactory = new ServiceFactory();
    

    private ServiceFactory(){

    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }
}
