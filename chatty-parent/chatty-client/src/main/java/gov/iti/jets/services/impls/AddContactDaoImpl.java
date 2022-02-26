package gov.iti.jets.services.impls;

import gov.iti.jets.commons.dtos.AddContactDto;
import gov.iti.jets.commons.remoteinterfaces.AddContactService;
import gov.iti.jets.services.AddContactDao;
import gov.iti.jets.services.util.ServiceFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class AddContactDaoImpl implements AddContactDao {
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public boolean addContacts( AddContactDto addContactDto ) throws NotBoundException, RemoteException {
        AddContactService addContactService = serviceFactory.getAddContactService();
        return addContactService.addContacts( addContactDto );
    }

    @Override
    public boolean doUsersExist( AddContactDto addContactDto ) throws NotBoundException, RemoteException {
        AddContactService addContactService = serviceFactory.getAddContactService();
        return addContactService.doUsersExist( addContactDto );
    }

    @Override
    public boolean didISendAnInvitationBefore( AddContactDto addContactDto ) throws NotBoundException, RemoteException {
        AddContactService addContactService = serviceFactory.getAddContactService();
        return addContactService.didISendAnInvitationBefore( addContactDto );
    }
}
