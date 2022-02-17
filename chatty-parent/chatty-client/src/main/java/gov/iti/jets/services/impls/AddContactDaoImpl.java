package gov.iti.jets.services.impls;

import gov.iti.jets.commons.dtos.ContactDto;
import gov.iti.jets.commons.dtos.LoginDto;
import gov.iti.jets.commons.remoteinterfaces.AddContactService;
import gov.iti.jets.commons.remoteinterfaces.LoginService;
import gov.iti.jets.services.AddContactDao;
import gov.iti.jets.services.LoginDao;
import gov.iti.jets.services.util.ServiceFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class AddContactDaoImpl implements AddContactDao {
    private final ServiceFactory registryFactory = ServiceFactory.getInstance();

    @Override
    public boolean addContacts(ContactDto contactDto) throws NotBoundException, RemoteException {
        AddContactService addContactService = registryFactory.getAddContactService();
        return addContactService.addContacts(contactDto);
    }
}
