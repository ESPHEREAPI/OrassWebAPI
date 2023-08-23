///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.administration;
//
//import Excell.IExcell;
//import controllers.parametrage.CracteristiqueController;
//import dao.OrclassCaracteristiquesDao;
//import dao.OrclassCategoriesDao;
//import dao.OrclassGarantieDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassRefGarantiesDao;
//import dao.OrclassRubriqueCaracteristiqueDao;
//import dao.OrclassRubriqueDao;
//import dao.OrclassRubriqueGarantieDao;
//import dao.OrclassTarifConditionDao;
//import dao.OrclassTarifDao;
//import dao.OrclassTypeTarifDao;
//import dao.OrclassUnite_CaracteristiqueDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleParametrage;
//import enums.ModeCalcul;
//import enums.ModeCalculRubrique;
//import enums.NatureGarantie;
//import enums.NatureOperationTarif;
//import enums.OperationsTarif;
//import enums.StatutCaracteristique;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.Serializable;
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Collection;
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
//import javax.inject.Inject;
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.ConstraintViolationException;
//import modele.OrclassActions;
//import modele.OrclassCaracteristiques;
//import modele.OrclassCategories;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassGarantie;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassRefGaranties;
//import modele.OrclassRubrique;
//import modele.OrclassRubriqueCaracteristique;
//import modele.OrclassRubriqueGarantie;
//import modele.OrclassTarif;
//import modele.OrclassTarifCondition;
//import modele.OrclassTypeTarif;
//import modele.OrclassUnite_Caracteristique;
//import modele.OrclassUtilisateurs;
//import org.primefaces.PrimeFaces;
//import org.primefaces.event.TabChangeEvent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import utils.GlobalFonctions;
//import utils.LocaleHelper;
//import utils.RecupBundle;
//
///**
// *
// * @author JIATOU FRANCK
// */
//@Named(value = "tarifConditionController")
//@ViewScoped
//public class TarifConditionController implements Serializable {
//
//    /**
//     * Creates a new instance of TarifConditionController
//     */
//    private static final Logger logger = LoggerFactory.getLogger(TarifConditionController.class);
//    @EJB
//    OrclassTarifDao tarifDao;
//    OrclassTarif tarif;
//    @EJB
//    OrclassRubriqueGarantieDao rubriqueGarantieDao;
//    OrclassRubriqueGarantie rubriqueGarantie;
//    OrclassRubriqueGarantie rubriqueGarantieSelect;
//    OrclassRubriqueGarantie rubriqueGarantieSelecte;
//    @EJB
//    OrclassGarantieDao garantieDao;
//    OrclassGarantie garantie;
//    @EJB
//    OrclassTypeTarifDao typeTarifDao;
//    OrclassTypeTarif typeTarif;
//    @EJB
//    OrclassRubriqueDao rubriqueDao;
//    OrclassRubrique rubrique;
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    IDroitAcces serviceAccess;
//    OrclassCategories categories;
//    @EJB
//    OrclassCaracteristiquesDao caracteristiquesDao;
//    OrclassCaracteristiques caracteristiques;
//    @EJB
//    OrclassUnite_CaracteristiqueDao unite_CaracteristiqueDao;
//    OrclassUnite_Caracteristique orclassUnite_Caracteristique;
//    @EJB
//    OrclassRubriqueCaracteristiqueDao rubriqueCaracteristiqueDao;
//    OrclassRubriqueCaracteristique rubriqueCaracteristique;
//    OrclassRubriqueCaracteristique rubriqueCaracteristiqueSelecte;
//    OrclassRubriqueCaracteristique rubriqueCaracteristiqueSelecteForTarifCondition;
//    @EJB
//    OrclassTarifConditionDao tarifConditionDao;
//    OrclassTarifCondition tarifCondition;
//    @EJB
//    OrclassCategoriesDao categoriesDao;
//    @EJB
//    OrclassRefGarantiesDao refGarantiesDao;
//    @EJB
//    IExcell serviceExcell;
//    @Inject
//    CracteristiqueController caracteristiqueController;
//    private Collection<OrclassCategories> lisCategories = new ArrayList<>();
//    private Collection<OrclassGarantie> lisGarantie = new ArrayList<>();
//    private List<OrclassRubriqueGarantie> listRubriqueGarantie = new ArrayList<>();
//    private List<OrclassTarif> lisTarif = new ArrayList<>();
//    private Collection<OrclassTypeTarif> lisTypeTarif = new ArrayList<>();
//    private Collection<OrclassCaracteristiques> lisTCaracteristiques = new ArrayList<>();
//    private Collection<OrclassUnite_Caracteristique> lisTUnite_Caracteristique = new ArrayList<>();
//    private Collection<OrclassRubrique> lisTRubrique = new ArrayList<>();
//    private List<OrclassRubriqueGarantie> filterRubriqueGarantie = new ArrayList<>();
//    private List<OrclassRubriqueGarantie> listeRG = new ArrayList<>();
//    private List<OrclassTarifCondition> filterOrclassTarifCondition = new ArrayList<>();
//    private List<OrclassRubrique> filterRubrique = new ArrayList<>();
//    private List<OrclassRubriqueCaracteristique> lisRubriqueCaracteristique = new ArrayList<>();
//    private List<OrclassRubriqueCaracteristique> lisRubriqueCaracteristiqueForTarifCondition = new ArrayList<>();
//    private Collection<OrclassRubriqueCaracteristique> filterRubriqueCaracteristique = new ArrayList<>();
//    private Collection<OrclassCaracteristiques> filterCaracteristique = new ArrayList<>();
//    private Collection<OrclassGarantie> filterOrclassGarantie = new ArrayList<>();
//    private Collection<OrclassRubriqueGarantie> filterOrclassRubriqueGarantie = new ArrayList<>();
//    private List<OrclassTarifCondition> lisTarifConditions = new ArrayList<>();
//    private List<OrclassRefGaranties> ListeRefGarantieses = new ArrayList<>();
//    private List<OrclassRefGaranties> filterRefGarantie = new ArrayList<>();
//    private Boolean afficheListeRubrique = Boolean.TRUE;
//    
//    String summary = "";
//    String msgdetail = "";
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//    OrclassEntreprises entreprise;
//    int valeurChoixNaturOperation = 0;
//    int valeurChoixNaturOperation2 = 0;
//    String libelle = "";
//    private String currentFolder = "/photos";
//    private String defautpathExcell = currentFolder + "/REF_GARANTIE.xls";
//    private OrclassRefGaranties refGaranties;
//    
//    public TarifConditionController() {
//        rubrique = new OrclassRubrique();
//        rubriqueGarantieSelecte = new OrclassRubriqueGarantie();
//        rubriqueCaracteristique = new OrclassRubriqueCaracteristique();
//        caracteristiques = new OrclassCaracteristiques();
//        rubriqueCaracteristiqueSelecte = new OrclassRubriqueCaracteristique();
//        rubriqueGarantieSelect = new OrclassRubriqueGarantie();
//        tarif = new OrclassTarif();
//        tarifCondition = new OrclassTarifCondition();
//        rubriqueCaracteristiqueSelecteForTarifCondition = new OrclassRubriqueCaracteristique();
//        garantie = new OrclassGarantie();
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
////
////        menu = menusDao.findEntityHavingValue("code", ModuleMenu.mdsp_tarif);
////        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        
//        lisCategories = categoriesDao.listAllCategoriesShowAllCompagnie();
//        lisTypeTarif = typeTarifDao.findAll();
//        lisTUnite_Caracteristique = unite_CaracteristiqueDao.findAll();
//        if (refGarantiesDao.findAll() == null || refGarantiesDao.findAll().isEmpty()) {
//            initialRefGarantieByExcell();
//        }
//        ListeRefGarantieses = refGarantiesDao.listAllRefGarantiesShowAllCompagnie();
////        ListeRefGarantieses.addAll(refGarantiesDao.listAllRefGarantiesByCompagnie(entreprise));
////        lisTCaracteristiques=caracteristiquesDao.listCaracteristiqueByCompagnie(entreprise);
//        this.updateTableRubrique();
//        
//    }
//    
//    public void initialRefGarantieByExcell() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        List<OrclassRefGaranties> listRefGaranties = new ArrayList<>();
//        OrclassRefGaranties ref;
//        OrclassEntreprises en = null;
//        try {
//            
//            String path = extContext.getRealPath("") + this.defautpathExcell;
//            File file = new File(path);
//            InputStream targetStream = new FileInputStream(file);
//            listRefGaranties = serviceExcell.recuperListeRefrenceGarantieByExcell(targetStream, path);
//            if (listRefGaranties == null) {
//                System.err.println("Une erruer est survenue dans l extraction des donnes sur le fichier Excell");
//            }
//            for (OrclassRefGaranties rfg : listRefGaranties) {
//                ref = refGarantiesDao.findEntityHavingValue("code", rfg.getCode());
//                if (ref == null) {
//                    ref = new OrclassRefGaranties();
//                    ref.setCode(rfg.getCode());
//                    ref.setLibelle(rfg.getLibelle());
//                    ref.setFamille(ref.getFamille());
//                    ref.setShowAllCompagnies(Boolean.TRUE);
////                    ref.setIdEntreprise(en);
//                    refGarantiesDao.create(ref);
//                    
//                }
//                
//            }
//        } catch (IOException e) {
//            System.out.println("controllers.administration.CategorieController.initialRefGarantieByExcell() ...une erreur inattandue.." + e.getMessage());
//            
//        }
//    }
//    
//    public void showDetailsRefGarantie(OrclassRefGaranties item) {
//        if (refGaranties == null || refGaranties.getId() == null) {
//            this.setRefGaranties(item);
//        }
//        this.chargeGarentieByRefGarantie();
//        
//    }
//    
//    public void chargeGarentieByRefGarantie() {
//        if (refGaranties != null && refGaranties != null) {
//            if (garantie == null) {
//                garantie = new OrclassGarantie();
//            }
//            garantie.setIdCategories(categories);
//            garantie.setIdRefGaranties(refGaranties);
//            garantie.setGlobalCompagnie(Boolean.TRUE);
//            garantie.setDateCreation(new Date());
//            garantie.setIdEntreprise(entreprise);
//            garantie.setModeCalcul(ModeCalcul.Automatique);
//            garantie.setNatureGarantie(NatureGarantie.obligatoire);
//            if (garantieDao.finKeyGaranties(categories, refGaranties, entreprise) == null) {
//                garantieDao.create(garantie);
//                if (lisGarantie.contains(garantie) == Boolean.FALSE) {
//                    lisGarantie.add(garantie);
//                    garantie = new OrclassGarantie();
//                    
//                }
//            } else {
//                garantie = new OrclassGarantie();
//            }
//            this.updateTableRefGarantie();
//            this.updateTableRubrique();
//            PrimeFaces.current().ajax().update(":tabprincipal:form1,:tabprincipal:form1:idirgTable");
//            
//        }
//        
//    }
//    
//    public void updateTableRefGarantie() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form111:idrefGarantieTable");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('refGarantieTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
////        this.reverseListe();
////        ListeRefGarantieses.removeAll(chargerReferenceHaveGarantie());
//    public void chargeChargerTariconditionByTarif() {
//        lisRubriqueCaracteristiqueForTarifCondition.clear();
//        if (tarif != null && tarif.getId() != null) {
//            lisRubriqueCaracteristiqueForTarifCondition = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagnies(entreprise, tarif.getIdRubriqueGarantie().getIdGarantie().getIdCategories(), tarif.getIdRubriqueGarantie());
//            
//            if (!lisRubriqueCaracteristiqueForTarifCondition.isEmpty()) {
//                
//                lisRubriqueCaracteristiqueForTarifCondition.removeAll(tarifConditionDao.listRubriqueCaracteristiqueByTarif(tarif, entreprise));
//            }
//            lisTarifConditions = tarifConditionDao.listConditionByTarif(tarif, entreprise);
//            if (lisTarifConditions.isEmpty()) {
//                this.tarifCondition = new OrclassTarifCondition();
//                tarifCondition.setIdTarif(tarif);
//                lisTarifConditions.add(tarifCondition);
//            } else {
//                this.tarifCondition = new OrclassTarifCondition();
//                tarifCondition.setIdTarif(tarif);
//                lisTarifConditions.add(tarifCondition);
//                this.reverseListeTarifCondition();
//            }
//            this.updateTableRubriqueCaracteristiqueSelectForTarifCondition();
//            this.updateTableTarifCondition();
//            PrimeFaces.current().ajax().update(":tabprincipal:form2:second");
//            PrimeFaces.current().executeScript(" PF('second').select(1)");
//        }
//    }
//    
//    public void reload() throws IOException {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
//    }
//    
//    public void chargeLigneTarifConditionByRubriqueCaracteristique() {
//        if (rubriqueCaracteristiqueSelecteForTarifCondition != null && rubriqueCaracteristiqueSelecteForTarifCondition.getId() != null) {
//            tarifCondition = lisTarifConditions.get(0);
//            tarifCondition.setIdRubriqueCaracteristique(rubriqueCaracteristiqueSelecteForTarifCondition);
//            lisTarifConditions.set(0, tarifCondition);
//        }
//        updateTableTarifCondition();
//        this.updateTableRubriqueCaracteristiqueSelectForTarifCondition();
//    }
//    
//    public void reverseListeTarifCondition() {
//        
//        List<OrclassTarifCondition> result = new ArrayList<>();
//        for (int i = lisTarifConditions.size() - 1; i >= 0; i--) {
//            result.add(lisTarifConditions.get(i));
//        }
//        
//        this.setLisTarifConditions(result);
////        this.updateTableRubriqueCategorie();
//    }
//    
//    public void chargeGarantieByCategories() {
//        lisGarantie = new ArrayList<>();
//        listRubriqueGarantie = new ArrayList<>();
//        if (categories != null && categories != null) {
//            lisGarantie = garantieDao.getallGarantieByCategorieByCompagnie(entreprise, categories);
//            if (lisGarantie == null) {
//                lisGarantie = new ArrayList<>();
//            }
////            for (OrclassGarantie g : lisGarantie) {
////                System.out.println("code " + g.getIdRefGaranties().getCode());
////                System.out.println("libelle " + g.getIdRefGaranties().getLibelle());
////            }
//            this.updateTableRubrique();
//            
//        }
//        
//    }
//    
//    public void changeValeurChoixForNatureOperation1() {
//        if (tarif==null || tarif.getOperationTarif1()==null) {
//            return;
//        }
//        switch (tarif.getOperationTarif1()) {
//            case contante:
//                valeurChoixNaturOperation = 1;
//                break;
//            case operation:
//                valeurChoixNaturOperation = 2;
//                break;
//            case rubrique:
//                valeurChoixNaturOperation = 3;
//                break;
//            case val_rub_precedent:
//                valeurChoixNaturOperation = 4;
//                break;
//            case caracteristique:
//                valeurChoixNaturOperation = 5;
//        }
//        if (tarif == null || tarif.getId() == null) {
//            PrimeFaces.current().executeScript("PF('manageFormulleDialog').show()");
//        }
//        
//    }
//    
//    public void changeValeurChoixForNatureOperation2() {
//        if (tarif==null || tarif.getOperationTarif2() == null) {
//            valeurChoixNaturOperation2 = 0;
//            return;
//            
//        }
//        switch (tarif.getOperationTarif2()) {
//            case contante:
//                valeurChoixNaturOperation2 = 11;
//                break;
//            case operation:
//                valeurChoixNaturOperation2 = 22;
//                break;
//            case rubrique:
//                valeurChoixNaturOperation2 = 33;
//                break;
//            case val_rub_precedent:
//                valeurChoixNaturOperation2 = 44;
//                break;
//            case caracteristique:
//                valeurChoixNaturOperation2 = 55;
//        }
//        if (tarif == null || tarif.getId() == null) {
//            PrimeFaces.current().executeScript("PF('manageFormulleDialog').show()");
//        }
//    }
//    
//    public void chargeListeRubriCaracteristique() {
//        Collection<OrclassCaracteristiques> listeCaracte = new ArrayList<>();
//        
//        if (rubriqueGarantieSelecte != null && rubriqueGarantieSelecte != null) {
//            BigInteger nbre = tarifDao.checkLastNumero8ordre(rubriqueGarantieSelecte, entreprise);
//            tarif=tarifDao.finKey(rubriqueGarantieSelecte, entreprise);
//            if (nbre.intValue() != 0 && tarif!=null && tarif.getId()!=null) {
//                tarif = tarifDao.finKey(rubriqueGarantieSelecte, entreprise);
//                this.changeValeurChoixForNatureOperation1();
//                this.changeValeurChoixForNatureOperation2();
//          
//                   lisTarifConditions = tarifConditionDao.listConditionByTarif(tarif, entreprise); 
//              
//                
//                if (lisTarifConditions.isEmpty()) {
//                    tarifCondition = new OrclassTarifCondition();
//                    tarifCondition.setIdTarif(tarif);
//                    lisTarifConditions.add(tarifCondition);
//                    lisRubriqueCaracteristiqueForTarifCondition = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagnies(entreprise, rubriqueGarantieSelecte.getIdGarantie().getIdCategories(), rubriqueGarantieSelecte);
//                } else {
//                    tarifCondition = new OrclassTarifCondition();
//                    tarifCondition.setIdTarif(tarif);
//                    lisTarifConditions.add(tarifCondition);
//                    lisRubriqueCaracteristiqueForTarifCondition = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagnies(entreprise, rubriqueGarantieSelecte.getIdGarantie().getIdCategories(), rubriqueGarantieSelecte);
//                    
//                    lisRubriqueCaracteristiqueForTarifCondition.removeAll(tarifConditionDao.listRubriqueCaracteristiqueByTarif(tarif, entreprise));
//                    this.reverseListeTarifCondition();
//                }
//                this.updateTableRubriqueCaracteristiqueSelectForTarifCondition();
//                this.updateTableTarifCondition();
//            } else {
//                tarif = new OrclassTarif();
//                
//            }
//            
//            lisTCaracteristiques = caracteristiquesDao.listCaracteristiqueByCompagnie(entreprise);
//            lisTCaracteristiques.addAll(caracteristiquesDao.listeCaracteristiqueForAllCompagnies());
//            if (lisTCaracteristiques.isEmpty()) {
//                lisTCaracteristiques = caracteristiqueController.getListeCaracteristique();
//            }
//            listeCaracte = caracteristiquesDao.listCaracteristiqueHaveRubriqueByCompagnie(entreprise, rubriqueGarantieSelecte);
//            if (!listeCaracte.isEmpty()) {
//                lisTCaracteristiques.removeAll(listeCaracte);
//            }
//            this.setCategories(rubriqueGarantieSelecte.getIdGarantie().getIdCategories());
//            this.setRubrique(rubriqueGarantieSelecte.getIdRubrique());
//            this.setGarantie(rubriqueGarantieSelecte.getIdGarantie());
//            lisRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagnies(entreprise, categories, rubriqueGarantieSelecte);
//            if (lisRubriqueCaracteristique.isEmpty()) {
//                rubriqueCaracteristique = new OrclassRubriqueCaracteristique();
//                rubriqueCaracteristique.setIdCategories(categories);
//                rubriqueCaracteristique.setIdRubriqueGarantie(rubriqueGarantieSelecte);
////                rubriqueCaracteristique.setIdGarantie(garantie);
////                rubriqueCaracteristique.setIdRubrique(rubrique);
//                lisRubriqueCaracteristique.add(rubriqueCaracteristique);
//            } else {
//                rubriqueCaracteristique = new OrclassRubriqueCaracteristique();
//                rubriqueCaracteristique.setIdCategories(categories);
//                rubriqueCaracteristique.setIdRubriqueGarantie(rubriqueGarantieSelecte);
////                rubriqueCaracteristique.setIdGarantie(garantie);
////                rubriqueCaracteristique.setIdRubrique(rubrique);
//                lisRubriqueCaracteristique.add(rubriqueCaracteristique);
//                this.reverseListeRubriqueCaracteristique();
//                
//            }
//            updateTableRubriqueCaracteristique();
//            updateTableCaracteristique();
//            updateTableRubriqueGarantieSelecte();
//            PrimeFaces.current().ajax().update(":form11");
//            PrimeFaces.current().ajax().update(":tabprincipal:form2");
//        }
//    }
//    
//    public Boolean controleIsGratuitOrForfait() {
//        if (garantie != null && garantie.getId() != null && typeTarif != null && typeTarif.getId() != null) {
//            if (Objects.equals(garantie.getForfaire(), Boolean.TRUE)) {
//                this.setLibelle("LA GARANTIE EST FORFAITAIRE PAS BESOIN DE RUBRIQUE");
//                PrimeFaces.current().ajax().update(":form12:libelle");
//                PrimeFaces.current().executeScript("PF('etatGarantieDialog').show()");
//                return Boolean.TRUE;
//            }
//            if (Objects.equals(garantie.getGratuit(), Boolean.TRUE)) {
//                this.setLibelle("LA GARANTIE EST GRATUIT  PAS BESOIN DE RUBRIQUE");
//                PrimeFaces.current().ajax().update(":form12:libelle");
//                PrimeFaces.current().executeScript("PF('etatGarantieDialog').show()");
//                return Boolean.TRUE;
//            }
//        }
//        return Boolean.FALSE;
//    }
//    
//    public void chargeRubriqueGarantieByGarantie() {
//        listRubriqueGarantie.clear();
//        
//        if (garantie != null && garantie.getId() != null && typeTarif != null && typeTarif.getId() != null) {
//            if (Objects.equals(this.controleIsGratuitOrForfait(), Boolean.TRUE)) {
//                listRubriqueGarantie.clear();
//                this.updateTableRubriqueGarantie();
//                this.updateTableRubrique();
//                
//                return;
//            }
////            lisTRubrique = rubriqueDao.liteRubriqueForAllCompagnie();
//            lisTRubrique = rubriqueDao.liteRubriqueForAllCompagnie();
//            listRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndGarantie(typeTarif, garantie, entreprise);
//            if (listRubriqueGarantie.isEmpty()) {
//                rubriqueGarantie = new OrclassRubriqueGarantie();
//                rubriqueGarantie.setIdGarantie(garantie);
//                rubriqueGarantie.setIdTypeTarif(typeTarif);
//                listRubriqueGarantie.add(rubriqueGarantie);
//                
//            } else {
//                rubriqueGarantie = new OrclassRubriqueGarantie();
//                rubriqueGarantie.setIdGarantie(garantie);
//                rubriqueGarantie.setIdTypeTarif(typeTarif);
//                listRubriqueGarantie.add(rubriqueGarantie);
//                this.reverseListeRubriqueGarantie();
////                lisTRubrique.removeAll(rubriqueGarantieDao.listeRubriqueHaveGarantieByTarifAndGarantie(typeTarif, garantie, entreprise));
//            }
//            this.updateTableRubriqueGarantie();
//            this.updateTableRubrique();
////            this.updateTableRubriqueGarantieSelecte();
//
//        }
//        
//    }
//    
//    public void resetValeurChoix() {
//        valeurChoixNaturOperation = 0;
//        valeurChoixNaturOperation2 = 0;
//    }
//    
//    public void removeRowIdIsNullForListeRubriqueCaracteristique() {
////        tarif = new OrclassTarif();
//        if (lisRubriqueCaracteristique.isEmpty()) {
//            PrimeFaces.current().executeScript("PF('message').show()");
////                PrimeFaces.current().executeScript(" PF('second').select(0)");
//            return;
//        }
//        rubriqueCaracteristique = lisRubriqueCaracteristique.get(0);
//        if (rubriqueCaracteristique != null && rubriqueCaracteristique.getId() == null) {
//            lisRubriqueCaracteristique.remove(rubriqueCaracteristique);
//        }
//        lisTRubrique = new ArrayList<>();
//        
//        this.updateTableRubriqueCaracteristiqueSelect();
//        this.updateTableRubriqueGarantieSelecte();
//        
//    }
//    
//    public void fermeDialogMessage() {
//        PrimeFaces.current().executeScript("PF('message').hide()");
//        PrimeFaces.current().executeScript(" PF('second').select(0)");
//    }
//    
//    public void updateTableRubriqueGarantie() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":tabprincipal:form1:idirgTable");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('rgTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void updateTableRubriqueGarantieSelecte() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form12:idrcgTable2");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('rcTable2').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void updateTableRubriqueCaracteristique() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":tabprincipal:form2:tabsecond:idrcgTable");
//        table.setValueExpression("sortBy", null);
////
//        PrimeFaces.current().executeScript("PF('rcTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void updateTableRubriqueCaracteristique2() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form12:idrubriqueCaracteristiqueTable");
//        table.setValueExpression("sortBy", null);
////
//        PrimeFaces.current().executeScript("PF('rubriqueCaracteristiqueTable2').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void updateTableTarifCondition() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":tabprincipal:form2:tabsecond:idtarifTable");
//        table.setValueExpression("sortBy", null);
////
//        PrimeFaces.current().executeScript("PF('tarifTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void updateTableRubriqueCaracteristiqueSelect() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form12:idrubriqueCaracteristiqueTable");
//        table.setValueExpression("sortBy", null);
////
//        PrimeFaces.current().executeScript("PF('rubriqueCaracteristiqueTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void updateTableRubriqueCaracteristiqueSelectForTarifCondition() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form12:idrubriqueCaracteristiqueTable22");
//        table.setValueExpression("sortBy", null);
////
//        PrimeFaces.current().executeScript("PF('rubriqueCaracteristiqueTable22').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void updateTableRubrique() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form11:idrubrique");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('rubriqueTable').clearFilters();");
////        PrimeFaces.current().executeScript("PF('rubriqueTable').refresh();");lisTRubrique
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void updateTableCaracteristique() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form11:idcaracteristiqueTable");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('caracteristiqueTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void udpdateDialogEtablirFormule() {
////        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form11:toolb,:form11");
////        PrimeFaces.current().ajax().update(":form11:toolb,:form11");
//        PrimeFaces.current().executeScript(" PF('second').select(1)");
//        PrimeFaces.current().executeScript(" PF('manageFormulleDialog').show()");
//        this.updateTableRubriqueCaracteristiqueSelect();
//        this.updateTableRubriqueGarantieSelecte();
//        
//    }
//
//    /*
//    charger listeTari by rubrique garantie
//     */
//    public void onTabChange(TabChangeEvent event) {
//        OrclassTarif t = null;
//        BigInteger ordre = BigInteger.ZERO;
//        lisTarif.clear();
//        tarif = new OrclassTarif();
//        
//        if ("tarif".equals(event.getTab().getId())) {
//            if (typeTarif != null && typeTarif.getId() != null && garantie != null && garantie.getId() != null) {
//                if (Objects.equals(this.controleIsGratuitOrForfait(), Boolean.TRUE)) {
//                    PrimeFaces.current().executeScript("PF('principal').select(0);");
//                    return;
//                    
//                }
//                this.afficheListeRubrique = Boolean.FALSE;
//            }
//        } else if ("rubriqueGarantie".equals(event.getTab().getId())) {
//            this.afficheListeRubrique = Boolean.TRUE;
//            lisTRubrique = rubriqueDao.liteRubriqueForAllCompagnie();
//            
//            PrimeFaces.current().ajax().update(":form11");
//            this.updateTableRubrique();
////            PrimeFaces.current().executeScript("PF('principal').select(0);");
//        } else {
//            
//        }
//        
//        if (listRubriqueGarantie.isEmpty()) {
//            listeRG = rubriqueGarantieDao.listeRubriqueGarantieByCompagnies(entreprise);
//
////            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, " THE LIST IS EMPTY ", "PLEASE SELECT  VALUE"));
//////            PrimeFaces.current().ajax().update(":tabprincipal:form1");
////            PrimeFaces.current().executeScript("PF('principal').select(0);");
////            return;
//        } else {
//            listeRG = listRubriqueGarantie;
//            rubriqueGarantie = listeRG.get(0);
//            if (rubriqueGarantie.getId() == null) {
//                listeRG.remove(rubriqueGarantie);
//            }
//            
//        }
//        
//        PrimeFaces.current().ajax().update(":tabprincipal:form2");
//        this.updateTableRubriqueGarantieSelecte();
//    }
//    
//    public void chargeTarifByRubriqueGarantieBy() {
//        
//    }
//    
//    public String addRubriqueGarantie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "rubrique.garantie", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        OrclassRubriqueGarantie rg = null;
//        try {
//            if (rubriqueGarantie != null && rubriqueGarantie.getId() == null && rubriqueGarantie.getIdRubrique() != null && rubriqueGarantie.getIdRubrique().getId() != null && rubriqueGarantieDao.finkey(rubriqueGarantie.getIdRubrique(), rubriqueGarantie.getIdTypeTarif(), entreprise, rubriqueGarantie.getIdGarantie(), rubriqueGarantie.getDateEffet(), rubriqueGarantie.getDateEcheance()) == null) {
//                if (rubriqueGarantie.getDateEffet() == null || rubriqueGarantie.getDateEcheance() == null) {
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE  SELECT VALUE FOR DATE", " VALUE DATE IS NULL"));
//                    return null;
//                }
//                rg = rubriqueGarantieDao.lastRubriqueGarantieForCompagny(rubriqueGarantie.getIdRubrique(), rubriqueGarantie.getIdTypeTarif(), entreprise, rubriqueGarantie.getIdGarantie());
////                rubriqueGarantie.setIdEntreprise(entreprise);
//                if (rg != null && rg.getId() != null) {
//                    if (rubriqueGarantie.getDateEcheance().compareTo(rg.getDateEcheance()) == 0 && rubriqueGarantie.getDateEffet().compareTo(rg.getDateEffet()) == 0) {
//                        ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, exception.Error.DUPLICATE_ERROR_INSERT.name(), "FATAL ..CHANGE CODE AND LIBELLE"));
//                        return null;
//                    }
//                }
//                this.garantie.setGlobalCompagnie(Boolean.TRUE);
//                garantieDao.update(garantie);
//                rubriqueGarantie.setIdEntreprise(entreprise);
//                rubriqueGarantieDao.create(rubriqueGarantie);
//                
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                
//                listRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndGarantie(typeTarif, garantie);
//                rubriqueGarantie = new OrclassRubriqueGarantie();
//                rubriqueGarantie.setIdGarantie(garantie);
//                rubriqueGarantie.setIdTypeTarif(typeTarif);
//                listRubriqueGarantie.add(rubriqueGarantie);
//                this.reverseListeRubriqueGarantie();
//                this.updateTableRubriqueGarantie();
//                
//            } else {
//                if (rubriqueGarantieDao.finkey(rubriqueGarantie.getIdRubrique(), rubriqueGarantie.getIdTypeTarif(), entreprise, rubriqueGarantie.getIdGarantie(), rubriqueGarantie.getDateEffet(), rubriqueGarantie.getDateEcheance()) != null) {
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, exception.Error.DUPLICATE_ERROR_INSERT.name(), "FATAL ..CHANGE CODE AND LIBELLE"));
//                } else {
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "FATAL ERROR"));
//                }
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "rubrique.garantie", exception.Error.FATAL_ERROR + "", new Object[]{"rubrique.garantie"});
//            logger.error("Erreur Survenu", th);
//        }
//        
//        return null;
//    }
//    
//    public String addRubriqueCaracteristique() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "rubrique.caracterisque", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        OrclassRubriqueCaracteristique rc = null;
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (rubriqueCaracteristique != null && rubriqueCaracteristique.getId() == null && rubriqueCaracteristique.getIdCaracteristiques() != null && rubriqueCaracteristique.getIdCaracteristiques().getId() != null && rubriqueCaracteristiqueDao.finKey(entreprise, rubriqueCaracteristique.getIdCategories(), rubriqueCaracteristique.getIdCaracteristiques(), rubriqueGarantieSelecte) == null) {
//                if (rubriqueCaracteristique.getStatutCaracteristique() == null) {
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE  SELECT VALUE FOR STAUT", " VALUE STATUT IS NULL"));
//                    return null;
//                }
//                
//                rubriqueCaracteristique.setIdEntreprise(entreprise);
//                rubriqueCaracteristique.setIdRubriqueGarantie(rubriqueGarantieSelecte);
//                
//                rubriqueCaracteristiqueDao.create(rubriqueCaracteristique);
//                
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                
//                lisRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagnies(entreprise, categories, rubriqueGarantieSelecte);
//                this.setCategories(rubriqueGarantieSelecte.getIdGarantie().getIdCategories());
//                this.setRubrique(rubriqueGarantieSelecte.getIdRubrique());
//                this.setGarantie(rubriqueGarantieSelecte.getIdGarantie());
//                this.setTypeTarif(rubriqueGarantieSelecte.getIdTypeTarif());
//                rc = new OrclassRubriqueCaracteristique();
//                rc.setIdRubriqueGarantie(rubriqueGarantieSelecte);
//                rc.setIdCategories(categories);
//                
//                lisRubriqueCaracteristique.add(rc);
//                lisTCaracteristiques.remove(caracteristiques);
//                this.reverseListeRubriqueCaracteristique();
//                this.updateTableRubriqueCaracteristique();
//                this.updateTableCaracteristique();
//                PrimeFaces.current().ajax().update(":tabprincipal:form2,:tabsecond:idrcgTable");
//                
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "FATAL ERROR"));
//                
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "rubrique.caracterisque", exception.Error.FATAL_ERROR + "", new Object[]{"rubrique.caracterisque"});
//            logger.error("Erreur Survenu", th);
//        }
//        
//        return null;
//    }
//    
//    public String updateRubriqueCaracteristique() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "rubrique.caracterisque", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (rubriqueCaracteristique != null && rubriqueCaracteristique.getId() != null) {
//                
//                rubriqueCaracteristiqueDao.update(rubriqueCaracteristique);
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                
//                lisRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagnies(entreprise, rubriqueGarantieSelecte.getIdGarantie().getIdCategories(), rubriqueGarantieSelecte);
//                this.setCategories(rubriqueGarantieSelecte.getIdGarantie().getIdCategories());
//                this.setRubrique(rubriqueGarantieSelecte.getIdRubrique());
//                this.setGarantie(rubriqueGarantieSelecte.getIdGarantie());
//                this.setTypeTarif(rubriqueGarantieSelecte.getIdTypeTarif());
//                this.rubriqueCaracteristique = new OrclassRubriqueCaracteristique();
//                rubriqueCaracteristique.setIdRubriqueGarantie(rubriqueGarantieSelecte);
//                rubriqueCaracteristique.setIdCategories(categories);
////                rubriqueCaracteristique.setIdRubrique(rubrique);
//
//                lisRubriqueCaracteristique.add(rubriqueCaracteristique);
//                this.reverseListeRubriqueCaracteristique();
//                this.updateTableRubriqueCaracteristique();
//                
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "FATAL ERROR"));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "rubrique.caracterisque", exception.Error.FATAL_ERROR + "", new Object[]{"rubrique.caracterisque"});
//            logger.error("Erreur Survenu", th);
//        }
//        
//        return null;
//    }
//    
//    public String deleteRubriqueCaracteristique() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "rubrique.caracterisque", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (rubriqueCaracteristique != null && rubriqueCaracteristique.getId() != null) {
//                
//                rubriqueCaracteristiqueDao.delete(rubriqueCaracteristique);
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.delete.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                
//                lisRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagnies(entreprise, rubriqueGarantieSelecte.getIdGarantie().getIdCategories(), rubriqueGarantieSelecte);
//                this.setCategories(rubriqueGarantieSelecte.getIdGarantie().getIdCategories());
//                this.setRubrique(rubriqueGarantieSelecte.getIdRubrique());
//                this.setGarantie(rubriqueGarantieSelecte.getIdGarantie());
//                this.setTypeTarif(rubriqueGarantieSelecte.getIdTypeTarif());
//                this.rubriqueCaracteristique = new OrclassRubriqueCaracteristique();
//                rubriqueCaracteristique.setIdRubriqueGarantie(rubriqueGarantieSelecte);
//                rubriqueCaracteristique.setIdCategories(categories);
////                rubriqueCaracteristique.setIdRubrique(rubrique);
//
//                lisRubriqueCaracteristique.add(rubriqueCaracteristique);
//                lisTCaracteristiques.add(caracteristiques);
//                this.reverseListeRubriqueCaracteristique();
//                this.updateTableRubriqueCaracteristique();
//                this.updateTableCaracteristique();
//                
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "rubrique.caracterisque", exception.Error.FATAL_ERROR + "", new Object[]{"rubrique.caracterisque"});
//            logger.error("Erreur Survenu", th);
//        }
//        
//        return null;
//    }
//
//    /*
//    tarif condotion
//     */
//    public String addTarifCondition() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "condition", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (tarifCondition != null && tarifCondition.getId() == null && tarifCondition.getIdRubriqueCaracteristique() != null && tarifCondition.getIdRubriqueCaracteristique().getId() != null && tarifConditionDao.finKey(tarifCondition.getIdTarif(), tarifCondition.getIdRubriqueCaracteristique(), entreprise) == null) {
//                
//                tarifCondition.setIdEntreprise(entreprise);
//                tarifCondition.setDateCreation(new Date());
//                tarifConditionDao.create(tarifCondition);
//                
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                
//                lisTarifConditions = tarifConditionDao.listConditionByTarif(tarifCondition.getIdTarif(), entreprise);
//                
//                tarifCondition = new OrclassTarifCondition();
//                tarifCondition.setIdTarif(tarif);
//                lisTarifConditions.add(tarifCondition);
//                this.reverseListeTarifCondition();
//                this.updateTableTarifCondition();
//                
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "FATAL ERROR"));
//                
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "condition", exception.Error.FATAL_ERROR + "", new Object[]{"condition"});
//            logger.error("Erreur Survenu", th);
//        }
//        PrimeFaces.current().executeScript(" PF('second').select(1)");
//        
//        return null;
//    }
//    
//    public String updateTarifCondition() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "rubrique.caracterisque", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (tarifCondition != null && tarifCondition.getId() != null) {
//                
//                tarifConditionDao.update(tarifCondition);
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                
//                lisTarifConditions = tarifConditionDao.listConditionByTarif(tarifCondition.getIdTarif(), entreprise);
//                
//                tarifCondition = new OrclassTarifCondition();
//                tarifCondition.setIdTarif(tarif);
//                lisTarifConditions.add(tarifCondition);
//                this.reverseListeTarifCondition();
//                this.updateTableTarifCondition();
//                
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "FATAL ERROR"));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "rubrique.caracterisque", exception.Error.FATAL_ERROR + "", new Object[]{"rubrique.caracterisque"});
//            logger.error("Erreur Survenu", th);
//        }
//        PrimeFaces.current().executeScript(" PF('second').select(1)");
//        
//        return null;
//    }
//    
//    public String deleteTarifCondition() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "rubrique.caracterisque", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (tarifCondition != null && tarifCondition.getId() != null) {
//                
//                tarifConditionDao.delete(tarifCondition);
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.delete.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                
//                lisTarifConditions = tarifConditionDao.listConditionByTarif(tarifCondition.getIdTarif(), entreprise);
//                
//                tarifCondition = new OrclassTarifCondition();
//                tarifCondition.setIdTarif(tarif);
//                lisTarifConditions.add(tarifCondition);
//                this.reverseListeTarifCondition();
//                this.updateTableTarifCondition();
//                
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "rubrique.caracterisque", exception.Error.FATAL_ERROR + "", new Object[]{"rubrique.caracterisque"});
//            logger.error("Erreur Survenu", th);
//        }
//        PrimeFaces.current().executeScript(" PF('second').select(1)");
//        return null;
//    }
//    
//    public void changerLaDateDecheance() {
//        OrclassRubriqueGarantie rg = null;
//        if (rubriqueGarantie != null && rubriqueGarantie.getId() != null) {
//            rg = rubriqueGarantie;
//            rubriqueGarantie = listRubriqueGarantie.get(0);
//            rubriqueGarantie.setIdGarantie(rg.getIdGarantie());
//            rubriqueGarantie.setIdRubrique(rg.getIdRubrique());
//            rubriqueGarantie.setIdTypeTarif(rg.getIdTypeTarif());
//            listRubriqueGarantie.set(0, rubriqueGarantie);
//            this.updateTableRubriqueGarantie();
//        }
//    }
//    
//    public String addTarif() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "tarif", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (tarif != null && tarif.getId() == null && tarif.getIdRubriqueGarantie() != null && tarif.getIdRubriqueGarantie().getId() != null) {
////                if (rubriqueCategorie.getUtilisationRubrique() == null) {
////                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE  SELECT VALUE FOR UTILISATION", ""));
////                    return null;
////                }
//                tarif.setIdEntreprise(entreprise);
//                tarifDao.create(tarif);
//                
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "FATAL ERROR"));
//                
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "tarif", exception.Error.FATAL_ERROR + "", new Object[]{"tarif"});
//            logger.error("Erreur Survenu", th);
//        }
//        
//        return null;
//    }
//    
//    public void removeRubriqueByListe() {
////        listRubriqueGarantie.remove(rubriqueGarantie);
//        listRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndGarantie(typeTarif, garantie, entreprise);
//        rubriqueGarantie = new OrclassRubriqueGarantie();
//        rubriqueGarantie.setIdGarantie(garantie);
//        rubriqueGarantie.setIdTypeTarif(typeTarif);
//        listRubriqueGarantie.add(rubriqueGarantie);
//        
//        PrimeFaces.current().ajax().update(":tabprincipal:form1");
//        this.updateRubriqueGarantie();
//        
//    }
//    
//    public void removeTarifConditionByListe() {
//        lisTarifConditions.remove(tarifCondition);
//        lisTarifConditions = tarifConditionDao.listConditionByTarif(tarifCondition.getIdTarif(), entreprise);
//        
//        tarifCondition = new OrclassTarifCondition();
//        tarifCondition.setIdTarif(tarif);
//        
//        lisTarifConditions.add(tarifCondition);
//        
//        PrimeFaces.current().ajax().update(":tabprincipal:tabsecond:form2");
//        PrimeFaces.current().executeScript(" PF('second').select(1)");
//        
//    }
//    
//    public void removeRubriqueByListeRubriqueCaracteristique() {
//        lisRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagnies(entreprise, categories, rubriqueGarantieSelecte);
//        OrclassRubriqueCaracteristique rc = null;
//        this.setCategories(rubriqueGarantieSelecte.getIdGarantie().getIdCategories());
//        this.setRubrique(rubriqueGarantieSelecte.getIdRubrique());
//        this.setGarantie(rubriqueGarantieSelecte.getIdGarantie());
//        this.setTypeTarif(rubriqueGarantieSelecte.getIdTypeTarif());
//        rc = new OrclassRubriqueCaracteristique();
//        rc.setIdRubriqueGarantie(rubriqueGarantieSelecte);
////        rc.setIdGarantie(garantie);
//        rc.setIdCategories(categories);
////        rc.setIdRubrique(rubrique);
//
//        lisRubriqueCaracteristique.add(rc);
//        this.reverseListeRubriqueCaracteristique();
//        this.updateTableRubriqueCaracteristique();
//        PrimeFaces.current().ajax().update(":tabprincipal:form2:tabsecond:idrcgTable");
//        
//    }
//    
//    public void chargeRubriqueInListRubriqueGarentie() {
//        if (rubrique != null && rubrique.getId() != null && !listRubriqueGarantie.isEmpty()) {
//            rubriqueGarantie = listRubriqueGarantie.get(0);
//            rubriqueGarantie.setIdRubrique(rubrique);
//            listRubriqueGarantie.set(0, rubriqueGarantie);
////            rubrique = new OrclassRubrique();
//
//        } else if (listRubriqueGarantie.isEmpty()) {
//            rubriqueGarantie = new OrclassRubriqueGarantie();
//            rubriqueGarantie.setIdRubrique(rubrique);
//            listRubriqueGarantie.add(rubriqueGarantie);
//        }
////        this.updateTableRubrique();
//
//    }
//    
//    public void chargeRubriqueInListRubriqueCaracteristiqueByCaracteristique() {
//        if (caracteristiques != null && caracteristiques.getId() != null) {
//            if (lisRubriqueCaracteristique.isEmpty()) {
//                OrclassRubriqueCaracteristique rc = new OrclassRubriqueCaracteristique();
//                rc.setIdCategories(categories);
//                rc.setIdRubriqueGarantie(rubriqueGarantieSelecte);
//                rc.setIdCaracteristiques(caracteristiques);
//                lisRubriqueCaracteristique.add(rc);
//                
//            } else {
//                rubriqueCaracteristique = lisRubriqueCaracteristique.get(0);
//                rubriqueCaracteristique.setIdCaracteristiques(caracteristiques);
//                lisRubriqueCaracteristique.set(0, rubriqueCaracteristique);
//            }
//            
//        }
//        
//    }
//    
//    public String updateRubriqueGarantie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "rubrique.garantie", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (rubriqueGarantie != null && rubriqueGarantie.getId() != null) {
//                
//                rubriqueGarantieDao.update(rubriqueGarantie);
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                
//                listRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndGarantie(typeTarif, garantie, entreprise);
//                rubriqueGarantie = new OrclassRubriqueGarantie();
//                rubriqueGarantie.setIdGarantie(garantie);
//                rubriqueGarantie.setIdTypeTarif(typeTarif);
//                listRubriqueGarantie.add(rubriqueGarantie);
//                this.reverseListeRubriqueGarantie();
//                this.updateTableRubriqueGarantie();
//                
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "FATAL ERROR"));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "rubrique.garantie", exception.Error.FATAL_ERROR + "", new Object[]{"rubrique.garantie"});
//            logger.error("Erreur Survenu", th);
//        }
//        
//        return null;
//    }
//    
//    public String deleteRubriqueGarantie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "rubrique.garantie", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (rubriqueGarantie != null && rubriqueGarantie.getId() != null) {
//                
//                rubriqueGarantieDao.delete(rubriqueGarantie);
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.delete.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                listRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndGarantie(typeTarif, garantie, entreprise);
//                rubriqueGarantie = new OrclassRubriqueGarantie();
//                rubriqueGarantie.setIdGarantie(garantie);
//                rubriqueGarantie.setIdTypeTarif(typeTarif);
//                listRubriqueGarantie.add(rubriqueGarantie);
//                this.reverseListeRubriqueGarantie();
//                this.updateTableRubriqueGarantie();
//                
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "rubrique.garantie", exception.Error.FATAL_ERROR + "", new Object[]{"rubrique.garantie"});
//            logger.error("Erreur Survenu", th);
//        }
//        
//        return null;
//    }
//    
//    public void chargeRubriqueCaracteristiqueByRubrique() {
//        if (rubriqueGarantieSelect != null && rubriqueGarantieSelect.getId() != null) {
//            if (valeurChoixNaturOperation == 3 && valeurChoixNaturOperation2 == 0) {
//                tarif.setIdRubrique1(rubriqueGarantieSelect.getIdRubrique().getCode());
//            } else if (valeurChoixNaturOperation2 == 33) {
//                tarif.setIdRubrique2(rubriqueGarantieSelect.getIdRubrique().getCode());
//            }
////            rubrique = new OrclassRubrique();
////            this.updateTableRubrique();
//            PrimeFaces.current().ajax().update(":form11");
//            PrimeFaces.current().executeScript("PF('manageFormulleDialog').show()");
//        }
//    }
//    
//    public void chargeDialogRubriqueCaracterististique() {
//        if (rubriqueCaracteristiqueSelecte == null) {
//            rubriqueCaracteristiqueSelecte = new OrclassRubriqueCaracteristique();
//            
//        }
//        PrimeFaces.current().executeScript("PF('manageRubriqueCaracteristiqueDialog').show()");
//        this.updateTableRubriqueCaracteristique2();
//        
//    }
//    
//    public void chargeCaracteristiqueByRubriqueCaracteristique() {
//        if (valeurChoixNaturOperation == 5 && valeurChoixNaturOperation2 == 0) {
//            if (rubriqueCaracteristiqueSelecte != null && rubriqueCaracteristiqueSelecte.getId() != null) {
//                tarif.setIdCaracteristiques1(rubriqueCaracteristiqueSelecte.getIdCaracteristiques().getCode());
//            }
//            
//        } else if (valeurChoixNaturOperation2 == 55) {
//            if (rubriqueCaracteristiqueSelecte != null && rubriqueCaracteristiqueSelecte.getId() != null) {
//                tarif.setIdCaracteristiques2(rubriqueCaracteristiqueSelecte.getIdCaracteristiques().getCode());
//            }
//        }
//        
//        PrimeFaces.current().ajax().update(":form11");
//        PrimeFaces.current().executeScript("PF('manageFormulleDialog').show()");
//    }
//    
//    public String updateTarif() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "tarif", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (tarif != null && tarif.getId() != null) {
//                
//                tarifDao.update(tarif);
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "FATAL ERROR"));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "rubrique.garantie", exception.Error.FATAL_ERROR + "", new Object[]{"rubrique.garantie"});
//            logger.error("Erreur Survenu", th);
//        }
//        
//        return null;
//    }
//    
//    public String valideTarif() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "tarif", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        long valeur = -1;
//        try {
//            if (tarif != null || tarif.getId() == null) {
//                BigInteger numberOrder = tarifDao.checkLastNumero8ordre(rubriqueGarantieSelecte, entreprise);
//                numberOrder = numberOrder.add(BigInteger.ONE);
//                if (tarifDao.finKey(rubriqueGarantieSelecte, entreprise) == null) {
//                    tarif.setIdEntreprise(entreprise);
//                    tarif.setDateCreation(new Date());
//                    tarif.setIdRubriqueGarantie(rubriqueGarantieSelecte);
//                    tarif.setNumero_Ordre(numberOrder);
////                    if (tarif.getConstant1() == null) {
////                        tarif.setConstant1(BigInteger.valueOf(valeur));
////                    }
////                    if (tarif.getConstant2() == null) {
////                        tarif.setConstant2(BigInteger.valueOf(valeur));
////                    }
////                    tarif.setLimiteInfProrata(BigInteger.valueOf(valeur));
////                    tarif.setLimiteInferieur(BigInteger.valueOf(valeur));
////                    tarif.setLimiteSupProrata(BigInteger.valueOf(valeur));
////                    tarif.setLimiteSuperieur(BigInteger.valueOf(valeur));
//
//                    tarifDao.create(tarif);
//                    this.chargeChargerTariconditionByTarif();
//                    valeurChoixNaturOperation = 0;
//                    valeurChoixNaturOperation2 = 0;
//                } else {
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "VALUES IS EXISTS ", "PLEASE TRY AGAINST"));
//                    return null;
//                }
//                
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "FATAL ERROR"));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "tarif", exception.Error.FATAL_ERROR + "", new Object[]{"tarif"});
//            logger.error("Erreur Survenu", th);
//        }
//        
//        PrimeFaces.current().executeScript(" PF('second').select(1)");
//        return null;
//    }
//    
//    public String deleteTarif() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "tarif", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (tarif != null && tarif.getId() != null) {
//                
//                tarifDao.delete(tarif);
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "rubrique.garantie", exception.Error.FATAL_ERROR + "", new Object[]{"rubrique.garantie"});
//            logger.error("Erreur Survenu", th);
//        }
//        
//        return null;
//    }
//    
//    public List<SelectItem> getModeCalcul() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        
//        for (ModeCalculRubrique mc : ModeCalculRubrique.values()) {
//            items.add(new SelectItem(mc, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, mc.name(), null, myLoc)));
//        }
//        
//        return items;
//    }
//    
//    public List<SelectItem> getStatutCaracteristique() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        
//        for (StatutCaracteristique s : StatutCaracteristique.values()) {
//            items.add(new SelectItem(s, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, s.name(), null, myLoc)));
//        }
//        
//        return items;
//    }
//    
//    public List<SelectItem> getNatureOperationForTarif() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        
//        for (NatureOperationTarif n : NatureOperationTarif.values()) {
//            if (n.equals(NatureOperationTarif.operation)) {
//                continue;
//            }
//            items.add(new SelectItem(n, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, n.name(), null, myLoc)));
//        }
//        
//        return items;
//    }
//    
//    public List<SelectItem> getOperationForTarif() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        
//        for (OperationsTarif o : OperationsTarif.values()) {
//            
//            items.add(new SelectItem(o, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, o.name(), null, myLoc)));
//        }
//        
//        return items;
//    }
//    
//    public void reverseListeRubriqueGarantie() {
//        
//        List<OrclassRubriqueGarantie> result = new ArrayList<>();
//        for (int i = listRubriqueGarantie.size() - 1; i >= 0; i--) {
//            result.add(listRubriqueGarantie.get(i));
//        }
//        
//        this.setListRubriqueGarantie(result);
////        this.updateTableRubriqueCategorie();
//    }
//    
//    public void reverseListeRubriqueCaracteristique() {
//        
//        List<OrclassRubriqueCaracteristique> result = new ArrayList<>();
//        for (int i = lisRubriqueCaracteristique.size() - 1; i >= 0; i--) {
//            result.add(lisRubriqueCaracteristique.get(i));
//        }
//        
//        this.setLisRubriqueCaracteristique(result);
////        this.updateTableRubriqueCategorie();
//    }
//    
//    public List<OrclassRubriqueGarantie> getListRubriqueGarantie() {
//        return listRubriqueGarantie;
//    }
//    
//    public void setListRubriqueGarantie(List<OrclassRubriqueGarantie> listRubriqueGarantie) {
//        this.listRubriqueGarantie = listRubriqueGarantie;
//    }
//    
//    public List<OrclassTarif> getLisTarif() {
//        return lisTarif;
//    }
//    
//    public void setLisTarif(List<OrclassTarif> lisTarif) {
//        this.lisTarif = lisTarif;
//    }
//    
//    public OrclassTarif getTarif() {
//        return tarif;
//    }
//    
//    public void setTarif(OrclassTarif tarif) {
//        this.tarif = tarif;
//    }
//    
//    public OrclassRubriqueGarantie getRubriqueGarantie() {
//        return rubriqueGarantie;
//    }
//    
//    public void setRubriqueGarantie(OrclassRubriqueGarantie rubriqueGarantie) {
//        this.rubriqueGarantie = rubriqueGarantie;
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
//    public OrclassTypeTarifDao getTypeTarifDao() {
//        return typeTarifDao;
//    }
//    
//    public void setTypeTarifDao(OrclassTypeTarifDao typeTarifDao) {
//        this.typeTarifDao = typeTarifDao;
//    }
//    
//    public OrclassRubrique getRubrique() {
//        return rubrique;
//    }
//    
//    public void setRubrique(OrclassRubrique rubrique) {
//        this.rubrique = rubrique;
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
//    public Collection<OrclassCategories> getLisCategories() {
//        return lisCategories;
//    }
//    
//    public void setLisCategories(Collection<OrclassCategories> lisCategories) {
//        this.lisCategories = lisCategories;
//    }
//    
//    public Collection<OrclassGarantie> getLisGarantie() {
//        return lisGarantie;
//    }
//    
//    public void setLisGarantie(Collection<OrclassGarantie> lisGarantie) {
//        this.lisGarantie = lisGarantie;
//    }
//    
//    public Collection<OrclassTypeTarif> getLisTypeTarif() {
//        return lisTypeTarif;
//    }
//    
//    public void setLisTypeTarif(Collection<OrclassTypeTarif> lisTypeTarif) {
//        this.lisTypeTarif = lisTypeTarif;
//    }
//    
//    public Collection<OrclassRubrique> getLisTRubrique() {
//        return lisTRubrique;
//    }
//    
//    public void setLisTRubrique(Collection<OrclassRubrique> lisTRubrique) {
//        this.lisTRubrique = lisTRubrique;
//    }
//    
//    public OrclassTypeTarif getTypeTarif() {
//        return typeTarif;
//    }
//    
//    public void setTypeTarif(OrclassTypeTarif typeTarif) {
//        this.typeTarif = typeTarif;
//    }
//    
//    public List<OrclassRubriqueGarantie> getFilterRubriqueGarantie() {
//        return filterRubriqueGarantie;
//    }
//    
//    public void setFilterRubriqueGarantie(List<OrclassRubriqueGarantie> filterRubriqueGarantie) {
//        this.filterRubriqueGarantie = filterRubriqueGarantie;
//    }
//    
//    public Collection<OrclassCaracteristiques> getLisTCaracteristiques() {
//        return lisTCaracteristiques;
//    }
//    
//    public void setLisTCaracteristiques(Collection<OrclassCaracteristiques> lisTCaracteristiques) {
//        this.lisTCaracteristiques = lisTCaracteristiques;
//    }
//    
//    public Collection<OrclassUnite_Caracteristique> getLisTUnite_Caracteristique() {
//        return lisTUnite_Caracteristique;
//    }
//    
//    public void setLisTUnite_Caracteristique(Collection<OrclassUnite_Caracteristique> lisTUnite_Caracteristique) {
//        this.lisTUnite_Caracteristique = lisTUnite_Caracteristique;
//    }
//    
//    public List<OrclassRubrique> getFilterRubrique() {
//        return filterRubrique;
//    }
//    
//    public void setFilterRubrique(List<OrclassRubrique> filterRubrique) {
//        this.filterRubrique = filterRubrique;
//    }
//    
//    public OrclassCaracteristiques getCaracteristiques() {
//        return caracteristiques;
//    }
//    
//    public void setCaracteristiques(OrclassCaracteristiques caracteristiques) {
//        this.caracteristiques = caracteristiques;
//    }
//    
//    public OrclassRubriqueCaracteristique getRubriqueCaracteristique() {
//        return rubriqueCaracteristique;
//    }
//    
//    public void setRubriqueCaracteristique(OrclassRubriqueCaracteristique rubriqueCaracteristique) {
//        this.rubriqueCaracteristique = rubriqueCaracteristique;
//    }
//    
//    public OrclassRubriqueGarantie getRubriqueGarantieSelecte() {
//        return rubriqueGarantieSelecte;
//    }
//    
//    public void setRubriqueGarantieSelecte(OrclassRubriqueGarantie rubriqueGarantieSelecte) {
//        this.rubriqueGarantieSelecte = rubriqueGarantieSelecte;
//    }
//    
//    public List<OrclassRubriqueCaracteristique> getLisRubriqueCaracteristique() {
//        return lisRubriqueCaracteristique;
//    }
//    
//    public void setLisRubriqueCaracteristique(List<OrclassRubriqueCaracteristique> lisRubriqueCaracteristique) {
//        this.lisRubriqueCaracteristique = lisRubriqueCaracteristique;
//    }
//    
//    public Collection<OrclassRubriqueCaracteristique> getFilterRubriqueCaracteristique() {
//        return filterRubriqueCaracteristique;
//    }
//    
//    public void setFilterRubriqueCaracteristique(Collection<OrclassRubriqueCaracteristique> filterRubriqueCaracteristique) {
//        this.filterRubriqueCaracteristique = filterRubriqueCaracteristique;
//    }
//    
//    public Collection<OrclassCaracteristiques> getFilterCaracteristique() {
//        return filterCaracteristique;
//    }
//    
//    public void setFilterCaracteristique(Collection<OrclassCaracteristiques> filterCaracteristique) {
//        this.filterCaracteristique = filterCaracteristique;
//    }
//    
//    public int getValeurChoixNaturOperation() {
//        return valeurChoixNaturOperation;
//    }
//    
//    public void setValeurChoixNaturOperation(int valeurChoixNaturOperation) {
//        this.valeurChoixNaturOperation = valeurChoixNaturOperation;
//    }
//    
//    public OrclassRubriqueCaracteristique getRubriqueCaracteristiqueSelecte() {
//        return rubriqueCaracteristiqueSelecte;
//    }
//    
//    public void setRubriqueCaracteristiqueSelecte(OrclassRubriqueCaracteristique rubriqueCaracteristiqueSelecte) {
//        this.rubriqueCaracteristiqueSelecte = rubriqueCaracteristiqueSelecte;
//    }
//    
//    public OrclassRubriqueGarantie getRubriqueGarantieSelect() {
//        return rubriqueGarantieSelect;
//    }
//    
//    public void setRubriqueGarantieSelect(OrclassRubriqueGarantie rubriqueGarantieSelect) {
//        this.rubriqueGarantieSelect = rubriqueGarantieSelect;
//    }
//    
//    public Collection<OrclassGarantie> getFilterOrclassGarantie() {
//        return filterOrclassGarantie;
//    }
//    
//    public void setFilterOrclassGarantie(Collection<OrclassGarantie> filterOrclassGarantie) {
//        this.filterOrclassGarantie = filterOrclassGarantie;
//    }
//    
//    public Collection<OrclassRubriqueGarantie> getFilterOrclassRubriqueGarantie() {
//        return filterOrclassRubriqueGarantie;
//    }
//    
//    public void setFilterOrclassRubriqueGarantie(Collection<OrclassRubriqueGarantie> filterOrclassRubriqueGarantie) {
//        this.filterOrclassRubriqueGarantie = filterOrclassRubriqueGarantie;
//    }
//    
//    public int getValeurChoixNaturOperation2() {
//        return valeurChoixNaturOperation2;
//    }
//    
//    public void setValeurChoixNaturOperation2(int valeurChoixNaturOperation2) {
//        this.valeurChoixNaturOperation2 = valeurChoixNaturOperation2;
//    }
//    
//    public OrclassTarifCondition getTarifCondition() {
//        return tarifCondition;
//    }
//    
//    public void setTarifCondition(OrclassTarifCondition tarifCondition) {
//        this.tarifCondition = tarifCondition;
//    }
//    
//    public List<OrclassTarifCondition> getLisTarifConditions() {
//        return lisTarifConditions;
//    }
//    
//    public void setLisTarifConditions(List<OrclassTarifCondition> lisTarifConditions) {
//        this.lisTarifConditions = lisTarifConditions;
//    }
//    
//    public List<OrclassRubriqueCaracteristique> getLisRubriqueCaracteristiqueForTarifCondition() {
//        return lisRubriqueCaracteristiqueForTarifCondition;
//    }
//    
//    public void setLisRubriqueCaracteristiqueForTarifCondition(List<OrclassRubriqueCaracteristique> lisRubriqueCaracteristiqueForTarifCondition) {
//        this.lisRubriqueCaracteristiqueForTarifCondition = lisRubriqueCaracteristiqueForTarifCondition;
//    }
//    
//    public OrclassRubriqueCaracteristique getRubriqueCaracteristiqueSelecteForTarifCondition() {
//        return rubriqueCaracteristiqueSelecteForTarifCondition;
//    }
//    
//    public void setRubriqueCaracteristiqueSelecteForTarifCondition(OrclassRubriqueCaracteristique rubriqueCaracteristiqueSelecteForTarifCondition) {
//        this.rubriqueCaracteristiqueSelecteForTarifCondition = rubriqueCaracteristiqueSelecteForTarifCondition;
//    }
//    
//    public List<OrclassTarifCondition> getFilterOrclassTarifCondition() {
//        return filterOrclassTarifCondition;
//    }
//    
//    public void setFilterOrclassTarifCondition(List<OrclassTarifCondition> filterOrclassTarifCondition) {
//        this.filterOrclassTarifCondition = filterOrclassTarifCondition;
//    }
//    
//    public List<OrclassRubriqueGarantie> getListeRG() {
//        return listeRG;
//    }
//    
//    public void setListeRG(List<OrclassRubriqueGarantie> listeRG) {
//        this.listeRG = listeRG;
//    }
//    
//    public Boolean accessCreerRubriqueGarantie() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.gestion_Tarif.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer_rubrique_garantie.name(), fon);
//            
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public Boolean accessCreerRubriqueCaracterisque() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.gestion_Tarif.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer_rubrique_caracteristique.name(), fon);
//            
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public Boolean accessModifierRubriqueGarantie() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.gestion_Tarif.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier_rubrique_garantie.name(), fon);
//            System.out.println("accees Mpdifier :" + serviceAccess.accessAction(user, action, menu));
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public Boolean accessModifierRubriqueCaracteristique() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.gestion_Tarif.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier_rubrique_caracteristique.name(), fon);
//            System.out.println("accees Mpdifier :" + serviceAccess.accessAction(user, action, menu));
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public Boolean accessSupprimerRubriqueGaranties() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.gestion_Tarif.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer_rubrique_Garantie.name(), fon);
//            System.out.println("accees supprimer :" + serviceAccess.accessAction(user, action, menu));
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public Boolean accessSupprimerRubriqueCaracteristique() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.gestion_Tarif.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer_rubrique_caracteristique.name(), fon);
//            System.out.println("accees supprimer :" + serviceAccess.accessAction(user, action, menu));
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public Boolean accessCreerFormule() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.gestion_Tarif.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer_formule.name(), fon);
//            System.out.println("accees supprimer :" + serviceAccess.accessAction(user, action, menu));
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
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
//    public List<OrclassRefGaranties> getListeRefGarantieses() {
//        return ListeRefGarantieses;
//    }
//    
//    public void setListeRefGarantieses(List<OrclassRefGaranties> ListeRefGarantieses) {
//        this.ListeRefGarantieses = ListeRefGarantieses;
//    }
//    
//    public OrclassRefGaranties getRefGaranties() {
//        return refGaranties;
//    }
//    
//    public void setRefGaranties(OrclassRefGaranties refGaranties) {
//        this.refGaranties = refGaranties;
//    }
//    
//    public List<OrclassRefGaranties> getFilterRefGarantie() {
//        return filterRefGarantie;
//    }
//    
//    public void setFilterRefGarantie(List<OrclassRefGaranties> filterRefGarantie) {
//        this.filterRefGarantie = filterRefGarantie;
//    }
//    
//    public Boolean getAfficheListeRubrique() {
//        return afficheListeRubrique;
//    }
//    
//    public void setAfficheListeRubrique(Boolean afficheListeRubrique) {
//        this.afficheListeRubrique = afficheListeRubrique;
//    }
//    
//    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
//        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
////        if (LangUtils.isBlank(filterText)) {
////            return true;
////        }
////        int filterInt = getInteger(filterText);
//
//        OrclassRubriqueGarantie gar = (OrclassRubriqueGarantie) value;
//        if (gar.getId() == null) {
//            return true;
//        }
//        return gar.getIdGarantie().getIdRefGaranties().getCode().toLowerCase().contains(filterText)
//                || gar.getIdGarantie().getIdRefGaranties().getLibelle().toLowerCase().contains(filterText);
//    }
//    
//    public boolean globalFilterFunctionRubriCaracter(Object value, Object filter, Locale locale) {
//        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
////        if (LangUtils.isBlank(filterText)) {
////            return true;
////        }
////        int filterInt = getInteger(filterText);
//
//        OrclassRubriqueCaracteristique gar = (OrclassRubriqueCaracteristique) value;
//        if (gar.getId() == null) {
//            return true;
//        }
//        return gar.getIdCaracteristiques().getCode().toLowerCase().contains(filterText)
//                || gar.getIdCaracteristiques().getLibelle().toLowerCase().contains(filterText);
//    }
//    
//}
