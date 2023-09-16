package org.indusbc.ejb;

import jakarta.ejb.Local;
import jakarta.mail.MessagingException;
import org.indusbc.model.ExpenseParty;
import org.indusbc.model.RevenueParty;

/**
 *
 * @author root
 */
@Local
public interface EmailerEjbLocal {
    
    public void sendRevenuePartyRegistrationEmail(RevenueParty rp);
    public void sendExpensePartyRegistrationEmail(ExpenseParty ep);
    public void sendAccessCreatedEmail(String email);
    public void sendExpensePartyAccountOverdrawnEmail(ExpenseParty ep) throws MessagingException;
    
}
