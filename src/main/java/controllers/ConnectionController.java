/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Licence.KeyGenCode;
import dao.ConnectionLoggerDao;
import dao.LicenceDao;
import dao.MoisDao;
import dao.OrclassEntreprisesDao;
import dao.OrclassUtilisateursDao;

import dao.OrclssMailInscriptionDao;
import dao.PersonneDao;
import dao.SocieteDao;
import droitAcces.IDroitAcces;
import enums.TypeUtilisateur;
import exception.LicenceException;
import exception.Success;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import mdb.Mail;
import modele.Adresse;
import modele.OrclassEntreprises;
import modele.OrclassMenus;
import modele.OrclassUtilisateurs;

import modele.OrclssMailInscription;
import modele.Societe;
import org.primefaces.PrimeFaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parametrage.IInitializeDB;
import parametrage.IParamModule;
import parametrage.ISecurite;
import parametrage.ModuleMenu;
import utils.Connection;
import utils.Crypto;
import utils.EsphereMail;
import utils.GlobalFonctions;
import utils.IdleDate;
import utils.InternetAvailabilitiChecker;
import utils.LocaleHelper;
import utils.RecupBundle;

/**
 *
 * @author hp
 */
@Named(value = "connectionController")
@SessionScoped
public class ConnectionController implements Serializable {
    
    private static final Logger logger = LoggerFactory.getLogger(ConnectionController.class);
    String summary = "";
    String msgdetail = "";
//    @ManagedProperty(value = "#{loginManager.users}")
//    private Set<Personne> usersConnectes;
    @EJB
    private ISecurite securiteUser;
    @EJB
    IInitializeDB initDB;
    @Inject
    CurrentInstance currentInstance;
    
    @EJB
    private SocieteDao societeDao;
    @EJB
    LicenceDao licenceDao;
    @EJB
    IParamModule paramModule;
    @EJB
    PersonneDao personneDao;
    @Inject
    LanguageController languageController;
    @EJB
    OrclassUtilisateursDao utilisateursDao;
    @EJB
    IDroitAcces droitAccesService;
    @EJB
    ConnectionLoggerDao connectionLoggerDao;
    
    @EJB
    OrclssMailInscriptionDao mailInscriptionDao;
    private Boolean showModuleAmin = Boolean.FALSE;
    private Boolean showModuleAssureIrd = Boolean.FALSE;
    private Boolean showModuleAssureAdp = Boolean.FALSE;
    private Boolean showModuleAssureAuto = Boolean.FALSE;
    private Boolean showModuleTransport = Boolean.FALSE;
    private Boolean showModuleCautionCredit = Boolean.FALSE;
    private Boolean showModuleAgricolte = Boolean.FALSE;
    private Boolean showModuleGestionAssure = Boolean.FALSE;
    private Boolean showModuleComptabiliteGeneral = Boolean.FALSE;
    private Boolean showModuleComptabiliteAgence = Boolean.FALSE;
    private Boolean showModuleReassurance = Boolean.FALSE;
    private Boolean showModuleReporting = Boolean.FALSE;
    private Boolean showModuleParametrage = Boolean.FALSE;
    //variable pour afficher les menus  administration pour un utilisateur precis
    private Boolean showMenuConfiguration = Boolean.FALSE;
    private Boolean showMenuProfilUtilisateur = Boolean.FALSE;
    private Boolean showMenuDroitAccess = Boolean.FALSE;
    private Boolean showMenuUtilisateur = Boolean.FALSE;
    private Boolean showMenuIntermediaire = Boolean.FALSE;
    private Boolean showMenuClasse = Boolean.FALSE;
    private Boolean showMenuBrance = Boolean.FALSE;
    private Boolean showMenuCategorie = Boolean.FALSE;
    private Boolean showMenuOptinEntreprise = Boolean.FALSE;
    private Boolean showMenuEntreprise = Boolean.FALSE;
    private Boolean showMenuCaracteristique = Boolean.FALSE;
    private Boolean showMenuRelation = Boolean.FALSE;
    private Boolean showMenuReference = Boolean.FALSE;
    private Boolean showMenuApporteur = Boolean.FALSE;
    private Boolean showMenuIntermediairePrime = Boolean.FALSE;
    private Boolean showMenuGarantieCategorie = Boolean.FALSE;
    private Boolean showMenuGarantieCaracteristique = Boolean.FALSE;
    private Boolean showMenuGarantieReference = Boolean.FALSE;
    private Boolean showMenuPrestation = Boolean.FALSE;
    private Boolean showMenuRubrique = Boolean.FALSE;
    private Boolean showMenuRubriqueCategorie = Boolean.FALSE;
    private Boolean showMenuRubriquePrestation = Boolean.FALSE;
    private Boolean showMenuRegistreProduction = Boolean.FALSE;
    private Boolean showMenuRegistrationSinistre = Boolean.FALSE;
    private Boolean showMenuInitialisationRegistre = Boolean.FALSE;
    private Boolean showMenuDuree = Boolean.FALSE;
    private Boolean showMenuTypeCaracteristique = Boolean.FALSE;
    private Boolean showMenuExoneration = Boolean.FALSE;
    private Boolean showMenuFraction = Boolean.FALSE;
    private Boolean showReduction = Boolean.FALSE;
    private Boolean showMenuPeriodicite = Boolean.FALSE;
    private Boolean showMenuPlafondMaladie = Boolean.FALSE;
    private Boolean showMenuAffaireNouvelle = Boolean.FALSE;
    private Boolean showMenuAffaireNouvelle_auto = Boolean.FALSE;
    private Boolean showMenuAvenant_auto = Boolean.FALSE;
    private Boolean showMenuEncaissement_auto = Boolean.FALSE;
    private Boolean showMenuAvenant = Boolean.FALSE;
    private Boolean showMenuEchaeance = Boolean.FALSE;
    private Boolean showMenuEncaissement = Boolean.FALSE;
    private Boolean showMenuVersement = Boolean.FALSE;
    private Boolean showMenuTarif = Boolean.FALSE;
    private Boolean showMenuTypeAvenant = Boolean.FALSE;
    private Boolean showMenuAccessAvenant = Boolean.FALSE;
    private Boolean showMenuAgenceMentAvenant = Boolean.FALSE;
    private Boolean showMenuPriseEncharge = Boolean.FALSE;
    private Boolean showMenuRemboursementMaladie = Boolean.FALSE;
    private Boolean showMenuFactureTier = Boolean.FALSE;
    private Boolean showMenuTaxe = Boolean.FALSE;
    private Boolean showMenuRegimeTaxe = Boolean.FALSE;
    private Boolean showMenuTaxePrime = Boolean.FALSE;
    private Boolean showMenuTypeDocument = Boolean.FALSE;
    private Boolean showMenuDocumentCategorie = Boolean.FALSE;
    private Boolean showMenuDocumentStock = Boolean.FALSE;
    private Boolean showMenuTimbreGradue = Boolean.FALSE;
    private String cle_securite;
    private Connection con;
    private OrclssMailInscription mailInscription;
    
    Collection<OrclassMenus> menusUser = new ArrayList<>();

//    ConnectionController connect;
//    @EJB
//    private IServicesAuto servicesAuto;
    @EJB
    private MoisDao moisDao;
    
    private OrclassUtilisateurs user;
    private int teste = 0;
    private int boucle = 0;
//    private Etablissements etablissements;
    private boolean isLoggedIn = false;
    private String currenPage;
    private Societe societe;
    public final static int GO_TO_LICENCE = 0;
    public final static int GO_TO_CONNECT = 1;
    public final static int GO_TO_CONFIG = 2;
    public final static int GO_TO_PAGE = 3;
    public final static int LICENCE_BAD_DATE_SERVER = 4;
    private Boolean afficheLogo = Boolean.FALSE;
    private String navigator = "principal";
    private String navigator_affaire_nouvelle = "affaire.nouvelle";
//    BackupMysql backupMysql;
//    String afficheLogoPaie = "resources/images/GP.png";
    private String currentFolder = "/photos";
    private String imagePath;
    private String date;
    int choixModule = 0;// à 0 aucun choix n est effectuer
    Boolean administration, assurance_ird, transport, caution_credit, agricolte, assurance_adp, gestion_assure, compta_general, assurance_auto = Boolean.FALSE;
    String oldPassWord;
    String newPassWord;
    Boolean affiche_home_acceuil_admin_system, affiche_home_acceuil_user = Boolean.FALSE;
    Boolean compta_agence, reassurance, reporting = Boolean.FALSE, paramettre = Boolean.FALSE;
    Boolean firstConnection = Boolean.FALSE;
    Boolean showMenuAffaireNouvelleReport = Boolean.FALSE;
    Boolean shwomenuParametrageForUserSysteme = Boolean.FALSE;
    int choixUserSysteme = 0;

    //permet d'eviter l'expiration illegal d'une session
    @PostConstruct
    void initialiseSession() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        backupMysql = new BackupMysql();

    }
    
    public void idleListener() throws Exception {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Votre session est ferme", "Votre session a expire veuillez vous reconnecter"));
        this.deconnexion();
        //invalidate session  
    }
    
    public ConnectionController() {
        user = new OrclassUtilisateurs();
        
    }
    
    public String optionChoixUserSysteme(int choix) {
        this.setChoixUserSysteme(choix);
        switch (choixUserSysteme) {
            case 1: // iici l utilisateur a clique r sur le module parametrage du cote Systeme
                this.setShwomenuParametrageForUserSysteme(Boolean.TRUE);
                break;
            
            default:
                this.setShwomenuParametrageForUserSysteme(Boolean.FALSE);
                break;
        }
        return navigator;
    }
    
    public String optionChoix(int choix) {
        
        if (Objects.equals(firstConnection, Boolean.TRUE)) {
//            if (Objects.equals(firstConnection, Boolean.TRUE)) {
            PrimeFaces.current().executeScript("PF('pass').show();");
//            }
            return "";
        }
        this.setChoixModule(choix);
        switch (choixModule) {
            case 1: // le choix est sur le module administration cest à dire que l utilisateur est cliquer sur le module administartion
                this.setAdministration(Boolean.TRUE);
                this.setAgricolte(Boolean.FALSE);
                this.setAssurance_adp(Boolean.FALSE);
                this.setAssurance_auto(Boolean.FALSE);
                this.setAssurance_ird(Boolean.FALSE);
                this.setCaution_credit(Boolean.FALSE);
                this.setCompta_general(Boolean.FALSE);
                this.setTransport(Boolean.FALSE);
                this.setGestion_assure(Boolean.FALSE);
                //permettre d afficher le retour precis d un untilisateur
                this.setAffiche_home_acceuil_user(Boolean.TRUE);
                this.setAffiche_home_acceuil_admin_system(Boolean.FALSE);
                this.setCompta_agence(Boolean.FALSE);
                this.setReassurance(Boolean.FALSE);
                this.setReporting(Boolean.FALSE);
                this.setParamettre(Boolean.FALSE);
                break;
            case 2:// le choix est sur le module assurance irp cest à dire que l utilisateur est cliquer sur le module assurance irp
                this.setAdministration(Boolean.FALSE);
                this.setAgricolte(Boolean.FALSE);
                this.setAssurance_adp(Boolean.FALSE);
                this.setAssurance_auto(Boolean.FALSE);
                this.setAssurance_ird(Boolean.TRUE);
                this.setCaution_credit(Boolean.FALSE);
                this.setCompta_general(Boolean.FALSE);
                this.setTransport(Boolean.FALSE);
                this.setGestion_assure(Boolean.FALSE);
                //permettre d afficher le retour precis d un untilisateur
                this.setAffiche_home_acceuil_user(Boolean.TRUE);
                this.setAffiche_home_acceuil_admin_system(Boolean.FALSE);
                this.setCompta_agence(Boolean.FALSE);
                this.setReassurance(Boolean.FALSE);
                this.setReporting(Boolean.FALSE);
                this.setParamettre(Boolean.FALSE);
                break;
            
            case 3: // le choix est sur le module transport cest à dire que l utilisateur est cliquer sur le module transport
                this.setAdministration(Boolean.FALSE);
                this.setAgricolte(Boolean.FALSE);
                this.setAssurance_adp(Boolean.FALSE);
                this.setAssurance_auto(Boolean.FALSE);
                this.setAssurance_ird(Boolean.FALSE);
                this.setCaution_credit(Boolean.FALSE);
                this.setCompta_general(Boolean.FALSE);
                this.setTransport(Boolean.TRUE);
                this.setGestion_assure(Boolean.FALSE);
                //permettre d afficher le retour precis d un untilisateur
                this.setAffiche_home_acceuil_user(Boolean.TRUE);
                this.setAffiche_home_acceuil_admin_system(Boolean.FALSE);
                this.setCompta_agence(Boolean.FALSE);
                this.setReassurance(Boolean.FALSE);
                this.setReporting(Boolean.FALSE);
                this.setParamettre(Boolean.FALSE);
                break;
            case 4: // le choix est sur le module caution et credit cest à dire que l utilisateur est cliquer sur le module caution et credit
                this.setAdministration(Boolean.FALSE);
                this.setAgricolte(Boolean.FALSE);
                this.setAssurance_adp(Boolean.FALSE);
                this.setAssurance_auto(Boolean.FALSE);
                this.setAssurance_ird(Boolean.FALSE);
                this.setCaution_credit(Boolean.TRUE);
                this.setCompta_general(Boolean.FALSE);
                this.setTransport(Boolean.FALSE);
                this.setGestion_assure(Boolean.FALSE);
                //permettre d afficher le retour precis d un untilisateur
                this.setAffiche_home_acceuil_user(Boolean.TRUE);
                this.setAffiche_home_acceuil_admin_system(Boolean.FALSE);
                this.setCompta_agence(Boolean.FALSE);
                this.setReassurance(Boolean.FALSE);
                this.setReporting(Boolean.FALSE);
                this.setParamettre(Boolean.FALSE);
                break;
            case 5: // le choix est sur le module agricolte cest à dire que l utilisateur est cliquer sur le module agricolte
                this.setAdministration(Boolean.FALSE);
                this.setAgricolte(Boolean.TRUE);
                this.setAssurance_adp(Boolean.FALSE);
                this.setAssurance_auto(Boolean.FALSE);
                this.setAssurance_ird(Boolean.FALSE);
                this.setCaution_credit(Boolean.FALSE);
                this.setCompta_general(Boolean.FALSE);
                this.setTransport(Boolean.FALSE);
                this.setGestion_assure(Boolean.FALSE);
                //permettre d afficher le retour precis d un untilisateur
                this.setAffiche_home_acceuil_user(Boolean.TRUE);
                this.setAffiche_home_acceuil_admin_system(Boolean.FALSE);
                this.setCompta_agence(Boolean.FALSE);
                this.setReassurance(Boolean.FALSE);
                this.setReporting(Boolean.FALSE);
                this.setParamettre(Boolean.FALSE);
                break;
            case 6: // le choix est sur le module assurance adp  cest à dire que l utilisateur est cliquer sur le module assurance adp
                this.setAdministration(Boolean.FALSE);
                this.setAgricolte(Boolean.FALSE);
                this.setAssurance_adp(Boolean.TRUE);
                this.setAssurance_auto(Boolean.FALSE);
                this.setAssurance_ird(Boolean.FALSE);
                this.setCaution_credit(Boolean.FALSE);
                this.setCompta_general(Boolean.FALSE);
                this.setTransport(Boolean.FALSE);
                this.setGestion_assure(Boolean.FALSE);
                //permettre d afficher le retour precis d un untilisateur
                this.setAffiche_home_acceuil_user(Boolean.TRUE);
                this.setAffiche_home_acceuil_admin_system(Boolean.FALSE);
                this.setCompta_agence(Boolean.FALSE);
                this.setReassurance(Boolean.FALSE);
                this.setReporting(Boolean.FALSE);
                this.setParamettre(Boolean.FALSE);
                break;
            
            case 7: // le choix est sur le module gestion assurer  cest à dire que l utilisateur est cliquer sur le module gestion assurer
                this.setAdministration(Boolean.FALSE);
                this.setAgricolte(Boolean.FALSE);
                this.setAssurance_adp(Boolean.FALSE);
                this.setAssurance_auto(Boolean.FALSE);
                this.setAssurance_ird(Boolean.FALSE);
                this.setCaution_credit(Boolean.FALSE);
                this.setCompta_general(Boolean.FALSE);
                this.setTransport(Boolean.FALSE);
                this.setGestion_assure(Boolean.TRUE);
                //permettre d afficher le retour precis d un untilisateur
                this.setAffiche_home_acceuil_user(Boolean.TRUE);
                this.setAffiche_home_acceuil_admin_system(Boolean.FALSE);
                this.setCompta_agence(Boolean.FALSE);
                this.setReassurance(Boolean.FALSE);
                this.setReporting(Boolean.FALSE);
                this.setParamettre(Boolean.FALSE);
                break;
            case 8: // le choix est sur le module Comptabilite general et credit cest à dire que l utilisateur est cliquer sur le module Comptabilite general
                this.setAdministration(Boolean.FALSE);
                this.setAgricolte(Boolean.FALSE);
                this.setAssurance_adp(Boolean.FALSE);
                this.setAssurance_auto(Boolean.FALSE);
                this.setAssurance_ird(Boolean.FALSE);
                this.setCaution_credit(Boolean.FALSE);
                this.setCompta_general(Boolean.TRUE);
                this.setTransport(Boolean.FALSE);
                this.setGestion_assure(Boolean.FALSE);
                //permettre d afficher le retour precis d un untilisateur
                this.setAffiche_home_acceuil_user(Boolean.TRUE);
                this.setAffiche_home_acceuil_admin_system(Boolean.FALSE);
                this.setCompta_agence(Boolean.FALSE);
                this.setReassurance(Boolean.FALSE);
                this.setReporting(Boolean.FALSE);
                this.setParamettre(Boolean.FALSE);
                break;
            
            case 9:// le choix est sur le module assurance auto et credit cest à dire que l utilisateur est cliquer sur le module assurance
                this.setAdministration(Boolean.FALSE);
                this.setAgricolte(Boolean.FALSE);
                this.setAssurance_adp(Boolean.FALSE);
                this.setAssurance_auto(Boolean.TRUE);
                this.setAssurance_ird(Boolean.FALSE);
                this.setCaution_credit(Boolean.FALSE);
                this.setCompta_general(Boolean.FALSE);
                this.setTransport(Boolean.FALSE);
                this.setGestion_assure(Boolean.FALSE);
                //permettre d afficher le retour precis d un untilisateur
                this.setAffiche_home_acceuil_user(Boolean.TRUE);
                this.setAffiche_home_acceuil_admin_system(Boolean.FALSE);
                this.setCompta_agence(Boolean.FALSE);
                this.setReassurance(Boolean.FALSE);
                this.setReporting(Boolean.FALSE);
                this.setParamettre(Boolean.FALSE);
                break;
            case 10:// le choix est sur le module comptabilite agence cest à dire que l utilisateur est cliquer 
                this.setAdministration(Boolean.FALSE);
                this.setAgricolte(Boolean.FALSE);
                this.setAssurance_adp(Boolean.FALSE);
                this.setAssurance_auto(Boolean.FALSE);
                this.setAssurance_ird(Boolean.FALSE);
                this.setCaution_credit(Boolean.FALSE);
                this.setCompta_general(Boolean.FALSE);
                this.setTransport(Boolean.FALSE);
                this.setGestion_assure(Boolean.FALSE);
                //permettre d afficher le retour precis d un untilisateur
                this.setAffiche_home_acceuil_user(Boolean.TRUE);
                this.setAffiche_home_acceuil_admin_system(Boolean.FALSE);
                this.setCompta_agence(Boolean.TRUE);
                this.setReassurance(Boolean.FALSE);
                this.setReporting(Boolean.FALSE);
                this.setParamettre(Boolean.FALSE);
                break;
            case 11:// le choix est sur le module Reassurance cest à dire que l utilisateur est cliquer 
                this.setAdministration(Boolean.FALSE);
                this.setAgricolte(Boolean.FALSE);
                this.setAssurance_adp(Boolean.FALSE);
                this.setAssurance_auto(Boolean.FALSE);
                this.setAssurance_ird(Boolean.FALSE);
                this.setCaution_credit(Boolean.FALSE);
                this.setCompta_general(Boolean.FALSE);
                this.setTransport(Boolean.FALSE);
                this.setGestion_assure(Boolean.FALSE);
                //permettre d afficher le retour precis d un untilisateur
                this.setAffiche_home_acceuil_user(Boolean.TRUE);
                this.setAffiche_home_acceuil_admin_system(Boolean.FALSE);
                this.setCompta_agence(Boolean.FALSE);
                this.setReassurance(Boolean.TRUE);
                this.setReporting(Boolean.FALSE);
                this.setParamettre(Boolean.FALSE);
                break;
            case 12:// le choix est sur le module reporting cest à dire que l utilisateur est cliquer 
                this.setAdministration(Boolean.FALSE);
                this.setAgricolte(Boolean.FALSE);
                this.setAssurance_adp(Boolean.FALSE);
                this.setAssurance_auto(Boolean.FALSE);
                this.setAssurance_ird(Boolean.FALSE);
                this.setCaution_credit(Boolean.FALSE);
                this.setCompta_general(Boolean.FALSE);
                this.setTransport(Boolean.FALSE);
                this.setGestion_assure(Boolean.FALSE);
                //permettre d afficher le retour precis d un untilisateur
                this.setAffiche_home_acceuil_user(Boolean.TRUE);
                this.setAffiche_home_acceuil_admin_system(Boolean.FALSE);
                this.setCompta_agence(Boolean.FALSE);
                this.setReassurance(Boolean.FALSE);
                this.setParamettre(Boolean.FALSE);
                this.setReporting(Boolean.TRUE);
                break;
            case 13:// le choix est sur le module paramettre 
                this.setAdministration(Boolean.FALSE);
                this.setAgricolte(Boolean.FALSE);
                this.setAssurance_adp(Boolean.FALSE);
                this.setAssurance_auto(Boolean.FALSE);
                this.setAssurance_ird(Boolean.FALSE);
                this.setCaution_credit(Boolean.FALSE);
                this.setCompta_general(Boolean.FALSE);
                this.setTransport(Boolean.FALSE);
                this.setGestion_assure(Boolean.FALSE);
                //permettre d afficher le retour precis d un untilisateur
                this.setAffiche_home_acceuil_user(Boolean.TRUE);
                this.setAffiche_home_acceuil_admin_system(Boolean.FALSE);
                this.setCompta_agence(Boolean.FALSE);
                this.setReassurance(Boolean.FALSE);
                this.setReporting(Boolean.FALSE);
                this.setParamettre(Boolean.TRUE);
                break;
            case 0:
                this.setAdministration(Boolean.FALSE);
                this.setAgricolte(Boolean.FALSE);
                this.setAssurance_adp(Boolean.FALSE);
                this.setAssurance_auto(Boolean.FALSE);
                this.setAssurance_ird(Boolean.FALSE);
                this.setCaution_credit(Boolean.FALSE);
                this.setCompta_general(Boolean.FALSE);
                this.setTransport(Boolean.FALSE);
                this.setGestion_assure(Boolean.FALSE);
                //permettre d afficher le retour precis d un untilisateur
                this.setAffiche_home_acceuil_user(Boolean.FALSE);
                this.setAffiche_home_acceuil_admin_system(Boolean.FALSE);
                this.setCompta_agence(Boolean.FALSE);
                this.setReassurance(Boolean.FALSE);
                this.setReporting(Boolean.FALSE);
                this.setParamettre(Boolean.FALSE);
                break;
            
        }
        if (choixModule == 0) {
            return "";
        }
        
        return navigator;
        
    }
    
    public void verifyContrainstBeforeToOpenPage(ComponentSystemEvent event) {
        //recupere le chemin de l'application
        ExternalContext etx = FacesContext.getCurrentInstance().getExternalContext();
        String chemin = etx.getRequestContextPath();
        OrclassUtilisateurs u = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
        //traval de la licence
        //        if (((int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.LICENCE_STATUT) == LicenceDao.LICENCE_GOOD) && licence != null) {
        if (boucle == 0) {
            boucle++;
            Collection<Societe> colEtab = societeDao.findAll();
            if ((colEtab == null || colEtab.isEmpty())) {
                if (isLoggedIn == true) {
                    //Recuperation des parametres de la session pour s assurer que c est la config
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(GlobalFonctions.SESSION_CONFIG, 1);
                    u.setStatut(GO_TO_PAGE);
//                    return GO_TO_CONFIG;
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(GlobalFonctions.SESSION_CONFIG, 0);
                    u.setStatut(GO_TO_CONNECT);

//                    return GO_TO_CONNECT;
                }
            } else if (societe != null && isLoggedIn == true) {

                //on est loge
                securiteUser.insertUserInLogger(u);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(GlobalFonctions.SESSION_CONFIG, 2);
                u.setStatut(GO_TO_PAGE);

//                return GO_TO_PAGE;
            } else {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(GlobalFonctions.SESSION_CONFIG, 0);
//                return GO_TO_CONNECT;
                u.setStatut(GO_TO_PAGE);
                
            }
        } else {
            u.setStatut(GO_TO_PAGE);
        }
//        }

//        return GO_TO_PAGE;
        u.setJ(u.getStatut());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(GlobalFonctions.SESSION_USER, u);
        
    }
    
    public String changePassWord() throws Exception {
        String cle_securite;
        String passeword;
        FacesContext ctx = FacesContext.getCurrentInstance();
        Locale myLoc = ctx.getViewRoot().getLocale();
        if (user != null && user.getAdresse() != null && user.getAdresse().getEmail() != null) {
            OrclassUtilisateurs utilisateurs = utilisateursDao.emailExiste(user.getAdresse().getEmail().trim());
            
            if (utilisateurs == null) {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "EMAIL NON EXISTANT..", "Saisir votre mail..."));
                
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("EMAIL NON EXISTANT..", "Saisir votre mail..."));
                return null;
            } else if (utilisateurs.getTypeUtilisateur().equals(TypeUtilisateur.UTILISATEUR_ENTITE)) {
                cle_securite = this.createCleSecuriteForUser(utilisateurs.getIdSociete());
                passeword = this.createPassWordForUser(utilisateurs.getIdSociete());
                this.sendInfosConnectionForMail(utilisateurs, passeword, cle_securite);
            } else {
                cle_securite = this.createCleSecuriteForUser(null);
                passeword = this.createPassWordForUser(null);
                this.sendInfosConnectionForMail(utilisateurs, passeword, cle_securite);
            }
            
        }
        return deconnexion();
    }
    
    public void sendInfosConnectionForMail(OrclassUtilisateurs utilisateurs, String password, String cle) throws Exception {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Locale myLoc = ctx.getViewRoot().getLocale();
        Mail mail = null;
        boolean test = false;
        String subject = "";
        String body = "";
        
        Adresse adresse = user.getAdresse();
        con = new Connection();
        
        if (adresse != null && adresse.getEmail() != null) {
            subject = "E-SPHERE: COMPTE CREE AVEC SUCCESS/PARAMETRE DE CONNEXION ";
            
            if (con.isHostServiceAvailable() == Boolean.TRUE && InternetAvailabilitiChecker.isInternetAvailable() == Boolean.TRUE) {
                mailInscription = new OrclssMailInscription(adresse.getEmail().trim(), subject, body(utilisateurs, password, cle));
                mailInscriptionDao.create(mailInscription);
                con.send(mailInscription);
                test = Boolean.TRUE;
            } else {
                test = Boolean.FALSE;
            }
        }
        
        subject = "";
        
        if (test) {
            utilisateurs.setPassword(Crypto.sha256(password.toUpperCase()));
            utilisateurs.setCle_securite(Crypto.sha256(cle.toUpperCase()));
            utilisateurs.setMailEnvoye(Boolean.TRUE);
            utilisateursDao.update(utilisateurs);
            System.out.println("envoit mails terminer");
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Success.OPERATION_SUCCESS.toString(), null));
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("NOTIFICATION..." + user.getAdresse().getEmail(), "LES INFORMATIONS DU COMPTE D'UTILISATEUR ONT ETE ENVOYÉ DANS SA BOITE MAIL "));
            this.reset();
//            PrimeFaces.current().ajax().update(":form1");

        } else {
            utilisateurs.setPassword(Crypto.sha256(password.toUpperCase()));
            utilisateurs.setCle_securite(Crypto.sha256(cle.toUpperCase()));
            utilisateurs.setMailEnvoye(Boolean.FALSE);
            utilisateursDao.update(utilisateurs);
//            login = utilisateurs.getLogin();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, exception.Error.OPERATION_FAILED.name(), ""));
            PrimeFaces.current().ajax().update(":form2");
            PrimeFaces.current().executeScript("PF('motPasseDialog').show()");
            
        }
    }
    
    public String body(OrclassUtilisateurs user, String passWord, String cle) {
        
        String prenom;
        
        if (user.getPrenom() != null) {
            prenom = user.getPrenom();
        } else {
            prenom = "";
        }
        
        String contenuMail = "Bonjour " + user.getNom().toUpperCase() + " " + prenom.toUpperCase() + ". \n VOTRE COMPTE/LOGIN : " + user.getLogin().toUpperCase() + ". \n VOTRE MOT DE PASS/PASSWORD : " + passWord.toUpperCase() + ". \n VOTRE CLE DE SECURITE/SECURITY KEY : " + cle;
        
        contenuMail = contenuMail + "\n ";
        contenuMail = contenuMail + "\n  ";
        String hostname;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
        } catch (Exception e) {
            hostname = "IP Host";
        }
        contenuMail = contenuMail + "\n CORDIALEMENT L EQUIPE E-SPHERE  " + "\n LIEN DE CONNECTION/CONNECTION LINK : " + "http://" + "" + hostname + ":8181" + cheminApplication();
        System.out.println("contenu mail: " + contenuMail);
        return contenuMail;
    }
    
    public String createCleSecuriteForUser(Societe s) {
//        user.setCle_securite(Crypto.sha256("141087"));// la cle de securite c est 6chiffre important lors de la mise a jour d un mot de passe

        String cle = KeyGenCode.gen();
//        OrclassEntreprises e = entrepriseDao.finkey(e.getCode());
        if (cle != null) {
            //verification si cette cle a ete deja generer  
            while (Objects.equals(utilisateursDao.cleSecuriteExiste(cle, s), Boolean.TRUE)) {
                cle = KeyGenCode.gen();
            }
        }
        return cle;
    }
    
    public String createPassWordForUser(Societe s) {
//        user.setCle_securite(Crypto.sha256("141087"));// la cle de securite c est 6chiffre important lors de la mise a jour d un mot de passe

        String password = KeyGenCode.genPassWord();
//        OrclassEntreprises e = entrepriseDao.finkey(entreprise.getCode());
        if (password != null && s != null && s.getCodesoci() != null) {
            //verification si cette cle a ete deja generer  
            while (Objects.equals(utilisateursDao.passWordExiste(password, s), Boolean.TRUE)) {
                password = KeyGenCode.genPassWord();
            }
        }
        return password;
    }
    
    public String updatePassWord() {
        FacesContext ctx = FacesContext.getCurrentInstance();
//        this.setOldPassWord(Crypto);

        if (user != null && user.getIdUtilisateur() != null && user.getPassword().equals(Crypto.sha256(oldPassWord.toUpperCase())) == Boolean.TRUE) {
            System.err.println("cle securite enregistre :" + user.getCle_securite());
            System.err.println("cle securite teste :" + Crypto.sha256(cle_securite.toUpperCase()));
            if (!user.getCle_securite().equals(Crypto.sha256(cle_securite.toUpperCase()))) {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "THE SECURITY KEY IS NOT VALID... AUTHORIZATION DENIED", exception.Error.OPERATION_FAILED.name()));
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE SECURITY KEY IS NOT VALID...", "AUTHORIZATION DENIED"));
                return null;
            }
            
            if (user.getCle_securite() == null) {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "THE SECURITY KEY HAS NOT BEEN CREATED... CONSULT YOUR ADMINISTRATOR", exception.Error.OPERATION_FAILED.name()));
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE SECURITY KEY HAS NOT BEEN CREATED...", "CONSULT YOUR ADMINISTRATOR"));
                
                return null;
            }
            String passWord = user.getPassword();
            user.setPassword(Crypto.sha256(newPassWord.toUpperCase()));
            utilisateursDao.update(user);
            
            init();
            this.firstConnection = Boolean.FALSE;
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, exception.Success.OPERATION_SUCCESS.toString(), exception.Success.UPDATE_SUCCESS.toString()));
//            this.doRedirect("client.deconnect");kjkkkkl
            return deconnexion();
        } else if (user == null || user.getIdUtilisateur() == null) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "USER DO NOT EXIST PLEASE TRY AGAINST", "OPERATION FAILED"));
        } else if (user.getPassword().equals(Crypto.sha256(oldPassWord.toUpperCase())) == Boolean.FALSE) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, " OLD PASSWORD NOT CORRECT  PLEASE TRY AGAINST", exception.Error.OPERATION_FAILED.name()));
        }
        return null;
    }
    
    public void init() {
//        user = new OrclassUtilisateurs();
        oldPassWord = "";
        newPassWord = "";
    }
    
    public void reset() {
        this.init();
        this.currentInstance.getAjaxRequestBuilder().reset();
//        RequestContext.getCurrentInstance().reset(":form1");
    }
    
    public Boolean afficherNameForEntity() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        OrclassUtilisateurs u = null;
        if (isLoggedIn == Boolean.FALSE) {
            return Boolean.FALSE;
        }
        
        if (isLoggedIn == Boolean.TRUE) {
            u = (OrclassUtilisateurs) ctx.getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
            if (u != null && u.getIdUtilisateur() != null) {
                if (u.getTypeUtilisateur().equals(TypeUtilisateur.UTILISATEUR_ENTITE)) {
                    if (this.societe.getCodesoci() == null) {
                        return Boolean.FALSE;
                    }
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }
        }
        return Boolean.FALSE;
    }

    // Programme de deconnexion   
    public String deconnexion() {

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
        
        this.setIsLoggedIn(false);
        firstConnection = Boolean.FALSE;
        this.optionChoix(0);
        
        return navigateTo;
    }
    
    private void doRedirect(String url) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void doRedirectMenu() {
        try {
            String path = this.cheminApplication() + "/parametrage/prestations/reference.xhtml";
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String login() {
        //default url in case of login failure;  
        String url = "connection.xhtml";
        FacesContext ctx = FacesContext.getCurrentInstance();
        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        String chemin = ctx.getExternalContext().getRequestContextPath();
        //Locale myLoc =new Locale("fr");
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "entite.connection", null, myLoc)};
        try {
            //Existance de l'utilisateur 
//            String mp=user.getPwd();
//            user.setPwd(Crypto.sha256(mp));

            securiteUser.checkDateConnexionWithLastConnexion(new Date());
//            connectionLoggerDao.checkDateConnexionWithLastConnexion(new Date());
            OrclassUtilisateurs u = securiteUser.getAuthentification(user);
            firstConnection = Boolean.FALSE;
            if (u != null && u.getIdUtilisateur() != null && u.getTypeUtilisateur().equals(TypeUtilisateur.UTILISATEUR_ENTITE)) {
                // verifions si cest la premiere Connection
                firstConnection = securiteUser.testeFirstConnection(u);
//                firstConnection = connectionLoggerDao.testeFirstConnection(u);
            }
            
            if (u != null && u.getIdUtilisateur() != null && Objects.equals(u.getActif(), Boolean.TRUE) && u.getLogin().toUpperCase().equals(user.getLogin().toUpperCase())) {
                securiteUser.insertUserInLogger(u);

                //Recuperation des parametres de la session pour l utilisateur courant
                this.setUser(u);
                languageController.setLocaleCode(u.getLangue());
                //creation des variables @ApplicationScoped
//                ctx.getExternalContext().getApplicationMap().put(GlobalFonctions.ENTREPRISE_ACTIF, this.getEntreprise());
                if (u.getTypeUtilisateur().equals(TypeUtilisateur.UTILISATEUR_SYSTEM)) {
                    /*
                    a definir 
                     */
                    if (societeDao.findAll().isEmpty()) {
                        doRedirect(chemin + "/" + "administration/configuration.xhtml");
                    } else {
                        ctx.getExternalContext().getSessionMap().put(GlobalFonctions.ENTREPRISE_ACTIF, u.getIdSociete());
                        ctx.getExternalContext().getSessionMap().put(GlobalFonctions.SESSION_USER, u);
                        this.optionChoix(0);
                        url = "acceuil_admin_system.xhtml";
                        doRedirect(chemin + "/" + url);
                    }
                    
                } else if (u.getTypeUtilisateur().equals(TypeUtilisateur.UTILISATEUR_ENTITE)) {
                    ctx.getExternalContext().getSessionMap().put(GlobalFonctions.ENTREPRISE_ACTIF, u.getIdSociete());
//                    this.setEntreprise(u.getIdSociete());
                    this.setSociete(u.getIdSociete());
                    this.setUser(u);
                    this.setShwomenuParametrageForUserSysteme(Boolean.FALSE);
                    
                    menusUser = securiteUser.getMenusByUser(u);
                    Map<String, String> mesChemin = new HashMap<>();
                    for (OrclassMenus menu : menusUser) {
                        mesChemin.put(menu.getChemin(), menu.getCode());
                    }
                    
                    ctx.getExternalContext().getSessionMap().put(GlobalFonctions.MENUS_USER, mesChemin);
                    url = "accueil.xhtml";
                    doRedirect(chemin + "/" + url);
                }

//                usersConnectes.add(u);
                this.setIsLoggedIn(true);
//                isLoggedIn = true;

//                ctx.getExternalContext().getSessionMap().put(GlobalFonctions.CONNECTION_CONNTROLLER, connect);
                u.setConnecter(Boolean.TRUE);
                ctx.getExternalContext().getSessionMap().put(GlobalFonctions.SESSION_USER, u);
            } else {
                //String[] detail = {entete[0], user.getLogin()};
                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "echec.connexion", null, myLoc);
                // msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.Errdoublons", detail, myLoc);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null));
            }
        } catch (LicenceException th) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, th.getCode(), null));
            
        }
        
        this.init();
        return null;
    }
    
    public Boolean showByMenuCode(String code) {
        for (OrclassMenus menu : menusUser) {
            System.out.println("menu code " + menu.getCode() + "- chemin " + menu.getChemin());
            if (menu.getCode().equals(code)) {
                return Boolean.TRUE;
            }
            
        }
        return Boolean.FALSE;
    }
    
    public void doRedirect() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            NavigationHandler nh = context.getApplication().getNavigationHandler();
            nh.handleNavigation(context, null, "client.deconnect");
            
        } catch (Exception e) {
            
        }
    }
    
    public boolean isIsLoggedIn() {
        return isLoggedIn;
    }
    
    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
    
    public ISecurite getSecuriteUser() {
        return securiteUser;
    }
    
    public void setSecuriteUser(ISecurite securiteUser) {
        this.securiteUser = securiteUser;
    }
    
    public String getCurrenPage() {
        return currenPage;
    }
    
    public void setCurrenPage(String currenPage) {
        this.currenPage = currenPage;
    }
    
   
    
    public OrclassUtilisateurs getUser() {
        return user;
    }
    
    public void setUser(OrclassUtilisateurs user) {
        this.user = user;
    }
    
    public String getDate() {
        
        return "" + IdleDate.getYear(new Date());
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public Boolean getAfficheLogo() {
        return afficheLogo;
    }
    
    public void setAfficheLogo(Boolean afficheLogo) {
        this.afficheLogo = afficheLogo;
    }
    
    public Boolean getAdministration() {
        return administration;
    }
    
    public void setAdministration(Boolean administration) {
        this.administration = administration;
    }
    
    public Boolean getAssurance_ird() {
        return assurance_ird;
    }
    
    public void setAssurance_ird(Boolean assurance_ird) {
        this.assurance_ird = assurance_ird;
    }
    
    public Boolean getTransport() {
        return transport;
    }
    
    public void setTransport(Boolean transport) {
        this.transport = transport;
    }
    
    public Boolean getCaution_credit() {
        return caution_credit;
    }
    
    public void setCaution_credit(Boolean caution_credit) {
        this.caution_credit = caution_credit;
    }
    
    public Boolean getAgricolte() {
        return agricolte;
    }
    
    public void setAgricolte(Boolean agricolte) {
        this.agricolte = agricolte;
    }
    
    public Boolean getAssurance_adp() {
        return assurance_adp;
    }
    
    public void setAssurance_adp(Boolean assurance_adp) {
        this.assurance_adp = assurance_adp;
    }
    
    public Boolean getCompta_general() {
        return compta_general;
    }
    
    public void setCompta_general(Boolean compta_general) {
        this.compta_general = compta_general;
    }
    
    public Boolean getAssurance_auto() {
        return assurance_auto;
    }
    
    public void setAssurance_auto(Boolean assurance_auto) {
        this.assurance_auto = assurance_auto;
    }
    
    public int getChoixModule() {
        return choixModule;
    }
    
    public void setChoixModule(int choixModule) {
        this.choixModule = choixModule;
    }
    
    public Boolean getGestion_assure() {
        return gestion_assure;
    }
    
    public void setGestion_assure(Boolean gestion_assure) {
        this.gestion_assure = gestion_assure;
    }
    
    public String getOldPassWord() {
        return oldPassWord;
    }
    
    public void setOldPassWord(String oldPassWord) {
        this.oldPassWord = oldPassWord;
    }
    
    public String getNewPassWord() {
        return newPassWord;
    }
    
    public void setNewPassWord(String newPassWord) {
        this.newPassWord = newPassWord;
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
    
    public Boolean getShowModuleAmin() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showModuleAmin = this.droitAccesService.accesToModuleAdministration(user);
        }
        return showModuleAmin;
    }
    
    public void setShowModuleAmin(Boolean showModuleAmin) {
        this.showModuleAmin = showModuleAmin;
    }
    
    public Boolean getShowModuleAssureIrd() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showModuleAssureIrd = this.droitAccesService.accesToModuleAssureIrd(user);
        }
        return showModuleAssureIrd;
    }
    
    public void setShowModuleAssureIrd(Boolean showModuleAssureIrd) {
        this.showModuleAssureIrd = showModuleAssureIrd;
    }
    
    public Boolean getShowModuleAssureAdp() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showModuleAssureAdp = this.droitAccesService.accesToModuleAssureAdp(user);
        }
        return showModuleAssureAdp;
    }
    
    public void setShowModuleAssureAdp(Boolean showModuleAssureAdp) {
        this.showModuleAssureAdp = showModuleAssureAdp;
    }
    
    public Boolean getShowModuleAssureAuto() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showModuleAssureAuto = this.droitAccesService.accesAssuranceAuto(user);
        }
        return showModuleAssureAuto;
    }
    
    public void setShowModuleAssureAuto(Boolean showModuleAssureAuto) {
        this.showModuleAssureAuto = showModuleAssureAuto;
    }
    
    public Boolean getShowModuleTransport() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showModuleTransport = this.droitAccesService.accesToModuleTransport(user);
        }
        return showModuleTransport;
    }
    
    public void setShowModuleTransport(Boolean showModuleTransport) {
        this.showModuleTransport = showModuleTransport;
    }
    
    public Boolean getShowModuleCautionCredit() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showModuleCautionCredit = this.droitAccesService.accesToModuleCautionCredit(user);
        }
        return showModuleCautionCredit;
    }
    
    public void setShowModuleCautionCredit(Boolean showModuleCautionCredit) {
        this.showModuleCautionCredit = showModuleCautionCredit;
    }
    
    public Boolean getShowModuleAgricolte() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showModuleAgricolte = this.droitAccesService.accesToModuleAgriclote(user);
        }
        return showModuleAgricolte;
    }
    
    public void setShowModuleAgricolte(Boolean showModuleAgricolte) {
        this.showModuleAgricolte = showModuleAgricolte;
    }
    
    public Boolean getShowModuleGestionAssure() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showModuleGestionAssure = this.droitAccesService.accesToModuleGestionAssurer(user);
        }
        return showModuleGestionAssure;
    }
    
    public void setShowModuleGestionAssure(Boolean showModuleGestionAssure) {
        this.showModuleGestionAssure = showModuleGestionAssure;
    }
    
    public Boolean getShowModuleComptabiliteGeneral() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showModuleComptabiliteGeneral = this.droitAccesService.accesComptabiliteGeneral(user);
        }
        return showModuleComptabiliteGeneral;
    }
    
    public void setShowModuleComptabiliteGeneral(Boolean showModuleComptabiliteGeneral) {
        this.showModuleComptabiliteGeneral = showModuleComptabiliteGeneral;
    }
    
    public Boolean getShowMenuConfiguration() {
        if (user != null && user.getIdUtilisateur() != null) {
            showMenuConfiguration = this.showByMenuCode("codeconfiguration");
        }
        return showMenuConfiguration;
    }
    
    public void setShowMenuConfiguration(Boolean showMenuConfiguration) {
        this.showMenuConfiguration = showMenuConfiguration;
    }
    
    public Boolean getShowMenuProfilUtilisateur() {
        if (user != null && user.getIdUtilisateur() != null) {
            showMenuProfilUtilisateur = this.showByMenuCode("entite.profil");
        }
        return showMenuProfilUtilisateur;
    }
    
    public void setShowMenuProfilUtilisateur(Boolean showMenuProfilUtilisateur) {
        this.showMenuProfilUtilisateur = showMenuProfilUtilisateur;
    }
    
    public Boolean getShowMenuDroitAccess() {
        if (user != null && user.getIdUtilisateur() != null) {
            showMenuDroitAccess = this.showByMenuCode("droit.access");
        }
        return showMenuDroitAccess;
    }
    
    public void setShowMenuDroitAccess(Boolean showMenuDroitAccess) {
        this.showMenuDroitAccess = showMenuDroitAccess;
    }
    
    public Boolean getShowMenuUtilisateur() {
        if (user != null && user.getIdUtilisateur() != null) {
            showMenuUtilisateur = this.showByMenuCode("entite.user");
        }
        return showMenuUtilisateur;
    }
    
    public void setShowMenuUtilisateur(Boolean showMenuUtilisateur) {
        this.showMenuUtilisateur = showMenuUtilisateur;
    }
    
    public Boolean getShowMenuIntermediaire() {
        if (user != null && user.getIdUtilisateur() != null) {
            showMenuIntermediaire = this.showByMenuCode("intermediaire");
        }
        return showMenuIntermediaire;
    }
    
    public void setShowMenuIntermediaire(Boolean showMenuIntermediaire) {
        this.showMenuIntermediaire = showMenuIntermediaire;
    }
    
    public Boolean getShowMenuClasse() {
        if (user != null && user.getIdUtilisateur() != null) {
            showMenuClasse = this.showByMenuCode("classe");
        }
        return showMenuClasse;
    }
    
    public void setShowMenuClasse(Boolean showMenuClasse) {
        
        this.showMenuClasse = showMenuClasse;
    }
    
    public Boolean getShowMenuBrance() {
        if (user != null && user.getIdUtilisateur() != null) {
            showMenuBrance = this.showByMenuCode("branche");
        }
        return showMenuBrance;
    }
    
    public void setShowMenuBrance(Boolean showMenuBrance) {
        this.showMenuBrance = showMenuBrance;
    }
    
    public Boolean getShowMenuCategorie() {
        if (user != null && user.getIdUtilisateur() != null) {
            showMenuCategorie = this.showByMenuCode("categorie");
        }
        return showMenuCategorie;
    }
    
    public void setShowMenuCategorie(Boolean showMenuCategorie) {
        this.showMenuCategorie = showMenuCategorie;
    }
    
    public Boolean getShowMenuOptinEntreprise() {
        if (user != null && user.getIdUtilisateur() != null) {
            showMenuOptinEntreprise = this.showByMenuCode("codeoptionetab");
        }
        return showMenuOptinEntreprise;
    }
    
    public void setShowMenuOptinEntreprise(Boolean showMenuOptinEntreprise) {
        this.showMenuOptinEntreprise = showMenuOptinEntreprise;
    }
    
    public Boolean getShowMenuEntreprise() {
        if (user != null && user.getIdUtilisateur() != null) {
            showMenuEntreprise = this.showByMenuCode("entite.entreprise");
//            showMenuEntreprise = droitAccesService.accessMenuByAdministration(user, "entite.entreprise");
        }
        return showMenuEntreprise;
    }
    
    public void setShowMenuEntreprise(Boolean showMenuEntreprise) {
        this.showMenuEntreprise = showMenuEntreprise;
    }
    
    public void chargeMenuAcces() {
        initDB.getAllMenuByFonctionnaliteByActionByModuleAdmininstration();
    }
    
    public Boolean getCompta_agence() {
        return compta_agence;
    }
    
    public void setCompta_agence(Boolean compta_agence) {
        this.compta_agence = compta_agence;
    }
    
    public Boolean getReassurance() {
        return reassurance;
    }
    
    public void setReassurance(Boolean reassurance) {
        this.reassurance = reassurance;
    }
    
    public Boolean getReporting() {
        return reporting;
    }
    
    public void setReporting(Boolean reporting) {
        this.reporting = reporting;
    }
    
    public Boolean getShowModuleComptabiliteAgence() {
        showModuleComptabiliteAgence = this.droitAccesService.accessModule(user, "mod.compt.agence");
        return showModuleComptabiliteAgence;
    }
    
    public void setShowModuleComptabiliteAgence(Boolean showModuleComptabiliteAgence) {
        this.showModuleComptabiliteAgence = showModuleComptabiliteAgence;
    }
    
    public Boolean getShowModuleReassurance() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showModuleReassurance = this.droitAccesService.accessModule(user, "mod.reassu");
        }
        return showModuleReassurance;
    }
    
    public void setShowModuleReassurance(Boolean showModuleReassurance) {
        this.showModuleReassurance = showModuleReassurance;
    }
    
    public Boolean getShowModuleReporting() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showModuleReporting = this.droitAccesService.accessModule(user, "gestionEtat");
        }
        return showModuleReporting;
    }
    
    public void setShowModuleReporting(Boolean showModuleReporting) {
        
        this.showModuleReporting = showModuleReporting;
    }
    
    public String cheminApplication() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        String chemin = ctx.getExternalContext().getRequestContextPath();
        return chemin;
    }
    
    public void navigator() {
//        return navigator_affaire_nouvelle;
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            NavigationHandler nh = context.getApplication().getNavigationHandler();
            nh.handleNavigation(context, null, "affaire.nouvelle");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Boolean getParamettre() {
        return paramettre;
    }
    
    public void setParamettre(Boolean paramettre) {
        this.paramettre = paramettre;
    }
    
    public Boolean getShowModuleParametrage() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showModuleParametrage = this.droitAccesService.accessModule(user, "mod.ref");
        }
        return showModuleParametrage;
    }
    
    public void setShowModuleParametrage(Boolean showModuleParametrage) {
        this.showModuleParametrage = showModuleParametrage;
    }
    
    public Boolean getShowMenuCaracteristique() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuCaracteristique = this.showByMenuCode("assure.caract");
        }
        return showMenuCaracteristique;
    }
    
    public void setShowMenuCaracteristique(Boolean showMenuCaracteristique) {
        
        this.showMenuCaracteristique = showMenuCaracteristique;
    }
    
    public Boolean getShowMenuRelation() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuRelation = this.showByMenuCode("groupe.caract");
        }
        return showMenuRelation;
    }
    
    public void setShowMenuRelation(Boolean showMenuRelation) {
        
        this.showMenuRelation = showMenuRelation;
    }
    
    public Boolean getShowMenuReference() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuReference = this.showByMenuCode("reference.caract");
        }
        return showMenuReference;
    }
    
    public void setShowMenuReference(Boolean showMenuReference) {
        this.showMenuReference = showMenuReference;
    }
    
    public Boolean getShowMenuApporteur() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuApporteur = this.showByMenuCode("apporteur");
        }
        return showMenuApporteur;
    }
    
    public void setShowMenuApporteur(Boolean showMenuApporteur) {
        this.showMenuApporteur = showMenuApporteur;
    }
    
    public Boolean getShowMenuIntermediairePrime() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuIntermediairePrime = this.showByMenuCode("intermediaire.prime");
        }
        return showMenuIntermediairePrime;
    }
    
    public void setShowMenuIntermediairePrime(Boolean showMenuIntermediairePrime) {
        this.showMenuIntermediairePrime = showMenuIntermediairePrime;
    }
    
    public Boolean getShowMenuGarantieCategorie() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuGarantieCategorie = this.showByMenuCode("assure.garantie");
        }
        return showMenuGarantieCategorie;
    }
    
    public void setShowMenuGarantieCategorie(Boolean showMenuGarantieCategorie) {
        this.showMenuGarantieCategorie = showMenuGarantieCategorie;
    }
    
    public Boolean getShowMenuGarantieCaracteristique() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuGarantieCaracteristique = this.showByMenuCode("groupe.garantie");
        }
        return showMenuGarantieCaracteristique;
    }
    
    public void setShowMenuGarantieCaracteristique(Boolean showMenuGarantieCaracteristique) {
        this.showMenuGarantieCaracteristique = showMenuGarantieCaracteristique;
    }
    
    public Boolean getShowMenuGarantieReference() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuGarantieReference = this.showByMenuCode("reference.garantie");
        }
        return showMenuGarantieReference;
    }
    
    public void setShowMenuGarantieReference(Boolean showMenuGarantieReference) {
        this.showMenuGarantieReference = showMenuGarantieReference;
    }
    
    public Boolean getShowMenuPrestation() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuPrestation = this.showByMenuCode("reference.prestation");
        }
        return showMenuPrestation;
    }
    
    public void setShowMenuPrestation(Boolean showMenuPrestation) {
        
        this.showMenuPrestation = showMenuPrestation;
    }
    
    public Boolean getShowMenuRubrique() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuRubrique = this.showByMenuCode("rubrique");
        }
        return showMenuRubrique;
    }
    
    public void setShowMenuRubrique(Boolean showMenuRubrique) {
        
        this.showMenuRubrique = showMenuRubrique;
    }
    
    public Boolean getShowMenuRubriqueCategorie() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuRubriqueCategorie = this.showByMenuCode("assure.rubrique");
        }
        return showMenuRubriqueCategorie;
    }
    
    public void setShowMenuRubriqueCategorie(Boolean showMenuRubriqueCategorie) {
        this.showMenuRubriqueCategorie = showMenuRubriqueCategorie;
    }
    
    public Boolean getShowMenuRubriquePrestation() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuRubriquePrestation = this.showByMenuCode("groupe.rubrique");
        }
        return showMenuRubriquePrestation;
    }
    
    public void setShowMenuRubriquePrestation(Boolean showMenuRubriquePrestation) {
        this.showMenuRubriquePrestation = showMenuRubriquePrestation;
    }
    
    public Boolean getShowMenuRegistreProduction() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuRegistreProduction = this.showByMenuCode("registre_production");
        }
        return showMenuRegistreProduction;
    }
    
    public void setShowMenuRegistreProduction(Boolean showMenuRegistreProduction) {
        this.showMenuRegistreProduction = showMenuRegistreProduction;
    }
    
    public Boolean getShowMenuRegistrationSinistre() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuRegistrationSinistre = this.showByMenuCode("sinistre.registre");
        }
        return showMenuRegistrationSinistre;
    }
    
    public void setShowMenuRegistrationSinistre(Boolean showMenuRegistrationSinistre) {
        this.showMenuRegistrationSinistre = showMenuRegistrationSinistre;
    }
    
    public Boolean getShowMenuInitialisationRegistre() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuInitialisationRegistre = this.showByMenuCode("initialisation_registre");
        }
        return showMenuInitialisationRegistre;
    }
    
    public void setShowMenuInitialisationRegistre(Boolean showMenuInitialisationRegistre) {
        this.showMenuInitialisationRegistre = showMenuInitialisationRegistre;
    }
    
    public Boolean getShowMenuDuree() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuDuree = this.showByMenuCode("duree");
        }
        return showMenuDuree;
    }
    
    public void setShowMenuDuree(Boolean showMenuDuree) {
        this.showMenuDuree = showMenuDuree;
    }
    
    public Boolean getShowMenuTypeCaracteristique() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuTypeCaracteristique = this.showByMenuCode(ModuleMenu.mdsp_type_caracteristique);
        }
        return showMenuTypeCaracteristique;
    }
    
    public void setShowMenuTypeCaracteristique(Boolean showMenuTypeCaracteristique) {
        this.showMenuTypeCaracteristique = showMenuTypeCaracteristique;
    }
    
    public Boolean getShowMenuExoneration() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuExoneration = this.showByMenuCode(ModuleMenu.mdsp_exoneration_taxe);
        }
        return showMenuExoneration;
    }
    
    public void setShowMenuExoneration(Boolean showMenuExoneration) {
        this.showMenuExoneration = showMenuExoneration;
    }
    
    public Boolean getShowMenuPlafondMaladie() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuPlafondMaladie = this.showByMenuCode(ModuleMenu.mdsp_plafond_maladie);
        }
        return showMenuPlafondMaladie;
    }
    
    public void setShowMenuPlafondMaladie(Boolean showMenuPlafondMaladie) {
        this.showMenuPlafondMaladie = showMenuPlafondMaladie;
    }
    
    public Boolean getShowMenuAffaireNouvelle() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuAffaireNouvelle = this.showByMenuCode(ModuleMenu.mAffaireNouvelle);
        }
        return showMenuAffaireNouvelle;
    }
    
    public void setShowMenuAffaireNouvelle(Boolean showMenuAffaireNouvelle) {
        this.showMenuAffaireNouvelle = showMenuAffaireNouvelle;
    }
    
    public Boolean getShowMenuAffaireNouvelleReport() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuAffaireNouvelleReport = this.showByMenuCode(ModuleMenu.mContratSanteEtat);
        }
        return showMenuAffaireNouvelleReport;
    }
    
    public void setShowMenuAffaireNouvelleReport(Boolean showMenuAffaireNouvelleReport) {
        this.showMenuAffaireNouvelleReport = showMenuAffaireNouvelleReport;
    }
    
    public Boolean getShowMenuAvenant() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuAvenant = this.showByMenuCode(ModuleMenu.mAvenant);
        }
        return showMenuAvenant;
    }
    
    public void setShowMenuAvenant(Boolean showMenuAvenant) {
        this.showMenuAvenant = showMenuAvenant;
    }
    
    public Boolean getShowMenuEchaeance() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuEchaeance = this.showByMenuCode(ModuleMenu.mEcheance);
        }
        return showMenuEchaeance;
    }
    
    public void setShowMenuEchaeance(Boolean showMenuEchaeance) {
        this.showMenuEchaeance = showMenuEchaeance;
    }
    
    public Boolean getShowMenuEncaissement() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuEncaissement = this.showByMenuCode(ModuleMenu.mEncaissement);
        }
        return showMenuEncaissement;
    }
    
    public void setShowMenuEncaissement(Boolean showMenuEncaissement) {
        this.showMenuEncaissement = showMenuEncaissement;
    }
    
    public Boolean getShowMenuVersement() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuVersement = this.showByMenuCode(ModuleMenu.mEncaissement);
        }
        return showMenuVersement;
    }
    
    public void setShowMenuVersement(Boolean showMenuVersement) {
        this.showMenuVersement = showMenuVersement;
    }
    
    public Boolean getShowMenuTarif() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuTarif = this.showByMenuCode(ModuleMenu.mdsp_tarif);
        }
        return showMenuTarif;
    }
    
    public void setShowMenuTarif(Boolean showMenuTarif) {
        this.showMenuTarif = showMenuTarif;
    }
    
    public String getCle_securite() {
        return cle_securite;
    }
    
    public void setCle_securite(String cle_securite) {
        this.cle_securite = cle_securite;
    }
    
    public int getChoixUserSysteme() {
        return choixUserSysteme;
    }
    
    public void setChoixUserSysteme(int choixUserSysteme) {
        this.choixUserSysteme = choixUserSysteme;
    }
    
    public Boolean getShwomenuParametrageForUserSysteme() {
        return shwomenuParametrageForUserSysteme;
    }
    
    public void setShwomenuParametrageForUserSysteme(Boolean shwomenuParametrageForUserSysteme) {
        this.shwomenuParametrageForUserSysteme = shwomenuParametrageForUserSysteme;
    }
    
    public Boolean getShowMenuAffaireNouvelle_auto() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuAffaireNouvelle_auto = this.showByMenuCode(ModuleMenu.mAffaireNouvelle_auto);
        }
        
        return showMenuAffaireNouvelle_auto;
    }
    
    public void setShowMenuAffaireNouvelle_auto(Boolean showMenuAffaireNouvelle_auto) {
        this.showMenuAffaireNouvelle_auto = showMenuAffaireNouvelle_auto;
    }
    
    public Boolean getShowMenuAvenant_auto() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuAvenant_auto = this.showByMenuCode(ModuleMenu.mAvenant_auto);
        }
        
        return showMenuAvenant_auto;
        
    }
    
    public void setShowMenuAvenant_auto(Boolean showMenuAvenant_auto) {
        this.showMenuAvenant_auto = showMenuAvenant_auto;
    }
    
    public Boolean getShowMenuEncaissement_auto() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuEncaissement_auto = this.showByMenuCode(ModuleMenu.mEncaissement_auto);
        }
        
        return showMenuEncaissement_auto;
        
    }
    
    public void setShowMenuEncaissement_auto(Boolean showMenuEncaissement_auto) {
        this.showMenuEncaissement_auto = showMenuEncaissement_auto;
    }
    
    public Boolean getShowMenuFraction() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuFraction = this.showByMenuCode(ModuleMenu.mReFfraction);
        }
        return showMenuFraction;
        
    }
    
    public void setShowMenuFraction(Boolean showMenuFraction) {
        this.showMenuFraction = showMenuFraction;
    }
    
    public Boolean getShowMenuPeriodicite() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuPeriodicite = this.showByMenuCode(ModuleMenu.mPeriodicite);
        }
        
        return showMenuPeriodicite;
    }
    
    public void setShowMenuPeriodicite(Boolean showMenuPeriodicite) {
        this.showMenuPeriodicite = showMenuPeriodicite;
    }
    
    public Boolean getShowReduction() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showReduction = this.showByMenuCode(ModuleMenu.mRduction);
        }
        return showReduction;
    }
    
    public void setShowReduction(Boolean showReduction) {
        this.showReduction = showReduction;
    }
    
    public Boolean getShowMenuTypeAvenant() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuTypeAvenant = this.showByMenuCode(ModuleMenu.mTypeAvenant);
        }
        return showMenuTypeAvenant;
    }
    
    public void setShowMenuTypeAvenant(Boolean showMenuTypeAvenant) {
        this.showMenuTypeAvenant = showMenuTypeAvenant;
    }
    
    public Boolean getShowMenuAccessAvenant() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuAccessAvenant = this.showByMenuCode(ModuleMenu.mAccessDroitAvenant);
        }
        
        return showMenuAccessAvenant;
    }
    
    public void setShowMenuAccessAvenant(Boolean showMenuAccessAvenant) {
        this.showMenuAccessAvenant = showMenuAccessAvenant;
    }
    
    public Boolean getShowMenuAgenceMentAvenant() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuAgenceMentAvenant = this.showByMenuCode(ModuleMenu.mAgencementAvenant);
        }
        
        return showMenuAgenceMentAvenant;
    }
    
    public void setShowMenuAgenceMentAvenant(Boolean showMenuAgenceMentAvenant) {
        this.showMenuAgenceMentAvenant = showMenuAgenceMentAvenant;
    }
    
    public Boolean getShowMenuPriseEncharge() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuPriseEncharge = this.showByMenuCode(ModuleMenu.mds_mPrise_En_Charge);
        }
        
        return showMenuPriseEncharge;
    }
    
    public void setShowMenuPriseEncharge(Boolean showMenuPriseEncharge) {
        this.showMenuPriseEncharge = showMenuPriseEncharge;
    }
    
    public Boolean getShowMenuRemboursementMaladie() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuPriseEncharge = this.showByMenuCode(ModuleMenu.mMaladie_remboursement);
        }
        return showMenuRemboursementMaladie;
    }
    
    public void setShowMenuRemboursementMaladie(Boolean showMenuRemboursementMaladie) {
        this.showMenuRemboursementMaladie = showMenuRemboursementMaladie;
    }
    
    public Boolean getShowMenuFactureTier() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuPriseEncharge = this.showByMenuCode(ModuleMenu.mds_mFacture_tier);
        }
        return showMenuFactureTier;
    }
    
    public void setShowMenuFactureTier(Boolean showMenuFactureTier) {
        this.showMenuFactureTier = showMenuFactureTier;
    }
    
    public Boolean getShowMenuTaxe() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuTaxe = this.showByMenuCode(ModuleMenu.mTypetaxe);
        }
        return showMenuTaxe;
    }
    
    public void setShowMenuTaxe(Boolean showMenuTaxe) {
        this.showMenuTaxe = showMenuTaxe;
    }
    
    public Boolean getShowMenuRegimeTaxe() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuRegimeTaxe = this.showByMenuCode(ModuleMenu.mRegimeTaxe);
        }
        return showMenuRegimeTaxe;
    }
    
    public void setShowMenuRegimeTaxe(Boolean showMenuRegimeTaxe) {
        this.showMenuRegimeTaxe = showMenuRegimeTaxe;
    }
    
    public Boolean getShowMenuTaxePrime() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuTaxePrime = this.showByMenuCode(ModuleMenu.mTaxePrime);
        }
        return showMenuTaxePrime;
    }
    
    public void setShowMenuTaxePrime(Boolean showMenuTaxePrime) {
        this.showMenuTaxePrime = showMenuTaxePrime;
    }
    
    public Boolean getShowMenuTypeDocument() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuTypeDocument = this.showByMenuCode(ModuleMenu.mTypeDocument);
        }
        return showMenuTypeDocument;
    }
    
    public void setShowMenuTypeDocument(Boolean showMenuTypeDocument) {
        this.showMenuTypeDocument = showMenuTypeDocument;
    }
    
    public Boolean getShowMenuDocumentCategorie() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuDocumentCategorie = this.showByMenuCode(ModuleMenu.mDocumentCategorie);
        }
        return showMenuDocumentCategorie;
    }
    
    public void setShowMenuDocumentCategorie(Boolean showMenuDocumentCategorie) {
        this.showMenuDocumentCategorie = showMenuDocumentCategorie;
    }
    
    public Boolean getShowMenuDocumentStock() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuDocumentStock = this.showByMenuCode(ModuleMenu.mGestionDocumentStock);
        }
        return showMenuDocumentStock;
    }
    
    public void setShowMenuDocumentStock(Boolean showMenuDocumentStock) {
        this.showMenuDocumentStock = showMenuDocumentStock;
    }
    
    public Boolean getShowMenuTimbreGradue() {
        if (this.getUser() != null && this.getUser().getIdUtilisateur() != null) {
            showMenuTimbreGradue = this.showByMenuCode(ModuleMenu.mTimbreGradue);
        }
        return showMenuTimbreGradue;
    }
    
    public void setShowMenuTimbreGradue(Boolean showMenuTimbreGradue) {
        this.showMenuTimbreGradue = showMenuTimbreGradue;
    }
    
    public Societe getSociete() {
        return societe;
    }
    
    public void setSociete(Societe societe) {
        this.societe = societe;
    }
    
}
