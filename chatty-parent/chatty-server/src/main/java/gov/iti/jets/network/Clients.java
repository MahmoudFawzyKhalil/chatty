package gov.iti.jets.network;

import gov.iti.jets.commons.callback.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Clients {
    private static final Clients INSTANCE = new Clients();
    private final Map<String, Client> clientMap = new HashMap<>();
    Logger logger = LoggerFactory.getLogger( Clients.class );

    public static Clients getInstance() {
        return INSTANCE;
    }

    public Optional<Client> getClient( String phoneNumber ) {
        return Optional.ofNullable( clientMap.get( phoneNumber ) );
    }

    // We add the phone number as an argument to avoid having to make a remote call to the client just to get their phone number!
    public void addClient( String phoneNumber, Client client ) {
        logger.info( "A client attempted to register its handler: " + phoneNumber );

        clientMap.put( phoneNumber, client );

        logger.info( "Client that attempted: " + clientMap.get( phoneNumber ) );
    }

    public Optional<Client> removeClient( String phoneNumber ) throws RemoteException {
        logger.info( "A client attempted to log out: " + phoneNumber );
        return Optional.ofNullable( clientMap.remove( phoneNumber ) );
    }

}
