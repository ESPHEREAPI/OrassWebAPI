///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package controllers.parametrage;
//
//import static Entreprise.EntrepriseServices.logger;
//import dao.OrclassBranchesDao;
//import dao.OrclassCaracteristiquesDao;
//import dao.OrclassCategoriesDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassSousCaracteristiqueProduitDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleParametrage;
//import enums.SousCaracteristiqueProduitEnum;
//import exception.GlobalException;
//import exception.Success;
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.application.FacesMessage;
//import javax.faces.component.UIComponent;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.faces.model.SelectItem;
//import javax.faces.view.ViewScoped;
//import javax.inject.Named;
//import modele.OrclassActions;
//import modele.OrclassBranches;
//import modele.OrclassCaracteristiques;
//import modele.OrclassCategories;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassMenus;
//import modele.OrclassModules;
//import modele.OrclassSousCaracteristiqueProduit;
//import modele.OrclassUtilisateurs;
//import modele.Orclass_Access_Avenant;
//import org.hibernate.exception.ConstraintViolationException;
//import org.primefaces.PrimeFaces;
//import utils.GlobalFonctions;
//import utils.LocaleHelper;
//import utils.RecupBundle;
//
///**
// *
// * @author JIATOU FRANCK
// */
//@Named(value = "sousCaractereProduitController")
//@ViewScoped
//public class SousCaractereProduitController implements Serializable {
//
//    @EJB
//    private OrclassCaracteristiquesDao caracteristiquesDao;
//    private OrclassCaracteristiques caracteristiques;
//    @EJB
//    private OrclassCategoriesDao categoriesDao;
//    private OrclassCategories categories;
//    @EJB
//    private OrclassSousCaracteristiqueProduitDao sousCaracteristiqueProduitDao;
//    private OrclassSousCaracteristiqueProduit sousCaracteristiqueProduit;
//    @EJB
//    private OrclassBranchesDao branchesDao;
//    private OrclassBranches branches;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    OrclassMenusDao menusDao;
//
//    private List<OrclassSousCaracteristiqueProduit> filterSousCaracteristiqueProduit = new ArrayList<>();
//    private List<OrclassSousCaracteristiqueProduit> listeSousCaracteristiqueProduit = new ArrayList<>();
//    private List<OrclassBranches> listesBranches = new ArrayList<>();
//    private List<OrclassCategories> listesCategories = new ArrayList<>();
//    private List<OrclassCaracteristiques> listesCaracteristique = new ArrayList<>();
//
//    String summary = "";
//    String msgdetail = "";
//    private OrclassEntreprises entreprise;
//    private OrclassMenus menu;
//    private OrclassModules module;
//    @EJB
//    IDroitAcces serviceAccess;
//    OrclassUtilisateurs user;
//
//    public SousCaractereProduitController(OrclassCaracteristiques caracteristiques, OrclassCategories categories) {
//        this.caracteristiques = caracteristiques;
//        this.categories = categories;
//        this.sousCaracteristiqueProduit = new OrclassSousCaracteristiqueProduit();
//    }
//
//    public SousCaractereProduitController() {
//        this.caracteristiques = new OrclassCaracteristiques();
//        this.categories = new OrclassCategories();
//        this.sousCaracteristiqueProduit = new OrclassSousCaracteristiqueProduit();
//        branches = new OrclassBranches();
//    }
//
//    public void reset() {
//        this.caracteristiques = new OrclassCaracteristiques();
//        this.categories = new OrclassCategories();
//        this.sousCaracteristiqueProduit = new OrclassSousCaracteristiqueProduit();
//        branches = new OrclassBranches();
//        PrimeFaces.current().ajax().update(":form1,:form2");
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
//        menu = menusDao.findEntityHavingValue("code", "caracteristique");
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        listeSousCaracteristiqueProduit = sousCaracteristiqueProduitDao.listeSousCaracteristique(entreprise);
//        listesCaracteristique = caracteristiquesDao.listCaracteristiqueByCompagnie(entreprise);
//        listesCaracteristique.addAll(caracteristiquesDao.listeCaracteristiqueForAllCompagnies());
//        listesBranches=branchesDao.listAllBrancheByCompagnie(entreprise);
//        listesBranches.addAll(branchesDao.listAllBrancheShowAllCompagnie());
//        this.updateDataTableSCP();
//    }
//
//    public void updateDataTableSCP() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:idscdprd");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('scdprd').clearFilters();");
//
//    }
//
//    public void chargeProduitByBranche() {
//        if (branches != null && branches.getIdBranche() != null) {
//            listesCategories = categoriesDao.listeCategorieByBranche(branches, entreprise);
//
//        }
//        PrimeFaces.current().ajax().update(":form2");
//        
//        PrimeFaces.current().executeScript("PF('manageCaracteristiqueDialog').show()");
//    }
//
//    public List<SelectItem> getEnumSousCaractProd() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        for (SousCaracteristiqueProduitEnum scp : SousCaracteristiqueProduitEnum.values()) {
//            items.add(new SelectItem(scp, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, scp.name(), null, myLoc)));
//        }
//
//        return items;
//
//    }
//
//    public void updateSCP() throws IOException {
//        // etudiant.setMatricule(GenerationController.genererMatricule());
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        Boolean valider = Boolean.FALSE;
//        try {
//
//            //traitement de la photo
//            if (sousCaracteristiqueProduit != null && sousCaracteristiqueProduit.getIdSousCarPrd() != null) {
//
//                sousCaracteristiqueProduitDao.update(sousCaracteristiqueProduit);
//                listeSousCaracteristiqueProduit = sousCaracteristiqueProduitDao.listeSousCaracteristique(entreprise);
//                this.updateDataTableSCP();
//                this.reset();
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_INFO, "scp.update", Success.UPDATE_SUCCESS + "", new Object[]{sousCaracteristiqueProduit});
//
//            } else {
////                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "police", exception.Error.DUPLICATE_ERROR_INSERT + "" + numero_police, null);
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "scp.update", exception.Error.UPDATE_ERROR + "", new Object[]{sousCaracteristiqueProduit});
//                return;
//            }
//
//        } catch (GlobalException e) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_ERROR, sousCaracteristiqueProduit.toString(), e.getCode(), e.getParam());
//            //throw e;
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.FATAL_ERROR + "", null);
//            throw ejb;
//        } catch (Throwable th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "scp.update", exception.Error.FATAL_ERROR + "", new Object[]{sousCaracteristiqueProduit});
//            logger.error("Erreur Survenu", th);
//        }
//
//    }
//
//    public void addSCP() {
//        // etudiant.setMatricule(GenerationController.genererMatricule());
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        Boolean valider = Boolean.FALSE;
//        Orclass_Access_Avenant access_Avenant = null;
//        try {
//
//            //traitement de la photo
//            if ((sousCaracteristiqueProduitDao.finKey(entreprise, caracteristiques, categories)) == null) {
//                sousCaracteristiqueProduit.setIdCaracteristiques(caracteristiques);
//                sousCaracteristiqueProduit.setIdEntreprise(entreprise);
//                sousCaracteristiqueProduit.setIdCategories(categories);
//                sousCaracteristiqueProduit.setIdUtilisateurs(user);
//                sousCaracteristiqueProduitDao.create(sousCaracteristiqueProduit);
//
//                listeSousCaracteristiqueProduit = sousCaracteristiqueProduitDao.listeSousCaracteristique(entreprise);
//
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_INFO, "scp.ajout", Success.OPERATION_SUCCESS + "", new Object[]{sousCaracteristiqueProduit});
//                this.updateDataTableSCP();
//                this.reset();
//            } else {
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "scp.ajout", exception.Error.DUPLICATE_ERROR_INSERT + "" + sousCaracteristiqueProduit.getIdCaracteristiques().getLibelle(), null);
//                return;
//            }
//
//        } catch (GlobalException e) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_ERROR, sousCaracteristiqueProduit.toString(), e.getCode(), e.getParam());
//            //throw e;
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.FATAL_ERROR + "", null);
//            throw ejb;
//        } catch (Throwable th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "scp.ajout", exception.Error.FATAL_ERROR + "", new Object[]{sousCaracteristiqueProduit});
//            logger.error("Erreur Survenu", th);
//        }
////        if (Objects.equals(valider, Boolean.TRUE)) {
////            this.reload();
////        }
//
//    }
//
//    public void deleteSCP() throws IOException {
//        // etudiant.setMatricule(GenerationController.genererMatricule());
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        Boolean valider = Boolean.FALSE;
//        int retournValeur = 0;
//        try {
//
//            //traitement de la photo
//            if ((sousCaracteristiqueProduit != null && sousCaracteristiqueProduit.getIdSousCarPrd() != null)) {
//                sousCaracteristiqueProduitDao.delete(sousCaracteristiqueProduit);
//                listeSousCaracteristiqueProduit = sousCaracteristiqueProduitDao.listeSousCaracteristique(entreprise);
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_INFO, "scp.delete", Success.DELETE_SUCCESS + "", new Object[]{sousCaracteristiqueProduit});
//                this.updateDataTableSCP();
//                this.reset();
//            } else {
////                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "police", exception.Error.DUPLICATE_ERROR_INSERT + "" + numero_police, null);
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "scp.delete", exception.Error.FATAL_ERROR + "", new Object[]{sousCaracteristiqueProduit});
//                return;
//            }
//
//        } catch (GlobalException e) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_ERROR, sousCaracteristiqueProduit.toString(), e.getCode(), e.getParam());
//            //throw e;
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.FATAL_ERROR + "", null);
//            throw ejb;
//        } catch (Throwable th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "scp.delete", exception.Error.FATAL_ERROR + "", new Object[]{sousCaracteristiqueProduit});
//            logger.error("Erreur Survenu", th);
//        }
//
//    }
//
//    public void showDetails(OrclassSousCaracteristiqueProduit item) {
//        if (sousCaracteristiqueProduit == null && sousCaracteristiqueProduit.getIdSousCarPrd() == null) {
//            this.setSousCaracteristiqueProduit(item);
//            
//        }
//
//        PrimeFaces.current().ajax().update(":form2");
//
//    }
//
//    public OrclassCaracteristiques getCaracteristiques() {
//        return caracteristiques;
//    }
//
//    public void setCaracteristiques(OrclassCaracteristiques caracteristiques) {
//        this.caracteristiques = caracteristiques;
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
//    public OrclassSousCaracteristiqueProduit getSousCaracteristiqueProduit() {
//        return sousCaracteristiqueProduit;
//    }
//
//    public void setSousCaracteristiqueProduit(OrclassSousCaracteristiqueProduit sousCaracteristiqueProduit) {
//        this.sousCaracteristiqueProduit = sousCaracteristiqueProduit;
//    }
//
//    public List<OrclassSousCaracteristiqueProduit> getFilterSousCaracteristiqueProduit() {
//        return filterSousCaracteristiqueProduit;
//    }
//
//    public void setFilterSousCaracteristiqueProduit(List<OrclassSousCaracteristiqueProduit> filterSousCaracteristiqueProduit) {
//        this.filterSousCaracteristiqueProduit = filterSousCaracteristiqueProduit;
//    }
//
//    public List<OrclassSousCaracteristiqueProduit> getListeSousCaracteristiqueProduit() {
//        return listeSousCaracteristiqueProduit;
//    }
//
//    public void setListeSousCaracteristiqueProduit(List<OrclassSousCaracteristiqueProduit> listeSousCaracteristiqueProduit) {
//        this.listeSousCaracteristiqueProduit = listeSousCaracteristiqueProduit;
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
//    public List<OrclassBranches> getListesBranches() {
//        return listesBranches;
//    }
//
//    public void setListesBranches(List<OrclassBranches> listesBranches) {
//        this.listesBranches = listesBranches;
//    }
//
//    public List<OrclassCaracteristiques> getListesCaracteristique() {
//        return listesCaracteristique;
//    }
//
//    public void setListesCaracteristique(List<OrclassCaracteristiques> listesCaracteristique) {
//        this.listesCaracteristique = listesCaracteristique;
//    }
//
//    public List<OrclassCategories> getListesCategories() {
//        return listesCategories;
//    }
//
//    public void setListesCategories(List<OrclassCategories> listesCategories) {
//        this.listesCategories = listesCategories;
//    }
//
//    public Boolean accessSupprimer() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.caracteristique_assure.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer.name(), fon);
//            if (action == null) {
//                return Boolean.FALSE;
//            }
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//}
