package gov.iti.jets.services.impls;

import gov.iti.jets.commons.dtos.AnnouncementDto;
import gov.iti.jets.network.Clients;
import gov.iti.jets.services.ServerNotificationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;

public class ServerNotificationsServiceImpl implements ServerNotificationsService {
    Clients clients = Clients.getInstance();
    private Logger logger = LoggerFactory.getLogger( ServerNotificationsServiceImpl.class );

    @Override
    public void notifyClientsOfServerShutDown() {
        clients.getAllClients().forEach( client -> {
            try {
                client.notifyOfServerShutDown();
            } catch (RemoteException e) {
                logger.info( "Failed to notify a client of shutdown." );
                e.printStackTrace();
            }
        } );

        clients.clearAllClients();
    }

    @Override
    public void sendAnnouncementToClients(AnnouncementDto announcementDto) {
        clients.getAllClients().forEach( client -> {
            try {
                client.receiveAnnouncement( announcementDto );
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } );
    }
}
