package gov.iti.jets.repository.impls;

import gov.iti.jets.commons.dtos.InvitationDecisionDto;
import gov.iti.jets.repository.InvitationRepository;
import gov.iti.jets.repository.util.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InvitationRepositoryImpl implements InvitationRepository {

    private Logger logger = LoggerFactory.getLogger( InvitationRepositoryImpl.class );

    @Override
    public boolean acceptInvite(InvitationDecisionDto invitationDecisionDto) {
        try (Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from invitations where sender = ? and receiver = ?")) {
            logger.info( "An attempt to accept an invitation was made: " + invitationDecisionDto.toString() );
            preparedStatement.setString(1, invitationDecisionDto.getSenderPhoneNumber());
            preparedStatement.setString(2, invitationDecisionDto.getReceiverPhoneNumber());
            int numInvitationsDeleted = preparedStatement.executeUpdate();

            int numContactsInserted = 0;
            try (PreparedStatement preparedStatement1 = connection.prepareStatement("insert into contacts values(?, ?)")) {
                preparedStatement1.setString(1, invitationDecisionDto.getSenderPhoneNumber());
                preparedStatement1.setString(2, invitationDecisionDto.getReceiverPhoneNumber());

                numContactsInserted = preparedStatement1.executeUpdate();
            }

            if (numInvitationsDeleted > 0 && numContactsInserted > 0) {
                    return true;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean refuseInvite(InvitationDecisionDto invitationDecisionDto) {
        try (Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from invitations where sender = ? and receiver = ?")) {
            logger.info( "An attempt to refuse an invitation was made: " + invitationDecisionDto.toString() );
            preparedStatement.setString(1, invitationDecisionDto.getSenderPhoneNumber());
            preparedStatement.setString(2, invitationDecisionDto.getReceiverPhoneNumber());

            int numInvitationsDeleted = preparedStatement.executeUpdate();

            if (numInvitationsDeleted > 0){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
