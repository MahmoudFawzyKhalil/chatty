package gov.iti.jets.repository.impls;

import gov.iti.jets.repository.ContactRepository;
import gov.iti.jets.repository.InvitationsRepository;
import gov.iti.jets.repository.entities.ContactEntity;
import gov.iti.jets.repository.entities.InvitationEntity;
import gov.iti.jets.repository.util.ConnectionPool;
import gov.iti.jets.repository.util.RepositoryFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InvitationsRepositoryImpl implements InvitationsRepository {
    @Override
    public Optional<InvitationEntity> getInvitation(String sender, String receiver) {

        ContactRepository contactRepository = RepositoryFactory.getInstance().getContactRepository();
        Optional<InvitationEntity> optionalInvitationEntity = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("select sender, receiver from invitations where sender = ? and receiver = ?");
        ) {
            statement.setString(1, sender);
            statement.setString(2, receiver);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    InvitationEntity invitationEntity = new InvitationEntity();
                    Optional<ContactEntity> optionalContactEntity = contactRepository.getContact(resultSet.getString("sender"));
                    if(!optionalContactEntity.isEmpty()){
                        invitationEntity.setContactEntity(optionalContactEntity.get());
                    }

                    optionalInvitationEntity = Optional.of(invitationEntity);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalInvitationEntity;
    }

    @Override
    public List<InvitationEntity> getInvitations(String phoneNumber) {
        ContactRepository contactRepository = RepositoryFactory.getInstance().getContactRepository();
        List<InvitationEntity> invitationList = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("select sender from invitations where receiver = ?");
        ) {
            statement.setString(1, phoneNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                Optional<ContactEntity> contactEntity;
                while(resultSet.next()) {
                    contactEntity = contactRepository.getContact(resultSet.getString("sender"));
                    if (!contactEntity.isEmpty()) {
                        invitationList.add(new InvitationEntity(contactEntity.get()));
                    }
                }
            }
            } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return invitationList;

    }
}
