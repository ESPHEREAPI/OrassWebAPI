/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import com.moyosoft.exchange.Exchange;
import com.moyosoft.exchange.ExchangeServiceException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.ejb.Asynchronous;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import microsolft_exchangemail.SendMail;

import modele.Personne;
import static org.atmosphere.annotation.AnnotationUtil.logger;

/**
 *
 * @author frank
 */
public class Mail {

    String serveurSTMP;
    int portSTMP;
    String username = "";
    String password = "";
    private Multipart _multipart;
    private boolean _auth;
    private String _subject = "";
    private String _body = "";
    private static final Logger LOGGER = Logger.getAnonymousLogger();

    public Mail(String serveurSTMP, int portSTMP, String username, String password) {

        _multipart = new MimeMultipart();
        _auth = true;
        this.serveurSTMP = serveurSTMP;
        this.portSTMP = portSTMP;
        this.username = username;
        this.password = password;
        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mc);

    }

    public Mail() {

        _multipart = new MimeMultipart();
        _auth = true;
        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mc);
    }

    public boolean send(String from, String to, String subject, String body) throws Exception {
        Properties props = _setProperties();
        if (!this.username.equals("") && !this.password.equals("") && to.length() > 0 && !from.equals("") && !subject.equals("") && !body.equals("")) {
//            Session session = Session.getInstance(props, null);
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
//            Transport transport = session.getTransport("smtp");
//            if (!transport.isConnected()) {
//                /**
//                 * ouvre la connexion si elle ne l'est pas faite !
//                 */
//                System.out.println("ouverture connextion");
//                System.out.println("user" + username);
//                System.out.println("password" + password);
//                transport.connect(this.username, this.password);
//                if (!transport.isConnected()) {
//                    System.out.println("connection ouvert");
//                } else {
//                    System.out.println("connection non ouvert");
//                }
//            }
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
//            InternetAddress[] addressTo = new InternetAddress[to.length()];
//            for (int i = 0; i < to.length(); i++) {
//                addressTo[i] = new InternetAddress(to[i]);
//            }
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

//            msg.setRecipients(MimeMessage.RecipientType.TO, new InternetAddress());
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            _multipart.addBodyPart(messageBodyPart);
            msg.setContent(_multipart);
            Transport.send(msg);
//            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("envoyÃ©");
            return true;
        } else {
            return false;
        }

    }

    @Asynchronous
    public Boolean envoiEmail(Collection<Personne> emailPers, String from) throws MessagingException, NamingException, UnsupportedEncodingException {
        Properties props = _setProperties();
        if (!this.username.equals("") && !this.password.equals("") && emailPers.isEmpty() == Boolean.FALSE && !from.equals("")) {
            System.out.println("Verification OK");
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            System.out.println("Session mail connecter");

//            MimeMessage msg = new MimeMessage(session);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            InternetAddress[] adress = null;
            int i = 0;
            if (!emailPers.isEmpty()) {
                adress = new InternetAddress[emailPers.size()];
            }
            String adresses = "";
            for (Personne personne : emailPers) {
                if (personne.getAdresse().getEmail() != null && !personne.getAdresse().getEmail().trim().equals("")) {
                    System.out.println("adresse :" + personne.getAdresse().getEmail());
                    adresses += personne.getAdresse().getEmail();
                    adress[i] = new InternetAddress(adresses);
                }

            }

            message.setRecipients(RecipientType.TO, adress);
//     

            message.setSubject(_subject);

            message.setSentDate(new Date());
            message.setText(_body);
//            BodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setText(_body);
//            _multipart.addBodyPart(messageBodyPart);
//            msg.setContent(_multipart);
//            Transport transport = session.getTransport("smtp");
//            System.out.println("Transport ok");
            try {
                Transport.send(message, message.getAllRecipients());
                System.out.println("message envoyer");
//                transport.connect();
//                transport.sendMessage(message, message.getAllRecipients());
                logger.info("Email envoyÃ©");
            } catch (MessagingException e) {
                LOGGER.log(Level.WARNING, "Erreur lors de l envoit : " + e.getMessage(), e);
                logger.error("Erreur lors de l envoit : " + e.getMessage() + " cause de l envoit " + e.getCause().getMessage());
                return false;
            } catch (Exception ex) {
                return false;
            }

            System.out.println("envoyÃ©");
            return true;
        } else {
            return false;
        }
    }

    @Asynchronous
    public Boolean sendExchangeMail(Collection<Personne> emailPers) {

        if (!this.username.equals("") && !this.password.equals("") && emailPers.isEmpty() == Boolean.FALSE) {

            try {
//                SendMail mail = new SendMail(this.serveurSTMP, username, password);
                Office365 office365 = new Office365(serveurSTMP, username, password);
                Exchange mail = office365.connection();
                if (mail == null) {
                    return false;
                }

                List<String> colAdresse = new ArrayList<>();
                int i = 0;

                String adresses = "";
                for (Personne personne : emailPers) {
                    if (personne.getAdresse().getEmail() != null && !personne.getAdresse().getEmail().trim().equals("")) {
                        System.out.println("adresse :" + personne.getAdresse().getEmail());

                        colAdresse.add(personne.getAdresse().getEmail());
                    }
                    office365.sendMailExchange(colAdresse, _subject, _body);

                }
            } catch (ExchangeServiceException ex) {
                LOGGER.log(Level.WARNING, "Erro ao enviar mensagem: " + ex.getMessage(), ex);
                return false;
            }

            System.out.println("envoyÃ©");
            return true;
        } else {
            return false;
        }
    }

    @Asynchronous
    public Boolean sendExchangeMailWithAttachement(String emailPers, String pathFile) {

        if (!this.username.equals("") && !this.password.equals("") && (!pathFile.equals(""))) {

            try {
                Office365 office365 = new Office365(serveurSTMP, username, password);
                Exchange mail = office365.connection();
                if (mail == null) {
                    return false;
                }
//                SendMail mail = new SendMail(this.serveurSTMP, username, password);

                office365.SendMailExchangeWithAttachment(emailPers, _subject, _body, pathFile);

            } catch (ExchangeServiceException | IOException ex) {
                LOGGER.log(Level.WARNING, "Erro ao enviar mensagem: " + ex.getMessage(), ex);
                return false;
            }

            System.out.println("envoyÃ©");
            return true;
        } else {
            return false;
        }
    }

    @Asynchronous
    public Boolean sendExchangeMail(String emailPers) {

        if (!this.username.equals("") && !this.password.equals("") && !emailPers.equals("")) {

            try {
                SendMail mail = new SendMail(this.serveurSTMP, username, password);

                mail.sendMailExchange(emailPers, _subject, _body);

            } catch (ExchangeServiceException ex) {
                LOGGER.log(Level.WARNING, "Erro ao enviar mensagem: " + ex.getMessage(), ex);
                return false;
            }

            System.out.println("envoyÃ©");
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean send(String from, String to) throws Exception {

        Properties props = _setProperties();
        System.out.println("Properties charger");
        System.out.println("username ");
        if (!this.username.equals("") && !this.password.equals("") && !to.equals("") && !from.equals("")) {
//            Session session = Session.getInstance(props, null);
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            session.setDebug(true);

            System.out.println("Session mail connecter");
            Transport transport = null;
//            if (username.contains("@yahoo.fr")) {
//                transport = session.getTransport("smtp");
//            } else if (username.contains("@gmail.com")) {
//                transport = session.getTransport("smtps");
//            }

            System.out.println("Transport ok");

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject(_subject);
            msg.setSentDate(new Date());
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(_body);
            _multipart.addBodyPart(messageBodyPart);
            msg.setContent(_multipart);

            try {
                if (username.contains("@yahoo.fr")) {
                    transport = session.getTransport("smtps");
//                    transport.connect();
                    transport.connect(serveurSTMP, Integer.valueOf(portSTMP), username, password);
                    transport.sendMessage(msg, msg.getAllRecipients());
//                    Transport.send(msg);
                } else if (username.contains("@gmail.com")) {
                    transport = session.getTransport("smtps");
                    transport.connect(serveurSTMP, Integer.valueOf(portSTMP), username, password);
                    transport.sendMessage(msg, msg.getAllRecipients());
                    transport.close();
                } else if (username.contains("@") == Boolean.FALSE) {
                    LOGGER.log(Level.WARNING, "USER NAME NOTE VALIDE: " + username);

                    System.out.println("USER NAME NOTE VALIDE: " + username);
                    return false;
                }

            } catch (MessagingException e) {
                LOGGER.log(Level.WARNING, "Erreur lors de l envoit : " + e.getMessage(), e);
                logger.error("Erreur lors de l envoit : " + e.getMessage() + " cause de l envoit " + e.getCause().getMessage());

                System.out.println("ERREUR ENVOIT : " + e.getCause() + "- " + e.getMessage());
                return false;
            } catch (Exception ex) {
                LOGGER.log(Level.WARNING, "Erreur lors de l envoit : " + ex.getMessage(), ex);
                logger.error("Erreur lors de l envoit : " + ex.getMessage() + " cause de l envoit " + ex.getCause().getMessage());

                System.out.println("ERREUR ENVOIT : " + ex.getCause() + "- " + ex.getMessage());
                return false;
            }

            System.out.println("envoyÃ©");
            return true;
        } else {
            return false;
        }

    }

    private Properties _setProperties() {
        Properties props = new Properties();
//        Properties props = System.getProperties();
        props.put("mail.smtp.host", this.serveurSTMP);
        props.put("mail.smtp.password", password);
////        if (_debuggable) {

////        }
//        if (_auth) {
//            props.put("mail.smtp.auth", "true");
//        }
//        props.put("mail.smtp.port", this.portSTMP);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");

        props.put("mail.smtp.starttls.enable", "true");

        if (username.contains("@gmail") == Boolean.TRUE) {
            props.put("mail.smtp.password", password);
            props.put("mail.smtp.user", username);
            props.put("mail.smtp.socketFactory.port", this.portSTMP);
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.debug", "true");

//            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//
        } else if (username.contains("@yahoo.fr") == Boolean.TRUE) {

//            props.put("mail.smtp.port", this.portSTMP); // 587 is the port number of yahoo mail
            props.put("mail.smtp.socketFactory.port", this.portSTMP);

            props.put("mail.debug", "true");
//            props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
//            props.setProperty(" mail.smtp.ssl.trust", this.serveurSTMP);
//            props.put("mail.smtp.socketFactory.fallback", "false");
//            props.put("mail.smtp.socketFactory.fallback", "true");

        }

//        props.put("mail.smtp.ssl.enable", "true");
//         props.put("mail.debug", "true");
        return props;
    }

    public void addAttachment(String filename) throws Exception {
        BodyPart messageBodyPart = new MimeBodyPart();
        FileDataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);

        _multipart.addBodyPart(messageBodyPart);
    }

    @Asynchronous
    public void envoiEmail(String to, String subject, String body) throws MessagingException, NamingException, UnsupportedEncodingException {
        Properties props = new Properties();
        InitialContext ictx = new InitialContext(props);
        Session mailSession = (Session) ictx.lookup("mail/gmailjndi");

        MimeMessage message = new MimeMessage(mailSession);
        String adresses = "";
//        for (Personne personne : emailPers) {
//            if (personne.getAdresse().getEmail() != null && !personne.getAdresse().getEmail().trim().equals("")) {
//                adresses += personne.getAdresse().getEmail() + ",";
//            }
        message.setSubject(subject);
        message.setRecipients(RecipientType.TO, InternetAddress.parse(to, false));

        message.setSentDate(new Date());
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(body);
        _multipart.addBodyPart(messageBodyPart);
        message.setContent(_multipart);
        message.setText(body);
        message.saveChanges();

        Transport transport = mailSession.getTransport("smtp");
        try {
            transport.connect();
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("envoyÃ©");
        } finally {
            transport.close();
        }
//        }
    }

    public void subjectMessade(String subject) {
        this._subject = subject;
    }

    public void bodyMessage(String body) {
        this._body = body;
    }
}
