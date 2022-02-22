package gov.iti.jets.services;

import gov.iti.jets.commons.dtos.UpdateProfileDto;
import gov.iti.jets.commons.dtos.UpdateProfilePicDto;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface UpdateProfileDao {
    boolean update(UpdateProfileDto updateProfileDto) throws NotBoundException, RemoteException;

    boolean updatePicture(UpdateProfilePicDto updateProfilePicDto)throws NotBoundException, RemoteException;
}
