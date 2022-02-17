package gov.iti.jets.services.util;

import gov.iti.jets.services.*;
import gov.iti.jets.services.impls.ConnectionDaoImpl;
import gov.iti.jets.services.impls.LoginDaoImpl;

public class DaoFactory {
    private static final DaoFactory DAO_FACTORY = new DaoFactory();

    private static final LoginDao LoginDao = new LoginDaoImpl();
    private static final ConnectionDao ConnectionDao = new ConnectionDaoImpl();

    private DaoFactory() {

    }

    public static DaoFactory getInstance() {
        return DAO_FACTORY;
    }

    public LoginDao getLoginService() {
        return LoginDao;
    }
    public ConnectionDao getConnectionService(){ return ConnectionDao;}

}
