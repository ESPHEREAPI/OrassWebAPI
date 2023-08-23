///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import Excell.IExcell;
//import dao.OrclassCategoriesDao;
//import dao.OrclassDureeDao;
//import dao.OrclassMajorationDureeDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import droitAcces.IDroitAcces;
//import enums.LibelleCategorie;
//import enums.ModeProrata;
//import enums.TypeContrat;
//import enums.TypeDuree;
//import enums.UniteDuree;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import javax.inject.Named;
//import java.io.Serializable;
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Date;
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
//import javax.validation.ConstraintViolationException;
//import modele.OrclassCategories;
//import modele.OrclassDuree;
//import modele.OrclassEntreprises;
//import modele.OrclassMajorationDuree;
//import modele.OrclassMenus;
//import modele.OrclassModules;
//import modele.OrclassRubrique;
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
// * @author hp
// */
//@Named(value = "dureeController")
//@ViewScoped
//public class DureeController implements Serializable {
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
//    OrclassMajorationDureeDao majorationDureeDao;
//    OrclassMajorationDuree majorationDuree;
//    @EJB
//    OrclassDureeDao dureeDao;
//    OrclassDuree duree;
//    OrclassDuree dureeSelect;
//    @EJB
//    OrclassCategoriesDao categoriesDao;
//    OrclassCategories categories;
//    @EJB
//    IExcell serviceExcell;
//
//    @EJB
//    private ISecurite securiteService;
//    private List<OrclassCategories> lisCategories = new ArrayList<>();
//    private List<OrclassCategories> filterCategories = new ArrayList<>();
//    private List<OrclassDuree> filterDuree = new ArrayList<>();
//    private List<OrclassDuree> listDuree = new ArrayList<>();
//    private List<OrclassMajorationDuree> listMajoration = new ArrayList<>();
//    private List<OrclassMajorationDuree> filterMajoration = new ArrayList<>();
//
//    private static final Logger logger = LoggerFactory.getLogger(DureeController.class);
//    String summary = "";
//    String msgdetail = "";
//    private OrclassEntreprises entreprise;
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//    Boolean variable = Boolean.FALSE;
//    private String currentFolder = "/photos";
//    private String pahtRubrique = currentFolder + "/duree.xls";
//
//    public DureeController() {
//        duree = new OrclassDuree();
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
//        menu = menusDao.findEntityHavingValue("code", "duree");
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        this.creatDureeByExcell();
//        listDuree = dureeDao.listAllClassesByCompagnie(entreprise);
//        listDuree.addAll(dureeDao.listAllClassesShowAllCompagnie());
//        lisCategories = categoriesDao.listAllCategorieByCompagnie(entreprise);
//        lisCategories.addAll(categoriesDao.listAllCategoriesShowAllCompagnie());
//        updateDataTableDuree();
//
//    }
//
//    public void chargeListeCategories() {
//        lisCategories = categoriesDao.getAllCategorieByEntreprise(entreprise);
//        lisCategories.addAll(categoriesDao.listAllCategoriesShowAllCompagnie());
//    }
//
//    public void chargeMajorationByDuree() {
//
//        if (dureeSelect != null && dureeSelect.getId() != null) {
//            this.chargeListeCategories();
//            listMajoration = majorationDureeDao.listMajoration(entreprise, dureeSelect);
//            if (listMajoration.isEmpty()) {
//                majorationDuree = new OrclassMajorationDuree();
//                System.err.println("coefficient:" + majorationDuree.getCoefficient().intValue());
//                majorationDuree.setIdDuree(dureeSelect);
//                listMajoration.add(majorationDuree);
//            } else {
//                majorationDuree = new OrclassMajorationDuree();
//                majorationDuree.setIdDuree(dureeSelect);
//                System.err.println("coefficient:" + majorationDuree.getCoefficient().intValue());
//                listMajoration.add(majorationDuree);
//            }
//            lisCategories.removeAll(majorationDureeDao.listMajorationWithCategories(entreprise, dureeSelect));
//
//        }
//        this.updateTableMajoration();
//        this.updateDataTableListeCategorie();
//        this.reverseListe();
//
//    }
//
//    public void creatDureeByExcell() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        List<OrclassDuree> listDuree = new ArrayList<>();
//        OrclassDuree d;
//        OrclassEntreprises en = null;
//        try {
//            String path = extContext.getRealPath("") + this.pahtRubrique;
//            File file = new File(path);
//            InputStream targetStream = new FileInputStream(file);
//            listDuree = serviceExcell.recuperListeDureeByExcell(targetStream, path);
//            if (listDuree == null) {
//                System.err.println("Une erruer est survenue dans l extraction des donnes sur le fichier Excell");
//            }
//            for (OrclassDuree r : listDuree) {
//                d = dureeDao.findEntityHavingValue("code", r.getCode());
//                if (d == null) {
//
//                    d = r;
//                    d.setShowAllCompagnies(Boolean.TRUE);
//                    dureeDao.create(d);
//                }
//
//            }
//        } catch (Exception e) {
//            System.err.println("Une erruer est survenue dans l insertion des donneés");
//
//        }
//    }
//
//    public void chargeMajorationByCategories() {
//        if (categories != null && categories.getIdCategorie() != null) {
//            majorationDuree = listMajoration.get(0);
//            majorationDuree.setIdCategories(categories);
//            majorationDuree.setIdDuree(dureeSelect);
//            majorationDuree.setEffet(new Date());
//            if (majorationDuree.getCoefficient() == null) {
//                majorationDuree.setCoefficient(BigInteger.ONE);
//
//            }
//            if (majorationDuree.getValeurAdditif() == null) {
//                majorationDuree.setValeurAdditif(BigInteger.ZERO);
//            }
//            listMajoration.set(0, majorationDuree);
//            majorationDuree = new OrclassMajorationDuree();
//        }
//    }
//
//    public void updateDataTableDuree() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("tabprincipal:form1:iddureeTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('dureeTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public void updateDataTableListeCategorie() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form11:idtableCategorie");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('tableCategorie').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public void testeValeurTypeDuree() {
//        if (duree.getTypeDuree().equals(TypeDuree.variable)) {
//            this.setVariable(Boolean.TRUE);
//        } else {
//            this.setVariable(Boolean.FALSE);
//        }
//    }
//
//    public void reset() {
//
//        this.init();
//
////        PrimeFaces.current().ajax().update("form1,form2");
//        this.updateDataTableDuree();
//        PrimeFaces.current().ajax().update("tabprincipal:form1");
//
//    }
//
//    public void init() {
//        duree = new OrclassDuree();
//        dureeSelect = new OrclassDuree();
//
//    }
//
//    public List<SelectItem> getTypeDuree() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (TypeDuree td : TypeDuree.values()) {
//            items.add(new SelectItem(td, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, td.name(), null, myLoc)));
//        }
//
//        return items;
//    }
//
//    public List<SelectItem> getTypeContrat() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (TypeContrat td : TypeContrat.values()) {
//            items.add(new SelectItem(td, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, td.name(), null, myLoc)));
//        }
//
//        return items;
//    }
//
//    public List<SelectItem> getModeProrata() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (ModeProrata mp : ModeProrata.values()) {
//            items.add(new SelectItem(mp, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, mp.name(), null, myLoc)));
//        }
//
//        return items;
//    }
//
//    public List<SelectItem> getUniteDuree() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (UniteDuree ud : UniteDuree.values()) {
//            items.add(new SelectItem(ud, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, ud.name(), null, myLoc)));
//        }
//
//        return items;
//    }
//
//    public String addDuree() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "duree", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        OrclassDuree code;
//        try {
//            if (dureeDao.findEntityHavingValue("code", duree.getCode()) == null && (duree.getCode() != null && !"".equals(duree.getCode()))) {
//                duree.setIdEntreprise(entreprise);
//                duree.setDateCreation(new Date());
//                dureeDao.create(duree);
//                listDuree.add(duree);
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                PrimeFaces.current().executeScript("PF('manageDureeDialog').show();");
//            } else {
//                code = dureeDao.findEntityHavingValue("code", duree.getCode());
//                if (code != null && code.getId() != null) {
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "VALUE IS EXIST"));
//                    return null;
//                }
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "VALUE IS NULL"));
//                return null;
//
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "duree", exception.Error.FATAL_ERROR + "", new Object[]{"duree"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        this.reset();
//
//        return null;
//    }
//
//    public void chargeDialog(OrclassDuree item) {
//        if (duree != null && duree.getId() != null) {
//            PrimeFaces.current().executeScript("PF('manageDureeDialog').show();");
//            if (item != null && item.getId() != null) {
//                this.setDuree(item);
//            }
//
//        }
//
//    }
//
//    public String updateDuree() {
//        String success = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        //Locale myLoc =new Locale("fr");
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "duree", null, myLoc)};
//        String[] detail = {entete[0], "Parametres"};
//
//        try {
//
//            if (duree != null && duree.getId() != null) {
//                listDuree.remove(duree);
//                duree.setDateModification(new Date());
//                dureeDao.update(duree);
//                listDuree.add(duree);
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + exception.Success.OPERATION_SUCCESS.toString(), entete, myLoc);
//
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
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
//    public String deleteDuree() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "duree", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (duree != null && duree.getId() != null) {
//
//                dureeDao.delete(duree);
//                listDuree.remove(duree);
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "duree", exception.Error.FATAL_ERROR + "", new Object[]{"duree"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        this.reset();
//        return null;
//    }
//
//    public void showDetail(OrclassDuree item) {
//        this.setDuree(item);
//        PrimeFaces.current().ajax().update(":form11:grid1");
//    }
//
//    public void showMajoration(OrclassMajorationDuree item) {
////        if (rubriqueCategorie.getIdRubrique()==null || rubriqueCategorie.getIdRubrique().getId()==null) {
//        this.setMajorationDuree(item);
////        }
//
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
//    public String deleteMajoration() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "majoration", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (majorationDuree != null && majorationDuree.getId() != null) {
//
//                majorationDureeDao.delete(majorationDuree);
//                listMajoration = majorationDureeDao.listMajoration(entreprise, dureeSelect);
//                majorationDuree = new OrclassMajorationDuree();
//                majorationDuree.setIdDuree(dureeSelect);
//                listMajoration.add(majorationDuree);
//                this.updateTableMajoration();
//
//                this.reverseListe();
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
//    public String updateMajoration() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "majoration", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (majorationDuree != null && majorationDuree.getId() != null) {
//
//                majorationDuree.setDateModification(new Date());
//
//                majorationDureeDao.update(majorationDuree);
//                listMajoration = majorationDureeDao.listMajoration(entreprise, dureeSelect);
//                majorationDuree = new OrclassMajorationDuree();
//                majorationDuree.setIdDuree(dureeSelect);
//                listMajoration.add(majorationDuree);
//                this.updateTableMajoration();
//
//                this.reverseListe();
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "majoration", exception.Error.FATAL_ERROR + "", new Object[]{"majoration"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        return null;
//    }
//
//    public String addMajoration() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "majoration", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if ((majorationDuree == null || majorationDuree.getId() == null) && (dureeSelect != null && dureeSelect.getId() != null)) {
//                if (majorationDuree.getFin() == null) {
//                    ctx.addMessage("msg2", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE  SELECT VALUE FOR END DATE ", "PLEASE GIVE A VALUE "));
//                    return null;
//                }
//                if (majorationDuree.getCoefficient() == null) {
//                    ctx.addMessage("msg2", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE  SELECT VALUE FOR COEFFICIENT", "PLEASE GIVE A VALUE "));
//                    return null;
//                }
//                if (majorationDuree.getEffet() == null) {
//                    ctx.addMessage("msg2", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE  SELECT VALUE FOR EFFECTIVE DATE ", "PLEASE GIVE A VALUE "));
//                    return null;
//                }
//
//                majorationDuree.setIdEntreprise(entreprise);
//                majorationDureeDao.create(majorationDuree);
//
//                listMajoration = majorationDureeDao.listMajoration(entreprise, dureeSelect);
//                majorationDuree = new OrclassMajorationDuree();
//                majorationDuree.setIdDuree(dureeSelect);
//                listMajoration.add(majorationDuree);
//                this.updateTableMajoration();
//
//                this.reverseListe();
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg2", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//            } else {
//                ctx.addMessage("msg2", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "FATAL ERROR"));
//
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg2", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "majoration", exception.Error.FATAL_ERROR + "", new Object[]{"majoration"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        return null;
//    }
//
//    public void reverseListe() {
//
//        List<OrclassMajorationDuree> result = new ArrayList<>();
//        for (int i = listMajoration.size() - 1; i >= 0; i--) {
//            result.add(listMajoration.get(i));
//        }
//
//        this.setListMajoration(result);
//
//    }
//
//    public void removeMajorationListeByListe() {
//        listMajoration.remove(majorationDuree);
//        this.updateTableMajoration();
//        PrimeFaces.current().ajax().update(":tabprincipal:form2");
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
//    public void updateTableMajoration() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":tabprincipal:form2:idmajorationTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('majorationTable').clearFilters();");
//    }
//
//    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
//        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
////        if (LangUtils.isBlank(filterText)) {
////            return true;
////        }
////        int filterInt = getInteger(filterText);
//
//        OrclassMajorationDuree m = (OrclassMajorationDuree) value;
//        if (m.getId() == null) {
//            return true;
//        }
//        return m.getIdCategories().getCode().toLowerCase().contains(filterText)
//                || this.valueObjectByLibelleAutres(m.getIdCategories()).toLowerCase().contains(filterText) //////////            
//                ;
//    }
//
//    public OrclassMajorationDuree getMajorationDuree() {
//        return majorationDuree;
//    }
//
//    public void setMajorationDuree(OrclassMajorationDuree majorationDuree) {
//        this.majorationDuree = majorationDuree;
//    }
//
//    public OrclassDuree getDuree() {
//        return duree;
//    }
//
//    public void setDuree(OrclassDuree duree) {
//        this.duree = duree;
//    }
//
//    public OrclassDuree getDureeSelect() {
//        return dureeSelect;
//    }
//
//    public void setDureeSelect(OrclassDuree dureeSelect) {
//        this.dureeSelect = dureeSelect;
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
//    public List<OrclassDuree> getFilterDuree() {
//        return filterDuree;
//    }
//
//    public void setFilterDuree(List<OrclassDuree> filterDuree) {
//        this.filterDuree = filterDuree;
//    }
//
//    public List<OrclassDuree> getListDuree() {
//        return listDuree;
//    }
//
//    public void setListDuree(List<OrclassDuree> listDuree) {
//        this.listDuree = listDuree;
//    }
//
//    public List<OrclassMajorationDuree> getListMajoration() {
//        return listMajoration;
//    }
//
//    public void setListMajoration(List<OrclassMajorationDuree> listMajoration) {
//        this.listMajoration = listMajoration;
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
//    public OrclassCategories getCategories() {
//        return categories;
//    }
//
//    public void setCategories(OrclassCategories categories) {
//        this.categories = categories;
//    }
//
//    public List<OrclassMajorationDuree> getFilterMajoration() {
//        return filterMajoration;
//    }
//
//    public void setFilterMajoration(List<OrclassMajorationDuree> filterMajoration) {
//        this.filterMajoration = filterMajoration;
//    }
//
//}
