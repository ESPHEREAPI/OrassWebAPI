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
 * @author frank
 */
public class SendMail {

    Exchange exchange;
    String smtpSever = "";
    String userName = "";
    String password = "";

    public SendMail(String smtpSever, String userName, String password) throws ExchangeServiceException {
        this.smtpSever = smtpSever;
        this.userName = userName;
        this.password = password;
        exchange = new Exchange(smtpSever, userName, password);
    }

    public void sendMailExchange(String adresseReception, String subject, String body) throws ExchangeServiceException {

//        Exchange exchange = new Exchange(smtpSever, userName, password);

        // Create a new e-mail:
        ExchangeMail mail = exchange.createMail();
      
        // Set the recipient, subject and body:
        mail.setToRecipient(adresseReception);
        mail.setSubject(subject);
        mail.setBody(body);

        // Send the message:
        mail.send();
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
