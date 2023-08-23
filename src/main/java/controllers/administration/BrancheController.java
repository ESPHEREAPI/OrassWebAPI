///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.administration;
//
//import Entreprise.OracleConnection;
//import dao.OrclassBranchesDao;
//import dao.OrclassClassesDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleAdministration;
//import enums.LibelleBranche;
//import enums.LibelleClasse;
//import java.io.Serializable;
//import java.math.BigInteger;
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
//import modele.OrclassBranches;
//import modele.OrclassClasses;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassUtilisateurs;
//import org.primefaces.PrimeFaces;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import utils.GlobalFonctions;
//import utils.LocaleHelper;
//import utils.RecupBundle;
//
///**
// *
// * @author hp
// */
//@Named(value = "brancheController")
//@ViewScoped
//public class BrancheController implements Serializable {
//
//    private static final Logger logger = LoggerFactory.getLogger(BrancheController.class);
//
//    @EJB
//    OrclassBranchesDao branchesDao;
//    OrclassBranches branches;
//    OrclassBranches branchesSelect;
//    @EJB
//    OrclassClassesDao classesDao;
//    private Collection<OrclassBranches> lisBranche = new ArrayList<>();
//    private List<OrclassBranches> filterBranche = new ArrayList<>();
//    private Collection<OrclassClasses> lisClass = new ArrayList<>();
//    private OrclassEntreprises entreprise;
//
//    String summary = "";
//    String msgdetail = "";
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//    OrclassUtilisateurs utilisateurs;
//    @EJB
//    IDroitAcces serviceAccess;
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//
//    /**
//     * Creates a new instance of BrancheController
//     */
//    public BrancheController() {
//        branches = new OrclassBranches();
//    }
//
//    public void reset() {
//
//        this.init();
//        this.chargeClasseByEntreprise(entreprise);
//        this.updateTableBranche();
//        PrimeFaces.current().ajax().update("form1,form2");
//    }
//
//    public void updateTableBranche() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:table");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('brancheTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public void init() {
//        branches = new OrclassBranches();
//
//    }
//
//    public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_branche.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);
//
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean accessModifier() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_branche.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public void chargeClasseByEntreprise(OrclassEntreprises e) {
//        if (e != null && e.getIdEntreprise() != null) {
//            lisBranche = branchesDao.listAllBrancheShowAllCompagnie();
//            lisBranche.addAll(branchesDao.getAllBrancheByEntreprise(e));
//            lisClass = (classesDao.listAllClassesShowAllCompagnie());
//            lisClass.addAll(classesDao.getAllClasseByEntreprise(e));
//        }
//    }
//
//    public String valueObjectByLibelleAutres(OrclassBranches cl) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        if (cl != null && cl.getLibelle().equals(LibelleBranche.autres)) {
//            return cl.getLibelleAutre();
//        }
//        if (cl != null) {
//            return LocaleHelper.getLocaleString(RecupBundle.FichierBundle, cl.getLibelle().toString(), null, myLoc);
//        }
//        return "";
//    }
//
//    public List<SelectItem> getTypeLibelleBrancheForCreer() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        if (branches != null && branches.getIdBranche() != null) {
//            items.add(new SelectItem(branches.getLibelle(), LocaleHelper.getLocaleString(RecupBundle.FichierBundle, branches.getLibelle().name(), null, myLoc)));
//        } else {
//            for (LibelleBranche tb : LibelleBranche.values()) {
//                if (tb.equals(LibelleBranche.autres)) {
//                    items.add(new SelectItem(tb, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, tb.name(), null, myLoc)));
//                } else {
//                    if (branchesDao.findEntityHavingValue("libelle", tb) == null) {
//                        items.add(new SelectItem(tb, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, tb.name(), null, myLoc)));
//                    }
//                }
//
//            }
//        }
//
//        return items;
//    }
//
//    public Boolean chargLibelleBranche() {
//        if (branches.getLibelle() != null) {
//            if (branches.getLibelle().equals(LibelleBranche.autres)) {
//                return Boolean.TRUE;
//            }
//        }
//        return Boolean.FALSE;
//    }
//
//    public String valueObjectByLibelleAutresClasse(OrclassClasses cl) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        try {
//            if (cl != null && cl.getLibelle().equals(LibelleClasse.autres)) {
//                return cl.getLibelleAutre();
//            } else if (cl != null) {
//
//                return LocaleHelper.getLocaleString(RecupBundle.FichierBundle, cl.getLibelle().toString(), null, myLoc);
//            }
//        } catch (Exception e) {
//            return "";
//        }
//        return "";
//
//    }
//    public void chargeDialog(){
//         PrimeFaces.current().executeScript("PF('manageBrancheDialog').show();");
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
//        this.chargeClasseByEntreprise(entreprise);
//        menu = menusDao.findEntityHavingValue("code", "branche");
//        module = modulesDao.findEntityHavingValue("code", "mod.admin");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        this.updateTableBranche();
//    }
//
//    public String addBranche() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "branche", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (branchesDao.findEntityHavingValue("code", branches.getCode()) == null && branches.getCode()!=null) {
//                branches.setIdEntreprise(entreprise);
//                branches.setCodePrincipal(new BigInteger(branches.getCode().trim()));
//                branchesDao.create(branches);
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "VALUE IS NULL"));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "branche", exception.Error.FATAL_ERROR + "", new Object[]{"Branche"});
//            logger.error("Erreur Survenu", th);
//        }
//        this.reset();
//        PrimeFaces.current().executeScript("PF('manageBrancheDialog').show();");
//        return null;
//    }
//
//    public String deleteBranche() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "branche", null, myLoc)};
//        OracleConnection con = new OracleConnection();
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (branches != null && branches.getIdBranche() != null) {
//
////                branchesDao.delete(branches);
//                con.deleteBranche(branches);
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "branche", exception.Error.FATAL_ERROR + "", new Object[]{"branche"});
//            logger.error("Erreur Survenu", th);
//        }
//        lisBranche.remove(branches);
//        this.reset();
//        return null;
//    }
//
//    public String updateBranche() {
//        String success = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        //Locale myLoc =new Locale("fr");
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "branche", null, myLoc)};
//        String[] detail = {entete[0], "Parametres"};
//
//        try {
//
//            if (branches != null && branches.getIdBranche() != null) {
//                branches.setDateModification(new Date());
//                  branches.setCodePrincipal(new BigInteger(branches.getCode().trim()));
//                branchesDao.update(branches);
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
//        reset();
//        return null;
//
//    }
//
//    public void showDetails(OrclassBranches item) {
//        if (branches == null || branches.getIdBranche() == null) {
//            this.setBranches(item);
//
//        }
//        this.setBranches(branchesSelect);
//
//    }
//
//    public OrclassBranches getBranches() {
//        return branches;
//    }
//
//    public void setBranches(OrclassBranches branches) {
//        this.branches = branches;
//    }
//
//    public Collection<OrclassBranches> getLisBranche() {
//        return lisBranche;
//    }
//
//    public void setLisBranche(Collection<OrclassBranches> lisBranche) {
//        this.lisBranche = lisBranche;
//    }
//
//    public List<OrclassBranches> getFilterBranche() {
//        return filterBranche;
//    }
//
//    public void setFilterBranche(List<OrclassBranches> filterBranche) {
//        this.filterBranche = filterBranche;
//    }
//
//    public Collection<OrclassClasses> getLisClass() {
//        return lisClass;
//    }
//
//    public void setLisClass(Collection<OrclassClasses> lisClass) {
//        this.lisClass = lisClass;
//    }
//
//    public OrclassBranches getBranchesSelect() {
//        return branchesSelect;
//    }
//
//    public void setBranchesSelect(OrclassBranches branchesSelect) {
//        this.branchesSelect = branchesSelect;
//    }
//
//}
