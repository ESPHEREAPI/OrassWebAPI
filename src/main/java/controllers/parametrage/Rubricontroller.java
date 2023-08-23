/////*
//// * To change this license header, choose License Headers in Project Properties.
//// * To change this template file, choose Tools | Templates
//// * and open the template in the editor.
//// */
////package controllers.parametrage;
////
////import static Entreprise.EntrepriseServices.logger;
////import Excell.IExcell;
////import dao.OrclassMenusDao;
////import dao.OrclassModulesDao;
////import dao.OrclassRubriqueDao;
////import droitAcces.IDroitAcces;
////import enums.Actions;
////import enums.FonctionnaliteModuleParametrage;
////import exception.GlobalException;
////import java.io.File;
////import java.io.FileInputStream;
////import java.io.InputStream;
////import java.io.Serializable;
////import java.util.ArrayList;
////import java.util.Date;
////import java.util.List;
////import java.util.Locale;
////import javax.annotation.PostConstruct;
////import javax.ejb.EJB;
////import javax.faces.application.FacesMessage;
////import javax.faces.component.UIComponent;
////import javax.faces.context.ExternalContext;
////import javax.faces.context.FacesContext;
////import javax.inject.Named;
////import javax.faces.view.ViewScoped;
////import javax.validation.ConstraintViolationException;
////import modele.OrclassActions;
////import modele.OrclassEntreprises;
////import modele.OrclassFonctionnalites;
////import modele.OrclassMenus;
////import modele.OrclassMenusAcces;
////import modele.OrclassModules;
////import modele.OrclassRubrique;
////import modele.OrclassUtilisateurs;
////import org.primefaces.PrimeFaces;
////import utils.GlobalFonctions;
////import utils.LocaleHelper;
////import utils.RecupBundle;
////
/////**
//// *
//// * @author hp
//// */
////@Named(value = "rubricontroller")
////@ViewScoped
////public class Rubricontroller implements Serializable {
////
////    /**
////     * Creates a new instance of Rubricontroller
////     */
////    @EJB
////    OrclassRubriqueDao rubriqueDao;
////    OrclassRubrique rubrique;
////    @EJB
////    IExcell serviceExcell;
////    @EJB
////    OrclassMenusDao menusDao;
////    @EJB
////    OrclassModulesDao modulesDao;
////    @EJB
////    IDroitAcces serviceAccess;
////
////    String summary = "";
////    String msgdetail = "";
////    private OrclassEntreprises entreprise;
////    private List<OrclassRubrique> listeRubrique = new ArrayList<>();
////    private List<OrclassRubrique> filterRubrique = new ArrayList<>();
////    private String currentFolder = "/photos";
////    private String pahtRubrique = currentFolder + "/rubrique.xls";
////
////    private OrclassMenus menu;
////    private OrclassModules module;
////    OrclassUtilisateurs user;
////
////    public Rubricontroller() {
////        rubrique = new OrclassRubrique();
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
////        menu = menusDao.findEntityHavingValue("code", "rubrique");
////        module = modulesDao.findEntityHavingValue("code", "mod.ref");
////        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
////        creatRubriqueByExcell();
////
////        try {
////            this.updateTableRubrique();
////        } catch (Exception e) {
////        }
////        chargeRubrique();
////    }
////
////    public void chargeRubrique() {
//////        listeRubrique = rubriqueDao.liteRubriqueByCompagnie(entreprise);
//////        listeRubrique.addAll(rubriqueDao.liteRubriqueForAllCompagnie());
////        listeRubrique = (List<OrclassRubrique>) rubriqueDao.findAll();
////    }
////
////    public void creatRubriqueByExcell() {
////        FacesContext ctx = FacesContext.getCurrentInstance();
////        ExternalContext extContext = ctx.getExternalContext();
////        List<OrclassRubrique> listRubrique = new ArrayList<>();
////        OrclassRubrique rub;
////        OrclassEntreprises en = null;
////        try {
////            String path = extContext.getRealPath("") + this.pahtRubrique;
////            File file = new File(path);
////            InputStream targetStream = new FileInputStream(file);
////            listRubrique = serviceExcell.recuperListeRubriqueByExcell(targetStream, path);
////            if (listRubrique == null) {
////                System.err.println("Une erruer est survenue dans l extraction des donnes sur le fichier Excell");
////            }
////            for (OrclassRubrique r : listRubrique) {
////                rub = rubriqueDao.findEntityHavingValue("code", r.getCode());
////                if (rub == null) {
////                    rub = rubriqueDao.findEntityHavingValue("libelle", r.getLibelle());
////                    if (rub != null) {
////                        continue;
//////                        rub = new OrclassRubrique(r.getCode(), r.getLibelle() + ".", r.getNatrubta());
//////                        rub.setDateCreation(new Date());
////////                        rub.setIdEntreprise(entreprise);
//////                        rub.setShowAllCompagnies(Boolean.TRUE);
//////                        rubriqueDao.create(rub);
////
////                    }
////                    rub = new OrclassRubrique(r.getCode(), r.getLibelle(), r.getNatrubta());
////                    rub.setDateCreation(new Date());
////                    rub.setShowAllCompagnies(Boolean.TRUE);
//////                    rub.setIdEntreprise(entreprise);
////                    rubriqueDao.create(rub);
////                }
////
////            }
////        } catch (Exception e) {
////            System.err.println("Une erruer est survenue dans l insertion des donneés");
////
////        }
////    }
////
////    public String deleteRubrique() {
////        FacesContext ctx = FacesContext.getCurrentInstance();
////        //get default locale
////        Locale myLoc = ctx.getViewRoot().getLocale();
////        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "rubrique", null, myLoc)};
////
////        // on recupere tous les modules qui lui sont attribuer puis on inserre
////        String[] detail = {entete[0], "Module(s)"};
////        try {
////            if (rubrique != null && rubrique.getId() != null) {
////
////                rubriqueDao.delete(rubrique);
////                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.succes", entete, myLoc);
////                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.delete.succes", detail, myLoc);
////                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
////
////            } else {
////                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
////            }
////        } catch (ConstraintViolationException ejb) {
////            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
////            throw ejb;
////        } catch (Exception th) {
////            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "prestation", exception.Error.FATAL_ERROR + "", new Object[]{"prestation"});
////            logger.error("Erreur Survenu", th);
////        }
////        listeRubrique.remove(rubrique);
////        this.reset();
////        return null;
////    }
////
////    public String addRubrique() {
////
////        FacesContext ctx = FacesContext.getCurrentInstance();
////        //get default locale
////        Locale myLoc = ctx.getViewRoot().getLocale();
////        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "rubrique", null, myLoc)};
////
////        // on recupere tous les modules qui lui sont attribuer puis on inserre
////        String[] detail = {entete[0], "Module(s)"};
////        try {
////            if (rubriqueDao.finkey(entreprise, rubrique.getCode()) == null && rubrique.getCode() != null) {
////                rubrique.setDateCreation(new Date());
////                rubrique.setIdEntreprise(entreprise);
////
////                rubriqueDao.create(rubrique);
////                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
////                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
////                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
////
////            } else {
////                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "CODE IS NULL OR EXISTS"));
////            }
////        } catch (GlobalException e) {
////
////            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCode(), e.getCode()));
//////throw e;
////        } catch (ConstraintViolationException ejb) {
////            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
////            throw ejb;
////        } catch (Exception th) {
////            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_FATAL, "prestation", exception.Error.FATAL_ERROR + "", new Object[]{"prestation"});
////            logger.error("Erreur Survenu", th);
////        }
////        listeRubrique.add(rubriqueDao.findEntityHavingValue("code", rubrique.getCode()));
////        this.reset();
////        return null;
////
////    }
////
////    public String updatePrestation() {
////        String success = null;
////        FacesContext ctx = FacesContext.getCurrentInstance();
////
////        //get default locale
////        Locale myLoc = ctx.getViewRoot().getLocale();
////        //Locale myLoc =new Locale("fr");
////        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "prestation", null, myLoc)};
////        String[] detail = {entete[0], "Parametres"};
////
////        try {
////
////            if (rubrique != null && rubrique.getId() != null) {
////                listeRubrique.remove(rubrique);
////                rubrique.setDateModification(new Date());
////                rubriqueDao.update(rubrique);
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
////        listeRubrique.add(rubrique);
////
////        reset();
////        return null;
////
////    }
////
////    public void reset() {
////
////        this.init();
////
////        PrimeFaces.current().ajax().update("form1");
////        this.updateTableRubrique();
////
////    }
////
////    public void updateTableRubrique() {
////        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:idrubrique");
////        table.setValueExpression("sortBy", null);
////
////        PrimeFaces.current().executeScript("PF('rubriqueTable').clearFilters();");
////
////    }
////
////    public void init() {
////        rubrique = new OrclassRubrique();
////    }
////
////    public OrclassRubrique getRubrique() {
////        return rubrique;
////    }
////
////    public void setRubrique(OrclassRubrique rubrique) {
////        this.rubrique = rubrique;
////    }
////
////    public List<OrclassRubrique> getListeRubrique() {
////        return listeRubrique;
////    }
////
////    public void setListeRubrique(List<OrclassRubrique> listeRubrique) {
////        this.listeRubrique = listeRubrique;
////    }
////
////    public List<OrclassRubrique> getFilterRubrique() {
////        return filterRubrique;
////    }
////
////    public void setFilterRubrique(List<OrclassRubrique> filterRubrique) {
////        this.filterRubrique = filterRubrique;
////    }
////
////    public Boolean accessCree() {
////        OrclassActions action = null;
////        OrclassFonctionnalites fon = null;
////        OrclassMenusAcces ma = null;
////        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
////            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.rubrique_reference.name());
////            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);
////
////            return serviceAccess.accessAction(user, action, menu);
////        }
////        return Boolean.FALSE;
////    }
////
////    public Boolean accessAjoute() {
////        OrclassActions action = null;
////        OrclassFonctionnalites fon = null;
////        OrclassMenusAcces ma = null;
////        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
////            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.rubrique_reference.name());
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
////            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.rubrique_reference.name());
////            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
////            return serviceAccess.accessAction(user, action, menu);
////        }
////        return Boolean.FALSE;
////    }
////
////    public Boolean accessSupprimer() {
////        OrclassActions action = null;
////        OrclassFonctionnalites fon = null;
////        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
////            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.rubrique_reference.name());
////            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer.name(), fon);
////            return serviceAccess.accessAction(user, action, menu);
////        }
////        return Boolean.FALSE;
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
////}
