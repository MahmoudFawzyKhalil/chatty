package gov.iti.jets.services.util;

import gov.iti.jets.commons.remoteinterfaces.ConnectionService;
import gov.iti.jets.commons.remoteinterfaces.*;
import gov.iti.jets.commons.remoteinterfaces.UpdateProfileService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServiceFactory {
    private static final ServiceFactory registryFactory = new ServiceFactory();
    private Registry registry;


    private static LoginService loginService;
    private static AddContactService addContactService;

    private static RegisterService registerService;
    private static CountryService countryService;
    private static InvitationDecisionService invitationDecisionService;
    private UpdateProfileService updateProfileService;
    private ConnectionService connectionService;
    private AddGroupChatService addGroupChatService;
    private static SingleMessageService singleMessageService;
    private static GroupMessageService groupMessageService;


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

    public AddContactService getAddContactService() throws NotBoundException, RemoteException {
        if (addContactService == null) {
            addContactService = (AddContactService) registry.lookup("AddContactService");
        }
        return addContactService;
    }


    public ConnectionService getConnectionService() throws NotBoundException, RemoteException {
        if (connectionService == null) {
            connectionService = (ConnectionService) registry.lookup("ConnectionService");
        }
        return connectionService;
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

    public InvitationDecisionService getInvitationDecisionService() throws NotBoundException, RemoteException {
        if (invitationDecisionService == null) {
            invitationDecisionService = (InvitationDecisionService) registry.lookup("InvitationDecisionService");

        }
        return invitationDecisionService;
    }

    public UpdateProfileService getUpdateProfileService() throws NotBoundException, RemoteException {
        if (updateProfileService == null) {
            updateProfileService = (UpdateProfileService) registry.lookup("UpdateProfileService");

        }
        return updateProfileService;
    }

    public AddGroupChatService getAddGroupService() throws NotBoundException, RemoteException {
        if (addGroupChatService == null) {
            addGroupChatService = (AddGroupChatService) registry.lookup("AddGroupChatService");
        }
        return addGroupChatService;
    }


    public SingleMessageService getSingleMessageService() throws NotBoundException, RemoteException{
        if(singleMessageService==null){
            singleMessageService = (SingleMessageService) registry.lookup("SingleMessageService");
        }
        return singleMessageService;
    }
    public GroupMessageService getGroupMessageService()throws NotBoundException, RemoteException{
        if(groupMessageService==null){
            groupMessageService = (GroupMessageService) registry.lookup("GroupMessageService");
        }
        return groupMessageService;
    }

}
