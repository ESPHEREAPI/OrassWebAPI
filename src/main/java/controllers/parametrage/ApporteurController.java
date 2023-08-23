///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import Entreprise.OracleConnection;
//import dao.OrclassApporteurDao;
//import dao.OrclassCategoriesDao;
//import dao.OrclassCommission_Prime_ApporteurDao;
//import dao.OrclassGarantieDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassRefApporteurDao;
//import dao.OrclassTypeApporteurDao;
//import dao.OrclassVilleDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleParametrage;
//import java.io.IOException;
//import java.io.Serializable;
//import java.math.BigDecimal;
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
//import javax.inject.Named;
//import javax.faces.view.ViewScoped;
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.ConstraintViolationException;
//import modele.OrclassActions;
//import modele.OrclassApporteur;
//import modele.OrclassCategories;
//import modele.OrclassCommission_Prime_Apporteur;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassGarantie;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassRefApporteur;
//import modele.OrclassRefIntermediaire;
//import modele.OrclassTypeApporteur;
//import modele.OrclassUtilisateurs;
//import modele.OrclassVille;
//import org.primefaces.PrimeFaces;
//import org.primefaces.event.SelectEvent;
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
//@Named(value = "apporteurController")
//@ViewScoped
//public class ApporteurController implements Serializable {
//    
//    private static final Logger logger = LoggerFactory.getLogger(ApporteurController.class);
//    @EJB
//    OrclassTypeApporteurDao typeApporteurDao;
//    @EJB
//    OrclassApporteurDao apporteurDao;
//    OrclassApporteur apporteur;
//    @EJB
//    OrclassGarantieDao garantieDao;
//    OrclassGarantie garantie;
//    @EJB
//    OrclassCategoriesDao categoriesDao;
//    @EJB
//    OrclassVilleDao villeDao;
//    @EJB
//    OrclassCommission_Prime_ApporteurDao commission_Prime_ApporteurDao;
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    IDroitAcces serviceAccess;
//    @EJB
//    OrclassRefApporteurDao refApporteurDao;
//    OrclassRefApporteur refApporteur;
//
//    /**
//     * Creates a new instance of ApporteurController
//     */
//    private List<OrclassApporteur> filterApporteur = new ArrayList<>();
//    private List<OrclassTypeApporteur> filterTypeApporteur = new ArrayList<>();
//    private List<OrclassGarantie> filterGarantie = new ArrayList<>();
//    private List<OrclassCategories> filterCategories = new ArrayList<>();
//    private List<OrclassVille> filterVille = new ArrayList<>();
//    private List<OrclassApporteur> listApporteur = new ArrayList<>();
//    
//    private List<OrclassApporteur> listApporteurSelect = new ArrayList<>();
//    private List<OrclassVille> listVille = new ArrayList<>();
//    private List<OrclassTypeApporteur> listTypeApporteur = new ArrayList<>();
//    private OrclassEntreprises entreprise;
//    OrclassUtilisateurs user;
//    private List<OrclassCommission_Prime_Apporteur> filterPrimeApporteur = new ArrayList<>();
//    private List<OrclassCommission_Prime_Apporteur> listePrimeApporteur = new ArrayList<>();
//    OrclassCommission_Prime_Apporteur prime_Apporteur;
//    OrclassCommission_Prime_Apporteur prime_ApporteurSave;
//    OrclassCommission_Prime_Apporteur prime_Apporteur_existe;
//    OrclassTypeApporteur typeApporteur;
//    private List<OrclassCategories> listCategories = new ArrayList<>();
//    private List<OrclassGarantie> listGarantie = new ArrayList<>();
//    private OrclassCategories categoriesSelect;
//    
//    private OrclassCategories categories;
//    BigDecimal taux_apport = BigDecimal.ZERO;
//    BigDecimal taux_Gestion = BigDecimal.ZERO;
//    int i = 1; //ici il s agit de la gestion des apporteurs,
//    int j = 0;//j represente la gestion des primes apporteur;
//
//    String summary = "";
//    String msgdetail = "";
//    private OrclassMenus menu;
//    private OrclassModules module;
////    OrclassUtilisateurs user;
//
//    public ApporteurController() {
//        apporteur = new OrclassApporteur();
//        prime_Apporteur = new OrclassCommission_Prime_Apporteur();
//        typeApporteur = new OrclassTypeApporteur();
//        categories = new OrclassCategories();
//        garantie = new OrclassGarantie();
//        categoriesSelect = new OrclassCategories();
//        prime_ApporteurSave = new OrclassCommission_Prime_Apporteur();
//        prime_ApporteurSave.setIdApporteur(new OrclassApporteur());
//        refApporteur = new OrclassRefApporteur();
//        
//    }
//    
//    public List<String> completeText(String query) {
//        List<String> results = new ArrayList<>();
//        if (query != null && !query.trim().equals("")) {
//            results = refApporteurDao.getRefApporteurCodeWithFilters(query);
//        }
//        
//        return results;
//    }
//    
//    public void onItemSelect(SelectEvent<String> event) {
//        OrclassRefApporteur ref = null;
//        ref = refApporteurDao.findEntityHavingValue("code", event.getObject());
//        if (ref != null && ref.getId() != null) {
//            PrimeFaces.current().executeScript("PF('manageApporteurDialog').hide()");
//            this.setRefApporteur(ref);
//            apporteur = new OrclassApporteur();
//            PrimeFaces.current().ajax().update(":form1");
//            PrimeFaces.current().executeScript("PF('manageApporteurDialog').show()");
//            
//        }
//    }
//    
//    public void chargeGarantieByCategorie() {
//        if (categoriesSelect != null && categoriesSelect.getIdCategorie() != null) {
//            listGarantie = garantieDao.getallGarantieByCategorieByCompagnie(entreprise, categoriesSelect);
////            UIComponent ui = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:idprimeApporteurTable:garantie");
////            System.out.println("controllers.parametrage.ApporteurController.chargeGarantieByCategorie()! " + ui.getId());
//
//        }
//    }
//    
//    public void chargeApporteurByTypeApporteur(OrclassCommission_Prime_Apporteur item) {
//        if (item != null && item.getIdTypeApporteur() != null && item.getIdTypeApporteur().getId() != null) {
//            listApporteur = apporteurDao.apporteurByCompagnies(entreprise, item.getIdTypeApporteur());
//        }
//    }
//    
//    public void chargeTypeApporteur() {
//        listTypeApporteur = typeApporteurDao.listAllApporteurShowAllCompagnie();
//        listTypeApporteur.addAll(typeApporteurDao.listAllApporteurByCompagnie(entreprise));
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
//        menu = menusDao.findEntityHavingValue("code", "apporteur");
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//        
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        chargeElementApporteur();
//        this.updateTableApporteur();
//        listCategories = categoriesDao.listAllCategoriesShowAllCompagnie();
//        listCategories.addAll(categoriesDao.listAllCategorieByCompagnie(entreprise));
//        
//    }
//    
//    public void chargerApporteurByType(OrclassTypeApporteur item) {
//        if (item != null && item.getId() != null) {
//            
//            listApporteurSelect = apporteurDao.apporteurByCompagnies(entreprise, item);
//            if (apporteur == null) {
//                apporteur = new OrclassApporteur();
//            }
//            if (!listApporteurSelect.isEmpty()) {
//                PrimeFaces.current().ajax().update(":form1");
//            }
//            
//            PrimeFaces.current().executeScript("PF('tabprincipal').select(1)");
//            UIComponent ui = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:idprimeApporteurTable");
//            ui.setValueExpression("sortBy", null);
////                 PrimeFaces.current().ajax().update(":form2");
//        }
//    }
//    
//    public void chargeElementApporteur() {
////        listApporteur = apporteurDao.apporteurByCompagnies(entreprise);
//        listApporteur = (List<OrclassApporteur>) apporteurDao.apporteurByCompagnies(entreprise);
//        listVille = (List<OrclassVille>) villeDao.findAll();
//        listTypeApporteur = typeApporteurDao.listAllApporteurShowAllCompagnie();
//        listTypeApporteur.addAll(typeApporteurDao.listAllApporteurByCompagnie(entreprise));
//    }
//    
//    public void init() {
//        apporteur = new OrclassApporteur();
//        refApporteur = new OrclassRefApporteur();
//    }
//    
//    public void reset() {
//        
//        this.init();
//        
//        PrimeFaces.current().ajax().update(":form1:tabprincipal,:form1");
//        
//    }
//    
//    public void reload() throws IOException {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
//    }
//    
//    public void eventTab() {
//        if (apporteur == null) {
//            apporteur = new OrclassApporteur();
//            
//        }
//        if (prime_ApporteurSave == null) {
//            prime_ApporteurSave = new OrclassCommission_Prime_Apporteur();
//            prime_ApporteurSave.setIdApporteur(new OrclassApporteur());
//            prime_ApporteurSave.getIdApporteur().setIdVille(new OrclassVille());
//        }
//        if (i == 1 && j == 0) {
//            j = 1;
//            i = 0;
//        } else if (j == 1 && i == 0) {
//            j = 0;
//            i = 1;
//        }
//        UIComponent grid = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:grid1");
//        PrimeFaces.current().ajax().update(grid.getClientId());
//    }
//    
//    public void showDialog() {
//        if (apporteur == null) {
//            apporteur = new OrclassApporteur();
//        }
//        if (prime_ApporteurSave == null || prime_ApporteurSave.getIdApporteur() == null) {
//            prime_ApporteurSave = new OrclassCommission_Prime_Apporteur();
//            prime_ApporteurSave.setIdApporteur(new OrclassApporteur());
//        }
//        PrimeFaces.current().executeScript("PF('manageApporteurDialog').show();");
//        
//        UIComponent grid = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:grid1");
//        grid.clearInitialState();
//        
//        PrimeFaces.current().ajax().update(grid.getClientId());
//        
//        updateTableApporteur();
//    }
//    
//    public void showDialogPrimeApporteur() {
//        if (apporteur == null) {
//            apporteur = new OrclassApporteur();
//        }
//        if (prime_ApporteurSave == null || prime_ApporteurSave.getIdApporteur() == null) {
//            prime_ApporteurSave = new OrclassCommission_Prime_Apporteur();
//            prime_ApporteurSave.setIdApporteur(new OrclassApporteur());
//        }
//        PrimeFaces.current().executeScript("PF('commissionPrime').show();");
//        
//        UIComponent grid = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:grid1");
//        
//        PrimeFaces.current().executeScript("PF('idcommissionPrime').clearFilters();");
//        
//    }
//    
//    public void instancieApporteur() {
//        if (apporteur == null) {
//            apporteur = new OrclassApporteur();
//        }
//        if (prime_ApporteurSave == null || prime_ApporteurSave.getIdApporteur() == null) {
//            prime_ApporteurSave = new OrclassCommission_Prime_Apporteur();
//            prime_ApporteurSave.setIdApporteur(new OrclassApporteur());
//        }
////        categoriesSelect = new OrclassCategories();
//        UIComponent grid = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:grid1");
//        PrimeFaces.current().ajax().update(grid.getClientId());
//        PrimeFaces.current().ajax().update(":form1");
////          grid.clearInitialState();
//    }
//    
//    public void updateTableApporteur() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:idapporteurTable");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('apporteurTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
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
//    public void updateByMajApporteur() {
//        this.updateApporteur();
//        this.updateTableApporteur();
//    }
//    
//    public void chargeDialog() {
//        PrimeFaces.current().executeScript("PF('manageApporteurDialog').show();");
//    }
//    
//    public String addApporteur() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "apporteur", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
////        this.setApporteur(prime_ApporteurSave.getIdApporteur());
//
//        try {
//            if (apporteur.getIdTypeApporteur() == null || apporteur.getIdTypeApporteur().getId() == null) {
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "FEEDER TYPE IS EMPTY", "PLEASE SELECT THE FEEDER TYPE"));
//                return "";
//            }
//            if (apporteur.getIdVille() == null || apporteur.getIdVille().getId() == null) {
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "CITY IS EMPTY", "PLEASE SELECT THE CITY"));
//                return "";
//            }
//            
//            if (refApporteur != null && refApporteur.getId() == null) {
//                if (refApporteurDao.findEntityHavingValue("code", refApporteur.getCode()) == null) {
//                    String raison = refApporteur.getRaisonSociale().toUpperCase();
//                    refApporteur.setRaisonSociale(raison);
//                    
//                    refApporteurDao.create(refApporteur);
//                }
//                refApporteur = refApporteurDao.findEntityHavingValue("code", refApporteur.getCode());
//            }
//            if (apporteurDao.appoteurByCompagnies(entreprise, refApporteur, apporteur.getIdVille()) == null) {
//                
//                apporteur.setIdEntreprise(entreprise);
//                apporteur.setIdRefApporteur(refApporteur);
//                apporteur.setCodePrincipal(new BigInteger(refApporteur.getCode().trim()));
//                apporteurDao.create(apporteur);
//                listApporteur.add(apporteur);
//                
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
////                prime_ApporteurSave = new OrclassCommission_Prime_Apporteur();
////                prime_ApporteurSave.setIdApporteur(new OrclassApporteur());
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "OBJET IS EXISTS OR IS NULL"));
//                this.reset();
//                return null;
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "categorie", exception.Error.FATAL_ERROR + "", new Object[]{"classe"});
//            logger.error("Erreur Survenu", th);
//        }
//        this.reset();
//        this.updateTableApporteur();
//        this.chargeDialog();
//        return null;
//    }
//    
//    public String deleteApporteur() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "apporteur", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        OracleConnection con = new OracleConnection();
//        try {
//            
//            if (apporteur != null && apporteur.getIdApporteur() != null) {
//                
//                apporteurDao.delete(apporteur);
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.delete.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DELETE_ERROR + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "apporteur", exception.Error.FATAL_ERROR + "", new Object[]{"apporteur"});
//            logger.error("Erreur Survenu", th);
//        }
////       lisClass.remove(classes);
//        listApporteur.remove(apporteur);
//        this.reset();
//        this.updateTableApporteur();
////        this.chargeDialog();
//        return null;
//    }
//    
//    public void chargeCommissionPrime() {
//        
//        if (categoriesSelect != null && categoriesSelect.getIdCategorie() != null) {
//            listePrimeApporteur = commission_Prime_ApporteurDao.getPrimeCommissionByCategories(categoriesSelect, entreprise);
//            chargeGarantieByCategorie();
//            this.prime_Apporteur.setIdCategories(categoriesSelect);
//            if (listePrimeApporteur == null || listePrimeApporteur.isEmpty()) {
//                listePrimeApporteur = new ArrayList<>();
//                listePrimeApporteur.add(prime_Apporteur);
//            } else {
//                prime_Apporteur = new OrclassCommission_Prime_Apporteur();
//                prime_Apporteur.setIdCategories(categoriesSelect);
//                listePrimeApporteur.add(prime_Apporteur);
//            }
//            
//            this.updateTableCommissionPrime();
//            System.out.println("taille typeapporteiur: " + listTypeApporteur.size());
//            System.out.println("taille garantie: " + listGarantie.size());
//            
//            PrimeFaces.current().ajax().update(":form1:tabprincipal:idprimeApporteurTable");
//            PrimeFaces.current().ajax().update(":form1");
//            PrimeFaces.current().executeScript("PF('tabprincipal').select(1)");
//            
//        }
//        
//    }
//    
//    public String addPrimeApporteur() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "commission_apporteur", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            OrclassCommission_Prime_Apporteur prime;
//            if (prime_Apporteur != null && prime_Apporteur.getIdCategories().getIdCategorie() != null && prime_Apporteur.getId() == null) {
//                
//                if (prime_Apporteur.getIdApporteur() == null && prime_Apporteur.getIdGarantie() == null) {
//                    prime = commission_Prime_ApporteurDao.getPrimeCommissionByTypeApporteur(prime_Apporteur.getIdTypeApporteur(), prime_Apporteur.getIdCategories(), entreprise);
//                    if (prime == null) {
//                        prime = new OrclassCommission_Prime_Apporteur();
//                        prime.setActif(Boolean.TRUE);
//                        prime.setDateCreation(new Date());
//                        prime.setIdCategories(prime_Apporteur.getIdCategories());
//                        prime.setIdEntreprise(entreprise);
//                        prime.setIdTypeApporteur(prime_Apporteur.getIdTypeApporteur());
//                        prime.setTaux_apport(prime_Apporteur.getTaux_apport());
//                        prime.setTaux_gestion(prime_Apporteur.getTaux_gestion());
//                        commission_Prime_ApporteurDao.create(prime);
//                        listePrimeApporteur = commission_Prime_ApporteurDao.getPrimeCommissionByCategories(prime_Apporteur.getIdCategories(), entreprise);
//                        this.setCategoriesSelect(prime_Apporteur.getIdCategories());
//                        prime_Apporteur = new OrclassCommission_Prime_Apporteur();
//                        prime_Apporteur.setIdCategories(categoriesSelect);
//                        listePrimeApporteur.add(prime_Apporteur);
//                        this.updateTableCommissionPrime();
//                    } else if (prime != null && prime.getId() != null) {
//                        prime_Apporteur_existe = new OrclassCommission_Prime_Apporteur();
//                        this.setPrime_Apporteur_existe(prime);
//                        if (prime_Apporteur.getTaux_apport().doubleValue() != prime.getTaux_apport().doubleValue() || prime.getTaux_gestion().doubleValue() != prime_Apporteur.getTaux_gestion().doubleValue()) {
//                            prime_Apporteur_existe.setActif(Boolean.FALSE);
//                            prime_Apporteur_existe.setDateModification(new Date());
//                            commission_Prime_ApporteurDao.update(prime_Apporteur_existe);
//                            
//                            prime = new OrclassCommission_Prime_Apporteur();
//                            prime.setActif(Boolean.TRUE);
//                            prime.setDateCreation(new Date());
//                            prime.setIdCategories(prime_Apporteur.getIdCategories());
//                            prime.setIdEntreprise(entreprise);
//                            prime.setIdTypeApporteur(prime_Apporteur.getIdTypeApporteur());
//                            prime.setTaux_apport(prime_Apporteur.getTaux_apport());
//                            prime.setTaux_gestion(prime_Apporteur.getTaux_gestion());
//                            commission_Prime_ApporteurDao.create(prime);
//                            listePrimeApporteur = commission_Prime_ApporteurDao.getPrimeCommissionByCategories(prime_Apporteur.getIdCategories(), entreprise);
//                            this.setCategoriesSelect(prime_Apporteur.getIdCategories());
//                            prime_Apporteur = new OrclassCommission_Prime_Apporteur();
//                            prime_Apporteur.setIdCategories(categoriesSelect);
//                            listePrimeApporteur.add(prime_Apporteur);
//                            this.updateTableCommissionPrime();
//                        } else if (prime_Apporteur.getTaux_apport().doubleValue() == prime.getTaux_apport().doubleValue() && prime.getTaux_gestion().doubleValue() == prime_Apporteur.getTaux_gestion().doubleValue()) {
//                            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "PLEASE CHANGE VALUE OF CONTRIBUTION OR MANAGEMENT"));
//                            prime_Apporteur = new OrclassCommission_Prime_Apporteur();
//                            prime_Apporteur.setIdCategories(prime_Apporteur.getIdCategories());
//                            listePrimeApporteur.add(prime_Apporteur);
//                            PrimeFaces.current().ajax().update(":form1");
//                            PrimeFaces.current().executeScript("PF('tabprincipal').select(1)");
//                            return null;
//                            
//                        }
//                    }
//                } else if (prime_Apporteur.getIdApporteur() != null && prime_Apporteur.getIdGarantie() == null) {
//                    prime = commission_Prime_ApporteurDao.getPrimeCommissionByApporteur(prime_Apporteur.getIdApporteur(), prime_Apporteur.getIdCategories(), entreprise);
//                    if (prime == null) {
//                        prime = new OrclassCommission_Prime_Apporteur();
//                        prime.setActif(Boolean.TRUE);
//                        prime.setDateCreation(new Date());
//                        prime.setIdCategories(prime_Apporteur.getIdCategories());
//                        prime.setIdEntreprise(entreprise);
//                        prime.setIdTypeApporteur(prime_Apporteur.getIdTypeApporteur());
//                        prime.setIdApporteur(prime_Apporteur.getIdApporteur());
//                        prime.setTaux_apport(prime_Apporteur.getTaux_apport());
//                        prime.setTaux_gestion(prime_Apporteur.getTaux_gestion());
//                        commission_Prime_ApporteurDao.create(prime);
//                        listePrimeApporteur = commission_Prime_ApporteurDao.getPrimeCommissionByCategories(prime_Apporteur.getIdCategories(), entreprise);
//                        this.setCategoriesSelect(prime_Apporteur.getIdCategories());
//                        prime_Apporteur = new OrclassCommission_Prime_Apporteur();
//                        prime_Apporteur.setIdCategories(categoriesSelect);
//                        listePrimeApporteur.add(prime_Apporteur);
//                        this.updateTableCommissionPrime();
//                    } else if (prime != null && prime.getId() != null) {
//                        prime_Apporteur_existe = new OrclassCommission_Prime_Apporteur();
//                        this.setPrime_Apporteur_existe(prime);
//                        if (prime_Apporteur.getTaux_apport().doubleValue() != prime.getTaux_apport().doubleValue() || prime.getTaux_gestion().doubleValue() != prime_Apporteur.getTaux_gestion().doubleValue()) {
//                            prime_Apporteur_existe.setActif(Boolean.FALSE);
//                            prime_Apporteur_existe.setDateModification(new Date());
//                            commission_Prime_ApporteurDao.update(prime_Apporteur_existe);
//                            
//                            prime = new OrclassCommission_Prime_Apporteur();
//                            prime.setActif(Boolean.TRUE);
//                            prime.setDateCreation(new Date());
//                            prime.setIdCategories(prime_Apporteur.getIdCategories());
//                            prime.setIdEntreprise(entreprise);
//                            prime.setIdTypeApporteur(prime_Apporteur.getIdTypeApporteur());
//                            prime.setIdApporteur(prime_Apporteur.getIdApporteur());
//                            prime.setTaux_apport(prime_Apporteur.getTaux_apport());
//                            prime.setTaux_gestion(prime_Apporteur.getTaux_gestion());
//                            commission_Prime_ApporteurDao.create(prime);
//                            listePrimeApporteur = commission_Prime_ApporteurDao.getPrimeCommissionByCategories(prime_Apporteur.getIdCategories(), entreprise);
//                            this.setCategoriesSelect(prime_Apporteur.getIdCategories());
//                            prime_Apporteur = new OrclassCommission_Prime_Apporteur();
//                            prime_Apporteur.setIdCategories(categoriesSelect);
//                            listePrimeApporteur.add(prime_Apporteur);
//                            this.updateTableCommissionPrime();
//                        } else if (prime_Apporteur.getTaux_apport().doubleValue() == prime.getTaux_apport().doubleValue() && prime.getTaux_gestion().doubleValue() == prime_Apporteur.getTaux_gestion().doubleValue()) {
//                            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "PLEASE CHANGE VALUE OF CONTRIBUTION OR MANAGEMENT"));
//                            prime_Apporteur = new OrclassCommission_Prime_Apporteur();
//                            prime_Apporteur.setIdCategories(prime_Apporteur.getIdCategories());
//                            listePrimeApporteur.add(prime_Apporteur);
//                            PrimeFaces.current().ajax().update(":form1");
//                            PrimeFaces.current().executeScript("PF('tabprincipal').select(1)");
//                            return null;
//                            
//                        }
//                    }
//                } else if ((prime_Apporteur.getIdApporteur() != null && prime_Apporteur.getIdApporteur().getIdApporteur() != null) && (prime_Apporteur.getIdGarantie() != null && prime_Apporteur.getIdGarantie().getId() != null)) {
//                    prime = commission_Prime_ApporteurDao.getPrimeCommissionByApporteurHaveGarantie(prime_Apporteur.getIdApporteur(), prime_Apporteur.getIdCategories(), prime_Apporteur.getIdGarantie(), entreprise);
//                    if (prime == null) {
//                        prime = new OrclassCommission_Prime_Apporteur();
//                        prime.setActif(Boolean.TRUE);
//                        prime.setDateCreation(new Date());
//                        prime.setIdCategories(prime_Apporteur.getIdCategories());
//                        prime.setIdEntreprise(entreprise);
//                        prime.setIdTypeApporteur(prime_Apporteur.getIdTypeApporteur());
//                        prime.setIdGarantie(prime_Apporteur.getIdGarantie());
//                        prime.setIdApporteur(prime_Apporteur.getIdApporteur());
//                        prime.setTaux_apport(prime_Apporteur.getTaux_apport());
//                        prime.setTaux_gestion(prime_Apporteur.getTaux_gestion());
//                        commission_Prime_ApporteurDao.create(prime);
//                        listePrimeApporteur = commission_Prime_ApporteurDao.getPrimeCommissionByCategories(prime_Apporteur.getIdCategories(), entreprise);
//                        this.setCategoriesSelect(prime_Apporteur.getIdCategories());
//                        prime_Apporteur = new OrclassCommission_Prime_Apporteur();
//                        prime_Apporteur.setIdCategories(categoriesSelect);
//                        listePrimeApporteur.add(prime_Apporteur);
//                        this.updateTableCommissionPrime();
//                    } else if (prime != null && prime.getId() != null) {
//                        prime_Apporteur_existe = new OrclassCommission_Prime_Apporteur();
//                        this.setPrime_Apporteur_existe(prime);
//                        if (prime_Apporteur.getTaux_apport().doubleValue() != prime.getTaux_apport().doubleValue() || prime.getTaux_gestion().doubleValue() != prime_Apporteur.getTaux_gestion().doubleValue()) {
//                            prime_Apporteur_existe.setActif(Boolean.FALSE);
//                            prime_Apporteur_existe.setDateModification(new Date());
//                            commission_Prime_ApporteurDao.update(prime_Apporteur_existe);
//                            
//                            prime = new OrclassCommission_Prime_Apporteur();
//                            prime.setActif(Boolean.TRUE);
//                            prime.setDateCreation(new Date());
//                            prime.setIdCategories(prime_Apporteur.getIdCategories());
//                            prime.setIdEntreprise(entreprise);
//                            prime.setIdTypeApporteur(prime_Apporteur.getIdTypeApporteur());
//                            prime.setIdApporteur(prime_Apporteur.getIdApporteur());
//                            prime.setIdGarantie(prime_Apporteur.getIdGarantie());
//                            prime.setTaux_apport(prime_Apporteur.getTaux_apport());
//                            prime.setTaux_gestion(prime_Apporteur.getTaux_gestion());
//                            commission_Prime_ApporteurDao.create(prime);
//                            listePrimeApporteur = commission_Prime_ApporteurDao.getPrimeCommissionByCategories(prime_Apporteur.getIdCategories(), entreprise);
//                            this.setCategoriesSelect(prime_Apporteur.getIdCategories());
//                            prime_Apporteur = new OrclassCommission_Prime_Apporteur();
//                            prime_Apporteur.setIdCategories(categoriesSelect);
//                            listePrimeApporteur.add(prime_Apporteur);
//                            this.updateTableCommissionPrime();
//                        } else if (prime_Apporteur.getTaux_apport().doubleValue() == prime.getTaux_apport().doubleValue() && prime.getTaux_gestion().doubleValue() == prime_Apporteur.getTaux_gestion().doubleValue()) {
//                            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "PLEASE CHANGE VALUE OF CONTRIBUTION OR MANAGEMENT"));
//                            prime_Apporteur = new OrclassCommission_Prime_Apporteur();
//                            prime_Apporteur.setIdCategories(prime_Apporteur.getIdCategories());
//                            listePrimeApporteur.add(prime_Apporteur);
//                            PrimeFaces.current().ajax().update(":form1");
//                            PrimeFaces.current().executeScript("PF('tabprincipal').select(1)");
//                            return null;
//                        }
//                    }
//                } else if ((prime_Apporteur.getIdApporteur() == null || prime_Apporteur.getIdApporteur().getIdApporteur() == null) && (prime_Apporteur.getIdGarantie() != null && prime_Apporteur.getIdGarantie().getId() != null)) {
//                    prime = commission_Prime_ApporteurDao.getPrimeCommissionByTypeApporteurHaveGarantie(prime_Apporteur.getIdTypeApporteur(), prime_Apporteur.getIdCategories(), prime_Apporteur.getIdGarantie(), entreprise);
//                    if (prime == null) {
//                        prime = new OrclassCommission_Prime_Apporteur();
//                        prime.setActif(Boolean.TRUE);
//                        prime.setDateCreation(new Date());
//                        prime.setIdCategories(prime_Apporteur.getIdCategories());
//                        prime.setIdEntreprise(entreprise);
//                        prime.setIdTypeApporteur(prime_Apporteur.getIdTypeApporteur());
//                        prime.setIdGarantie(prime_Apporteur.getIdGarantie());
//                        prime.setTaux_apport(prime_Apporteur.getTaux_apport());
//                        prime.setTaux_gestion(prime_Apporteur.getTaux_gestion());
//                        commission_Prime_ApporteurDao.create(prime);
//                        listePrimeApporteur = commission_Prime_ApporteurDao.getPrimeCommissionByCategories(prime_Apporteur.getIdCategories(), entreprise);
//                        this.setCategoriesSelect(prime_Apporteur.getIdCategories());
//                        prime_Apporteur = new OrclassCommission_Prime_Apporteur();
//                        prime_Apporteur.setIdCategories(categoriesSelect);
//                        listePrimeApporteur.add(prime_Apporteur);
//                        this.updateTableCommissionPrime();
//                    } else if (prime != null && prime.getId() != null) {
//                        prime_Apporteur_existe = new OrclassCommission_Prime_Apporteur();
//                        this.setPrime_Apporteur_existe(prime);
//                        if (prime_Apporteur.getTaux_apport().doubleValue() != prime.getTaux_apport().doubleValue() || prime.getTaux_gestion().doubleValue() != prime_Apporteur.getTaux_gestion().doubleValue()) {
//                            prime_Apporteur_existe.setActif(Boolean.FALSE);
//                            prime_Apporteur_existe.setDateModification(new Date());
//                            commission_Prime_ApporteurDao.update(prime_Apporteur_existe);
//                            
//                            prime = new OrclassCommission_Prime_Apporteur();
//                            prime.setActif(Boolean.TRUE);
//                            prime.setDateCreation(new Date());
//                            prime.setIdCategories(prime_Apporteur.getIdCategories());
//                            prime.setIdEntreprise(entreprise);
//                            prime.setIdTypeApporteur(prime_Apporteur.getIdTypeApporteur());
//                            prime.setIdGarantie(prime_Apporteur.getIdGarantie());
//                            prime.setTaux_apport(prime_Apporteur.getTaux_apport());
//                            prime.setTaux_gestion(prime_Apporteur.getTaux_gestion());
//                            commission_Prime_ApporteurDao.create(prime);
//                            listePrimeApporteur = commission_Prime_ApporteurDao.getPrimeCommissionByCategories(prime_Apporteur.getIdCategories(), entreprise);
//                            this.setCategoriesSelect(prime_Apporteur.getIdCategories());
//                            prime_Apporteur = new OrclassCommission_Prime_Apporteur();
//                            prime_Apporteur.setIdCategories(categoriesSelect);
//                            listePrimeApporteur.add(prime_Apporteur);
//                            this.updateTableCommissionPrime();
//                        } else if (prime_Apporteur.getTaux_apport().doubleValue() == prime.getTaux_apport().doubleValue() && prime.getTaux_gestion().doubleValue() == prime_Apporteur.getTaux_gestion().doubleValue()) {
//                            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "PLEASE CHANGE VALUE OF CONTRIBUTION OR MANAGEMENT"));
//                            prime_Apporteur = new OrclassCommission_Prime_Apporteur();
//                            prime_Apporteur.setIdCategories(categoriesSelect);
//                            listePrimeApporteur.add(prime_Apporteur);
//                            PrimeFaces.current().ajax().update(":form1");
//                            PrimeFaces.current().executeScript("PF('tabprincipal').select(1)");
//                            return null;
//                        }
//                    }
//                }
//                
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "commission_apporteur", exception.Error.FATAL_ERROR + "", new Object[]{"garantie"});
//            logger.error("Erreur Survenu", th);
//        }
//        PrimeFaces.current().ajax().update(":form1");
//        PrimeFaces.current().executeScript("PF('tabprincipal').select(1)");
//        this.updateTableCommissionPrime();
//        this.reverseListe();
//        return null;
//    }
//    
//    public void removePrimeApporteurByListe() {
//        this.setCategoriesSelect(prime_Apporteur.getIdCategories());
//        listePrimeApporteur.remove(prime_Apporteur);
//        prime_Apporteur = new OrclassCommission_Prime_Apporteur();
//        prime_Apporteur.setIdCategories(categoriesSelect);
//        listePrimeApporteur.add(prime_Apporteur);
//        this.updateTableCommissionPrime();
//        PrimeFaces.current().ajax().update("form1");
//        PrimeFaces.current().executeScript("PF('tabprincipal').select(1)");
//        
//    }
//    
//    public void updateTableCommissionPrime() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:idprimeApporteurTable");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('primeApporteurTable').clearFilters();");
//        try {
//            this.reverseListe();// ce try permet d eviter une exception lorsque la liste a un seul element
//        } catch (Exception e) {
//        }
//        
//    }
//    
//    public String deleteCommissionPrime() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "commission_apporteur", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (prime_Apporteur != null && prime_Apporteur.getId() != null) {
//                
//                commission_Prime_ApporteurDao.delete(prime_Apporteur);
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.delete.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                listePrimeApporteur = commission_Prime_ApporteurDao.getPrimeCommissionByCategories(prime_Apporteur.getIdCategories(), entreprise);
//                prime_Apporteur = new OrclassCommission_Prime_Apporteur();
//                prime_Apporteur.setIdCategories(categoriesSelect);
//                if (listePrimeApporteur.isEmpty()) {
//                    listePrimeApporteur = new ArrayList<>();
//                }
//                listePrimeApporteur.add(prime_Apporteur);
//                
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "commission_apporteur", exception.Error.FATAL_ERROR + "", new Object[]{"commission_apporteur"});
//            logger.error("Erreur Survenu", th);
//        }
//        
//        this.updateTableCommissionPrime();
//        PrimeFaces.current().executeScript("PF('tabprincipal').select(1)");
//        return null;
//    }
//    
//    public String updateApporteur() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "apporteur", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
////            this.setApporteur(prime_ApporteurSave.getIdApporteur());
//
//            if (apporteur != null && apporteur.getIdApporteur() != null) {
//                String raison = refApporteur.getRaisonSociale();
//                refApporteur.setRaisonSociale(raison.toUpperCase());
//                refApporteurDao.update(refApporteur);
//                
//                apporteur.setDateModification(new Date());
//                apporteur.setCodePrincipal(new BigInteger(refApporteur.getCode().trim()));
//                apporteurDao.update(apporteur);
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
////                prime_ApporteurSave = new OrclassCommission_Prime_Apporteur();
////                prime_ApporteurSave.setIdApporteur(new OrclassApporteur());
//
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "apporteur", exception.Error.FATAL_ERROR + "", new Object[]{"apporteur"});
//            logger.error("Erreur Survenu", th);
//        }
//        reset();
//        this.updateTableApporteur();
//        
//        return null;
//    }
//    
//    public void shows(OrclassApporteur item) {
//
////        if (prime_ApporteurSave.getIdApporteur()!=null && prime_ApporteurSave.getIdApporteur().getIdApporteur()!=null && (apporteur==null && apporteur.getIdApporteur()==null) ) {
//        this.setApporteur(prime_ApporteurSave.getIdApporteur());
////        }
//
//        UIComponent grid = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:grid1");
//        PrimeFaces.current().ajax().update(grid.getClientId());
//    }
//    
//    public void shows() {
//        
//        UIComponent grid = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:grid1");
//        PrimeFaces.current().ajax().update(grid.getClientId());
//    }
//    
//    public void reverseListe() {
//        
//        List<OrclassCommission_Prime_Apporteur> result = new ArrayList<>();
//        for (int i = listePrimeApporteur.size() - 1; i >= 0; i--) {
//            result.add(listePrimeApporteur.get(i));
//        }
//        
//        this.setListePrimeApporteur(result);
////        this.updateTableGarantie();
//    }
//    
//    public OrclassApporteur getApporteur() {
////        if (apporteur!=null && apporteur.getIdApporteur()!=null ) {
////            this.setRefApporteur(apporteur.getIdRefApporteur());
//////            PrimeFaces.current().ajax().update(":form1");
//////            PrimeFaces.current().executeScript("PF('manageApporteurDialog').show()");
////        }
//        return apporteur;
//    }
//    
//    public void setApporteur(OrclassApporteur apporteur) {
//        this.apporteur = apporteur;
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
//    public OrclassCommission_Prime_ApporteurDao getCommission_Prime_ApporteurDao() {
//        return commission_Prime_ApporteurDao;
//    }
//    
//    public void setCommission_Prime_ApporteurDao(OrclassCommission_Prime_ApporteurDao commission_Prime_ApporteurDao) {
//        this.commission_Prime_ApporteurDao = commission_Prime_ApporteurDao;
//    }
//    
//    public List<OrclassApporteur> getFilterApporteur() {
//        return filterApporteur;
//    }
//    
//    public void setFilterApporteur(List<OrclassApporteur> filterApporteur) {
//        this.filterApporteur = filterApporteur;
//    }
//    
//    public List<OrclassTypeApporteur> getFilterTypeApporteur() {
//        return filterTypeApporteur;
//    }
//    
//    public void setFilterTypeApporteur(List<OrclassTypeApporteur> filterTypeApporteur) {
//        this.filterTypeApporteur = filterTypeApporteur;
//    }
//    
//    public List<OrclassGarantie> getFilterGarantie() {
//        return filterGarantie;
//    }
//    
//    public void setFilterGarantie(List<OrclassGarantie> filterGarantie) {
//        this.filterGarantie = filterGarantie;
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
//    public List<OrclassApporteur> getListApporteur() {
//        return listApporteur;
//    }
//    
//    public void setListApporteur(List<OrclassApporteur> listApporteur) {
//        this.listApporteur = listApporteur;
//    }
//    
//    public List<OrclassVille> getListVille() {
//        return listVille;
//    }
//    
//    public void setListVille(List<OrclassVille> listVille) {
//        this.listVille = listVille;
//    }
//    
//    public List<OrclassTypeApporteur> getListTypeApporteur() {
//        return listTypeApporteur;
//    }
//    
//    public void setListTypeApporteur(List<OrclassTypeApporteur> listTypeApporteur) {
//        this.listTypeApporteur = listTypeApporteur;
//    }
//    
//    public List<OrclassCommission_Prime_Apporteur> getFilterPrimeApporteur() {
//        return filterPrimeApporteur;
//    }
//    
//    public void setFilterPrimeApporteur(List<OrclassCommission_Prime_Apporteur> filterPrimeApporteur) {
//        this.filterPrimeApporteur = filterPrimeApporteur;
//    }
//    
//    public OrclassTypeApporteur getTypeApporteur() {
//        return typeApporteur;
//    }
//    
//    public void setTypeApporteur(OrclassTypeApporteur typeApporteur) {
//        this.typeApporteur = typeApporteur;
//    }
//    
//    public List<OrclassCategories> getListCategories() {
//        return listCategories;
//    }
//    
//    public void setListCategories(List<OrclassCategories> listCategories) {
//        this.listCategories = listCategories;
//    }
//    
//    public List<OrclassGarantie> getListGarantie() {
//        return listGarantie;
//    }
//    
//    public void setListGarantie(List<OrclassGarantie> listGarantie) {
//        this.listGarantie = listGarantie;
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
//    public BigDecimal getTaux_apport() {
//        return taux_apport;
//    }
//    
//    public void setTaux_apport(BigDecimal taux_apport) {
//        this.taux_apport = taux_apport;
//    }
//    
//    public BigDecimal getTaux_Gestion() {
//        return taux_Gestion;
//    }
//    
//    public void setTaux_Gestion(BigDecimal taux_Gestion) {
//        this.taux_Gestion = taux_Gestion;
//    }
//    
//    public OrclassCommission_Prime_Apporteur getPrime_Apporteur() {
//        return prime_Apporteur;
//    }
//    
//    public void setPrime_Apporteur(OrclassCommission_Prime_Apporteur prime_Apporteur) {
//        this.prime_Apporteur = prime_Apporteur;
//    }
//    
//    public OrclassCommission_Prime_Apporteur getPrime_Apporteur_existe() {
//        return prime_Apporteur_existe;
//    }
//    
//    public void setPrime_Apporteur_existe(OrclassCommission_Prime_Apporteur prime_Apporteur_existe) {
//        this.prime_Apporteur_existe = prime_Apporteur_existe;
//    }
//    
//    public List<OrclassCommission_Prime_Apporteur> getListePrimeApporteur() {
//        return listePrimeApporteur;
//    }
//    
//    public void setListePrimeApporteur(List<OrclassCommission_Prime_Apporteur> listePrimeApporteur) {
//        this.listePrimeApporteur = listePrimeApporteur;
//    }
//    
//    public OrclassCategories getCategoriesSelect() {
//        return categoriesSelect;
//    }
//    
//    public void setCategoriesSelect(OrclassCategories categoriesSelect) {
//        this.categoriesSelect = categoriesSelect;
//    }
//    
//    public List<OrclassApporteur> getListApporteurSelect() {
//        return listApporteurSelect;
//    }
//    
//    public void setListApporteurSelect(List<OrclassApporteur> listApporteurSelect) {
//        this.listApporteurSelect = listApporteurSelect;
//    }
//    
//    public OrclassCommission_Prime_Apporteur getPrime_ApporteurSave() {
//        return prime_ApporteurSave;
//    }
//    
//    public void setPrime_ApporteurSave(OrclassCommission_Prime_Apporteur prime_ApporteurSave) {
//        this.prime_ApporteurSave = prime_ApporteurSave;
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
//    public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.commission_apporteur.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);
//            
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public Boolean accessAjouter() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.commission_apporteur.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.commission_apporteur.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
//            System.out.println("accees Mpdifier :" + serviceAccess.accessAction(user, action, menu));
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public Boolean accessSupprimer() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.commission_apporteur.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer.name(), fon);
//            System.out.println("accees supprimer :" + serviceAccess.accessAction(user, action, menu));
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public Boolean accessAjouterCommision() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.commission_apporteur.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.ajouter_commission_Apporteur.name(), fon);
//            System.out.println("ajouter commission apporteur :" + serviceAccess.accessAction(user, action, menu));
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public Boolean accessAfficherList() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.commission_apporteur.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.afficher_liste.name(), fon);
//            System.out.println("afficherListe :" + serviceAccess.accessAction(user, action, menu));
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public OrclassRefApporteur getRefApporteur() {
//        return refApporteur;
//    }
//    
//    public void setRefApporteur(OrclassRefApporteur refApporteur) {
//        this.refApporteur = refApporteur;
//    }
//    
//}
