package org.indusbc.ejb;


import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.logging.Logger;
import jakarta.persistence.TypedQuery;
import org.indusbc.model.RevenueAccount;
import org.indusbc.model.RevenueAccountTransaction;

/**
 *
 * @author user
 */
@Stateless
public class RevenueAccountEjb implements RevenueAccountEjbLocal {
    
    private static Logger LOGGER = Logger.getLogger(RevenueAccountEjb.class.getName());
    
    @PersistenceContext(name = "indusbcPU")
    private EntityManager em;
    
    @Inject
    private RevenuePartyEjbLocal rpl;
    
    @Inject
    private EmailerEjbLocal eMailer;

    @Override
    public RevenueAccount createRevenueAccount(RevenueAccount revenueAccount) {
        em.persist(revenueAccount);
        em.flush();
        LOGGER.info("RevenueAccount record persisted with ID: "+revenueAccount.getId());
        return revenueAccount;
    }

    @Override
    public RevenueAccount findById(int id) {
        return em.find(RevenueAccount.class, id);
    }

    @Override
    public RevenueAccount saveRevenueAccount(RevenueAccount revenueAccount) {
        em.merge(revenueAccount);
        em.flush();
        LOGGER.info("RevenueAccount record saved with ID: "+revenueAccount.getId());
        return revenueAccount;
    }

    @Override
    public boolean addToBalanceRevenueAccount(int accountId, double balanceToAdd) {
        RevenueAccount rA=findById(accountId);
        /*rA.setBalance(rA.getBalance()+balanceToAdd);
        rA = saveRevenueAccount(rA);
        LOGGER.info(String.format("Revenue Account {1} new Balance is {2}", accountId, rA.getBalance()));
        */
        return true;
    }

    @Override
    public boolean createMoneyInRevenueAccount(RevenueAccountTransaction revenueAccountTransaction) {
        em.persist(revenueAccountTransaction);
        em.flush();
        LOGGER.info(String.format("RevenueAccountTransaction record saved with ID: %d",+revenueAccountTransaction.getId()));
        return true;
    }

    @Override
    public boolean createMoneyOutRevenueAccount(RevenueAccountTransaction revenueAccountTransaction) {
        em.persist(revenueAccountTransaction);
        em.flush();
        LOGGER.info(String.format("RevenueAccountTransaction record saved with ID: %d",+revenueAccountTransaction.getId()));
        return true;
    }

    @Override
    public List<RevenueAccountTransaction> getRevenueAccountTransactions(int accountId, int year) {
        TypedQuery<RevenueAccountTransaction> tQ =em.createQuery("select rat from RevenueAccountTransaction rat where rat.revenueAccountId=?1 and rat.year=?2", RevenueAccountTransaction.class);
        tQ.setParameter(1, accountId);
        tQ.setParameter(2, year);
        List<RevenueAccountTransaction> revAcctTxs= tQ.getResultList();
        return revAcctTxs;
    }

    @Override
    public List<RevenueAccount> getAll(int year) {
        TypedQuery<RevenueAccount> tQ =em.createQuery("select ra from RevenueAccount ra where ra.year=?1", RevenueAccount.class);
        tQ.setParameter(1, year);
        List<RevenueAccount> revAccts = tQ.getResultList();
        LOGGER.info(String.format("RevenueAccount %d extracted for year %d",revAccts.size(),year));
        return revAccts; 
        
    }

    @Override
    public List<RevenueAccount> getAllByCategoryAndYear(String revenueCategory, int year) {
        TypedQuery<RevenueAccount> tQ =em.createQuery("select ra from RevenueAccount ra join RevenueCategory rc where ra.revenueCategoryId=rc.id and rc.revenueCategory=?1 and ra.year=?2", RevenueAccount.class);
        tQ.setParameter(1, revenueCategory);
        tQ.setParameter(2, year);
        List<RevenueAccount> revAccts= tQ.getResultList();
        return revAccts;
    }

}
