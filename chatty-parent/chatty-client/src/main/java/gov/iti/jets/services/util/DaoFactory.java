package gov.iti.jets.services.util;

import gov.iti.jets.services.*;
import gov.iti.jets.services.impls.*;

public class DaoFactory {
    private static final DaoFactory DAO_FACTORY = new DaoFactory();

    private static final LoginDao LoginDao = new LoginDaoImpl();
    private static final ConnectionDao ConnectionDao = new ConnectionDaoImpl();
    private final RegisterDao registerDao = new RegisterDaoImpl();
    private final CountryDao countryDao = new CountryDaoImpl();
    private final UpdateProfileDao updateProfileDao = new UpdateProfileDaoImpl();

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

    public UpdateProfileDao getUpdateProfileDao() {
        return updateProfileDao;
    }
}
