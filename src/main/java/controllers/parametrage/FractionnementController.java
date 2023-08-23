///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import dao.OrclassCategoriesDao;
//import dao.OrclassFractionnementCategoriesDao;
//import dao.OrclassFractionnementDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleParametrage;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Collection;
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
//import modele.OrclassCategories;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassFractionnement;
//import modele.OrclassFractionnementCategories;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
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
// * @author JIATOU FRANCK
// */
//@Named(value = "fractionnementController")
//@ViewScoped
//public class FractionnementController implements Serializable {
//
//    private static final Logger logger = LoggerFactory.getLogger(FractionnementController.class);
//    @EJB
//    OrclassFractionnementDao fractionnementDao;
//    OrclassFractionnement fractionnement;
//    @EJB
//    OrclassFractionnementCategoriesDao fractionnementCategoriesDao;
//    OrclassFractionnementCategories fractionnementCategories;
//    @EJB
//    OrclassCategoriesDao categoriesDao;
//    OrclassCategories categories;
//
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//
//    @EJB
//    IDroitAcces serviceAccess;
//
//    private List<OrclassFractionnement> listeFractionnement = new ArrayList<>();
//    private List<OrclassCategories> filterFractionnement = new ArrayList<>();
//   List<OrclassCategories> filterCategories = new ArrayList<>(); 
//    private List<OrclassFractionnementCategories> listeFractionnementCategorieses = new ArrayList<>();
//    private List<OrclassFractionnementCategories> filterFractionnementCategories = new ArrayList<>();
//    private List<OrclassCategories> listeCategorieses = new ArrayList<>();
//
//    String summary = "";
//    String msgdetail = "";
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//    OrclassEntreprises entreprise;
//    OrclassFractionnement addFraction;
//
//    /**
//     * Creates a new instance of FractionnementController
//     */
//    public FractionnementController() {
//        fractionnementCategories = new OrclassFractionnementCategories();
//        categories = new OrclassCategories();
//        addFraction=new OrclassFractionnement();
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
//        listeCategorieses = categoriesDao.listAllCategoriesShowAllCompagnie();
//        listeCategorieses.addAll(categoriesDao.listAllCategorieByCompagnie(entreprise));
//        listeFractionnement = (List<OrclassFractionnement>) fractionnementDao.findAll();
//        this.updateTablecategorie();
//        this.updateTableLibelleFraction();
//        this.updateTableLibelleFraction();
//
//    }
//
//    public void showDetailsRubrique(OrclassFractionnement item) {
//        if (fractionnement == null || fractionnement.getId() == null) {
//            this.setFractionnement(item);
//        }
//        if (fractionnementCategoriesDao.lastRowFractionnement(categories, entreprise, fractionnement) == null) {
//            fractionnementCategories = listeFractionnementCategorieses.get(0);
//            fractionnementCategories.setIdFractionnemente(fractionnement);
//            fractionnementCategories.setIdCategories(categories);
//            listeFractionnementCategorieses.set(0, fractionnementCategories);
//        }
//
//    }
//    
//       public void updateTableFraction() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:idfractionCategorieTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('fractionCategorieTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//         public void updateTablecategorie() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:idtableCategorie");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('tableCategorie').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//         
//           public void updateTableLibelleFraction() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:idfractionTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('fractionTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public void chargeFractionnementByCategories(OrclassCategories item) {
//
//        listeFractionnementCategorieses.clear();
//        OrclassFractionnementCategories fc = null;
//        if (categories==null|| categories.getIdCategorie()==null) {
//           this.setCategories(item);
//        }
//
//        if (categories != null && categories.getIdCategorie() != null) {
//
//            listeFractionnementCategorieses = fractionnementCategoriesDao.listeFractionnementHaveCategories(categories, entreprise);
//            if (listeFractionnementCategorieses.isEmpty()) {
//
//                fc = new OrclassFractionnementCategories();
//                fc.setIdCategories(categories);
//                listeFractionnementCategorieses.add(fc);
//            } else {
////                for (OrclassGarantie ga : ListeGaranties) {
////                    ListeGarantie.add(ga);
////                }
//                fc = new OrclassFractionnementCategories();
//                fc.setIdCategories(categories);
//                listeFractionnementCategorieses.add(fc);
//            }
//            
//        }
//
//      
//       
//      
//        this.updateTableFraction();
//        PrimeFaces.current().ajax().update(":form1,:form1:idfractionCategorieTable");
//         this.reverseListe();
//
//    }
//       public void removeFractioneByListe() {
//        listeFractionnementCategorieses.remove(fractionnementCategories);
//        
//        PrimeFaces.current().ajax().update("form1");
//
//    }
//
//    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
//        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
////        if (LangUtils.isBlank(filterText)) {
////            return true;
////        }
////        int filterInt = getInteger(filterText);
//
//        OrclassFractionnementCategories fc = (OrclassFractionnementCategories) value;
//        if (fc.getId() == null) {
//            return true;
//        }
//        return fc.getIdFractionnemente().getCode().toLowerCase().contains(filterText)
//                || fc.getIdFractionnemente().getLibelle().toLowerCase().contains(filterText);
//    }
//
//    public void chargeFractionnementByCategoerie() {
//        if (categories != null && categories.getIdCategorie() != null) {
//            listeFractionnementCategorieses = fractionnementCategoriesDao.listeFractionnementByCategorie(categories, entreprise);
//        }
//    }
//
//    public String addFractionnementCategorie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "fractionnement", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (fractionnementCategories != null && fractionnementCategories.getId()==null && fractionnementCategories.getIdFractionnemente() != null && fractionnementCategories.getIdFractionnemente().getId() != null) {
//                if (fractionnementCategories.getIdFractionnemente().getCode() == null || "".equals(fractionnementCategories.getIdFractionnemente().getCode())) {
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE  CODE IS NULL", "WRITE VALUE"));
//                    return null;
//                }
//                fractionnementCategories.setIdEntreprise(entreprise);
//                fractionnementCategories.setIdCategories(categories);
//
//                fractionnementCategoriesDao.create(fractionnementCategories);
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
//                listeFractionnementCategorieses = fractionnementCategoriesDao.listeFractionnementHaveCategories(categories, entreprise);
//                fractionnementCategories = new OrclassFractionnementCategories();
//                listeFractionnementCategorieses.add(fractionnementCategories);
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "fractionnement", exception.Error.FATAL_ERROR + "", new Object[]{"fractionnement"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        return null;
//    }
//
//    public void removeRubriqueByListe() {
//        listeFractionnementCategorieses.remove(fractionnementCategories);
//
//        PrimeFaces.current().ajax().update("form1");
//
//    }
//
//    public String updateFractionnementCategorie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "fractionnement", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (fractionnementCategories != null && fractionnementCategories.getId() != null) {
//
//                fractionnementCategoriesDao.update(fractionnementCategories);
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//                listeFractionnementCategorieses = fractionnementCategoriesDao.listeFractionnementByCategorie(categories, entreprise);
//                fractionnementCategories = new OrclassFractionnementCategories();
//                listeFractionnementCategorieses.add(fractionnementCategories);
//                this.reverseListe();
//
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "fractionnement", exception.Error.FATAL_ERROR + "", new Object[]{"fractionnement"});
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
//    public String deleteFractionnement() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "fractionnement", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (fractionnementCategories != null && fractionnementCategories.getId() != null) {
//
//                fractionnementCategoriesDao.delete(fractionnementCategories);
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
//        listeFractionnementCategorieses = fractionnementCategoriesDao.listeFractionnementByCategorie(categories, entreprise);
//        fractionnementCategories = new OrclassFractionnementCategories();
//        listeFractionnementCategorieses.add(fractionnementCategories);
//
//        this.reverseListe();
//
//        return null;
//    }
//
//    public void reverseListe() {
//
//        List<OrclassFractionnementCategories> result = new ArrayList<>();
//        for (int i = listeFractionnementCategorieses.size() - 1; i >= 0; i--) {
//            result.add(listeFractionnementCategorieses.get(i));
//        }
//
//        this.setListeFractionnementCategorieses(result);
////        this.updateTableRubriqueCategorie();
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
//    public OrclassFractionnementCategories getFractionnementCategories() {
//        return fractionnementCategories;
//    }
//
//    public void setFractionnementCategories(OrclassFractionnementCategories fractionnementCategories) {
//        this.fractionnementCategories = fractionnementCategories;
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
//    public Collection<OrclassFractionnement> getListeFractionnement() {
//        return listeFractionnement;
//    }
//
//    public void setListeFractionnement(List<OrclassFractionnement> listeFractionnement) {
//        this.listeFractionnement = listeFractionnement;
//    }
//
//    public Collection<OrclassCategories> getFilterFractionnement() {
//        return filterFractionnement;
//    }
//
//    public void setFilterFractionnement(List<OrclassCategories> filterFractionnement) {
//        this.filterFractionnement = filterFractionnement;
//    }
//
//    public List<OrclassFractionnementCategories> getListeFractionnementCategorieses() {
//        return listeFractionnementCategorieses;
//    }
//
//    public void setListeFractionnementCategorieses(List<OrclassFractionnementCategories> listeFractionnementCategorieses) {
//        this.listeFractionnementCategorieses = listeFractionnementCategorieses;
//    }
//
//    public Collection<OrclassFractionnementCategories> getFilterFractionnementCategories() {
//        return filterFractionnementCategories;
//    }
//
//    public void setFilterFractionnementCategories(List<OrclassFractionnementCategories> filterFractionnementCategories) {
//        this.filterFractionnementCategories = filterFractionnementCategories;
//    }
//
//    public Collection<OrclassCategories> getListeCategorieses() {
//        return listeCategorieses;
//    }
//
//    public void setListeCategorieses(List<OrclassCategories> listeCategorieses) {
//        this.listeCategorieses = listeCategorieses;
//    }
//
//    public Boolean accessModifier() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.fractionnement.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.fractionnement.name());
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
//            try {
//                fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.fractionnement.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.afficher_liste.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//            } catch (Exception e) {
//                   return Boolean.FALSE;
//            }
//            
//        }
//        return Boolean.FALSE;
//    }
//
//    public void showFractionnementCategories(OrclassFractionnementCategories item) {
////        if (rubriqueCategorie.getIdRubrique()==null || rubriqueCategorie.getIdRubrique().getId()==null) {
//        this.setFilterFractionnementCategories(filterFractionnementCategories);
////        }
//
//    }
//
//    public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.fractionnement.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.fractionnement.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);
//
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Collection<OrclassCategories> getFilterCategories() {
//        return filterCategories;
//    }
//
//    public void setFilterCategories(List<OrclassCategories> filterCategories) {
//        this.filterCategories = filterCategories;
//    }
//    
//    
//
//}
