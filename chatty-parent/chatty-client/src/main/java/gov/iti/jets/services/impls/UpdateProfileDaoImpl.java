package gov.iti.jets.services.impls;

import gov.iti.jets.commons.dtos.UpdateProfileDto;
import gov.iti.jets.commons.remoteinterfaces.UpdateProfileService;
import gov.iti.jets.services.UpdateProfileDao;
import gov.iti.jets.services.util.ServiceFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class UpdateProfileDaoImpl implements UpdateProfileDao {
    @Override
    public boolean update(UpdateProfileDto updateProfileDto) throws NotBoundException, RemoteException {
        UpdateProfileService updateProfileService = ServiceFactory.getInstance().getUpdateProfileService();
        return updateProfileService.updateProfile(updateProfileDto);
    }
}
