package gov.iti.jets.services;

import gov.iti.jets.commons.dtos.InvitationDecisionDto;
import gov.iti.jets.commons.dtos.InvitationDto;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface InvitationDecisionDao {
    boolean acceptInvite( InvitationDecisionDto InvitationDto ) throws NotBoundException, RemoteException;
    boolean refuseInvite( InvitationDecisionDto InvitationDto ) throws NotBoundException, RemoteException;

}
