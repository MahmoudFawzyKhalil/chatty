package gov.iti.jets.services;

public interface ServerNotificationsService {
    void notifyClientsOfServerShutDown();
    void sendAnnouncementToClients();
}
