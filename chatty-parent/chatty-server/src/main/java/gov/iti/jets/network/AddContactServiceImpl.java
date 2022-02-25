package gov.iti.jets.network;

import gov.iti.jets.commons.callback.Client;
import gov.iti.jets.commons.dtos.AddContactDto;
import gov.iti.jets.commons.dtos.InvitationDto;
import gov.iti.jets.commons.remoteinterfaces.AddContactService;
import gov.iti.jets.repository.InvitationsRepository;
import gov.iti.jets.repository.UserRepository;
import gov.iti.jets.repository.entities.InvitationEntity;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.repository.util.mappers.InvitationMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;

public class AddContactServiceImpl extends UnicastRemoteObject implements AddContactService {
    private transient RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private final InvitationsRepository invitationsRepository = repositoryFactory.getInvitationsRepository();
    private transient UserRepository userRepository = repositoryFactory.getUserRepository();
    private final Clients clients = Clients.getInstance();
    private Optional<Client> optionalReceiver;
    private Optional<Client> optionalSender;
    private InvitationDto senderInvitationDto;
    private InvitationDto receiverInvitationDto;

    public AddContactServiceImpl() throws RemoteException {
    }

    @Override
    public boolean addContacts(AddContactDto addContactDto) throws RemoteException {
        if(userRepository.addContacts(addContactDto)) {
            for (String receiverNumber : addContactDto.getPhoneNumbers()) {
                String senderPhoneNumber = addContactDto.getPhoneNumber();
                String receiverPhoneNumber = receiverNumber;

                Optional<InvitationEntity> optionalSenderInvitationEntity = invitationsRepository.getInvitation(senderPhoneNumber, receiverNumber);
                Optional<InvitationEntity> optionalReceiverInvitationEntity = invitationsRepository.getInvitation(senderPhoneNumber, receiverPhoneNumber);

                if (optionalSenderInvitationEntity.isPresent()) {
                    senderInvitationDto = InvitationMapper.INSTANCE.invitationEntityToDto(optionalSenderInvitationEntity.get());
                    if (optionalReceiverInvitationEntity.isPresent()) {
                        receiverInvitationDto = InvitationMapper.INSTANCE.invitationEntityToDto(optionalReceiverInvitationEntity.get());

                        optionalReceiver = clients.getClient(receiverPhoneNumber);
                        optionalSender = clients.getClient(senderPhoneNumber);

                        if (optionalReceiver.isPresent()) {
                            try {
                                Client receiver = optionalReceiver.get();
                                receiver.addInvitation(senderInvitationDto);
                                return true;
                            } catch (RemoteException e) {
                                clients.removeClientFromOnlineAndGroups( optionalReceiver.get() );
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
