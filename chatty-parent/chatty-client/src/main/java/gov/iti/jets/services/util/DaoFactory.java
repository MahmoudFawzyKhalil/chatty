package gov.iti.jets.services.util;

import gov.iti.jets.services.*;
import gov.iti.jets.services.RegisterDao;
import gov.iti.jets.services.impls.CountryDaoImpl;
import gov.iti.jets.services.impls.LoginDaoImpl;
import gov.iti.jets.services.impls.RegisterDaoImpl;
import gov.iti.jets.services.impls.UpdateProfileDaoImpl;

public class DaoFactory {
    private static final DaoFactory DAO_FACTORY = new DaoFactory();

    private final LoginDao LoginDao = new LoginDaoImpl();
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
