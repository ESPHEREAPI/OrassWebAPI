///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import dao.OrclassCategoriesDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassRubriqueCategorieDao;
//import dao.OrclassRubriqueDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleParametrage;
//import enums.NatureRubrique;
//import enums.TypeRecoursRubrique;
//import enums.UtilisationRubrique;
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
//import javax.inject.Inject;
//import javax.validation.ConstraintViolationException;
//import modele.OrclassActions;
//import modele.OrclassCategories;
//import modele.OrclassElt_Categorie_Compagnie;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassGarantie;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassRubrique;
////import modele.OrclassRubrique;
//import modele.OrclassRubriqueCategorie;
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
//@Named(value = "rubriqueCategorieController")
//@ViewScoped
//public class RubriqueCategorieController implements Serializable {
//
//    private static final Logger logger = LoggerFactory.getLogger(RubriqueCategorieController.class);
//    @EJB
//    OrclassCategoriesDao categoriesDao;
//    OrclassCategories categories;
//    OrclassCategories categoriesSelect;
//    @EJB
//    OrclassRubriqueCategorieDao rubriqueCategorieDao;
//    OrclassRubriqueCategorie rubriqueCategorie;
//    OrclassEntreprises entreprise;
//    @EJB
//    OrclassRubriqueDao rubriqueDao;
//    OrclassRubrique rubrique;
//
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    IDroitAcces serviceAccess;
//    @Inject
//    Rubricontroller rubricontroller;
//
//    private Collection<OrclassCategories> lisCategories = new ArrayList<>();
//    private Collection<OrclassCategories> filterCategories = new ArrayList<>();
//    private List<OrclassRubriqueCategorie> lisRubriqueCategoeie = new ArrayList<>();
//    private Collection<OrclassRubriqueCategorie> filterRubriqueCategoeie = new ArrayList<>();
//    private List<OrclassRubrique> lisRubrique = new ArrayList<>();
//    private Collection<OrclassRubrique> filterRubrique = new ArrayList<>();
//    String summary = "";
//    String msgdetail = "";
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//
//    /**
//     * Creates a new instance of RubriqueCategorieController
//     */
//    public RubriqueCategorieController() {
//        categories = new OrclassCategories();
//        categoriesSelect = new OrclassCategories();
//        rubriqueCategorie = new OrclassRubriqueCategorie();
//    }
//
//    public List<SelectItem> getNatureRubrique() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (NatureRubrique n : NatureRubrique.values()) {
//            items.add(new SelectItem(n, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, n.name(), null, myLoc)));
//
//        }
//        return items;
//    }
//
//    public List<SelectItem> getTypeRecoursRubrique() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (TypeRecoursRubrique n : TypeRecoursRubrique.values()) {
//            items.add(new SelectItem(n, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, n.name(), null, myLoc)));
//
//        }
//        return items;
//    }
//
//    public List<SelectItem> getUtilisationRubrique() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (UtilisationRubrique u : UtilisationRubrique.values()) {
//            items.add(new SelectItem(u, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, u.name(), null, myLoc)));
//
//        }
//        return items;
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
////        lisRubrique = rubriqueDao.liteRubriqueByCompagnie(entreprise);
//        menu = menusDao.findEntityHavingValue("code", "assure.rubrique");
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        lisCategories = categoriesDao.listAllCategoriesShowAllCompagnie();
//        lisCategories.addAll(categoriesDao.listAllCategorieByCompagnie(entreprise));
//        chargeRubrique();
//        this.updateTableCategories();
//        this.updateTableRubrique();
//
//    }
//
//    public void updateTableCategories() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2_2:idtableCategorie");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('tableCategorie').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//       public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
//        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
////        if (LangUtils.isBlank(filterText)) {
////            return true;
////        }
////        int filterInt = getInteger(filterText);
//
//        OrclassRubriqueCategorie rubCat = (OrclassRubriqueCategorie) value;
//        if (rubCat.getId() == null) {
//            return true;
//        }
//        return rubCat.getIdRubrique().getCode().toLowerCase().contains(filterText)
//                || rubCat.getIdRubrique().getLibelle().toLowerCase().contains(filterText);
//    }
//
//    public void chargeRubrique() {
////        lisRubrique = rubriqueDao.liteRubriqueByCompagnie(entreprise);
////        lisRubrique.addAll(rubriqueDao.liteRubriqueForAllCompagnie());
//        lisRubrique = (List<OrclassRubrique>) rubriqueDao.findAll();
//        if (lisRubrique.isEmpty()) {
////            rubricontroller.setEntreprise(entreprise);
//            rubricontroller.creatRubriqueByExcell();
////            lisRubrique = rubriqueDao.liteRubriqueByCompagnie(entreprise);
////            lisRubrique.addAll(rubriqueDao.liteRubriqueForAllCompagnie());
//            lisRubrique = (List<OrclassRubrique>) rubriqueDao.findAll();
//        }
//
//    }
//
//    public void chargeRubriqueByCategories() {
//        OrclassRubriqueCategorie rc = null;
//        OrclassElt_Categorie_Compagnie elt = null;
//        this.chargeRubrique();
//        lisRubriqueCategoeie.clear();
//
//        List<OrclassGarantie> ListeGaranties = new ArrayList<>();
//        if (categoriesSelect != null && categoriesSelect.getIdCategorie() != null) {
//
//            lisRubriqueCategoeie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categoriesSelect, entreprise);
//            if (ListeGaranties.isEmpty()) {
//
//                rc = new OrclassRubriqueCategorie();
//                rc.setIdCategories(categoriesSelect);
//                lisRubriqueCategoeie.add(rc);
//            } else {
////                for (OrclassGarantie ga : ListeGaranties) {
////                    ListeGarantie.add(ga);
////                }
//                rc = new OrclassRubriqueCategorie();
//                rc.setIdCategories(categoriesSelect);
//                lisRubriqueCategoeie.add(rc);
//            }
//            lisRubrique.removeAll(rubriqueCategorieDao.OrclassRubriqueByCategorieDao(categoriesSelect, entreprise));
//
//        }
//
//        this.updateTableRubriqueCategorie();
//        this.updateTableRubrique();
//        PrimeFaces.current().ajax().update("form1_1");
//        PrimeFaces.current().ajax().update(":form1_1:updateTableRubriqueCategorie");
//        PrimeFaces.current().ajax().update(":form2_2:idrefGarantieTable");
//        this.reverseListe();
//
//    }
//
//    public void updateTableRubriqueCategorie() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1_1:idrubriqueCategorieTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('rubriqueCategorieTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public void updateTableRubrique() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2_2:idrefGarantieTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('refGarantieTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public void showRubriCategories(OrclassRubriqueCategorie item) {
////        if (rubriqueCategorie.getIdRubrique()==null || rubriqueCategorie.getIdRubrique().getId()==null) {
//        this.setRubriqueCategorie(item);
////        }
//
//    }
//
//    public String addRubriqueByCategorie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "rubrique.categorie", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (rubriqueCategorie != null && rubriqueCategorie.getIdRubrique().getId() != null && (categoriesSelect != null && categoriesSelect.getIdCategorie() != null)) {
//                if (rubriqueCategorie.getUtilisationRubrique() == null) {
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE  SELECT VALUE FOR UTILISATION", ""));
//                    return null;
//                }
//                rubriqueCategorie.setIdEntreprise(entreprise);
//                rubriqueCategorie.setIdCategories(categoriesSelect);
//                rubriqueCategorie.setDateCreation(new Date());
//                rubriqueCategorieDao.create(rubriqueCategorie);
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
////                for (OrclassRubriqueCategorie rc : lisRubriqueCategoeie) {
////                    if (rc.getIdRubrique() != null && rc.getIdRubrique().getId() != null && rubriqueCategorie.getIdRubrique().getId().equals(rc.getIdRubrique().getId())) {
////                        int index = lisRubriqueCategoeie.indexOf(rc);
////                        if (index > 0) {
////                            lisRubriqueCategoeie.set(index, rubriqueCategorie);
////                        }
////                    }
////                }
//                lisRubriqueCategoeie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(rubriqueCategorie.getIdCategories(), entreprise);
//                rubriqueCategorie = new OrclassRubriqueCategorie();
//                rubriqueCategorie.setIdCategories(categoriesSelect);
//                lisRubriqueCategoeie.add(rubriqueCategorie);
//                this.updateTableRubriqueCategorie();
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "rubrique.categorie", exception.Error.FATAL_ERROR + "", new Object[]{"rubrique.categorie"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        return null;
//    }
//
//    public void removeRubriqueByListe() {
//        lisRubriqueCategoeie.remove(rubriqueCategorie);
//        this.updateTableRubriqueCategorie();
//        PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public String updateRubriqueByCategorie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "rubrique.categorie", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (rubriqueCategorie != null && rubriqueCategorie.getId() != null) {
//
//                rubriqueCategorie.setDateModification(new Date());
//
//                rubriqueCategorieDao.update(rubriqueCategorie);
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//                lisRubriqueCategoeie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(rubriqueCategorie.getIdCategories(), entreprise);
//                rubriqueCategorie = new OrclassRubriqueCategorie();
//                rubriqueCategorie.setIdCategories(categoriesSelect);
//                lisRubriqueCategoeie.add(rubriqueCategorie);
//                this.updateTableRubriqueCategorie();
//                this.reverseListe();
//
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "garantie", exception.Error.FATAL_ERROR + "", new Object[]{"garantie"});
//            logger.error("Erreur Survenu", th);
//        }
////        for (OrclassRubriqueCategorie rc : lisRubriqueCategoeie) {
////            if (rc.getIdRubrique().getId() != null && rc.getIdRubrique().getId().equals(rc.getIdRubrique().getId())) {
////                int index = lisRubriqueCategoeie.indexOf(rc);
////                if (index > 0) {
////                    lisRubriqueCategoeie.set(index, rubriqueCategorie);
////                }
////            }
////        }
////        this.removeGarantieByListe();
////        this.reverseListe();
//        return null;
//    }
//
//    public String deleteRubriqueCateGories() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "rubrique.categorie", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (rubriqueCategorie != null && rubriqueCategorie.getId() != null) {
//
//                rubriqueCategorieDao.delete(rubriqueCategorie);
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "rubrique.categorie", exception.Error.FATAL_ERROR + "", new Object[]{"rubrique.categorie"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        lisRubriqueCategoeie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(rubriqueCategorie.getIdCategories(), entreprise);
//        rubriqueCategorie = new OrclassRubriqueCategorie();
//        rubriqueCategorie.setIdCategories(categoriesSelect);
//        lisRubriqueCategoeie.add(rubriqueCategorie);
//        this.updateTableRubriqueCategorie();
//        this.reverseListe();
//
//        return null;
//    }
//
//    public void showDetailsRubrique(OrclassRubrique item) {
//        if (rubrique == null || rubrique.getId() == null) {
//            this.setRubrique(rubrique);
//        }
//        if (rubriqueCategorieDao.OrclassRubriqueCategorieDao(categoriesSelect, rubrique, entreprise) == null) {
//            chargeRubriqueCategorie();
//        }
//
//    }
//
//    public void reverseListe() {
//
//        List<OrclassRubriqueCategorie> result = new ArrayList<>();
//        for (int i = lisRubriqueCategoeie.size() - 1; i >= 0; i--) {
//            result.add(lisRubriqueCategoeie.get(i));
//        }
//
//        this.setLisRubriqueCategoeie(result);
////        this.updateTableRubriqueCategorie();
//    }
//
//    public void chargeRubriqueCategorie() {
//        OrclassRubriqueCategorie rc = null;
//        int size = lisRubriqueCategoeie.size();
//        if (size > 0 && size == 1 && rubrique != null && rubrique.getId() != null) {
//            rc = lisRubriqueCategoeie.get(0);
//            rc.setIdRubrique(rubrique);
//
//            lisRubriqueCategoeie.set(0, rc);
//            rc = new OrclassRubriqueCategorie();
//            lisRubriqueCategoeie.add(rc);
//
//        }
//        if (size > 0 && size > 1 && rubrique != null && rubrique.getId() != null && categoriesSelect != null && categoriesSelect.getIdCategorie() != null) {
////            int index = size - 1;
//            int index = 0;
//            if (rubriqueCategorieDao.OrclassRubriqueCategorieDao(categoriesSelect, rubrique, entreprise) == null) {
//                rc = lisRubriqueCategoeie.get(index);
//                for (OrclassRubriqueCategorie r : lisRubriqueCategoeie) {
//                    if (r.getIdRubrique().getId() != null && r.getIdRubrique().getId().equals(rubrique.getId())) {
//                        return;
//                    }
//                }
//                rc.setIdCategories(categoriesSelect);
//                rc.setIdRubrique(rubrique);
//                lisRubriqueCategoeie.set(index, rc);
//                rc = new OrclassRubriqueCategorie();
//
//                lisRubriqueCategoeie.add(rc);
//            } else {
//                rc = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categoriesSelect, rubrique, entreprise);
//                lisRubriqueCategoeie.add(rc);
//
//            }
//
//        }
////        this.reverseListe();
//        this.updateTableRubriqueCategorie();
//        PrimeFaces.current().ajax().update("form1_1");
//
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
//    public OrclassCategories getCategoriesSelect() {
//        return categoriesSelect;
//    }
//
//    public void setCategoriesSelect(OrclassCategories categoriesSelect) {
//        this.categoriesSelect = categoriesSelect;
//    }
//
//    public OrclassCategoriesDao getCategoriesDao() {
//        return categoriesDao;
//    }
//
//    public void setCategoriesDao(OrclassCategoriesDao categoriesDao) {
//        this.categoriesDao = categoriesDao;
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
//    public Collection<OrclassCategories> getLisCategories() {
//        return lisCategories;
//    }
//
//    public void setLisCategories(Collection<OrclassCategories> lisCategories) {
//        this.lisCategories = lisCategories;
//    }
//
//    public Collection<OrclassCategories> getFilterCategories() {
//        return filterCategories;
//    }
//
//    public void setFilterCategories(Collection<OrclassCategories> filterCategories) {
//        this.filterCategories = filterCategories;
//    }
//
//    public List<OrclassRubriqueCategorie> getLisRubriqueCategoeie() {
//        return lisRubriqueCategoeie;
//    }
//
//    public void setLisRubriqueCategoeie(List<OrclassRubriqueCategorie> lisRubriqueCategoeie) {
//        this.lisRubriqueCategoeie = lisRubriqueCategoeie;
//    }
//
//    public Collection<OrclassRubriqueCategorie> getFilterRubriqueCategoeie() {
//        return filterRubriqueCategoeie;
//    }
//
//    public void setFilterRubriqueCategoeie(Collection<OrclassRubriqueCategorie> filterRubriqueCategoeie) {
//        this.filterRubriqueCategoeie = filterRubriqueCategoeie;
//    }
//
//    public List<OrclassRubrique> getLisRubrique() {
//        return lisRubrique;
//    }
//
//    public void setLisRubrique(List<OrclassRubrique> lisRubrique) {
//        this.lisRubrique = lisRubrique;
//    }
//
//    public Collection<OrclassRubrique> getFilterRubrique() {
//        return filterRubrique;
//    }
//
//    public void setFilterRubrique(Collection<OrclassRubrique> filterRubrique) {
//        this.filterRubrique = filterRubrique;
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
//    public Boolean accessModifier() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.rubrique_assure.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.rubrique_assure.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean accessAfficheListe() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.rubrique_assure.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.afficher_liste.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.rubrique_assure.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.rubrique_assure.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);
//
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//}
