package gov.iti.jets.services.impls;

import gov.iti.jets.commons.dtos.LoginDto;
import gov.iti.jets.services.LoginDao;

public class LoginDaoImpl implements LoginDao {
    @Override
    public boolean isAuthenticated(LoginDto loginDto) {
        return false;
    }
}
