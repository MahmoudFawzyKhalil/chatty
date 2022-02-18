package gov.iti.jets.repository.impls;

import gov.iti.jets.commons.dtos.InvitationDecisionDto;
import gov.iti.jets.repository.InvitationRepository;
import gov.iti.jets.repository.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InvitationRepositoryImpl implements InvitationRepository {


    @Override
    public boolean acceptInvite(InvitationDecisionDto invitationDecisionDto) {
        try (Connection connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from invitations where contact_phone_number = ? and contactee_phone_number=?")) {
            preparedStatement.setString(1, invitationDecisionDto.getReceiverPhoneNumber());
            preparedStatement.setString(2, invitationDecisionDto.getSenderPhoneNumber());
            int numInvitationsDeleted = preparedStatement.executeUpdate();

            int numContactsInserted;
            try (PreparedStatement preparedStatement1 = connection.prepareStatement("insert into contacts values(?, ?)")) {
                preparedStatement1.setString(1, invitationDecisionDto.getReceiverPhoneNumber());
                preparedStatement1.setString(2, invitationDecisionDto.getSenderPhoneNumber());

                numContactsInserted = preparedStatement1.executeUpdate();
            }

            if (numInvitationsDeleted >0 && numContactsInserted > 0) {
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
            PreparedStatement preparedStatement = connection.prepareStatement("delete from invitations where contact_phone_number = ? and contactee_phone_number= ?")) {
            preparedStatement.setString(1, invitationDecisionDto.getReceiverPhoneNumber());
            preparedStatement.setString(2, invitationDecisionDto.getSenderPhoneNumber());
            int numInvitationsDeleted = preparedStatement.executeUpdate();

            if (numInvitationsDeleted > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
