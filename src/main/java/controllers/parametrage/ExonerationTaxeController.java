///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import Excell.IExcell;
//
//import dao.OrclassExonerationDao;
//import dao.OrclassExonerationTaxeDao;
//
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassRefTimbreGradueDao;
//import dao.OrclassTypeTaxeDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleParametrage;
//import java.io.IOException;
//import javax.inject.Named;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//import java.util.Objects;
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.application.FacesMessage;
//import javax.faces.component.UIComponent;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.faces.view.ViewScoped;
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.ConstraintViolationException;
//import modele.OrclassActions;
//import modele.OrclassEntreprises;
//import modele.OrclassExoneration;
//import modele.OrclassExonerationTaxe;
//import modele.OrclassFonctionnalites;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassRefTimbreGradue;
//import modele.OrclassTypeTaxe;
//import modele.OrclassUtilisateurs;
//import org.primefaces.PrimeFaces;
//import org.primefaces.event.SelectEvent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import parametrage.ISecurite;
//import parametrage.ModuleMenu;
//import reference.IExoneration;
//import utils.GlobalFonctions;
//import utils.LocaleHelper;
//import utils.RecupBundle;
//
///**
// *
// * @author hp
// */
//@Named(value = "exonerationTaxeController")
//@ViewScoped
//public class ExonerationTaxeController implements Serializable {
//
//    /**
//     * Creates a new instance of DureeController
//     */
//    @EJB
//    IDroitAcces serviceAccess;
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    OrclassExonerationTaxeDao exonerationTaxeDao;
//    OrclassExonerationTaxe exonerationTaxe;
//    //OrclassMajorationDuree majorationDuree;
//    @EJB
//    OrclassExonerationDao exonerationDao;
//    OrclassExoneration exoneration;
//    OrclassExoneration exonerationSelect;
//    @EJB
//    OrclassTypeTaxeDao typeTaxeDao;
//    OrclassTypeTaxe typeTaxe;
//    OrclassTypeTaxe typeTaxeSelect;
//    @EJB
//    IExcell serviceExcell;
//    @EJB
//    IExoneration serviceExoneration;
//    @EJB
//    OrclassRefTimbreGradueDao refTimbreGradueDao;
//    OrclassRefTimbreGradue refTimbreGradue;
//
//    @EJB
//    private ISecurite securiteService;
//    private List<OrclassTypeTaxe> listTypeTaxe = new ArrayList<>();
//    private List<OrclassRefTimbreGradue> listeRefTimbreGradue = new ArrayList<>();
//    private List<OrclassTypeTaxe> selecteTypeTaxes = new ArrayList<>();
//    private List<OrclassTypeTaxe> filterTypeTaxe = new ArrayList<>();
//    private List<OrclassExoneration> filterExoneration = new ArrayList<>();
//    private List<OrclassExoneration> listExoneration = new ArrayList<>();
//    private List<OrclassExonerationTaxe> listExonerationTaxe = new ArrayList<>();
//    private List<OrclassExonerationTaxe> filterExonerationTaxe = new ArrayList<>();
//
//    private static final Logger logger = LoggerFactory.getLogger(ExonerationTaxeController.class);
//    String summary = "";
//    String msgdetail = "";
//    private OrclassEntreprises entreprise;
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//    Boolean variable = Boolean.FALSE;
//    private String currentFolder = "/photos";
//    //private String pahtRubrique = currentFolder + "/duree.xls";
//
//    public ExonerationTaxeController() {
//        exoneration = new OrclassExoneration();
//        exonerationTaxe = new OrclassExonerationTaxe();
//        typeTaxe=new OrclassTypeTaxe();
//        refTimbreGradue=new OrclassRefTimbreGradue();
//        exonerationSelect=new OrclassExoneration();
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
//        menu = menusDao.findEntityHavingValue("code", ModuleMenu.mdsp_exoneration_taxe);
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//
//        listExoneration = (List<OrclassExoneration>) exonerationDao.findAll();
//        //listDuree.addAll(dureeDao.listAllClassesShowAllCompagnie());
////        listTypeTaxe = (List<OrclassTypeTaxe>) typeTaxeDao.findAll();
//        listExonerationTaxe = (List<OrclassExonerationTaxe>) exonerationTaxeDao.listExonerationTaxe(entreprise);
//        //lisCategories.addAll(categoriesDao.listAllCategoriesShowAllCompagnie());
//        this.updateTableExonerationTaxe();
//    }
//
//    public void chargeListeTypeTaxe() {
//        if (exoneration!= null && exoneration.getIdExoneration() != null) {
//            listTypeTaxe = typeTaxeDao.listeTypeTaxeNotExisteForExonerationTaxe(exoneration, entreprise);
//            listeRefTimbreGradue = refTimbreGradueDao.listeTimbreGradueNotExisteForExonerationTaxe(exoneration, entreprise);
//            this.updateTableExonerationTaxe();
//        }
//
//        //lisCategories.addAll(categoriesDao.listAllCategoriesShowAllCompagnie());
//    }
//
////    public void chargeListeTypeTaxeNotIn() {
////
////        listTypeTaxe = typeTaxeDao.listeTypeTaxeNotExisteForExonerationTaxe(exonerationSelect);
////
////    }
//    public void chargeExonerationTaxeByExoneration() {
//
//        if (exonerationTaxe != null && exonerationTaxe.getIdExoneration() != null && exonerationTaxe.getIdExoneration().getIdExoneration() != null) {
////            this.chargeListeTypeTaxe();
//
//            if (listExonerationTaxe.isEmpty()) {
//                exonerationTaxe = new OrclassExonerationTaxe();
//                exonerationTaxe.setIdExoneration(exonerationSelect);
//                listExonerationTaxe.add(exonerationTaxe);
//            } else {
//                exonerationTaxe = new OrclassExonerationTaxe();
//                exonerationTaxe.setIdExoneration(exonerationSelect);
//                listExonerationTaxe.add(exonerationTaxe);
//            }
////            listTypeTaxe.removeAll(exonerationTaxeDao.listExonerationTaxe(exonerationSelect, entreprise));
//
//        }
//
//        this.updateTableExonerationTaxe();
//        this.reverseListe();
//
//    }
//
//    public void chargeExonerationTaxeByTypeTaxe() {
//        if (typeTaxe != null && typeTaxe.getIdTypeTaxe() != null) {
//            exonerationTaxe = listExonerationTaxe.get(0);
//            exonerationTaxe.setIdTypeTaxe(typeTaxe);
//            exonerationTaxe.setIdExoneration(exonerationSelect);
//            listExonerationTaxe.set(0, exonerationTaxe);
//            exonerationTaxe = new OrclassExonerationTaxe();
//        }
//    }
//
//    public void reset() {
//
//        this.init();
//
////        PrimeFaces.current().ajax().update("form1,form2");
//        listExonerationTaxe = exonerationTaxeDao.listExonerationTaxe(entreprise);
//        this.updateTableExonerationTaxe();
//        PrimeFaces.current().ajax().update(":form1");
//
//    }
//
//    public void init() {
//        exoneration = new OrclassExoneration();
//        exonerationSelect = new OrclassExoneration();
//        exonerationTaxe = new OrclassExonerationTaxe();
//        typeTaxe = new OrclassTypeTaxe();
//
//    }
//      public void reload() throws IOException {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
//    }
//
//
//    public String addExonerationRef() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "exoneration", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        OrclassExoneration code;
//        try {
//            if (exonerationSelect != null && exonerationSelect.getCode() == null) {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "THE VALUE CODE IS NULL", "PLEASE WRITE THE VALUE CODE"));
//                return null;
//            }
//            if (exonerationSelect != null && exonerationSelect.getLibelle() == null) {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "THE VALUE LIBELLE IS NULL", "PLEASE WRITE THE VALUE LIBELLE"));
//                return null;
//            }
//            if (exonerationDao.findEntityHavingValue("code", exonerationSelect.getCode()) == null) {
//
//                exonerationDao.create(exonerationSelect);
//                if (exonerationSelect != null && exonerationSelect.getIdExoneration() != null) {
//                    
//                    this.setExoneration(exonerationSelect);
//                   listExoneration = (List<OrclassExoneration>) exonerationDao.findAll();
//                    
//                }
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//            } else {
//
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "THE VALUE EXIST"));
//                return null;
//
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "exoneration", exception.Error.FATAL_ERROR + "", new Object[]{"exoneration"});
//            logger.error("Erreur Survenu", th);
//        }
//
////        this.reset();
//        return null;
//    }
//
//    public String addExoneration() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "exoneration", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        OrclassExoneration code;
//        try {
//            if (exonerationTaxe == null || exonerationTaxe.getIdExonerationTaxe() == null) {
//
//                exonerationTaxe = serviceExoneration.addExonerationTaxe(exoneration, typeTaxe, refTimbreGradue, entreprise);
//                listExonerationTaxe = (List<OrclassExonerationTaxe>) exonerationTaxeDao.listExonerationTaxe(entreprise);
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//            } else {
//
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "VALUE EXISTE"));
//                return null;
//
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "exoneration", exception.Error.FATAL_ERROR + "", new Object[]{"exoneration"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        this.reset();
//
//        return null;
//    }
//
//    public void chargeDialog(OrclassExoneration item) {
//        if (exoneration != null && exoneration.getIdExoneration() != null) {
//            PrimeFaces.current().executeScript("PF('manageExonerationDialog').show();");
//            if (item != null && item.getIdExoneration() != null) {
//                this.setExoneration(item);
//            }
//
//        }
//
//    }
//
//    public String updateExoneration() {
//        String success = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        //Locale myLoc =new Locale("fr");
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "exoneration", null, myLoc)};
//        String[] detail = {entete[0], "Parametres"};
//
//        try {
//
//            if (exonerationTaxe != null && exonerationTaxe.getIdExonerationTaxe() != null) {
//                if (Objects.equals(serviceExoneration.updateExoneration(exonerationTaxe), Boolean.TRUE)) {
//                    summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + exception.Success.OPERATION_SUCCESS.toString(), entete, myLoc);
//                    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
//                    listExonerationTaxe = (List<OrclassExonerationTaxe>) exonerationTaxeDao.findAll();
//                } else {
//                    summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + exception.Error.UPDATE_ERROR.toString(), entete, myLoc);
//                    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, null));
//                    listExonerationTaxe = (List<OrclassExonerationTaxe>) exonerationTaxeDao.findAll();
//                }
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
//    public String deleteExoneration() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "exoneration", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (exonerationTaxe != null && exonerationTaxe.getIdExonerationTaxe() != null) {
//
//                exonerationTaxeDao.delete(exonerationTaxe);
//                listExonerationTaxe = (List<OrclassExonerationTaxe>) exonerationTaxeDao.listExonerationTaxe(entreprise);
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "exoneration", exception.Error.FATAL_ERROR + "", new Object[]{"exoneration"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        this.reset();
//        return null;
//    }
//
//    public void showDetail(OrclassExoneration item) {
//        this.setExoneration(item);
//        PrimeFaces.current().ajax().update(":form11:grid1");
//    }
//
//    public void showExonerationTaxe(OrclassExonerationTaxe item) {
////        if (rubriqueCategorie.getIdRubrique()==null || rubriqueCategorie.getIdRubrique().getId()==null) {
//        this.setExonerationTaxe(item);
////        }
//
//    }
//
//    public void onItemSelectExo(SelectEvent<String> event) {
//        OrclassExoneration ex = null;
//        ex = exonerationDao.findEntityHavingValue("code", event.getObject());
//        if (ex == null) {
//            ex = exonerationDao.findEntityHavingValue("libelle", event.getObject());
//        }
//        if (ex != null && ex.getIdExoneration() != null) {
//            this.exonerationTaxe.setIdExoneration(ex);
//
//            PrimeFaces.current().ajax().update(":form1");
//        }
//    }
//
//    public void onItemSelectTypeTax(SelectEvent<String> event) {
//        OrclassTypeTaxe t = null;
//        t = typeTaxeDao.findEntityHavingValue("code", event.getObject());
//        if (t == null) {
//            t = typeTaxeDao.findEntityHavingValue("libelle", event.getObject());
//        }
//        if (t != null && t.getIdTypeTaxe() != null) {
//            this.exonerationTaxe.setIdTypeTaxe(t);
//
//            PrimeFaces.current().ajax().update(":form1");
//        }
//    }
//
//    public List<String> completeTextExo(String query) {
//        List<String> results = new ArrayList<>();
//        if (query != null && !query.trim().equals("")) {
//            results = exonerationDao.getExonerationWithFilters(query);
//        }
//
//        return results;
//    }
//
//    public List<String> completeTextExoLibelle(String query) {
//        List<String> results = new ArrayList<>();
//        if (query != null && !query.trim().equals("")) {
//            results = exonerationDao.getExonerationLibelleWithFilters(query);
//        }
//
//        return results;
//    }
//
//    public List<String> completeTextTypeTax(String query) {
//        List<String> results = new ArrayList<>();
//        if (query != null && !query.trim().equals("")) {
//            results = typeTaxeDao.getTypeTaxeWithFilters(query);
//        }
//
//        return results;
//    }
//
//    public List<String> completeTextTypeTaxLibelle(String query) {
//        List<String> results = new ArrayList<>();
//        if (query != null && !query.trim().equals("")) {
//            results = typeTaxeDao.getTypeTaxeLibelleWithFilters(query);
//        }
//
//        return results;
//    }
//
////    public void chargeDialogGrid2(OrclassDuree item) {
////        if (duree == null | duree.getId() == null) {
////            this.setDuree(item);
////        }
//////        PrimeFaces.current().ajax().update(":form11:grid1");
//////        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form11:dgl");
//////        table.findComponent(":form11:add,:form11:update");
////
//////        PrimeFaces.current().executeScript("PF('manageDureeDialog').clearFilters();");
//////        PrimeFaces.current().executeScript("PF('manageDureeDialog').show();");
////
////    }
//    public String deleteExonerationTaxe() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "exoneration_taxe", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (exonerationTaxe != null && exonerationTaxe.getIdExonerationTaxe() != null) {
//
//                exonerationTaxeDao.delete(exonerationTaxe);
//                listExonerationTaxe = (List<OrclassExonerationTaxe>) exonerationTaxeDao.findAll();
//
//                this.updateTableExonerationTaxe();
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.delete.succes", detail, myLoc);
//                ctx.addMessage("msg2", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//            } else {
//                ctx.addMessage("msg2", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg2", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "majoration", exception.Error.FATAL_ERROR + "", new Object[]{"majoration"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        return null;
//    }
//
//    public String updateExonerationTaxe() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "exoneration_taxe", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (exonerationTaxe != null && exonerationTaxe.getIdExonerationTaxe() != null) {
//
//                exonerationTaxeDao.update(exonerationTaxe);
//                listExonerationTaxe = (List<OrclassExonerationTaxe>) exonerationTaxeDao.find(exonerationSelect.getIdExoneration());
//                exonerationTaxe = new OrclassExonerationTaxe();
//                exonerationTaxe.setIdExoneration(exonerationSelect);
//                listExonerationTaxe.add(exonerationTaxe);
//                this.updateTableExonerationTaxe();
//
//                //this.reverseListe();
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
//                ctx.addMessage("msg2", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//            } else {
//                ctx.addMessage("msg2", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg2", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "majoration", exception.Error.FATAL_ERROR + "", new Object[]{"exoneration_taxe"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        return null;
//    }
//
//    public void reverseListe() {
//
//        List<OrclassExonerationTaxe> result = new ArrayList<>();
//        for (int i = listExonerationTaxe.size() - 1; i >= 0; i--) {
//            result.add(listExonerationTaxe.get(i));
//        }
//
//        this.setListExonerationTaxe(result);
//
//    }
//
//    public void removeExonerationTaxeListeByListe() {
//        listExonerationTaxe.remove(exonerationTaxe);
//        this.updateTableExonerationTaxe();
//        PrimeFaces.current().ajax().update(":tabprincipal:form2");
//
//    }
//
//    public void updateTableExonerationTaxe() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:idExonerationTaxeTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('exonerationTaxeTable').clearFilters();");
//    }
//
//    public void showDetailsExonerationTaxe(OrclassExonerationTaxe item) {
////        if (rubriquePrestation == null || rubriquePrestation.getId() == null) {
//        this.setExonerationTaxe(item);
////        }
//
//    }
//
//    public Boolean getVariable() {
//        return variable;
//    }
//
//    public void setVariable(Boolean variable) {
//        this.variable = variable;
//    }
//
//    public OrclassExonerationTaxeDao getExonerationTaxeDao() {
//        return exonerationTaxeDao;
//    }
//
//    public void setExonerationTaxeDao(OrclassExonerationTaxeDao exonerationTaxeDao) {
//        this.exonerationTaxeDao = exonerationTaxeDao;
//    }
//
//    public OrclassExonerationTaxe getExonerationTaxe() {
//        return exonerationTaxe;
//    }
//
//    public void setExonerationTaxe(OrclassExonerationTaxe exonerationTaxe) {
//        this.exonerationTaxe = exonerationTaxe;
//    }
//
//    public OrclassExonerationDao getExonerationDao() {
//        return exonerationDao;
//    }
//
//    public void setExonerationDao(OrclassExonerationDao exonerationDao) {
//        this.exonerationDao = exonerationDao;
//    }
//
//    public OrclassExoneration getExoneration() {
//        return exoneration;
//    }
//
//    public void setExoneration(OrclassExoneration exoneration) {
//        this.exoneration = exoneration;
//    }
//
//    public OrclassExoneration getExonerationSelect() {
//        return exonerationSelect;
//    }
//
//    public void setExonerationSelect(OrclassExoneration exonerationSelect) {
//        this.exonerationSelect = exonerationSelect;
//    }
//
//    public OrclassTypeTaxe getTypeTaxeSelect() {
//        return typeTaxeSelect;
//    }
//
//    public void setTypeTaxeSelect(OrclassTypeTaxe typeTaxeSelect) {
//        this.typeTaxeSelect = typeTaxeSelect;
//    }
//
//    public OrclassTypeTaxeDao getTypeTaxeDao() {
//        return typeTaxeDao;
//    }
//
//    public void setTypeTaxeDao(OrclassTypeTaxeDao typeTaxeDao) {
//        this.typeTaxeDao = typeTaxeDao;
//    }
//
//    public OrclassTypeTaxe getTypeTaxe() {
//        return typeTaxe;
//    }
//
//    public void setTypeTaxe(OrclassTypeTaxe typeTaxe) {
//        this.typeTaxe = typeTaxe;
//    }
//
//    public List<OrclassTypeTaxe> getListTypeTaxe() {
//        return listTypeTaxe;
//    }
//
//    public void setListTypeTaxe(List<OrclassTypeTaxe> listTypeTaxe) {
//        this.listTypeTaxe = listTypeTaxe;
//    }
//
//    public List<OrclassTypeTaxe> getFilterTypeTaxe() {
//        return filterTypeTaxe;
//    }
//
//    public void setFilterTypeTaxe(List<OrclassTypeTaxe> filterTypeTaxe) {
//        this.filterTypeTaxe = filterTypeTaxe;
//    }
//
//    public List<OrclassExoneration> getFilterExoneration() {
//        return filterExoneration;
//    }
//
//    public void setFilterExoneration(List<OrclassExoneration> filterExoneration) {
//        this.filterExoneration = filterExoneration;
//    }
//
//    public List<OrclassExoneration> getListExoneration() {
//        return listExoneration;
//    }
//
//    public void setListExoneration(List<OrclassExoneration> listExoneration) {
//        this.listExoneration = listExoneration;
//    }
//
//    public List<OrclassExonerationTaxe> getListExonerationTaxe() {
//        return listExonerationTaxe;
//    }
//
//    public void setListExonerationTaxe(List<OrclassExonerationTaxe> listExonerationTaxe) {
//        this.listExonerationTaxe = listExonerationTaxe;
//    }
//
//    public List<OrclassExonerationTaxe> getFilterExonerationTaxe() {
//        return filterExonerationTaxe;
//    }
//
//    public void setFilterExonerationTaxe(List<OrclassExonerationTaxe> filterExonerationTaxe) {
//        this.filterExonerationTaxe = filterExonerationTaxe;
//    }
//
//    public List<OrclassTypeTaxe> getSelecteTypeTaxes() {
//        return selecteTypeTaxes;
//    }
//
//    public void setSelecteTypeTaxes(List<OrclassTypeTaxe> selecteTypeTaxes) {
//        this.selecteTypeTaxes = selecteTypeTaxes;
//    }
//
//    public List<OrclassRefTimbreGradue> getListeRefTimbreGradue() {
//        return listeRefTimbreGradue;
//    }
//
//    public void setListeRefTimbreGradue(List<OrclassRefTimbreGradue> listeRefTimbreGradue) {
//        this.listeRefTimbreGradue = listeRefTimbreGradue;
//    }
//
//    public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.exoneration_taxe.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);
//            if (action == null) {
//                action = serviceAccess.getActionByCodeByFonctionnalit(Actions.ajouter.name(), fon);
//            }
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.exoneration_taxe.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.exoneration_taxe.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public OrclassRefTimbreGradue getRefTimbreGradue() {
//        return refTimbreGradue;
//    }
//
//    public void setRefTimbreGradue(OrclassRefTimbreGradue refTimbreGradue) {
//        this.refTimbreGradue = refTimbreGradue;
//    }
//    
//
//}
