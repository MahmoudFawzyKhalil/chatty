package gov.iti.jets.network;

import gov.iti.jets.commons.callback.Client;
import gov.iti.jets.commons.dtos.SingleMessageDto;
import gov.iti.jets.commons.remoteinterfaces.SingleMessageService;
import gov.iti.jets.repository.entities.SingleMessageEntity;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.repository.util.mappers.SingleMessageMapper;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;

public class SingleMessageServiceImpl extends UnicastRemoteObject implements SingleMessageService {

    private RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private Clients clients = Clients.getInstance();
    protected SingleMessageServiceImpl() throws RemoteException {
    }

    @Override
    public void sendMessage(SingleMessageDto singleMessageDto) throws RemoteException {
        Optional<Client> client  = clients.getClient(singleMessageDto.getReceiverPhoneNumber());
        if(!client.isEmpty()){
            try {
                client.get().receiveSingleMessage(singleMessageDto);
            } catch (RemoteException | NotBoundException e) {
                clients.removeClientFromOnlineAndGroups( client.get() );
                e.printStackTrace();
            }
        }
        SingleMessageEntity singleMessageEntity = SingleMessageMapper.INSTANCE.dtoToEntity(singleMessageDto);
        repositoryFactory.getSingleMessageRepository().insertMessage(singleMessageEntity);
    }
}
