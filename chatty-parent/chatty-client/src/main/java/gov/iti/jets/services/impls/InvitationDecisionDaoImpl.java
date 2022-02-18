package gov.iti.jets.services.impls;

import gov.iti.jets.commons.dtos.InvitationDecisionDto;
import gov.iti.jets.commons.remoteinterfaces.InvitationDecisionService;
import gov.iti.jets.services.InvitationDecisionDao;
import gov.iti.jets.services.util.ServiceFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class InvitationDecisionDaoImpl implements InvitationDecisionDao {
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public boolean acceptInvite(InvitationDecisionDto invitationDecisionDto) throws NotBoundException, RemoteException {
        InvitationDecisionService invitationDecisionService = serviceFactory.getInvitationDecisionService();
        return invitationDecisionService.acceptInvite( invitationDecisionDto );
    }

    @Override
    public boolean refuseInvite(InvitationDecisionDto invitationDecisionDto) throws NotBoundException, RemoteException {
        InvitationDecisionService invitationDecisionService = serviceFactory.getInvitationDecisionService();
        return invitationDecisionService.refuseInvite( invitationDecisionDto );
    }
}
