package org.indusbc.ejb;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;
import org.indusbc.ejb.exception.UserRegisteredAlreadyException;
import org.indusbc.model.Access;
import org.indusbc.model.RevenueAccount;
import org.indusbc.model.RevenueParty;
import org.indusbc.util.HashGenerator;

/**
 *
 * @author user
 */
@Stateless
public class RevenuePartyEjb implements RevenuePartyEjbLocal {
    
    private static Logger LOGGER = Logger.getLogger(RevenuePartyEjb.class.getName());

    @PersistenceContext(name = "indusbcPU")
    private EntityManager em;
    
    @Inject
    RevenueAccountEjbLocal ral;
    
    @Inject
    EmailerEjbLocal eel;
    
    @Inject
    private AccessEjbLocal ael;

    @Override
    public RevenueParty createRevenueParty(RevenueParty revenueParty) throws UserRegisteredAlreadyException, MessagingException {
        String[] revAcctHashes = new String[revenueParty.getRevenueAccounts().size()];
        for (int i=0; i<revenueParty.getRevenueAccounts().size(); i++ ) {
		RevenueAccount ra = revenueParty.getRevenueAccounts().get(i);
		revAcctHashes[i]=ra.getRevenueAccountHash();
	}
        em.persist(revenueParty);
        em.flush();
        LOGGER.info(String.format("Revenue Party created with ID: %d",revenueParty.getId()));
        for(RevenueAccount ra : revenueParty.getRevenueAccounts()){
            ra.setRevenuePartyId(revenueParty.getId());
            ra.setCreatedOn(new Timestamp(System.currentTimeMillis()));
            ral.createRevenueAccount(ra);
        }
        LOGGER.info(String.format("%d Revenue Accounts updated with RevenueParty Id %d",revenueParty.getRevenueAccounts().size(),revenueParty.getId()));
        //Create Acccess record now.
        Access access= ael.createRevenuePartyAccess(revenueParty);
        LOGGER.info(String.format("Access ID for the Revenue Party %d is %d",revenueParty.getId(),access.getId()));
        return revenueParty;
    }

    @Override
    public RevenueParty findById(int id) {
        return em.find(RevenueParty.class, id);
    }
    
    @Override
    public boolean isEmailRegistered(String email) {
        TypedQuery<RevenueParty> tQ=em.createQuery("select rp from RevenueParty rp where rp.email=?1", RevenueParty.class);
        tQ.setParameter(1, email);
        try{
            tQ.getSingleResult();
            return true;
        }catch(NoResultException ex){
            //good for us
            return false;
        }
    }

    @Override
    public RevenueParty updateRevenueParty(RevenueParty revenueParty) {
        String partyHash=HashGenerator.generateHash(revenueParty.getName().concat(revenueParty.getEmail()).concat(revenueParty.getIdentityType()).concat(revenueParty.getIdentityType()));
        revenueParty.setPartyHash(partyHash);
        em.merge(revenueParty);
        LOGGER.info(String.format("Revenue Party with ID: %d updated",revenueParty.getId()));
        return revenueParty;
    }

    @Override
    public List<RevenueAccount> findRevenueAccountsOfParty(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public RevenueParty addMoreRevenueAccounts(RevenueParty revenueParty, List<RevenueAccount> moreRevenueAccounts) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public RevenueParty findByEmail(String email) {
        TypedQuery<RevenueParty> tQ=em.createQuery("select rp from RevenueParty rp where rp.email=?1", RevenueParty.class);
        tQ.setParameter(1, email);
        return tQ.getSingleResult();
    }
}
