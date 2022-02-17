package gov.iti.jets.services;

import gov.iti.jets.commons.callback.Client;

public interface ConnectionDao {
   void registerClient(String phoneNumber, Client client);
   void unregisterClient(String phoneNumber);
}
