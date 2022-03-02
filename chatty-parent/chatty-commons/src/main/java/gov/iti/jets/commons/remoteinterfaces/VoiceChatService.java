package gov.iti.jets.commons.remoteinterfaces;

import gov.iti.jets.commons.dtos.VoiceChatDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VoiceChatService extends Remote {
    boolean askPermissionForCalling(VoiceChatDto voiceChatDto) throws RemoteException;

    boolean startVoiceChat(VoiceChatDto voiceChatDto) throws RemoteException;

    void closeVoiceChat(VoiceChatDto voiceChatDto) throws RemoteException;
}
