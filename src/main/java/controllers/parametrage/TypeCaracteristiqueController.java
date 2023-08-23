///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import static Entreprise.EntrepriseServices.logger;
//import Excell.IExcell;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassTypeCaracteristiqueDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleAdministration;
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
// * @author admin
// */
//@Named(value = "typeCaracteristiqueController")
//@ViewScoped
//public class TypeCaracteristiqueController implements Serializable {
//
//    @EJB
//    OrclassTypeCaracteristiqueDao typeCaracteristiqueDao;
//    OrclassType_Caracteristique typeCaracteristique;
//    OrclassType_Caracteristique typeCaracteristiqueSelect;
//
//    @EJB
//    OrclassMenusDao menusDao;
//
//    @EJB
//    OrclassModulesDao modulesDao;
//
//    @EJB
//    IDroitAcces serviceAccess;
//
//    @EJB
//    IExcell serviceExcell;
//
//    String summary = "";
//    String msgdetail = "";
//
//    private List< OrclassType_Caracteristique> listeTypeCaracteristique = new ArrayList<>();
//    private List< OrclassType_Caracteristique> filterTypeCaracteristique = new ArrayList<>();
//    private OrclassEntreprises entreprise;
//
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//
//    public TypeCaracteristiqueController() {
//        typeCaracteristique = new OrclassType_Caracteristique();
//    }
//
//    @PostConstruct
//    void initialiseSession() {
//        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
//        if (entreprise == null) {
//            entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
//        }
//
//        listeTypeCaracteristique = (List<OrclassType_Caracteristique>) typeCaracteristiqueDao.findAll();
//        this.updateTableTypeCaracteristique();
//
//        menu = menusDao.findEntityHavingValue("code", "type_caracteristique");
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//
//    }
//
//    public String addTypeCaracteristique() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "type_caracteristique", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (typeCaracteristiqueDao.findEntityHavingValue("code", typeCaracteristique.getCode()) == null && typeCaracteristique.getCode() != null) {
//                typeCaracteristiqueDao.create(typeCaracteristique);
//                listeTypeCaracteristique.add(typeCaracteristique);
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "type caracteristique", exception.Error.FATAL_ERROR + "", new Object[]{"classe"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        this.reset();
//        return null;
//    }
//
//    public String updateTypeCaracteristique() {
//        String success = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        //Locale myLoc =new Locale("fr");
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "type_caracteristique", null, myLoc)};
//        String[] detail = {entete[0], "Parametres"};
//
//        try {
//
//            if (typeCaracteristique != null && typeCaracteristique.getId() != null) {
//                listeTypeCaracteristique.remove(typeCaracteristique);
//                typeCaracteristiqueDao.update(typeCaracteristique);
//                listeTypeCaracteristique.add(typeCaracteristique);
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
//        this.reset();
//        return null;
//
//    }
//
//    public String deleteTypeCaracteristique() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "type_caracteristique", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (typeCaracteristique != null && typeCaracteristique.getId() != null) {
//
//                typeCaracteristiqueDao.delete(typeCaracteristique);
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
//
//        listeTypeCaracteristique.remove(typeCaracteristique);
//
//        this.reset();
//        return null;
//    }
//
//    public void updateTableTypeCaracteristique() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:idTypeCaractTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('typeCaractTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.type_caracteristique.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.type_caracteristique.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.type_caracteristique.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    public void chargeDialog(){
//       PrimeFaces.current().executeScript("PF('managePeriodiciteDialog').show();");
//    }
//
//    public void showDetails() {
//        this.setTypeCaracteristique(typeCaracteristiqueSelect);
//    }
//
//    public void init() {
//        typeCaracteristique = new OrclassType_Caracteristique();
//    }
//
//    public void reset() {
//        this.init();
//        this.updateTableTypeCaracteristique();
//        PrimeFaces.current().ajax().update("(form1,form2)");
//    }
//
//    public OrclassType_Caracteristique getTypeCaracteristique() {
//        return typeCaracteristique;
//    }
//
//    public void setTypeCaracteristique(OrclassType_Caracteristique typeCaracteristique) {
//        this.typeCaracteristique = typeCaracteristique;
//    }
//
//    public OrclassType_Caracteristique getTypeCaracteristiqueSelect() {
//        return typeCaracteristiqueSelect;
//    }
//
//    public void setTypeCaracteristiqueSelect(OrclassType_Caracteristique typeCaracteristiqueSelect) {
//        this.typeCaracteristiqueSelect = typeCaracteristiqueSelect;
//    }
//
//    public List<OrclassType_Caracteristique> getListeTypeCaracteristique() {
//        return listeTypeCaracteristique;
//    }
//
//    public void setListeTypeCaracteristique(List<OrclassType_Caracteristique> listeTypeCaracteristique) {
//        this.listeTypeCaracteristique = listeTypeCaracteristique;
//    }
//
//    public List<OrclassType_Caracteristique> getFilterTypeCaracteristique() {
//        return filterTypeCaracteristique;
//    }
//
//    public void setFilterTypeCaracteristique(List<OrclassType_Caracteristique> filterTypeCaracteristique) {
//        this.filterTypeCaracteristique = filterTypeCaracteristique;
//    }
//
//}
