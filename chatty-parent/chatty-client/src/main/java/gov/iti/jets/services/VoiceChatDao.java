package gov.iti.jets.services;

import gov.iti.jets.commons.dtos.VoiceChatDto;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface VoiceChatDao {
    boolean askPermissionForCalling(VoiceChatDto voiceChatDto) throws NotBoundException, RemoteException;

    boolean startVoiceChat(VoiceChatDto voiceChatDto) throws NotBoundException, RemoteException;

    void closeVoiceChat(VoiceChatDto voiceChatDto) throws NotBoundException, RemoteException;
}
