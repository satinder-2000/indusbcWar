package org.indusbc.ejb;

import jakarta.ejb.Local;
import jakarta.mail.MessagingException;
import java.util.List;
import org.indusbc.ejb.exception.UserRegisteredAlreadyException;
import org.indusbc.model.RevenueAccount;
import org.indusbc.model.RevenueParty;

/**
 *
 * @author root
 */
@Local
public interface RevenuePartyEjbLocal {
    public RevenueParty createRevenueParty(RevenueParty revenueParty) throws UserRegisteredAlreadyException, MessagingException;
    public RevenueParty findById(int id);
    public RevenueParty findByEmail(String email);
    public boolean isEmailRegistered(String email);
    public RevenueParty updateRevenueParty(RevenueParty revenueParty);
    public List<RevenueAccount> findRevenueAccountsOfParty(String email);
    public RevenueParty addMoreRevenueAccounts(RevenueParty revenueParty, List<RevenueAccount> moreRevenueAccounts);
    
}
