package gov.iti.jets.commons.remoteinterfaces;

import gov.iti.jets.commons.dtos.InvitationDecisionDto;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface InvitationDecisionService extends Remote {
    public boolean acceptInvite(InvitationDecisionDto InvitationDto) throws RemoteException;;
    public boolean refuseInvite(InvitationDecisionDto InvitationDto) throws RemoteException;;

}