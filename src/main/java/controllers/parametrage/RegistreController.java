/////*
//// * To change this license header, choose License Headers in Project Properties.
//// * To change this template file, choose Tools | Templates
//// * and open the template in the editor.
//// */
////package controllers.parametrage;
////
////import dao.OrclassCategoriesDao;
////import dao.OrclassElt_Categorie_CompagnieDao;
////import dao.OrclassEntreprisesDao;
////import dao.OrclassMenusDao;
////import dao.OrclassModulesDao;
////import dao.OrclassRegistreProductionDao;
////import droitAcces.IDroitAcces;
////import enums.Actions;
////import enums.FonctionnaliteModuleParametrage;
////import exception.GlobalException;
////import java.io.Serializable;
////import java.math.BigInteger;
////import java.util.ArrayList;
////import java.util.Collection;
////import java.util.List;
////import java.util.Locale;
////import javax.annotation.PostConstruct;
////import javax.ejb.EJB;
////import javax.faces.application.FacesMessage;
////import javax.faces.component.UIComponent;
////import javax.faces.context.FacesContext;
////import javax.inject.Named;
////import javax.faces.view.ViewScoped;
////import javax.validation.ConstraintViolationException;
////import modele.OrclassActions;
////import modele.OrclassBranches;
////import modele.OrclassCategories;
////import modele.OrclassClasses;
////import modele.OrclassElt_Categorie_Compagnie;
////import modele.OrclassEntreprises;
////import modele.OrclassFonctionnalites;
////import modele.OrclassMenus;
////import modele.OrclassMenusAcces;
////import modele.OrclassModules;
////import modele.OrclassRegistreProduction;
////import modele.OrclassUtilisateurs;
////import org.primefaces.PrimeFaces;
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////import parametrage.ISecurite;
////import utils.GlobalFonctions;
////import utils.LocaleHelper;
////import utils.RecupBundle;
////
/////**
//// *
//// * @author hp
//// */
////@Named(value = "registreController")
////@ViewScoped
////public class RegistreController implements Serializable {
////
////    @EJB
////    private OrclassEntreprisesDao entrepriseDao;
////    @EJB
////    private OrclassRegistreProductionDao productionDao;
////    private OrclassRegistreProduction production;
////    @EJB
////    OrclassCategoriesDao categoriesDao;
////    OrclassCategories categories;
////
////    @EJB
////    IDroitAcces serviceAccess;
////    @EJB
////    OrclassMenusDao menusDao;
////    @EJB
////    OrclassModulesDao modulesDao;
////    @EJB
////    OrclassElt_Categorie_CompagnieDao elt_Categorie_CompagnieDao;
////    @EJB
////    private ISecurite securiteService;
////
////    private static final Logger logger = LoggerFactory.getLogger(RegistreController.class);
////    String summary = "";
////    String msgdetail = "";
////
////    private Collection<OrclassCategories> lisCategories = new ArrayList<>();
////    private Collection<OrclassRegistreProduction> lisRegistreProduction = new ArrayList<>();
////    private OrclassEntreprises entreprise;
////    private List<OrclassCategories> filterCategories;
////    private List<OrclassRegistreProduction> filterRegistreProduction;
////
////    private OrclassMenus menu;
////    private OrclassModules module;
////    OrclassUtilisateurs user;
////    OrclassUtilisateurs utilisateurs;
////    private OrclassBranches branches;
////    private OrclassClasses classes;
////
////    /**
////     * Creates a new instance of RegistreController
////     */
////    public RegistreController() {
////        categories = new OrclassCategories();
////        production = new OrclassRegistreProduction();
////        branches = new OrclassBranches();
////        classes = new OrclassClasses();
////
////    }
////
////    public void chargeByCategories() {
////        if (categories != null && categories.getIdCategorie() != null) {
////            this.setBranches(categories.getIdBranche());
////            this.setClasses(categories.getIdBranche().getIdClasse());
////        }
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
////
////        menu = menusDao.findEntityHavingValue("code", "registre_production");
////        module = modulesDao.findEntityHavingValue("code", "mod.ref");
////        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
////
////        this.chargeRegistreProductionByCompagnies();
////
////    }
////
////    public void chargeRegistreProductionByCompagnies() {
////        if (entreprise != null && entreprise.getIdEntreprise() != null) {
////            lisCategories = categoriesDao.listAllCategoriesShowAllCompagnie();
////            lisCategories.addAll(categoriesDao.listAllCategorieByCompagnie(entreprise));
//////            lisCategories.removeAll(categoriesDao.listAllCategoriesIsHaveElementsCategorie(entreprise));
////            lisCategories.removeAll(productionDao.listeCategoriesHaveRegistreProductionByCompagnie(entreprise));
////
////            lisRegistreProduction = productionDao.getAllRegistreProductionByCompagnie(entreprise);
////
////        }
////    }
////
////    public void reset() {
////
////        this.init();
////        lisRegistreProduction = productionDao.getAllRegistreProductionByCompagnie(entreprise);
////
////        PrimeFaces.current().ajax().update("form1,form2");
//////        this.updateDataTable();
////    }
////
////    public void init() {
////        categories = new OrclassCategories();
////        production = new OrclassRegistreProduction();
////        production.setPlage_numero(0);
////        branches = new OrclassBranches();
////        classes = new OrclassClasses();
////
////    }
////
////    public String AddRegistreProduction() {
////
////        FacesContext ctx = FacesContext.getCurrentInstance();
////        //get default locale
////        Locale myLoc = ctx.getViewRoot().getLocale();
////        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "registre_production", null, myLoc)};
////
////        // on recupere tous les modules qui lui sont attribuer puis on inserre
////        String[] detail = {entete[0], "Module(s)"};
////        try {
////            if (productionDao.finKeyRegistreProductionByCompagnie(entreprise, production.getCode()) == null && production.getCode() != null && categories != null && categories.getIdCategorie() != null) {
////                securiteService.addRegistreProduction(production, categories, entreprise);
////                OrclassRegistreProduction pr = productionDao.finKeyRegistreProductionByCompagnie(entreprise, production.getCode());
////                if (pr != null && pr.getId() != null) {
////                    lisRegistreProduction.add(pr);
////                }
////
////                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
////                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
////                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
////
////            } else {
////                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "CODE IS NULL"));
////            }
////        } catch (GlobalException e) {
////
////            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCode(), e.getCode()));
//////throw e;
////        } catch (ConstraintViolationException ejb) {
////            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
////            throw ejb;
////        } catch (Exception th) {
////            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_FATAL, "registre_production", exception.Error.FATAL_ERROR + "", new Object[]{"classe"});
////            logger.error("Erreur Survenu", th);
////        }
////        this.addElemementCategories();
////        this.reset();
////        return null;
////
////    }
////
////    public void addElemementCategories() {
////        OrclassElt_Categorie_Compagnie elt_Categorie_Compagnie = null;
////        FacesContext ctx = FacesContext.getCurrentInstance();
////        OrclassRegistreProduction pr = null;
////        if (categories != null && categories.getIdCategorie() != null && entreprise != null && entreprise.getIdEntreprise() != null) {
////            elt_Categorie_Compagnie = elt_Categorie_CompagnieDao.finkey(categories, entreprise);
////            if (elt_Categorie_Compagnie == null) {
////                elt_Categorie_Compagnie = new OrclassElt_Categorie_Compagnie();
////                elt_Categorie_Compagnie.setIdCategories(categories);
////                elt_Categorie_Compagnie.setIdEntreprise(entreprise);
////
////                pr = productionDao.finKeyRegistreProductionByCompagnie(entreprise, production.getCode());
////                if (pr == null) {
////                    GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_FATAL, "registre_production", exception.Error.FATAL_ERROR + "", new Object[]{"registre_production"});
////                    return;
////                }
////
////                elt_Categorie_Compagnie.setIdRegistreProduction(pr);
////                elt_Categorie_CompagnieDao.create(elt_Categorie_Compagnie);
////
////            } else if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getId() != null) {
////
////                pr = productionDao.findEntityHavingValue("code", production.getCode());
////                if (pr == null) {
////                    GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_FATAL, "registre_production", exception.Error.FATAL_ERROR + "", new Object[]{"registre_production"});
////                    return;
////                }
////
////                elt_Categorie_Compagnie.setIdRegistreProduction(pr);
////                elt_Categorie_CompagnieDao.update(elt_Categorie_Compagnie);
////
////            }
////        }
////
////    }
////
////    public void updateDataTable() {
////        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:idproductionTable");
////        table.setValueExpression("sortBy", null);
////
////        PrimeFaces.current().executeScript("PF('productionTable').clearFilters();");
//////         PrimeFaces.current().ajax().update("form1_1");
////
////    }
////
//////    manageConfirmeDialog
////    public void controllerValidationRegistreProduction() {
////        if (categories == null || categories.getIdCategorie() == null) {
////            PrimeFaces.current().executeScript("PF('manageConfirmeDialog').show();");
////        } else {
////            this.AddRegistreProduction();
////        }
////    }
////
////    public String updateProduction() {
////        String success = null;
////        FacesContext ctx = FacesContext.getCurrentInstance();
////
////        //get default locale
////        Locale myLoc = ctx.getViewRoot().getLocale();
////        //Locale myLoc =new Locale("fr");
////        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "registre_production", null, myLoc)};
////        String[] detail = {entete[0], "Parametres"};
////
////        try {
////
////            if (production != null && production.getId() != null && productionDao.finKeyRegistreProductionByCompagnie(entreprise, production.getCode()) != null) {
////
////                productionDao.update(production);
////
////                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + exception.Success.OPERATION_SUCCESS.toString(), entete, myLoc);
////
////                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
////
////            } else {
////                //ecrire dans le fichier de log  
////                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.error", entete, myLoc);
////                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
////                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail));
////
////            }
////
////        } catch (Throwable th) {
////
////            //ecrire dans le fichier de log  
////            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.error", entete, myLoc);
////            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
////            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));
////
////        }
////
////        reset();
////        return null;
////
////    }
////
////    public void generatValeurByPlageNumero() {
////        StringBuilder code = null;
////
////        boolean test = true;
//////        while (test) {
////        if (production.getPlage_numero().intValue() != 0) {
////            code = new StringBuilder();
////            int longeurNumber = production.getPlage_numero();
////            longeurNumber = longeurNumber - 1;
////            code.append(production.getPrefixe());
////
////            for (int i = 0; i < longeurNumber; i++) {
////                code.append("0");
////            }
////            code.append("1");
////            production.setNum_debut(new BigInteger(code.toString()));
////            //on fait la meme chose pour le numero de fin
////            code = new StringBuilder();
////            longeurNumber = longeurNumber + 1;
////            code.append(production.getPrefixe());
////
////            for (int i = 0; i < longeurNumber; i++) {
////                code.append("9");
////            }
////            production.setNum_fin(new BigInteger(code.toString()));
////
////        }
////
////    }
////
////    public OrclassEntreprisesDao getEntrepriseDao() {
////        return entrepriseDao;
////    }
////
////    public void setEntrepriseDao(OrclassEntreprisesDao entrepriseDao) {
////        this.entrepriseDao = entrepriseDao;
////    }
////
////    public OrclassRegistreProductionDao getProductionDao() {
////        return productionDao;
////    }
////
////    public void setProductionDao(OrclassRegistreProductionDao productionDao) {
////        this.productionDao = productionDao;
////    }
////
////    public OrclassRegistreProduction getProduction() {
////        return production;
////    }
////
////    public void setProduction(OrclassRegistreProduction production) {
////        this.production = production;
////    }
////
////    public OrclassCategoriesDao getCategoriesDao() {
////        return categoriesDao;
////    }
////
////    public void setCategoriesDao(OrclassCategoriesDao categoriesDao) {
////        this.categoriesDao = categoriesDao;
////    }
////
////    public OrclassCategories getCategories() {
////        return categories;
////    }
////
////    public void setCategories(OrclassCategories categories) {
////        this.categories = categories;
////    }
////
////    public IDroitAcces getServiceAccess() {
////        return serviceAccess;
////    }
////
////    public void setServiceAccess(IDroitAcces serviceAccess) {
////        this.serviceAccess = serviceAccess;
////    }
////
////    public OrclassMenusDao getMenusDao() {
////        return menusDao;
////    }
////
////    public void setMenusDao(OrclassMenusDao menusDao) {
////        this.menusDao = menusDao;
////    }
////
////    public OrclassModulesDao getModulesDao() {
////        return modulesDao;
////    }
////
////    public void setModulesDao(OrclassModulesDao modulesDao) {
////        this.modulesDao = modulesDao;
////    }
////
////    public OrclassElt_Categorie_CompagnieDao getElt_Categorie_CompagnieDao() {
////        return elt_Categorie_CompagnieDao;
////    }
////
////    public void setElt_Categorie_CompagnieDao(OrclassElt_Categorie_CompagnieDao elt_Categorie_CompagnieDao) {
////        this.elt_Categorie_CompagnieDao = elt_Categorie_CompagnieDao;
////    }
////
////    public String getSummary() {
////        return summary;
////    }
////
////    public void setSummary(String summary) {
////        this.summary = summary;
////    }
////
////    public String getMsgdetail() {
////        return msgdetail;
////    }
////
////    public void setMsgdetail(String msgdetail) {
////        this.msgdetail = msgdetail;
////    }
////
////    public Collection<OrclassCategories> getLisCategories() {
////        return lisCategories;
////    }
////
////    public void setLisCategories(Collection<OrclassCategories> lisCategories) {
////        this.lisCategories = lisCategories;
////    }
////
////    public Collection<OrclassRegistreProduction> getLisRegistreProduction() {
////        return lisRegistreProduction;
////    }
////
////    public void setLisRegistreProduction(Collection<OrclassRegistreProduction> lisRegistreProduction) {
////        this.lisRegistreProduction = lisRegistreProduction;
////    }
////
////    public OrclassEntreprises getEntreprise() {
////        return entreprise;
////    }
////
////    public void setEntreprise(OrclassEntreprises entreprise) {
////        this.entreprise = entreprise;
////    }
////
////    public OrclassMenus getMenu() {
////        return menu;
////    }
////
////    public void setMenu(OrclassMenus menu) {
////        this.menu = menu;
////    }
////
////    public OrclassModules getModule() {
////        return module;
////    }
////
////    public void setModule(OrclassModules module) {
////        this.module = module;
////    }
////
////    public OrclassUtilisateurs getUser() {
////        return user;
////    }
////
////    public void setUser(OrclassUtilisateurs user) {
////        this.user = user;
////    }
////
////    public OrclassUtilisateurs getUtilisateurs() {
////        return utilisateurs;
////    }
////
////    public void setUtilisateurs(OrclassUtilisateurs utilisateurs) {
////        this.utilisateurs = utilisateurs;
////    }
////
////    public List<OrclassCategories> getFilterCategories() {
////        return filterCategories;
////    }
////
////    public void setFilterCategories(List<OrclassCategories> filterCategories) {
////        this.filterCategories = filterCategories;
////    }
////
////    public OrclassBranches getBranches() {
////        return branches;
////    }
////
////    public void setBranches(OrclassBranches branches) {
////        this.branches = branches;
////    }
////
////    public OrclassClasses getClasses() {
////        return classes;
////    }
////
////    public void setClasses(OrclassClasses classes) {
////        this.classes = classes;
////    }
////
////    public List<OrclassRegistreProduction> getFilterRegistreProduction() {
////        return filterRegistreProduction;
////    }
////
////    public void setFilterRegistreProduction(List<OrclassRegistreProduction> filterRegistreProduction) {
////        this.filterRegistreProduction = filterRegistreProduction;
////    }
////
////    public void showDetails(OrclassRegistreProduction item) {
////        OrclassElt_Categorie_Compagnie elts = null;
////        if (production == null || production.getId() == null) {
////            this.setProduction(item);
////        }
////        elts = elt_Categorie_CompagnieDao.finkey(production, entreprise);
////        if (elts != null && elts.getId() != null) {
////            this.setCategories(elts.getIdCategories());
////            this.setBranches(categories.getIdBranche());
////            this.setClasses(branches.getIdClasse());
////        }
////    }
////
////    public OrclassCategories chargeCategoriesByRegistreProduction(OrclassRegistreProduction item) {
////        OrclassElt_Categorie_Compagnie elts = null;
////        if (item != null && item.getId() != null) {
////            elts = elt_Categorie_CompagnieDao.finkey(item, entreprise);
////            if (elts != null && elts.getId() != null) {
////                return elts.getIdCategories();
////            }
////        }
////        return null;
////    }
////
////    public Boolean accessCree() {
////        OrclassActions action = null;
////        OrclassFonctionnalites fon = null;
////        OrclassMenusAcces ma = null;
////        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
////            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.registre_production.name());
////            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);
////
////            return serviceAccess.accessAction(user, action, menu);
////        }
////        return Boolean.FALSE;
////    }
////    
////        public Boolean accessAjouter() {
////        OrclassActions action = null;
////        OrclassFonctionnalites fon = null;
////        OrclassMenusAcces ma = null;
////        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
////            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.registre_production.name());
////            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.ajouter.name(), fon);
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
////            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.registre_production.name());
////            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
////            return serviceAccess.accessAction(user, action, menu);
////        }
////        return Boolean.FALSE;
////    }
////
////    public Boolean accessAfficheListe() {
////        OrclassActions action = null;
////        OrclassFonctionnalites fon = null;
////        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
////            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.registre_production.name());
////            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.afficher_liste.name(), fon);
////            return serviceAccess.accessAction(user, action, menu);
////        }
////        return Boolean.FALSE;
////    }
////
////}
