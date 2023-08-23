/*
package mdb;



import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import modele.Adresse;
import modele.Etudiant;
import modele.Inscription;


@MessageDriven(mappedName = "topic/MailConfirmationInscription", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class SmsConfirmationMdbBean implements MessageListener {
    static final Logger log=Logger.getLogger(SmsConfirmationMdbBean.class.getName());
     
    
    private Passerelle passerelle;
    public SmsConfirmationMdbBean() {
         log.log(Level.INFO,"Initialisation de l’envoi du sms depuis SmsConfirmationMdbBean"); 
    passerelle=new Passerelle();
    }
    
    @Override
    public void onMessage(Message message) {
      
         // Pour la classe de test MailConfirmationProducteur
      if (message instanceof TextMessage) {
           TextMessage mail = (TextMessage) message;
           // L’envoi d’un mail de confirmation au client est ici 
            //simulé par l’affichage d’un message au niveau des logs.
           try {
            String leMail = mail.getText();
            log.log(Level.INFO, " Envoi du sms : {0}", leMail);
            log.info(" --------------------------------------------------- ");
            log.info(" --------------------------------------------------- ");
            log.info(" Mail envoyé.");
           }
    
           catch (JMSException e) {
            e.printStackTrace();
           } 
      } else if  (message instanceof ObjectMessage) {
            try {
                ObjectMessage lemessage = (ObjectMessage) message;
                
                     Inscription inscrip = (Inscription)lemessage.getObject();
                      Etudiant etud = inscrip.getEtudiant();
                      Adresse adresse = etud.getAdresse();
                      String contenuSMS = "Bonjour " + etud.getNom() + " " + etud.getPrenom() + "." 
                      + "Votre  inscription a ete pris en compte." 
                      + "Votre Matricule :" +etud.getMatricule()+"."
                      + "Votre classe: "+inscrip.getClasse().getCode();
                
                      
                      contenuSMS += "merci de votre confiance";
                      //contenuSMS="Votre  inscription a ete pris en compte." ;
                      if(contenuSMS.length()>159){
                          contenuSMS=contenuSMS.substring(0, 159); 
                      }
                     
                      
                      log.info(" Envoi du sms au client: " );
                      log.info(" --------------------------------------------------- ");
                      
                      sendSms(adresse.getTel(), contenuSMS);
                      log.info(" --------------------------------------------------- ");
                      log.log(Level.INFO, " sms envoy\u00e9 au.{0}", adresse.getTel());
            } catch (JMSException ex) {
                Logger.getLogger(SmsConfirmationMdbBean.class.getName()).log(Level.SEVERE, null, ex);
            }
               }
                                    
      }
    protected void sendSms(String number, String  sms){
            passerelle.SendSms(Passerelle.LOGIN, Passerelle.PWD,Passerelle.SENDERID, number, sms);
        }
         
     @PreDestroy  
     public void remove() {
         log.info("Suppression de MailConfirmationMdbBean.");
     }

}*/
