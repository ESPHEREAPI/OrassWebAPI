///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import com.sun.imageio.plugins.jpeg.JPEG;
//import dao.OrclassCategoriesDao;
//import dao.OrclassGarantieDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassReductionDao;
//import dao.OrclassRefReductionDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleParametrage;
//import enums.LibelleCategorie;
//import exception.GlobalException;
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
//import javax.validation.ConstraintViolationException;
//import modele.OrclassActions;
//import modele.OrclassCategories;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassGarantie;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassReduction;
//import modele.OrclassRefReduction;
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
//@Named(value = "reductionController")
//@ViewScoped
//public class ReductionController implements Serializable {
//
//    /**
//     * Creates a new instance of ReductionController
//     */
//    @EJB
//    OrclassReductionDao reductionDao;
//    OrclassReduction reduction;
//    @EJB
//    OrclassCategoriesDao categoriesDao;
//    OrclassCategories categories;
//
//    @EJB
//    OrclassGarantieDao garantieDao;
//    OrclassGarantie garantie;
//    @EJB
//    OrclassRefReductionDao refReductionDao;
//
//    OrclassRefReduction refReduction, refReductionAdd;
//    @EJB
//    IDroitAcces serviceAccess;
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    private static final Logger logger = LoggerFactory.getLogger(ReductionController.class);
//    String summary = "";
//    String msgdetail = "";
//    private List<OrclassCategories> listeCategories = new ArrayList<>();
//    private List<OrclassGarantie> listeGaranties = new ArrayList<>();
//    private List<OrclassReduction> listeReduction = new ArrayList<>();
//    private List<OrclassReduction> filterReduction = new ArrayList<>();
//    private List<OrclassRefReduction> listeRefReduction = new ArrayList<>();
//
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//    OrclassUtilisateurs utilisateurs;
//    private OrclassEntreprises entreprise;
//
//    public ReductionController() {
//        refReduction = new OrclassRefReduction();
//        reduction = new OrclassReduction();
//        garantie = new OrclassGarantie();
//        categories = new OrclassCategories();
//        refReductionAdd=new OrclassRefReduction();
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
//
//        menu = menusDao.findEntityHavingValue("code", "reduction");
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
////        listeReduction = reductionDao.listeReductionByCompagnie(entreprise);
//        listeCategories = categoriesDao.getAllCategorieByEntreprise(entreprise);
//        listeCategories.addAll(categoriesDao.listAllCategoriesShowAllCompagnie());
//        listeRefReduction = (List<OrclassRefReduction>) refReductionDao.findAll();
//    }
//
//    public void reset() {
//
//        this.init();
//
//        PrimeFaces.current().ajax().update("form1,form2");
////        this.updateDataTable();
//    }
//
//    public void init() {
////        refReduction = new OrclassRefReduction();
//        reduction = new OrclassReduction();
////        garantie = new OrclassGarantie();
//        categories = new OrclassCategories();
//           refReductionAdd=new OrclassRefReduction();
//    }
//
//    public void chargeGarantieByCategorie() {
//        if (categories != null && categories.getIdCategorie() != null) {
//            listeGaranties = garantieDao.getallGarantieByCategorieByCompagnie(entreprise, categories);
//
//        }
//    }
//    // permettre la modifcation d une reduction qui n a pas ete utlise si la reduction est deja lieé a une police la modification sera bloquer
//    public void controleUpdateReductionUsePolice(OrclassReduction item){
//        if (item!=null && item.getId()!=null) {
//            reductionDao.reductionUseForPolice(entreprise, item);
//        }
//        if (item==null) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("VALEUR REDUCTION EST NULL", "CHARGEMENT NON PRISE EN COMPTE"));
//        }
//    }
//
//    public void chargeReductionByRefReduction() {
//        listeReduction = new ArrayList<>();
//        if (refReduction != null && refReduction.getId() != null) {
//            listeReduction = reductionDao.listeReductionByCompagnie(entreprise, refReduction);
//        }
//        if (listeReduction.isEmpty()) {
//            reduction = new OrclassReduction();
//            listeReduction.add(reduction);
//        } else {
//            reduction = new OrclassReduction();
//            listeReduction.add(reduction);
//            this.reverseListe();
//        }
//        this.updateDataTable();
//        PrimeFaces.current().ajax().update(":form1");
//    }
//
//    public void updateDataTable() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:idreductionTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('reductionTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public void reverseListe() {
//
//        List<OrclassReduction> result = new ArrayList<>();
//        for (int i = listeReduction.size() - 1; i >= 0; i--) {
//
//            result.add(listeReduction.get(i));
//        }
//        this.setListeReduction(result);
//
//    }
//
//    public void removeReductionByListe() {
//        listeReduction.remove(reduction);
//        this.updateDataTable();
//        PrimeFaces.current().ajax().update("form1");
//        this.reverseListe();
//
//    }
//
//    public void addRefReduction() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        if (refReductionAdd.getCode() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "CODE IS NULL"));
//            return;
//        }
//        if (refReductionAdd.getLibelle() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "CODE IS NULL"));
//            return;
//        }
//        refReduction = refReductionDao.findEntityHavingValue("code", refReductionAdd.getCode());
//        if (refReduction == null) {
//            refReduction = refReductionDao.findEntityHavingValue("libelle", refReductionAdd.getLibelle());
//        }
//        if (refReduction == null) {
//            refReductionDao.create(refReductionAdd);
//            this.setRefReduction(refReductionAdd);
//        }
//        listeRefReduction=(List<OrclassRefReduction>) refReductionDao.findAll();
//        this.chargeReductionByRefReduction();
//        PrimeFaces.current().executeScript("PF('manageAddRefReductionDialog').hide()");
//
//    }
//    public void chargeUpdateRefReduction(){
//        if (refReduction!=null && refReduction.getId()!=null) {
//            this.setRefReductionAdd(refReduction);
//            PrimeFaces.current().executeScript("PF('manageAddRefReductionDialog').show()");
//        }
//    }
//
//    public void updateRefReduction() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        if (this.refReductionAdd != null && this.refReductionAdd.getId() != null) {
//            refReductionDao.update(refReductionAdd);
//            this.setRefReduction(refReductionAdd);
//        }
//
//        PrimeFaces.current().executeScript("PF('manageAddRefReductionDialog').hide()");
//
//    }
//
//    public String addRduction() {
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "reduction", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (refReduction.getCode() == null) {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "CODE IS NULL"));
//                return null;
//            }
//            if (refReduction.getLibelle() == null) {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "Libelle IS NULL"));
//                return null;
//            }
//            if (refReductionDao.findEntityHavingValue("code", refReduction.getCode()) == null) {
//                  ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE CREATE OBJET", "OBJET IS NOT EXISTE"));
//                return null;
//            }
////            refReduction = refReductionDao.findEntityHavingValue("code", refReduction.getCode());
//
//            if (reductionDao.finKey(entreprise, refReduction, reduction.getDateEffet(), reduction.getIdGarantie()) == null) {
//                reduction.setDateCreation(new Date());
//                reduction.setIdEntreprise(entreprise);
//                reduction.setIdRefReduction(refReduction);
////                reduction.setIdGarantie(garantie);
//                reduction.setCreer_par(user.getNom() + " " + user.getPrenom());
//                reductionDao.create(reduction);
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//               listeReduction=reductionDao.listeReductionByCompagnie(entreprise, refReduction);
//                reduction=new OrclassReduction();
//                listeReduction.add(reduction);
//              
//                this.reverseListe();
//                  this.updateDataTable();
//
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", exception.Error.DUPLICATE_ERROR_INSERT.name()));
//            }
//        } catch (GlobalException e) {
//
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCode(), e.getCode()));
////throw e;
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_FATAL, "reduction", exception.Error.FATAL_ERROR + "", new Object[]{"reduction"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        this.reset();
//        return null;
//
//    }
//
//    public String updateReduction() {
//        String success = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        //Locale myLoc =new Locale("fr");
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "reduction", null, myLoc)};
//        String[] detail = {entete[0], "Parametres"};
//
//        try {
//
//            if (reduction != null && reduction.getId() != null) {
//
//                reductionDao.update(reduction);
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
//    public String deleteReduction() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "reduction", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (reduction != null && reduction.getId() != null) {
//
//                reductionDao.delete(reduction);
//                listeReduction.remove(reduction);
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "duree", exception.Error.FATAL_ERROR + "", new Object[]{"reduction"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        this.reset();
//        return null;
//    }
//
//    public OrclassReduction getReduction() {
//        return reduction;
//    }
//
//    public void setReduction(OrclassReduction reduction) {
//        this.reduction = reduction;
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
//    public Collection<OrclassCategories> getListeCategories() {
//        return listeCategories;
//    }
//
//    public void setListeCategories(List<OrclassCategories> listeCategories) {
//        this.listeCategories = listeCategories;
//    }
//
//    public Collection<OrclassGarantie> getListeGaranties() {
//        return listeGaranties;
//    }
//
//    public void setListeGaranties(List<OrclassGarantie> listeGaranties) {
//        this.listeGaranties = listeGaranties;
//    }
//
//    public Collection<OrclassReduction> getListeReduction() {
//        return listeReduction;
//    }
//
//    public void setListeReduction(List<OrclassReduction> listeReduction) {
//        this.listeReduction = listeReduction;
//    }
//
//    public Collection<OrclassReduction> getFilterReduction() {
//        return filterReduction;
//    }
//
//    public void setFilterReduction(List<OrclassReduction> filterReduction) {
//        this.filterReduction = filterReduction;
//    }
//
//    public OrclassGarantie getGarantie() {
//        return garantie;
//    }
//
//    public void setGarantie(OrclassGarantie garantie) {
//        this.garantie = garantie;
//    }
//
//    public OrclassRefReduction getRefReduction() {
//        return refReduction;
//    }
//
//    public void setRefReduction(OrclassRefReduction refReduction) {
//        this.refReduction = refReduction;
//    }
//
//    public Collection<OrclassRefReduction> getListeRefReduction() {
//        return listeRefReduction;
//    }
//
//    public void setListeRefReduction(List<OrclassRefReduction> listeRefReduction) {
//        this.listeRefReduction = listeRefReduction;
//    }
//
//    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
//        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
////        if (LangUtils.isBlank(filterText)) {
////            return true;
////        }
////        int filterInt = getInteger(filterText);
//
//        OrclassReduction red = (OrclassReduction) value;
//        if (red.getId() == null) {
//            return true;
//        }
//        return red.getIdGarantie().getIdCategories().getCode().toLowerCase().contains(filterText)
//                || valueObjectByLibelleAutres(red.getIdGarantie().getIdCategories()).toLowerCase().contains(filterText)
//                || red.getIdGarantie().getIdRefGaranties().getCode().toLowerCase().contains(filterText)
//                || red.getIdGarantie().getIdRefGaranties().getLibelle().toLowerCase().contains(filterText);
//
//    }
//
//    public String valueObjectByLibelleAutres(OrclassCategories cl) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        if (cl != null && cl.getLibelle().equals(LibelleCategorie.autres)) {
//            return cl.getLibelleAutre();
//        }
//        if (cl != null) {
//            return LocaleHelper.getLocaleString(RecupBundle.FichierBundle, cl.getLibelle().toString(), null, myLoc);
//        }
//        return "";
//    }
//
//    public Boolean accessAjouter() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.reduction.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.ajouter.name(), fon);
//
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean accessCreer() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.reduction.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.reduction.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.reduction.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public OrclassRefReduction getRefReductionAdd() {
//        return refReductionAdd;
//    }
//
//    public void setRefReductionAdd(OrclassRefReduction refReductionAdd) {
//        this.refReductionAdd = refReductionAdd;
//    }
//    
//    
//
//}
