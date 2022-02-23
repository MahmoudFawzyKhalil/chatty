package gov.iti.jets.commons.remoteinterfaces;

import gov.iti.jets.commons.dtos.UpdateProfileDto;
import gov.iti.jets.commons.dtos.UpdateProfilePicDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UpdateProfileService extends Remote {
    boolean updateProfile(UpdateProfileDto updateProfileDto) throws RemoteException;

    boolean updateProfilePicture(UpdateProfilePicDto updateProfileDto) throws  RemoteException;
}
