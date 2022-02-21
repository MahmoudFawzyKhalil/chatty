package gov.iti.jets.services;

import gov.iti.jets.commons.dtos.UpdateProfileDto;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface UpdateProfileDao {
    boolean update(UpdateProfileDto updateProfileDto) throws NotBoundException, RemoteException;

    boolean updatePicture(String imageBase64,String phoneNumber)throws NotBoundException, RemoteException;
}
