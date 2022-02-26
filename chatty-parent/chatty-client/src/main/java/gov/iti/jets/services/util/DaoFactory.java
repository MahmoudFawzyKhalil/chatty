package gov.iti.jets.services.util;

import gov.iti.jets.services.*;
import gov.iti.jets.services.impls.*;
import gov.iti.jets.services.RegisterDao;
import gov.iti.jets.services.impls.CountryDaoImpl;
import gov.iti.jets.services.impls.LoginDaoImpl;
import gov.iti.jets.services.impls.RegisterDaoImpl;
import gov.iti.jets.services.impls.UpdateProfileDaoImpl;

public class DaoFactory {
    private static final DaoFactory DAO_FACTORY = new DaoFactory();

    private static final LoginDao LoginDao = new LoginDaoImpl();
    private static final ConnectionDao ConnectionDao = new ConnectionDaoImpl();
    private final RegisterDao registerDao = new RegisterDaoImpl();
    private final CountryDao countryDao = new CountryDaoImpl();
    private final UpdateProfileDao updateProfileDao = new UpdateProfileDaoImpl();
    private static final AddContactDao addContactDao = new AddContactDaoImpl();
    private final InvitationDecisionDao invitationDecisionDao = new InvitationDecisionDaoImpl();
    private final SingleMessageDao singleMessageDao = new SingleMessageDaoImpl();
    private final GroupMessageDao groupMessageDao = new GroupMessageDaoImpl();
    private final AddGroupChatDao addGroupChatDao = new AddGroupChatDaoImpl();
    private final FileTransferDao fileTransferDao = new FileTransferDaoImpl();


    private DaoFactory() {

    }

    public static DaoFactory getInstance() {
        return DAO_FACTORY;
    }

    public LoginDao getLoginDao() {
        return LoginDao;
    }

    public ConnectionDao getConnectionDao() {
        return ConnectionDao;
    }


    public RegisterDao getRegisterDao() {
        return registerDao;
    }

    public CountryDao getCountryDao() {
        return countryDao;
    }

    public AddContactDao getAddContactDao() {
        return addContactDao;
    }

    public InvitationDecisionDao getInvitationDecisionDao() {
        return invitationDecisionDao;
    }

    public UpdateProfileDao getUpdateProfileDao() {
        return updateProfileDao;
    }

    public SingleMessageDao getSingleMessageDao(){
        return singleMessageDao;
    }

    public GroupMessageDao getGroupMessageDao(){
        return groupMessageDao;
    }

    public AddGroupChatDao getAddGroupChatDao() {
        return addGroupChatDao;
    }

    public FileTransferDao getFileTransferDao() {
        return fileTransferDao;
    }
}
