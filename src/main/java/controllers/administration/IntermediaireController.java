///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.administration;
//
//import dao.OrclassBranchesDao;
//import dao.OrclassCategoriesDao;
//import dao.OrclassIntermediairesDao;
//import dao.OrclassRefIntermediaireDao;
//import dao.OrclassSuspensionBrancheIntemediaireDao;
//import dao.OrclassSuspensionCategorieIntermediaireDao;
//import dao.OrclassTypeBureauDao;
//import dao.OrclassUtilisateursDao;
//import dao.OrclassVilleDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleAdministration;
//import enums.TypeBureau;
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.AbstractList;
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
//import javax.faces.model.SelectItem;
//import javax.inject.Named;
//import javax.faces.view.ViewScoped;
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.ConstraintViolationException;
//import modele.Adresse;
//import modele.OrclassActions;
//import modele.OrclassBranches;
//import modele.OrclassCategories;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassIntermediaires;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassRefGaranties;
//import modele.OrclassRefIntermediaire;
//import modele.OrclassSuspensionBrancheIntemediaire;
//import modele.OrclassSuspensionCategorieIntermediaire;
//import modele.OrclassTypeBureau;
//import modele.OrclassUtilisateurs;
//import modele.OrclassVille;
//import org.primefaces.PrimeFaces;
//import org.primefaces.event.FlowEvent;
//import org.primefaces.event.SelectEvent;
//import org.primefaces.event.TabChangeEvent;
//import org.primefaces.event.TransferEvent;
//import org.primefaces.event.UnselectEvent;
//import org.primefaces.model.DualListModel;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import utils.GlobalFonctions;
//import utils.LocaleHelper;
//import utils.RecupBundle;
//
///**
// *
// * @author hp
// */
//@Named(value = "intermediaireController")
//@ViewScoped
//public class IntermediaireController implements Serializable {
//    
//    private static final Logger logger = LoggerFactory.getLogger(IntermediaireController.class);
//    /**
//     * Creates a new instance of IntermediaireController
//     */
//    @EJB
//    OrclassIntermediairesDao intermediairesDao;
//    OrclassIntermediaires intermediaires;
//    OrclassIntermediaires intermediairesSelect;
//    OrclassIntermediaires intermediairesSelectForTable;
//    
//    @EJB
//    OrclassUtilisateursDao utilisateursDao;
//    
//    @EJB
//    OrclassVilleDao villeDao;
//    OrclassVille ville;
//    @EJB
//    OrclassTypeBureauDao typeBureauDao;
//    OrclassTypeBureau typeBureau;
//    @EJB
//    OrclassRefIntermediaireDao refIntermediaireDao;
//    OrclassRefIntermediaire refIntermediaire;
//    
//    @EJB
//    IDroitAcces serviceAccess;
//    @EJB
//    OrclassSuspensionBrancheIntemediaireDao suspensionBrancheIntemediaireDao;
//    @EJB
//    OrclassSuspensionCategorieIntermediaireDao suspensionCategorieIntermediaireDao;
//    @EJB
//    OrclassBranchesDao branchesDao;
//    @EJB
//    OrclassCategoriesDao categoriesDao;
//    private List<OrclassTypeBureau> listeTypeBureau = new ArrayList<>();
//    private List<OrclassIntermediaires> listeIntermediaire = new ArrayList<>();
//    private List<OrclassVille> listeVille = new ArrayList<>();
//    private List<OrclassIntermediaires> filteredIntermediaires;
//    private List<OrclassVille> filteredVille;
//    private List<OrclassTypeBureau> filteredTypeBureau;
//    private List<OrclassCategories> listeCategoriesSuspenduForIntermediaire = new ArrayList<>();
//    private List<OrclassBranches> listeBranchesesSuspenduForIntermediaire = new ArrayList<>();
//    private DualListModel<OrclassBranches> brancheModel = new DualListModel<>(new ArrayList<OrclassBranches>(), new ArrayList<OrclassBranches>());
//    private List<OrclassBranches> brancheSource;
//    private List<OrclassBranches> brancheTarget;
//    private List<OrclassBranches> listeBranchesesAccees = new ArrayList<>();
//    
//    private DualListModel<OrclassCategories> categorieModel = new DualListModel<>(new ArrayList<OrclassCategories>(), new ArrayList<OrclassCategories>());
//    private List<OrclassCategories> categorieSource;
//    private List<OrclassCategories> categorieTarget;
//    private OrclassBranches brancheSelect;
//    
//    String summary = "";
//    String msgdetail = "";
//    private OrclassEntreprises entreprise;
//    private OrclassUtilisateurs user;
//    private OrclassMenus menu;
//    private OrclassModules module;
//    private String code = "", libelle = "";
//    private int i = 1;// reprepresente le tabView qui conserne information General
//    private int j = 0;// represente tabvien id =tab2 qui represente la suspension pour un intermediaire
//    Boolean suspension=Boolean.FALSE;
//
//    public IntermediaireController() {
//        intermediaires = new OrclassIntermediaires();
//        intermediairesSelect = new OrclassIntermediaires();
//        refIntermediaire=new OrclassRefIntermediaire();
//        intermediairesSelectForTable = new OrclassIntermediaires();
//        ville = new OrclassVille();
//        typeBureau = new OrclassTypeBureau();
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
//        menu = serviceAccess.getMenuByCode("intermediaire");
//        module = serviceAccess.getModuleByCode("mod.admin");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        listeTypeBureau = (List<OrclassTypeBureau>) typeBureauDao.listeTypeBureauShowAllCompagnie();
//        if (!typeBureauDao.listeTypeByCompagnie(entreprise).isEmpty()) {
//            listeTypeBureau.addAll(typeBureauDao.listeTypeByCompagnie(entreprise));
//        }
//
////        listeVille = (List<OrclassVille>) villeDao.listeByCompagnie(entreprise);
//        listeVille = (List<OrclassVille>) villeDao.findAll();
//        this.chargeIntermediaireByEntreprise();
//        intermediaires = new OrclassIntermediaires();
//        refIntermediaire=new OrclassRefIntermediaire();
//        updateTableIntermediaire();
//        
//    }
//    
//    public List<String> completeText(String query) {
//        List<String> results = new ArrayList<>();
//        if (query != null && !query.trim().equals("")) {
//            results = refIntermediaireDao.getRefIntermediaireCodeWithFilters(query);
//        }
//        
//        return results;
//    }
//    
//    public void onItemSelect(SelectEvent<String> event) {
//        OrclassRefIntermediaire ref = null;
//        ref = refIntermediaireDao.findEntityHavingValue("code", event.getObject());
//        if (ref != null && ref.getId() != null) {
//            this.setRefIntermediaire(ref);
//            PrimeFaces.current().ajax().update(":accord:form1");
//        }
//    }
//    
//    public void updateTableIntermediaire() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("accord:form2:idtableIntemediare");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('tableIntemediare').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
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
//    public Boolean chargeTybureauByLibelle() {
//        if (typeBureau != null && typeBureau.getTypeBureau() != null) {
//            if (typeBureau.getTypeBureau().equals(TypeBureau.Autre)) {
//                return Boolean.TRUE;
//            }
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
//    public Boolean accessSuspention() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_intermediaire.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.suspension.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public Boolean accessAjouteVille() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_intermediaire.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.ajoute_ville.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public Boolean accessAjouteBureau() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_intermediaire.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.ajoute_typeBureau.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public List<OrclassUtilisateurs> getUlisateurByIntermediaire(OrclassIntermediaires item) {
//        if (item != null && item.getIdIntermediaire() != null) {
//            return utilisateursDao.listeUtilisateurByIntemediaire(item, entreprise);
//        }
//        return null;
//    }
//    
//    public void reset() {
//        
//        this.init();
//        this.chargeIntermediaireByEntreprise();
//        updateTableIntermediaire();
//        PrimeFaces.current().ajax().update("form1,form2,form3,form4");
////        RequestContext.getCurrentInstance().reset(":accord:formPersonnel");
//    }
//    
//    public void selecteFormTab() {
//        PrimeFaces.current().executeScript("PF('accordP').select(0);");
//    }
//    
//    public void init() {
//        
//        intermediaires = new OrclassIntermediaires();
//        refIntermediaire=new OrclassRefIntermediaire();
//        ville = new OrclassVille();
//        typeBureau = new OrclassTypeBureau();
//        code = "";
//        libelle = "";
//        
//    }
//    
//    public void showDetails(OrclassIntermediaires item) {
//        Adresse ad;
//        if (intermediaires == null || intermediaires.getIdIntermediaire() == null) {
//            this.setIntermediaires(item);
//        }
//        this.setIntermediaires(intermediairesSelectForTable);
//        if (intermediaires.getAdresse() == null) {
//            ad = new Adresse();
//            intermediaires.setAdresse(ad);
//        } else if (intermediaires.getAdresse() != null) {
//            ad = intermediaires.getAdresse();
//            if (ad.getBp() == null) {
//                ad.setBp("");
//            }
//            if (ad.getEmail() == null) {
//                ad.setEmail("");
//            }
//            if (ad.getFax() == null) {
//                ad.setFax("");
//            }
//            if (ad.getTel() == null) {
//                ad.setTel("");
//            }
//        }
//        this.setRefIntermediaire(intermediaires.getIdRefIntermediaire());
//        
//    }
//    
//    public void initialisationVille() {
//        if (ville == null) {
//            ville = new OrclassVille();
//            PrimeFaces.current().executeScript("PF('manageVilleDialog').show();");
//        } else {
//            PrimeFaces.current().executeScript("PF('manageVilleDialog').show();");
//        }
//    }
//    
//    public void initialisationBureau() {
//        if (typeBureau == null) {
//            typeBureau = new OrclassTypeBureau();
//            PrimeFaces.current().executeScript("PF('manageBureauDialog').show();");
//        } else {
//            PrimeFaces.current().executeScript("PF('manageBureauDialog').show();");
//        }
//    }
//    
//    public String addIntermediaire() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "intermediaire", null, myLoc)};
//        try {
//            if (refIntermediaire.getCode() == null || "".equals(refIntermediaire.getCode())) {
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "CODE IS EMPTY", "PLEASE WRITE THE CODE"));
//                return "";
//            }
//            if (refIntermediaire.getRaisonSociale() == null || "".equals(refIntermediaire.getRaisonSociale())) {
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "REASON SOCIAL IS EMPTY", "PLEASE WRITE THE REASON SOCIAL"));
//                return "";
//            }
//            if (intermediaires.getIdTypeBureau() == null || intermediaires.getIdTypeBureau().getId() == null) {
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "OFFICE TYPE IS EMPTY", "PLEASE SELECT THE OFFICE TYPE"));
//                return "";
//            }
//            if (intermediaires.getIdVille() == null || intermediaires.getIdVille().getId() == null) {
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "CITY IS EMPTY", "PLEASE SELECT THE CITY"));
//                return "";
//            }
//            if ("".equals(intermediaires.getAdresse().getBp()) || intermediaires.getAdresse().getBp() == null) {
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "POST OFFICE BOX IS EMPTY", "PLEASE TO WRITE THE POST OFFICE BOX"));
//                return "";
//            }
//            if ("".equals(intermediaires.getAdresse().getTel()) || intermediaires.getAdresse().getTel() == null) {
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "PHONE  IS EMPTY", "PLEASE TO WRITE THE PHONE"));
//                return "";
//            }
//            if (refIntermediaire != null && refIntermediaire.getId() == null) {
//                if (refIntermediaireDao.findEntityHavingValue("code", refIntermediaire.getCode()) == null) {
//                    String raison = refIntermediaire.getRaisonSociale().toUpperCase();
//                    refIntermediaire.setRaisonSociale(raison);
//                    refIntermediaireDao.create(refIntermediaire);
//                }
//                refIntermediaire=refIntermediaireDao.findEntityHavingValue("code", refIntermediaire.getCode());
//            }
//            //recherche si le pays existe
//            if (intermediairesDao.IntermediaireByCompagnies(entreprise, refIntermediaire, intermediaires.getIdVille()) == null) {
//                
//                intermediaires.setDateCreation(new Date());
//                intermediaires.setIdEntreprise(entreprise);
//                intermediaires.setIdRefIntermediaire(refIntermediaire);
//                intermediairesDao.create(intermediaires);
//                
//                String[] detail = {entete[0], refIntermediaire.getCode() + "," + refIntermediaire.getRaisonSociale()};
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//            } else {
//                String[] detail = {entete[0], refIntermediaire.getCode() + "," + refIntermediaire.getRaisonSociale()};
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.doublons", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.Errdoublons", detail, myLoc);
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
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
//        this.reset();
//        return null;
//    }
//    
//    public String updateIntermediare() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "intermediaire", null, myLoc)};
//        try {
//            //teste si le pays existe
//            
//            if (intermediaires.getIdIntermediaire() != null) {
//                
//                refIntermediaireDao.update(refIntermediaire);
//                this.intermediaires.setIdRefIntermediaire(refIntermediaire);
//                intermediaires.setDateModification(new Date());
//                intermediairesDao.update(intermediaires);
//                String[] detail = {entete[0], refIntermediaire.getCode() + "," + refIntermediaire.getRaisonSociale()};
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
//                
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//            } else {
//                String[] detail = {entete[0], refIntermediaire.getCode() + "," + refIntermediaire.getRaisonSociale()};
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
//    public List<SelectItem> getTypeBureauEnum() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        
//        for (TypeBureau tb : TypeBureau.values()) {
//            if (tb.equals(TypeBureau.Autre)) {
//                for (OrclassTypeBureau item : typeBureauDao.listTypeBureauByAutre()) {
//                    items.add(new SelectItem(item, item.getLibelle()));
//                }
//                
//            } else {
//                if (typeBureauDao.findEntityHavingValue("typeBureau", tb) != null) {
//                    OrclassTypeBureau t = typeBureauDao.findEntityHavingValue("typeBureau", tb);
//                    items.add(new SelectItem(t, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, t.getTypeBureau().name(), null, myLoc)));
//                }
//            }
//            
//        }
//        
//        return items;
//    }
//    
//    public List<SelectItem> getTypeBureauForCreer() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        
//        for (TypeBureau tb : TypeBureau.values()) {
//            if (tb.equals(TypeBureau.Autre)) {
//                items.add(new SelectItem(tb, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, tb.name(), null, myLoc)));
//            } else {
//                if (typeBureauDao.findEntityHavingValue("typeBureau", tb) == null) {
//                    items.add(new SelectItem(tb, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, tb.name(), null, myLoc)));
//                }
//            }
//            
//        }
//        
//        return items;
//    }
//    
//    public void chargeIntermediaireByEntreprise() {
//        if (entreprise != null && entreprise.getIdEntreprise() != null) {
//            listeIntermediaire = intermediairesDao.listeIntermediaireByEntreprise(entreprise);
//        }
//    }
//    
//    public String addVille() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "adresse.ville", null, myLoc)};
//        try {
//            ville = new OrclassVille();
//            ville.setCode(code);
//            ville.setLibelle(libelle);
//            
//            if (villeDao.findEntityHavingValue("code", ville.getCode()) == null && villeDao.findEntityHavingValue("libelle", ville.getLibelle()) == null) {
//                ville.setIdEntreprise(entreprise);
//                villeDao.create(ville);
//                if (listeVille.contains(ville) == false) {
//                    listeVille.add(ville);
//                    this.getIntermediaires().setIdVille(ville);
//                }
//                
//                String[] detail = {entete[0], ville.getCode() + "," + ville.getLibelle()};
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//            } else {
//                String[] detail = {entete[0], ville.getCode() + "," + ville.getLibelle()};
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.doublons", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.Errdoublons", detail, myLoc);
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
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
//        code = "";
//        libelle = "";
//        this.selecteFormTab();
////        this.reset();
//        return null;
//    }
//    
//    public String updateVille() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "adrille.ville", null, myLoc)};
//        try {
//            //teste si le pays existe
//            ville.setCode(code);
//            ville.setLibelle(libelle);
//            if (ville.getId() != null) {
//                villeDao.update(ville);
//                String[] detail = {entete[0], ville.getCode() + "," + ville.getLibelle()};
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
//                
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//            } else {
//                String[] detail = {entete[0], ville.getCode() + "," + ville.getLibelle()};
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
//        code = "";
//        libelle = "";
////        this.reset();
//        return null;
//    }
//    
//    public String addTypeBufreau() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {exception.Success.INSERT_SUCCESS.name()};
//        try {
//            //recherche si le pays existe
//            if (typeBureauDao.findEntityHavingValue("libelle", typeBureau.getLibelle()) == null) {
//                typeBureau.setIdEntreprise(entreprise);
//                typeBureauDao.create(typeBureau);
//                if (listeTypeBureau.contains(typeBureau) == false) {
//                    listeTypeBureau.add(typeBureau);
//                    intermediaires.setIdTypeBureau(typeBureau);
//                }
//                
//                String[] detail = {entete[0], typeBureau.getTypeBureau() + "," + typeBureau.getLibelle()};
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//            } else {
//                String[] detail = {entete[0], typeBureau.getTypeBureau() + "," + typeBureau.getLibelle()};
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.doublons", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.Errdoublons", detail, myLoc);
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
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
////        this.reset();
//        return null;
//    }
//    
//    public String addSuspensionBrancheForIntermediare() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {exception.Success.OPERATION_SUCCESS.name()};
//        OrclassSuspensionBrancheIntemediaire sbint = null;
//        try {
//            
//            for (OrclassBranches br : brancheModel.getSource()) {
//                if (intermediairesSelect != null && intermediairesSelect.getIdIntermediaire() != null && suspensionBrancheIntemediaireDao.finKey(intermediairesSelect, entreprise, br) == null) {
//                    sbint = new OrclassSuspensionBrancheIntemediaire();
//                    sbint.setDateCreation(new Date());
//                    sbint.setIdBranche(br);
//                    sbint.setIdEntreprise(entreprise);
//                    sbint.setIdIntermediaire(intermediairesSelect);
//                    suspensionBrancheIntemediaireDao.create(sbint);
//                }
//            }
//            
//            for (OrclassBranches br : brancheModel.getTarget()) {
//                if (intermediairesSelect != null && intermediairesSelect.getIdIntermediaire() != null) {
//                    sbint = suspensionBrancheIntemediaireDao.finKey(intermediairesSelect, entreprise, br);
//                    if (sbint != null && sbint.getId() != null) {
//                        suspensionBrancheIntemediaireDao.delete(sbint);
//                    }
//                }
//            }
//            
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, exception.Success.OPERATION_SUCCESS.name(), null));
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
////        this.reset();
//        this.chargeListeBrancheAcces();
//        return null;
//    }
//    
//    public String onFlowProcess(FlowEvent event) {
////		if(skip) {
////			skip = false;	//reset in case user goes back
////			return "confirm";
////		}
////		else {
////			return event.getNewStep();
////		}
//        listeBranchesesAccees.clear();
//        brancheSelect = new OrclassBranches();
//        this.listeBranchesesAccees.addAll(brancheModel.getTarget());
//        PrimeFaces.current().ajax().update("form1:tabprincipal:pickListCategorie");
//        return event.getNewStep();
//    }
//    
//    public String addSuspensionCategorieForIntermediare() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {exception.Success.OPERATION_SUCCESS.name()};
//        OrclassSuspensionCategorieIntermediaire sbint = null;
//        try {
//            
//            for (OrclassCategories br : categorieModel.getSource()) {
//                if (intermediairesSelect != null && intermediairesSelect.getIdIntermediaire() != null && suspensionCategorieIntermediaireDao.finKey(intermediairesSelect, entreprise, br) == null) {
//                    sbint = new OrclassSuspensionCategorieIntermediaire();
//                    sbint.setDateCreation(new Date());
//                    sbint.setIdCategories(br);
//                    sbint.setIdEntreprise(entreprise);
//                    sbint.setIdIntermediaire(intermediairesSelect);
//                    suspensionCategorieIntermediaireDao.create(sbint);
//                }
//            }
//            
//            for (OrclassCategories br : categorieModel.getTarget()) {
//                if (intermediairesSelect != null && intermediairesSelect.getIdIntermediaire() != null) {
//                    sbint = suspensionCategorieIntermediaireDao.finKey(intermediairesSelect, entreprise, br);
//                    if (sbint != null && sbint.getId() != null) {
//                        suspensionCategorieIntermediaireDao.delete(sbint);
//                    }
//                }
//            }
//            
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, exception.Success.OPERATION_SUCCESS.name(), null));
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
////        this.reset();
//        return null;
//    }
//    
//    public String updateTyBureau() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {exception.Success.UPDATE_SUCCESS.name()};
//        try {
//            
//            if (typeBureau.getId() != null) {
//                villeDao.update(ville);
//                String[] detail = {entete[0], exception.Success.UPDATE_SUCCESS.name()};
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
//                
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//            } else {
//                String[] detail = {entete[0], typeBureau.getTypeBureau() + "," + typeBureau.getLibelle()};
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
////        this.reset();
//        return null;
//    }
//    
//    public String showDetailIntermediare(OrclassIntermediaires item) {
//        if (item != null && item.getIdIntermediaire() != null) {
//            this.setIntermediaires(item);
//        }
//        if (intermediaires != null && intermediaires.getIdIntermediaire() != null && intermediaires.getAdresse() != null) {
//            if (intermediaires.getAdresse().getBp() == null) {
//                intermediaires.getAdresse().setBp("");
//            }
//            if (intermediaires.getAdresse().getTel() == null) {
//                intermediaires.getAdresse().setTel("");
//                
//            }
//            if (intermediaires.getAdresse().getFax() == null) {
//                intermediaires.getAdresse().setFax("");
//            }
//        } else if (intermediaires != null && intermediaires.getIdIntermediaire() != null && intermediaires.getAdresse() == null) {
//            Adresse adresse = new Adresse();
//            intermediaires.setAdresse(adresse);
//           
//        }
//         this.setRefIntermediaire(intermediaires.getIdRefIntermediaire());
//        PrimeFaces.current().ajax().update("accord:form1");
//        
//        return null;
//    }
//    
//    public void reload() throws IOException {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
//    }
//    
//    public String showDetailVille(OrclassVille item) {
//        if (item != null && item.getId() != null) {
//            this.setCode(item.getCode());
//            this.setLibelle(item.getLibelle());
//            this.setVille(item);
//        }
//        return null;
//    }
//    
//    public void chargeModelBranche() {
//        List<OrclassBranches> listeBrancheByCompanie = new ArrayList<>();
//        brancheTarget = new ArrayList<>();
//        if (intermediairesSelect != null && intermediairesSelect.getIdIntermediaire() != null) {
//            listeBrancheByCompanie = branchesDao.listAllBrancheShowAllCompagnie();
//            listeBrancheByCompanie.addAll(branchesDao.listAllBrancheByCompagnie(entreprise));
//            /*
//            je charge tous les branches suspendu pour cert intermediaire
//             */
//            brancheSource = suspensionBrancheIntemediaireDao.listeBrancheSuspendueForIntermediaire(intermediairesSelect, entreprise);
//            if (!listeBrancheByCompanie.isEmpty()) {
//                for (OrclassBranches br : listeBrancheByCompanie) {
//                    if (brancheSource.contains(br) == false) {
//                        brancheTarget.add(br);
//                    }
//                }
//            }
//            brancheModel = new DualListModel<>(brancheSource, brancheTarget);
//        }
//    }
//    
//    public void chargeModelCategorie() {
//        List<OrclassCategories> listeCategoriesByCompanie = new ArrayList<>();
//        categorieTarget = new ArrayList<>();
//        if (intermediairesSelect != null && intermediairesSelect.getIdIntermediaire() != null && brancheSelect != null && brancheSelect.getIdBranche() != null) {
////            if (brancheSelect.getShowAllCompagnies() == Boolean.TRUE) {
////                listeCategoriesByCompanie = categoriesDao.listeCategorieByBrancheCreateBySysteme(brancheSelect);
////            } else {
//            listeCategoriesByCompanie = categoriesDao.listeCategorieByBranche(brancheSelect, entreprise);
////            }
//
//            categorieSource = suspensionCategorieIntermediaireDao.listeBrancheSuspendueForIntermediaire(intermediairesSelect, entreprise, brancheSelect);
//            for (OrclassCategories cat : listeCategoriesByCompanie) {
//                if (categorieSource.contains(cat) == Boolean.FALSE) {
//                    categorieTarget.add(cat);
//                }
//            }
//            categorieModel = new DualListModel<>(categorieSource, categorieTarget);
//        }
//    }
//    
//    public void iniatialElementException() {
//        intermediaires = new OrclassIntermediaires();
//        brancheSelect = new OrclassBranches();
//        brancheSource = new ArrayList<>();
//        brancheTarget = new ArrayList<>();
//        brancheModel = new DualListModel<>(brancheSource, brancheTarget);
//        categorieSource = new ArrayList<>();
//        categorieTarget = new ArrayList<>();
//        categorieModel = new DualListModel<>(categorieSource, categorieTarget);
//        intermediairesSelect = new OrclassIntermediaires();
//        if (i == 1 && j == 0) {
//            j = 1;
//            i = 0;
//            
//        } else if (j == 1 && i == 0) {
//            i = 1;
//            j = 0;
//        }
//        
//        
//    }
//     public void onTabChange(TabChangeEvent event) {
//          intermediaires = new OrclassIntermediaires();
//        brancheSelect = new OrclassBranches();
//        brancheSource = new ArrayList<>();
//        brancheTarget = new ArrayList<>();
//        brancheModel = new DualListModel<>(brancheSource, brancheTarget);
//        categorieSource = new ArrayList<>();
//        categorieTarget = new ArrayList<>();
//        categorieModel = new DualListModel<>(categorieSource, categorieTarget);
//        intermediairesSelect = new OrclassIntermediaires();
//          if (event.getTab().getId().equals("suspension")) {
//              suspension=Boolean.TRUE;
//          }else{
//              suspension=Boolean.FALSE;
//          }
//     }
//    
//    public void chargeListeBrancheAcces() {
//        if (brancheModel != null && !brancheModel.getTarget().isEmpty()) {
//            listeBranchesesAccees = brancheModel.getTarget();
//        }
//        brancheSelect = new OrclassBranches();
//    }
//    
//    public void onTransfer(TransferEvent event) {
//        StringBuilder builder = new StringBuilder();
//        for (Object item : event.getItems()) {
//            builder.append(((Object) item).toString()).append("<br />");
//        }
//        
//        FacesMessage msg = new FacesMessage();
//        msg.setSeverity(FacesMessage.SEVERITY_INFO);
//        msg.setSummary("Items Transferred");
//        msg.setDetail(builder.toString());
//        
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//    
//    public void onSelect(SelectEvent<Object> event) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
//    }
//    
//    public void onUnselect(UnselectEvent<Object> event) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
//    }
//    
//    public void onReorder() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
//    }
//    
////    public void onTabChange(TabChangeEvent event) {
////        FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getId());
////        FacesContext.getCurrentInstance().addMessage("msg", msg);
////    }
//    
//    public String showDetailTypeBureau() {
//        
//        return null;
//    }
//    
//    public OrclassIntermediaires getIntermediaires() {
//        return intermediaires;
//    }
//    
//    public void setIntermediaires(OrclassIntermediaires intermediaires) {
//        this.intermediaires = intermediaires;
//    }
//    
//    public List<OrclassTypeBureau> getListeTypeBureau() {
//        return listeTypeBureau;
//    }
//    
//    public void setListeTypeBureau(List<OrclassTypeBureau> listeTypeBureau) {
//        this.listeTypeBureau = listeTypeBureau;
//    }
//    
//    public List<OrclassVille> getListeVille() {
//        return listeVille;
//    }
//    
//    public void setListeVille(List<OrclassVille> listeVille) {
//        this.listeVille = listeVille;
//    }
//    
//    public OrclassVille getVille() {
//        return ville;
//    }
//    
//    public void setVille(OrclassVille ville) {
//        this.ville = ville;
//    }
//    
//    public OrclassTypeBureau getTypeBureau() {
//        return typeBureau;
//    }
//    
//    public void setTypeBureau(OrclassTypeBureau typeBureau) {
//        this.typeBureau = typeBureau;
//    }
//    
//    public List<OrclassIntermediaires> getListeIntermediaire() {
//        return listeIntermediaire;
//    }
//    
//    public void setListeIntermediaire(List<OrclassIntermediaires> listeIntermediaire) {
//        this.listeIntermediaire = listeIntermediaire;
//    }
//    
//    public List<OrclassIntermediaires> getFilteredIntermediaires() {
//        return filteredIntermediaires;
//    }
//    
//    public void setFilteredIntermediaires(List<OrclassIntermediaires> filteredIntermediaires) {
//        this.filteredIntermediaires = filteredIntermediaires;
//    }
//    
//    public List<OrclassVille> getFilteredVille() {
//        return filteredVille;
//    }
//    
//    public void setFilteredVille(List<OrclassVille> filteredVille) {
//        this.filteredVille = filteredVille;
//    }
//    
//    public List<OrclassTypeBureau> getFilteredTypeBureau() {
//        return filteredTypeBureau;
//    }
//    
//    public void setFilteredTypeBureau(List<OrclassTypeBureau> filteredTypeBureau) {
//        this.filteredTypeBureau = filteredTypeBureau;
//    }
//    
//    public String getCode() {
//        return code;
//    }
//    
//    public void setCode(String code) {
//        this.code = code;
//    }
//    
//    public String getLibelle() {
//        return libelle;
//    }
//    
//    public void setLibelle(String libelle) {
//        this.libelle = libelle;
//    }
//    
//    public OrclassTypeBureauDao getTypeBureauDao() {
//        return typeBureauDao;
//    }
//    
//    public void setTypeBureauDao(OrclassTypeBureauDao typeBureauDao) {
//        this.typeBureauDao = typeBureauDao;
//    }
//    
//    public DualListModel<OrclassBranches> getBrancheModel() {
//        return brancheModel;
//    }
//    
//    public void setBrancheModel(DualListModel<OrclassBranches> brancheModel) {
//        this.brancheModel = brancheModel;
//    }
//    
//    public DualListModel<OrclassCategories> getCategorieModel() {
//        return categorieModel;
//    }
//    
//    public void setCategorieModel(DualListModel<OrclassCategories> categorieModel) {
//        this.categorieModel = categorieModel;
//    }
//    
//    public OrclassBranches getBrancheSelect() {
//        return brancheSelect;
//    }
//    
//    public void setBrancheSelect(OrclassBranches brancheSelect) {
//        this.brancheSelect = brancheSelect;
//    }
//    
//    public OrclassIntermediaires getIntermediairesSelect() {
//        return intermediairesSelect;
//    }
//    
//    public void setIntermediairesSelect(OrclassIntermediaires intermediairesSelect) {
//        this.intermediairesSelect = intermediairesSelect;
//    }
//    
//    public List<OrclassBranches> getListeBranchesesAccees() {
//        return listeBranchesesAccees;
//    }
//    
//    public void setListeBranchesesAccees(List<OrclassBranches> listeBranchesesAccees) {
//        this.listeBranchesesAccees = listeBranchesesAccees;
//    }
//    
//    public int getI() {
//        return i;
//    }
//    
//    public void setI(int i) {
//        this.i = i;
//    }
//    
//    public int getJ() {
//        return j;
//    }
//    
//    public void setJ(int j) {
//        this.j = j;
//    }
//    
//    public OrclassIntermediaires getIntermediairesSelectForTable() {
//        return intermediairesSelectForTable;
//    }
//    
//    public void setIntermediairesSelectForTable(OrclassIntermediaires intermediairesSelectForTable) {
//        this.intermediairesSelectForTable = intermediairesSelectForTable;
//    }
//    
//    public OrclassRefIntermediaire getRefIntermediaire() {
//        return refIntermediaire;
//    }
//    
//    public void setRefIntermediaire(OrclassRefIntermediaire refIntermediaire) {
//        this.refIntermediaire = refIntermediaire;
//    }
//
//    public Boolean getSuspension() {
//        return suspension;
//    }
//
//    public void setSuspension(Boolean suspension) {
//        this.suspension = suspension;
//    }
//    
//    
//}
