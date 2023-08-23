///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import static Entreprise.EntrepriseServices.logger;
//import dao.OrclassFractionnementDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleParametrage;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.application.FacesMessage;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.inject.Named;
//import javax.faces.view.ViewScoped;
//import javax.validation.ConstraintViolationException;
//import modele.OrclassActions;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassFractionnement;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassType_Caracteristique;
//import modele.OrclassUtilisateurs;
//import org.primefaces.PrimeFaces;
//import utils.GlobalFonctions;
//import utils.LocaleHelper;
//import utils.RecupBundle;
//
///**
// *
// * @author JIATOU FRANCK
// */
//@Named(value = "periodiciteController")
//@ViewScoped
//public class PeriodiciteController implements Serializable {
//    @EJB
//    OrclassFractionnementDao fractionnementDao;
//    OrclassFractionnement fractionnement,fractionnementSelect;
//    
//      @EJB
//    OrclassMenusDao menusDao;
//
//    @EJB
//    OrclassModulesDao modulesDao;
//
//    @EJB
//    IDroitAcces serviceAccess;
//     String summary = "";
//    String msgdetail = "";
//
//    private List< OrclassFractionnement> listeFractionnement = new ArrayList<>();
//    private List< OrclassFractionnement> filterFractionnement = new ArrayList<>();
//    private OrclassEntreprises entreprise;
//
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//
//    /**
//     * Creates a new instance of PeriodiciteController
//     */
//    public PeriodiciteController() {
//        fractionnement=new OrclassFractionnement();
//        fractionnementSelect=new OrclassFractionnement();
//    }
//    
//      @PostConstruct
//    void initialiseSession() {
//        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
//        if (entreprise == null) {
//            entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
//        }
//
//       listeFractionnement=(List<OrclassFractionnement>) fractionnementDao.findAll();
//        this.updateTableFRactionnement();
//
//        menu = menusDao.findEntityHavingValue("code", "type_caracteristique");
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//
//    }
//    
//     public String addFractionnement() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "periodicite", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (fractionnementDao.findEntityHavingValue("code", fractionnement.getCode()) == null && fractionnement.getCode() != null) {
//                fractionnementDao.create(fractionnement);
//                listeFractionnement=(List<OrclassFractionnement>) fractionnementDao.findAll();
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "periodicite", exception.Error.FATAL_ERROR + "", new Object[]{"periodicite"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        this.reset();
//        this.chargeDialog();
//        return null;
//    }
//
//    public String updateFractionnement() {
//        String success = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        //Locale myLoc =new Locale("fr");
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "periodicite", null, myLoc)};
//        String[] detail = {entete[0], "Parametres"};
//
//        try {
//
//            if (fractionnement != null && fractionnement.getId() != null) {
//                fractionnementDao.update(fractionnement);
//              listeFractionnement=(List<OrclassFractionnement>) fractionnementDao.findAll();
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
//        this.reset();
//        return null;
//
//    }
//
//    public String deleteTFractionnement() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "periodicite", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (fractionnement != null && fractionnement.getId() != null) {
//
//                fractionnementDao.delete(fractionnement);
//                listeFractionnement=(List<OrclassFractionnement>) fractionnementDao.findAll();
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "periodicite", exception.Error.FATAL_ERROR + "", new Object[]{"periodicite"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        this.reset();
//        return null;
//    }
//    
//    
//      public String showDetails() {
//          this.setFractionnement(fractionnementSelect);
//       return null;
//    }
//
//      public void updateTableFRactionnement() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:idfractionTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('fractionTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//      
//       public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.periodicite.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.periodicite.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean accessSupprimer() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.periodicite.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    public void chargeDialog(){
//       PrimeFaces.current().executeScript("PF('managePeriodiciteDialog').show();");
//    }
//
//    
//    public void init() {
//        fractionnement=new OrclassFractionnement();
//    }
//
//    public void reset() {
//        this.init();
//        this.updateTableFRactionnement();
//        PrimeFaces.current().ajax().update("(form1,form2)");
//    }
//
//    public OrclassFractionnement getFractionnement() {
//        return fractionnement;
//    }
//
//    public void setFractionnement(OrclassFractionnement fractionnement) {
//        this.fractionnement = fractionnement;
//    }
//
//    public List<OrclassFractionnement> getListeFractionnement() {
//        return listeFractionnement;
//    }
//
//    public void setListeFractionnement(List<OrclassFractionnement> listeFractionnement) {
//        this.listeFractionnement = listeFractionnement;
//    }
//
//    public List<OrclassFractionnement> getFilterFractionnement() {
//        return filterFractionnement;
//    }
//
//    public void setFilterFractionnement(List<OrclassFractionnement> filterFractionnement) {
//        this.filterFractionnement = filterFractionnement;
//    }
//
//    public OrclassFractionnement getFractionnementSelect() {
//        return fractionnementSelect;
//    }
//
//    public void setFractionnementSelect(OrclassFractionnement fractionnementSelect) {
//        this.fractionnementSelect = fractionnementSelect;
//    }
//
//
//
//    
//}
