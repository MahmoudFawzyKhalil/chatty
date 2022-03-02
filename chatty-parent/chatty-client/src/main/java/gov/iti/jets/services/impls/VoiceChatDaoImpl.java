package gov.iti.jets.services.impls;

import gov.iti.jets.commons.dtos.VoiceChatDto;
import gov.iti.jets.services.VoiceChatDao;
import gov.iti.jets.services.util.ServiceFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class VoiceChatDaoImpl implements VoiceChatDao {
    ServiceFactory serviceFactory=ServiceFactory.getInstance();
    @Override
    public boolean askPermissionForCalling(VoiceChatDto voiceChatDto) throws NotBoundException, RemoteException {
        return serviceFactory.getVoiceChatService().askPermissionForCalling(voiceChatDto);
    }

    @Override
    public boolean startVoiceChat(VoiceChatDto voiceChatDto) throws NotBoundException, RemoteException {
        return serviceFactory.getVoiceChatService().startVoiceChat(voiceChatDto);
    }

    @Override
    public void closeVoiceChat(VoiceChatDto voiceChatDto) throws NotBoundException, RemoteException {
        serviceFactory.getVoiceChatService().closeVoiceChat(voiceChatDto);
    }
}
