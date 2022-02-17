package gov.iti.jets.services.util;

import gov.iti.jets.commons.remoteinterfaces.CountryService;
import gov.iti.jets.commons.remoteinterfaces.LoginService;
import gov.iti.jets.commons.remoteinterfaces.RegisterService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServiceFactory {
    private static final ServiceFactory registryFactory = new ServiceFactory();
    private Registry registry;


    private static LoginService loginService;
    private static RegisterService registerService;
    private static CountryService countryService;

    private ServiceFactory() {
        try {
            this.registry = LocateRegistry.getRegistry(1099);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public static ServiceFactory getInstance() {
        return registryFactory;
    }

    public LoginService getLoginService() throws NotBoundException, RemoteException {
        if (loginService == null) {
            loginService = (LoginService) registry.lookup("LoginService");

        }
        return loginService;
    }

    public RegisterService getRegisterService() throws NotBoundException, RemoteException {
        if (registerService == null) {
            registerService = (RegisterService) registry.lookup("RegisterService");

        }
        return registerService;
    }

    public CountryService getCountryService() throws NotBoundException, RemoteException {
        if (countryService == null) {
            countryService = (CountryService) registry.lookup("CountryService");

        }
        return countryService;
    }

}
