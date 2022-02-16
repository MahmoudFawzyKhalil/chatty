package gov.iti.jets.services.util;

import gov.iti.jets.services.*;
import gov.iti.jets.services.RegisterDao;
import gov.iti.jets.services.impls.LoginDaoImpl;
import gov.iti.jets.services.impls.RegisterDaoImpl;

public class DaoFactory {
    private static final DaoFactory DAO_FACTORY = new DaoFactory();

    private static final LoginDao LoginDao = new LoginDaoImpl();
    private static final RegisterDao registerDao = new RegisterDaoImpl();

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
}
