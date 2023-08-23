//package controllers.parametrage;
//
//import Entreprise.EntrepriseServices;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.Orclass_RefAvenantDao;
//import dao.Orclass_TypeAvenantDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.Contrat;
//import enums.FonctionnaliteModuleAdministration;
//import enums.NatureAvenant;
//import exception.Error;
//import exception.Success;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.application.FacesMessage;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.model.SelectItem;
//import javax.faces.view.ViewScoped;
//import javax.inject.Named;
//import javax.validation.ConstraintViolationException;
//import modele.DAOEntry;
//import modele.OrclassActions;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassUtilisateurs;
//import modele.Orclass_RefAvenant;
//import modele.Orclass_TypeAvenant;
//import org.primefaces.PrimeFaces;
//import org.primefaces.event.SelectEvent;
//import utils.GlobalFonctions;
//import utils.LocaleHelper;
//
//@Named("typeAvenantController")
//@ViewScoped
//public class TypeAvenantController implements Serializable {
//    
//    @EJB
//    Orclass_TypeAvenantDao typeAvenantDao;
//    
//    Orclass_TypeAvenant typeAvenant;
//    
//    @EJB
//    Orclass_RefAvenantDao refAvenantDao;
//    
//    Orclass_RefAvenant refAvenant;
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
//    String summary = "";
//    
//    String msgdetail = "";
//    
//    private List<Orclass_TypeAvenant> listeTypeAvenant = new ArrayList<>();
//    
//    private List<Orclass_TypeAvenant> filterTypeAvenant = new ArrayList<>();
//    
//    private OrclassEntreprises entreprise;
//    
//    private OrclassMenus menu;
//    
//    private OrclassModules module;
//    
//    OrclassUtilisateurs user;
//    
//    public TypeAvenantController() {
//        this.typeAvenant = new Orclass_TypeAvenant();
//        this.refAvenant = new Orclass_RefAvenant();
//    }
//    
//    public String genereCodeRefAveant() {
//        if (this.refAvenantDao.findAll() == null || this.refAvenantDao.findAll().isEmpty()) {
//            return "1";
//        }
//        return this.refAvenantDao.getcodeRefAvenant();
//    }
//    
//    @PostConstruct
//    void initialiseSession() {
//        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        this.entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("entrepriseActif");
//        if (this.entreprise == null) {
//            this.entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("entrepriseActif");
//        }
//        this.listeTypeAvenant = this.typeAvenantDao.listeTypeAvenantByCompagnie(this.entreprise);
//        updateTableTypeAvenant();
//        this.menu = (OrclassMenus) this.menusDao.findEntityHavingValue("code", "type_caracteristique");
//        this.module = (OrclassModules) this.modulesDao.findEntityHavingValue("code", "mod.ref");
//        this.user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionuser");
//        this.refAvenant.setCode(genereCodeRefAveant());
//    }
//    
//    public void updateTableTypeAvenant() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:idtypeAvenantTable");
//        table.setValueExpression("sortBy", null);
//        PrimeFaces.current().executeScript("PF('typeAvenantTable').clearFilters();");
//    }
//    
//    public void init() {
//        this.typeAvenant = new Orclass_TypeAvenant();
//        this.refAvenant = new Orclass_RefAvenant(genereCodeRefAveant());
//    }
//    
//    public void reset() {
//        init();
//        updateTableTypeAvenant();
//        PrimeFaces.current().ajax().update(new String[]{":form1"});
//    }
//    
//    public String addTypeAvenant() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String[] entete = {LocaleHelper.getLocaleString("langue.welcome", "typeAvenant", null, myLoc)};
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (typeAvenant.getContrat() == null) {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "SELECT CONTRACT", "VALUE CONTRACT IS NULL"));
//                PrimeFaces.current().executeScript("PF('TypeAvenantDialog').show()");
//                return null;
//            }
//            String libelle = this.refAvenant.getLibelle().toUpperCase();
//            if (this.refAvenantDao.findEntityHavingValue("libelle", libelle) == null) {
//                this.refAvenant.setLibelle(libelle);
//                this.refAvenantDao.create(this.refAvenant);
//            }
//            this.refAvenant = (Orclass_RefAvenant) this.refAvenantDao.findEntityHavingValue("libelle", libelle);
//            if (this.refAvenant != null && this.refAvenant.getId() != null && this.typeAvenantDao.finKey(this.entreprise, this.refAvenant, this.typeAvenant.getNatureAvenant()) == null) {
//                this.typeAvenant.setIdRefAvenant(this.refAvenant);
//                this.typeAvenant.setIdEntreprise(this.entreprise);
//                this.typeAvenant.setDateCreation(new Date());
//                this.typeAvenantDao.create(this.typeAvenant);
//                if (this.listeTypeAvenant.contains(this.typeAvenant) == Boolean.FALSE.booleanValue()) {
//                    this.listeTypeAvenant.add(this.typeAvenant);
//                }
//                this.summary = LocaleHelper.getLocaleString("langue.welcome", "summary.ajout.succes", (Object[]) entete, myLoc);
//                this.msgdetail = LocaleHelper.getLocaleString("langue.welcome", "detail.ajout.succes", (Object[]) detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, this.summary, this.msgdetail));
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "type caracteristique", Error.FATAL_ERROR + "", new Object[]{"classe"});
//            EntrepriseServices.logger.error("Erreur Survenu", th);
//        }
//        reset();
//        PrimeFaces.current().executeScript("PF('TypeAvenantDialog').show()");
//        return null;
//    }
//    
//    public String updateTypeAvenant() {
//        String success = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String[] entete = {LocaleHelper.getLocaleString("langue.welcome", "typeAvenant", null, myLoc)};
//        String[] detail = {entete[0], "Parametres"};
//        try {
//            String libelle = this.refAvenant.getLibelle().toUpperCase();
//            this.refAvenantDao.update(this.refAvenant);
//            if (this.typeAvenant != null && this.typeAvenant.getId() != null) {
//                this.typeAvenant.setDateModification(new Date());
//                this.typeAvenantDao.update(this.typeAvenant);
//                this.listeTypeAvenant = this.typeAvenantDao.listeTypeAvenantByCompagnie(this.entreprise);
//                this.summary = LocaleHelper.getLocaleString("langue.welcome", "" + Success.OPERATION_SUCCESS.toString(), (Object[]) entete, myLoc);
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, this.summary, null));
//            }
//        } catch (Throwable th) {
//            this.summary = LocaleHelper.getLocaleString("langue.welcome", "summary.update.error", (Object[]) entete, myLoc);
//            this.msgdetail = LocaleHelper.getLocaleString("langue.welcome", "detail.error", null, myLoc);
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, this.summary, this.msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));
//        }
//        reset();
//        return null;
//    }
//    
//    public String deleteTypeAvenan() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String[] entete = {LocaleHelper.getLocaleString("langue.welcome", "typeAvenant", null, myLoc)};
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (this.typeAvenant != null && this.typeAvenant.getId() != null) {
//                this.typeAvenantDao.delete(this.typeAvenant);
//                this.summary = LocaleHelper.getLocaleString("langue.welcome", "summary.delete.succes", (Object[]) entete, myLoc);
//                this.msgdetail = LocaleHelper.getLocaleString("langue.welcome", "detail.delete.succes", (Object[]) detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, this.summary, this.msgdetail));
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "branche", Error.FATAL_ERROR + "", new Object[]{"typeAvenant"});
//            EntrepriseServices.logger.error("Erreur Survenu", th);
//        }
//        this.listeTypeAvenant = this.typeAvenantDao.listeTypeAvenantByCompagnie(this.entreprise);
//        reset();
//        return null;
//    }
//    
//    public List<SelectItem> getTypeNature() {
//        List<SelectItem> items = new ArrayList<>();
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        for (NatureAvenant n : NatureAvenant.values()) {
//            items.add(new SelectItem(n, LocaleHelper.getLocaleString("langue.welcome", n.name(), null, myLoc)));
//        }
//        return items;
//    }
//    
//    public List<SelectItem> getTypeContrat() {
//        List<SelectItem> items = new ArrayList<>();
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        for (Contrat ct : Contrat.values()) {
//            items.add(new SelectItem(ct, LocaleHelper.getLocaleString("langue.welcome", ct.name(), null, myLoc)));
//        }
//        return items;
//    }
//    
//    public List<String> completeText(String query) {
//        List<String> results = new ArrayList<>();
//        if (query != null && !query.trim().equals("")) {
//            results = this.refAvenantDao.getRefApporteurCodeWithFilters(query.toUpperCase());
//        }
//        return results;
//    }
//    
//    public void onItemSelect(SelectEvent<String> event) {
//        Orclass_RefAvenant ref = null;
//        ref = (Orclass_RefAvenant) this.refAvenantDao.findEntityHavingValue("libelle", event.getObject());
//        if (ref != null && ref.getId() != null) {
//            setRefAvenant(ref);
//            PrimeFaces.current().ajax().update(new String[]{":form2"});
//            PrimeFaces.current().executeScript("PF('TypeAvenantDialog').show()");
//        }
//    }
//    
//    public Orclass_TypeAvenant getTypeAvenant() {
//        return this.typeAvenant;
//    }
//    
//    public void setTypeAvenant(Orclass_TypeAvenant typeAvenant) {
//        this.typeAvenant = typeAvenant;
//    }
//    
//    public Orclass_RefAvenant getRefAvenant() {
//        return this.refAvenant;
//    }
//    
//    public void setRefAvenant(Orclass_RefAvenant refAvenant) {
//        this.refAvenant = refAvenant;
//    }
//    
//    public List<Orclass_TypeAvenant> getListeTypeAvenant() {
//        return this.listeTypeAvenant;
//    }
//    
//    public void setListeTypeAvenant(List<Orclass_TypeAvenant> listeTypeAvenant) {
//        this.listeTypeAvenant = listeTypeAvenant;
//    }
//    
//    public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (this.menu != null && this.menu.getIdMenu() != null && this.module != null && this.module.getIdModule() != null) {
//            fon = this.serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_intermediaire.name());
//            action = this.serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);
//            return this.serviceAccess.accessAction(this.user, action, this.menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public Boolean accessModifier() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (this.menu != null && this.menu.getIdMenu() != null && this.module != null && this.module.getIdModule() != null) {
//            fon = this.serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_intermediaire.name());
//            action = this.serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
//            return this.serviceAccess.accessAction(this.user, action, this.menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public void showDetails(Orclass_TypeAvenant item) {
//        if (this.typeAvenant == null || this.typeAvenant.getId() == null) {
//            setTypeAvenant(item);
//        }
//        setRefAvenant(this.typeAvenant.getIdRefAvenant());
//    }
//    
//    public List<Orclass_TypeAvenant> getFilterTypeAvenant() {
//        return this.filterTypeAvenant;
//    }
//    
//    public void setFilterTypeAvenant(List<Orclass_TypeAvenant> filterTypeAvenant) {
//        this.filterTypeAvenant = filterTypeAvenant;
//    }
//}
