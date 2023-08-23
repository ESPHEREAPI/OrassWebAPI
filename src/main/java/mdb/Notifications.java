//package mdb;
//
//import exception.GlobalException;
//import java.io.File;
//import java.io.UnsupportedEncodingException;
//import java.util.Collection;
//import java.util.Properties;
//import java.util.concurrent.Future;
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//
//import javax.annotation.Resource;
//import javax.ejb.AsyncResult;
//import javax.ejb.Asynchronous;
//import javax.ejb.EJB;
//import javax.ejb.LocalBean;
//import javax.ejb.SessionContext;
//import javax.ejb.Stateless;
//import javax.mail.BodyPart;
//import javax.mail.Message.RecipientType;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//
//import modele.Personne;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import parametrage.IParamModule;
//import utils.GlobalFonctions;
//
///**
// *
// * @author fabrice
// */
//@Stateless
//@LocalBean
//public class Notifications {
//
//    @EJB
//    IParamModule paramModule;
//    @Resource
//    SessionContext sc;
////    @Resource(name = "mail/gmailjndi")
////    private Session mailSession;
//    static final Logger logger = LoggerFactory.getLogger(Notifications.class);
//    static final Logger log = LoggerFactory.getLogger(Notifications.class.getName());
//    String serveurSTMP;
//    int portSTMP;
//    String username = "";
//    String password = "";
//    private Multipart _multipart;
//    private boolean _auth;
//    private String _subject = "";
//    private String _body = "";
//
//    @Asynchronous
//    public Future<Integer> envoiSms(Personne pers, String message) {
//
//        Integer etat = 0;
//        try {
//
//            if (sc.wasCancelCalled()) {
//                etat = 1;
//                return new AsyncResult<>(etat);
//            }
//            if (pers.getAdresse() != null && pers.getAdresse().getTel() != null && !pers.getAdresse().getTel().trim().equals("")) {
//                new Passerelle().SendSms2(paramModule.getParamModule().getLogin(), paramModule.getParamModule().getPwd(),
//                        paramModule.getParamModule().getCompte(), paramModule.getParamModule().getSenderId(), pers.getAdresse().getIndicatifPays() + pers.getAdresse().getTel(), message);
//                // apres traitement
//                etat = 2;
//            }
//            return new AsyncResult<>(etat);
//        } catch (Exception e) {
//            // en cas d’erreur
//            etat = 3;
//            return new AsyncResult<>(etat);
//
//        }
//    }
//
//    @Asynchronous
//    public Future<Integer> envoiSms(Collection<Personne> pers, String message) {
//        Integer etat = 0;
//        try {
//
//            if (sc.wasCancelCalled()) {
//                etat = 1;
//                return new AsyncResult<>(etat);
//            }
//            for (Personne personne : pers) {
//
//                if (personne.getAdresse() != null && personne.getAdresse().getTel() != null && !personne.getAdresse().getTel().trim().equals("")) {
//                    //mettre la pause
//                    Thread.sleep(1000);
//
//                    new Passerelle().SendSms(paramModule.getParamModule().getLogin(), paramModule.getParamModule().getPwd(),
//                            paramModule.getParamModule().getSenderId(), personne.getAdresse().getTel(), message);
//                }
//            }
//            // apres traitement
//            etat=2;
//            return new AsyncResult<>(etat);
//        } catch (IllegalStateException | InterruptedException e) {
//            // en cas d’erreur
//            etat = 3;
//            return new AsyncResult<>(etat);
//
//        }
//    }
//
//    @Asynchronous
//    public Future<Integer> soldeSms() {
//        Integer etat = 0;
//        try {
//
//            if (sc.wasCancelCalled()) {
//                etat = 1;
//                return new AsyncResult<>(etat);
//            }
//
//            // apres traitement
//            etat = new Passerelle().soldeCompte(paramModule.getParamModule().getLogin(), paramModule.getParamModule().getPwd(), paramModule.getParamModule().getSenderId());
//            return new AsyncResult<>(etat);
//        } catch (IllegalStateException e) {
//            // en cas d’erreur
//            etat = -2;
//            return new AsyncResult<>(etat);
//
//        }
//    }
//
//    @Asynchronous
//    public void envoiEmail(Personne emailPers, String subject, String body) throws MessagingException, NamingException, UnsupportedEncodingException {
//        Properties props = new Properties();
//        InitialContext ictx = new InitialContext(props);
//        Session mailSession = (Session) ictx.lookup("mail/gmailjndi");
//
//        MimeMessage message = new MimeMessage(mailSession);
//
//        message.setSubject(subject);
//        message.setRecipients(RecipientType.TO, InternetAddress.parse(emailPers.getAdresse().getEmail(), false));
//        message.setText(body);
//        message.saveChanges();
//
//        Transport transport = mailSession.getTransport("smtp");
//        try {
//            transport.connect();
//            transport.sendMessage(message, message.getAllRecipients());
//            logger.info("Email envoyé");
//        } finally {
//            transport.close();
//        }
//    }
//
//    @Asynchronous
//    public void envoiEmail(Collection<Personne> emailPers, String subject, String body) throws MessagingException, NamingException, UnsupportedEncodingException {
//        Properties props = new Properties();
//        InitialContext ictx = new InitialContext(props);
//        Session mailSession = (Session) ictx.lookup("mail/gmailjndi");
//
//        MimeMessage message = new MimeMessage(mailSession);
//        String adresses = "";
//        for (Personne personne : emailPers) {
//            if (personne.getAdresse().getEmail() != null && !personne.getAdresse().getEmail().trim().equals("")) {
//                adresses += personne.getAdresse().getEmail() + ",";
//            }
//            message.setSubject(subject);
//            message.setRecipients(RecipientType.TO, InternetAddress.parse(adresses, false));
//            message.setText(body);
//            message.saveChanges();
//
//            Transport transport = mailSession.getTransport("smtp");
//            try {
//                transport.connect();
//                transport.sendMessage(message, message.getAllRecipients());
//                logger.info("Email envoyé");
//            } finally {
//                transport.close();
//            }
//        }
//    }
//    private Properties _setProperties() {
//        Properties props = new Properties();
//        props.put("mail.smtp.host", this.serveurSTMP);
////        if (_debuggable) {
////            props.put("mail.debug", "true");
////        }
//        if (_auth) {
//            props.put("mail.smtp.auth", "true");
//        }
//        props.put("mail.smtp.port", this.portSTMP);
//        props.put("mail.smtp.socketFactory.port", portSTMP);
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.socketFactory.fallback", "false");
//        props.put("mail.smtp.ssl.enable", "true");
////        props.put("mail.smtp.starttls.enable","true");
//
//        return props;
//    }
//
//    public void envoyer_email(String serveurSTMP, int portSTMP, String from, String to, String sujet,
//            String message, String username, String password) {
//        try {
//            String PATH_TO_FILE = GlobalFonctions.dossierBulletin;
//            File file = new File(PATH_TO_FILE + "\\BulletinPaie.pdf");
//            if (file.exists() == false) {
//                System.err.println("Pieces jointes:" + file.getPath());
//                log.info("-----------------------------");
//                log.info("Pieces jointes non existance");
//                System.err.println("Pieces jointes non existance");
//                log.info("-----------------------------");
//                System.err.println("Erreur d envoit piece jointe non creeé");
//                throw new GlobalException("Erreur d envoit piece jointe non creeé");
//            }
//            System.out.println("traitement de message");
//
//            java.util.Properties props = new java.util.Properties();
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.starttls.enable", "true");
//            Session session = Session.getDefaultInstance(props, null);
//
//            //traitement de message
//            MimeMessage msg = new MimeMessage(session);
//            msg.setFrom(new InternetAddress(from));
//            msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
//            msg.setSubject(sujet);
//            msg.setText(message);
//            //ajouter une piece jointe
//            FileDataSource datasource1 = new FileDataSource(file);
//            DataHandler handler1 = new DataHandler(datasource1);
//            MimeBodyPart autruche = new MimeBodyPart();
//            MimeMultipart mimeMultipart = new MimeMultipart();
//            mimeMultipart.addBodyPart(autruche);
//            msg.setContent(mimeMultipart);
//            Transport transport = session.getTransport("smtp");
//            transport.connect(serveurSTMP, portSTMP, username, password);
//            transport.send(msg);
//            System.out.println("traitement de message ok");
//
//        } catch (Exception e) {
//            System.out.println("traitement de message Erreur");
//
//        }
//
//    }
//
//    public void email(String serveurSTMP, int portSTMP, String from, String to, String sujet,
//            String message, String username, String password) {
//        try {
//            String PATH_TO_FILE = GlobalFonctions.dossierBulletin;
//            File file = new File(PATH_TO_FILE + "\\BulletinPaie.pdf");
//            if (file.exists() == false) {
//                System.err.println("Pieces jointes:" + file.getPath());
//                log.info("-----------------------------");
//                log.info("Pieces jointes non existance");
//                System.err.println("Pieces jointes non existance");
//                log.info("-----------------------------");
//                System.err.println("Erreur d envoit piece jointe non creeé");
//                throw new GlobalException("Erreur d envoit piece jointe non creeé");
//            }
//            System.out.println("traitement de message");
//
//            Properties props = System.getProperties();
//            props.put("mail.smtp.host", serveurSTMP);
////            props.put("mail.smtp.auth", "true");
////            props.put("mail.smtp.starttls.enable", "true");
//            Session session = Session.getDefaultInstance(props, null);
//
//            //traitement de message
//            MimeMessage msg = new MimeMessage(session);
//            msg.setFrom(new InternetAddress(from));
//            msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
//            msg.setSubject(sujet);
//            msg.setText(message);
//            //ajouter une piece jointe
////            FileDataSource datasource1 = new FileDataSource(file);
////            DataHandler handler1 = new DataHandler(datasource1);
////            MimeBodyPart autruche = new MimeBodyPart();
////            MimeMultipart mimeMultipart = new MimeMultipart();
////            mimeMultipart.addBodyPart(autruche);
////            msg.setContent(mimeMultipart);
//
//            Multipart multipart = new MimeMultipart();
//            BodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setText("le bulletin de paie");
//            multipart.addBodyPart(messageBodyPart);
//            messageBodyPart = new MimeBodyPart();
//            DataSource source = new FileDataSource(file);
//            messageBodyPart.setDataHandler(new DataHandler(source));
//            messageBodyPart.setFileName("D:\\dossier\\pieceJointe.pdf");
//            multipart.addBodyPart(messageBodyPart);
//            msg.setContent(multipart);
////            Transport transport = session.getTransport("smtp");
////            transport.connect(serveurSTMP, portSTMP, username, password);
//            Transport.send(msg);
//            System.out.println("traitement de message ok");
//
//        } catch (GlobalException | MessagingException e) {
//            System.out.println("traitement de message Erreur");
//
//        }
//
//    }
//
//}
