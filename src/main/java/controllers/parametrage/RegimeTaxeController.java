///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassRegimeTaxeDao;
//import dao.OrclassTypeTaxeDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleParametrage;
//import enums.NatureTypeTaxe;
//import enums.RegimeTaxes;
//import java.io.Serializable;
//import java.util.ArrayList;
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
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassRegimeTaxe;
//import modele.OrclassTypeTaxe;
//import modele.OrclassUtilisateurs;
//import org.primefaces.PrimeFaces;
//import parametrage.ModuleMenu;
//import utils.GlobalFonctions;
//import utils.LocaleHelper;
//import utils.RecupBundle;
//
///**
// *
// * @author JIATOU FRANCK
// */
//@Named(value = "regimeTaxeController")
//@ViewScoped
//public class RegimeTaxeController implements Serializable {
//
//    /**
//     * Creates a new instance of RegimeTaxeController
//     */
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    OrclassRegimeTaxeDao regimeTaxeDao;
//      OrclassRegimeTaxe regimeTaxe;
//    @EJB
//    IDroitAcces serviceAccess;
//
//    private List<OrclassRegimeTaxe> listeRegimeTaxe = new ArrayList<>();
//    private OrclassEntreprises entreprise;
//
//    /**
//     * Creates a new instance of TypeTaxeController
//     */
//    String summary = "";
//    String msgdetail = "";
//
//    private List<OrclassRegimeTaxe> filterRegimeTaxe = new ArrayList<>();
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//
//    public RegimeTaxeController() {
//        regimeTaxe=new OrclassRegimeTaxe();
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
//        menu = menusDao.findEntityHavingValue("code", ModuleMenu.mRegimeTaxe);
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//
//        listeRegimeTaxe = (List<OrclassRegimeTaxe>) regimeTaxeDao.findAll();
//         this.updateTableTypetaxe();
//
//    }
//
//    public void reset() {
//
//        this.init();
//      listeRegimeTaxe = (List<OrclassRegimeTaxe>) regimeTaxeDao.findAll();
//        this.updateTableTypetaxe();
//        PrimeFaces.current().ajax().update("form1");
//
//    }
//     
//
//    public void init() {
//        regimeTaxe=new OrclassRegimeTaxe();
//    }
//
//    public void updateTableTypetaxe() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:idTypeTaxeTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('typeTaxeTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public String addTypeTaxe() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        //Locale myLoc =new Locale("fr");
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "regimetaxe", null, myLoc)};
//
//        try {
//            //recherche si le classe existe"
//            if (regimeTaxe!=null && regimeTaxe.getRegimeTaxes()==null) {
//                 ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "VALUE REGIME IS NULL", "PLEASE SELECT VALUE"));
//                  PrimeFaces.current().executeScript("PF('TaxeDialog').show()");
//                  return null;
//            }
//            if (regimeTaxeDao.finKey(regimeTaxe.getCode(), regimeTaxe.getLibelle())==null) {
//
//                regimeTaxeDao.create(regimeTaxe);
//
//                String[] detail = {entete[0], regimeTaxe.getLibelle()};
//                //ctx.getApplication().getMessageBundle()
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//            } else {
//                String[] detail = {entete[0], regimeTaxe.getLibelle()};
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.doublons", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.Errdoublons", detail, myLoc);
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//
//            }
//        } catch (ConstraintViolationException E) {
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.error", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, msgdetail + E.getMessage() + " " + E.getCause()));
//            throw E;
//        } catch (Throwable th) {
//            //ecrire dans le fichier de log  
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.error", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));
//
//        }
//
//        this.reset();
//        PrimeFaces.current().executeScript("PF('TaxeDialog').show()");
//        return null;
//    }
//
//    public String updateTypeTaxe() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "regimetaxe", null, myLoc)};
//        try {
//            //teste si l' etabformation existe
//            if (regimeTaxe.getId()!= null) {
//                regimeTaxeDao.update(regimeTaxe);
//
//                String[] detail = {entete[0], regimeTaxe.getLibelle()};
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
//
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//            } else {
//                String[] detail = {entete[0], regimeTaxe.getLibelle()};
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.ErrUpdate", detail, myLoc);
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//
//            }
//
//        } catch (ConstraintViolationException E) {
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.error", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, msgdetail + E.getMessage() + " " + E.getCause()));
//            throw E;
//        } catch (Throwable th) {
//            //ecrire dans le fichier de log  
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.error", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));
//
//        }
//        this.reset();
//        return null;
//    }
//
//    public String deleteTypeTaxe() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "entite.classe", null, myLoc)};
//        try {
//            regimeTaxeDao.delete(regimeTaxe);
//
//            String[] detail = {entete[0], regimeTaxe.getCode()};
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.succes", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.delete.succes", detail, myLoc);
//
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//        } catch (ConstraintViolationException E) {
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.error", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, msgdetail + E.getMessage() + " " + E.getCause()));
//            throw E;
//        } catch (Throwable th) {
//            //ecrire dans le fichier de log  
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.error", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));
//
//        }
//        this.reset();
//        return null;
//    }
//
//    public List<SelectItem> getRegimeTaxes() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (RegimeTaxes n : RegimeTaxes.values()) {
//
//            items.add(new SelectItem(n, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, n.name(), null, myLoc)));
//
//        }
//
//        return items;
//    }
//
//    public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.regimetaxe.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.regimetaxe.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public String showDetails(OrclassRegimeTaxe item) {
//        if (item!=null && item.getId()!=null) {
//            this.setRegimeTaxe(item);
//        }
//        return null;
//    }
//
//    
//    public OrclassEntreprises getEntreprise() {
//        return entreprise;
//    }
//
//    public void setEntreprise(OrclassEntreprises entreprise) {
//        this.entreprise = entreprise;
//    }
//
//    public OrclassRegimeTaxe getRegimeTaxe() {
//        return regimeTaxe;
//    }
//
//    public void setRegimeTaxe(OrclassRegimeTaxe regimeTaxe) {
//        this.regimeTaxe = regimeTaxe;
//    }
//
//    public List<OrclassRegimeTaxe> getListeRegimeTaxe() {
//        return listeRegimeTaxe;
//    }
//
//    public void setListeRegimeTaxe(List<OrclassRegimeTaxe> listeRegimeTaxe) {
//        this.listeRegimeTaxe = listeRegimeTaxe;
//    }
//
//    public List<OrclassRegimeTaxe> getFilterRegimeTaxe() {
//        return filterRegimeTaxe;
//    }
//
//    public void setFilterRegimeTaxe(List<OrclassRegimeTaxe> filterRegimeTaxe) {
//        this.filterRegimeTaxe = filterRegimeTaxe;
//    }
//
//
//
//    public Boolean accessSupprimer() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.regimetaxe.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//}
