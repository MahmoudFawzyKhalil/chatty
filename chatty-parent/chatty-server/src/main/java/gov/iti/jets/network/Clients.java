package gov.iti.jets.network;

import gov.iti.jets.commons.callback.Client;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class Clients {
    private static final Clients INSTANCE = new Clients();
    private final Map<String, Client> clientsMap = new HashMap<>();

    public static Clients getInstance(){
        return INSTANCE;
    }

    public Client getClient(String phoneNUmber){
        if(clientsMap.containsKey(phoneNUmber))
            return clientsMap.get(phoneNUmber);
        return null;
    }

    public void addClient(Client client) throws RemoteException {
        String phoneNumber = client.getPhoneNumber();
        if(!clientsMap.containsKey(phoneNumber)){
            clientsMap.put(phoneNumber,client);
        }
    }

    public void removeClient(Client client) throws RemoteException{
        String phoneNumber = client.getPhoneNumber();
        if(clientsMap.containsValue(client)){
            clientsMap.remove(phoneNumber,client);
        }
    }

}
