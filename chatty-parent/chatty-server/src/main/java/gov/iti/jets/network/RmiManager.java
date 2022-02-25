package gov.iti.jets.network;

import gov.iti.jets.commons.remoteinterfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiManager {
    private final static RmiManager INSTANCE = new RmiManager();
    private final Logger logger = LoggerFactory.getLogger( RmiManager.class );
    private Registry registry;
    private boolean isRunning = false;

    private LoginService loginService;
    private ConnectionService connectionService;
    private RegisterService registerService;
    private CountryService countryService;
    private AddContactService addContactService;
    private InvitationDecisionService invitationDecisionService;
    private UpdateProfileService updateProfileService;
    private SingleMessageService singleMessageService;
    private AddGroupChatService addGroupChatService;
    private GroupMessageService groupMessageService;

    private RmiManager() {
    }

    public static RmiManager getInstance() {
        return INSTANCE;
    }

    public void start() throws RemoteException {
        createOrObtainRegistry();
        createServices();
        registerServices();
        isRunning = true;
    }

    private void createOrObtainRegistry() throws RemoteException {
        try {
            this.registry = LocateRegistry.createRegistry( 1099 );

            logger.info( "Created an RMI registry listening on port 1099" );
        } catch (RemoteException e) {
            this.registry = LocateRegistry.getRegistry( 1099 );

            logger.info( "Registry was already alive. Obtained an RMI registry already listening on port 1099" );
        }
    }

    private void createServices() throws RemoteException {
        loginService = new LoginServiceImpl();
        connectionService = new ConnectionServiceImpl();
        registerService = new RegisterServiceImpl();
        countryService = new CountryServiceImpl();
        addContactService = new AddContactServiceImpl();
        invitationDecisionService = new InvitationDecisionServiceImpl();
        updateProfileService = new UpdateProfileServiceImpl();
        singleMessageService = new SingleMessageServiceImpl();
        addGroupChatService = new AddGroupChatChatServiceImpl();
        groupMessageService = new GroupMessageServiceImpl();

        logger.info( "Created service remote objects." );
    }

    private void registerServices() throws RemoteException {
        registry.rebind( "LoginService", loginService );
        registry.rebind( "ConnectionService", connectionService );
        registry.rebind( "RegisterService", registerService );
        registry.rebind( "CountryService", countryService );
        registry.rebind( "AddContactService", addContactService );
        registry.rebind( "InvitationDecisionService", invitationDecisionService );
        registry.rebind( "UpdateProfileService", updateProfileService );
        registry.rebind( "SingleMessageService", singleMessageService );
        registry.rebind( "AddGroupChatService", addGroupChatService );
        registry.rebind( "GroupMessageService", groupMessageService );

        logger.info( "Registered services with RMI registry." );
    }

    public void stop() throws RemoteException, NotBoundException {
        if (!isRunning) return;

        unbindServices();
        unexportServices();
        isRunning = false;
    }

    private void unexportServices() throws NoSuchObjectException {
        UnicastRemoteObject.unexportObject( loginService, false );
        UnicastRemoteObject.unexportObject( connectionService, false );
        UnicastRemoteObject.unexportObject( registerService, false );
        UnicastRemoteObject.unexportObject( countryService, false );
        UnicastRemoteObject.unexportObject( addContactService, false );
        UnicastRemoteObject.unexportObject( invitationDecisionService, false );
        UnicastRemoteObject.unexportObject( updateProfileService, false );
        UnicastRemoteObject.unexportObject( singleMessageService, false );
        UnicastRemoteObject.unexportObject( addGroupChatService, false );
        UnicastRemoteObject.unexportObject( groupMessageService, false );

        logger.info( "Unexported service remote objects." );
    }

    private void unbindServices() throws RemoteException, NotBoundException {
        registry.unbind( "LoginService" );
        registry.unbind( "ConnectionService" );
        registry.unbind( "RegisterService" );
        registry.unbind( "CountryService" );
        registry.unbind( "AddContactService" );
        registry.unbind( "InvitationDecisionService" );
        registry.unbind( "UpdateProfileService" );
        registry.unbind( "SingleMessageService" );
        registry.unbind( "AddGroupChatService" );
        registry.unbind( "GroupMessageService" );

        logger.info( "Unbound service remote objects." );
    }
}
