///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import Entreprise.OracleConnection;
//import dao.OrclassDetailPlafondMaladieDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassPlafondMaladieDao;
//import dao.OrclassRubriqueCategorieDao;
//import dao.OrclassRubriquePrestationDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.DomaineApplication;
//import enums.FonctionnaliteModuleParametrage;
//import enums.ModeCalculDetailMaladie;
//import enums.ModeControle;
//import enums.TypeDetailMaladie;
//import enums.UniteDuree;
//import exception.GlobalException;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.application.FacesMessage;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.model.SelectItem;
//import javax.inject.Named;
//import javax.faces.view.ViewScoped;
//import javax.validation.ConstraintViolationException;
//import modele.OrclassActions;
//import modele.OrclassCategories;
//import modele.OrclassDetailPlafondMaladie;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassMenus;
//import modele.OrclassModules;
//import modele.OrclassPlafondMaladie;
//import modele.OrclassRubrique;
//import modele.OrclassRubriqueCategorie;
//import modele.OrclassRubriquePrestation;
//import modele.OrclassUtilisateurs;
//import org.primefaces.PrimeFaces;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import parametrage.ISecurite;
//import utils.GlobalFonctions;
//import utils.LocaleHelper;
//import utils.RecupBundle;
//
///**
// *
// * @author hp
// */
//@Named(value = "plafondMaladieController")
//@ViewScoped
//public class PlafondMaladieController implements Serializable {
//
//    /**
//     * Creates a new instance of PlafondMaladieController
//     */
//    private static final Logger logger = LoggerFactory.getLogger(PlafondMaladieController.class);
//    @EJB
//    OrclassRubriqueCategorieDao rubriqueCategorieDao;
//    OrclassRubriqueCategorie rubriqueCategorie;
//    OrclassEntreprises entreprise;
//    @EJB
//    OrclassPlafondMaladieDao plafondMaladieDao;
//    OrclassPlafondMaladie plafondMaladie;
//    OrclassPlafondMaladie plafondMaladieSelecte;
//    OrclassPlafondMaladie plafondMaladieSelectEdit;
//    @EJB
//    OrclassDetailPlafondMaladieDao detailPlafondMaladieDao;
//    OrclassDetailPlafondMaladie detailPlafondMaladie;
//    OrclassDetailPlafondMaladie detailPlafondMaladieSelecte;
//
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    IDroitAcces serviceAccess;
//    @EJB
//    OrclassRubriquePrestationDao rubriquePrestationDao;
//    OrclassRubriquePrestation rubriquePrestation;
//
//    OrclassCategories categories;
//    @EJB
//    ISecurite securiteService;
//
//    private Collection<OrclassCategories> lisCategories = new ArrayList<>();
//    private Collection<OrclassPlafondMaladie> lisPlafondMaladie = new ArrayList<>();
//    private List<OrclassPlafondMaladie> filterPlafondMaladie = new ArrayList<>();
//    private List<OrclassDetailPlafondMaladie> filterDetailPlafondMaladie = new ArrayList<>();
//    private Collection<OrclassDetailPlafondMaladie> lisDetailPlafondMaladie = new ArrayList<>();
//    private Collection<OrclassRubriquePrestation> listRubriquePrestation = new ArrayList<>();
//    private Collection<OrclassDetailPlafondMaladie> listDetailRubriqueMaladies = new ArrayList<>();
//    private List<OrclassRubriquePrestation> selecteRubriquePrestation = new ArrayList<>();
//    private Boolean controleCouverture=Boolean.FALSE;
//
//    String summary = "";
//    String msgdetail = "";
//
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//
//    public PlafondMaladieController() {
//        plafondMaladie = new OrclassPlafondMaladie();
//        detailPlafondMaladie = new OrclassDetailPlafondMaladie();
//        rubriqueCategorie = new OrclassRubriqueCategorie();
//        categories = new OrclassCategories();
//        detailPlafondMaladieSelecte = new OrclassDetailPlafondMaladie();
//        plafondMaladieSelectEdit = new OrclassPlafondMaladie();
//
//    }
//
//    @PostConstruct
//    void initialiseSession() {
//        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
//        if (entreprise == null) {
//            entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
//
//        }
//
//        menu = menusDao.findEntityHavingValue("code", "exoneration_taxe");
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        lisPlafondMaladie = plafondMaladieDao.listePlafondMaladie(entreprise);
//        lisCategories = rubriquePrestationDao.listeCategorieSanteByCompagnies(entreprise);
//        this.updatePlafondMaladie();
//        this.updatedDtailPlafondMaladieSelecte();
//
//    }
//    public void controleMode(){
//        if (plafondMaladie.getModeControle()!=null && plafondMaladie.getModeControle().equals(ModeControle.periode_couverture)) {
//            controleCouverture=Boolean.TRUE;
//        }else{
//           controleCouverture=Boolean.FALSE; 
//        }
//        PrimeFaces.current().ajax().update(":form111");
//        PrimeFaces.current().executeScript("PF('managePlafondDialog').show()");
//    }
//
//    public void changeTabPrincipal() {
//
//        plafondMaladieSelecte = new OrclassPlafondMaladie();
//        selecteRubriquePrestation = new ArrayList<>();
//        detailPlafondMaladieSelecte = new OrclassDetailPlafondMaladie();
//        detailPlafondMaladie = new OrclassDetailPlafondMaladie();
//        plafondMaladie = new OrclassPlafondMaladie();
//
//    }
//
//    public void chrageRubiquePrestationByCategories() {
//        listRubriquePrestation.clear();
//        List<OrclassRubrique> listRubrique = new ArrayList<>();
//        if (categories != null && categories.getIdCategorie() != null) {
//            for (OrclassRubriquePrestation rp : rubriquePrestationDao.listeRubriPrestationByCategorie(categories, entreprise)) {
//                if (listRubrique.contains(rp.getIdRubriqueCategorie().getIdRubrique()) == Boolean.FALSE) {
//                    listRubriquePrestation.add(rp);
//                }
//                listRubrique.add(rp.getIdRubriqueCategorie().getIdRubrique());
//            }
//
//        }
//        PrimeFaces.current().executeScript("PF('managePlafondDialog').show()");
//    }
//
//    public void chargeDetailPlafondMaladiesByPlafondMaladie() {
//        lisDetailPlafondMaladie.clear();
//        if (plafondMaladieSelecte != null && plafondMaladieSelecte.getId() != null) {
//            lisDetailPlafondMaladie = detailPlafondMaladieDao.listeDetailPlafondMaladieByPlafondMalaide(plafondMaladieSelecte, entreprise);
//        }
//        this.updateTableDetailPlafondMaladie();
//    }
//
//    public String addPlafondMaladie() {
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "plafond.Maladie", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (plafondMaladie.getCode()==null || plafondMaladie.getCode()=="") {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("VALUE IS EMPTY PLEASE WRITE VALUE"));
//                return null ;
//            }
//            if (plafondMaladieDao.finkey(plafondMaladie.getCode(), entreprise) == null && plafondMaladie.getCode() != null) {
//                securiteService.addPlafondMaladie(plafondMaladie, selecteRubriquePrestation, detailPlafondMaladie, entreprise);
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "CODE IS NULL"));
//            }
//        } catch (GlobalException e) {
//
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCode(), e.getCode()));
////throw e;
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_FATAL, "plafond.Maladie", exception.Error.FATAL_ERROR + "", new Object[]{"classe"});
//            logger.error("Erreur Survenu", th);
//        }
//        lisPlafondMaladie = plafondMaladieDao.listePlafondMaladie(entreprise);
//        this.updateTablePlafondMaladie();
//        plafondMaladie = new OrclassPlafondMaladie();
//        detailPlafondMaladie = new OrclassDetailPlafondMaladie();
//        PrimeFaces.current().executeScript("PF('managePlafondDialog').show()");
////         this.reset();
//        return null;
//
//    }
//
//    public void showDetail(OrclassPlafondMaladie item) {
//        if (plafondMaladieSelectEdit == null || plafondMaladieSelectEdit.getId() == null) {
//            this.setPlafondMaladieSelectEdit(item);
//            PrimeFaces.current().executeScript("PF('manageUpdateDialog').show()");
//        }
//
//    }
//
//    public String updatePlafondMaladie() {
//        String success = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        //Locale myLoc =new Locale("fr");
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "plafond.Maladie", null, myLoc)};
//        String[] detail = {entete[0], "Parametres"};
//
//        try {
//
//            if (plafondMaladieSelectEdit != null && plafondMaladieSelectEdit.getId() != null) {
//
//                plafondMaladie.setDateModification(new Date());
//                plafondMaladieDao.update(plafondMaladieSelectEdit);
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + exception.Success.OPERATION_SUCCESS.toString(), entete, myLoc);
//
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
//
//            }
//
//        } catch (Throwable th) {
//
//            //ecrire dans le fichier de log  
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.error", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));
//
//        }
//
//        lisPlafondMaladie = plafondMaladieDao.listePlafondMaladie(entreprise);
//
//        this.updateTablePlafondMaladie();
//
//        return null;
//
//    }
//        public List<SelectItem> getUniteDuree() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (UniteDuree ud : UniteDuree.values()) {
//            items.add(new SelectItem(ud, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, ud.name(), null, myLoc)));
//        }
//
//        return items;
//    }
//
//    public String deletePlafondMaladie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "plafond.Maladie", null, myLoc)};
//        OracleConnection con = new OracleConnection();
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (plafondMaladieSelectEdit != null && plafondMaladieSelectEdit.getId() != null) {
//                con.deleteDetailPlafondMaladieByPlafondMaladie(plafondMaladieSelectEdit);
//                plafondMaladieDao.delete(plafondMaladieSelectEdit);
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.delete.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "duree", exception.Error.FATAL_ERROR + "", new Object[]{"duree"});
//            logger.error("Erreur Survenu", th);
//        }
//        lisPlafondMaladie = plafondMaladieDao.listePlafondMaladie(entreprise);
//
//        this.updateTablePlafondMaladie();
//        PrimeFaces.current().ajax().update(":tabprincipal:form1:idpmgTable");
//        PrimeFaces.current().ajax().update(":tabprincipal:form1:msg");
//        return null;
//    }
//
//    public String updatedDtailPlafondMaladieSelecte() {
//        String success = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        //Locale myLoc =new Locale("fr");
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "plafond.Maladie", null, myLoc)};
//        String[] detail = {entete[0], "Parametres"};
//
//        try {
//
//            if (detailPlafondMaladieSelecte != null && detailPlafondMaladieSelecte.getId() != null) {
//
//                detailPlafondMaladieSelecte.setDateModification(new Date());
//                detailPlafondMaladieDao.update(detailPlafondMaladieSelecte);
//                listDetailRubriqueMaladies = detailPlafondMaladieDao.listeDetailPlafondMaladieByPlafondMalaide(plafondMaladieSelecte, entreprise);
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + exception.Success.OPERATION_SUCCESS.toString(), entete, myLoc);
//
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
//
//            }
//
//        } catch (Throwable th) {
//
//            //ecrire dans le fichier de log  
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.error", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));
//
//        }
//
//        this.updateTableDetailPlafondMaladie();
//        return null;
//
//    }
//
//    public String deleteDetailPlafondMaladie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "plafond.Maladie", null, myLoc)};
//        OracleConnection con = new OracleConnection();
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (detailPlafondMaladieSelecte != null && detailPlafondMaladieSelecte.getId() != null) {
//                detailPlafondMaladieDao.delete(detailPlafondMaladieSelecte);
//                listDetailRubriqueMaladies = detailPlafondMaladieDao.listeDetailPlafondMaladieByPlafondMalaide(plafondMaladieSelecte, entreprise);
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.delete.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "plafond.Maladie", exception.Error.FATAL_ERROR + "", new Object[]{"plafond.Maladie"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        this.updateTableDetailPlafondMaladie();
//        return null;
//    }
//
//    public void initial() {
//
//        rubriquePrestation = new OrclassRubriquePrestation();
//        selecteRubriquePrestation = new ArrayList<>();
//
//        plafondMaladie = new OrclassPlafondMaladie();
//        detailPlafondMaladie = new OrclassDetailPlafondMaladie();
//        rubriqueCategorie = new OrclassRubriqueCategorie();
//        categories = new OrclassCategories();
//        detailPlafondMaladieSelecte = new OrclassDetailPlafondMaladie();
//    }
//
//    public void reset() {
//        initial();
//
//        this.updateTableDetailPlafondMaladie();
//        this.updateTablePlafondMaladie();
//        PrimeFaces.current().ajax().update(":form111");
//
//    }
//
//    public List<SelectItem> getDomaineApplicationPlafondMaladie() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (DomaineApplication dm : DomaineApplication.values()) {
//            items.add(new SelectItem(dm, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, dm.name(), null, myLoc)));
//
//        }
//        return items;
//    }
//
//    public List<SelectItem> getModeControle() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (ModeControle dm : ModeControle.values()) {
//            items.add(new SelectItem(dm, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, dm.name(), null, myLoc)));
//
//        }
//        return items;
//    }
//
//    public List<SelectItem> getModeCalculDetailPlafondMaladie() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (ModeCalculDetailMaladie dm : ModeCalculDetailMaladie.values()) {
//            items.add(new SelectItem(dm, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, dm.name(), null, myLoc)));
//
//        }
//        return items;
//    }
//
//    public List<SelectItem> getTypeDetailPlafondMaladie() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (TypeDetailMaladie dm : TypeDetailMaladie.values()) {
//            items.add(new SelectItem(dm, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, dm.name(), null, myLoc)));
//
//        }
//        return items;
//    }
//
//    public OrclassRubriqueCategorie getRubriqueCategorie() {
//        return rubriqueCategorie;
//    }
//
//    public void setRubriqueCategorie(OrclassRubriqueCategorie rubriqueCategorie) {
//        this.rubriqueCategorie = rubriqueCategorie;
//    }
//
//    public OrclassPlafondMaladie getPlafondMaladie() {
//        return plafondMaladie;
//    }
//
//    public void setPlafondMaladie(OrclassPlafondMaladie plafondMaladie) {
//        this.plafondMaladie = plafondMaladie;
//    }
//
//    public OrclassDetailPlafondMaladie getDetailPlafondMaladie() {
//        return detailPlafondMaladie;
//    }
//
//    public void setDetailPlafondMaladie(OrclassDetailPlafondMaladie detailPlafondMaladie) {
//        this.detailPlafondMaladie = detailPlafondMaladie;
//    }
//
//    public OrclassDetailPlafondMaladie getDetailPlafondMaladieSelecte() {
//        return detailPlafondMaladieSelecte;
//    }
//
//    public void setDetailPlafondMaladieSelecte(OrclassDetailPlafondMaladie detailPlafondMaladieSelecte) {
//        this.detailPlafondMaladieSelecte = detailPlafondMaladieSelecte;
//    }
//
//    public OrclassRubriquePrestation getRubriquePrestation() {
//        return rubriquePrestation;
//    }
//
//    public void setRubriquePrestation(OrclassRubriquePrestation rubriquePrestation) {
//        this.rubriquePrestation = rubriquePrestation;
//    }
//
//    public Collection<OrclassCategories> getLisCategories() {
//        return lisCategories;
//    }
//
//    public void setLisCategories(Collection<OrclassCategories> lisCategories) {
//        this.lisCategories = lisCategories;
//    }
//
//    public Collection<OrclassPlafondMaladie> getLisPlafondMaladie() {
//        return lisPlafondMaladie;
//    }
//
//    public void setLisPlafondMaladie(Collection<OrclassPlafondMaladie> lisPlafondMaladie) {
//        this.lisPlafondMaladie = lisPlafondMaladie;
//    }
//
//    public List<OrclassPlafondMaladie> getFilterPlafondMaladie() {
//        return filterPlafondMaladie;
//    }
//
//    public void setFilterPlafondMaladie(List<OrclassPlafondMaladie> filterPlafondMaladie) {
//        this.filterPlafondMaladie = filterPlafondMaladie;
//    }
//
//    public Collection<OrclassDetailPlafondMaladie> getLisDetailPlafondMaladie() {
//        return lisDetailPlafondMaladie;
//    }
//
//    public void setLisDetailPlafondMaladie(Collection<OrclassDetailPlafondMaladie> lisDetailPlafondMaladie) {
//        this.lisDetailPlafondMaladie = lisDetailPlafondMaladie;
//    }
//
//    public Collection<OrclassDetailPlafondMaladie> getListDetailRubriqueMaladies() {
//        return listDetailRubriqueMaladies;
//    }
//
//    public void setListDetailRubriqueMaladies(Collection<OrclassDetailPlafondMaladie> listDetailRubriqueMaladies) {
//        this.listDetailRubriqueMaladies = listDetailRubriqueMaladies;
//    }
//
//    public List<OrclassRubriquePrestation> getSelecteRubriquePrestation() {
//        return selecteRubriquePrestation;
//    }
//
//    public void setSelecteRubriquePrestation(List<OrclassRubriquePrestation> selecteRubriquePrestation) {
//        this.selecteRubriquePrestation = selecteRubriquePrestation;
//    }
//
//    public OrclassCategories getCategories() {
//        return categories;
//    }
//
//    public void setCategories(OrclassCategories categories) {
//        this.categories = categories;
//    }
//
//    public Collection<OrclassRubriquePrestation> getListRubriquePrestation() {
//        return listRubriquePrestation;
//    }
//
//    public void setListRubriquePrestation(Collection<OrclassRubriquePrestation> listRubriquePrestation) {
//        this.listRubriquePrestation = listRubriquePrestation;
//    }
//
//    public void updateTablePlafondMaladie() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("tabprincipal:form1:idpmgTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('pmTable').clearFilters();");
//
//    }
//
//    public void updateTableDetailPlafondMaladie() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("tabprincipal:form2:iddpmgTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('dpmTable').clearFilters();");
//
//    }
//
//    public OrclassPlafondMaladie getPlafondMaladieSelecte() {
//        return plafondMaladieSelecte;
//    }
//
//    public void setPlafondMaladieSelecte(OrclassPlafondMaladie plafondMaladieSelecte) {
//        this.plafondMaladieSelecte = plafondMaladieSelecte;
//    }
//
//    public OrclassPlafondMaladie getPlafondMaladieSelectEdit() {
//        return plafondMaladieSelectEdit;
//    }
//
//    public void setPlafondMaladieSelectEdit(OrclassPlafondMaladie plafondMaladieSelectEdit) {
//        this.plafondMaladieSelectEdit = plafondMaladieSelectEdit;
//    }
//
//    public List<OrclassDetailPlafondMaladie> getFilterDetailPlafondMaladie() {
//        return filterDetailPlafondMaladie;
//    }
//
//    public void setFilterDetailPlafondMaladie(List<OrclassDetailPlafondMaladie> filterDetailPlafondMaladie) {
//        this.filterDetailPlafondMaladie = filterDetailPlafondMaladie;
//    }
//
//    public Boolean accessCreer() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.plafond_maladie.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);
//            System.out.println("accees supprimer :" + serviceAccess.accessAction(user, action, menu));
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean accessAjouter() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.plafond_maladie.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.ajouter.name(), fon);
//            System.out.println("accees supprimer :" + serviceAccess.accessAction(user, action, menu));
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//     public Boolean accessSupprimer() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.plafond_maladie.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.ajouter.name(), fon);
//            System.out.println("accees supprimer :" + serviceAccess.accessAction(user, action, menu));
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//     
//       public Boolean accessModifier() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.plafond_maladie.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
//            System.out.println("accees supprimer :" + serviceAccess.accessAction(user, action, menu));
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean getControleCouverture() {
//        return controleCouverture;
//    }
//
//    public void setControleCouverture(Boolean controleCouverture) {
//        this.controleCouverture = controleCouverture;
//    }
//       
//       
//
//}
