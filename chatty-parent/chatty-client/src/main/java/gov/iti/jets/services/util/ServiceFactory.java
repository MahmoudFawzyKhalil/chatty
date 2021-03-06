package gov.iti.jets.services.util;

import gov.iti.jets.commons.remoteinterfaces.*;
import gov.iti.jets.services.ChatBotService;
import gov.iti.jets.services.impls.ChatBotServiceImpl;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServiceFactory {
    private static final ServiceFactory registryFactory = new ServiceFactory();
    private Registry registry;

    private String host = "127.0.0.1";

    private LoginService loginService;
    private AddContactService addContactService;
    private RegisterService registerService;
    private CountryService countryService;
    private InvitationDecisionService invitationDecisionService;
    private UpdateProfileService updateProfileService;
    private ConnectionService connectionService;
    private AddGroupChatService addGroupChatService;
    private SingleMessageService singleMessageService;
    private ChatBotService chatBotService;
    private GroupMessageService groupMessageService;
    private FileTransferService fileTransferService;
    private VoiceChatService voiceChatService;


    private ServiceFactory() {

    }

    public void setRegistry(String host) throws RemoteException {
        this.registry = LocateRegistry.getRegistry(host, 1099);
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
        if (connectionService == null && registry != null) {
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


    public SingleMessageService getSingleMessageService() throws NotBoundException, RemoteException {
        if (singleMessageService == null) {
            singleMessageService = (SingleMessageService) registry.lookup("SingleMessageService");
        }
        return singleMessageService;
    }

    public ChatBotService getChatBotService() {
        if (chatBotService == null) {
            chatBotService = new ChatBotServiceImpl();
        }
        return chatBotService;
    }

    public GroupMessageService getGroupMessageService() throws NotBoundException, RemoteException {
        if (groupMessageService == null) {
            groupMessageService = (GroupMessageService) registry.lookup("GroupMessageService");
        }
        return groupMessageService;
    }

    public FileTransferService getFileTransferService() throws NotBoundException, RemoteException {
        if (fileTransferService == null) {
            fileTransferService = (FileTransferService) registry.lookup("FileTransferService");
        }
        return fileTransferService;
    }

    public VoiceChatService getVoiceChatService() throws NotBoundException, RemoteException {
        if (voiceChatService == null) {
            voiceChatService = (VoiceChatService) registry.lookup("VoiceChatService");

        }
        return voiceChatService;
    }

    public void shutdown() {
        loginService = null;
        addContactService = null;
        registerService = null;
        countryService = null;
        invitationDecisionService = null;
        updateProfileService = null;
        connectionService = null;
        addGroupChatService = null;
        singleMessageService = null;
        chatBotService = null;
        groupMessageService = null;
    }
}
