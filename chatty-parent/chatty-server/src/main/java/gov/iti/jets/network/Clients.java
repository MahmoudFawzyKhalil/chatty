package gov.iti.jets.network;

import gov.iti.jets.commons.callback.Client;
import gov.iti.jets.commons.dtos.*;
import gov.iti.jets.commons.enums.StatusNotificationType;
import gov.iti.jets.repository.util.RepositoryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.util.*;
import java.util.stream.Collectors;

public class Clients {
    private static final Clients INSTANCE = new Clients();
    private final Map<String, Client> clientMap = new HashMap<>();
    private final Map<Integer, List<Client>> groupMap = new HashMap<>();


    Logger logger = LoggerFactory.getLogger(Clients.class);

    public static Clients getInstance() {
        return INSTANCE;
    }

    public Optional<Client> getClient(String phoneNumber) {
        return Optional.ofNullable(clientMap.get(phoneNumber));
    }

    public void notifyUserContactsPicChange() {

    }

    public List<Client> getGroupClients(int groupId) {
        return groupMap.get(groupId);
    }

    public Collection<Client> getAllClients(){
        return clientMap.values();
    }

    public Set<Map.Entry<String, Client>> getAllClientEntries(){
        return clientMap.entrySet();
    }

    public void addGroup(GroupChatDto groupChatDto) throws RemoteException {
        logger.info("new group added: " + groupChatDto.getGroupChatId());
        List<Client> clients = getClients(groupChatDto.getGroupMembersList());
        groupMap.put(groupChatDto.getGroupChatId(), clients);
        addGroupTo(clients, groupChatDto);
        logger.info("Client that attempted to group: " + groupMap.get(groupChatDto.getGroupChatId()));
    }

    public List<Client> getClients(List<ContactDto> contactDtos) {
        List<Client> clients = new ArrayList<>();
        for (ContactDto contactDto : contactDtos) {
            Optional<Client> optionalClient = getClient(contactDto.getPhoneNumber());
            optionalClient.ifPresent(clients::add);

        }
        return clients;
    }

    public void addGroupTo(List<Client> clients, GroupChatDto groupChatDto) throws RemoteException {
        List<Client> unresponsiveClients = new ArrayList<>();

        for (Client client : clients) {
            try {
                client.addGroupChat(groupChatDto);
            } catch (RemoteException e) {
                unresponsiveClients.add( client );
                e.printStackTrace();
            }
        }

        unresponsiveClients.forEach( this::removeClientFromOnlineAndGroups );
    }

    public void sendMessageToOnlineClientsOfAGroupChat( GroupMessageDto groupMessageDto) throws RemoteException {
        List <Client> clientsInGroup = groupMap.get( groupMessageDto.getGroupChatId() );

        List<Client> unresponsiveClients = new ArrayList<>();

        for (Client client : clientsInGroup) {
            try {
                client.receiveGroupMessage(groupMessageDto);
            } catch (RemoteException e) {
                unresponsiveClients.add( client );
                e.printStackTrace();
            }
        }

        unresponsiveClients.forEach( this::removeClientFromOnlineAndGroups );
    }


    // We add the phone number as an argument to avoid having to make a remote call to the client just to get their phone number!
    public void addClient(String phoneNumber, Client client) {
        logger.info("A client attempted to register its handler: " + phoneNumber);

        clientMap.put(phoneNumber, client);

        logger.info("Client that attempted: " + clientMap.get(phoneNumber));
    }

    public Optional<Client> removeClient(String phoneNumber)  {
        logger.info("A client attempted to log out: " + phoneNumber);

        if (phoneNumber == null) {
            return Optional.ofNullable( null );
        }

        if (clientMap.get(phoneNumber) != null){
            unregisterClientGroups(clientMap.get(phoneNumber));
        }


        List<String> contactsToNotify = RepositoryFactory.getInstance()
                .getContactRepository()
                .getUserContacts( phoneNumber )
                .stream()
                .map( ce -> ce.getPhoneNumber() )
                .collect( Collectors.toList() );

        StatusNotificationDto dto = new StatusNotificationDto( phoneNumber, StatusNotificationType.Offline );

        for (var contact : contactsToNotify){
            getClient( contact ).ifPresent( client -> {
                try {
                    client.notifyOfStatusUpdate( dto );
                } catch (RemoteException e) {
                    var clientOptional = getClient( contact );
                    if (clientOptional.isPresent()){
                        removeClientFromOnlineAndGroups( clientOptional.get() );
                    }
                }
            } );
        }

        return Optional.ofNullable(clientMap.remove(phoneNumber));
    }

    private String getPhoneNumberFromClientReference(Client client){
        String phoneNumber = null;

        for (var entry : clientMap.entrySet()) {
            if (entry.getValue() == client){
                phoneNumber = entry.getKey();
            }
        }

        return  phoneNumber;
    }

    private void removeClientFromGroups(Client client){
        for (List<Client> clientList : groupMap.values()){
            clientList.remove( client );
        }
    }

    public void removeClientFromOnlineAndGroups(Client client){
        removeClient( getPhoneNumberFromClientReference( client ) );
        removeClientFromGroups( client );
    }

    public void clearAllClients() {
        clientMap.clear();
        groupMap.clear();
    }

    public int getCountOnlineUsers(){
        return clientMap.size();
    }

    public void registerClientGroups(List<Integer> groupIds, Client client){
        for (Integer groupId:groupIds){
            if(groupMap.get(groupId) == null){
                List<Client> clientList = new ArrayList<>();
                clientList.add(client);
                groupMap.put(groupId, clientList);
            } else {
                groupMap.get(groupId).add(client);
            }
        }
    }

    public void unregisterClientGroups(Client client){
        for (Integer groupId:groupMap.keySet()){
            if(groupMap.get(groupId).contains(client)){
                groupMap.get(groupId).remove(client);
            }
        }
    }

    public void notifyContactPicChange(List<String> contactsPhoneNumber, UpdateProfilePicDto updateProfilePicDto) throws RemoteException {


        for (String phoneNumber : contactsPhoneNumber) {
            Optional<Client> client = getClient(phoneNumber);
            if (client.isPresent()) {
                try {
                    client.get().notifyContactPicChange(updateProfilePicDto);
                } catch (RemoteException e) {
                    this.removeClientFromOnlineAndGroups( client.get() );
                }
            }
        }
    }

    public void notifyContactProfileChange(List<String> contactsPhoneNumber, UpdateProfileDto updateProfileDto) throws RemoteException {
        for (String phoneNumber : contactsPhoneNumber) {
            Optional<Client> client = getClient(phoneNumber);
            if (client.isPresent()) {
                try {
                    client.get().notifyContactProfileChange(updateProfileDto);
                } catch (RemoteException e) {
                    this.removeClientFromOnlineAndGroups( client.get() );
                }
            }
        }
    }
}
