///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import static Entreprise.EntrepriseServices.logger;
//import Excell.IExcell;
//import dao.OrclassFamillePrestationDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassPrestationDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleParametrage;
//import exception.GlobalException;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.io.Serializable;
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
//import javax.inject.Named;
//import javax.faces.view.ViewScoped;
//import javax.validation.ConstraintViolationException;
//import modele.OrclassActions;
//import modele.OrclassEntreprises;
//import modele.OrclassFamillePrestation;
//import modele.OrclassFonctionnalites;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassPrestation;
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
//@Named(value = "prestationController")
//@ViewScoped
//public class PrestationController implements Serializable {
//
//    @EJB
//    OrclassFamillePrestationDao famillePrestationDao;
//    OrclassFamillePrestation famillePrestation;
//    OrclassFamillePrestation famillePrestationCreate;
//
//    @EJB
//    OrclassPrestationDao prestationDao;
//    OrclassPrestation prestation;
//    private OrclassEntreprises entreprise;
//    @EJB
//    IExcell serviceExcell;
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    IDroitAcces serviceAccess;
//    String summary = "";
//    String msgdetail = "";
//    private List<OrclassFamillePrestation> listeFamillePrestation = new ArrayList<>();
//    private List<OrclassPrestation> listePrestation = new ArrayList<>();
//    private List<OrclassPrestation> filterPrestation = new ArrayList<>();
//    private List<OrclassFamillePrestation> filterFamillePrestation = new ArrayList<>();
//    private String currentFolder = "/photos";
//    private String pahtPrestation = currentFolder + "/prestation.xls";
//    private String pahtFamille = currentFolder + "/famillePrestation.xls";
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//
//    /**
//     * Creates a new instance of PrestationController
//     */
//    public PrestationController() {
//        prestation = new OrclassPrestation();
//        famillePrestation = new OrclassFamillePrestation();
//        famillePrestationCreate = new OrclassFamillePrestation();
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
//        menu = menusDao.findEntityHavingValue("code", "prestation");
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        try {
//            this.creatFamillePrestationByExcell();
//            this.creatPrestationByExcell();
//        } catch (Exception e) {
//
//        }
//        chargeFamillePrestation();
//        chargePrestation();
//
//        this.updateTablePrestation();
//
//    }
//
//    public void chargeFamillePrestation() {
//
//        listeFamillePrestation = famillePrestationDao.listeFamillePrestationByCompagnie(entreprise);
//        listeFamillePrestation.addAll(famillePrestationDao.listeFamillePrestationForAllCompagnie());
//    }
//
//    public void chargePrestation() {
//        listePrestation = prestationDao.listePrestationByCompagnie(entreprise);
//        listePrestation.addAll(prestationDao.listePrestationForAllCompagnie());
//    }
//
//    public void creatFamillePrestationByExcell() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        List<OrclassFamillePrestation> listFamillePrestation = new ArrayList<>();
//        OrclassFamillePrestation f;
//        OrclassEntreprises en = null;
//        try {
//            String path = extContext.getRealPath("") + this.pahtFamille;
//            File file = new File(path);
//            InputStream targetStream = new FileInputStream(file);
//            listFamillePrestation = serviceExcell.recuperListeFamillePrestationByExcell(targetStream, path);
//            if (listFamillePrestation == null) {
//                System.err.println("Une erruer est survenue dans l extraction des donnes sur le fichier Excell");
//            }
//            for (OrclassFamillePrestation fp : listFamillePrestation) {
//                f = famillePrestationDao.findEntityHavingValue("code", fp.getCode());
//                if (f == null) {
//                    f = new OrclassFamillePrestation();
//                    f.setCode(fp.getCode());
//                    f.setLibelle(fp.getLibelle());
//                    f.setDateCreation(new Date());
//                    f.setShowAllCompagnies(Boolean.TRUE);
////                    f.setIdEntreprise(entreprise);
//                    famillePrestationDao.create(f);
//                }
//
//            }
//        } catch (Exception e) {
//            System.err.println("Une erruer est survenue dans l insertion des donneés");
//
//        }
//    }
//
//    public void creatPrestationByExcell() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        List<OrclassPrestation> listPrestation = new ArrayList<>();
//        OrclassPrestation pr;
//        OrclassEntreprises en = null;
//        OrclassFamillePrestation f;
//        try {
//            String path = extContext.getRealPath("") + this.pahtPrestation;
//            File file = new File(path);
//            InputStream targetStream = new FileInputStream(file);
//            listPrestation = serviceExcell.recuperListePrestationByExcell(targetStream, path);
//            if (listPrestation == null) {
//                System.err.println("Une erruer est survenue dans l extraction des donnes sur le fichier Excell");
//            }
//            for (OrclassPrestation p : listPrestation) {
//                f = famillePrestationDao.findEntityHavingValue("code", p.getIdFamillePrestation().getCode());
//                if (f == null) {
//                    continue;
//                }
//                pr = prestationDao.findEntityHavingValue("code", p.getCode());
//                if (pr == null) {
//                    pr = new OrclassPrestation(p.getCode(), p.getLibelle(), p.getCodeCle());
////                    pr.setIdEntreprise(entreprise);
//                    pr.setShowAllCompagnies(Boolean.TRUE);
//                    pr.setIdFamillePrestation(f);
//                    pr.setDateCreation(new Date());
//                    prestationDao.create(pr);
//                }
//
//            }
//        } catch (Exception e) {
//            System.err.println("Une erruer est survenue dans l insertion des donneés");
//
//        }
//    }
//
//    public void reset() {
//
//        this.init();
//
//        PrimeFaces.current().ajax().update("form1");
//        this.updateTablePrestation();
//
//    }
//
//    public void reset2() {
//
//        famillePrestationCreate = new OrclassFamillePrestation();
//
////        PrimeFaces.current().ajax().update("form2");
////        updateTableFamillePrestation();
////        PrimeFaces.current().executeInitScript("PF('manageFamillePrestationDialog'.show()");
//    }
//
//    public void initialisationFamillePrestation() {
//        if (famillePrestationCreate == null) {
//            famillePrestationCreate = new OrclassFamillePrestation();
//            PrimeFaces.current().executeScript("PF('manageFamillePrestationDialog').show();");
//        } else {
//            PrimeFaces.current().executeScript("PF('manageFamillePrestationDialog').show();");
//        }
//        updateTableFamillePrestation();
//        PrimeFaces.current().ajax().update(":form2");
//    }
//
//    public void init() {
//        prestation = new OrclassPrestation();
//        famillePrestation = new OrclassFamillePrestation();
//        famillePrestationCreate = new OrclassFamillePrestation();
//    }
//
//    public void updateTableCategorie() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:idmanageCategorie");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('manageCategorieTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public void updateTableFamillePrestation() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:idtableFamille");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('tableFamille').clearFilters();");
//
//    }
//
//    public void updateTablePrestation() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:idprestation");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('prestationTable').clearFilters();");
//
//    }
//
//    public String deletePrestation() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "prestation", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (prestation != null && prestation.getId() != null) {
//
//                prestationDao.delete(prestation);
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "prestation", exception.Error.FATAL_ERROR + "", new Object[]{"prestation"});
//            logger.error("Erreur Survenu", th);
//        }
//        listePrestation.remove(prestation);
//        this.reset();
//        return null;
//    }
//
//    public String addPrestation() {
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "prestation", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (prestationDao.findEntityHavingValue("code", prestation.getCode()) == null && prestation.getCode() != null) {
//                prestation.setDateCreation(new Date());
//                prestation.setIdEntreprise(entreprise);
//                prestation.setIdFamillePrestation(famillePrestation);
//                prestationDao.create(prestation);
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "CODE EXIST"));
//            }
//        } catch (GlobalException e) {
//
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCode(), e.getCode()));
////throw e;
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_FATAL, "prestation", exception.Error.FATAL_ERROR + "", new Object[]{"prestation"});
//            logger.error("Erreur Survenu", th);
//        }
//        listePrestation.add(prestationDao.findEntityHavingValue("code", prestation.getCode()));
////        this.reset();
//        prestation = new OrclassPrestation();
//        famillePrestation=new OrclassFamillePrestation();
//        this.updateTablePrestation();
//        PrimeFaces.current().executeScript("PF('PrestationDialog').show();");
//        return null;
//
//    }
//
//    public String updatePrestation() {
//        String success = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        //Locale myLoc =new Locale("fr");
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "prestation", null, myLoc)};
//        String[] detail = {entete[0], "Parametres"};
//
//        try {
//
//            if (prestation != null && prestation.getId() != null) {
//                listePrestation.remove(prestation);
//                prestation.setDateModification(new Date());
//                prestation.setIdFamillePrestation(famillePrestation);
//                prestationDao.update(prestation);
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
//        listePrestation.add(prestation);
//
//        reset();
//        return null;
//
//    }
//
//    public String addFamillePrestation() {
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "famille.prestation", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (famillePrestationDao.findEntityHavingValue("code", famillePrestationCreate.getId()) == null && famillePrestationCreate.getCode() != null) {
//                famillePrestationCreate.setDateCreation(new Date());
//                famillePrestationCreate.setIdEntreprise(entreprise);
//                famillePrestationDao.create(famillePrestationCreate);
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "CODE IS NULL"));
//            }
//        } catch (GlobalException e) {
//
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCode(), e.getCode()));
////throw e;
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_FATAL, "famille.prestation", exception.Error.FATAL_ERROR + "", new Object[]{"classe"});
//            logger.error("Erreur Survenu", th);
//        }
//        listeFamillePrestation.add(famillePrestationDao.findEntityHavingValue("code", famillePrestationCreate.getCode()));
//        this.setFamillePrestation(famillePrestationCreate);
//        this.reset2();
//        return null;
//
//    }
//
//    public String updateFamillePrestation() {
//        String success = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        //Locale myLoc =new Locale("fr");
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "famille.prestation", null, myLoc)};
//        String[] detail = {entete[0], "Parametres"};
//
//        try {
//
//            if (famillePrestationCreate != null && famillePrestationCreate.getId() != null) {
//                listeFamillePrestation.remove(famillePrestationCreate);
//                famillePrestationCreate.setDateModification(new Date());
//                famillePrestationDao.update(famillePrestationCreate);
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
//        listeFamillePrestation.add(famillePrestationCreate);
//        reset2();
//        return null;
//
//    }
//
//    public void showDetails() {
//
//        if (prestation != null || prestation.getId() != null) {
//            this.setFamillePrestation(prestation.getIdFamillePrestation());
//        }
//        PrimeFaces.current().ajax().update(":form4");
//
//    }
//
//    public OrclassFamillePrestationDao getFamillePrestationDao() {
//        return famillePrestationDao;
//    }
//
//    public void setFamillePrestationDao(OrclassFamillePrestationDao famillePrestationDao) {
//        this.famillePrestationDao = famillePrestationDao;
//    }
//
//    public OrclassFamillePrestation getFamillePrestation() {
//        return famillePrestation;
//    }
//
//    public void setFamillePrestation(OrclassFamillePrestation famillePrestation) {
//        this.famillePrestation = famillePrestation;
//    }
//
//    public OrclassFamillePrestation getFamillePrestationCreate() {
//        return famillePrestationCreate;
//    }
//
//    public void setFamillePrestationCreate(OrclassFamillePrestation famillePrestationCreate) {
//        this.famillePrestationCreate = famillePrestationCreate;
//    }
//
//    public OrclassPrestation getPrestation() {
//        if (prestation != null && prestation.getId() != null) {
//            this.setFamillePrestation(prestation.getIdFamillePrestation());
//        }
//        return prestation;
//    }
//
//    public void setPrestation(OrclassPrestation prestation) {
//        this.prestation = prestation;
//    }
//
//    public List<OrclassFamillePrestation> getListeFamillePrestation() {
//        return listeFamillePrestation;
//    }
//
//    public void setListeFamillePrestation(List<OrclassFamillePrestation> listeFamillePrestation) {
//        this.listeFamillePrestation = listeFamillePrestation;
//    }
//
//    public List<OrclassPrestation> getListePrestation() {
//        return listePrestation;
//    }
//
//    public void setListePrestation(List<OrclassPrestation> listePrestation) {
//        this.listePrestation = listePrestation;
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
//    public List<OrclassFamillePrestation> getFilterFamillePrestation() {
//        return filterFamillePrestation;
//    }
//
//    public void setFilterFamillePrestation(List<OrclassFamillePrestation> filterFamillePrestation) {
//        this.filterFamillePrestation = filterFamillePrestation;
//    }
//
//    public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.prestation_reference.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);
//
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean accessAjoute() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.prestation_reference.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.ajouter.name(), fon);
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.prestation_reference.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.prestation_reference.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.prestation_reference.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.afficher_liste.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean accessCreerFamille() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.prestation_reference.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer_famille_prestation.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean accessModifierrFamille() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.prestation_reference.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier_famille_prestation.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//}
