package gov.iti.jets.services.util;



public class ServiceFactory {
    private static final ServiceFactory serviceFactory = new ServiceFactory();
    

    private ServiceFactory(){

    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }
}
