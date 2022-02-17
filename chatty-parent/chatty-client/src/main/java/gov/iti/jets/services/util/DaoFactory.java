package gov.iti.jets.services.util;

import gov.iti.jets.services.*;
import gov.iti.jets.services.impls.AddContactDaoImpl;
import gov.iti.jets.services.impls.LoginDaoImpl;

public class DaoFactory {
    private static final DaoFactory DAO_FACTORY = new DaoFactory();

    private static final LoginDao LoginDao = new LoginDaoImpl();

    private static final AddContactDao addContactDao = new AddContactDaoImpl();


    private DaoFactory() {

    }

    public static DaoFactory getInstance() {
        return DAO_FACTORY;
    }

    public LoginDao getLoginService() {
        return LoginDao;
    }
    public AddContactDao getAddContactService(){
        return addContactDao;
    }

}
