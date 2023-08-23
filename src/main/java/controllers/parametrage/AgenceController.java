///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import static Entreprise.EntrepriseServices.logger;
//import dao.OrclassCategoriesDao;
//import dao.OrclassCommission_Prime_IntermediaireDao;
//import dao.OrclassGarantieDao;
//import dao.OrclassIntermediairesDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassTypeBureauDao;
//import dao.OrclassUtilisateursDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleParametrage;
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
//import javax.inject.Named;
//import javax.faces.view.ViewScoped;
//import javax.validation.ConstraintViolationException;
//import modele.OrclassActions;
//import modele.OrclassCategories;
//import modele.OrclassCommission_Prime_Intermediaire;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassGarantie;
//import modele.OrclassIntermediaires;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassTypeBureau;
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
//@Named(value = "agenceController")
//@ViewScoped
//public class AgenceController implements Serializable {
//
//    @EJB
//    OrclassIntermediairesDao intermediairesDao;
//    @EJB
//    OrclassUtilisateursDao utilisateursDao;
//    @EJB
//    OrclassTypeBureauDao typeBureauDao;
//    @EJB
//    OrclassGarantieDao garantieDao;
//    @EJB
//    OrclassCommission_Prime_IntermediaireDao prime_IntermediaireDao;
//    @EJB
//    OrclassCategoriesDao categoriesDao;
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    IDroitAcces serviceAccess;
//
//    /**
//     * Creates a new instance of AgenceController
//     */
//    private List<OrclassIntermediaires> listeIntermediaire = new ArrayList<>();
//    private OrclassEntreprises entreprise;
//    OrclassCategories categories;
//    private List<OrclassCommission_Prime_Intermediaire> listePrimeIntermediaire = new ArrayList<>();
//    private List<OrclassCommission_Prime_Intermediaire> filterPrimeIntermediare = new ArrayList<>();
//    OrclassCommission_Prime_Intermediaire prime_Intermediaire, prime_Apporteur_existe;
//    private List<OrclassTypeBureau> listTypeBureau = new ArrayList<>();
//    private List<OrclassIntermediaires> listIntermediaireSelect = new ArrayList<>();
//    private List<OrclassGarantie> listGarantie = new ArrayList<>();
//    String summary = "";
//    String msgdetail = "";
//    private List<OrclassCategories> listCategories = new ArrayList<>();
//    private List<OrclassCategories> filterCategories = new ArrayList<>();
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//
//    public AgenceController() {
//        categories = new OrclassCategories();
//        prime_Intermediaire = new OrclassCommission_Prime_Intermediaire();
//
//    }
//
//    public void chargeIntermediaireByEntreprise() {
//        if (entreprise != null && entreprise.getIdEntreprise() != null) {
//            listeIntermediaire = intermediairesDao.listeIntermediaireByEntreprise(entreprise);
//        }
//        this.updateTableIntermediaire();
//    }
//
//    public void chargeGarantieByCategorie() {
//        if (categories != null && categories.getIdCategorie() != null) {
//            listGarantie = garantieDao.getallGarantieByCategorieByCompagnie(entreprise, categories);
//
//        }
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
//        menu = menusDao.findEntityHavingValue("code", "intermediaire.prime");
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        chargeIntermediaireByEntreprise();
//        listCategories = categoriesDao.listAllCategoriesShowAllCompagnie();
//        listCategories.addAll(categoriesDao.listAllCategorieByCompagnie(entreprise));
//        chargeTypeBureau();
//
//    }
//
//    public void reset() {
//
//        this.init();
//        this.chargeIntermediaireByEntreprise();
//        PrimeFaces.current().ajax().update("form1");
//
//    }
//
//    public void reverseListe() {
//
//        List<OrclassCommission_Prime_Intermediaire> result = new ArrayList<>();
//        for (int i = listePrimeIntermediaire.size() - 1; i >= 0; i--) {
//            result.add(listePrimeIntermediaire.get(i));
//        }
//
//        this.setListePrimeIntermediaire(result);
////        this.updateTableGarantie();
//    }
//
//    public void chargeTypeBureau() {
//        listTypeBureau = typeBureauDao.listeTypeBureauShowAllCompagnie();
//        listTypeBureau.addAll(typeBureauDao.listeTypeByCompagnie(entreprise));
//    }
//
//    public void init() {
//        this.chargeIntermediaireByEntreprise();
//    }
//
//    public List<OrclassUtilisateurs> listeUtilisateurByIntermediaire(OrclassIntermediaires item) {
//        if (item != null && item.getIdIntermediaire() != null && entreprise != null && entreprise.getIdEntreprise() != null) {
//            return utilisateursDao.listeUtilisateurByIntemediaire(item, entreprise);
//        }
//        return null;
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
//    public void updateTableCategorie() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:idmanageCategorie");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('manageCategorieTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//     public void updateTableIntermediaire() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:idIntermediaire");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('intermediaire').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public void chargerIntermediaireByType(OrclassTypeBureau item) {
//        if (item != null && item.getId() != null) {
//
//            listIntermediaireSelect = intermediairesDao.IntermediaireByCompagnies(entreprise, item);
//
//            PrimeFaces.current().executeScript("PF('tabprincipal').select(1)");
//            UIComponent ui = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:idprimeApporteurTable");
//            ui.setValueExpression("sortBy", null);
////                 PrimeFaces.current().ajax().update(":form2");
//        }
//    }
//
//    public String deleteCommissionPrime() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "commission_intermediaire", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (prime_Intermediaire != null && prime_Intermediaire.getId() != null) {
//
//                prime_IntermediaireDao.delete(prime_Intermediaire);
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "commission_apporteur", exception.Error.FATAL_ERROR + "", new Object[]{"commission_apporteur"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        listePrimeIntermediaire = prime_IntermediaireDao.getPrimeCommissionByCategories(prime_Intermediaire.getIdCategories(), entreprise);
//        prime_Intermediaire = new OrclassCommission_Prime_Intermediaire();
//        prime_Intermediaire.setIdCategories(categories);
//        listePrimeIntermediaire.add(prime_Intermediaire);
//        this.updateTableCommissionPrime();
//        PrimeFaces.current().executeScript("PF('tabprincipal').select(1)");
//        return null;
//    }
//
//    public void updateTableCommissionPrime() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:idprimeApporteurTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('primeApporteurTable').clearFilters();");
//        this.reverseListe();
//
//    }
//
//    public void removePrimeApporteurByListe() {
//        this.setCategories(prime_Intermediaire.getIdCategories());
//        listePrimeIntermediaire.remove(prime_Intermediaire);
//        prime_Intermediaire = new OrclassCommission_Prime_Intermediaire();
//        prime_Intermediaire.setIdCategories(categories);
//        listePrimeIntermediaire.add(prime_Intermediaire);
//        this.updateTableCommissionPrime();
//        PrimeFaces.current().ajax().update("form1");
//        PrimeFaces.current().executeScript("PF('tabprincipal').select(1)");
//
//    }
//
//    public String addPrimeIntermediaire() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "commission_intermediaire", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            OrclassCommission_Prime_Intermediaire prime;
//            if (prime_Intermediaire.getTaux_apport().intValue() == 0 && prime_Intermediaire.getTaux_gestion().intValue() == 0) {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "PLEASE  VALUE OF CONTRIBUTION AND MANAGEMENT IS NULL"));
//                return null;
//            }
//            if (prime_Intermediaire != null && prime_Intermediaire.getIdCategories().getIdCategorie() != null && prime_Intermediaire.getId() == null) {
//
//                if (prime_Intermediaire.getIdIntermediaire() == null && prime_Intermediaire.getIdGarantie() == null) {
//                    prime = prime_IntermediaireDao.getPrimeCommissionByTypeBureau(prime_Intermediaire.getIdTypeBureau(), prime_Intermediaire.getIdCategories(), entreprise);
//                    if (prime == null) {
//                        prime = new OrclassCommission_Prime_Intermediaire();
//                        prime.setActif(Boolean.TRUE);
//                        prime.setDateCreation(new Date());
//                        prime.setIdCategories(prime_Intermediaire.getIdCategories());
//                        prime.setIdEntreprise(entreprise);
//                        prime.setIdTypeBureau(prime_Intermediaire.getIdTypeBureau());
//                        prime.setTaux_apport(prime_Intermediaire.getTaux_apport());
//                        prime.setTaux_gestion(prime_Intermediaire.getTaux_gestion());
//                        prime_IntermediaireDao.create(prime);
//                        listePrimeIntermediaire = prime_IntermediaireDao.getPrimeCommissionByCategories(prime_Intermediaire.getIdCategories(), entreprise);
//                        this.setCategories(prime_Intermediaire.getIdCategories());
//                        prime_Intermediaire = new OrclassCommission_Prime_Intermediaire();
//                        prime_Intermediaire.setIdCategories(categories);
//                        listePrimeIntermediaire.add(prime_Intermediaire);
//                        this.updateTableCommissionPrime();
//                    } else if (prime != null && prime.getId() != null) {
//                        prime_Apporteur_existe = new OrclassCommission_Prime_Intermediaire();
//                        this.setPrime_Apporteur_existe(prime);
//                        if (prime_Intermediaire.getTaux_apport().doubleValue() != prime.getTaux_apport().doubleValue() || prime.getTaux_gestion().doubleValue() != prime_Intermediaire.getTaux_gestion().doubleValue()) {
//                            prime_Apporteur_existe.setActif(Boolean.FALSE);
//                            prime_Apporteur_existe.setDateModification(new Date());
//                            prime_IntermediaireDao.update(prime_Apporteur_existe);
//
//                            prime = new OrclassCommission_Prime_Intermediaire();
//                            prime.setActif(Boolean.TRUE);
//                            prime.setDateCreation(new Date());
//                            prime.setIdCategories(prime_Intermediaire.getIdCategories());
//                            prime.setIdEntreprise(entreprise);
//                            prime.setIdTypeBureau(prime_Intermediaire.getIdTypeBureau());
//                            prime.setTaux_apport(prime_Intermediaire.getTaux_apport());
//                            prime.setTaux_gestion(prime_Intermediaire.getTaux_gestion());
//                            prime_IntermediaireDao.create(prime);
//                            listePrimeIntermediaire = prime_IntermediaireDao.getPrimeCommissionByCategories(prime_Intermediaire.getIdCategories(), entreprise);
//                            this.setCategories(prime_Intermediaire.getIdCategories());
//                            prime_Intermediaire = new OrclassCommission_Prime_Intermediaire();
//                            prime_Intermediaire.setIdCategories(categories);
//                            listePrimeIntermediaire.add(prime_Intermediaire);
//                            this.updateTableCommissionPrime();
//                        } else if (prime_Intermediaire.getTaux_apport().doubleValue() == prime.getTaux_apport().doubleValue() && prime.getTaux_gestion().doubleValue() == prime_Intermediaire.getTaux_gestion().doubleValue()) {
//                            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "PLEASE CHANGE VALUE OF CONTRIBUTION OR MANAGEMENT"));
//                            prime_Intermediaire = new OrclassCommission_Prime_Intermediaire();
//                            prime_Intermediaire.setIdCategories(prime_Intermediaire.getIdCategories());
//                            listePrimeIntermediaire.add(prime_Intermediaire);
//                            PrimeFaces.current().ajax().update(":form1");
//                            PrimeFaces.current().executeScript("PF('tabprincipal').select(1)");
//                            return null;
//
//                        }
//                    }
//                } else if (prime_Intermediaire.getIdIntermediaire() != null && prime_Intermediaire.getIdGarantie() == null) {
//                    prime = prime_IntermediaireDao.getPrimeCommissionByIntermediaire(prime_Intermediaire.getIdIntermediaire(), prime_Intermediaire.getIdCategories(), entreprise);
//                    if (prime == null) {
//                        prime = new OrclassCommission_Prime_Intermediaire();
//                        prime.setActif(Boolean.TRUE);
//                        prime.setDateCreation(new Date());
//                        prime.setIdCategories(prime_Intermediaire.getIdCategories());
//                        prime.setIdEntreprise(entreprise);
//                        prime.setIdTypeBureau(prime_Intermediaire.getIdTypeBureau());
//                        prime.setIdIntermediaire(prime_Intermediaire.getIdIntermediaire());
//                        prime.setTaux_apport(prime_Intermediaire.getTaux_apport());
//                        prime.setTaux_gestion(prime_Intermediaire.getTaux_gestion());
//                        prime_IntermediaireDao.create(prime);
//                        listePrimeIntermediaire = prime_IntermediaireDao.getPrimeCommissionByCategories(prime_Intermediaire.getIdCategories(), entreprise);
//                        this.setCategories(prime_Intermediaire.getIdCategories());
//                        prime_Intermediaire = new OrclassCommission_Prime_Intermediaire();
//                        prime_Intermediaire.setIdCategories(categories);
//                        listePrimeIntermediaire.add(prime_Intermediaire);
//                        this.updateTableCommissionPrime();
//                    } else if (prime != null && prime.getId() != null) {
//                        prime_Apporteur_existe = new OrclassCommission_Prime_Intermediaire();
//                        this.setPrime_Apporteur_existe(prime);
//                        if (prime_Intermediaire.getTaux_apport().doubleValue() != prime.getTaux_apport().doubleValue() || prime.getTaux_gestion().doubleValue() != prime_Intermediaire.getTaux_gestion().doubleValue()) {
//                            prime_Apporteur_existe.setActif(Boolean.FALSE);
//                            prime_Apporteur_existe.setDateModification(new Date());
//                            prime_IntermediaireDao.update(prime_Apporteur_existe);
//
//                            prime = new OrclassCommission_Prime_Intermediaire();
//                            prime.setActif(Boolean.TRUE);
//                            prime.setDateCreation(new Date());
//                            prime.setIdCategories(prime_Intermediaire.getIdCategories());
//                            prime.setIdEntreprise(entreprise);
//                            prime.setIdTypeBureau(prime_Intermediaire.getIdTypeBureau());
//                            prime.setIdIntermediaire(prime_Intermediaire.getIdIntermediaire());
//                            prime.setTaux_apport(prime_Intermediaire.getTaux_apport());
//                            prime.setTaux_gestion(prime_Intermediaire.getTaux_gestion());
//                            prime_IntermediaireDao.create(prime);
//                            listePrimeIntermediaire = prime_IntermediaireDao.getPrimeCommissionByCategories(prime_Intermediaire.getIdCategories(), entreprise);
//                            this.setCategories(prime_Intermediaire.getIdCategories());
//                            prime_Intermediaire = new OrclassCommission_Prime_Intermediaire();
//                            prime_Intermediaire.setIdCategories(categories);
//                            listePrimeIntermediaire.add(prime_Intermediaire);
//                            this.updateTableCommissionPrime();
//                        } else if (prime_Intermediaire.getTaux_apport().doubleValue() == prime.getTaux_apport().doubleValue() && prime.getTaux_gestion().doubleValue() == prime_Intermediaire.getTaux_gestion().doubleValue()) {
//                            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "PLEASE CHANGE VALUE OF CONTRIBUTION OR MANAGEMENT"));
//                            prime_Intermediaire = new OrclassCommission_Prime_Intermediaire();
//                            prime_Intermediaire.setIdCategories(prime_Intermediaire.getIdCategories());
//                            listePrimeIntermediaire.add(prime_Intermediaire);
//                            PrimeFaces.current().ajax().update(":form1");
//                            PrimeFaces.current().executeScript("PF('tabprincipal').select(1)");
//                            return null;
//
//                        }
//                    }
//                } else if ((prime_Intermediaire.getIdIntermediaire() != null && prime_Intermediaire.getIdIntermediaire().getIdIntermediaire() != null) && (prime_Intermediaire.getIdGarantie() != null && prime_Intermediaire.getIdGarantie().getId() != null)) {
//                    prime = prime_IntermediaireDao.getPrimeCommissionByIntermediaireHaveGarantie(prime_Intermediaire.getIdIntermediaire(), prime_Intermediaire.getIdCategories(), prime_Intermediaire.getIdGarantie(), entreprise);
//                    if (prime == null) {
//                        prime = new OrclassCommission_Prime_Intermediaire();
//                        prime.setActif(Boolean.TRUE);
//                        prime.setDateCreation(new Date());
//                        prime.setIdCategories(prime_Intermediaire.getIdCategories());
//                        prime.setIdEntreprise(entreprise);
//                        prime.setIdIntermediaire(prime_Intermediaire.getIdIntermediaire());
//                        prime.setIdGarantie(prime_Intermediaire.getIdGarantie());
//                        prime.setIdTypeBureau(prime_Intermediaire.getIdTypeBureau());
//                        prime.setTaux_apport(prime_Intermediaire.getTaux_apport());
//                        prime.setTaux_gestion(prime_Intermediaire.getTaux_gestion());
//                        prime_IntermediaireDao.create(prime);
//                        listePrimeIntermediaire = prime_IntermediaireDao.getPrimeCommissionByCategories(prime_Intermediaire.getIdCategories(), entreprise);
//                        this.setCategories(prime_Intermediaire.getIdCategories());
//                        prime_Intermediaire = new OrclassCommission_Prime_Intermediaire();
//                        prime_Intermediaire.setIdCategories(categories);
//                        listePrimeIntermediaire.add(prime_Intermediaire);
//                        this.updateTableCommissionPrime();
//                    } else if (prime != null && prime.getId() != null) {
//                        prime_Apporteur_existe = new OrclassCommission_Prime_Intermediaire();
//                        this.setPrime_Apporteur_existe(prime);
//                        if (prime_Intermediaire.getTaux_apport().doubleValue() != prime.getTaux_apport().doubleValue() || prime.getTaux_gestion().doubleValue() != prime_Intermediaire.getTaux_gestion().doubleValue()) {
//                            prime_Apporteur_existe.setActif(Boolean.FALSE);
//                            prime_Apporteur_existe.setDateModification(new Date());
//                            prime_IntermediaireDao.update(prime_Apporteur_existe);
//
//                            prime = new OrclassCommission_Prime_Intermediaire();
//                            prime.setActif(Boolean.TRUE);
//                            prime.setDateCreation(new Date());
//                            prime.setIdCategories(prime_Intermediaire.getIdCategories());
//                            prime.setIdEntreprise(entreprise);
//                            prime.setIdTypeBureau(prime_Intermediaire.getIdTypeBureau());
//                            prime.setIdIntermediaire(prime_Intermediaire.getIdIntermediaire());
//                            prime.setIdGarantie(prime_Intermediaire.getIdGarantie());
//                            prime.setTaux_apport(prime_Intermediaire.getTaux_apport());
//                            prime.setTaux_gestion(prime_Intermediaire.getTaux_gestion());
//                            prime_IntermediaireDao.create(prime);
//                            listePrimeIntermediaire = prime_IntermediaireDao.getPrimeCommissionByCategories(prime_Intermediaire.getIdCategories(), entreprise);
//                            this.setCategories(prime_Intermediaire.getIdCategories());
//                            prime_Intermediaire = new OrclassCommission_Prime_Intermediaire();
//                            prime_Intermediaire.setIdCategories(categories);
//                            listePrimeIntermediaire.add(prime_Intermediaire);
//                            this.updateTableCommissionPrime();
//                        } else if (prime_Intermediaire.getTaux_apport().doubleValue() == prime.getTaux_apport().doubleValue() && prime.getTaux_gestion().doubleValue() == prime_Intermediaire.getTaux_gestion().doubleValue()) {
//                            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "PLEASE CHANGE VALUE OF CONTRIBUTION OR MANAGEMENT"));
//                            prime_Intermediaire = new OrclassCommission_Prime_Intermediaire();
//                            prime_Intermediaire.setIdCategories(prime_Intermediaire.getIdCategories());
//                            listePrimeIntermediaire.add(prime_Intermediaire);
//                            PrimeFaces.current().ajax().update(":form1");
//                            PrimeFaces.current().executeScript("PF('tabprincipal').select(1)");
//                            return null;
//                        }
//                    }
//                } else if ((prime_Intermediaire.getIdIntermediaire() == null || prime_Intermediaire.getIdIntermediaire().getIdIntermediaire() == null) && (prime_Intermediaire.getIdGarantie() != null && prime_Intermediaire.getIdGarantie().getId() != null)) {
//                    prime = prime_IntermediaireDao.getPrimeCommissionByTypeBureauHaveGarantie(prime_Intermediaire.getIdTypeBureau(), prime_Intermediaire.getIdCategories(), prime_Intermediaire.getIdGarantie(), entreprise);
//                    if (prime == null) {
//                        prime = new OrclassCommission_Prime_Intermediaire();
//                        prime.setActif(Boolean.TRUE);
//                        prime.setDateCreation(new Date());
//                        prime.setIdCategories(prime_Intermediaire.getIdCategories());
//                        prime.setIdEntreprise(entreprise);
//                        prime.setIdTypeBureau(prime_Intermediaire.getIdTypeBureau());
//                        prime.setIdGarantie(prime_Intermediaire.getIdGarantie());
//                        prime.setTaux_apport(prime_Intermediaire.getTaux_apport());
//                        prime.setTaux_gestion(prime_Intermediaire.getTaux_gestion());
//                        prime_IntermediaireDao.create(prime);
//                        listePrimeIntermediaire = prime_IntermediaireDao.getPrimeCommissionByCategories(prime_Intermediaire.getIdCategories(), entreprise);
//                        this.setCategories(prime_Intermediaire.getIdCategories());
//                        prime_Intermediaire = new OrclassCommission_Prime_Intermediaire();
//                        prime_Intermediaire.setIdCategories(categories);
//                        listePrimeIntermediaire.add(prime_Intermediaire);
//                        this.updateTableCommissionPrime();
//                    } else if (prime != null && prime.getId() != null) {
//                        prime_Apporteur_existe = new OrclassCommission_Prime_Intermediaire();
//                        this.setPrime_Apporteur_existe(prime);
//                        if (prime_Intermediaire.getTaux_apport().doubleValue() != prime.getTaux_apport().doubleValue() || prime.getTaux_gestion().doubleValue() != prime_Intermediaire.getTaux_gestion().doubleValue()) {
//                            prime_Apporteur_existe.setActif(Boolean.FALSE);
//                            prime_Apporteur_existe.setDateModification(new Date());
//                            prime_IntermediaireDao.update(prime_Apporteur_existe);
//
//                            prime = new OrclassCommission_Prime_Intermediaire();
//                            prime.setActif(Boolean.TRUE);
//                            prime.setDateCreation(new Date());
//                            prime.setIdCategories(prime_Intermediaire.getIdCategories());
//                            prime.setIdEntreprise(entreprise);
//                            prime.setIdTypeBureau(prime_Intermediaire.getIdTypeBureau());
//                            prime.setIdGarantie(prime_Intermediaire.getIdGarantie());
//                            prime.setTaux_apport(prime_Intermediaire.getTaux_apport());
//                            prime.setTaux_gestion(prime_Intermediaire.getTaux_gestion());
//                            prime_IntermediaireDao.create(prime);
//                            listePrimeIntermediaire = prime_IntermediaireDao.getPrimeCommissionByCategories(prime_Intermediaire.getIdCategories(), entreprise);
//                            this.setCategories(prime_Intermediaire.getIdCategories());
//                            prime_Intermediaire = new OrclassCommission_Prime_Intermediaire();
//                            prime_Intermediaire.setIdCategories(categories);
//                            listePrimeIntermediaire.add(prime_Intermediaire);
//                            this.updateTableCommissionPrime();
//                        } else if (prime_Intermediaire.getTaux_apport().doubleValue() == prime.getTaux_apport().doubleValue() && prime.getTaux_gestion().doubleValue() == prime_Intermediaire.getTaux_gestion().doubleValue()) {
//                            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "PLEASE CHANGE VALUE OF CONTRIBUTION OR MANAGEMENT"));
//                            prime_Intermediaire = new OrclassCommission_Prime_Intermediaire();
//                            prime_Intermediaire.setIdCategories(categories);
//                            listePrimeIntermediaire.add(prime_Intermediaire);
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
//    public void chargeCommissionPrime() {
//
//        if (categories != null && categories.getIdCategorie() != null) {
//            listePrimeIntermediaire = prime_IntermediaireDao.getPrimeCommissionByCategories(categories, entreprise);
//            chargeGarantieByCategorie();
//            this.prime_Intermediaire.setIdCategories(categories);
//            if (listePrimeIntermediaire == null || listePrimeIntermediaire.isEmpty()) {
//                listePrimeIntermediaire = new ArrayList<>();
//                listePrimeIntermediaire.add(prime_Intermediaire);
//            } else {
//                prime_Intermediaire = new OrclassCommission_Prime_Intermediaire();
//                prime_Intermediaire.setIdCategories(categories);
//                listePrimeIntermediaire.add(prime_Intermediaire);
//            }
//
//            this.updateTableCommissionPrime();
//
//            PrimeFaces.current().ajax().update(":form1:tabprincipal:idprimeApporteurTable");
//            PrimeFaces.current().ajax().update(":form1");
//            PrimeFaces.current().executeScript("PF('tabprincipal').select(1)");
//
//        }
//
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
//    public List<OrclassCommission_Prime_Intermediaire> getListePrimeIntermediaire() {
//        return listePrimeIntermediaire;
//    }
//
//    public void setListePrimeIntermediaire(List<OrclassCommission_Prime_Intermediaire> listePrimeIntermediaire) {
//        this.listePrimeIntermediaire = listePrimeIntermediaire;
//    }
//
//    public List<OrclassCommission_Prime_Intermediaire> getFilterPrimeIntermediare() {
//        return filterPrimeIntermediare;
//    }
//
//    public void setFilterPrimeIntermediare(List<OrclassCommission_Prime_Intermediaire> filterPrimeIntermediare) {
//        this.filterPrimeIntermediare = filterPrimeIntermediare;
//    }
//
//    public OrclassCommission_Prime_Intermediaire getPrime_Intermediaire() {
//        return prime_Intermediaire;
//    }
//
//    public void setPrime_Intermediaire(OrclassCommission_Prime_Intermediaire prime_Intermediaire) {
//        this.prime_Intermediaire = prime_Intermediaire;
//    }
//
//    public List<OrclassTypeBureau> getListTypeBureau() {
//        return listTypeBureau;
//    }
//
//    public void setListTypeBureau(List<OrclassTypeBureau> listTypeBureau) {
//        this.listTypeBureau = listTypeBureau;
//    }
//
//    public List<OrclassIntermediaires> getListIntermediaireSelect() {
//        return listIntermediaireSelect;
//    }
//
//    public void setListIntermediaireSelect(List<OrclassIntermediaires> listIntermediaireSelect) {
//        this.listIntermediaireSelect = listIntermediaireSelect;
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
//    public OrclassCommission_Prime_Intermediaire getPrime_Apporteur_existe() {
//        return prime_Apporteur_existe;
//    }
//
//    public void setPrime_Apporteur_existe(OrclassCommission_Prime_Intermediaire prime_Apporteur_existe) {
//        this.prime_Apporteur_existe = prime_Apporteur_existe;
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
//    public List<OrclassCategories> getFilterCategories() {
//        return filterCategories;
//    }
//
//    public void setFilterCategories(List<OrclassCategories> filterCategories) {
//        this.filterCategories = filterCategories;
//    }
//
//    public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.commission_intermediaire.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);
//
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//      public Boolean accessAjouter() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.commission_intermediaire.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.commission_intermediaire.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.commission_intermediaire.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer.name(), fon);
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.commission_intermediaire.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.ajouter_commission_intermediaire.name(), fon);
//
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.commission_intermediaire.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.afficher_liste.name(), fon);
//
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//}
