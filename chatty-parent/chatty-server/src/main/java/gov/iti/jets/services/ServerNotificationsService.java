package gov.iti.jets.services;

import gov.iti.jets.commons.dtos.AnnouncementDto;

public interface ServerNotificationsService {
    void notifyClientsOfServerShutDown();
    void sendAnnouncementToClients( AnnouncementDto announcementDto );
}
