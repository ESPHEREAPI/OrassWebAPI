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
//import dao.OrclassPrestataireDao;
//import dao.orclassRefPrestataireDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleAdministration;
//import enums.StatutPrestataire;
//import java.io.Serializable;
//import java.util.ArrayList;
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
//import javax.validation.ConstraintViolationException;
//import modele.OrclassActions;
//import modele.OrclassCategories;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassIntermediaires;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassPrestataire;
//import modele.OrclassRefIntermediaire;
//import modele.OrclassTypeBureau;
//import modele.OrclassUtilisateurs;
//import modele.OrclassVille;
//import modele.orclassRefPrestataire;
//import org.primefaces.PrimeFaces;
//import org.primefaces.event.SelectEvent;
//import sante.ISinistre;
//import utils.GlobalFonctions;
//import utils.LocaleHelper;
//import utils.RecupBundle;
//
///**
// *
// * @author JIATOU FRANCK
// */
//@Named(value = "prestataireControllers")
//@ViewScoped
//public class PrestataireControllers implements Serializable {
//    
//    @EJB
//    orclassRefPrestataireDao refPrestataireDao;
//    orclassRefPrestataire refPrestataire;
//    @EJB
//    OrclassPrestataireDao prestataireDao;
//    OrclassPrestataire prestataire;
//    
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    IDroitAcces serviceAccess;
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//    @EJB
//    ISinistre sinistreService;
//    
//    String summary = "";
//    String msgdetail = "";
//    private OrclassEntreprises entreprise;
//    private List<OrclassPrestataire> listePrestataire = new ArrayList<>();
//    private List<OrclassPrestataire> filterPrestataire;
//
//    /**
//     * Creates a new instance of PrestataireControllers
//     */
//    public PrestataireControllers() {
//        
//        prestataire = new OrclassPrestataire();
//        refPrestataire = new orclassRefPrestataire();
//    }
//    
//    public void init() {
//        
//        prestataire = new OrclassPrestataire();
//        refPrestataire = new orclassRefPrestataire();
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
//        menu = menusDao.findEntityHavingValue("code", "prestation");
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        listePrestataire=prestataireDao.listePrstataireByCompagnie(entreprise);
//        
//    }
//    
//    public List<String> completeText(String query) {
//        List<String> results = new ArrayList<>();
//        if (query != null && !query.trim().equals("")) {
//            results = refPrestataireDao.getRefPrestataireCodeWithFilters(query);
//        }
//        
//        return results;
//    }
//    
//    public void updateTablePrestataire() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("accord:form2:idtableIntemediare");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('tableIntemediare').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void onItemSelect(SelectEvent<String> event) {
//        orclassRefPrestataire ref = null;
//        ref = refPrestataireDao.findEntityHavingValue("code", event.getObject());
//        if (ref != null && ref.getId() != null) {
//            this.setRefPrestataire(ref);
//            PrimeFaces.current().ajax().update(":form1");
//        }
//    }
//    
//    public void reset() {
//        
//        this.init();
//        
//        PrimeFaces.current().ajax().update("form1,form2");
//        
//    }
//    
//    public String addPrestataire() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String message = "";
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "prestataire", null, myLoc)};
//        try {
//            if (refPrestataire.getCode() == null || "".equals(refPrestataire.getCode())) {
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "CODE IS EMPTY", "PLEASE WRITE THE CODE"));
//                return "";
//            }
//            if (refPrestataire.getLibelle() == null || "".equals(refPrestataire.getLibelle())) {
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "LIBELLE IS EMPTY", "PLEASE WRITE THE REASON SOCIAL"));
//                return "";
//            }
//
//            
//            if (refPrestataire.getCode() != null && !"".equals(refPrestataire.getCode()) && refPrestataire.getLibelle() != null && !"".equals(refPrestataire.getLibelle())) {
//                message = sinistreService.creationPrestataire(entreprise, refPrestataire, prestataire);
//                listePrestataire=prestataireDao.listePrstataireByCompagnie(entreprise);
//                String[] detail = {entete[0], refPrestataire.getCode() + "," + refPrestataire.getLibelle()};
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                if (message.equals(exception.Success.INSERT_SUCCESS.name())) {
//                    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message + " ..." + summary, msgdetail));
//                }else{
//                    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message + " ..." + summary, msgdetail));
//                }
//                
//            } else {
////                String[] detail = {entete[0], refPrestataire.getCode() + "," + refPrestataire.getLibelle()};
////                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.doublons", entete, myLoc);
////                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.Errdoublons", detail, myLoc);
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "CODE OR LIBELLE IS EMPTY...", "NULL"));
//                return null;
//                
//            }
//            
//        } catch (ConstraintViolationException E) {
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.error", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//            
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, msgdetail + E.getMessage() + " " + E.getCause()));
//            throw E;
//        } catch (Throwable th) {
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.error", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//
//            //ecrire dans le fichier de log  
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));
//            
//        }
//        
//        this.reset();
//        return null;
//    }
//    
//    public String updatePrestatiare() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "prestataire", null, myLoc)};
//        try {
//            //teste si le pays existe
//
//            if (refPrestataire.getId() != null) {
//                
//                prestataire.setDateModification(new Date());
//                refPrestataireDao.update(refPrestataire);
//
//                prestataireDao.update(prestataire);
//                String[] detail = {entete[0], refPrestataire.getCode() + "," + refPrestataire.getLibelle()};
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
//                
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//            } else {
//                String[] detail = {entete[0], refPrestataire.getCode() + "," + refPrestataire.getLibelle()};
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.ErrUpdate", detail, myLoc);
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                
//            }
//            
//        } catch (ConstraintViolationException E) {
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.error", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//            
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, msgdetail + E.getMessage() + " " + E.getCause()));
//            throw E;
//        } catch (Throwable th) {
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.error", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//
//            //ecrire dans le fichier de log  
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));
//            
//        }
//        this.reset();
//        return null;
//    }
//    
//    public List<SelectItem> getStatutPrestataires() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        
//        for (StatutPrestataire sp : StatutPrestataire.values()) {
//            
//            items.add(new SelectItem(sp, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, sp.name(), null, myLoc)));
//            
//        }
//        
//        return items;
//    }
//    
//    public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_intermediaire.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_intermediaire.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public void showDetails(OrclassPrestataire item) {
//        if (prestataire == null || prestataire.getId() == null) {
//            this.setPrestataire(item);
//        }
//        this.setRefPrestataire(prestataire.getIdRefPrestataire());
//        PrimeFaces.current().ajax().update(":form1");
//    }
//    
//     public String deletePrestataire() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "prestataire", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//       
//        try {
//            if (prestataire != null && prestataire.getId()!= null) {
//
////               categoriesDao.delete(categories);
//               prestataireDao.delete(prestataire);
//              
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "categorie", exception.Error.FATAL_ERROR + "", new Object[]{"prestataire"});
//            logger.error("Erreur Survenu", th);
//        }
////       lisClass.remove(classes);
//        this.reset();
//        listePrestataire=prestataireDao.listePrstataireByCompagnie(entreprise);
//        PrimeFaces.current().ajax().update(":form1,:form2");
//        
//        return null;
//    }
//
//    
//    public orclassRefPrestataire getRefPrestataire() {
//        return refPrestataire;
//    }
//    
//    public void setRefPrestataire(orclassRefPrestataire refPrestataire) {
//        this.refPrestataire = refPrestataire;
//    }
//    
//    public OrclassPrestataire getPrestataire() {
//        return prestataire;
//    }
//    
//    public void setPrestataire(OrclassPrestataire prestataire) {
//        this.prestataire = prestataire;
//    }
//    
//    public List<OrclassPrestataire> getListePrestataire() {
//        return listePrestataire;
//    }
//    
//    public void setListePrestataire(List<OrclassPrestataire> listePrestataire) {
//        this.listePrestataire = listePrestataire;
//    }
//    
//    public List<OrclassPrestataire> getFilterPrestataire() {
//        return filterPrestataire;
//    }
//    
//    public void setFilterPrestataire(List<OrclassPrestataire> filterPrestataire) {
//        this.filterPrestataire = filterPrestataire;
//    }
//    
//}
