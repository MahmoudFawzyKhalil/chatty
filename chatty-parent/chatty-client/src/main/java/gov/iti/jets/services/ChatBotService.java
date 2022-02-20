package gov.iti.jets.services;

import gov.iti.jets.commons.dtos.SingleMessageDto;
import gov.iti.jets.presentation.models.MessageModel;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface ChatBotService {
    MessageModel formulateAndSendChatBotReply( SingleMessageDto dto ) throws NotBoundException, RemoteException;
}
