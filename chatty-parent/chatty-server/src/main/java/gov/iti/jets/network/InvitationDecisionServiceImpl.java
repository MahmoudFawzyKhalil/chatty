package gov.iti.jets.network;


import gov.iti.jets.commons.callback.Client;
import gov.iti.jets.commons.dtos.ContactDto;
import gov.iti.jets.commons.dtos.InvitationDecisionDto;
import gov.iti.jets.commons.remoteinterfaces.InvitationDecisionService;
import gov.iti.jets.repository.ContactRepository;
import gov.iti.jets.repository.InvitationRepository;
import gov.iti.jets.repository.entities.ContactEntity;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.repository.util.mappers.ContactMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;

public class InvitationDecisionServiceImpl extends UnicastRemoteObject implements InvitationDecisionService {
    private final RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private final ContactRepository contactRepository = repositoryFactory.getContactRepository();
    private final InvitationRepository invitationRepository = repositoryFactory.getInvitationRepository();
    private final  Clients clients = Clients.getInstance();
    private Optional<Client> optionalReceiver;
    private Optional<Client> optionalSender;
    private ContactDto senderContactDto;
    private ContactDto receiverContactDto;

    protected InvitationDecisionServiceImpl() throws RemoteException {
    }

    @Override
    public boolean acceptInvite(InvitationDecisionDto invitationDto) throws RemoteException {
        if (invitationRepository.acceptInvite(invitationDto)) {
            String senderPhoneNumber = invitationDto.getSenderPhoneNumber();
            String receiverPhoneNumber = invitationDto.getReceiverPhoneNumber();

            Optional<ContactEntity> optionalSenderContactEntity = contactRepository.getContact(senderPhoneNumber);
            Optional<ContactEntity> optionalReceiverContactEntity = contactRepository.getContact(receiverPhoneNumber);

            if (optionalSenderContactEntity.isPresent()) {
                senderContactDto = ContactMapper.INSTANCE.contactEntityToDto(optionalSenderContactEntity.get());
                if (optionalReceiverContactEntity.isPresent()) {
                    receiverContactDto = ContactMapper.INSTANCE.contactEntityToDto(optionalReceiverContactEntity.get());

                    optionalReceiver = clients.getClient(receiverPhoneNumber);
                    optionalSender = clients.getClient(senderPhoneNumber);

                    if (optionalSender.isPresent()) {
                        try {
                            Client sender = optionalSender.get();
                            sender.addContact(receiverContactDto);
                        } catch (RemoteException e) {
                            clients.removeClientFromOnlineAndGroups( optionalSender.get() );
                            e.printStackTrace();
                        }
                    }

                    if (optionalReceiver.isPresent()) {
                        try {
                            Client receiver = optionalReceiver.get();
                            receiver.addContact(senderContactDto);
                            return true;
                        } catch (RemoteException e) {
                            clients.removeClientFromOnlineAndGroups( optionalReceiver.get() );
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean refuseInvite(InvitationDecisionDto invitationDto) throws RemoteException {
        return invitationRepository.refuseInvite(invitationDto);
    }
}
