package gov.iti.jets.network;

import gov.iti.jets.commons.callback.Client;
import gov.iti.jets.commons.dtos.FileTransferPermissionDto;
import gov.iti.jets.commons.dtos.FileTransferResponseDto;
import gov.iti.jets.commons.remoteinterfaces.FileTransferService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;

public class FileTransferServiceImpl extends UnicastRemoteObject implements FileTransferService {

    private Clients clients = Clients.getInstance();

    protected FileTransferServiceImpl() throws RemoteException {
    }

    @Override
    public boolean askForPermissionToSendFile(FileTransferPermissionDto fileTransferPermissionDto) throws RemoteException {
        Optional<Client> client = clients.getClient(fileTransferPermissionDto.getReceiverPhoneNumber());
        if (!client.isEmpty()) {
            client.get().receiveFileTransferPermission(fileTransferPermissionDto);
            return true;
        }
        return false;
    }

    @Override
    public void sendFileTransferResponse(FileTransferResponseDto fileTransferResponseDto) throws RemoteException {
        Optional<Client> client = clients.getClient(fileTransferResponseDto.getSenderPhoneNumber());
        if(!client.isEmpty()){
            client.get().receiveFileTransferResponse(fileTransferResponseDto);
        }

    }
}
