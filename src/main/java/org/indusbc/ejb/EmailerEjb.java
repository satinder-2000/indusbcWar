package org.indusbc.ejb;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import java.util.Map;
import org.indusbc.model.AccessType;
import org.indusbc.model.ExpenseParty;
import org.indusbc.model.RevenueParty;
import org.indusbc.util.IndusConstants;

/**
 *
 * @author user
 */
@Stateless
public class EmailerEjb implements EmailerEjbLocal {
    
    private static Logger LOGGER = Logger.getLogger(EmailerEjb.class.getName());
    
    @Resource(name = "mail/indusbc")
    Session session;
    
    @Resource(name = "WebURI")
    String webURI;
    
    @Resource(name="accessCreateURI")
    String accessCreateURI;
    
    @Resource(name="loginURI")
    String loginURI;
    
    @Inject
    private EmailMessageEjbLocal emailMessageEjbLocal;

    @Override
    public void sendRevenuePartyRegistrationEmail(RevenueParty rp) {
        Map<String, String> emailMessagesMap= emailMessageEjbLocal.getAllForRevenuePartyRegisterAsMap();
        MimeMessage mimeMessage = new MimeMessage(session);
        Multipart multipart = new MimeMultipart();
        //<h2>Dear, "+%s+ "</h2>"
        String salute=String.format(emailMessagesMap.get(IndusConstants.EMAIL_SALUTE), rp.getEmail());
        StringBuilder htmlMsg = new StringBuilder(salute);
        String congrats=String.format(emailMessagesMap.get(IndusConstants.EMAIL_CONGRATULATIONS),AccessType.REVENUE_PARTY.toString());
        htmlMsg.append(congrats);
        String createAccess=emailMessagesMap.get(IndusConstants.EMAIL_CREATE_ACCESS);
        htmlMsg.append(createAccess);
        htmlMsg.append(webURI).append(accessCreateURI).append(rp.getEmail()).append("&accessType="+AccessType.REVENUE_PARTY.toString());
        String bestWishes=emailMessagesMap.get(IndusConstants.EMAIL_BEST_WISHES);
        htmlMsg.append(bestWishes);
        MimeBodyPart htmlPart = new MimeBodyPart();
        try {
            htmlPart.setContent( htmlMsg.toString(), "text/html; charset=utf-8" );
            multipart.addBodyPart(htmlPart);
            mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(rp.getEmail()));
            mimeMessage.setContent(multipart);
            mimeMessage.setSubject(emailMessagesMap.get(IndusConstants.EMAIL_SUBJECT));
            Transport.send(mimeMessage);
            LOGGER.info("Sent message successfully....");
        } catch (MessagingException ex) {
            Logger.getLogger(EmailerEjb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendExpensePartyRegistrationEmail(ExpenseParty ep) {
        Map<String, String> emailMessagesMap= emailMessageEjbLocal.getAllForExpensePartyRegisterAsMap();
        MimeMessage mimeMessage = new MimeMessage(session);
        Multipart multipart = new MimeMultipart();
        //<h2>Dear, "+%s+ "</h2>"
        String salute=String.format(emailMessagesMap.get(IndusConstants.EMAIL_SALUTE), ep.getEmail());
        StringBuilder htmlMsg = new StringBuilder(salute);
        String congrats=String.format(emailMessagesMap.get(IndusConstants.EMAIL_CONGRATULATIONS),AccessType.EXPENSE_PARTY.toString());
        htmlMsg.append(congrats);
        String createAccess=emailMessagesMap.get(IndusConstants.EMAIL_CREATE_ACCESS);
        htmlMsg.append(createAccess);
        htmlMsg.append(webURI).append(accessCreateURI).append(ep.getEmail()).append("&accessType="+AccessType.EXPENSE_PARTY.toString());
        String bestWishes=emailMessagesMap.get(IndusConstants.EMAIL_BEST_WISHES);
        htmlMsg.append(bestWishes);
        MimeBodyPart htmlPart = new MimeBodyPart();
        try {
            htmlPart.setContent( htmlMsg.toString(), "text/html; charset=utf-8" );
            multipart.addBodyPart(htmlPart);
            mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(ep.getEmail()));
            mimeMessage.setContent(multipart);
            mimeMessage.setSubject(emailMessagesMap.get(IndusConstants.EMAIL_SUBJECT));
            Transport.send(mimeMessage);
            LOGGER.info("Sent message successfully....");
        } catch (MessagingException ex) {
            Logger.getLogger(EmailerEjb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendAccessCreatedEmail(String email) {
        Map<String, String> emailMessagesMap= emailMessageEjbLocal.getAllForAccessCreatedAsMap();
        MimeMessage mimeMessage = new MimeMessage(session);
        Multipart multipart = new MimeMultipart();
        //<h2>Dear, "+%s+ "</h2>"
        String salute=String.format(emailMessagesMap.get(IndusConstants.EMAIL_SALUTE), email);
        StringBuilder htmlMsg = new StringBuilder(salute);
        String congrats=emailMessagesMap.get(IndusConstants.EMAIL_CONGRATULATIONS);
        htmlMsg.append(congrats);
        String lookForwardMsg=emailMessagesMap.get(IndusConstants.EMAIL_LOOK_FORWARD_MSG);
        htmlMsg.append(lookForwardMsg);
        String resumeActivity=emailMessagesMap.get(IndusConstants.EMAIL_RESUME_ACTIVITY);
        htmlMsg.append(resumeActivity);
        String loginLink=webURI+loginURI;
        htmlMsg.append(loginLink);
        String bestWishes=emailMessagesMap.get(IndusConstants.EMAIL_BEST_WISHES);
        htmlMsg.append(bestWishes);
        
        MimeBodyPart htmlPart = new MimeBodyPart();
        try {
            htmlPart.setContent( htmlMsg.toString(), "text/html; charset=utf-8" );
            multipart.addBodyPart(htmlPart);
            mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(email));
            mimeMessage.setContent(multipart);
            mimeMessage.setSubject(emailMessagesMap.get(IndusConstants.EMAIL_SUBJECT));
            Transport.send(mimeMessage);
            LOGGER.info("Sent message successfully....");
        } catch (MessagingException ex) {
            Logger.getLogger(EmailerEjb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void sendExpensePartyAccountOverdrawnEmail(ExpenseParty ep) throws MessagingException {
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setSubject("Warning - Account is overdrawn!!");
        Multipart multipart = new MimeMultipart();
        StringBuilder htmlMsg = new StringBuilder("<h2>Dear, "+ep.getName()+ "</h2>");
        htmlMsg.append("<p>\"Just to let you know that your Account is overdrawn. Please take action urgently to top it up.\\n\\n\"</p>");
        htmlMsg.append("<p>Best Wishes, <br/>www.bjmbc.net Admin</p>");
        MimeBodyPart htmlPart = new MimeBodyPart();
        try {
            
            htmlPart.setContent( htmlMsg.toString(), "text/html; charset=utf-8" );
            multipart.addBodyPart(htmlPart);
            mimeMessage.setContent(multipart);
            Transport.send(mimeMessage);
            LOGGER.info("Sent message successfully....");
        } catch (MessagingException ex) {
            Logger.getLogger(EmailerEjb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
