///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import static Entreprise.EntrepriseServices.logger;
//import dao.OrclassBranchesDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassTypeDocumentDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.CategorieDocument;
//import enums.FonctionnaliteModuleParametrage;
//import enums.GenreDocument;
//import enums.LibelleBranche;
//import enums.NiveauGestionDocument;
//import java.io.IOException;
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
//import javax.faces.model.SelectItem;
//import javax.inject.Named;
//import javax.faces.view.ViewScoped;
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.ConstraintViolationException;
//import modele.OrclassActions;
//import modele.OrclassBranches;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassTypeDocument;
//import modele.OrclassUtilisateurs;
//import org.primefaces.PrimeFaces;
//import parametrage.ModuleMenu;
//import utils.GlobalFonctions;
//import utils.LocaleHelper;
//import utils.RecupBundle;
//
///**
// *
// * @author JIATOU FRANCK
// */
//@Named(value = "typeDocumentController")
//@ViewScoped
//public class TypeDocumentController implements Serializable {
//
//    /**
//     * Creates a new instance of TypeDocumentController
//     */
//    @EJB
//    OrclassTypeDocumentDao typeDocumentDao;
//    OrclassTypeDocument typeDocument;
//    @EJB
//    OrclassBranchesDao branchesDao;
//    OrclassBranches branches;
//
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    IDroitAcces serviceAccess;
//
//    private List<OrclassTypeDocument> listeTypeDocument = new ArrayList<>();
//    private List<OrclassTypeDocument> filterTypeDocument = new ArrayList<>();
//    private List<OrclassBranches> listeBranches = new ArrayList<>();
//    String summary = "";
//    String msgdetail = "";
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//    OrclassEntreprises entreprise;
//
//    public TypeDocumentController() {
//        typeDocument = new OrclassTypeDocument();
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
//        menu = menusDao.findEntityHavingValue("code", ModuleMenu.mTypeDocument);
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        listeTypeDocument = (List<OrclassTypeDocument>) typeDocumentDao.findAll();
//        listeBranches = (List<OrclassBranches>) branchesDao.findAll();
//        this.updateTableTaxePrime();
//
//    }
//
//    public void updateTableTaxePrime() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:idTableTypeDoc");
//        if (table == null) {
//            return;
//        }
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('tableTypeDoc').clearFilters();");
//
//    }
//
//    public String valueObjectByLibelleAutres(OrclassBranches br) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        if (br != null && br.getLibelle().equals(LibelleBranche.autres)) {
//            return br.getLibelleAutre();
//        }
//        if (br != null) {
//            return LocaleHelper.getLocaleString(RecupBundle.FichierBundle, br.getLibelle().toString(), null, myLoc);
//        }
//        return "";
//    }
//
//    public String addTypeDocument() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "typeDocument", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if ((typeDocument != null && typeDocument.getCategorieDocument() == null) || (typeDocument != null && typeDocument.getNiveauGestionDocument() == null) || (typeDocument != null && typeDocument.getGenreDocument() == null)) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE VALUE IS NULL", "PLEASE SELECT THE  VALUE"));
//                PrimeFaces.current().executeScript("PF('TypeDocDialog').hide()");
//                return null;
//            }
//            if (typeDocument != null && Objects.equals(typeDocument.getAllBranches(), Boolean.TRUE)) {
//                if (branches != null && branches.getIdBranche() != null) {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE CHOICE OF THE VALUE IS ALL BRANCH ", "PLEASE NOT FIXE THE VALUE OF BRANCH"));
//                    branches = new OrclassBranches();
//                    PrimeFaces.current().executeScript("PF('TypeDocDialog').hide()");
//                    return null;
//                }
//            } else {
//                if (branches == null && branches.getIdBranche() == null) {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE VALUE BRANCH IS NULL  ", "PLEASE  FIXE THE VALUE OF  BRANCH"));
//                    PrimeFaces.current().executeScript("PF('TypeDocDialog').hide()");
//                    return null;
//                }
//            }
//            if (typeDocument != null && typeDocument.getId() == null) {
//                if (branches != null && branches.getIdBranche() != null) {
//                    typeDocument.setIdBranche(branches);
//                }
//                if (typeDocumentDao.findEntityHavingValue("code", typeDocument.getCode()) == null) {
//                    typeDocumentDao.create(typeDocument);
//                    listeTypeDocument = (List<OrclassTypeDocument>) typeDocumentDao.findAll();
//                }
//                typeDocument = new OrclassTypeDocument();
//
//                this.updateTableTaxePrime();
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                PrimeFaces.current().ajax().update(":form1,:form2");
//                PrimeFaces.current().executeScript("PF('TypeDocDialog').show()");
//
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "FATAL ERROR"));
//
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "condition", exception.Error.FATAL_ERROR + "", new Object[]{"Type Document"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        return null;
//    }
//
//    public String updatetypeDocument() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "typeDocument", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//
//            if ((typeDocument != null && typeDocument.getCategorieDocument() == null) || (typeDocument != null && typeDocument.getNiveauGestionDocument() == null) || (typeDocument != null && typeDocument.getGenreDocument() == null)) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE VALUE IS NULL", "PLEASE SELECT THE  VALUE"));
//                return null;
//            }
//            if (typeDocument != null && Objects.equals(typeDocument.getAllBranches(), Boolean.TRUE)) {
//                if (branches != null && branches.getIdBranche() != null) {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE CHOICE OF THE VALUE IS ALL BRANCH ", "PLEASE NOT FIXE THE VALUE OF BRANCH"));
//                    branches = new OrclassBranches();
//                    return null;
//                }
//            } else {
//                if (branches == null && branches.getIdBranche() == null) {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE VALUE BRANCH IS NULL  ", "PLEASE  FIXE THE VALUE OF  BRANCH"));
//
//                    return null;
//                }
//            }
//            if (typeDocument != null && typeDocument.getId() != null) {
//                if (typeDocument != null && Objects.equals(typeDocument.getAllBranches(), Boolean.TRUE)) {
//                    typeDocument.setIdBranche(null);
//                } else {
//                    typeDocument.setAllBranches(Boolean.FALSE);
//                }
//                typeDocumentDao.update(typeDocument);
//                listeTypeDocument = (List<OrclassTypeDocument>) typeDocumentDao.findAll();
//                typeDocument = new OrclassTypeDocument();
//                this.updateTableTaxePrime();
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//                PrimeFaces.current().executeScript("PF('TypeDocDialog').hide()");
//                PrimeFaces.current().ajax().update(":form2,:form1");
//
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "FATAL ERROR"));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "typeDocument", exception.Error.FATAL_ERROR + "", new Object[]{"type Document"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        return null;
//    }
//
//    public void reload() throws IOException {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
//    }
//
//    public String deleteTypeDocument() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "typeDocument", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (typeDocument != null && typeDocument.getId() != null) {
//
//                typeDocumentDao.delete(typeDocument);
//                listeTypeDocument = (List<OrclassTypeDocument>) typeDocumentDao.findAll();
//                typeDocument = new OrclassTypeDocument();
//                this.updateTableTaxePrime();
//                PrimeFaces.current().ajax().update(":form1");
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "typeDocument", exception.Error.FATAL_ERROR + "", new Object[]{"type_Document"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        return null;
//    }
//
//    public List<SelectItem> getCategoriesDocuments() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (CategorieDocument cdoc : CategorieDocument.values()) {
//
//            items.add(new SelectItem(cdoc, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, cdoc.name(), null, myLoc)));
//        }
//
//        return items;
//    }
//
//    public List<SelectItem> getNiveauGestionDocuments() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (NiveauGestionDocument nvdoc : NiveauGestionDocument.values()) {
//
//            items.add(new SelectItem(nvdoc, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, nvdoc.name(), null, myLoc)));
//        }
//
//        return items;
//    }
//
//    public List<SelectItem> getGenreDocuments() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (GenreDocument gdoc : GenreDocument.values()) {
//
//            items.add(new SelectItem(gdoc, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, gdoc.name(), null, myLoc)));
//        }
//
//        return items;
//    }
//
//    public OrclassTypeDocument getTypeDocument() {
//        return typeDocument;
//    }
//
//    public void setTypeDocument(OrclassTypeDocument typeDocument) {
//        this.typeDocument = typeDocument;
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
//    public List<OrclassTypeDocument> getListeTypeDocument() {
//        return listeTypeDocument;
//    }
//
//    public void setListeTypeDocument(List<OrclassTypeDocument> listeTypeDocument) {
//        this.listeTypeDocument = listeTypeDocument;
//    }
//
//    public List<OrclassTypeDocument> getFilterTypeDocument() {
//        return filterTypeDocument;
//    }
//
//    public void setFilterTypeDocument(List<OrclassTypeDocument> filterTypeDocument) {
//        this.filterTypeDocument = filterTypeDocument;
//    }
//
//    public List<OrclassBranches> getListeBranches() {
//        return listeBranches;
//    }
//
//    public void setListeBranches(List<OrclassBranches> listeBranches) {
//        this.listeBranches = listeBranches;
//    }
//
//    public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.typeDocument.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.typeDocument.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.typeDocument.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public void showDetail(OrclassTypeDocument item) {
//        if (typeDocument != null && typeDocument.getId() != null) {
//            if (typeDocument.getIdBranche() != null && typeDocument.getIdBranche().getIdBranche() != null) {
//                this.setBranches(typeDocument.getIdBranche());
////                PrimeFaces.current().ajax().update(":form2");
//            }
//        } else if (item != null && item.getId() != null) {
//            this.setBranches(item.getIdBranche());
//        } else {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE VALUE ENTITY IS NULL", "PLEASE TRY AGAINST ACTION"));
//            PrimeFaces.current().executeScript("PF('TypeDocDialog').hide()");
//        }
//    }
//
//}
