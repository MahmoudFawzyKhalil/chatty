package gov.iti.jets.services;

import gov.iti.jets.commons.dtos.LoginDto;

public interface LoginDao {
    boolean isAuthenticated(LoginDto loginDto);
}
