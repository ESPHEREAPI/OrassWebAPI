/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.administration;

import Licence.KeyGenCode;
import controllers.CurrentInstance;
import dao.IndicatifPaysDao;
import dao.ORCLASS_PROFILS_UTILISATEURSDao;

import dao.OrclassMenusDao;
import dao.OrclassModulesDao;
import dao.OrclassProfilsDao;
import dao.OrclassUtilisateursDao;

import dao.OrclssMailInscriptionDao;
import dao.PaysDao;

import dao.PhotoDao;

import droitAcces.IDroitAcces;
import enums.Actions;
import enums.FonctionnaliteModuleAdministration;
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
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import mdb.Mail;
import modele.Adresse;
import modele.IndicatifPays;
import modele.ORCLASS_PROFILS_UTILISATEURS;
import modele.OrclassActions;
import modele.OrclassEntreprises;
import modele.OrclassFonctionnalites;

import modele.OrclassMenus;
import modele.OrclassMenusAcces;
import modele.OrclassModules;
import modele.OrclassProfils;
import modele.OrclassUtilisateurs;

import modele.OrclssMailInscription;
import modele.Photo;
import modele.Societe;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parametrage.ISecurite;
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
@Named(value = "utilisateurController")
@ViewScoped
public class UtilisateurController implements Serializable {

    /**
     * Creates a new instance of UtilisateurController
     */
    @EJB
    private ISecurite securiteService;
    private Collection<OrclassModules> moduleUser;

    @EJB
    ORCLASS_PROFILS_UTILISATEURSDao pROFILS_UTILISATEURSDao;
    @EJB
    private ISecurite securiteUser;

    OrclassUtilisateurs utilisateurs;
    FileUploadEvent fileUploadEvent = null;
    private String imagePath;
    private String absolutePathImages;
    private String currentFolder = "/photos";
    private String defautImage = currentFolder + "/inconnu.jpg";
    private String represntation;
    //taille par defaut des images
    private static final int DEFAULT_BUFFER_SIZE = 10240;
    @EJB
    PhotoDao photoDao;
    Photo photo;
    @EJB
    PaysDao paysDao;

    @EJB
    IndicatifPaysDao indicatifPaysDao;
    @EJB
    OrclassProfilsDao profilsDao;

    @EJB
    ISecurite serviceEntreprise;
    @EJB
    OrclassUtilisateursDao utilisateursDao;
    @Inject
    CurrentInstance currentInstance;
    @EJB
    OrclassMenusDao menusDao;
    @EJB
    OrclassModulesDao modulesDao;
    @EJB
    IDroitAcces serviceAccess;

    @EJB
    OrclssMailInscriptionDao mailInscriptionDao;

    private DualListModel<OrclassModules> modulesModel;
    // private DualListModel<String> Roles;
    private DualListModel<OrclassFonctionnalites> menusModel;
    private DualListModel<OrclassActions> actionModel;
    private List<OrclassModules> modulesSource;
    private List<OrclassModules> modulesTarget;
    private OrclassModules modules;
    private DualListModel<String> etatModel;
    private List<String> etatAcquis;
    private List<String> etatNonAcquis;
    private DualListModel<OrclassFonctionnalites> fonctionnaliteModel;
    private List<OrclassFonctionnalites> colFonctionnaliteByUser;
    private List<OrclassFonctionnalites> menusSource;
    private List<OrclassFonctionnalites> menusTarget;

    private List<OrclassActions> actionSource;
    private List<OrclassActions> actionTarget;
    private List<OrclassProfils> colProfil = new ArrayList<>();

    private List<OrclassProfils> selectedProfils = new ArrayList<>();
    private Societe societe;
    private OrclassProfils profilsByUser;
    private List<OrclassUtilisateurs> listUtilisateur = new ArrayList<>();
    private OrclassFonctionnalites fonctionnaliteByUser;

    private List<IndicatifPays> listeIndicatifPays = new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(UtilisateurController.class);
    String summary = "";
    String msgdetail = "";
    private LazyDataModel<OrclassUtilisateurs> lazyModel;
    List<OrclassUtilisateurs> datasource = new ArrayList<>();
    List<OrclassUtilisateurs> filteredUtilisateur;
    private OrclassUtilisateurs selectedUtiliseur;
    private OrclassMenus menu;
    private OrclassModules module;
    OrclassUtilisateurs user;
    String cle, cle_securite;
    String passWord;
    String login;
    Connection con;
    OrclssMailInscription mailInscription;

    private String ancienEmail;

    public UtilisateurController() {
        photo = new Photo();
        selectedProfils = new ArrayList<>();
        utilisateurs = new OrclassUtilisateurs();
        selectedUtiliseur = new OrclassUtilisateurs();
        fonctionnaliteByUser = new OrclassFonctionnalites();
        con = new Connection();
    }

    public void reset() {

        this.init();
        this.currentInstance.getAjaxRequestBuilder().reset();
//        RequestContext.getCurrentInstance().reset(":accord:formPersonnel");
    }

    public OrclassProfils chargerProfilAdmin() {
        OrclassProfils adminProfil = profilsDao.findEntityHavingValue("code", "admin");
        return adminProfil;
    }

    public void init() {
        this.absolutePathImages = defautImage;
        selectedProfils = new ArrayList<>();
        photo = new Photo();
        utilisateurs = new OrclassUtilisateurs();
        selectedUtiliseur = new OrclassUtilisateurs();
        profilsByUser = new OrclassProfils();
        colProfil = (List<OrclassProfils>) profilsDao.getAllProfilHaveAccesByEntreprise(societe);
        colProfil.add(chargerProfilAdmin());
        if (Objects.equals(societe.getLogin_user_automatique(), Boolean.TRUE)) {
            utilisateurs.setLogin(this.createLoginUser());
        }
        ancienEmail = "";

    }

    public void chargerUtilisateur() {
        if (societe != null && societe.getCodesoci() != null) {
            datasource = (List<OrclassUtilisateurs>) utilisateursDao.listUtilisateurWithFilters(societe);
        }

    }

    public void handleFileUpload(FileUploadEvent event) {
//        ImageIcon inconImage=null;
        this.fileUploadEvent = event;
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        imagePath = extContext.getRealPath("") + currentFolder;
        //imagePath+=File.separator;
        String fileName = generateCodePhoto();
//        if (utilisateurs == null) {
//            fileName = generateCodePhoto();
//        } else {
//            fileName = matricule;
//        }

        photo.setIdUtilisateur(utilisateurs);
//       GlobalFonctions.modifierTailImage(paramModule.getParamModule().getHauteurImage(), paramModule.getParamModule().getLargeurImage(), event.getFile().getContents());
        photo.setImage(event.getFile().getContent());

        photo.setLibelle(this.uploadFile(fileUploadEvent, imagePath, fileName));
    }

    public void onTabChange(TabChangeEvent event) {
        if (event.getTab().getId().equals("tab2")) {
            if (utilisateurs != null && utilisateurs.getIdUtilisateur() != null && profilsByUser != null && profilsByUser.getIdProfil() != null) {
                moduleUser = modulesModel.getTarget();
            }
        }
        if (event.getTab().getId().equals("action_tab")) {
            if (utilisateurs != null && utilisateurs.getIdUtilisateur() != null && profilsByUser != null && profilsByUser.getIdProfil() != null && modules != null && modules.getIdModule() != null) {
                this.getColFonctionnaliteByUser();
            }
        }

//        if (utilisateurs != null && utilisateurs.getIdUtilisateur() != null && profilsByUser != null && profilsByUser.getIdProfil() != null) {
//            this.getModuleUser();
//        } else if (utilisateurs != null && utilisateurs.getIdUtilisateur() != null && profilsByUser != null && profilsByUser.getIdProfil() != null && modules != null && modules.getIdModule() != null) {
//           
//        } else {
//            this.getprofilByUser();
//        }
        if ("autreinfos".equals(event.getTab().getId())) {
            if ("".equals(utilisateurs.getNom()) || utilisateurs.getNom() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "WRITE THE VALUE NAME"));
                PrimeFaces.current().executeScript("PF('tabView1').select(0);");
                return;
            }

            if (utilisateurs.getDatenaissance() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "WRITE THE VALUE BIRTH DATE"));
                PrimeFaces.current().executeScript("PF('tabView1').select(0);");
                return;
            }

        } else if ("adresseuser".equals(event.getTab().getId())) {
            if ("".equals(utilisateurs.getNumeroCNI()) || utilisateurs.getNumeroCNI() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "WRITE THE VALUE CNI"));
                PrimeFaces.current().executeScript("PF('tabView1').select(1);");
                return;
            }
            if ("".equals(utilisateurs.getSexe()) || utilisateurs.getSexe() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "WRITE THE VALUE Sex"));
                PrimeFaces.current().executeScript("PF('tabView1').select(1);");
                return;
            }
            if ("".equals(utilisateurs.getSituationMatrimoniale()) || utilisateurs.getSituationMatrimoniale() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "WRITE THE VALUE SITUATION"));
                PrimeFaces.current().executeScript("PF('tabView1').select(1);");
                return;
            }

            if (selectedProfils.isEmpty()) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "WRITE THE VALUE PROFIL"));
                PrimeFaces.current().executeScript("PF('tabView1').select(1);");
                return;
            }

//            PrimeFaces.current().executeScript("PF('principal').select(0);");
        } else if ("connectionuser".equals(event.getTab().getId())) {
            if ("".equals(utilisateurs.getAdresse().getEmail()) || utilisateurs.getAdresse().getEmail() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "WRITE THE VALUE ADRESS MAIL"));
                PrimeFaces.current().executeScript("PF('tabView1').select(2);");
                return;
            }
            if ("".equals(utilisateurs.getAdresse().getTel()) || utilisateurs.getAdresse().getTel() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "WRITE THE VALUE PHONE"));
                PrimeFaces.current().executeScript("PF('tabView1').select(2);");
                return;
            }
        }

        PrimeFaces.current().ajax().update(":tabprincipal:form2");

    }

    @PostConstruct
    void initialiseSession() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        societe = (Societe) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
        if (societe == null) {
            societe = (Societe) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
            if (societe == null) {
                return;
            }
        }
        this.absolutePathImages = defautImage;
        colProfil = (List<OrclassProfils>) profilsDao.findAll();
        colProfil.add(chargerProfilAdmin());
        selectedProfils = new ArrayList<>();

        datasource = (List<OrclassUtilisateurs>) utilisateursDao.findAll();
//        colProfil=new ArrayList<>();
        menu = menusDao.findEntityHavingValue("code", "entite.user");
        module = modulesDao.findEntityHavingValue("code", "mod.admin");
        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);

        listeIndicatifPays = (List<IndicatifPays>) indicatifPaysDao.findAll();
        if (Objects.equals(societe.getLogin_user_automatique(), Boolean.TRUE)) {
            utilisateurs.setLogin(this.createLoginUser());
        }

    }

    public String createLoginUser() {
        String login, prefixe;
        Long nbre = utilisateursDao.nbreUserCreateByCompagny(societe);
        nbre++;
        login = nbre.toString().length() == 1 ? "0" + nbre.toString() : nbre.toString();
        if (societe.getPrefix_login_user() != null && !"".equals(societe.getPrefix_login_user())) {

            login = societe.getPrefix_login_user() + "" + login;
            while (utilisateursDao.findEntityHavingValue("login", login) != null) {
                nbre++;
                login = nbre.toString().length() == 1 ? "0" + nbre.toString() : nbre.toString();
                login = societe.getPrefix_login_user() + "" + login;

            }
            return login;

        }
        while (utilisateursDao.findEntityHavingValue("login", login) != null) {
            nbre++;
            login = nbre.toString().length() == 1 ? "0" + nbre.toString() : nbre.toString();
//            login = entreprise.getPrefix_login_user() + "" + login;

        }

        return login;
    }

    /*
    liste les profil en fonction d un utilisateur
     */
    public void getprofilByUser() {
        if (utilisateurs != null && utilisateurs.getIdUtilisateur() != null) {
            colProfil = profilsDao.getProfilByUtilisateu(utilisateurs);

        }
    }

    public Boolean accessCreeUser() {
        OrclassActions action = null;
        OrclassFonctionnalites fon = null;
        OrclassMenusAcces ma = null;
        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_utilisateur.name());
            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);

            return serviceAccess.accessAction(user, action, menu);
        }
        return Boolean.FALSE;
    }

    public Boolean accessModifierUser() {
        OrclassActions action = null;
        OrclassFonctionnalites fon = null;
        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_utilisateur.name());
            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
            return serviceAccess.accessAction(user, action, menu);
        }
        return Boolean.FALSE;
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

    public String generateCodePhoto() {

        StringBuilder code = null;

        //recuperation du nombre du personnel
        Long nbre = photoDao.count();
        boolean test = true;
        while (test) {
            code = new StringBuilder();
            int longeurNumber = nbre.toString().length();

            for (int i = 0; i < 5 - longeurNumber; i++) {
                code.append("0");
            }
            code.append(++nbre);

            test = photoDao.findEntityHavingValue("libelle", code.toString()) != null;
        }

        return code.toString();

    }

    public List<SelectItem> getLanguageCountrie() {
        List<SelectItem> items = new ArrayList<>();

        FacesContext ctx = FacesContext.getCurrentInstance();
        Locale myLoc = ctx.getViewRoot().getLocale();

        items.add(new SelectItem("fr", "Francais"));
        items.add(new SelectItem("en", "English"));
        items.add(new SelectItem("es", "Espagnol"));

        return items;
    }

    public String addUtilisateur() {
        String success = null;
        FacesContext ctx = FacesContext.getCurrentInstance();

        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        //Locale myLoc =new Locale("fr");
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "utilisateur", null, myLoc)};
        String[] detail = {entete[0], "Parametres"};
//        if (Objects.equals(entreprise.getEnvoiMail(), Boolean.FALSE) || entreprise.getServeurSTMP() == null || entreprise.getUsername() == null || entreprise.getPortSTMP() == null || entreprise.getPassword() == null) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("VERIFIER LES PARAMETRES DE NOTIFICATION  ...PLEASE CHECK VALUE FOR NOTIFICATION "));
//            return null;
//        }
        if (utilisateurs.getLangue() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("PLEASE CHECK THE VALUE FOR LANGUAGE  ...FAIRE UN CHOIX SUR LA LANGUE PAR DEFAUT "));
            return null;

        }

        if ("".equals(utilisateurs.getNom()) || utilisateurs.getNom() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "WRITE THE VALUE NAME"));
//                PrimeFaces.current().executeScript("PF('tabView1').select(0);");
            return null;
        }

        if (utilisateurs.getDatenaissance() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "WRITE THE VALUE BIRTH DATE"));
//                PrimeFaces.current().executeScript("PF('tabView1').select(0);");
            return null;
        }

        if ("".equals(utilisateurs.getNumeroCNI()) || utilisateurs.getNumeroCNI() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "WRITE THE VALUE CNI"));
//                PrimeFaces.current().executeScript("PF('tabView1').select(1);");
            return null;
        }
        if ("".equals(utilisateurs.getSexe()) || utilisateurs.getSexe() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "WRITE THE VALUE Sex"));
//                PrimeFaces.current().executeScript("PF('tabView1').select(1);");
            return null;
        }
        if ("".equals(utilisateurs.getSituationMatrimoniale()) || utilisateurs.getSituationMatrimoniale() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "WRITE THE VALUE SITUATION"));
//                PrimeFaces.current().executeScript("PF('tabView1').select(1);");
            return null;
        }

        if (selectedProfils.isEmpty()) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "WRITE THE VALUE PROFIL"));
//                PrimeFaces.current().executeScript("PF('tabView1').select(1);");
            return null;
        }

        if ("".equals(utilisateurs.getAdresse().getEmail()) || utilisateurs.getAdresse().getEmail() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "WRITE THE VALUE ADRESS MAIL"));
//                PrimeFaces.current().executeScript("PF('tabView1').select(2);");
            return null;
        } else if (Objects.equals(utilisateursDao.isexistEmail(utilisateurs.getAdresse().getEmail()), Boolean.TRUE)) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ADRESSE EMAIL UTILISATEUR EXISTANT...RENSEIGNER A NOUVEAU VOTRE  EMAIL", "IL SERA IMPOSSIBLE A CET UTILISATEUR DE SE CONNECTER"));
            return null;
        }

        if ("".equals(utilisateurs.getAdresse().getTel()) || utilisateurs.getAdresse().getTel() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "WRITE THE VALUE PHONE"));
//                PrimeFaces.current().executeScript("PF('tabView1').select(2);");
            return null;
        }

        try {
//            employeur.setMatricule(matricule);
//            if (utilisateursDao.findEntityHavingValue("login", utilisateurs.getLogin().toUpperCase()) == null) {
            utilisateurs.setActif(Boolean.TRUE);
            this.serviceEntreprise.addUtilisateur(utilisateurs, selectedProfils, utilisateurs.getServiceDepartement());
//            }

            utilisateurs = utilisateursDao.findEntityHavingValue("login", utilisateurs.getLogin().toUpperCase());
            if (utilisateurs != null && utilisateurs.getIdUtilisateur() != null) {
                try {
                    if (photoDao.getPhotoByPersonne(utilisateurs) == null && !photo.getLibelle().equals(defautImage)) {
                        photo.setIdUtilisateur(utilisateursDao.findEntityHavingValue("login", utilisateurs.getLogin().toUpperCase()));
                        photoDao.create(photo);
                    }
                } catch (Exception e) {

                }

                /*
                liason entre profil et utilisateur
                 */
                for (OrclassProfils pr : selectedProfils) {
                    if (pROFILS_UTILISATEURSDao.finkey(utilisateurs, pr) == null) {
                        ORCLASS_PROFILS_UTILISATEURS pu = new ORCLASS_PROFILS_UTILISATEURS();
                        pu.setIdProfil(pr);
                        pu.setIdUtilisateur(utilisateurs);
                        pROFILS_UTILISATEURSDao.create(pu);
                    }

                }
                this.sendInfosConnectionForMail();
                //                FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put(GlobalFonctions.ENTREPRISE_ACTIF, entreprise);
                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + exception.Success.OPERATION_SUCCESS.toString(), entete, myLoc);
                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
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
        this.chargerUtilisateur();
        reset();
        return success;

    }

    public String createCleSecuriteForUser() {
//        user.setCle_securite(Crypto.sha256("141087"));// la cle de securite c est 6chiffre important lors de la mise a jour d un mot de passe

        String cle = KeyGenCode.gen();
        Societe e = this.societe;
        if (cle != null) {
            //verification si cette cle a ete deja generer  
            while (Objects.equals(utilisateursDao.cleSecuriteExiste(cle, e), Boolean.TRUE)) {
                cle = KeyGenCode.gen();
            }
        }
        return cle;
    }

    public String createPassWordForUser() {
//        user.setCle_securite(Crypto.sha256("141087"));// la cle de securite c est 6chiffre important lors de la mise a jour d un mot de passe

        String password = KeyGenCode.genPassWord();
        Societe e = this.societe;
        if (password != null && e != null && e.getCodesoci() != null) {
            //verification si cette cle a ete deja generer  
            while (Objects.equals(utilisateursDao.passWordExiste(password, e), Boolean.TRUE)) {
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
        user = this.utilisateurs;
        Adresse adresse = user.getAdresse();
        if (adresse == null) {
            user.setMailEnvoye(Boolean.FALSE);
            passWord = this.createPassWordForUser().toUpperCase();
            user.setPassword(Crypto.sha256(passWord));
            cle = this.createCleSecuriteForUser().toUpperCase();
            user.setCle_securite(Crypto.sha256(cle));
            login = user.getLogin();
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
//            mail = new Mail(entreprise.getServeurSTMP().trim(), Integer.parseInt(entreprise.getPortSTMP().trim()), entreprise.getUsername().trim(), entreprise.getPassword().trim());
            subject = "E-SPHERE: COMPTE CREE AVEC SUCCESS/PARAMETRE DE CONNEXION ";
//            mail.subjectMessade(subject);
//            mail.bodyMessage(body(user));
            if (con.isHostServiceAvailable() == Boolean.TRUE && InternetAvailabilitiChecker.isInternetAvailable() == Boolean.TRUE) {
                mailInscription = new OrclssMailInscription(adresse.getEmail().trim(), subject, body(user));
                mailInscriptionDao.create(mailInscription);
                con.send(mailInscription);
                test = Boolean.TRUE;
            } else {
                test = Boolean.FALSE;
            }
//            test = mail.send(entreprise.getUsername().trim(), adresse.getEmail().trim());
        }

//        mail = new Mail(this.paramModule.getParamModule().getServeurSTMP().trim(), Integer.parseInt(this.paramModule.getParamModule().getPortSTMP()), this.paramModule.getParamModule().getUsername().trim(), this.paramModule.getParamModule().getPassword());
//        subject = "BULLETIN DE PAIE : " + bul.getMois().getTypeMois().name() + "_" + bul.getMois().getAnnee().getId();
//        mail.subjectMessade(subject);
//        mail.bodyMessage(body(user));
//
//        test = mail.send(this.paramModule.getParamModule().getUsername().trim(), adresse.getEmail().trim());
        subject = "";

        if (test) {
            user.setPassword(Crypto.sha256(passWord == null ? this.createPassWordForUser().toUpperCase() : passWord.toUpperCase()));
            user.setCle_securite(cle == null ? Crypto.sha256(this.createCleSecuriteForUser().toUpperCase()) : Crypto.sha256(cle.toUpperCase()));
            user.setMailEnvoye(Boolean.TRUE);
            utilisateursDao.update(user);
            System.out.println("envoit mails terminer");
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Success.OPERATION_SUCCESS.toString(), null));
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("NOTIFICATION..." + user.getAdresse().getEmail(), "LES INFORMATIONS DU COMPTE D'UTILISATEUR ONT ETE ENVOYÉ DANS SON ADRESSE "));
            this.reset();
//            PrimeFaces.current().ajax().update(":form1");

        } else {
            user.setPassword(Crypto.sha256(passWord == null ? this.createPassWordForUser().toUpperCase() : passWord.toUpperCase()));
            user.setCle_securite(cle == null ? Crypto.sha256(this.createCleSecuriteForUser().toUpperCase()) : Crypto.sha256(cle.toUpperCase()));
            user.setMailEnvoye(Boolean.FALSE);
            utilisateursDao.update(user);
            login = user.getLogin();
            cle = cle == null ? this.createCleSecuriteForUser().toUpperCase() : cle;
            passWord = passWord == null ? Crypto.sha256(this.createPassWordForUser().toUpperCase()) : Crypto.sha256(passWord.toUpperCase());
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
        System.out.println(" tail contenu mail: " + contenuMail.length());
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

    public void reinitialiserLoginAndMotPasse(OrclassUtilisateurs u) throws Exception {
        if (u != null && u.getIdUtilisateur() != null && Objects.equals(u.getMailEnvoye(), Boolean.FALSE)) {
            this.sendInfosConnectionForMail();
        }
    }

    public String updateUser() {
        String success = null;
        FacesContext ctx = FacesContext.getCurrentInstance();

        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        //Locale myLoc =new Locale("fr");
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "utilisateur", null, myLoc)};
        String[] detail = {entete[0], "Parametres"};

        if (ancienEmail.equals(utilisateurs.getAdresse().getEmail()) == Boolean.FALSE && Objects.equals(utilisateursDao.isexistEmail(utilisateurs.getAdresse().getEmail()), Boolean.TRUE)) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ADRESSE EMAIL UTILISATEUR EXISTANT...RENSEIGNER A NOUVEAU VOTRE  EMAIL", "IL SERA IMPOSSIBLE A CET UTILISATEUR DE SE CONNECTER"));
            return null;
        }
        try {
////            employeur.setMatricule(matricule);
////            if (utilisateursDao.findEntityHavingValue("login", utilisateurs.getLogin().toUpperCase()) == null) {
//            this.serviceEntreprise.addUtilisateur(utilisateurs, colProfil, entreprise);
////            }

            if (utilisateurs != null && utilisateurs.getIdUtilisateur() != null) {
                if (!selectedProfils.isEmpty()) {
                    this.serviceEntreprise.addUtilisateur(utilisateurs, selectedProfils, utilisateurs.getServiceDepartement());
                    for (OrclassProfils pr : selectedProfils) {
                        if (pROFILS_UTILISATEURSDao.finkey(utilisateurs, pr) == null) {
                            ORCLASS_PROFILS_UTILISATEURS pu = new ORCLASS_PROFILS_UTILISATEURS();
                            pu.setIdProfil(pr);
                            pu.setIdUtilisateur(utilisateurs);
                            pROFILS_UTILISATEURSDao.create(pu);
                        }

                    }
                }
                utilisateursDao.update(utilisateurs);
                if (photoDao.getPhotoByPersonne(utilisateurs) != null && !photo.getLibelle().equals(defautImage)) {
                    photoDao.update(photo);

                } else if (photo.getId() == null && photo.getLibelle() != null) {
                    photoDao.create(photo);
                }
                if (utilisateurs != null && Objects.equals(utilisateurs.getMailEnvoye(), Boolean.FALSE) && Objects.equals(securiteUser.testeFirstConnection(utilisateurs), Boolean.TRUE) && (utilisateurs.getPassword() == null || utilisateurs.getPassword() == "") && (utilisateurs.getCle_securite() == null || utilisateurs.getCle_securite() == "")) {//first conenection a true montre que cet utilisateur ne c est pas encore connecter et n a pas 
                    this.sendInfosConnectionForMail();
                }

//                FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put(GlobalFonctions.ENTREPRISE_ACTIF, entreprise);
                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + exception.Success.OPERATION_SUCCESS.toString(), entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));

            } else {
                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.error", entete, myLoc);
                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail));

            }

        } catch (Throwable th) {

            //ecrire dans le fichier de log  
            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.error", entete, myLoc);
            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));

        }
        this.chargerUtilisateur();
        reset();
        return success;

    }

    public String saveUserModule() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "entite.module", null, myLoc)};

        // on recupere tous les modules qui lui sont attribuer puis on inserre
        String[] detail = {entete[0], "Module(s)"};
        try {
            if (utilisateurs != null && utilisateurs.getIdUtilisateur() != null) {
                securiteService.removeModulesToUser(utilisateurs, modulesModel.getSource(), profilsByUser);
                securiteService.addModulesToUser(utilisateurs, modulesModel.getTarget(), profilsByUser);
                StringBuffer msg;

                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));

            }
        } catch (Exception th) {
            //ecrire dans le fichier de log 
            logger.error("erreur lors de linsertion user", th);
            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.error", entete, myLoc);
            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));
        }

        return null;
    }

    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for (Object item : event.getItems()) {
            builder.append(((Object) item).toString()).append("<br />");
        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onSelect(SelectEvent<Object> event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }

    public void onUnselect(UnselectEvent<Object> event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }

    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }

    public String saveUserMenu() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "entite.menu", null, myLoc)};
        StringBuilder menusString = new StringBuilder();
        // on recupere tous les modules qui lui sont attribuer puis on inserre
        try {
            if (utilisateurs != null && utilisateurs.getIdUtilisateur() != null && modules != null && modules.getIdModule() != null && profilsByUser != null && profilsByUser.getIdProfil() != null) {

                if (menusModel.getSource() != null) {
                    for (OrclassFonctionnalites fn : menusModel.getSource()) {
                        securiteService.removeMenutoUser(utilisateurs, modules, profilsByUser, fn);
                    }
                    for (OrclassFonctionnalites menus : menusModel.getTarget()) {
                        securiteService.addMenutoUser(utilisateurs, modules, menus, profilsByUser);
                        menusString.append(menus.getCode()).append("--");
                    }
                }
                String m = "";
                if (menusString.length() >= 2) {
                    m = menusString.substring(0, menusString.length() - 2);
                }
                String[] detail = {entete[0], m};
                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
                ctx.addMessage("msg3", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));

            }
        } catch (Throwable th) {
            //ecrire dans le fichier de log  
            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.error", entete, myLoc);
            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
            ctx.addMessage("msg3", new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));
        }

        return null;
    }

    public String saveUserAction() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "entite.menu", null, myLoc)};
        StringBuilder menusString = new StringBuilder();
        // on recupere tous les modules qui lui sont attribuer puis on inserre
        try {
            if (utilisateurs != null && utilisateurs.getIdUtilisateur() != null && modules != null && modules.getIdModule() != null && profilsByUser != null && profilsByUser.getIdProfil() != null && fonctionnaliteByUser != null && fonctionnaliteByUser.getIdFonctionnalite() != null) {

                if (actionModel.getSource() != null) {
                    for (OrclassActions act : actionModel.getSource()) {
                        securiteService.removeActiontoUser(utilisateurs, modules, profilsByUser, fonctionnaliteByUser, act);
                    }
                    for (OrclassActions a : actionModel.getTarget()) {
                        securiteService.addActiontoUser(utilisateurs, modules, fonctionnaliteByUser, profilsByUser, a);
                        menusString.append(a.getCode()).append("--");
                    }
                }
                String m = "";
                if (menusString.length() >= 2) {
                    m = menusString.substring(0, menusString.length() - 2);
                }
                String[] detail = {entete[0], m};
                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
                ctx.addMessage("msg3", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));

            }
        } catch (Throwable th) {
            //ecrire dans le fichier de log  
            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.error", entete, myLoc);
            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
            ctx.addMessage("msg3", new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));
        }

        return null;
    }

    public void onRowSelect() {
        if (selectedUtiliseur != null) {
            ancienEmail = selectedUtiliseur.getAdresse().getEmail();
            photo = photoDao.getPhotoByPersonne(selectedUtiliseur);
            if (photo == null) {
                this.absolutePathImages = this.defautImage;
                this.photo = new Photo();

            } else {
                FacesContext ctx = FacesContext.getCurrentInstance();
                ExternalContext extContext = ctx.getExternalContext();

                imagePath = extContext.getRealPath("") + photo.getLibelle();
                File dossiers = new File(imagePath);
                if (dossiers.exists() == false) {
//                        String libelle = this.createImageByLibellePhotos(photo.getLibelle(), photo.getImage());
                    GlobalFonctions.createImageByLibellePhotos(photo.getLibelle(), photo.getImage());
                }
                this.absolutePathImages = photo.getLibelle();
            }
            this.setUtilisateurs(selectedUtiliseur);
            if (utilisateurs.getAdresse() == null) {
                utilisateurs.setAdresse(new Adresse());

            }

            colProfil = (List<OrclassProfils>) profilsDao.getAllProfilHaveAccesByEntreprise(societe);
            List<OrclassProfils> colProfilsHaveUser = new ArrayList<>();
            selectedProfils = profilsDao.getProfilByUtilisateu(utilisateurs);
            for (OrclassProfils p : selectedProfils) {
                if (colProfil.contains(p) == false) {
                    colProfil.add(p);
                }
            }
//            if (colProfil.containsAll(colProfilsHaveUser)) {
//                colProfil.removeAll(colProfilsHaveUser);
//            }

        }
    }

    public String getRepresentationByUser(OrclassUtilisateurs u) {

        if (u != null && u.getIdUtilisateur() != null) {
            Photo user = photoDao.getPhotoByPersonne(u);
            if (user == null) {
                return defautImage;

            }
            return user.getLibelle();
        }
        return defautImage;
    }

    public String getRepresentationByUserForHeaderPage() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        OrclassUtilisateurs u = (OrclassUtilisateurs) ctx.getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
        if (u != null && u.getIdUtilisateur() != null) {
            Photo user = photoDao.getPhotoByPersonne(u);
            if (user == null) {
                return defautImage;

            }
            File file = new File("" + user.getLibelle());
            if (file.exists() == Boolean.TRUE) {
                return user.getLibelle();

            } else {
                // on creer le logo à apprtir de l image byte
                GlobalFonctions.createImageByLibellePhotos(user.getLibelle(), user.getImage());
            }
            return user.getLibelle();
        }
        return defautImage;
    }

    public String getlogo() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Societe s = (Societe) ctx.getExternalContext().getSessionMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
        if (s != null && s.getCodesoci() != null && s.getLogosoci() != null) {
            if (!"".equals(s.getChemin_logo()) && s.getChemin_logo() != null) {
                File file = new File("" + s.getChemin_logo());
                if (file.exists() == Boolean.TRUE) {
                    return s.getChemin_logo();

                } else {
                    // on creer le logo à apprtir de l image byte
                    GlobalFonctions.createImageByLibellePhotos(s.getChemin_logo(), s.getLogosoci());
                }

                return s.getChemin_logo();
            }

        }
        return "";
    }

    public String getAbsolutePathImages() {
        return absolutePathImages;
    }

    public void setAbsolutePathImages(String absolutePathImages) {
        this.absolutePathImages = absolutePathImages;
    }

    public OrclassUtilisateurs getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(OrclassUtilisateurs utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public PaysDao getPaysDao() {
        return paysDao;
    }

    public void setPaysDao(PaysDao paysDao) {
        this.paysDao = paysDao;
    }

    public IndicatifPaysDao getIndicatifPaysDao() {
        return indicatifPaysDao;
    }

    public void setIndicatifPaysDao(IndicatifPaysDao indicatifPaysDao) {
        this.indicatifPaysDao = indicatifPaysDao;
    }

    public OrclassProfilsDao getProfilsDao() {
        return profilsDao;
    }

    public void setProfilsDao(OrclassProfilsDao profilsDao) {
        this.profilsDao = profilsDao;
    }

    public List<OrclassFonctionnalites> getColFonctionnaliteByUser() {
        colFonctionnaliteByUser = new ArrayList<OrclassFonctionnalites>();
        if (utilisateurs != null && utilisateurs.getIdUtilisateur() != null && modules != null && modules.getIdModule() != null && profilsByUser != null && profilsByUser.getIdProfil() != null) {
            colFonctionnaliteByUser = (List<OrclassFonctionnalites>) securiteService.getFonctionnaliteByModuleByUserByProfil(modules, utilisateurs, profilsByUser);
        }
        return colFonctionnaliteByUser;
    }

    public void setColFonctionnaliteByUser(List<OrclassFonctionnalites> colFonctionnaliteByUser) {
        this.colFonctionnaliteByUser = colFonctionnaliteByUser;
    }

    public Collection<OrclassModules> getModuleUser() {
//        moduleUser = new ArrayList<OrclassModules>();
//        if (utilisateurs != null && utilisateurs.getIdUtilisateur() != null && profilsByUser != null && profilsByUser.getIdProfil() != null) {
//            moduleUser = securiteService.getModuleByUser(utilisateurs, profilsByUser);
//        }
////        return moduleUser;
////    }
        return moduleUser;
    }

//    public void onTabChange(TabChangeEvent event) {
//        if (event.getTab().getId().equals("tab2")) {
//            if (utilisateurs != null && utilisateurs.getIdUtilisateur() != null && profilsByUser != null && profilsByUser.getIdProfil() != null) {
//                moduleUser = modulesModel.getTarget();
//            }
//        }
//        if (event.getTab().getId().equals("action_tab")) {
//            if (utilisateurs != null && utilisateurs.getIdUtilisateur() != null && profilsByUser != null && profilsByUser.getIdProfil() != null && modules != null && modules.getIdModule() != null) {
//                this.getColFonctionnaliteByUser();
//            }
//        }
//
////        if (utilisateurs != null && utilisateurs.getIdUtilisateur() != null && profilsByUser != null && profilsByUser.getIdProfil() != null) {
////            this.getModuleUser();
////        } else if (utilisateurs != null && utilisateurs.getIdUtilisateur() != null && profilsByUser != null && profilsByUser.getIdProfil() != null && modules != null && modules.getIdModule() != null) {
////           
////        } else {
////            this.getprofilByUser();
////        }
//    }
    public DualListModel<OrclassActions> getActionModel() {
        this.populateAction();
        actionModel = new DualListModel<OrclassActions>(actionSource, actionTarget);

        return actionModel;
    }

    public void setActionModel(DualListModel<OrclassActions> actionModel) {
        this.actionModel = actionModel;
    }

    public DualListModel<OrclassFonctionnalites> getFonctionnaliteModel() {
        return fonctionnaliteModel;
    }

    public void setFonctionnaliteModel(DualListModel<OrclassFonctionnalites> fonctionnaliteModel) {
        this.fonctionnaliteModel = fonctionnaliteModel;
    }

    public OrclassFonctionnalites getFonctionnaliteByUser() {
        return fonctionnaliteByUser;
    }

    public void setFonctionnaliteByUser(OrclassFonctionnalites fonctionnaliteByUser) {
        this.fonctionnaliteByUser = fonctionnaliteByUser;
    }

    public DualListModel<OrclassFonctionnalites> getMenusModel() {
        this.populateMenus();
        menusModel = new DualListModel<OrclassFonctionnalites>(menusSource, menusTarget);

        return menusModel;
    }

    public void populateMenus() {
        menusSource = new ArrayList<>();
        menusTarget = new ArrayList<>();
        Collection<OrclassFonctionnalites> menu1 = new ArrayList<OrclassFonctionnalites>();
        Collection<OrclassFonctionnalites> menu2 = new ArrayList<OrclassFonctionnalites>();
        menusSource.clear();
        menusTarget.clear();

        if (utilisateurs != null && utilisateurs.getIdUtilisateur() != null && modules != null && modules.getIdModule() != null) {
            menu2 = securiteService.getFonctionnalitesesForNotModuleUserProfile(utilisateurs, modules, profilsByUser);

            menu1 = securiteService.getFonctionnaliteByModuleByUserByProfil(modules, utilisateurs, profilsByUser);

            if (menu1 != null || menu1.isEmpty() == false) {
                menusTarget.addAll(menu1);
            }
            if (menu2 != null || menu2.isEmpty() == false) {
                menusSource.addAll(menu2);
            }
        } else {
            menusSource = new ArrayList<>();
            menusTarget = new ArrayList<>();
        }
    }

    public void populateAction() {;
        actionTarget = new ArrayList<>();
        actionSource = new ArrayList<>();
        Collection<OrclassActions> menu1 = new ArrayList<OrclassActions>();
        Collection<OrclassActions> menu2 = new ArrayList<OrclassActions>();
        menusSource.clear();
        menusTarget.clear();

        if (utilisateurs != null && utilisateurs.getIdUtilisateur() != null && modules != null && modules.getIdModule() != null && fonctionnaliteByUser != null && fonctionnaliteByUser.getIdFonctionnalite() != null) {
            menu1 = securiteService.getActionByFonctionnalite(utilisateurs, modules, fonctionnaliteByUser, profilsByUser);

            menu2 = securiteService.getActionNotForUserForFonctionnalite(utilisateurs, modules, fonctionnaliteByUser, profilsByUser);
//            for (OrclassActions act : menu1) {
//                if (menu1.contains(act) == Boolean.TRUE) {
//                    menu1.remove(act);
//                }
//            }

            if (menu1 != null || menu1.isEmpty() == false) {
                actionTarget.addAll(menu1);
            }
            if (menu2 != null || menu2.isEmpty() == false) {
                actionSource.addAll(menu2);
            }
        } else {
            actionSource = new ArrayList<>();
            actionTarget = new ArrayList<>();
        }
    }

    public void setModuleUser(Collection<OrclassModules> moduleUser) {
        this.moduleUser = moduleUser;
    }

    public DualListModel<OrclassModules> getModulesModel() {
        modulesSource = new ArrayList<>();
        modulesTarget = new ArrayList<>();
        Collection<OrclassModules> modul = new ArrayList<>();
        Collection<OrclassModules> modul2 = new ArrayList<>();

        if (utilisateurs.getIdUtilisateur() != null && profilsByUser != null && profilsByUser.getIdProfil() != null) {

            //filtre les modules acquis ou non par ce user
            modulesSource.clear();
            modulesTarget.clear();
            //  gauche
            modul = securiteService.getModuleForNotUser(utilisateurs, profilsByUser);
            //droite
            modul2 = securiteService.getModuleByUser(utilisateurs, profilsByUser);

            if (modul != null || modul.isEmpty() == false) {
                modulesSource.addAll(modul);
            }

            if (modul2 != null || modul2.isEmpty() == false) {
                modulesTarget.addAll(modul2);
            }

        } else {
            modulesSource = new ArrayList<>();
            modulesTarget = new ArrayList<>();
//            modul = securiteService.getAllModules();
//            if (modul != null || modul.isEmpty() == false) {
//                modulesSource.addAll(modul);
//            }
        }

        modulesModel = new DualListModel<>(modulesSource, modulesTarget);
        return modulesModel;
    }

    public void setModulesModel(DualListModel<OrclassModules> modulesModel) {
        this.modulesModel = modulesModel;
    }

    public void setMenusModel(DualListModel<OrclassFonctionnalites> menusModel) {
        this.menusModel = menusModel;
    }

    public List<OrclassModules> getModulesSource() {
        return modulesSource;
    }

    public void setModulesSource(List<OrclassModules> modulesSource) {
        this.modulesSource = modulesSource;
    }

    public List<OrclassModules> getModulesTarget() {
        return modulesTarget;
    }

    public void setModulesTarget(List<OrclassModules> modulesTarget) {
        this.modulesTarget = modulesTarget;
    }

    public DualListModel<String> getEtatModel() {
        return etatModel;
    }

    public void setEtatModel(DualListModel<String> etatModel) {
        this.etatModel = etatModel;
    }

    public List<String> getEtatAcquis() {
        return etatAcquis;
    }

    public void setEtatAcquis(List<String> etatAcquis) {
        this.etatAcquis = etatAcquis;
    }

    public List<String> getEtatNonAcquis() {
        return etatNonAcquis;
    }

    public void setEtatNonAcquis(List<String> etatNonAcquis) {
        this.etatNonAcquis = etatNonAcquis;
    }

    public List<OrclassProfils> getColProfil() {
        return colProfil;
    }

    public void setColProfil(List<OrclassProfils> colProfil) {
        this.colProfil = colProfil;
    }

    public List<OrclassProfils> getSelectedProfils() {
        return selectedProfils;
    }

    public void setSelectedProfils(List<OrclassProfils> selectedProfils) {
        this.selectedProfils = selectedProfils;
    }

    public void onRowSelect(SelectEvent<OrclassUtilisateurs> event) {
        FacesMessage msg = new FacesMessage("user Selected", String.valueOf(event.getObject().getIdUtilisateur()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<OrclassFonctionnalites> getMenusSource() {
        return menusSource;
    }

    public void setMenusSource(List<OrclassFonctionnalites> menusSource) {
        this.menusSource = menusSource;
    }

    public List<OrclassFonctionnalites> getMenusTarget() {
        return menusTarget;
    }

    public void setMenusTarget(List<OrclassFonctionnalites> menusTarget) {
        this.menusTarget = menusTarget;
    }

    public LazyDataModel<OrclassUtilisateurs> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<OrclassUtilisateurs> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public OrclassUtilisateurs getSelectedUtiliseur() {
        return selectedUtiliseur;
    }

    public void setSelectedUtiliseur(OrclassUtilisateurs selectedUtiliseur) {
        this.selectedUtiliseur = selectedUtiliseur;
    }

    public List<OrclassUtilisateurs> getDatasource() {
        return datasource;
    }

    public void setDatasource(List<OrclassUtilisateurs> datasource) {
        this.datasource = datasource;
    }

    public List<OrclassUtilisateurs> getFilteredUtilisateur() {
        return filteredUtilisateur;
    }

    public void setFilteredUtilisateur(List<OrclassUtilisateurs> filteredUtilisateur) {
        this.filteredUtilisateur = filteredUtilisateur;
    }

    public OrclassModules getModules() {
        return modules;
    }

    public void setModules(OrclassModules modules) {
        this.modules = modules;
    }

    public OrclassProfils getProfilsByUser() {
        return profilsByUser;
    }

    public void setProfilsByUser(OrclassProfils profilsByUser) {
        this.profilsByUser = profilsByUser;
    }

    public List<IndicatifPays> getListeIndicatifPays() {
        return listeIndicatifPays;
    }

    public void setListeIndicatifPays(List<IndicatifPays> listeIndicatifPays) {
        this.listeIndicatifPays = listeIndicatifPays;
    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) {
        this.cle = cle;
    }

    public String getCle_securite() {
        return cle_securite;
    }

    public void setCle_securite(String cle_securite) {
        this.cle_securite = cle_securite;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
