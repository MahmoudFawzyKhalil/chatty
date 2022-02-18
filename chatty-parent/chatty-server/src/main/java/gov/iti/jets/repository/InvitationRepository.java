package gov.iti.jets.repository;

import gov.iti.jets.commons.dtos.InvitationDecisionDto;

public interface InvitationRepository {
    boolean acceptInvite(InvitationDecisionDto invitationDecisionDto);
    boolean refuseInvite(InvitationDecisionDto invitationDecisionDto);

}
