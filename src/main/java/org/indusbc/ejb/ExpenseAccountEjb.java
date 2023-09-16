package org.indusbc.ejb;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.indusbc.model.ExpenseAccount;
import org.indusbc.model.ExpenseAccountTransaction;

/**
 *
 * @author user
 */
@Stateless
public class ExpenseAccountEjb implements ExpenseAccountEjbLocal {
    
    private static Logger LOGGER = Logger.getLogger(ExpenseAccountEjb.class.getName());
    
    @PersistenceContext(name = "indusbcPU")
    private EntityManager em;
    
    @Inject
    private ExpensePartyEjbLocal epl;
    
    @Inject
    private EmailerEjbLocal eMailer;

    @Override
    public ExpenseAccount createExpenseAccount(ExpenseAccount expenseAccount){
        em.persist(expenseAccount);
        em.flush();
        LOGGER.info("ExpenseAccount record persisted with ID: "+expenseAccount.getId());
        return expenseAccount;
    }

    @Override
    public ExpenseAccount findById(int id) {
        return em.find(ExpenseAccount.class, id);
    }

    @Override
    public ExpenseAccount saveExpenseAccount(ExpenseAccount expenseAccount) {
        em.merge(expenseAccount);
        em.flush();
        LOGGER.info("ExpenseAccount record saved with ID: "+expenseAccount.getId());
        return expenseAccount;
    }

    @Override
    public boolean addToBalanceExpenseAccount(int accountId, double balanceToAdd) {
        ExpenseAccount eA=findById(accountId);
        //eA.setBalance(eA.getBalance()+balanceToAdd);
        eA = saveExpenseAccount(eA);
        //LOGGER.info(String.format("Expense Account {1} new Balance is {2}", accountId, eA.getBalance()));
        return true;
    }

    @Override
    public boolean withdrawFromBalanceExpenseAccount(int accountId, double balanceToWithdraw) {
        ExpenseAccount eA=findById(accountId);
        /*eA.setBalance(eA.getBalance()-balanceToWithdraw);
        if (eA.getBalance()<0){
            int expPartyId = eA.getExpensePartyId();
            ExpenseParty ep=epl.findById(expPartyId);
            try {
                eMailer.sendExpensePartyAccountOverdrawnEmail(ep);
            } catch (MessagingException ex) {
                Logger.getLogger(ExpenseAccountEjb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
        em.persist(eA);
        return true;
    }

    @Override
    public boolean createMoneyOutExpenseAccount(ExpenseAccountTransaction expenseAccountTransaction) {
        em.persist(expenseAccountTransaction);
        em.flush();
        LOGGER.info(String.format("ExpenseAccountTransaction record saved with ID: %d",+expenseAccountTransaction.getId()));
        return true;
    }

    @Override
    public boolean createMoneyInExpenseAccount(ExpenseAccountTransaction expenseAccountTransaction) {
        em.persist(expenseAccountTransaction);
        em.flush();
        LOGGER.info(String.format("ExpenseAccountTransaction record saved with ID: %d",+expenseAccountTransaction.getId()));
        return true;
    }

    @Override
    public List<ExpenseAccountTransaction> getExpenseAccountTransactions(int accountId, int year) {
        TypedQuery<ExpenseAccountTransaction> tQ =em.createQuery("select eat from ExpenseAccountTransaction eat where eat.expenseAccountId=?1 and eat.year=?2", ExpenseAccountTransaction.class);
        tQ.setParameter(1, accountId);
        tQ.setParameter(2, year);
        List<ExpenseAccountTransaction> expAcctTxs= tQ.getResultList();
        return expAcctTxs;
    }

    @Override
    public List<ExpenseAccount> getAll(int year) {
        TypedQuery<ExpenseAccount> tQ =em.createQuery("select ea from ExpenseAccount ea where ea.year=?1", ExpenseAccount.class);
        tQ.setParameter(1, year);
        List<ExpenseAccount> expAccts = tQ.getResultList();
        LOGGER.info(String.format("ExpenseAccount %d extracted for year %d",expAccts.size(),year));
        return expAccts; 
        
    }

    @Override
    public List<ExpenseAccount> getAllByCategoryAndYear(String expenseCategory, int year) {
        TypedQuery<ExpenseAccount> tQ =em.createQuery("select ea from ExpenseAccount ea join ExpenseCategory ec where ea.expenseCategoryId=ec.id and ec.expenseCategory=?1 and ea.year=?2", ExpenseAccount.class);
        tQ.setParameter(1, expenseCategory);
        tQ.setParameter(2, year);
        List<ExpenseAccount> expAccts= tQ.getResultList();
        return expAccts;
    }
    
}
