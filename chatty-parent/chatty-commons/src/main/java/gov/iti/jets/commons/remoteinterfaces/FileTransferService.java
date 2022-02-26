package gov.iti.jets.commons.remoteinterfaces;

import gov.iti.jets.commons.dtos.FileTransferPermissionDto;
import gov.iti.jets.commons.dtos.FileTransferResponseDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileTransferService extends Remote {
    boolean askForPermissionToSendFile(FileTransferPermissionDto fileTransferPermissionDto) throws RemoteException;
    void sendFileTransferResponse(FileTransferResponseDto fileTransferResponseDto) throws  RemoteException;
}
