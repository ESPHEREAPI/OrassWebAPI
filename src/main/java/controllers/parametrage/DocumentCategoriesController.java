///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import static Entreprise.EntrepriseServices.logger;
//import dao.OrclassCategoriesDao;
//import dao.OrclassDocumentCategoriesDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassNatureDocumentDao;
//import dao.OrclassTypeDocumentDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleParametrage;
//import enums.LibelleBranche;
//import enums.LibelleCategorie;
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//import java.util.Objects;
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.application.FacesMessage;
//import javax.faces.component.UIComponent;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.inject.Named;
//import javax.faces.view.ViewScoped;
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.ConstraintViolationException;
//import modele.OrclassActions;
//import modele.OrclassBranches;
//import modele.OrclassCategories;
//import modele.OrclassDocumentCategories;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassNatureDocument;
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
//@Named(value = "documentCategoriesController")
//@ViewScoped
//public class DocumentCategoriesController implements Serializable {
//
//    /**
//     * Creates a new instance of DocumentCategoriesController
//     */
//    @EJB
//    OrclassDocumentCategoriesDao documentCategoriesDao;
//    OrclassDocumentCategories documentCategories;
//    @EJB
//    OrclassNatureDocumentDao natureDocumentDao;
//    OrclassNatureDocument natureDocument;
//    @EJB
//    OrclassTypeDocumentDao typeDocumentDao;
//    OrclassTypeDocument typeDocument;
//    @EJB
//    OrclassCategoriesDao categoriesDao;
//    OrclassCategories categories;
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    IDroitAcces serviceAccess;
//    
//    private List<OrclassDocumentCategories> listeDocumentCategories = new ArrayList<>();
//    private List<OrclassTypeDocument> listeTypeDocument = new ArrayList<>();
//    private List<OrclassDocumentCategories> filterDocumentCategories = new ArrayList<>();
//    private List<OrclassCategories> listeCategories = new ArrayList<>();
//    private List<OrclassNatureDocument> listeNatureDocument = new ArrayList<>();
//    String summary = "";
//    String msgdetail = "";
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//    OrclassEntreprises entreprise;
//    
//    public DocumentCategoriesController() {
//        categories = new OrclassCategories();
//        typeDocument = new OrclassTypeDocument();
//        natureDocument = new OrclassNatureDocument();
//        documentCategories = new OrclassDocumentCategories();
//    }
//    
//    public void initial() {
//        categories = new OrclassCategories();
//        typeDocument = new OrclassTypeDocument();
//        natureDocument = new OrclassNatureDocument();
//        documentCategories = new OrclassDocumentCategories();
//    }
//    
//    public void reset() {
//        this.initial();
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
//        menu = menusDao.findEntityHavingValue("code", ModuleMenu.mDocumentCategorie);
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        listeTypeDocument = (List<OrclassTypeDocument>) typeDocumentDao.findAll();
//        listeDocumentCategories = documentCategoriesDao.listeDoucumentCategoriesByCompagnie(entreprise);
//        listeNatureDocument = (List<OrclassNatureDocument>) natureDocumentDao.findAll();
//        listeTypeDocument = (List<OrclassTypeDocument>) typeDocumentDao.findAll();
//        this.updateTableDocumentCategorie();
//        
//    }
//    
//    public void updateTableDocumentCategorie() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:idTableDoc_Cat");
//        if (table == null) {
//            return;
//        }
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('tableDoc_Cat').clearFilters();");
//        
//    }
//
//    /*
//    ici on charge les categories par raport au choix du type document
//     */
//    public void chargeCategorieByTypeDocument() {
//        if (typeDocument != null && typeDocument.getId() != null) {
//            if (typeDocument.getIdBranche() != null && typeDocument.getIdBranche().getIdBranche() != null) {
//                listeCategories = categoriesDao.listeCategorieByBranche(typeDocument.getIdBranche(), entreprise);
//            } else {
//                listeCategories = categoriesDao.listAllCategorieByCompagnie(entreprise);
//                if (listeCategories.isEmpty()) {
//                    listeCategories = categoriesDao.listAllCategoriesShowAllCompagnie();
//                } else {
//                    listeCategories.addAll(categoriesDao.listAllCategoriesShowAllCompagnie());
//                }
//            }
//            if (listeCategories.isEmpty()) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("LIST CATEGORY IS EMPTY", "PLEASE CREATE THE CATEGORY OR CONTACT YOU ADMINISTRACTOR..."));
//                PrimeFaces.current().executeScript("PF('TypeDocDialog').hide()");
//                return;
//            } else {
//                PrimeFaces.current().ajax().update(":form2");
//                PrimeFaces.current().executeScript("PF('TypeDocDialog').show()");
//            }
//        }
//    }
//    
//    public String valueObjectByLibelleAutres(OrclassCategories cat) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        if (cat != null && cat.getLibelle().equals(LibelleCategorie.autres)) {
//            return cat.getLibelleAutre();
//        }
//        if (cat != null) {
//            return LocaleHelper.getLocaleString(RecupBundle.FichierBundle, cat.getLibelle().toString(), null, myLoc);
//        }
//        return "";
//    }
//    
//    public String addDocumentCategories() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "documentCategorie", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        String nomComplet = user.getNom();
//        if (user.getPrenom() != null && user.getPrenom() != "") {
//            nomComplet += " " + user.getPrenom();
//        }
//        try {
//            
//            if (documentCategories != null && documentCategories.getId() == null) {
//                
//                if (documentCategoriesDao.finKey(entreprise, categories, typeDocument, natureDocument, documentCategories.getDate_effet()) == null) {
//                    documentCategories.setIdEntreprises(entreprise);
//                    documentCategories.setIdCategories(categories);
//                    documentCategories.setNatureDocuments(natureDocument);
//                    documentCategories.setTypeDocument(typeDocument);
//                    documentCategories.setDateCreation(new Date());
//                    documentCategories.setSaisir_par(nomComplet);
//                    documentCategoriesDao.create(documentCategories);
//                    listeDocumentCategories = documentCategoriesDao.listeDoucumentCategoriesByCompagnie(entreprise);
//                }
//                
//                this.updateTableDocumentCategorie();
//                
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                reset();
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "documentCategorie", exception.Error.FATAL_ERROR + "", new Object[]{"documentCategorie"});
//            logger.error("Erreur Survenu", th);
//        }
//        
//        return null;
//    }
//    
//    public String updateDocumentCategorie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "documentCategorie", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        String nomComplet = user.getNom();
//        if (user.getPrenom() != null && user.getPrenom() != "") {
//            nomComplet += " " + user.getPrenom();
//        }
//        try {
//            
//            if (documentCategories != null && documentCategories.getId() != null) {
//                documentCategories.setDateModification(new Date());
//                documentCategories.setModifier_par(nomComplet);
//                documentCategoriesDao.update(documentCategories);
//                listeDocumentCategories = documentCategoriesDao.listeDoucumentCategoriesByCompagnie(entreprise);
//                
//                this.updateTableDocumentCategorie();
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                reset();
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "documentCategorie", exception.Error.FATAL_ERROR + "", new Object[]{"documentCategorie"});
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
//    public String deleteDocumentCategories() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "documentCategorie", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (documentCategories != null && documentCategories.getId() != null) {
//                
//                documentCategoriesDao.delete(documentCategories);
//                listeDocumentCategories = documentCategoriesDao.listeDoucumentCategoriesByCompagnie(entreprise);
//                
//                this.updateTableDocumentCategorie();
//                reset();
//                PrimeFaces.current().ajax().update(":form1,:form2");
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "documentCategorie", exception.Error.FATAL_ERROR + "", new Object[]{"documentCategorie"});
//            logger.error("Erreur Survenu", th);
//        }
//        
//        return null;
//    }
//    
//    public void showDetail(OrclassDocumentCategories item) {
//        if (documentCategories != null && documentCategories.getId() != null) {
//            this.setCategories(documentCategories.getIdCategories());
//            this.setNatureDocument(documentCategories.getNatureDocuments());
//            this.setTypeDocument(documentCategories.getTypeDocument());
//            
//        } else if (item != null && item.getId() != null) {
//            this.setCategories(item.getIdCategories());
//            this.setNatureDocument(item.getNatureDocuments());
//            this.setTypeDocument(item.getTypeDocument());
//        } else {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE VALUE ENTITY IS NULL", "PLEASE TRY AGAINST ACTION"));
//            PrimeFaces.current().executeScript("PF('TypeDocDialog').hide()");
//        }
//        if (typeDocument != null && typeDocument.getId() != null && categories != null && categories.getIdCategorie() != null) {
//            listeCategories = categoriesDao.listeCategorieByBranche(typeDocument.getIdBranche(), entreprise);
//        } else {
//            listeCategories = categoriesDao.listAllCategorieByCompagnie(entreprise);
//        }
//    }
//    
//    public OrclassDocumentCategories getDocumentCategories() {
//        return documentCategories;
//    }
//    
//    public void setDocumentCategories(OrclassDocumentCategories documentCategories) {
//        this.documentCategories = documentCategories;
//    }
//    
//    public OrclassNatureDocument getNatureDocument() {
//        return natureDocument;
//    }
//    
//    public void setNatureDocument(OrclassNatureDocument natureDocument) {
//        this.natureDocument = natureDocument;
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
//    public OrclassCategories getCategories() {
//        return categories;
//    }
//    
//    public void setCategories(OrclassCategories categories) {
//        this.categories = categories;
//    }
//    
//    public List<OrclassDocumentCategories> getListeDocumentCategories() {
//        return listeDocumentCategories;
//    }
//    
//    public void setListeDocumentCategories(List<OrclassDocumentCategories> listeDocumentCategories) {
//        this.listeDocumentCategories = listeDocumentCategories;
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
//    public List<OrclassDocumentCategories> getFilterDocumentCategories() {
//        return filterDocumentCategories;
//    }
//    
//    public void setFilterDocumentCategories(List<OrclassDocumentCategories> filterDocumentCategories) {
//        this.filterDocumentCategories = filterDocumentCategories;
//    }
//    
//    public List<OrclassCategories> getListeCategories() {
//        return listeCategories;
//    }
//    
//    public void setListeCategories(List<OrclassCategories> listeCategories) {
//        this.listeCategories = listeCategories;
//    }
//    
//    public List<OrclassNatureDocument> getListeNatureDocument() {
//        return listeNatureDocument;
//    }
//    
//    public void setListeNatureDocument(List<OrclassNatureDocument> listeNatureDocument) {
//        this.listeNatureDocument = listeNatureDocument;
//    }
//    
//    public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.documentCategorie.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.documentCategorie.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.documentCategorie.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//}
