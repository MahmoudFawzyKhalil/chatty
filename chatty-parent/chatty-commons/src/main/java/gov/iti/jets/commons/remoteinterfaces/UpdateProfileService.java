package gov.iti.jets.commons.remoteinterfaces;

import gov.iti.jets.commons.dtos.UpdateProfileDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UpdateProfileService extends Remote {
    boolean updateProfile(UpdateProfileDto updateProfileDto) throws RemoteException;

    boolean updateProfilePicture(String imageBase64,String phoneNumber) throws  RemoteException;
}
