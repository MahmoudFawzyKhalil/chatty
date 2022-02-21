package gov.iti.jets.services.util;


import gov.iti.jets.services.ServerNotificationsService;
import gov.iti.jets.services.impls.ServerNotificationsServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory serviceFactory = new ServiceFactory();
    private final ServerNotificationsService serverNotificationsService = new ServerNotificationsServiceImpl();

    private ServiceFactory(){

    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public ServerNotificationsService getServerNotificationsService() {
        return serverNotificationsService;
    }
}
