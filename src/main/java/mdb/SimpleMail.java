///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package mdb;
//
///**
// *
// * @author JIATOU
// */
//import java.security.Security;
//import java.util.Date;
//import java.util.Properties;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.activation.DataHandler;
//import javax.activation.FileDataSource;
//import javax.mail.Authenticator;
//import javax.mail.BodyPart;
//
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//
//public class SimpleMail {
//    
//    String serveurSTMP;
//    int portSTMP;
//    String username = "";
//    String password = "";
//    String path = "";
//    private boolean _auth;
//    private String _subject = "";
//    private String _body = "";
//    private String mailhost = "smtp.gmail.com";
//    private Multipart _multipart;
//    private static final Logger LOGGER = Logger.getAnonymousLogger();
//    
//    public SimpleMail(String serveurSTMP, int portSTMP, String username, String password) {
//        this.serveurSTMP = serveurSTMP;
//        this.portSTMP = portSTMP;
//        this.username = username;
//        this.password = password;
//        
//        _multipart = new MimeMultipart();
//    }
//    
//    public SimpleMail() {
//        _multipart = new MimeMultipart();
//    }
//    
//    public synchronized boolean sendMail(String subject, String body, String sender, String recipients)
//            throws Exception {
//        
//        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//        
//        Properties props = new Properties();
////        props.setProperty("mail.transport.protocol", "smtp");
//        props.setProperty("mail.host", serveurSTMP);
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", portSTMP);
//        props.put("mail.smtp.socketFactory.port", portSTMP);
//        props.put("mail.smtp.socketFactory.class",
//                "javax.net.ssl.SSLSocketFactory");
////        props.put("mail.smtp.socketFactory.fallback", "false");
////        props.put("mail.smtp.ssl.enable", "true");
////        props.setProperty("mail.smtp.quitwait", "false");
//        
//        Authenticator auth = new Authenticator() {
//			//override the getPasswordAuthentication method
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(username, password);
//			}
//		};
//        Session session = Session.getInstance(props,auth);
//          
//        session.setDebug(true);
//        MimeMessage message = new MimeMessage(session);
//        message.setSender(new InternetAddress(sender));
//        message.setSubject(subject);
//        message.setSentDate(new Date());
//        BodyPart messageBodyPart = new MimeBodyPart();
//        messageBodyPart.setText(body);
//        _multipart.addBodyPart(messageBodyPart);
//        message.setContent(_multipart);
////        message.setContent(body, "text/plain");
//        if (recipients.indexOf(',') > 0) {
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
//        } else {
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
//        }
//        try {
////           Transport transport=session.getTransport("smtps");
////            transport.connect(serveurSTMP,portSTMP, username, password);
////            transport.sendMessage(message, message.getAllRecipients());
//            Transport.send(message);
//        } catch (MessagingException e) {
//            LOGGER.log(Level.WARNING, "Erreur lors de l envoit : " + e.getMessage(), e);
//            
//            System.out.println("ERREUR ENVOIT : " + e.getCause() + "- " + e.getMessage());
//            return false;
//        } catch (Exception ex) {
//            LOGGER.log(Level.WARNING, "Erreur lors de l envoit : " + ex.getMessage(), ex);
//            
//            System.out.println("ERREUR ENVOIT : " + ex.getCause() + "- " + ex.getMessage());
//            return false;
//        }
//        
//        System.out.println("envoyé");
//        return true;
//        
//    }
//    
//    public void addAttachment(String filename) throws Exception {
//        BodyPart messageBodyPart = new MimeBodyPart();
//        FileDataSource source = new FileDataSource(filename);
//        messageBodyPart.setDataHandler(new DataHandler(source));
//        messageBodyPart.setFileName(filename);
//        
//        _multipart.addBodyPart(messageBodyPart);
//    }
//    
//}
