///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import static Entreprise.EntrepriseServices.logger;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassPrestationDao;
//import dao.OrclassRubriqueCategorieDao;
//import dao.OrclassRubriquePrestationDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleParametrage;
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
//import javax.inject.Named;
//import javax.faces.view.ViewScoped;
//import javax.inject.Inject;
//import javax.validation.ConstraintViolationException;
//import modele.OrclassActions;
//import modele.OrclassCategories;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassPrestation;
//import modele.OrclassRubrique;
//import modele.OrclassRubriqueCategorie;
//import modele.OrclassRubriquePrestation;
//import modele.OrclassUtilisateurs;
//import org.primefaces.PrimeFaces;
//import utils.GlobalFonctions;
//import utils.LocaleHelper;
//import utils.RecupBundle;
//
///**
// *
// * @author hp
// */
//@Named(value = "rubriquePrestationController")
//@ViewScoped
//public class RubriquePrestationController implements Serializable {
//
//    @EJB
//    OrclassRubriquePrestationDao rubriquePrestationDao;
//    OrclassRubriquePrestation rubriquePrestation;
//    @EJB
//    OrclassRubriqueCategorieDao rubriqueCategorieDao;
//    OrclassRubriqueCategorie rubriqueCategorie;
//    @EJB
//    OrclassPrestationDao prestationDao;
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    IDroitAcces serviceAccess;
////    @Inject
////    PrestationController prestationController;
//
//    private List<OrclassCategories> lisCategories = new ArrayList<>();
//    private List<OrclassCategories> filterCategories = new ArrayList<>();
//    private List<OrclassRubrique> lisRubriques = new ArrayList<>();
//    private List<OrclassPrestation> lisPrestation = new ArrayList<>();
//    private List<OrclassPrestation> filterPrestation = new ArrayList<>();
//    private List<OrclassPrestation> selectePrestations = new ArrayList<>();
//    private List<OrclassRubriquePrestation> lisRubriquePrestations = new ArrayList<>();
//    private List<OrclassRubriquePrestation> filterRubriquePrestations = new ArrayList<>();
//    OrclassCategories categories;
//    OrclassRubrique rubrique;
//    String summary = "";
//    String msgdetail = "";
//    OrclassEntreprises entreprise;
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//
//    /**
//     * Creates a new instance of RubriquePrestationController
//     */
//    public RubriquePrestationController() {
//        rubriquePrestation = new OrclassRubriquePrestation();
//        rubriqueCategorie = new OrclassRubriqueCategorie();
//
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
//         menu = menusDao.findEntityHavingValue("code", "groupe.rubrique");
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//         user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
////        lisRubrique = rubriqueDao.liteRubriqueByCompagnie(entreprise);
//        lisCategories = rubriqueCategorieDao.listCategorieHaveRubrique(entreprise);
////        lisPrestation=prestationDao.listePrestationByCompagnie(entreprise);
//        this.updateTablePrestation();
//        this.updateTableRubriquePrestationPrestation();
//
//    }
//
//    public void initial() {
//        rubrique = new OrclassRubrique();
//        rubriquePrestation = new OrclassRubriquePrestation();
//        rubriqueCategorie = new OrclassRubriqueCategorie();
//        categories = new OrclassCategories();
//        selectePrestations = new ArrayList<>();
//        lisPrestation = new ArrayList<>();
//        lisRubriquePrestations= new ArrayList<>(); 
//
//    }
//
//      public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
//        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
////        if (LangUtils.isBlank(filterText)) {
////            return true;
////        }
////        int filterInt = getInteger(filterText);
//
//        OrclassRubriquePrestation rubPres = (OrclassRubriquePrestation) value;
//        if (rubPres.getId() == null) {
//            return true;
//        }
//        return rubPres.getIdPrestation().getCode().toLowerCase().contains(filterText)
//                || rubPres.getIdPrestation().getLibelle().toLowerCase().contains(filterText);
//    }
//    
//    public void reset() {
//        initial();
//        this.updateTablePrestation();
//        this.updateTableRubriquePrestationPrestation();
//        PrimeFaces.current().ajax().update(":form1");
//    }
//
//    public void chargerRubriqueByCategories() {
//        lisRubriques.clear();
//        if (categories != null && categories.getIdCategorie() != null) {
//            lisRubriques = rubriqueCategorieDao.OrclassRubriqueByCategorieDao(categories, entreprise);
//            if (rubrique!=null && rubrique.getId()!=null) {
//                this.chargeRubriPrestationByRubriCategories();
//            }
//
//        }
//
//    }
//
//    public void chargePrestation() {
//        if (rubriqueCategorie != null && rubriqueCategorie.getId() != null) {
//            lisPrestation = prestationDao.listePrestationtNaotHaveRubrique(rubriqueCategorie, entreprise);
////            if (lisPrestation.isEmpty()) {
////                prestationController.getListePrestation();
////            }
//        }
//    }
//
//    public void chargeRubriPrestationByRubriCategories() {
//        lisRubriquePrestations.clear();
//        if (categories != null && categories.getIdCategorie() != null && rubrique != null && rubrique.getId() != null) {
//            rubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, rubrique, entreprise);
//            if (rubriqueCategorie != null && rubriqueCategorie.getId() != null) {
//
//                lisRubriquePrestations = rubriquePrestationDao.listeRubriPrestationByRubriqueCategorie(rubriqueCategorie, entreprise);
//
//                if (lisRubriquePrestations.isEmpty()) {
//                    rubriquePrestation = new OrclassRubriquePrestation();
//                    rubriquePrestation.setIdRubriqueCategorie(rubriqueCategorie);
//                    lisRubriquePrestations.add(rubriquePrestation);
//
//                } else {
//                    rubriquePrestation = new OrclassRubriquePrestation();
//                    rubriquePrestation.setIdRubriqueCategorie(rubriqueCategorie);
//                    lisRubriquePrestations.add(rubriquePrestation);
//                    this.reverseListe();
//                }
//
//            }
//            this.chargePrestation();
//        }
//        this.updateTablePrestation();
//        this.updateTableRubriquePrestationPrestation();
//        PrimeFaces.current().ajax().update(":form2:idprestationableTable");
//    }
//
//    public void chargePrestaionForRubriqueCategorie() {
//        if (!selectePrestations.isEmpty()) {
//            for (OrclassPrestation p : selectePrestations) {
//                rubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, rubrique, entreprise);
//                rubriquePrestation = rubriquePrestationDao.finKey(rubriqueCategorie, p, entreprise);
//                if (rubriquePrestation == null) {
//                    rubriquePrestation = new OrclassRubriquePrestation();
//                    rubriquePrestation.setIdPrestation(p);
//                    rubriquePrestation.setIdRubriqueCategorie(rubriqueCategorie);
//                    lisRubriquePrestations.add(rubriquePrestation);
//
//                } else {
//                    if (lisRubriquePrestations.contains(rubriquePrestation) == Boolean.FALSE) {
//                        lisRubriquePrestations.add(rubriquePrestation);
//
//                    }
//                }
//            }
//            lisPrestation.removeAll(selectePrestations);
//
//            selectePrestations.clear();
//        }
//        this.updateTableRubriquePrestationPrestation();
//        this.updateTablePrestation();
//        PrimeFaces.current().ajax().update(":form1:idrubriquePrestationTable,:form1");
//        this.reverseListe();
//
//    }
//
//    public void initialSelectePrestation() {
//        selectePrestations = new ArrayList<>();
//        this.updateTablePrestation();
//        PrimeFaces.current().ajax().update(":form2:idprestationableTable");
//    }
//
//    public void updateTablePrestation() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:idprestationableTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('prestationableTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public void updateTableRubriquePrestationPrestation() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:idrubriquePrestationTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('rubriquePrestationTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public void reverseListe() {
//
//        List<OrclassRubriquePrestation> result = new ArrayList<>();
//        for (int i = lisRubriquePrestations.size() - 1; i >= 0; i--) {
//            result.add(lisRubriquePrestations.get(i));
//        }
//
//        this.setLisRubriquePrestations(result);
////        this.updateTableRubriqueCategorie();
//    }
//
//    public void removeRubriqueByListe() {
//        lisRubriquePrestations.remove(rubriquePrestation);
//        if (lisPrestation.contains(rubriquePrestation.getIdPrestation()) == Boolean.FALSE) {
//            lisPrestation.add(rubriquePrestation.getIdPrestation());
//        }
////        this.updateTableRubriqueCategorie();
//        this.updateTablePrestation();
//        this.updateTableRubriquePrestationPrestation();
//        PrimeFaces.current().ajax().update("form1");
//
//    }
//
//    public String addRubriquePrestation() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "rubrique.prestation", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (!lisRubriquePrestations.isEmpty()) {
//                rubriquePrestation = (OrclassRubriquePrestation) lisRubriquePrestations.toArray()[0];
//                rubriqueCategorie = rubriquePrestation.getIdRubriqueCategorie();
//                for (OrclassRubriquePrestation rp : lisRubriquePrestations) {
//                    if (rp.getIdPrestation() == null || rp.getIdPrestation().getId() == null) {
//                        continue;
//                    }
//                    if (rp != null && rp.getId() != null) {
//                        continue;
//                    }
//                    rubriquePrestation = rubriquePrestationDao.finKey(rp.getIdRubriqueCategorie(), rp.getIdPrestation(), entreprise);
//                    if (rubriquePrestation == null) {
//                        rubriquePrestation = new OrclassRubriquePrestation();
//                        rubriquePrestation.setIdEntreprise(entreprise);
//                        rubriquePrestation.setIdPrestation(rp.getIdPrestation());
//                        rubriquePrestation.setIdRubriqueCategorie(rp.getIdRubriqueCategorie());
//                        rubriquePrestation.setDateCreation(new Date());
//                        rubriquePrestationDao.create(rubriquePrestation);
//                    }
//                }
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//                lisRubriquePrestations = rubriquePrestationDao.listeRubriPrestationByRubriqueCategorie(rubriqueCategorie, entreprise);
//                rubriquePrestation = new OrclassRubriquePrestation();
//                rubriquePrestation.setIdRubriqueCategorie(rubriqueCategorie);
//                lisRubriquePrestations.add(rubriquePrestation);
//
//                this.updateTableRubriquePrestationPrestation();
//                this.reverseListe();
//
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "rubrique.prestation", exception.Error.FATAL_ERROR + "", new Object[]{"rubrique.categorie"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        return null;
//    }
//
//    public String deleteRubriquePrestagtion() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "rubrique.prestation", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (rubriquePrestation != null && rubriquePrestation.getId() != null) {
//                this.setRubriqueCategorie(rubriquePrestation.getIdRubriqueCategorie());
//                rubriquePrestationDao.delete(rubriquePrestation);
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "rubrique.prestation", exception.Error.FATAL_ERROR + "", new Object[]{"rubrique.categorie"});
//            logger.error("Erreur Survenu", th);
//        }
//        lisRubriquePrestations = rubriquePrestationDao.listeRubriPrestationByRubriqueCategorie(rubriqueCategorie, entreprise);
//        rubriquePrestation = new OrclassRubriquePrestation();
//        rubriquePrestation.setIdRubriqueCategorie(rubriqueCategorie);
//        lisRubriquePrestations.add(rubriquePrestation);
//
//        this.reverseListe();
//        this.updateTableRubriquePrestationPrestation();
//
//        return null;
//    }
//
//    public void showDetailsRubrique(OrclassRubriquePrestation item) {
////        if (rubriquePrestation == null || rubriquePrestation.getId() == null) {
//        this.setRubriquePrestation(item);
////        }
//
//    }
//
//    public OrclassRubriquePrestation getRubriquePrestation() {
//        return rubriquePrestation;
//    }
//
//    public void setRubriquePrestation(OrclassRubriquePrestation rubriquePrestation) {
//        this.rubriquePrestation = rubriquePrestation;
//    }
//
//    public OrclassRubriqueCategorie getRubriqueCategorie() {
//        return rubriqueCategorie;
//    }
//
//    public void setRubriqueCategorie(OrclassRubriqueCategorie rubriqueCategorie) {
//        this.rubriqueCategorie = rubriqueCategorie;
//    }
//
//    public List<OrclassCategories> getLisCategories() {
//        return lisCategories;
//    }
//
//    public void setLisCategories(List<OrclassCategories> lisCategories) {
//        this.lisCategories = lisCategories;
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
//    public List<OrclassRubrique> getLisRubriques() {
//        return lisRubriques;
//    }
//
//    public void setLisRubriques(List<OrclassRubrique> lisRubriques) {
//        this.lisRubriques = lisRubriques;
//    }
//
//    public List<OrclassPrestation> getLisPrestation() {
//        return lisPrestation;
//    }
//
//    public void setLisPrestation(List<OrclassPrestation> lisPrestation) {
//        this.lisPrestation = lisPrestation;
//    }
//
//    public List<OrclassPrestation> getSelectePrestations() {
//        return selectePrestations;
//    }
//
//    public void setSelectePrestations(List<OrclassPrestation> selectePrestations) {
//        this.selectePrestations = selectePrestations;
//    }
//
//    public List<OrclassRubriquePrestation> getLisRubriquePrestations() {
//        return lisRubriquePrestations;
//    }
//
//    public void setLisRubriquePrestations(List<OrclassRubriquePrestation> lisRubriquePrestations) {
//        this.lisRubriquePrestations = lisRubriquePrestations;
//    }
//
//    public List<OrclassRubriquePrestation> getFilterRubriquePrestations() {
//        return filterRubriquePrestations;
//    }
//
//    public void setFilterRubriquePrestations(List<OrclassRubriquePrestation> filterRubriquePrestations) {
//        this.filterRubriquePrestations = filterRubriquePrestations;
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
//    public OrclassRubrique getRubrique() {
//        return rubrique;
//    }
//
//    public void setRubrique(OrclassRubrique rubrique) {
//        this.rubrique = rubrique;
//    }
//
//    public List<OrclassPrestation> getFilterPrestation() {
//        return filterPrestation;
//    }
//
//    public void setFilterPrestation(List<OrclassPrestation> filterPrestation) {
//        this.filterPrestation = filterPrestation;
//    }
//
//    public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.rubrique_group.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.rubrique_group.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.ajouter.name(), fon);
//
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//    
//    public Boolean accessSupprimer() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.rubrique_group.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//}
