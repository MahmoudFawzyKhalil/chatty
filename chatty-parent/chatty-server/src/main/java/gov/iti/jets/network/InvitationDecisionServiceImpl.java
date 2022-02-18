package gov.iti.jets.network;

import gov.iti.jets.commons.dtos.InvitationDecisionDto;
import gov.iti.jets.commons.remoteinterfaces.InvitationDecisionService;
import gov.iti.jets.repository.InvitationRepository;
import gov.iti.jets.repository.util.RepositoryFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InvitationDecisionServiceImpl extends UnicastRemoteObject implements InvitationDecisionService {
    private RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private InvitationRepository invitationRepository = repositoryFactory.getInvitationRepository();

    protected InvitationDecisionServiceImpl() throws RemoteException {
    }

    @Override
    public boolean acceptInvite(InvitationDecisionDto invitationDto) throws RemoteException {
        return invitationRepository.acceptInvite(invitationDto);
    }

    @Override
    public boolean refuseInvite(InvitationDecisionDto invitationDto) throws RemoteException {
        return invitationRepository.refuseInvite(invitationDto);
    }
}
