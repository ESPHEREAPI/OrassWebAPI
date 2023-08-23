//package mdb;
//
//import exception.GlobalException;
//import java.io.File;
//import java.io.UnsupportedEncodingException;
//import java.util.Properties;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.activation.DataHandler;
//import javax.activation.FileDataSource;
//
//import javax.annotation.PreDestroy;
//import javax.ejb.ActivationConfigProperty;
//import javax.ejb.MessageDriven;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.jms.ObjectMessage;
//import javax.jms.TextMessage;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import modele.Adresse;
//import modele.Personnel;
//import modele.Salarier;
//
//import utils.GlobalFonctions;
//
//@MessageDriven(mappedName = "topic/MailConfirmationInscription", activationConfig = {
//    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
//    ,
//    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
//})
//public class MailConfirmationMdbBean implements MessageListener {
//
//    static final Logger log = Logger.getLogger(MailConfirmationMdbBean.class.getName());
//
//    public MailConfirmationMdbBean() {
//        log.log(Level.INFO, "Initialisation de l’envoi du mail depuis MailConfirmationMdbBean");
//    }
//
//    @Override
//    public void onMessage(Message message) {
////          Pour la classe de test MailConfirmationProducteur
//        if (message instanceof TextMessage) {
//            TextMessage mail = (TextMessage) message;
////            L’envoi d’un mail de confirmation au client est ici 
////            simulé par l’affichage d’un message au niveau des logs.
//            try {
//                String leMail = mail.getText();
//                log.log(Level.INFO, " Envoi du mail : {0}", leMail);
//                log.info(" --------------------------------------------------- ");
//                log.info(" --------------------------------------------------- ");
//                log.info(" Mail envoyé.");
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
//        } else if (message instanceof ObjectMessage) {
//            ObjectMessage lemessage = (ObjectMessage) message;
//
//            try {
//                Salarier salarier = (Salarier) lemessage.getObject();
//                Personnel pers = salarier.getPersonnel();
////                Adresse adresse = etud.getAdresse();
//                String contenuMail = "Bonjour " + pers.getNom() + " " + pers.getPrenom() + ". \n"
//                        + "Votre  inscription a ete pris de compte" + ". \n"
//                        + "Votre Matricule :" + pers.getMatricule()
//                        + " \n" + "Votre Poste: " + salarier.getSalaire().getCoeficiant().getPoste().getPoste().toString();
//
//                String ladresse = " \n" + "Votre adresse est : " + " \n"
//                        + pers.getAdresse().getVille() + " rue " + pers.getAdresse().getQuartier();
//
//                contenuMail += ladresse;
//
//                contenuMail += "\n merci de votre confiance";
//
//                log.info(" Envoi du mail au Salarier: ");
//                log.info(" --------------------------------------------------- ");
//
//                sendMsg(pers.getAdresse().getEmail(), "Confirmation de l envoir de votre bulletin de paie.", contenuMail);
//                log.info(" --------------------------------------------------- ");
//                log.info(" Mail envoyé au Salarier.");
//            } catch (MessagingException e) {
//                log.info(" --------------------------------------------------- ");
//                log.info(" Mail Non envoyé au Salarier.");
//                e.printStackTrace();
//            } catch (NamingException e) {
//                log.info(" --------------------------------------------------- ");
//                log.info(" Mail Non envoyé au Salarier.");
//
//                e.printStackTrace();
//            } catch (JMSException e) {
//                log.info(" --------------------------------------------------- ");
//                log.info(" Mail Non envoyé au Salarier.");
//                e.printStackTrace();
//            } catch (UnsupportedEncodingException e) {
//                log.info(" --------------------------------------------------- ");
//                log.info(" Mail Non envoyé au Salarier.");
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//    public static void envoyer_email(String serveurSTMP, int portSTMP, String from, String to, String sujet,
//            String message, String username, String password) {
//        try {
//            String PATH_TO_FILE = GlobalFonctions.dossierBulletin;
//            File file = new File(PATH_TO_FILE + "\\BulletinPaie.pdf");
//            if (file.exists() == false) {
//                log.info("-----------------------------");
//                log.info("Pieces jointes non existance");
//                log.info("-----------------------------");
//                throw new GlobalException("Erreur d envoit piece jointe non creeé");
//            }
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
//
//        } catch (Exception e) {
//
//        }
//
//    }
//
//    protected void sendMsg(String email, String subject, String body) throws MessagingException, NamingException, UnsupportedEncodingException {
//
//        Properties props = new Properties();
//        InitialContext ictx = new InitialContext(props);
//        javax.mail.Session mailSession = (javax.mail.Session) ictx.lookup("mail/gmailjndi");
//
//        MimeMessage message = new MimeMessage(mailSession);
//
//        message.setSubject(subject);
//        message.setRecipients(javax.mail.Message.RecipientType.TO, javax.mail.internet.InternetAddress.parse(email, false));
//        message.setText(body);
//        message.saveChanges();
//
//        Transport transport = mailSession.getTransport("smtp");
//        try {
//            transport.connect();
//            transport.sendMessage(message, message.getAllRecipients());
//            log.info("Message envoyé");
//        } finally {
//            transport.close();
//        }
//    }
//
//    @PreDestroy
//    public void remove() {
//        log.info("Suppression de MailConfirmationMdbBean.");
//    }
//
//}
