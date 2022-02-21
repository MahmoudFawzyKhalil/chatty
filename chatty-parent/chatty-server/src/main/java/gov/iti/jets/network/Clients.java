package gov.iti.jets.network;

import gov.iti.jets.commons.callback.Client;
import gov.iti.jets.commons.dtos.ContactDto;
import gov.iti.jets.commons.dtos.GroupChatDto;
import gov.iti.jets.commons.dtos.StatusNotificationDto;
import gov.iti.jets.commons.enums.StatusNotificationType;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.commons.dtos.GroupMessageDto;
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

    public List<Client> getGroupClients(int groupId) {
        return groupMap.get(groupId);
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
        for (Client client : clients) {
            client.addGroupChat(groupChatDto);
        }
    }
    public void sendMessageToOnlineClientsOfAGroupChat( GroupMessageDto groupMessageDto) throws RemoteException {
        List <Client> clients = groupMap.get( groupMessageDto.getGroupChatId() );
        for (Client client : clients) {
            client.receiveGroupMessage(groupMessageDto);
        }
    }


    // We add the phone number as an argument to avoid having to make a remote call to the client just to get their phone number!
    public void addClient(String phoneNumber, Client client) {
        logger.info("A client attempted to register its handler: " + phoneNumber);

        clientMap.put(phoneNumber, client);

        logger.info("Client that attempted: " + clientMap.get(phoneNumber));
    }

    public Optional<Client> removeClient(String phoneNumber)  {
        logger.info("A client attempted to log out: " + phoneNumber);

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
                    removeClient( contact );
                }
            } );
        }

        return Optional.ofNullable(clientMap.remove(phoneNumber));
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

}
