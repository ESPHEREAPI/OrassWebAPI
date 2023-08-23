/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import com.moyosoft.exchange.Exchange;
import com.moyosoft.exchange.ExchangeServiceException;
import com.moyosoft.exchange.mail.ExchangeMail;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Frankjiatou
 */
public class Office365 {

    private Exchange exchange;
    private String hostname, username, password;

    public Office365() {
    }

    public Office365(String hostname, String username, String password) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;

    }

    public Exchange connection() {
        try {
            Exchange.Settings.setTrustInvalidCertificate(Boolean.FALSE);
            exchange = new Exchange(hostname, username, password);

        } catch (ExchangeServiceException ex) {
            exchange = null;
        }
        return exchange;
    }
    
       public void sendMailExchange(List<String>colAdresse, String subject, String body) throws ExchangeServiceException {

//        Exchange exchange = new Exchange(smtpSever, userName, password);

        // Create a new e-mail:
        ExchangeMail mail = exchange.createMail();
      
        // Set the recipient, subject and body:
        mail.setToRecipients(colAdresse);
        mail.setSubject(subject);
        mail.setBody(body);

        // Send the message:
        mail.send();
    }

    
    public void SendMailExchangeWithAttachment(String adresseReception, String subject, String body, String pathFile) throws ExchangeServiceException, IOException {
//        Exchange exchange = new Exchange("" + this.smtpSever, "" + this.userName, "" + this.password);

        // Create a new e-mail:
        ExchangeMail mail = exchange.createMail();

        // Set the recipient, subject and body:
//        adresseReception+=";"+"frankjiatou@yahoo.fr";
        mail.setToRecipient(adresseReception);
        mail.setSubject(subject);
        mail.setBody(body);

        // Add a new file attachment:
        mail.getAttachments().add(new File(pathFile));

        // Send the message:
        mail.send();
    }


}
