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
    public boolean acceptInvite(InvitationDecisionDto InvitationDto) throws NotBoundException, RemoteException {
        //return addContactService.addContacts( addContactDto );

        InvitationDecisionService invitationDecisionService = serviceFactory.getInvitationDecisionService();
        return false;
    }

    @Override
    public boolean refuseInvite(InvitationDecisionDto InvitationDto) throws NotBoundException, RemoteException {
        //AddContactService addContactService = registryFactory.getAddContactService();
        //return addContactService.addContacts( addContactDto );
        return false;
    }
}
