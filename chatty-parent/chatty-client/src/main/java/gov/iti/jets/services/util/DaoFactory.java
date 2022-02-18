package gov.iti.jets.services.util;

import gov.iti.jets.services.ConnectionDao;
import gov.iti.jets.services.CountryDao;
import gov.iti.jets.services.LoginDao;
import gov.iti.jets.services.RegisterDao;
import gov.iti.jets.services.impls.ConnectionDaoImpl;
import gov.iti.jets.services.impls.CountryDaoImpl;
import gov.iti.jets.services.impls.LoginDaoImpl;
import gov.iti.jets.services.impls.RegisterDaoImpl;

public class DaoFactory {
    private static final DaoFactory DAO_FACTORY = new DaoFactory();

    private static final LoginDao LoginDao = new LoginDaoImpl();
    private static final ConnectionDao ConnectionDao = new ConnectionDaoImpl();
    private final RegisterDao registerDao = new RegisterDaoImpl();
    private final CountryDao countryDao = new CountryDaoImpl();

    private DaoFactory() {

    }

    public static DaoFactory getInstance() {
        return DAO_FACTORY;
    }

    public LoginDao getLoginService() {
        return LoginDao;
    }
    public ConnectionDao getConnectionService(){ return ConnectionDao;}


    public RegisterDao getRegisterDao() {
        return registerDao;
    }

    public CountryDao getCountryDao() {
        return countryDao;
    }
}
