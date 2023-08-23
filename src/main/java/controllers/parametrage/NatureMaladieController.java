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
//import dao.OrclassNatureMaladieDao;
//import dao.OrclassRefNatureMaladieDao;
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
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassNatureMaladie;
//import modele.OrclassPrestataire;
//import modele.OrclassRefNatureMaladie;
//import modele.OrclassUtilisateurs;
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
//@Named(value = "natureMaladieController")
//@ViewScoped
//public class NatureMaladieController implements Serializable {
//
//    @EJB
//    OrclassRefNatureMaladieDao refNatureMaladieDao;
//    OrclassRefNatureMaladie refNatureMaladie;
//    @EJB
//    OrclassNatureMaladieDao natureMaladieDao;
//    OrclassNatureMaladie natureMaladie;
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
//    String summary = "";
//    String msgdetail = "";
//    private OrclassEntreprises entreprise;
//    private List<OrclassNatureMaladie> listeNatureMaladie = new ArrayList<>();
//    private List<OrclassNatureMaladie> filterNatureMaladie;
//
//    /**
//     * Creates a new instance of NatureMaladieController
//     */
//    public NatureMaladieController() {
//        natureMaladie = new OrclassNatureMaladie();
//        refNatureMaladie = new OrclassRefNatureMaladie();
//    }
//
//    public void init() {
//        natureMaladie = new OrclassNatureMaladie();
//        refNatureMaladie = new OrclassRefNatureMaladie();
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
//        listeNatureMaladie = natureMaladieDao.listeNatureMaladieByCompagnie(entreprise);
//
//    }
//
//    public List<String> completeText(String query) {
//        List<String> results = new ArrayList<>();
//        if (query != null && !query.trim().equals("")) {
//            results = refNatureMaladieDao.getRefNatureMaladieCodeWithFilters(query);
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
//     public void onItemSelect(SelectEvent<String> event) {
//        OrclassRefNatureMaladie ref = null;
//        ref = refNatureMaladieDao.findEntityHavingValue("code", event.getObject());
//        if (ref != null && ref.getId() != null) {
//            this.setRefNatureMaladie(ref);
//            PrimeFaces.current().ajax().update(":form1");
//        }
//    }
//         public void reset() {
//        
//        this.init();
//        
//        PrimeFaces.current().ajax().update("form1,form2");
//        
//    }
//         
//         public String addNatureMaladie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String message = "";
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "natureMaladie", null, myLoc)};
//        try {
//            if (refNatureMaladie.getCode() == null || "".equals(refNatureMaladie.getCode())) {
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "CODE IS EMPTY", "PLEASE WRITE THE CODE"));
//                return "";
//            }
//            if (refNatureMaladie.getLibelle() == null || "".equals(refNatureMaladie.getLibelle())) {
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "LIBELLE IS EMPTY", "PLEASE WRITE THE REASON SOCIAL"));
//                return "";
//            }
//
//            
//            if (refNatureMaladie.getCode() != null && !"".equals(refNatureMaladie.getCode()) && refNatureMaladie.getLibelle() != null && !"".equals(refNatureMaladie.getLibelle())) {
//                message = sinistreService.creationNatureMaladie(entreprise, refNatureMaladie, natureMaladie);
//               listeNatureMaladie=natureMaladieDao.listeNatureMaladieByCompagnie(entreprise);
//                String[] detail = {entete[0], refNatureMaladie.getCode() + "," + refNatureMaladie.getLibelle()};
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
//    public String updateNatureMaladie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "natureMaladie", null, myLoc)};
//        try {
//            //teste si le pays existe
//
//            if (refNatureMaladie.getId() != null) {
//                
//                natureMaladie.setDateModification(new Date());
//                refNatureMaladieDao.update(refNatureMaladie);
//
//                natureMaladieDao.update(natureMaladie);
//                String[] detail = {entete[0], refNatureMaladie.getCode() + "," + refNatureMaladie.getLibelle()};
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
//                
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//            } else {
//                String[] detail = {entete[0], refNatureMaladie.getCode() + "," + refNatureMaladie.getLibelle()};
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
//    public void showDetails(OrclassNatureMaladie item) {
//        if (natureMaladie == null || natureMaladie.getId() == null) {
//            this.setNatureMaladie(item);
//        }
//        this.setRefNatureMaladie(natureMaladie.getIdRefNatureMaladie());
//        PrimeFaces.current().ajax().update(":form1");
//    }
//    
//     public String deleteNatureMaladie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "natureMaladie", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//       
//        try {
//            if (natureMaladie != null && natureMaladie.getId()!= null) {
//
////               categoriesDao.delete(categories);
//               natureMaladieDao.delete(natureMaladie);
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "categorie", exception.Error.FATAL_ERROR + "", new Object[]{"natureMaladie"});
//            logger.error("Erreur Survenu", th);
//        }
////       lisClass.remove(classes);
//        this.reset();
//        listeNatureMaladie=natureMaladieDao.listeNatureMaladieByCompagnie(entreprise);
//        PrimeFaces.current().ajax().update(":form1,:form2");
//        
//        return null;
//    }
//
//    public OrclassRefNatureMaladie getRefNatureMaladie() {
//        return refNatureMaladie;
//    }
//
//    public void setRefNatureMaladie(OrclassRefNatureMaladie refNatureMaladie) {
//        this.refNatureMaladie = refNatureMaladie;
//    }
//
//    public OrclassNatureMaladie getNatureMaladie() {
//        return natureMaladie;
//    }
//
//    public void setNatureMaladie(OrclassNatureMaladie natureMaladie) {
//        this.natureMaladie = natureMaladie;
//    }
//
//    public List<OrclassNatureMaladie> getListeNatureMaladie() {
//        return listeNatureMaladie;
//    }
//
//    public void setListeNatureMaladie(List<OrclassNatureMaladie> listeNatureMaladie) {
//        this.listeNatureMaladie = listeNatureMaladie;
//    }
//
//    public List<OrclassNatureMaladie> getFilterNatureMaladie() {
//        return filterNatureMaladie;
//    }
//
//    public void setFilterNatureMaladie(List<OrclassNatureMaladie> filterNatureMaladie) {
//        this.filterNatureMaladie = filterNatureMaladie;
//    }
//     
//     
//     
//     
//     
//
//}
