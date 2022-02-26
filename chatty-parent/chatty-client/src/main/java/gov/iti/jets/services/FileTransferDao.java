package gov.iti.jets.services;

import gov.iti.jets.commons.dtos.FileTransferPermissionDto;
import gov.iti.jets.commons.dtos.FileTransferResponseDto;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface FileTransferDao {
    boolean askForPermissionToSendFile(FileTransferPermissionDto fileTransferPermissionDto) throws NotBoundException, RemoteException;
    void sendFileTransferResponse(FileTransferResponseDto fileTransferResponseDto) throws RemoteException, NotBoundException;
}
