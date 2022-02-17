package gov.iti.jets.services.impls;

import gov.iti.jets.commons.dtos.LoginDto;
import gov.iti.jets.commons.remoteinterfaces.LoginService;
import gov.iti.jets.services.LoginDao;
import gov.iti.jets.services.util.ServiceFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LoginDaoImpl implements LoginDao {
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public boolean isAuthenticated(LoginDto loginDto) throws NotBoundException, RemoteException {
        LoginService loginService = serviceFactory.getLoginService();
        return loginService.login(loginDto);
    }
}
