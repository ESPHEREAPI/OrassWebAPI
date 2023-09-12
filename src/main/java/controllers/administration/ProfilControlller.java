/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.administration;

//import Entreprise.OracleConnection;
import controllers.CurrentInstance;
import dao.ORCLASS_PROFILS_UTILISATEURSDao;
import dao.OrclassActionsDao;
import dao.OrclassFonctionnalitesDao;
import dao.OrclassMenusDao;
import dao.OrclassModulesDao;
import dao.OrclassProfilsAccesDao;
import dao.OrclassProfilsDao;
import dao.OrclassUtilisateursAccesDao;
import dao.OrclassUtilisateursDao;
import dao.ServiceProfilDao;
import droitAcces.IDroitAcces;
import enums.Actions;
import enums.FonctionnaliteModuleAdministration;
import enums.ServiceDepartement;
import exception.GlobalException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import modele.OrclassActions;
import modele.OrclassFonctionnalites;

import modele.OrclassMenus;
import modele.OrclassMenusAcces;
import modele.OrclassModules;
import modele.OrclassProfils;
import modele.OrclassProfilsAcces;
import modele.OrclassUtilisateurs;
import modele.OrclassUtilisateursAcces;
import modele.ServiceProfil;
import modele.Societe;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parametrage.ISecurite;
import utils.GlobalFonctions;
import utils.LocaleHelper;
import utils.RecupBundle;

/**
 *
 * @author hp
 */
@Named(value = "profilControlller")
@ViewScoped
public class ProfilControlller implements Serializable {

    /**
     * Creates a new instance of ProfilControlller
     */
    @EJB
    private ISecurite securiteService;
    private Collection<OrclassModules> moduleUser;
    @EJB
    private ServiceProfilDao serviceProfilDao;
    private ServiceProfil serviceProfils;

    @EJB
    ORCLASS_PROFILS_UTILISATEURSDao pROFILS_UTILISATEURSDao;
    @EJB
    OrclassUtilisateursDao utilisateursDao;
    @Inject
    CurrentInstance currentInstance;
    @EJB
    OrclassFonctionnalitesDao fonctionnalitesDao;
    @EJB
    OrclassProfilsAccesDao profilsAccesDao;
    @EJB
    OrclassProfilsDao profilsDao;
    @EJB
    OrclassActionsDao actionsDao;
    @EJB
    OrclassUtilisateursAccesDao utilisateursAccesDao;
    @EJB
    OrclassMenusDao menusDao;
    @EJB
    OrclassModulesDao modulesDao;
    private DualListModel<OrclassModules> modulesModel = new DualListModel<>(new ArrayList<OrclassModules>(), new ArrayList<OrclassModules>());
    // private DualListModel<String> Roles;
    private DualListModel<OrclassFonctionnalites> fonctionaliteModel = new DualListModel<>(new ArrayList<OrclassFonctionnalites>(), new ArrayList<OrclassFonctionnalites>());
    private DualListModel<OrclassActions> actionModel;
    private List<OrclassModules> modulesSource;
    private List<OrclassModules> modulesTarget;
    private List<OrclassModules> listeModuleSaveForFonctionnalite = new ArrayList<>();
    private OrclassModules modules;
    private DualListModel<String> etatModel;
    private List<String> etatAcquis;
    private List<String> etatNonAcquis;
    private DualListModel<OrclassFonctionnalites> fonctionnaliteModel;
    private List<OrclassFonctionnalites> colFonctionnaliteByUser;
    private List<OrclassFonctionnalites> menusSource;
    private List<OrclassFonctionnalites> menusTarget;
    private List<OrclassProfils> listeProfileAccesByEntreprise = new ArrayList<>();

    private List<OrclassActions> actionSource;
    private List<OrclassActions> actionTarget;
    private List<OrclassProfils> colProfil = new ArrayList<>();

    private List<OrclassProfils> selectedProfils = new ArrayList<>();
    private Societe societe;
    private OrclassProfils profilsByUser;
    private OrclassProfils profilsByUserForDelete;
    private List<OrclassUtilisateurs> listUtilisateur = new ArrayList<>();
    private OrclassFonctionnalites fonctionnaliteByUser;
    private List<OrclassProfils> filteredProfilAcces;
     private List<ServiceProfil> filteredServiceProfil;
    private Boolean activeAction = Boolean.FALSE;// ceci permettra de controller la manipulation des actions pour un profil lorsqu il n apas ete attribuer par un utilisateur

    private static final Logger logger = LoggerFactory.getLogger(UtilisateurController.class);
    String summary = "";
    String msgdetail = "";
    OrclassModules moduleSelectForTarget;
    OrclassFonctionnalites[][] tableauFonctinnaliteBymodule;
    int instanceTableau = 0;
    int nombreMaxFonctionaliteByModule = 15;
    List<OrclassModules> moduleTransFert = new ArrayList<>();
    List<OrclassFonctionnalites> fonctionnaliteTransFert = new ArrayList<>();
    List<ServiceProfil> listeProfilsSelected = new ArrayList<>();
    private Boolean adminNotUpdate = Boolean.FALSE;
    private OrclassMenus menu;
    private OrclassModules module;
    OrclassUtilisateurs user;
    OrclassUtilisateurs utilisateurs;
    @EJB
    IDroitAcces serviceAccess;
    ServiceDepartement serviceDepartement;

    public ProfilControlller() {
        moduleSelectForTarget = new OrclassModules();
        profilsByUser = new OrclassProfils();
        profilsByUserForDelete = new OrclassProfils();
        serviceProfils = new ServiceProfil();

    }

    public void chargeModuleTransfert() {
        if (profilsByUser != null && profilsByUser.getIdProfil() != null) {
//            moduleTransFert = new ArrayList<>();
            moduleTransFert = modulesModel.getTarget();
            fonctionnaliteTransFert = fonctionaliteModel.getTarget();
        } else {

            moduleTransFert = modulesModel.getTarget();
        }

    }

    public Boolean accessCree() {
        OrclassActions action = null;
        OrclassFonctionnalites fon = null;
        OrclassMenusAcces ma = null;
        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_profil.name());
            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);

            return serviceAccess.accessAction(user, action, menu);
        }
        return Boolean.FALSE;
    }

    public Boolean accessModifier() {
        OrclassActions action = null;
        OrclassFonctionnalites fon = null;
        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_profil.name());
            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
            return serviceAccess.accessAction(user, action, menu);
        }
        return Boolean.FALSE;
    }

    public Boolean accessSupprimer() {
        OrclassActions action = null;
        OrclassFonctionnalites fon = null;
        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_profil.name());
            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer.name(), fon);
            return serviceAccess.accessAction(user, action, menu);
        }
        return Boolean.FALSE;
    }

    public void reset() {

        this.init();
        this.currentInstance.getAjaxRequestBuilder().reset();
//        RequestContext.getCurrentInstance().reset(":accord:formPersonnel");
    }

    public void init() {
        profilsByUser = new OrclassProfils();
        instanceTableau = 0;
        moduleSelectForTarget = new OrclassModules();
        listeModuleSaveForFonctionnalite = new ArrayList<>();
        modulesTarget = new ArrayList<>();
        modulesModel = new DualListModel<>(modulesSource, modulesTarget);
        profilsByUserForDelete = new OrclassProfils();
        serviceDepartement = null;
//        modulesSource = new ArrayList<>();

        this.getModulesModel();
        this.getFonctionaliteModel();
        this.getallProfilAccessByEntreprise();
        if (Objects.equals(societe.getProfil_code_automatique(), Boolean.TRUE)) {
            profilsByUser.setCode(this.codeprofil());
        }
        PrimeFaces.current().ajax().update(":form1,:form2");

    }

    public String codeprofil() {
        String code;
        Long nbre = profilsDao.nbreProfileCreateByCompagny(societe);
        nbre++;
        code = nbre.toString().length() == 1 ? "0" + nbre.toString() : nbre.toString();
        if (societe.getPrefix_code_profile() != null && !"".equals(societe.getPrefix_code_profile())) {
            return societe.getPrefix_code_profile() + "" + code;
        }
        return code;
    }

    public void getallProfilAccessByEntreprise() {
        if (societe != null && societe.getCodesoci() != null) {
            listeProfileAccesByEntreprise = profilsDao.getAllProfilHaveAccesByEntreprise(societe);
            for (OrclassProfils p : profilsDao.findAll()) {
                if (listeProfileAccesByEntreprise.contains(p) == false) {
                    listeProfileAccesByEntreprise.add(p);
                }
            }
            OrclassProfils profilAdmin = profilsDao.findEntityHavingValue("code", "admin");
            if (listeProfileAccesByEntreprise.contains(profilAdmin) == false) {
                listeProfileAccesByEntreprise.add(profilAdmin);
            }
        }
    }

    @PostConstruct
    void initialiseSession() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        societe = (Societe) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
        if (societe == null) {
            societe = (Societe) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(GlobalFonctions.ENTREPRISE_ACTIF);

        }
        this.getallProfilAccessByEntreprise();
        menu = menusDao.findEntityHavingValue("code", "entite.profil");
        module = modulesDao.findEntityHavingValue("code", "mod.admin");
        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);

    }

    public String updateProfil() {
        String success = null;
        FacesContext ctx = FacesContext.getCurrentInstance();

        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        //Locale myLoc =new Locale("fr");
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "profil", null, myLoc)};
        String[] detail = {entete[0], "Parametres"};
        OrclassProfilsAcces pa = null;
//        OracleConnection con = new OracleConnection();
        List<OrclassUtilisateursAcces> listUserAccess = new ArrayList<>();
        OrclassUtilisateursAcces ua = null;
        try {

            if (profilsByUser != null && profilsByUser.getIdProfil() != null) {
                /*
                on retire les action dans la table profil access 
                 */
                if (fonctionnaliteByUser != null && fonctionnaliteByUser.getIdFonctionnalite() != null) {
                    for (OrclassActions action : actionModel.getSource()) {
                        pa = profilsAccesDao.finkey(fonctionnaliteByUser.getIdModule(), fonctionnaliteByUser, action, profilsByUser);
                        if (pa == null) {
                            continue;
                        }
//                        System.out.println("retour delete" + con.deleteProfilAcces(pa));
                        /*
                        apres avoir retirer l l access au profil , nous allons retirer l access aux utilisateur de ce profile
                         */
                        for (OrclassUtilisateursAcces u : utilisateursAccesDao.getAllAccessUserByProfilAccess(pa)) {
//                            System.out.println("retour delete" + con.deleteUserAccess(u));
                        }
                    }
                    for (OrclassActions act : actionModel.getTarget()) {
                        pa = profilsAccesDao.finkey(fonctionnaliteByUser.getIdModule(), fonctionnaliteByUser, act, profilsByUser);
                        if (pa != null) {
                            //verifions si l user a cette access
                            listUserAccess = utilisateursAccesDao.getAllAccessUserByProfilAccess(pa);
                            if (!listUserAccess.isEmpty()) {
                                continue;
                            }
                            for (OrclassUtilisateurs u : utilisateursDao.userByProfil(pa.getIdProfil())) {
                                ua = utilisateursAccesDao.finKey(pa, u);
                                if (ua == null) {
                                    ua = new OrclassUtilisateursAcces();
                                    ua.setAutorisation_principal(Boolean.TRUE);
                                    ua.setAutorisation_fonctionnalite(Boolean.TRUE);
                                    ua.setAutorisation_action(Boolean.TRUE);
                                    ua.setIdAction(pa.getIdAction());
                                    ua.setIdFonctionnalite(pa.getIdFonctionnalite());
                                    ua.setIdModule(pa.getIdModule());
                                    ua.setIdProfil(pa.getIdProfil());
                                    ua.setIdUtilisateur(u);
                                    utilisateursAccesDao.create(ua);

                                }
                            }
                            continue;
                        }
                        //ajouton ce profile access
                        pa = new OrclassProfilsAcces();
                        pa.setAutorisation(Boolean.TRUE);
                        pa.setIdAction(act);
                        pa.setIdFonctionnalite(fonctionnaliteByUser);
                        pa.setIdModule(fonctionnaliteByUser.getIdModule());
                        pa.setIdProfil(profilsByUser);
                        profilsAccesDao.create(pa);
                        //ajoutons l access aux utilisateurt lier a ce profile
                        for (OrclassUtilisateurs u : utilisateursDao.userByProfil(pa.getIdProfil())) {
                            ua = utilisateursAccesDao.finKey(pa, u);
                            if (ua == null) {
                                ua = new OrclassUtilisateursAcces();
                                ua.setAutorisation_principal(Boolean.TRUE);
                                ua.setAutorisation_fonctionnalite(Boolean.TRUE);
                                ua.setAutorisation_action(Boolean.TRUE);
                                ua.setIdAction(pa.getIdAction());
                                ua.setIdFonctionnalite(pa.getIdFonctionnalite());
                                ua.setIdModule(pa.getIdModule());
                                ua.setIdProfil(pa.getIdProfil());
                                ua.setIdUtilisateur(u);
                                utilisateursAccesDao.create(ua);

                            }
                        }

                    }
                }

                profilsDao.update(profilsByUser);
                if (serviceProfilDao.finKey(profilsByUser, serviceDepartement) == null) {
                    serviceProfils = new ServiceProfil();
                    serviceProfils.setIdProfil(profilsByUser);
                    serviceProfils.setServiceDepartement(serviceDepartement);
                    serviceProfilDao.create(serviceProfils);
                }

                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + exception.Success.OPERATION_SUCCESS.toString(), entete, myLoc);

                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));

            }

        } catch (Throwable th) {

            //ecrire dans le fichier de log  
            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.error", entete, myLoc);
            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));

        }

//        reset();
        return null;

    }

    /*
    charger les service lier au profil
     */
    public void chargeServiceByProfile(OrclassProfils item) {
        if (item == null || item.getIdProfil() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("PLEASE SELECT PROFIL... VALUES IS EMPTY"));
            return;
        }
        this.setProfilsByUser(item);
        listeProfilsSelected = serviceProfilDao.listeProfilByService(item);
        PrimeFaces.current().ajax().update(":form1");
        PrimeFaces.current().executeScript("PF('manageServiceDialog').show()");

    }

    public void deleteServiceProfil(ServiceProfil sd) {
        if (sd == null || sd.getIdProfil() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("PLEASE SELECT SERVICE AND PROFIL... VALUES IS EMPTY"));
            return;
        }
        serviceProfilDao.delete(sd);
        listeProfilsSelected = serviceProfilDao.listeProfilByService(sd.getIdProfil());
        PrimeFaces.current().ajax().update(":form1");
        PrimeFaces.current().executeScript("PF('manageServiceDialog').show()");

    }

    public void checkDelete() {
        if (profilsByUserForDelete != null && profilsByUserForDelete.getIdProfil() != null) {
            if (profilsByUser == null || profilsByUser.getIdProfil() == null) {
                this.setProfilsByUser(profilsByUserForDelete);
            }
        }
        if (profilsByUser != null && profilsByUser.getIdProfil() != null) {
            if (profilsByUser.getCode().equals("admin")) {
                FacesContext ctx = FacesContext.getCurrentInstance();
                //get default locale
                Locale myLoc = ctx.getViewRoot().getLocale();
                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", " YOU DON'T HAVE AUTORISATION FOR THIS PROFILE"));
            } else {
                this.deleteProfile();
            }
        }
    }

    public String deleteProfile() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "profil", null, myLoc)};

        // on recupere tous les modules qui lui sont attribuer puis on inserre
        String[] detail = {entete[0], "Module(s)"};
//        OracleConnection con = new OracleConnection();
        try {

            if (profilsByUser != null && profilsByUser.getIdProfil() != null) {
//                for (OrclassProfilsAcces pa : profilsAccesDao.getAllAccesByProfil(profilsByUser)) {//faire une condition sur la requette delete
//                    profilsAccesDao.delete(pa);
//
//                }
//                con.deleteProfilAccessByProfil(profilsByUser);
                profilsDao.delete(profilsByUser);
                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.succes", entete, myLoc);
                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.delete.succes", detail, myLoc);
                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));

            } else {
                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
            }
        } catch (ConstraintViolationException ejb) {
            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
            throw ejb;
        } catch (Exception th) {
            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "profil", exception.Error.FATAL_ERROR + "", new Object[]{"profil"});
            logger.error("Erreur Survenu", th);
        }
//       lisClass.remove(classes);
        this.reset();
        return null;
    }

    public String saveServiceProfil() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "cree.profil", null, myLoc)};

        // on recupere tous les modules qui lui sont attribuer puis on inserre
        String[] detail = {entete[0], "Module(s)"};
        try {
            if (listeProfilsSelected.isEmpty() || listeProfilsSelected.isEmpty()) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, "PROFILE NOT SELECT", "PLEASE SELECT PROFILE"));
                return "";
            }

//            for (OrclassProfils p : listeProfilsSelected) {
//                if (serviceProfilDao.finKey(p, serviceDepartement) == null) {
//                    serviceProfils = new ServiceProfil();
//                    serviceProfils.setIdProfil(p);
//                    serviceProfils.setServiceDepartement(serviceDepartement);
//                    serviceProfilDao.create(serviceProfils);
//                }
//
//            }
            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));

        } catch (GlobalException e) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_ERROR, profilsByUser.getCode(), e.getCode(), e.getParam());
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCode(), e.getCode()));
//throw e;
        } catch (ConstraintViolationException ejb) {
            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
            throw ejb;
        } catch (Exception th) {
            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "cree.profil", exception.Error.FATAL_ERROR + "", new Object[]{"cree.profil"});
            logger.error("Erreur Survenu", th);
        }
        this.reset();
        PrimeFaces.current().executeScript("PF('manageProfilDialog').show();");
        return null;
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

    public String saveProfilAccess() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "cree.profil", null, myLoc)};

        // on recupere tous les modules qui lui sont attribuer puis on inserre
        String[] detail = {entete[0], "Module(s)"};
        try {
            if (listeModuleSaveForFonctionnalite.isEmpty() || tableauFonctinnaliteBymodule.length == 0) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, "GIVE THE FUNCTIONALY TO THIS PROFILE", "PLEASE TRY AGAINST"));
                return "";
            }
            if (serviceDepartement == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, "VALUES IS NULL ", "PLEASE TRY AGAINST...VALUE SERVICE"));
                return "";
            }
            if ((profilsByUser == null || profilsByUser.getIdProfil() == null) && profilsByUser.getCode() != null) {
//                profilsByUser.setIdSociete(societe);
                profilsByUser.setServiceDepartement(serviceDepartement);
                summary = securiteService.addProfilForAccess(listeModuleSaveForFonctionnalite, tableauFonctinnaliteBymodule, profilsByUser);
                if (summary.equals(exception.Success.OPERATION_SUCCESS.toString())) {
                    summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
                    msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
                } else {
                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, exception.Error.INSERT_ERROR.toString(), "PLEASE TRY AGAINST"));
                }

            } else if ((profilsByUser == null || profilsByUser.getIdProfil() == null) && profilsByUser.getCode() == null) {
                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, exception.Error.FATAL_ERROR.toString(), "PLEASE TRY AGAINST... VALUE IS NULL"));
            }
        } catch (GlobalException e) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_ERROR, profilsByUser.getCode(), e.getCode(), e.getParam());
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCode(), e.getCode()));
//throw e;
        } catch (ConstraintViolationException ejb) {
            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
            throw ejb;
        } catch (Exception th) {
            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "cree.profil", exception.Error.FATAL_ERROR + "", new Object[]{"cree.profil"});
            logger.error("Erreur Survenu", th);
        }
        this.reset();
        PrimeFaces.current().executeScript("PF('manageProfilDialog').show();");
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

    public void onRowSelect() {

    }

    public void populateAction() {;
        actionTarget = new ArrayList<>();
        actionSource = new ArrayList<>();
        Collection<OrclassActions> menu1 = new ArrayList<>();
        Collection<OrclassActions> menu2 = new ArrayList<>();
        menusSource.clear();
        menusTarget.clear();

        if (fonctionnaliteByUser != null && fonctionnaliteByUser.getIdFonctionnalite() != null && profilsByUser != null && profilsByUser.getIdProfil() != null) {
            menu1 = securiteService.getAllActionByFonctionnaliteNotHaveProfile(fonctionnaliteByUser, profilsByUser);
            menu2 = securiteService.getAllActionByFonctionnaliteAndProfile(fonctionnaliteByUser.getIdModule(), fonctionnaliteByUser, profilsByUser);

//            for (OrclassActions act : menu1) {
//                if (menu1.contains(act) == Boolean.TRUE) {
//                    menu1.remove(act);
//                }
//            }
            if (!menu2.isEmpty()) {
                actionTarget.addAll(menu2);
            }
            if (!menu1.isEmpty()) {
                for (OrclassActions act : menu1) {
                    if (actionSource.contains(act) == false) {
                        actionSource.add(act);
                    }
                }
//                actionSource.addAll(menu1);
            } else if (menu1.isEmpty()) {
                actionSource = actionsDao.getAllactionByFonctionnalite(fonctionnaliteByUser, fonctionnaliteByUser.getIdModule());
                for (OrclassActions act : menu2) {
                    if (actionSource.contains(act) == true) {
                        actionSource.remove(act);
                    }

                }
            }
        } else {
            actionSource = new ArrayList<>();
            actionTarget = new ArrayList<>();
        }
    }

    public DualListModel<OrclassActions> getActionModel() {
        this.populateAction();
        actionModel = new DualListModel<>(actionSource, actionTarget);

        return actionModel;
    }

    public DualListModel<OrclassModules> getModulesModel() {

        Collection<OrclassModules> modul = new ArrayList<>();
        Collection<OrclassModules> modul2 = new ArrayList<>();

        if (societe != null && societe.getCodesoci() != null && (profilsByUser == null || profilsByUser.getIdProfil() == null)) {

            if (modulesModel != null && !modulesModel.getSource().isEmpty()) {
                modulesSource = new ArrayList<>();
                modulesTarget = new ArrayList<>();
                modul = securiteService.getAllModulesByEntreprise(user.getServiceDepartement());
                modulesTarget = modulesModel.getTarget();
                if (modul.isEmpty() == false) {
                    for (OrclassModules md : modulesModel.getTarget()) {
                        if (modul.contains(md) == true) {
                            modul.remove(md);
                        }
                    }
                    modulesSource.addAll(modul);

                }

            } else {
                modulesSource = new ArrayList<>();
                modulesTarget = new ArrayList<>();
                modul = securiteService.getAllModulesByEntreprise(user.getServiceDepartement());

                if (modul.isEmpty() == false) {
                    modulesSource.addAll(modul);

                }
            }
            //filtre les modules acquis ou non par ce user
//            modulesSource.clear();
//            modulesTarget.clear();
            //  gauche

//            if (modul2 != null || modul2.isEmpty() == false) {
//                modulesTarget.addAll(modul2);
//            }
        } else if (profilsByUser != null && profilsByUser.getIdProfil() != null) {
            if (modulesModel != null && !modulesModel.getTarget().isEmpty()) {
                modulesSource = new ArrayList<>();
                modulesTarget = new ArrayList<>();

                modul.addAll(modulesModel.getTarget());
                modulesTarget = (List<OrclassModules>) securiteService.getModuleByProfile(profilsByUser);
                for (OrclassModules m : modul) {
                    if (modulesTarget.contains(m) == false) {
                        modulesTarget.add(m);
                    }
                }
            } else {
                modulesSource = new ArrayList<>();
                modulesTarget = new ArrayList<>();
                modulesTarget = (List<OrclassModules>) securiteService.getModuleByProfile(profilsByUser);
            }

            modul = securiteService.getAllModulesByEntreprise(user.getServiceDepartement());
            for (OrclassModules mdo : modulesTarget) {

                if (modul.contains(mdo) == true) {
                    modul.remove(mdo);
                }
            }
            modulesSource.addAll(modul);

        }

        modulesModel = new DualListModel<>(modulesSource, modulesTarget);
        return modulesModel;
    }

//    public void removeAndAddModule() {
//        OrclassProfilsAcces pacces = null;
//        if (profilsByUser != null && profilsByUser.getIdProfil() != null) {
//            for (OrclassModules m : modulesModel.getSource()) {
////               pacces=profilsAccesDao.getAllAccesByProfil(profilsByUser)kk
//                profilsAccesDao.deleteAll("idModule", myuyyyy
//                utilisateursAccesDao.deleteAll("idModule", m);
//            }
//            FacesContext context = FacesContext.getCurrentInstance();
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.OPERATION_SUCCESS, "" + exception.Success.RESULTS_FOUND));
//
//        }
//    }
    public void setModulesModel(DualListModel<OrclassModules> modulesModel) {
        this.modulesModel = modulesModel;
    }

    public void saveFonctionnnaliteByModule() {
        int j = 0;

        if (moduleSelectForTarget != null && moduleSelectForTarget.getIdModule() != null) {

            if (instanceTableau == 0) {
                int taille1 = securiteService.getAllModulesByEntreprise(user.getServiceDepartement()).size();
                int taille2 = fonctionnalitesDao.findAll().size();
                tableauFonctinnaliteBymodule = new OrclassFonctionnalites[taille1][taille2];
                listeModuleSaveForFonctionnalite = new ArrayList<>();
            }
            if (listeModuleSaveForFonctionnalite.isEmpty()) {
                moduleSelectForTarget.setIndiceTableau(instanceTableau);
                listeModuleSaveForFonctionnalite.add(moduleSelectForTarget);
                for (OrclassFonctionnalites f : fonctionaliteModel.getTarget()) {
                    tableauFonctinnaliteBymodule[instanceTableau][j] = f;
                    j++;
                }
                instanceTableau++;
            } else if (listeModuleSaveForFonctionnalite.contains(moduleSelectForTarget) == false) {
                moduleSelectForTarget.setIndiceTableau(instanceTableau);
                listeModuleSaveForFonctionnalite.add(moduleSelectForTarget);
                j = 0;
                for (OrclassFonctionnalites f : fonctionaliteModel.getTarget()) {
                    tableauFonctinnaliteBymodule[instanceTableau][j] = f;
                    j++;
                }
                instanceTableau++;
            }

            if (profilsByUser != null && profilsByUser.getIdProfil() != null) {
//                OracleConnection con = new OracleConnection();
                List<OrclassProfilsAcces> listPa = new ArrayList<>();
                List<OrclassUtilisateursAcces> listUa = new ArrayList<>();
                OrclassUtilisateursAcces ua = null;
                for (OrclassFonctionnalites f : fonctionaliteModel.getSource()) {
                    listPa = profilsAccesDao.getAllProfilByFonctionnalite(f, profilsByUser, societe);
                    if (listPa.isEmpty()) {
                        continue;
                    }
                    for (OrclassProfilsAcces pa : listPa) {
                        /*
                        l access au profil existe on verifit si l a deja ete attribuer a un utilisateur 
                         */
                        listUa = utilisateursAccesDao.getAllAccessUserByProfilAccess(pa);
                        if (listUa.isEmpty()) {
                            /*
                            qucun utilisateur n a access a ce profile on supprime directement cette fonctionnalite lie a ce profile
                             */
//                            System.out.println("idPa:" + pa.getIdProfilAcces() + "retour :" + con.deleteProfilAcces(pa));
//                            con.deleteProfilAcces(pa);
                            continue;
                        }
                        for (OrclassUtilisateursAcces uAccess : listUa) {
//                            System.out.println(" suprimer User access " + uAccess.getIdUtilisateurAcces() + "retour :" + con.deleteUserAccess(ua));

                        }

                    }
                }

            }
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, exception.Success.OPERATION_SUCCESS.toString(), null));
        }
    }

    public DualListModel<OrclassFonctionnalites> getFonctionaliteModel() {
        this.populateFonctionnalite();
        fonctionaliteModel = new DualListModel<>(menusSource, menusTarget);
        return fonctionaliteModel;
    }

    public void setFonctionaliteModel(DualListModel<OrclassFonctionnalites> fonctionaliteModel) {
        this.fonctionaliteModel = fonctionaliteModel;
    }

    public void populateFonctionnalite() {

        Collection<OrclassFonctionnalites> menu1 = new ArrayList<>();
        Collection<OrclassFonctionnalites> menu2 = new ArrayList<>();
//        menusSource.clear();
//        menusTarget.clear();
        if (profilsByUser == null || profilsByUser.getIdProfil() == null && this.listeModuleSaveForFonctionnalite.isEmpty()) {

            fonctionaliteModel.setTarget(new ArrayList<OrclassFonctionnalites>());
        } else if (profilsByUser == null || profilsByUser.getIdProfil() == null && !this.listeModuleSaveForFonctionnalite.isEmpty()) {
            if (listeModuleSaveForFonctionnalite.contains(moduleSelectForTarget) == Boolean.TRUE) {
                fonctionaliteModel.setTarget(new ArrayList<OrclassFonctionnalites>());
                int j = 0;
                Boolean etat = Boolean.TRUE;
                OrclassFonctionnalites f;
                Collection<OrclassFonctionnalites> menu22 = new ArrayList<>();
                while (etat) {
                    f = tableauFonctinnaliteBymodule[moduleSelectForTarget.getIndiceTableau()][j];
                    if (f == null || f.getIdFonctionnalite() == null) {
                        etat = Boolean.FALSE;
                        break;
                    }
                    menu22.add(f);

                    j++;
                }
                if (!menu22.isEmpty()) {
                    fonctionaliteModel.setTarget((List<OrclassFonctionnalites>) menu22);
                }

            } else {
                fonctionaliteModel.setTarget(new ArrayList<OrclassFonctionnalites>());
            }
        }

        if (moduleSelectForTarget != null && moduleSelectForTarget.getIdModule() != null && (profilsByUser == null || profilsByUser.getIdProfil() == null)) {
            if (fonctionaliteModel != null && !fonctionaliteModel.getSource().isEmpty()) {
                menusSource = new ArrayList<>();
                menusTarget = new ArrayList<>();
                menu1 = securiteService.getFonctionaliteHaveAction(moduleSelectForTarget);
                menusTarget = fonctionaliteModel.getTarget();
                if (menu1.isEmpty() == false) {
                    for (OrclassFonctionnalites f : menusTarget) {
                        if (menu1.contains(f) == true) {
                            menu1.remove(f);
                        }
                    }
                    menusSource.addAll(menu1);
                }
            } else if (fonctionaliteModel != null && fonctionaliteModel.getSource().isEmpty()) {
                menusSource = new ArrayList<>();
                menusTarget = new ArrayList<>();
                menu1 = securiteService.getFonctionaliteHaveAction(moduleSelectForTarget);
                menusTarget = fonctionaliteModel.getTarget();
                if (menu1.isEmpty() == false) {
                    for (OrclassFonctionnalites f : menusTarget) {
                        if (menu1.contains(f) == true) {
                            menu1.remove(f);
                        }
                    }
                    menusSource.addAll(menu1);
                }
            } else {
                menusSource = new ArrayList<>();
                menusTarget = new ArrayList<>();
            }

        } else if (profilsByUser != null && profilsByUser.getIdProfil() != null && moduleSelectForTarget != null && moduleSelectForTarget.getIdModule() != null) {
            menusSource = new ArrayList<>();
            menusTarget = new ArrayList<>();
            menusTarget = (List<OrclassFonctionnalites>) securiteService.getFonctionnaliteByProfil(profilsByUser, moduleSelectForTarget);
            menu1 = securiteService.getFonctionaliteHaveAction(moduleSelectForTarget);
            for (OrclassFonctionnalites f : menusTarget) {
                if (menu1.contains(f) == true) {
                    menu1.remove(f);
                }
            }

            if (menu1.isEmpty() == false) {
                menusSource.addAll(menu1);
            }

        } else {
            menusSource = new ArrayList<>();
            menusTarget = new ArrayList<>();
        }
// {
//            menusSource = new ArrayList<>();
//            menusTarget = new ArrayList<>();
//        }
    }
    
    public String retourneValeur(Object object){
        return GlobalFonctions.valueObject(object);
    }

    public void showDetails(OrclassProfils item) {
        if (profilsByUser == null || profilsByUser.getIdProfil() == null) {
            this.setProfilsByUser(item);
        }
        this.chargeModuleTransfert();
        moduleSelectForTarget = new OrclassModules();
    }

    public List<OrclassFonctionnalites> getColFonctionnaliteByUser() {
        return colFonctionnaliteByUser;
    }

    public void setColFonctionnaliteByUser(List<OrclassFonctionnalites> colFonctionnaliteByUser) {
        this.colFonctionnaliteByUser = colFonctionnaliteByUser;
    }

    public List<OrclassActions> getActionSource() {
        return actionSource;
    }

    public void setActionSource(List<OrclassActions> actionSource) {
        this.actionSource = actionSource;
    }

    public List<OrclassActions> getActionTarget() {
        return actionTarget;
    }

    public void setActionTarget(List<OrclassActions> actionTarget) {
        this.actionTarget = actionTarget;
    }

    public OrclassProfils getProfilsByUser() {
        return profilsByUser;
    }

    public void setProfilsByUser(OrclassProfils profilsByUser) {
        this.profilsByUser = profilsByUser;
    }

    public OrclassModules getModuleSelectForTarget() {
        return moduleSelectForTarget;
    }

    public void setModuleSelectForTarget(OrclassModules moduleSelectForTarget) {
        this.moduleSelectForTarget = moduleSelectForTarget;
    }

    public List<OrclassModules> getModulesTarget() {
        return modulesTarget;
    }

    public void setModulesTarget(List<OrclassModules> modulesTarget) {
        this.modulesTarget = modulesTarget;
    }

    public List<OrclassProfils> getListeProfileAccesByEntreprise() {
        return listeProfileAccesByEntreprise;
    }

    public void setListeProfileAccesByEntreprise(List<OrclassProfils> listeProfileAccesByEntreprise) {
        this.listeProfileAccesByEntreprise = listeProfileAccesByEntreprise;
    }

    public List<OrclassProfils> getFilteredProfilAcces() {
        return filteredProfilAcces;
    }

    public void setFilteredProfilAcces(List<OrclassProfils> filteredProfilAcces) {
        this.filteredProfilAcces = filteredProfilAcces;
    }

    public List<OrclassModules> getListeModuleSaveForFonctionnalite() {
        return listeModuleSaveForFonctionnalite;
    }

    public void setListeModuleSaveForFonctionnalite(List<OrclassModules> listeModuleSaveForFonctionnalite) {
        this.listeModuleSaveForFonctionnalite = listeModuleSaveForFonctionnalite;
    }

    public List<OrclassModules> getModuleTransFert() {
        return moduleTransFert;
    }

    public void setModuleTransFert(List<OrclassModules> moduleTransFert) {
        this.moduleTransFert = moduleTransFert;
    }

    public OrclassFonctionnalites getFonctionnaliteByUser() {
        return fonctionnaliteByUser;
    }

    public void setFonctionnaliteByUser(OrclassFonctionnalites fonctionnaliteByUser) {
        this.fonctionnaliteByUser = fonctionnaliteByUser;
    }

    public Boolean getActiveAction() {
        if (profilsByUser != null && profilsByUser.getIdProfil() != null) {
            activeAction = securiteService.checkEtatProfilExisteForUserAccess(profilsByUser);
        }
        return activeAction;
    }

    public void setActiveAction(Boolean activeAction) {
        this.activeAction = activeAction;
    }

    public List<OrclassFonctionnalites> getFonctionnaliteTransFert() {
        return fonctionnaliteTransFert;
    }

    public void setFonctionnaliteTransFert(List<OrclassFonctionnalites> fonctionnaliteTransFert) {
        this.fonctionnaliteTransFert = fonctionnaliteTransFert;
    }

    public void setActionModel(DualListModel<OrclassActions> actionModel) {
        this.actionModel = actionModel;
    }

    public void setFonctionnaliteModel(DualListModel<OrclassFonctionnalites> fonctionnaliteModel) {
        this.fonctionnaliteModel = fonctionnaliteModel;
    }

    public Boolean getAdminNotUpdate() {
        if (profilsByUser != null && profilsByUser.getIdProfil() != null && profilsByUser.getCode().equals("admin")) {
            adminNotUpdate = Boolean.TRUE;
        } else {
            adminNotUpdate = Boolean.FALSE;
        }
        return adminNotUpdate;
    }

    public void setAdminNotUpdate(Boolean adminNotUpdate) {
        this.adminNotUpdate = adminNotUpdate;
    }

    public OrclassProfils getProfilsByUserForDelete() {
        return profilsByUserForDelete;
    }

    public void setProfilsByUserForDelete(OrclassProfils profilsByUserForDelete) {
        this.profilsByUserForDelete = profilsByUserForDelete;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public List<ServiceProfil> getListeProfilsSelected() {
        return listeProfilsSelected;
    }

    public void setListeProfilsSelected(List<ServiceProfil> listeProfilsSelected) {
        this.listeProfilsSelected = listeProfilsSelected;
    }

    public ServiceDepartement getServiceDepartement() {
        return serviceDepartement;
    }

    public void setServiceDepartement(ServiceDepartement serviceDepartement) {
        this.serviceDepartement = serviceDepartement;
    }

    public List<ServiceProfil> getFilteredServiceProfil() {
        return filteredServiceProfil;
    }

    public void setFilteredServiceProfil(List<ServiceProfil> filteredServiceProfil) {
        this.filteredServiceProfil = filteredServiceProfil;
    }

}
