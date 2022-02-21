package gov.iti.jets.services;

import gov.iti.jets.commons.callback.Client;
import gov.iti.jets.commons.dtos.StatusNotificationDto;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public interface ConnectionDao {
   void registerClient(String phoneNumber, Client client) throws NotBoundException, RemoteException;

   void unregisterClient(String phoneNumber) throws NotBoundException, RemoteException;

   void registerGroups(List<Integer> groupIds, Client client) throws NotBoundException, RemoteException;

   void notifyOthersOfStatusUpdate( StatusNotificationDto statusNotificationDto, List<String> contactsToNotifyPhoneNumbers ) throws NotBoundException, RemoteException;
   List<String> getOfflineContacts( List<String> contactsPhoneNumbers ) throws  NotBoundException, RemoteException;
}
