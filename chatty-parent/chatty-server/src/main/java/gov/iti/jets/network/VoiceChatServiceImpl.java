package gov.iti.jets.network;

import gov.iti.jets.commons.callback.Client;
import gov.iti.jets.commons.dtos.VoiceChatDto;
import gov.iti.jets.commons.remoteinterfaces.VoiceChatService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;

public class VoiceChatServiceImpl extends UnicastRemoteObject implements VoiceChatService {
    private transient Clients clients = Clients.getInstance();

    protected VoiceChatServiceImpl() throws RemoteException {
    }

    @Override
    public boolean askPermissionForCalling(VoiceChatDto voiceChatDto) throws RemoteException {
        Optional<Client> clientOptional = clients.getClient(voiceChatDto.getReceiverPhoneNumber());
        if (clientOptional.isPresent()) {

            return clientOptional.get().receiveVoiceChatPermission(voiceChatDto);
        }
        return false;
    }

    @Override
    public boolean startVoiceChat(VoiceChatDto voiceChatDto) throws RemoteException {
        Optional<Client> clientOptional = clients.getClient(voiceChatDto.getReceiverPhoneNumber());
        if (clientOptional.isPresent()) {
            return clientOptional.get().startVoiceChat(voiceChatDto);
        }
        return false;
    }

    @Override
    public void closeVoiceChat(VoiceChatDto voiceChatDto) throws RemoteException {
        Optional<Client> clientOptional = clients.getClient(voiceChatDto.getReceiverPhoneNumber());
        if (clientOptional.isPresent()) {
            clientOptional.get().closeVoiceChat(voiceChatDto);
        }
    }
}
