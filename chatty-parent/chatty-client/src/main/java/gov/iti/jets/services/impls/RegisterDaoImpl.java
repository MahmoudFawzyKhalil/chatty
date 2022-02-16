package gov.iti.jets.services.impls;

import gov.iti.jets.commons.dtos.RegisterDto;
import gov.iti.jets.commons.remoteinterfaces.RegisterService;
import gov.iti.jets.services.RegisterDao;
import gov.iti.jets.services.util.ServiceFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RegisterDaoImpl implements RegisterDao {
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public boolean register(RegisterDto registerDto) throws NotBoundException, RemoteException {
        RegisterService registerService = serviceFactory.getRegisterService();
        return registerService.register(registerDto);
    }
}
