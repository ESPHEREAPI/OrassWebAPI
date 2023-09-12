/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.administration;

import Entreprise.IEntreprise;
import Licence.KeyGenCode;
import static com.sun.org.apache.xerces.internal.impl.io.ASCIIReader.DEFAULT_BUFFER_SIZE;

//import static com.sun.org.apache.xerces.internal.impl.XMLEntityManager.DEFAULT_BUFFER_SIZE;
//import static com.sun.org.apache.xerces.internal.impl.XMLEntityManager.DEFAULT_BUFFER_SIZE;
import controllers.ConnectionController;
import controllers.CurrentInstance;
import dao.IndicatifPaysDao;

import dao.OrclassEntreprisesModulesDao;
import dao.OrclassModulesDao;
import dao.OrclassUtilisateursDao;
import dao.OrclssMailInscriptionDao;
import dao.PaysDao;
import dao.SocieteDao;
import dao.VilleDao;
import enums.ServiceDepartement;

import enums.TypeUtilisateur;
import exception.Success;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import mdb.Mail;
import modele.Adresse;
import modele.IndicatifPays;
import modele.OrclassEntreprises;
import modele.OrclassEntreprisesModules;
import modele.OrclassEntreprisesModulesPK;
import modele.OrclassModules;
import modele.OrclassUtilisateurs;
import modele.OrclssMailInscription;
import modele.Societe;
import modele.Ville;
import org.primefaces.PrimeFaces;
//import static org.eclipse.jdt.internal.core.util.CharArrayBuffer.DEFAULT_BUFFER_SIZE;
import org.primefaces.component.wizard.Wizard;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Connection;
import utils.Crypto;
import utils.EsphereMail;
import utils.GlobalFonctions;
import utils.InternetAvailabilitiChecker;
import utils.LocaleHelper;
import utils.RecupBundle;

/**
 *
 * @author hp
 */
@Named(value = "configurationController")
@ViewScoped
public class ConfigurationController implements Serializable {

    /**
     * Creates a new instance of EntrepriseController
     */
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationController.class);
    @EJB
    private SocieteDao societeDao;
    private Societe societe;
    @EJB
    private IEntreprise serviceEntreprise;
    @EJB
    private OrclassModulesDao orclassModulesDao;
    OrclassModules modules;
    @EJB
    OrclassEntreprisesModulesDao orclassEntreprisesModulesDao;
    OrclassEntreprisesModules orclassEntreprisesModules;
    @EJB
    PaysDao paysDao;
    @EJB
    VilleDao villeDao;
    @Inject
    CurrentInstance currentInstance;
    private String absolutePathImages;
    @EJB
    OrclassUtilisateursDao utilisateursDao;
    @EJB
    OrclssMailInscriptionDao mailInscriptionDao;
    @Inject
    ConnectionController connectionController;
     @EJB
    IndicatifPaysDao indicatifPaysDao;
    OrclassUtilisateurs utilisateur;
    private String currentFolder = "/photos";
    private String imagePath;
    String summary = "";
    String msgdetail = "";
    private byte[] file1;
    private Wizard wizard;
    private StreamedContent logoimages;
    private Collection<OrclassModules> listModules = new ArrayList<>();
    private Collection<Ville> listVille = new ArrayList<>();
    private Collection<OrclassModules> selectedModules = new ArrayList<>();
    private Collection<Societe> listeSociete = new ArrayList<>();
    Date debut, fin;
    private String newPassWord;
    private String oldPassWord;
    Boolean affiche_home_acceuil_admin_system, affiche_home_acceuil_user = Boolean.FALSE;
    String cle, cle_securite;
    String passWord;
    String login;
    Connection con;
    OrclssMailInscription mailInscription;
    ServiceDepartement serviceDepartement;
      private List<IndicatifPays> listeIndicatifPays = new ArrayList<>();

    public ConfigurationController() {
        societe = new Societe();
        modules = new OrclassModules();
        utilisateur = new OrclassUtilisateurs();
        con = new Connection();

    }

    public void init() {
        societe = new Societe();
        selectedModules = new ArrayList<>();
        orclassEntreprisesModules = new OrclassEntreprisesModules();
        utilisateur = new OrclassUtilisateurs();

    }

    public void reset() {
        this.init();
        currentInstance.getAjaxRequestBuilder().reset();
//        RequestContext.getCurrentInstance().reset(":form1");
    }

    //permet d'eviter l'expiration illegal d'une session
    @PostConstruct
    void initialiseSession() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);

        societe = (Societe) (societeDao.findAll().isEmpty() ? new Societe():societeDao.findAll().toArray()[0]);
        selectedModules = new ArrayList<>();
        orclassEntreprisesModules = new OrclassEntreprisesModules();
        utilisateur = new OrclassUtilisateurs();
//        listModules = orclassModulesDao.findAll();
        listeSociete = societeDao.findAll();
        listVille = villeDao.findAll();
        listeIndicatifPays = (List<IndicatifPays>) indicatifPaysDao.findAll();
//        connectionController.optionChoix(0);

    }

    public List<Short> completeTextByCode(String query) {
        List<Short> results = new ArrayList<>();
        if (query != null) {
            results = societeDao.getSocieteCodeWithFilters(query);
        }

        return results;
    }

    public List<String> completeTextByRaison(String query) {
        List<String> results = new ArrayList<>();
        if (query != null && "" != query) {
            results = societeDao.getSocieteRaisonWithFilters(query);
        }

        return results;
    }

    public void onItemSelect(SelectEvent<Short> event) {
        Societe s = null;
        s = societeDao.findEntityHavingValue("codesoci", event.getObject());
        if (s != null && s.getCodesoci() != null) {
            this.setSociete(s);
            if (s.getLogosoci()!=null) {
                this.chargeLogo();
            }
            PrimeFaces.current().ajax().update(":form1");
        }
    }

    public void onItemSelect2(SelectEvent<String> event) {
        Societe s = null;
        s = societeDao.findEntityHavingValue("raissoci", event.getObject());
        if (s != null && s.getCodesoci() != null) {
            this.setSociete(s);
            if (s.getLogosoci()!=null) {
                this.chargeLogo();
            }
            PrimeFaces.current().ajax().update(":form1");
        }
    }

    public void chargerModuleNotHaveEntite() {
        if (serviceDepartement != null ) {
            listModules = orclassModulesDao.listeModulesNotHaveEntreprise(serviceDepartement);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {

        file1 = event.getFile().getContent();
        String contentType = event.getFile().getContentType();
        societe.setLogosoci(file1);
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        imagePath = extContext.getRealPath(currentFolder);
        //imagePath+=File.separator;
//        String fileName = employeur.getSociete();
        String fileName = event.getFile().getFileName();
        societe.setChemin_logo(uploadFile(event, imagePath, fileName));
        FacesMessage msg = new FacesMessage("Success", event.getFile().getFileName() + " was Upload! .");
        FacesContext.getCurrentInstance().addMessage("growl", msg);
        //Refresh image
//        logoimages = new DefaultStreamedContent(new ByteArrayInputStream(file1), contentType);
        logoimages = new DefaultStreamedContent();
//logoimages.set

    }

     public void chargeLogo() {
        if (societe != null && societe.getCodesoci() != null) {

          
            if (societe != null && societe.getLogosoci()!= null) {
                FacesContext ctx = FacesContext.getCurrentInstance();
                ExternalContext extContext = ctx.getExternalContext();
                if (societe.getChemin_logo()==null) {
                  imagePath=  extContext.getRealPath(currentFolder)+"/logo.png";
            
                }else{
                      imagePath = extContext.getRealPath("") + societe.getChemin_logo();
                }
              
                File dossiers = new File(imagePath);
                if (dossiers.exists() == false) {
                    String libelle = GlobalFonctions.createImageByLibellePhotos(imagePath, societe.getLogosoci());
                 societe.setChemin_logo(libelle);
                }
               
                absolutePathImages = societe.getChemin_logo();
//            absolutePathImages=photo.getImage().toString();
            } 

        }

    }
    public String uploadFile(FileUploadEvent event, String folderDestination, String nameFile) {
        InputStream inputStream = null;
        OutputStream out = null;

        FacesContext ctx = FacesContext.getCurrentInstance();

        File dossiers = new File(folderDestination);

        //cree le dossier s'il n'existe pas
        if (!dossiers.exists()) {
            dossiers.mkdir();
        }

        try {

            String contentType = event.getFile().getContentType();
            String ext = contentType.split("/")[1];

            if (ext.equals("jpeg")) {
                ext = "jpg";
            }
            //je concatene le fichier avec l'extension
            nameFile += "." + ext;

            inputStream = event.getFile().getInputStream();

            out = new FileOutputStream(new File(dossiers, nameFile));
            int read = 0;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);

            }

        } catch (IOException e) {
            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, nameFile, "FILE_WRITTING_FAILED", null);
        } finally {
            try {
                inputStream.close();
                out.flush();
                out.close();
            } catch (IOException ex) {
                logger.error("Erreur Survenu", ex);
            }

        }

        absolutePathImages = currentFolder + "/" + nameFile;
        return absolutePathImages;
    }

    public String save() {
        String success = null;
        FacesContext ctx = FacesContext.getCurrentInstance();

        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        //Locale myLoc =new Locale("fr");
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "entite.entreprise", null, myLoc)};
        String[] detail = {entete[0], "Parametres"};
        Boolean resultat=Boolean.FALSE;
        try {
//            employeur.setMatricule(matricule);
            if (utilisateur.getAdresse() != null && utilisateur.getAdresse().getEmail() == null) {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ADRESSE EMAIL UTILISATEUR EMPTY...RENSEIGNER L ADRESSE EMAIL", "IL SERA IMPOSSIBLE A CET UTILISATEUR DE SE CONNECTER"));
                return null;
            } else if (Objects.equals(utilisateursDao.isexistEmail(utilisateur.getAdresse().getEmail()), Boolean.TRUE)) {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ADRESSE EMAIL UTILISATEUR EXISTANT...RENSEIGNER A NOUVEAU VOTRE  EMAIL", "IL SERA IMPOSSIBLE A CET UTILISATEUR DE SE CONNECTER"));
                return null;
            }

            resultat = serviceEntreprise.addEntreprise(utilisateur);
            //generation cle de securite et mot de passe

            if (Objects.equals(resultat, Boolean.TRUE)) {
                this.sendInfosConnectionForMail();

                FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put(GlobalFonctions.ENTREPRISE_ACTIF, societe);
                success = societe.getRaissoci();

                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + exception.Success.OPERATION_SUCCESS.toString(), entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
//                this.doRedirect("acceuil_admin_system");

            } else {
                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.error", entete, myLoc);
                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail));

            }

        } catch (Throwable th) {

            //ecrire dans le fichier de log  
            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.error", entete, myLoc);
            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));

        }

        return success;

    }

    public void accueil() {
        this.doRedirect("acceuil_admin_system");
    }

    public String createCleSecuriteForUser() {
//        user.setCle_securite(Crypto.sha256("141087"));// la cle de securite c est 6chiffre important lors de la mise a jour d un mot de passe

        String cle = KeyGenCode.gen();
        Societe s = societeDao.getSocieteByCode(societe.getCodesoci());
        if (cle != null) {
            //verification si cette cle a ete deja generer  
            while (Objects.equals(utilisateursDao.cleSecuriteExiste(cle, s), Boolean.TRUE)) {
                cle = KeyGenCode.gen();
            }
        }
        return cle;
    }

    public String createPassWordForUser() {
//        user.setCle_securite(Crypto.sha256("141087"));// la cle de securite c est 6chiffre important lors de la mise a jour d un mot de passe

        String password = KeyGenCode.genPassWord();
        Societe s = societeDao.getSocieteByCode(societe.getCodesoci());
        if (password != null && s != null && s.getCodesoci() != null) {
            //verification si cette cle a ete deja generer  
            while (Objects.equals(utilisateursDao.passWordExiste(password, s), Boolean.TRUE)) {
                password = KeyGenCode.genPassWord();
            }
        }
        return password;
    }

    public void sendInfosConnectionForMail() throws Exception {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Locale myLoc = ctx.getViewRoot().getLocale();
        Mail mail = null;
        boolean test = false;
        String subject = "";
        String body = "";
        OrclassUtilisateurs user = null;
        user = utilisateursDao.findEntityHavingValue("login", utilisateur.getLogin().toUpperCase());
        Adresse adresse = user.getAdresse();
        if (adresse == null) {
            user.setMailEnvoye(Boolean.FALSE);
            passWord = this.createPassWordForUser().toUpperCase();
            user.setPassword(Crypto.sha256(passWord));
            cle = this.createCleSecuriteForUser().toUpperCase();
            user.setCle_securite(Crypto.sha256(cle));
//            user.setPassword(Crypto.sha256(this.createPassWordForUser().toUpperCase()));
            utilisateursDao.update(user);
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ADRESSE EMAIL NON EXISTANT...RENSEIGNER L ADRESSE EMAIL", "IL SERA IMPOSSIBLE A CET UTILISATEUR DE SE CONNECTER"));
//            System.out.println("pas d adresse mail");
            PrimeFaces.current().ajax().update(":form2");
            PrimeFaces.current().executeScript("PF('motPasseDialog').show()");

            return;
        }
        if (adresse != null && adresse.getEmail() == null) {
            user.setMailEnvoye(Boolean.FALSE);
            passWord = this.createPassWordForUser().toUpperCase();
            user.setPassword(Crypto.sha256(passWord));
            cle = this.createCleSecuriteForUser().toUpperCase();
            user.setCle_securite(Crypto.sha256(cle));
//            user.setPassword(Crypto.sha256(this.createPassWordForUser().toUpperCase()));
            utilisateursDao.update(user);
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ADRESSE EMAIL NON EXISTANT...RENSEIGNER L ADRESSE EMAIL", "IL SERA IMPOSSIBLE A CET UTILISATEUR DE SE CONNECTER"));

            System.out.println("pas d adresse mail");
            return;
        }
        if (user != null && user.getIdUtilisateur() != null) {
            mail = new Mail(EsphereMail.SMTP_SERVER.trim(), EsphereMail.PORT, EsphereMail.USER.trim(), EsphereMail.PASSWORD.trim());
            subject = "E-SPHERE: COMPTE CREE AVEC SUCCESS/PARAMETRE DE CONNEXION ";
            mail.subjectMessade(subject);
            mail.bodyMessage(body(user));
//            test = mail.send(EsphereMail.USER.trim(), adresse.getEmail().trim());
        }

//        mail = new Mail(this.paramModule.getParamModule().getServeurSTMP().trim(), Integer.parseInt(this.paramModule.getParamModule().getPortSTMP()), this.paramModule.getParamModule().getUsername().trim(), this.paramModule.getParamModule().getPassword());
//        subject = "BULLETIN DE PAIE : " + bul.getMois().getTypeMois().name() + "_" + bul.getMois().getAnnee().getId();
//        mail.subjectMessade(subject);
//        mail.bodyMessage(body(user));
//
//        test = mail.send(this.paramModule.getParamModule().getUsername().trim(), adresse.getEmail().trim());
        if (con.isHostServiceAvailable() == Boolean.TRUE && InternetAvailabilitiChecker.isInternetAvailable() == Boolean.TRUE) {
            mailInscription = new OrclssMailInscription(adresse.getEmail().trim(), subject, body(user));
            mailInscriptionDao.create(mailInscription);
            con.send(mailInscription);
            test = Boolean.TRUE;
        } else {
            test = Boolean.FALSE;
        }
        subject = "";

        if (test) {
            user.setPassword(Crypto.sha256(passWord.toUpperCase()));
            user.setCle_securite(Crypto.sha256(cle.toUpperCase()));
            user.setMailEnvoye(Boolean.TRUE);
            utilisateursDao.update(user);
            System.out.println("envoit mails terminer");
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Success.OPERATION_SUCCESS.toString(), null));
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("NOTIFICATION..." + user.getAdresse().getEmail(), "LES INFORMATIONS DU COMPTE D'UTILISATEUR ONT ETE ENVOYÉ DANS SA BOITE MAIL "));
            this.reset();
//            PrimeFaces.current().ajax().update(":form1");

        } else {
            user.setPassword(Crypto.sha256(passWord.toUpperCase()));
            user.setCle_securite(Crypto.sha256(cle.toUpperCase()));
            user.setMailEnvoye(Boolean.FALSE);
            utilisateursDao.update(user);
            login = user.getLogin();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, exception.Error.OPERATION_FAILED.name(), ""));
            PrimeFaces.current().ajax().update(":form2");
            PrimeFaces.current().executeScript("PF('motPasseDialog').show()");

        }
    }

    public String body(OrclassUtilisateurs user) {

        String prenom;
        cle = createCleSecuriteForUser();
        passWord = createPassWordForUser();
        if (user.getPrenom() != null) {
            prenom = user.getPrenom();
        } else {
            prenom = "";
        }

        String hostname;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
        } catch (Exception e) {
            hostname = "IP Host";
        }

        String contenuMail = "Bonjour " + user.getNom().toUpperCase() + " " + prenom.toUpperCase() + ". \n VOTRE COMPTE/LOGIN : " + user.getLogin().toUpperCase() + ". \n VOTRE MOT DE PASS/PASSWORD : " + passWord.toUpperCase() + ". \n VOTRE CLE DE SECURITE/SECURITY KEY : " + cle;

        contenuMail = contenuMail + "\n ";
        contenuMail = contenuMail + "\n  ";
//        contenuMail = contenuMail + "\n CORDIALEMENT L EQUIPE E-SPHERE  " + "\n LIEN DE CONNECTION/CONNECTION LINK : " + cheminApplication();
        contenuMail = contenuMail + "\n CORDIALEMENT L EQUIPE E-SPHERE  " + "\n LIEN DE CONNECTION/CONNECTION LINK : " + "http://" + "" + hostname + ":8181" + cheminApplication();

        System.out.println("contenu mail: " + contenuMail);
        return contenuMail;
    }

    public String cheminApplication() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
        String chemin = request.getContextPath() + "/connection.xhtml";
        System.out.println("lien de connection: " + chemin);
        return chemin;
    }

    public List<SelectItem> getLanguageCountrie() {
        List<SelectItem> items = new ArrayList<>();

        FacesContext ctx = FacesContext.getCurrentInstance();
        Locale myLoc = ctx.getViewRoot().getLocale();

        items.add(new SelectItem("fr", "French"));
        items.add(new SelectItem("en", "English"));
        items.add(new SelectItem("es", "Espagnol"));

        return items;
    }

    //getter setter
    private void doRedirect(String key) {
//         String chemin="administration/optionEtab.xhtml";
//      this.doRedirect(chemin);
        FacesContext fc = FacesContext.getCurrentInstance();
        NavigationHandler nh = fc.getApplication().getNavigationHandler();

        nh.handleNavigation(fc, null, "" + key);

    }

    //ajouter un  un ou pluisieur module a une entreprise
    public void addModuleforEntreprise() {

        FacesContext ctx = FacesContext.getCurrentInstance();

        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        //Locale myLoc =new Locale("fr");
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "entite.entreprise", null, myLoc)};
        String[] detail = {entete[0], "Parametres"};
        Collection<OrclassEntreprisesModules> mes = new ArrayList<>();
        OrclassEntreprisesModules me;
        OrclassEntreprisesModulesPK mepk;
        try {

            for (OrclassModules m : selectedModules) {
                
                me = orclassEntreprisesModulesDao.finKey(m,serviceDepartement);
                if (me == null) {
                    me = new OrclassEntreprisesModules();
                    me.setDateDebut(debut);
                    me.setDateFin(fin);
                    me.setServiceDepartement(serviceDepartement);
                    me.setOrclassModules(m);

                    mes.add(me);

                }

            }
            orclassEntreprisesModulesDao.createAll(mes);

            if (societe != null && societe != null) {

                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + exception.Success.OPERATION_SUCCESS.toString(), entete, myLoc);

                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));

            } else {
                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.error", entete, myLoc);
                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail));

            }

        } catch (Throwable th) {

            //ecrire dans le fichier de log  
            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.error", entete, myLoc);
            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));

        }
        this.reset();

    }

    public Boolean afficheAcceuil(int i) {
        if (i == 0) {

            return Boolean.TRUE;

        }

        return Boolean.FALSE;
    }

    //supprime un module a une entreprise
    public void deleteModuleforEntreprise() {

        FacesContext ctx = FacesContext.getCurrentInstance();

        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        //Locale myLoc =new Locale("fr");
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "entite.entreprise", null, myLoc)};
        String[] detail = {entete[0], "Parametres"};
        Collection<OrclassEntreprisesModules> mes = new ArrayList<>();
        OrclassEntreprisesModules me;
        OrclassEntreprisesModulesPK mepk;
        try {

            if (orclassEntreprisesModules.getIdSocieteModules() != null) {
                orclassEntreprisesModulesDao.delete(orclassEntreprisesModules);
            }

            if (societe != null && societe.getCodesoci() != null) {

                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + exception.Success.OPERATION_SUCCESS.toString(), entete, myLoc);

                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));

            } else {
                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.error", entete, myLoc);
                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail));

            }

        } catch (Throwable th) {

            //ecrire dans le fichier de log  
            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.error", entete, myLoc);
            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));

        }
        this.reset();
    }

    public void showDialogConfirmeCleSecurite() {
        if (utilisateur != null && utilisateur.getLogin() != null && utilisateur.getNom() != null && utilisateur.getPrenom() != null) {
            PrimeFaces.current().executeScript("PF('motPasseDialog2').show()");
        }
    }

    public void controlerAddUtisateurSysteme() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        OrclassUtilisateurs user = (OrclassUtilisateurs) ctx.getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
        if (user != null && user.getIdUtilisateur() != null) {
            if (cle_securite.contains("-")) {
                cle_securite = cle_securite.replace("-", "");
            }
            if (!user.getCle_securite().equals(Crypto.sha256(cle_securite.toUpperCase()))) {
                PrimeFaces.current().executeScript("PF('motPasseDialog2').hide()");
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "THE SECURITY KEY IS NOT VALID... AUTHORIZATION DENIED", exception.Error.OPERATION_FAILED.name()));
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE SECURITY KEY IS NOT VALID...", "AUTHORIZATION DENIED"));
                return;
            } else {
                this.addUserSystem();
            }

        }
    }

    public void addUserSystem() {

        FacesContext ctx = FacesContext.getCurrentInstance();

        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "utilisateur.system", null, myLoc)};
        OrclassUtilisateurs user = null;
        try {
            if (utilisateursDao.findEntityHavingValue("login", utilisateur.getLogin().toUpperCase()) == null) {
                user = new OrclassUtilisateurs();
                user.setLogin(utilisateur.getLogin().toUpperCase());
//                user.setPassword(Crypto.sha256(utilisateur.getPassword().toUpperCase()));
                user.setActif(Boolean.TRUE);
                user.setNom(utilisateur.getNom());
                user.setLangue(utilisateur.getLangue());
                user.setPrenom(utilisateur.getPrenom());
                user.setAdresse(utilisateur.getAdresse());
                user.setTypeUtilisateur(TypeUtilisateur.UTILISATEUR_SYSTEM);
                user.setDateCreation(new Date());
                utilisateursDao.create(user);
                this.sendInfosConnectionForMail();
                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + exception.Success.OPERATION_SUCCESS.toString(), entete, myLoc);

                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
            } else {
                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.error", entete, myLoc);
                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail));
            }

        } catch (Throwable th) {

            //ecrire dans le fichier de log  
            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.error", entete, myLoc);
            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));

        }
        this.reset();
    }

    public String redirect(String pathFile) {
        ExternalContext etx = FacesContext.getCurrentInstance().getExternalContext();
        String chemin = etx.getRequestContextPath();

        this.setAffiche_home_acceuil_admin_system(Boolean.TRUE);
        this.setAffiche_home_acceuil_user(Boolean.FALSE);
        return pathFile;
//         doRedirect(chemin + "/" + "administration/configuration.xhtml");

    }

    private void doRedirect_path(String url) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chargeUserForUpdatePassWord() {
        FacesContext ctx = FacesContext.getCurrentInstance();

        utilisateur = (OrclassUtilisateurs) ctx.getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
        if (utilisateur == null || utilisateur.getIdUtilisateur() == null) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "USER DO NOT EXIST PLEASE TRY AGAINST", exception.Error.OPERATION_FAILED.name()));
        }
    }

    public void updatePassWord() {
        FacesContext ctx = FacesContext.getCurrentInstance();
//        this.setOldPassWord(Crypto);

        if (utilisateur != null && utilisateur.getIdUtilisateur() != null && utilisateur.getPassword().equals(Crypto.sha256(oldPassWord.toUpperCase())) == Boolean.TRUE) {
            if (utilisateur.getCle_securite() == null) {
//        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "THE SECURITY KEY IS NOT VALID... AUTHORIZATION DENIED", exception.Error.OPERATION_FAILED.name()));
                if (utilisateur.getLogin().equals("SYSTEME")) {
                    utilisateur.setCle_securite(Crypto.sha256("SYSTEM"));
                } else {
                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE SECURITY KEY IS NULL..", "AUTHORIZATION DENIED"));
                    return;
                }

            }
            if (!utilisateur.getCle_securite().equals(Crypto.sha256(cle_securite.toUpperCase()))) {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "THE SECURITY KEY IS NOT VALID... AUTHORIZATION DENIED", exception.Error.OPERATION_FAILED.name()));
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE SECURITY KEY IS NOT VALID...", "AUTHORIZATION DENIED"));
                return;
            }

            String passWord = utilisateur.getPassword();
            utilisateur.setPassword(Crypto.sha256(newPassWord.toUpperCase()));
            utilisateursDao.update(utilisateur);
//             connectionController.deconnexion();
            deconection();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, exception.Success.OPERATION_SUCCESS.toString(), exception.Success.UPDATE_SUCCESS.toString()));
//            this.doRedirect("client.deconnect");kjkkkkl

        } else if (utilisateur == null || utilisateur.getIdUtilisateur() == null) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "USER DO NOT EXIST PLEASE TRY AGAINST", exception.Error.OPERATION_FAILED.name()));
        } else if (utilisateur.getPassword().equals(Crypto.sha256(oldPassWord.toUpperCase())) == Boolean.FALSE) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, " OLD PASSWORD NOT CORRECT  PLEASE TRY AGAINST", exception.Error.OPERATION_FAILED.name()));
        }
    }

    public void deconection() {
        //variable pour la redirection
        String navigateTo = "client.deconnect";
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ext = fc.getExternalContext();
        //on nettoie les variables sessions
        ext.getSessionMap().clear();
        ext.getRequestMap().remove("page");

        //reinitialise la session 
        ext.getSession(false);
        ext.invalidateSession();

        connectionController.setIsLoggedIn(false);
        doRedirect();
    }

    public void doRedirect() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            NavigationHandler nh = context.getApplication().getNavigationHandler();
            nh.handleNavigation(context, null, "client.deconnect");

        } catch (Exception e) {

        }
    }

       public List<SelectItem> getServiceDepartements() {
        List<SelectItem> items = new ArrayList<>();

        FacesContext ctx = FacesContext.getCurrentInstance();
        Locale myLoc = ctx.getViewRoot().getLocale();
        for (ServiceDepartement sd : ServiceDepartement.values()) {

            items.add(new SelectItem(sd, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, sd.name(), null, myLoc)));

        }

        return items;
    }

    
    public String showDetails() {
        return null;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public Collection<Societe> getListeSociete() {
        return listeSociete;
    }

    public void setListeSociete(Collection<Societe> listeSociete) {
        this.listeSociete = listeSociete;
    }

    public IEntreprise getServiceEntreprise() {
        return serviceEntreprise;
    }

    public void setServiceEntreprise(IEntreprise serviceEntreprise) {
        this.serviceEntreprise = serviceEntreprise;
    }

    public OrclassModulesDao getOrclassModulesDao() {
        return orclassModulesDao;
    }

    public void setOrclassModulesDao(OrclassModulesDao orclassModulesDao) {
        this.orclassModulesDao = orclassModulesDao;
    }

    public OrclassModules getModules() {
        return modules;
    }

    public void setModules(OrclassModules modules) {
        this.modules = modules;
    }

    public OrclassEntreprisesModulesDao getOrclassEntreprisesModulesDao() {
        return orclassEntreprisesModulesDao;
    }

    public void setOrclassEntreprisesModulesDao(OrclassEntreprisesModulesDao orclassEntreprisesModulesDao) {
        this.orclassEntreprisesModulesDao = orclassEntreprisesModulesDao;
    }

    public OrclassEntreprisesModules getOrclassEntreprisesModules() {
        return orclassEntreprisesModules;
    }

    public void setOrclassEntreprisesModules(OrclassEntreprisesModules orclassEntreprisesModules) {
        this.orclassEntreprisesModules = orclassEntreprisesModules;
    }

    public CurrentInstance getCurrentInstance() {
        return currentInstance;
    }

    public void setCurrentInstance(CurrentInstance currentInstance) {
        this.currentInstance = currentInstance;
    }

    public String getAbsolutePathImages() {
        return absolutePathImages;
    }

    public void setAbsolutePathImages(String absolutePathImages) {
        this.absolutePathImages = absolutePathImages;
    }

    public String getCurrentFolder() {
        return currentFolder;
    }

    public void setCurrentFolder(String currentFolder) {
        this.currentFolder = currentFolder;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getMsgdetail() {
        return msgdetail;
    }

    public void setMsgdetail(String msgdetail) {
        this.msgdetail = msgdetail;
    }

    public byte[] getFile1() {
        return file1;
    }

    public void setFile1(byte[] file1) {
        this.file1 = file1;
    }

    public Wizard getWizard() {
        return wizard;
    }

    public void setWizard(Wizard wizard) {
        this.wizard = wizard;
    }

    public StreamedContent getLogoimages() {
        return logoimages;
    }

    public void setLogoimages(StreamedContent logoimages) {
        this.logoimages = logoimages;
    }

    public Collection<OrclassModules> getListModules() {
        this.chargerModuleNotHaveEntite();
        return listModules;
    }

    public void setListModules(Collection<OrclassModules> listModules) {
        this.listModules = listModules;
    }

    public Collection<OrclassModules> getSelectedModules() {
        return selectedModules;
    }

    public void setSelectedModules(Collection<OrclassModules> selectedModules) {
        this.selectedModules = selectedModules;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public OrclassUtilisateurs getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(OrclassUtilisateurs utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getNewPassWord() {
        return newPassWord;
    }

    public void setNewPassWord(String newPassWord) {
        this.newPassWord = newPassWord;
    }

    public String getOldPassWord() {
        return oldPassWord;
    }

    public void setOldPassWord(String oldPassWord) {
        this.oldPassWord = oldPassWord;
    }

    public Boolean getAffiche_home_acceuil_admin_system() {
        return affiche_home_acceuil_admin_system;
    }

    public void setAffiche_home_acceuil_admin_system(Boolean affiche_home_acceuil_admin_system) {
        this.affiche_home_acceuil_admin_system = affiche_home_acceuil_admin_system;
    }

    public Boolean getAffiche_home_acceuil_user() {
        return affiche_home_acceuil_user;
    }

    public void setAffiche_home_acceuil_user(Boolean affiche_home_acceuil_user) {
        this.affiche_home_acceuil_user = affiche_home_acceuil_user;
    }

    public ConnectionController getConnectionController() {
        return connectionController;
    }

    public void setConnectionController(ConnectionController connectionController) {
        this.connectionController = connectionController;
    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) {
        this.cle = cle;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getCle_securite() {
        return cle_securite;
    }

    public void setCle_securite(String cle_securite) {
        this.cle_securite = cle_securite;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Collection<Ville> getListVille() {
        return listVille;
    }

    public void setListVille(Collection<Ville> listVille) {
        this.listVille = listVille;
    }

    public ServiceDepartement getServiceDepartement() {
        return serviceDepartement;
    }

    public void setServiceDepartement(ServiceDepartement serviceDepartement) {
        this.serviceDepartement = serviceDepartement;
    }

    public List<IndicatifPays> getListeIndicatifPays() {
        return listeIndicatifPays;
    }

    public void setListeIndicatifPays(List<IndicatifPays> listeIndicatifPays) {
        this.listeIndicatifPays = listeIndicatifPays;
    }
    
    

}
