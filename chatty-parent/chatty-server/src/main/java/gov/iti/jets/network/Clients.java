package gov.iti.jets.network;

import gov.iti.jets.commons.callback.Client;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Clients {
    private static final Clients INSTANCE = new Clients();
    private final Map<String, Client> clientMap = new HashMap<>();

    public static Clients getInstance() {
        return INSTANCE;
    }

    public Optional<Client> getClient( String phoneNumber ) {
        return Optional.ofNullable( clientMap.get( phoneNumber ) );
    }

    // We add the phone number as an argument to avoid having to make a remote call to the client just to get their phone number!
    public void addClient( String phoneNumber, Client client ) {
        clientMap.put( phoneNumber, client );
    }

    public Optional<Client> removeClient( String phoneNumber ) throws RemoteException {
        return Optional.ofNullable( clientMap.remove( phoneNumber ) );
    }

}
