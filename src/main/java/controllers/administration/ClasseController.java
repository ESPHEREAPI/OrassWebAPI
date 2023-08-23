///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.administration;
//
//import dao.OrclassClassesDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleAdministration;
//import enums.LibelleBranche;
//import enums.LibelleClasse;
//import enums.TypeBureau;
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
//@Named(value = "classeController")
//@ViewScoped
//public class ClasseController implements Serializable {
//
//    private static final Logger logger = LoggerFactory.getLogger(ClasseController.class);
//    /**
//     * Creates a new instance of ClasseController
//     */
//    @EJB
//    OrclassClassesDao classesDao;
//    OrclassClasses classes;
//      OrclassClasses classesSlecte;
//    private List<OrclassClasses> lisClass = new ArrayList<>();
//    private List<OrclassClasses> filterClass = new ArrayList<>();
//    private OrclassEntreprises entreprise;
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
//    String summary = "";
//    String msgdetail = "";
//
//    public ClasseController() {
//        classes = new OrclassClasses();
//    }
//
//    public void reset() {
//
//        this.init();
//        this.chargeClasseByEntreprise(entreprise);
//        this.updateTableClasse();
//        PrimeFaces.current().ajax().update("@(form)");
//    }
//
//    public void init() {
//        classes = new OrclassClasses();
//        classesSlecte=new OrclassClasses();
//
//    }
//
//    public void chargeClasseByEntreprise(OrclassEntreprises e) {
//        lisClass = classesDao.listAllClassesShowAllCompagnie();
//        if (e != null && e.getIdEntreprise() != null) {
//            List<OrclassClasses> lisClass2 = new ArrayList<>();
//            lisClass2 = classesDao.listAllClassesByCompagnie(e);
//            lisClass.addAll(lisClass2);
//        }
//    }
//
//    public void updateTableClasse() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:idclassTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('classTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    public void charge(){
//        PrimeFaces.current().executeScript("PF('manageClasseDialog').show();");
//    }
//
//    public String valueObjectByLibelleAutres(OrclassClasses cl) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        try {
//            if (cl != null && cl.getLibelle().equals(LibelleClasse.autres)) {
//                return cl.getLibelleAutre();
//            }
//            if (cl != null) {
//                return LocaleHelper.getLocaleString(RecupBundle.FichierBundle, cl.getLibelle().toString(), null, myLoc);
//            }
//        } catch (Exception e) {
//            return "";
//        }
//
//        return "";
//    }
//
//    public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_classe.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_classe.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
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
//        menu = menusDao.findEntityHavingValue("code", "classe");
//        module = modulesDao.findEntityHavingValue("code", "mod.admin");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        this.updateTableClasse();
//    }
//
//    public List<SelectItem> getTypeLibelleClasseForCreer() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        if (classes != null && classes.getIdClasse() != null) {
//            items.add(new SelectItem(classes.getLibelle(), LocaleHelper.getLocaleString(RecupBundle.FichierBundle, classes.getLibelle().name(), null, myLoc)));
//        } else {
//            for (LibelleClasse tb : LibelleClasse.values()) {
//                if (tb.equals(LibelleClasse.autres)) {
//                    items.add(new SelectItem(tb, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, tb.name(), null, myLoc)));
//                } else {
//                    if (classesDao.findEntityHavingValue("libelle", tb) == null) {
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
//    public Boolean chargLibelleClasse() {
//        if (classes!=null && classes.getLibelle() != null) {
//            if (classes.getLibelle().equals(LibelleClasse.autres)) {
//                return Boolean.TRUE;
//            }
//        }
//        return Boolean.FALSE;
//    }
//
//    public String addClasse() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "classe", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (classesDao.findEntityHavingValue("code", classes.getCode()) == null && classes.getCode()!=null) {
//                classes.setIdEntreprise(entreprise);
//                classes.setDateCreation(new Date());
//                classesDao.create(classes);
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "classe", exception.Error.FATAL_ERROR + "", new Object[]{"classe"});
//            logger.error("Erreur Survenu", th);
//        }
//        this.reset();
//        PrimeFaces.current().executeScript("PF('manageClasseDialog').show();");
//        return null;
//    }
//
//    public String updateClasse() {
//        String success = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        //Locale myLoc =new Locale("fr");
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "classe", null, myLoc)};
//        String[] detail = {entete[0], "Parametres"};
//
//        try {
//
//            if (classes != null && classes.getIdEntreprise() != null) {
//                classes.setDateModification(new Date());
//                classesDao.update(classes);
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
//     public String deleteClasse() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "classe", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (classes != null && classes.getIdClasse()!= null) {
//
//                classesDao.delete(classes);
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "classe", exception.Error.FATAL_ERROR + "", new Object[]{"classe"});
//            logger.error("Erreur Survenu", th);
//        }
////       lisClass.remove(classes);
//        this.reset();
//        return null;
//    }
//
//    public void showDetails(OrclassClasses item) {
//        if (classes == null || classes.getIdClasse() == null) {
//            this.setClasses(item);
//        }
//   this.setClasses(classesSlecte);
//    }
//
//    public OrclassClasses getClasses() {
//        return classes;
//    }
//
//    public void setClasses(OrclassClasses classes) {
//        this.classes = classes;
//    }
//
//    public List<OrclassClasses> getLisClass() {
//        return lisClass;
//    }
//
//    public void setLisClass(List<OrclassClasses> lisClass) {
//        this.lisClass = lisClass;
//    }
//
//    public List<OrclassClasses> getFilterClass() {
//        return filterClass;
//    }
//
//    public void setFilterClass(List<OrclassClasses> filterClass) {
//        this.filterClass = filterClass;
//    }
//
//    public OrclassClasses getClassesSlecte() {
//        return classesSlecte;
//    }
//
//    public void setClassesSlecte(OrclassClasses classesSlecte) {
//        this.classesSlecte = classesSlecte;
//    }
//    
//
//}
