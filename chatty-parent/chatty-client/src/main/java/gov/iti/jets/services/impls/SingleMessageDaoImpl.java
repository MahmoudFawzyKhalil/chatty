package gov.iti.jets.services.impls;

import gov.iti.jets.commons.dtos.SingleMessageDto;
import gov.iti.jets.commons.remoteinterfaces.SingleMessageService;
import gov.iti.jets.services.SingleMessageDao;
import gov.iti.jets.services.util.ServiceFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SingleMessageDaoImpl implements SingleMessageDao {
    @Override
    public void sendMessage(SingleMessageDto singleMessageDto) throws NotBoundException, RemoteException {
        SingleMessageService singleMessageService = ServiceFactory.getInstance().getSingleMessageService();
        singleMessageService.sendMessage(singleMessageDto);
    }
}
