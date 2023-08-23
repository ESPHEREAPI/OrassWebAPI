///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import dao.OrclassBranchesDao;
//import dao.OrclassCategoriesDao;
//import dao.OrclassElt_Categorie_CompagnieDao;
//import dao.OrclassEntreprisesDao;
//import dao.OrclassExerciceDao;
//import dao.OrclassIntermediairesDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassRegistreSinistreDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleParametrage;
//import exception.GlobalException;
//import java.io.Serializable;
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Locale;
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.application.FacesMessage;
//import javax.faces.component.UIComponent;
//import javax.inject.Named;
//import javax.faces.context.FacesContext;
//import javax.faces.view.ViewScoped;
//import javax.validation.ConstraintViolationException;
//import modele.OrclassActions;
//import modele.OrclassBranches;
//import modele.OrclassCategories;
//import modele.OrclassClasses;
//import modele.OrclassElt_Categorie_Compagnie;
//import modele.OrclassEntreprises;
//import modele.OrclassExercice;
//import modele.OrclassFonctionnalites;
//import modele.OrclassIntermediaires;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassRegistreProduction;
//import modele.OrclassRegistreSinistre;
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
// * @author JIATOU FRANCK
// */
//@Named(value = "registreSinistreController")
//@ViewScoped
//public class RegistreSinistreController implements Serializable {
//
//    /**
//     * Creates a new instance of RegistreSinistreController
//     */
//    @EJB
//    private OrclassEntreprisesDao entrepriseDao;
//    @EJB
//    private OrclassRegistreSinistreDao registreSinistreDao;
//    private OrclassRegistreSinistre registreSinistre;
//    @EJB
//    OrclassCategoriesDao categoriesDao;
//    OrclassCategories categories;
//
//    @EJB
//    IDroitAcces serviceAccess;
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    OrclassElt_Categorie_CompagnieDao elt_Categorie_CompagnieDao;
//    @EJB
//    private ISecurite securiteService;
//    @EJB
//    OrclassExerciceDao exerciceDao;
//    OrclassExercice exercice;
//    @EJB
//    OrclassBranchesDao branchesDao;
//    @EJB
//    OrclassIntermediairesDao intermediairesDao;
//
//    private static final Logger logger = LoggerFactory.getLogger(RegistreController.class);
//    String summary = "";
//    String msgdetail = "";
//
//    private Collection<OrclassBranches> lisCategories = new ArrayList<>();
//    private Collection<OrclassRegistreSinistre> lisRegistreSinistre = new ArrayList<>();
//    private OrclassEntreprises entreprise;
//    private List<OrclassCategories> filterCategories;
//    private List<OrclassBranches> filterBranches;
//    private List<OrclassRegistreSinistre> filterSinistreProduction;
//    private Collection<OrclassExercice> listeExercice = new ArrayList<>();
//    private List<OrclassIntermediaires> listeAgence = new ArrayList<>();
//
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//    OrclassUtilisateurs utilisateurs;
//    private OrclassBranches branches;
//    private OrclassClasses classes;
//
//    public RegistreSinistreController() {
//
//        categories = new OrclassCategories();
//        registreSinistre = new OrclassRegistreSinistre();
//        branches = new OrclassBranches();
//        classes = new OrclassClasses();
//        exercice = new OrclassExercice();
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
//        menu = menusDao.findEntityHavingValue("code", "sinistre.registre");
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//
//        this.chargeRegistreProductionByCompagnies();
//        listeExercice = exerciceDao.findAll();
//        listeAgence=intermediairesDao.listeIntermediaireByEntreprise(entreprise);
//  
//
//    }
//
//    public void chargeRegistreProductionByCompagnies() {
//        if (entreprise != null && entreprise.getIdEntreprise() != null) {
//            lisCategories = branchesDao.listAllBrancheShowAllCompagnie();
//            lisCategories.addAll(branchesDao.listAllBrancheByCompagnie(entreprise));
////            lisCategories.removeAll(categoriesDao.listAllCategoriesIsHaveElementsCategorie(entreprise));
////            lisCategories.removeAll(registreSinistreDao.listeCategoriesHaveRegistreSinistreByCompagnie(entreprise));
//
//            lisRegistreSinistre = registreSinistreDao.getAllRegistreSinistreByCompagnie(entreprise);
//
//        }
//    }
//
//    public void reset() {
//
//        this.init();
//        lisRegistreSinistre = registreSinistreDao.getAllRegistreSinistreByCompagnie(entreprise);
//
//        PrimeFaces.current().ajax().update("form1,form2");
////        this.updateDataTable();
//    }
//
//    public void init() {
//        categories = new OrclassCategories();
//        registreSinistre = new OrclassRegistreSinistre();
//        branches = new OrclassBranches();
//        classes = new OrclassClasses();
//        exercice = new OrclassExercice();
//    }
//
//    public void chargeByCategories() {
//        if (branches != null && branches.getIdBranche() != null) {
//
////            this.setBranches(categories.getIdBranche());
//            this.setClasses(branches.getIdClasse());
//        }
//    }
//
//    public void addExercise() {
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        if (exercice != null && exercice.getCode() == null) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("VALUE CODE IS EMPTY...", "PLEASE WRITE THE CODE"));
//            return;
//        }
//        if (exercice != null && exercice.getLibelle() == null) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("VALUE LIBELLE IS EMPTY...", "PLEASE WRITE THE LIBELLE"));
//            return;
//        }
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "exercice", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (exercice != null && exerciceDao.find(exercice.getCode()) == null) {
////  exercice.set
//                exerciceDao.create(exercice);
////                registreSinistre.setIdExercice(exercice);
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//            } else {
//                GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, "EXERCICE EXISTANT...", exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//
//            }
//        } catch (GlobalException e) {
//
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCode(), e.getCode()));
////throw e;
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_FATAL, "exercice", exception.Error.FATAL_ERROR + "", new Object[]{"classe"});
//            logger.error("Erreur Survenu", th);
//        }
//        if (listeExercice.contains(exercice) == Boolean.FALSE) {
//            listeExercice.add(exercice);
//
//        }
//        if (this.getRegistreSinistre() == null) {
//            registreSinistre = new OrclassRegistreSinistre();
//            registreSinistre.setIdExercice(exercice);
//        } else {
//            registreSinistre.setIdExercice(exercice);
//        }
//
//        exercice = new OrclassExercice();
//        PrimeFaces.current().ajax().update(":form1");
//        PrimeFaces.current().executeScript("PF('manageAddExerciceDialog').hide()");
//
//    }
//
//    public String addRegistreSinistre() {
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "sinistre.registre", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        OrclassRegistreSinistre rs = null;
//        try {
//            if (registreSinistre != null && registreSinistre.getCode() != null && registreSinistre.getIdExercice() != null && registreSinistre.getIdExercice().getCode() != null && branches != null && branches.getIdBranche() != null && registreSinistre.getIdIntermediaire() != null &&  registreSinistre.getIdIntermediaire().getIdIntermediaire() != null) {
////                securiteService.addRegistreSinistre(registreSinistre, branches, entreprise, user);
//                rs = registreSinistreDao.finKeyRegistreSinistre(registreSinistre.getCode(), entreprise, registreSinistre.getIdExercice(),registreSinistre.getIdIntermediaire());
//                if (rs == null) {
//                    rs = new OrclassRegistreSinistre();
//                    rs.setCode(registreSinistre.getCode());
//                    rs.setIdEntreprise(entreprise);
//                    rs.setIdExercice(registreSinistre.getIdExercice());
//                    rs.setNum_Registre(BigInteger.ZERO);
//                    rs.setIdBranche(branches);
//                    rs.setIdIntermediaire(registreSinistre.getIdIntermediaire());
//                    rs.setIdUtilisateur(user);
//                    registreSinistreDao.create(rs);
//                } else {
//                    GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_FATAL, rs.getCode() + " ", exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//                    return null;
//
//                }
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "ONE VALUE IS NULL"));
//            }
//        } catch (GlobalException e) {
//
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCode(), e.getCode()));
////throw e;
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_FATAL, "sinistre.registre", exception.Error.FATAL_ERROR + "", new Object[]{"classe"});
//            logger.error("Erreur Survenu", th);
//        }
//        if (lisRegistreSinistre.contains(rs) == Boolean.FALSE) {
//            lisRegistreSinistre.add(rs);
//        }
//
//        this.reset();
//        return null;
//
//    }
//
//    public void updateDataTable() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:idproductionTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('productionTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
////    manageConfirmeDialog
//    public void controllerValidationRegistreProduction() {
//        if (branches == null || branches.getIdBranche() == null) {
//            PrimeFaces.current().executeScript("PF('manageConfirmeDialog').show();");
//        } else {
//            this.addRegistreSinistre();
//        }
//    }
//
//    public void showDetails(OrclassRegistreSinistre item) {
//        OrclassElt_Categorie_Compagnie elts = null;
//        if (registreSinistre == null || registreSinistre.getId() == null) {
//            this.setRegistreSinistre(item);
//        }
////        elts = elt_Categorie_CompagnieDao.finkeyForRegistreSinistre(registreSinistre, entreprise);
//        if (this.getRegistreSinistre() != null && this.getRegistreSinistre().getId() != null) {
////            this.setCategories(elts.getIdCategories());
//            this.setBranches(this.getRegistreSinistre().getIdBranche());
//            this.setClasses(branches.getIdClasse());
//        }
//    }
//
//    public String updateRegistreSinistre() {
//        String success = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        //Locale myLoc =new Locale("fr");
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "sinistre.registre", null, myLoc)};
//        String[] detail = {entete[0], "Parametres"};
//
//        try {
//
//            if (registreSinistre != null && registreSinistre.getId() != null) {
//
//                registreSinistreDao.update(registreSinistre);
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + exception.Success.OPERATION_SUCCESS.toString(), entete, myLoc);
//
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
//
//            } else {
//                //ecrire dans le fichier de log  
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.error", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail));
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
//    public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.registre_production.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);
//
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean accessAjouter() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.registre_production.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.ajouter.name(), fon);
//
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public OrclassBranches chargeCategoriesByRegistreProduction(OrclassRegistreSinistre item) {
//        OrclassElt_Categorie_Compagnie elts = null;
//        if (item != null && item.getId() != null) {
//            elts = elt_Categorie_CompagnieDao.finkeyForRegistreSinistre(item, entreprise);
//            if (elts != null && elts.getId() != null) {
//                return elts.getIdCategories().getIdBranche();
//            }
//        }
//        return null;
//    }
//
//    public Boolean accessModifier() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.registre_production.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean accessAfficheListe() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.registre_production.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.afficher_liste.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public OrclassRegistreSinistreDao getRegistreSinistreDao() {
//        return registreSinistreDao;
//    }
//
//    public void setRegistreSinistreDao(OrclassRegistreSinistreDao registreSinistreDao) {
//        this.registreSinistreDao = registreSinistreDao;
//    }
//
//    public OrclassRegistreSinistre getRegistreSinistre() {
//        return registreSinistre;
//    }
//
//    public void setRegistreSinistre(OrclassRegistreSinistre registreSinistre) {
//        this.registreSinistre = registreSinistre;
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
//    public IDroitAcces getServiceAccess() {
//        return serviceAccess;
//    }
//
//    public void setServiceAccess(IDroitAcces serviceAccess) {
//        this.serviceAccess = serviceAccess;
//    }
//
//    public OrclassMenusDao getMenusDao() {
//        return menusDao;
//    }
//
//    public void setMenusDao(OrclassMenusDao menusDao) {
//        this.menusDao = menusDao;
//    }
//
//    public ISecurite getSecuriteService() {
//        return securiteService;
//    }
//
//    public void setSecuriteService(ISecurite securiteService) {
//        this.securiteService = securiteService;
//    }
//
//    public Collection<OrclassBranches> getLisCategories() {
//        return lisCategories;
//    }
//
//    public void setLisCategories(Collection<OrclassBranches> lisCategories) {
//        this.lisCategories = lisCategories;
//    }
//
//    public Collection<OrclassRegistreSinistre> getLisRegistreSinistre() {
//        return lisRegistreSinistre;
//    }
//
//    public void setLisRegistreSinistre(Collection<OrclassRegistreSinistre> lisRegistreSinistre) {
//        this.lisRegistreSinistre = lisRegistreSinistre;
//    }
//
//    public List<OrclassCategories> getFilterCategories() {
//        return filterCategories;
//    }
//
//    public void setFilterCategories(List<OrclassCategories> filterCategories) {
//        this.filterCategories = filterCategories;
//    }
//
//    public List<OrclassRegistreSinistre> getFilterSinistreProduction() {
//        return filterSinistreProduction;
//    }
//
//    public void setFilterSinistreProduction(List<OrclassRegistreSinistre> filterSinistreProduction) {
//        this.filterSinistreProduction = filterSinistreProduction;
//    }
//
//    public OrclassMenus getMenu() {
//        return menu;
//    }
//
//    public void setMenu(OrclassMenus menu) {
//        this.menu = menu;
//    }
//
//    public OrclassModules getModule() {
//        return module;
//    }
//
//    public void setModule(OrclassModules module) {
//        this.module = module;
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
//    public OrclassClasses getClasses() {
//        return classes;
//    }
//
//    public void setClasses(OrclassClasses classes) {
//        this.classes = classes;
//    }
//
//    public Collection<OrclassExercice> getListeExercice() {
//        return listeExercice;
//    }
//
//    public void setListeExercice(Collection<OrclassExercice> listeExercice) {
//        this.listeExercice = listeExercice;
//    }
//
//    public OrclassExercice getExercice() {
//        return exercice;
//    }
//
//    public void setExercice(OrclassExercice exercice) {
//        this.exercice = exercice;
//    }
//
//    public List<OrclassBranches> getFilterBranches() {
//        return filterBranches;
//    }
//
//    public void setFilterBranches(List<OrclassBranches> filterBranches) {
//        this.filterBranches = filterBranches;
//    }
//
//    public List<OrclassIntermediaires> getListeAgence() {
//        return listeAgence;
//    }
//
//    public void setListeAgence(List<OrclassIntermediaires> listeAgence) {
//        this.listeAgence = listeAgence;
//    }
//
//  
//    
//
//}
