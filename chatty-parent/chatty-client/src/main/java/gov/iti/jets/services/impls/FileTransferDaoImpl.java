package gov.iti.jets.services.impls;

import gov.iti.jets.commons.dtos.FileTransferPermissionDto;
import gov.iti.jets.commons.dtos.FileTransferResponseDto;
import gov.iti.jets.commons.remoteinterfaces.FileTransferService;
import gov.iti.jets.services.FileTransferDao;
import gov.iti.jets.services.util.ServiceFactory;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class FileTransferDaoImpl implements FileTransferDao {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private FileTransferService fileTransferService;

    @Override
    public boolean askForPermissionToSendFile(FileTransferPermissionDto fileTransferPermissionDto) throws NotBoundException, RemoteException {
        fileTransferService = serviceFactory.getFileTransferService();
        return fileTransferService.askForPermissionToSendFile(fileTransferPermissionDto);
    }

    @Override
    public void sendFileTransferResponse(FileTransferResponseDto fileTransferResponseDto) throws RemoteException, NotBoundException {
        fileTransferService = serviceFactory.getFileTransferService();
        fileTransferService.sendFileTransferResponse(fileTransferResponseDto);
    }

}
