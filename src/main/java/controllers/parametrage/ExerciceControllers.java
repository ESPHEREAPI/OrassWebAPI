/////*
//// * To change this license header, choose License Headers in Project Properties.
//// * To change this template file, choose Tools | Templates
//// * and open the template in the editor.
//// */
////package controllers.parametrage;
////
////import static Entreprise.EntrepriseServices.logger;
////import dao.OrclassExerciceDao;
////import dao.OrclassMenusDao;
////import dao.OrclassModulesDao;
////import droitAcces.IDroitAcces;
////import enums.Actions;
////import enums.FonctionnaliteModuleAdministration;
////import enums.FonctionnaliteModuleParametrage;
////import exception.GlobalException;
////import java.io.Serializable;
////import java.util.ArrayList;
////import java.util.List;
////import java.util.Locale;
////import javax.annotation.PostConstruct;
////import javax.ejb.EJB;
////import javax.faces.application.FacesMessage;
////import javax.faces.context.FacesContext;
////import javax.inject.Named;
////import javax.faces.view.ViewScoped;
////import javax.validation.ConstraintViolationException;
////import modele.OrclassActions;
////import modele.OrclassEntreprises;
////import modele.OrclassExercice;
////import modele.OrclassFonctionnalites;
////import modele.OrclassMenus;
////import modele.OrclassMenusAcces;
////import modele.OrclassModules;
////import modele.OrclassUtilisateurs;
////import org.primefaces.PrimeFaces;
////import sante.ISinistre;
////import utils.GlobalFonctions;
////import utils.LocaleHelper;
////import utils.RecupBundle;
////
/////**
//// *
//// * @author JIATOU FRANCK
//// */
////@Named(value = "exerciceControllers")
////@ViewScoped
////public class ExerciceControllers implements Serializable {
////
////    @EJB
////    OrclassExerciceDao exerciceDao;
////    OrclassExercice exercice;
////    @EJB
////    OrclassMenusDao menusDao;
////    @EJB
////    OrclassModulesDao modulesDao;
////    @EJB
////    IDroitAcces serviceAccess;
////    private OrclassMenus menu;
////    private OrclassModules module;
////    OrclassUtilisateurs user;
////    @EJB
////    ISinistre sinistreService;
////    String summary = "";
////    String msgdetail = "";
////    private OrclassEntreprises entreprise;
////    private List<OrclassExercice> listeExercice = new ArrayList<>();
////    private List<OrclassExercice> filterExercice;
////
////    /**
////     * Creates a new instance of ExerciceControllers
////     */
////    public ExerciceControllers() {
////        exercice = new OrclassExercice();
////    }
////
////    public void init() {
////        exercice = new OrclassExercice();
////    }
////
////    @PostConstruct
////    void initialiseSession() {
////        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
////        entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
////        if (entreprise == null) {
////            entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
////
////        }
////        menu = menusDao.findEntityHavingValue("code", "prestation");
////        module = modulesDao.findEntityHavingValue("code", "mod.ref");
////        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
////        listeExercice = (List<OrclassExercice>) exerciceDao.findAll();
////
////    }
////
////    public void reset() {
////
////        this.init();
////
////        PrimeFaces.current().ajax().update("form1,form2");
////
////    }
////
////    public void addExercise() {
////
////        FacesContext ctx = FacesContext.getCurrentInstance();
////        if (exercice != null && exercice.getCode() == null) {
////            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("VALUE CODE IS EMPTY...", "PLEASE WRITE THE CODE"));
////            return;
////        }
////        if (exercice != null && exercice.getLibelle() == null) {
////            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("VALUE LIBELLE IS EMPTY...", "PLEASE WRITE THE LIBELLE"));
////            return;
////        }
////        //get default locale
////        Locale myLoc = ctx.getViewRoot().getLocale();
////        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "exercice", null, myLoc)};
////
////        // on recupere tous les modules qui lui sont attribuer puis on inserre
////        String[] detail = {entete[0], "Module(s)"};
////        try {
////            if (exercice != null && exerciceDao.find(exercice.getCode()) == null) {
//////  exercice.set
////                exerciceDao.create(exercice);
//////                registreSinistre.setIdExercice(exercice);
////                listeExercice = (List<OrclassExercice>) exerciceDao.findAll();
////                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
////                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
////                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
////
////            } else {
////                GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, "EXERCICE EXISTANT...", exception.Error.DUPLICATE_ERROR_INSERT + "", null);
////
////            }
////        } catch (GlobalException e) {
////
////            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCode(), e.getCode()));
//////throw e;
////        } catch (ConstraintViolationException ejb) {
////            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
////            throw ejb;
////        } catch (Exception th) {
////            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_FATAL, "exercice", exception.Error.FATAL_ERROR + "", new Object[]{"classe"});
////            logger.error("Erreur Survenu", th);
////        }
////
////        reset();
////
////    }
////    public Boolean controleExercice(){
////        if (exercice!=null && exercice.getCode()!=null) {
////            if (exerciceDao.findEntityHavingValue("code", exercice.getCode())!=null) {
////                // ici on ne peut que effectuer une modification
////                return Boolean.TRUE;
////            }else{
////                //ici on ne peut que effectuer un ajout
////              return Boolean.FALSE;  
////            }
////        }
////        //ici on ne peut que effectuer un ajout
////              return Boolean.FALSE; 
////    }
////
////    public String updateExercice() {
////        FacesContext ctx = FacesContext.getCurrentInstance();
////        //get default locale
////        Locale myLoc = ctx.getViewRoot().getLocale();
////        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "exercice", null, myLoc)};
////        try {
////            //teste si le pays existe
////
////            if (exercice.getCode() != null) {
////
////                exerciceDao.update(exercice);
////                listeExercice = (List<OrclassExercice>) exerciceDao.findAll();
////                String[] detail = {entete[0], exercice.getCode() + "," + exercice.getLibelle()};
////                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
////                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
////
////                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
////            } else {
////                String[] detail = {entete[0], exercice.getCode() + "," + exercice.getLibelle()};
////                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
////                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.ErrUpdate", detail, myLoc);
////                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
////
////            }
////
////        } catch (ConstraintViolationException E) {
////            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.error", entete, myLoc);
////            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
////
////            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, msgdetail + E.getMessage() + " " + E.getCause()));
////            throw E;
////        } catch (Throwable th) {
////            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.error", entete, myLoc);
////            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
////
////            //ecrire dans le fichier de log  
////            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));
////
////        }
////        this.reset();
////        return null;
////    }
////
////    public Boolean accessCree() {
////        OrclassActions action = null;
////        OrclassFonctionnalites fon = null;
////        OrclassMenusAcces ma = null;
////        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
////            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.exercice.name());
////            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);
////
////            return serviceAccess.accessAction(user, action, menu);
////        }
////        return Boolean.FALSE;
////    }
////
////    public Boolean accessModifier() {
////        OrclassActions action = null;
////        OrclassFonctionnalites fon = null;
////        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
////            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.exercice.name());
////            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
////            return serviceAccess.accessAction(user, action, menu);
////        }
////        return Boolean.FALSE;
////    }
////
////    public void showDetails(OrclassExercice item) {
////        if (exercice == null || exercice.getCode() == null) {
////            this.setExercice(item);
////        }
////
////        PrimeFaces.current().ajax().update(":form1");
////    }
////
////    public String deleteExercicde() {
////        FacesContext ctx = FacesContext.getCurrentInstance();
////        //get default locale
////        Locale myLoc = ctx.getViewRoot().getLocale();
////        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "exercice", null, myLoc)};
////
////        // on recupere tous les modules qui lui sont attribuer puis on inserre
////        String[] detail = {entete[0], "Module(s)"};
////
////        try {
////            if (exercice != null && exercice.getCode() != null) {
////
//////               categoriesDao.delete(categories);
////                exerciceDao.delete(exercice);
////                listeExercice = (List<OrclassExercice>) exerciceDao.findAll();
////
////                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.succes", entete, myLoc);
////                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.delete.succes", detail, myLoc);
////                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
////
////            } else {
////                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
////            }
////        } catch (ConstraintViolationException ejb) {
////            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
////            throw ejb;
////        } catch (Exception th) {
////            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "categorie", exception.Error.FATAL_ERROR + "", new Object[]{"exercice"});
////            logger.error("Erreur Survenu", th);
////        }
//////       lisClass.remove(classes);
////        this.reset();
////
////        PrimeFaces.current().ajax().update(":form1,:form2");
////
////        return null;
////    }
////
////    public Boolean accessAjouter() {
////        OrclassActions action = null;
////        OrclassFonctionnalites fon = null;
////        OrclassMenusAcces ma = null;
////        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
////            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.exercice.name());
////            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.ajouter.name(), fon);
////
////            return serviceAccess.accessAction(user, action, menu);
////        }
////        return Boolean.FALSE;
////    }
////
////    public OrclassExercice getExercice() {
////        return exercice;
////    }
////
////    public void setExercice(OrclassExercice exercice) {
////        this.exercice = exercice;
////    }
////
////    public List<OrclassExercice> getListeExercice() {
////        return listeExercice;
////    }
////
////    public void setListeExercice(List<OrclassExercice> listeExercice) {
////        this.listeExercice = listeExercice;
////    }
////
////    public List<OrclassExercice> getFilterExercice() {
////        return filterExercice;
////    }
////
////    public void setFilterExercice(List<OrclassExercice> filterExercice) {
////        this.filterExercice = filterExercice;
////    }
////
////}
