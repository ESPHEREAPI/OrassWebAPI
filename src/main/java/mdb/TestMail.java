///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package mdb;
//
//import exception.GlobalException;
//import java.io.File;
//import javax.activation.DataHandler;
//import javax.activation.FileDataSource;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import static mdb.Notifications.log;
//import utils.GlobalFonctions;
//
///**
// *
// * @author frank
// */
//public class TestMail {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here
////   envoyer_email("mail.yahoo.fr", 25, "frankjiatou@yahoo.fr", "frankjiatou@yahoo.fr", "Confirmation de paiemeent", "bulletin de paie comme piece jointe", "frankjiatou@yahoo.fr", "noubi14101987");
//        
//    }
//        
//// public  static void envoyer_email(String serveurSTMP, int portSTMP, String from, String to, String sujet,
////            String message, String username, String password) {
////        try {
////            String PATH_TO_FILE = GlobalFonctions.dossierBulletin;
////            File file = new File(PATH_TO_FILE + "\\BulletinPaie.pdf");
////            if (file.exists() == false) {
////                log.info("-----------------------------");
////                log.info("Pieces jointes non existance");
////                log.info("-----------------------------");
////                throw new GlobalException("Erreur d envoit piece jointe non creeé");
////            }
////
////            java.util.Properties props = new java.util.Properties();
////            props.put("mail.smtp.auth", "true");
////            props.put("mail.smtp.starttls.enable", "true");
////            Session session = Session.getDefaultInstance(props, null);
////
////            //traitement de message
////            MimeMessage msg = new MimeMessage(session);
////            msg.setFrom(new InternetAddress(from));
////            msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
////            msg.setSubject(sujet);
////            msg.setText(message);
////            //ajouter une piece jointe
////            FileDataSource datasource1 = new FileDataSource(file);
////            DataHandler handler1 = new DataHandler(datasource1);
////            MimeBodyPart autruche = new MimeBodyPart();
////            MimeMultipart mimeMultipart = new MimeMultipart();
////            mimeMultipart.addBodyPart(autruche);
////            msg.setContent(mimeMultipart);
////            Transport transport = session.getTransport("smtp");
////            transport.connect(serveurSTMP, portSTMP, username, password);
////
////        } catch (Exception e) {
////
////        }
////
////    }
//
//    
//     
//
//    
//}
