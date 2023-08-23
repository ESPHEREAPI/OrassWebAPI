///////*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.administration;
//
//import Entreprise.OracleConnection;
//import Excell.IExcell;
//import dao.OrclassBranchesDao;
//import dao.OrclassCategoriesDao;
//import dao.OrclassElt_Categorie_CompagnieDao;
//import dao.OrclassGarantieDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassRefGarantiesDao;
//import dao.OrclassRegistreProductionDao;
//import dao.OrclassRegistreSinistreDao;
//import dao.OrclasseType_GarantieDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleAdministration;
//import enums.FonctionnaliteModuleParametrage;
//import enums.LibelleBranche;
//import enums.LibelleCategorie;
//import enums.LibelleClasse;
//import enums.LibelleRisque;
//import enums.ModeApplicationSante;
//import enums.ModeCalcul;
//import enums.NatureGarantie;
//import enums.TypeGarantie;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.Serializable;
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.application.FacesMessage;
//import javax.faces.component.UIComponent;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.faces.event.ValueChangeEvent;
//import javax.faces.model.SelectItem;
//import javax.inject.Named;
//import javax.faces.view.ViewScoped;
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.ConstraintViolationException;
//import modele.OrclassActions;
//import modele.OrclassBranches;
//import modele.OrclassCategories;
//import modele.OrclassClasses;
//import modele.OrclassElt_Categorie_Compagnie;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassGarantie;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassRefGaranties;
//import modele.OrclassRegistreProduction;
//import modele.OrclassRegistreSinistre;
//import modele.OrclassUtilisateurs;
//import modele.OrclasseType_Garantie;
//import org.apache.commons.collections.comparators.ComparatorChain;
//import org.primefaces.PrimeFaces;
//import org.primefaces.event.CellEditEvent;
//import org.primefaces.event.RowEditEvent;
//import org.primefaces.model.SortMeta;
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
//@Named(value = "categorieController")
//@ViewScoped
//public class CategorieController implements Serializable {
//    
//    private static final Logger logger = LoggerFactory.getLogger(CategorieController.class);
//    @EJB
//    OrclassCategoriesDao categoriesDao;
//    OrclassCategories categories;
//    OrclassCategories categoriesSelect;
//    @EJB
//    OrclassBranchesDao branchesDao;
//    @EJB
//    OrclasseType_GarantieDao type_GarantieDao;
//    @EJB
//    OrclassRefGarantiesDao refGarantiesDao;
////     OrclassRefGaranties refGaranties;
//    @EJB
//    OrclassGarantieDao garantieDao;
//    
//    OrclassGarantie garantieSave;
//    OrclassGarantie garantieIndice;
//    private String currentFolder = "/photos";
//    private String defautpathExcell = currentFolder + "/REF_GARANTIE.xls";
//    
//    private Collection<OrclassCategories> lisCategories = new ArrayList<>();
//    private List<OrclassCategories> filterCategories = new ArrayList<>();
//    private List<OrclassElt_Categorie_Compagnie> filterElts = new ArrayList<>();
//    private List<OrclassGarantie> filterGarantie = new ArrayList<>();
//    private List<OrclassRefGaranties> filterRefGarantie = new ArrayList<>();
//    private List<OrclassRegistreSinistre> filterRSinistre = new ArrayList<>();
//    private List<OrclassRegistreProduction> filterRProduction = new ArrayList<>();
//    private Collection<OrclassBranches> lisBranche = new ArrayList<>();
//    private List<OrclassElt_Categorie_Compagnie> lisEts_CategoriesCompagnies = new ArrayList<>();
//    private Collection<OrclassRegistreSinistre> listOrclassRegistreSinistre = new ArrayList<>();
//    private Collection<OrclassRegistreProduction> listOrclassRegistreProductione = new ArrayList<>();
//    private OrclassEntreprises entreprise;
//    private OrclassClasses classes;
//    private List<SortMeta> sortBy;
//    private List<OrclassGarantie> ListeGarantie = new ArrayList<>();
//    private List<OrclassRefGaranties> ListeRefGarantieses = new ArrayList<>();
//    private List<OrclasseType_Garantie> ListeType_Garanties = new ArrayList<>();
//    OrclassRefGaranties refGaranties;
//    OrclassRefGaranties refGarantiesAdd;
//    OrclassGarantie ga;
//    Boolean ajouterLibelleAutre = Boolean.FALSE;
//    
//    String summary = "";
//    String msgdetail = "";
//    private OrclassMenus menu;
//    private OrclassMenus menu2;
//    private OrclassModules module;
//    private OrclassModules module2;
//    OrclassUtilisateurs user;
//    OrclassUtilisateurs utilisateurs;
//    @EJB
//    IDroitAcces serviceAccess;
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    OrclassElt_Categorie_CompagnieDao elt_Categorie_CompagnieDao;
//    @EJB
//    OrclassRegistreProductionDao registreProductionDao;
//    @EJB
//    OrclassRegistreSinistreDao registreSinistreDao;
//    @EJB
//    IExcell serviceExcell;
//
//    /**
//     * Creates a new instance of CategorieController
//     */
//    public CategorieController() {
//        categories = new OrclassCategories();
//        classes = new OrclassClasses();
//        refGaranties = new OrclassRefGaranties();
//        garantieSave = new OrclassGarantie();
//        categoriesSelect = new OrclassCategories();
//        refGarantiesAdd = new OrclassRefGaranties();
//        
//    }
//    
//    public void chargeClasseByEntreprise(OrclassEntreprises e) {
//        
//        if (e != null && e.getIdEntreprise() != null) {
//            lisCategories = categoriesDao.listAllCategoriesShowAllCompagnie();
//            lisCategories.addAll(categoriesDao.listAllCategorieByCompagnie(e));
//            lisBranche = branchesDao.listAllBrancheShowAllCompagnie();
//            lisBranche.addAll(branchesDao.listAllBrancheByCompagnie(e));
//            
//        }
//    }
//    
//    public void showDialog() {
//        PrimeFaces.current().executeScript("PF('manageCategorieDialog2').show()");
//        this.updateTableRefGarantie();
//    }
//    
//    public void reload() throws IOException {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
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
//    public void rechargeDialo() {
//        ajouterLibelleAutre = Boolean.TRUE;
//        PrimeFaces.current().executeScript("PF('manageCategorieDialog').show()");
//        
//    }
//    
//    public void ajoutLigneGrantie() {
//        ga = new OrclassGarantie();
//        ga.setId(1l);
//        ListeGarantie.add(ga);
//        System.err.println("taille" + ListeGarantie.size());
//    }
//    
//    public void reverseListe() {
//        
//        List<OrclassGarantie> result = new ArrayList<>();
//        for (int i = ListeGarantie.size() - 1; i >= 0; i--) {
//            
//            result.add(ListeGarantie.get(i));
//        }
//        ListeGarantie = new ArrayList<>(result);
////         this.getListeGarantie();
////        System.err.println("taille"+ ListeGarantie.size());
////        this.setListeGarantie(result);
////        this.updateTableGarantie();
//    }
//    
//    public void updateTableRefGarantie() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2_2:idrefGarantieTable");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('refGarantieTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void chargeGarantieByCategories() {
////        garantieIndice = ListeGarantie.get(0);
//        OrclassElt_Categorie_Compagnie elt = null;
//        OrclassGarantie garantie = null;
//        ListeGarantie = new ArrayList<>();
//        chargeRefGarentie();
//        List<OrclassGarantie> ListeGaranties = new ArrayList<>();
//        if (categoriesSelect != null && categoriesSelect.getIdCategorie() != null) {
//            elt = elt_Categorie_CompagnieDao.finkey(categoriesSelect, entreprise);
//            if (elt == null || elt.getMin_garantie() == 0) {
//                PrimeFaces.current().executeScript("PF('infosDialog').show();");
//                return;
//            }
//            ListeGaranties = garantieDao.getallGarantieByCategorieByCompagnie(entreprise, categoriesSelect);
//            if (ListeGaranties.isEmpty()) {
//                
//                garantie = new OrclassGarantie();
//                garantie.setIdCategories(categoriesSelect);
//                ListeGarantie.add(garantie);
//            } else {
//                for (OrclassGarantie ga : ListeGaranties) {
//                    ListeGarantie.add(ga);
//                }
//                garantie = new OrclassGarantie();
//                garantie.setIdCategories(categoriesSelect);
//                ListeGarantie.add(garantie);
//            }
//
////            ListeRefGarantieses = refGarantiesDao.listAllRefGarantiesShowAllCompagnie();
////            ListeRefGarantieses.addAll(refGarantiesDao.listAllRefGarantiesByCompagnie(entreprise));
//        } else {
//            garantie = new OrclassGarantie();
//            garantie.setIdCategories(categoriesSelect);
//            ListeGarantie.add(garantie);
//        }
//        ListeRefGarantieses.removeAll(chargerReferenceHaveGarantie());
//        this.reverseListe();
//        this.updateTableGarantie();
//        updateTableRefGarantie();
//
////        PrimeFaces.current().ajax().update("form1_1");
////        PrimeFaces.current().ajax().update(":form1_1:idgarantieTable");
////        PrimeFaces.current().ajax().update(":form2_2:idrefGarantieTable");
//    }
//    
//    public List<OrclassRefGaranties> chargerReferenceHaveGarantie() {
//        return garantieDao.getRefGarantieByCategorieByCompagnie(entreprise, categoriesSelect);
//    }
//    
//    public void chargeGarentieByRefGarantie() {
//        OrclassGarantie garantie = null;
//        int size = ListeGarantie.size();
//        if (size > 0 && size == 1 && refGaranties != null && refGaranties.getId() != null) {
//            garantie = ListeGarantie.get(0);
//            garantie.setIdRefGaranties(refGaranties);
//            ListeGarantie.set(0, garantie);
//            garantie = new OrclassGarantie();
//            ListeGarantie.add(garantie);
//            
//        }
//        if (size > 0 && size > 1 && refGaranties != null && refGaranties.getId() != null && categoriesSelect != null && categoriesSelect.getIdCategorie() != null) {
////            int index = size - 1;
//            int index = 0;
//            if (garantieDao.finKeyGaranties(categoriesSelect, refGaranties, entreprise) == null) {
//                garantie = ListeGarantie.get(index);
//                for (OrclassGarantie ga : ListeGarantie) {
//                    if (ga.getIdRefGaranties().getId() != null && ga.getIdRefGaranties().getId().equals(refGaranties.getId())) {
//                        return;
//                    }
//                }
//                garantie.setIdRefGaranties(refGaranties);
//                ListeGarantie.set(index, garantie);
//                garantie = new OrclassGarantie();
//                ListeGarantie.add(garantie);
//            }
//            
//        }
////        this.reverseListe();
////        ListeRefGarantieses.removeAll(chargerReferenceHaveGarantie());
//        this.updateTableGarantie();
//        PrimeFaces.current().ajax().update("form1_1");
//        
//    }
//    
//    public void updateRowForDataTable(OrclassElt_Categorie_Compagnie elt) {
//        int index = elt.getIndex();
////         int i=0;
//        OrclassElt_Categorie_Compagnie elts;
//        
//        for (int j = 0; j < lisEts_CategoriesCompagnies.size(); j++) {
//            elts = lisEts_CategoriesCompagnies.get(j);
//            if (elts.getIdCategories().getIdCategorie().equals(elt.getIdCategories().getIdCategorie())) {
//                lisEts_CategoriesCompagnies.set(j, elt);
//                System.err.println("index :" + j + " code :" + elt.getIdCategories().getCode());
//                break;
//            }
//        }
//        
//    }
//    
//    public void chargeElementCategorieByCompagnes() {
//        OrclassElt_Categorie_Compagnie elt_Categorie_Compagnie = null;
//        lisEts_CategoriesCompagnies.clear();
//        int index = 0;
//        if (!lisCategories.isEmpty()) {
//            for (OrclassCategories cat : lisCategories) {
//                elt_Categorie_Compagnie = elt_Categorie_CompagnieDao.finkey(cat, entreprise);
//                if (elt_Categorie_Compagnie == null) {
//                    elt_Categorie_Compagnie = new OrclassElt_Categorie_Compagnie();
//                    elt_Categorie_Compagnie.setIdCategories(cat);
//                    elt_Categorie_Compagnie.setIdEntreprise(entreprise);
//                    elt_Categorie_Compagnie.setMax(0);
//                    elt_Categorie_Compagnie.setMin(0);
//                    elt_Categorie_Compagnie.setMin_garantie(0);
//                    elt_Categorie_Compagnie.setIdRegistreProduction(new OrclassRegistreProduction());
////                    elt_Categorie_Compagnie.setIdRegistreSinistre(new OrclassRegistreSinistre());
////                    elt_Categorie_Compagnie.setIndex(index);
//                    lisEts_CategoriesCompagnies.add(elt_Categorie_Compagnie);
////                    index++;
//                } else {
////                    elt_Categorie_Compagnie.setIndex(index);
//                    lisEts_CategoriesCompagnies.add(elt_Categorie_Compagnie);
////                    index++;
//                }
//            }
//            ComparatorChain chain = new ComparatorChain();
//            chain.addComparator(OrclassElt_Categorie_Compagnie.ByCodeASC);
//            Collections.sort((List<OrclassElt_Categorie_Compagnie>) lisEts_CategoriesCompagnies, chain);
//        }
//    }
//    
//    public void addEltCategorie(OrclassElt_Categorie_Compagnie item) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
////          ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//        if (item != null && item.getId() != null) {
//            elt_Categorie_CompagnieDao.update(item);
//            
//        } else if (item == null || item.getId() == null) {
//            OrclassElt_Categorie_Compagnie items = elt_Categorie_CompagnieDao.finkey(item.getIdCategories(), entreprise);
//            if (items == null) {
//                items = new OrclassElt_Categorie_Compagnie();
//                items.setIdCategories(item.getIdCategories());
//                items.setMax(item.getMax());
//                items.setIndex(item.getIndex());
//                items.setMin(item.getMin());
//                items.setMin_garantie(item.getMin_garantie());
//                items.setRisque(item.getRisque());
//                items.setIdEntreprise(item.getIdEntreprise());
//                if (item.getIdRegistreProduction() != null && item.getIdRegistreProduction().getId() != null) {
//                    items.setIdRegistreProduction(item.getIdRegistreProduction());
//                } else {
//                    items.setIdRegistreProduction(null);
//                }
////                if (item.getIdRegistreSinistre() != null && item.getIdRegistreSinistre().getId() != null) {
////                    items.setIdRegistreSinistre(item.getIdRegistreSinistre());
////                }
//
//                elt_Categorie_CompagnieDao.create(items);
//                this.updateRowForDataTable(items);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, exception.Success.INSERT_SUCCESS.name(), exception.Success.OPERATION_SUCCESS.name()));
//                
//            } else if (items != null && items.getId() != null) {
////                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, exception.Error.DUPLICATE_ERROR_INSERT.name(), " ID : " + items.getId() + " EXIST"));
//
//            }
//        }
//
////        this.chargeElementCategorieByCompagnes();
//        this.updateTableElementCategorie();
////        return Boolean.FALSE;
//    }
//    
//    public void change(ValueChangeEvent event) throws IOException {
//        String page = (String) event.getNewValue(); //Must however be the exact page URL. E.g. "contact.jsf".
//        FacesContext.getCurrentInstance().getExternalContext().redirect(page);
//        
//    }
//    
//    public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_categorie.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_categorie.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
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
//        this.chargeClasseByEntreprise(entreprise);
//        menu = menusDao.findEntityHavingValue("code", "categorie");
//        module = modulesDao.findEntityHavingValue("code", "mod.admin");
//        menu2 = menusDao.findEntityHavingValue("code", "assure.garantie");
//        module2 = modulesDao.findEntityHavingValue("code", "mod.ref");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        chargeElementCategorieByCompagnes();
//        
//        listOrclassRegistreSinistre = registreSinistreDao.getAllRegistreSinistreByCompagnie(entreprise);
//        listOrclassRegistreProductione = registreProductionDao.getAllRegistreProductionByCompagnie(entreprise);
//        ListeType_Garanties = type_GarantieDao.listAllType_GarantieShowAllCompagnie();
//        ListeType_Garanties.addAll(type_GarantieDao.listAllType_GarantieByCompagnie(entreprise));
//        if (refGarantiesDao.findAll() == null || refGarantiesDao.findAll().isEmpty()) {
//            initialRefGarantieByExcell();
//        }
//        ListeRefGarantieses = refGarantiesDao.listAllRefGarantiesShowAllCompagnie();
//        ListeRefGarantieses.addAll(refGarantiesDao.listAllRefGarantiesByCompagnie(entreprise));
//        ga = new OrclassGarantie();
//        ListeGarantie.add(ga);
////        PrimeFaces.current().ajax().update("form_tab");
//        this.updateTableElementCategorie();
//        this.updateTableCategorie();
//        refGaranties = new OrclassRefGaranties();
////        this.updateTableRefGarantie();
//
//    }
//    
//    public void createRefgarantieDialog() {
//        refGarantiesAdd = new OrclassRefGaranties();
//        
//        PrimeFaces.current().executeScript("PF('manageAddRefGarantieDialog').show()");
//    }
//    
//    public void chargeRefGarentie() {
//        ListeRefGarantieses = refGarantiesDao.listAllRefGarantiesShowAllCompagnie();
//        ListeRefGarantieses.addAll(refGarantiesDao.listAllRefGarantiesByCompagnie(entreprise));
//    }
//    
//    public void reset() {
//        
//        this.init();
////        this.chargeClasseByEntreprise(entreprise);
//        this.chargeElementCategorieByCompagnes();
//        this.updateTableElementCategorie();
//        this.updateTableCategorie();
//        
//        FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1");
//        PrimeFaces.current().ajax().update("form1,form2");
//        
//    }
//    
//    public void init() {
//        categories = new OrclassCategories();
//        
//    }
//    
//    public String addCategorie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "categorie", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if ((categories.getCategoriesVehicule() == null || categories.getCategoriesVehicule().intValue() == 0) && categories.getIdBranche().getLibelle().equals(LibelleBranche.automobile)) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("PLEASE CHECK VALUE", "Valeur Catégorie Véhicule n est pas correct..."));
//                return null;
//            }
//            if (categoriesDao.findEntityHavingValue("code", categories.getCode()) == null && categories.getCode() != null) {
//                categories.setIdEntreprise(entreprise);
//                categories.setDateCreation(new Date());
//                categories.setCodePrincipal(new BigInteger(categories.getCode().trim()));
//                categoriesDao.create(categories);
//                
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "VALUE IS NULL"));
//                return null;
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "categorie", exception.Error.FATAL_ERROR + "", new Object[]{"classe"});
//            logger.error("Erreur Survenu", th);
//        }
//        lisCategories.add(categories);
//        this.reset();
//        return null;
//    }
//    
//    public String addRefGarantie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "garantie", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if ("".equals(refGarantiesAdd.getCode()) || refGarantiesAdd.getCode() == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, "CODE IS EMPTY", "PLEASE WRITE VALUE "));
//                PrimeFaces.current().executeScript("PF('manageAddRefGarantieDialog').show()");
//                return null;
//                
//            }
//            if ("".equals(refGarantiesAdd.getLibelle()) || refGarantiesAdd.getLibelle() == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, "LIBELLE IS EMPTY", "PLEASE WRITE VALUE "));
//                PrimeFaces.current().executeScript("PF('manageAddRefGarantieDialog').show()");
//                return null;
//            }
//            if (refGarantiesDao.findEntityHavingValue("code", refGarantiesAdd.getCode()) == null) {
//                refGarantiesAdd.setIdEntreprise(entreprise);
//                refGarantiesDao.create(refGarantiesAdd);
//                
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "VALUE IS EXISTS"));
//                return null;
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "categorie", exception.Error.FATAL_ERROR + "", new Object[]{"classe"});
//            logger.error("Erreur Survenu", th);
//        }
//        
//        ListeRefGarantieses.add(refGarantiesAdd);
//        this.setRefGaranties(refGarantiesAdd);
//        this.chargeGarentieByRefGarantie();
//        this.updateTableRefGarantie();
//        PrimeFaces.current().executeScript("PF('manageAddRefGarantieDialog').hide()");
//        
//        return null;
//    }
//    
//    public void updateTableGarantie() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1_1:idgarantieTable");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('garantieTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
////    public void updateTableRefGarantie() {
////        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2_2:idrefGarantieTable");
////        table.setValueExpression("sortBy", null);
////
////        PrimeFaces.current().executeScript("PF('refGarantieTable').clearFilters();");
//////         PrimeFaces.current().ajax().update("form1_1");
////
////    }
//    public void updateTableElementCategorie() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form_tab:tabprincipal:idElt");
//        try {
//            table.setValueExpression("sortBy", null);
//            
//            PrimeFaces.current().executeScript("PF('eltTable').clearFilters();");
//        } catch (Exception e) {
//        }
//        
//    }
//    
//    public void updateTableCategorie() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:table2");
//        try {
//            table.setValueExpression("sortBy", null);
//            PrimeFaces.current().executeScript("PF('classTable2').clearFilters();");
//        } catch (Exception e) {
//        }
//        
//    }
//    
//    public void chargeListeCategorieByForm2() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:idmanageCategorie");
//        PrimeFaces.current().executeScript("PF('manageCategorieDialog').show();");
//    }
//    
//    public void chargeListeCategorieByForm2_2() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2_2:idmanageCategorie2");
//        PrimeFaces.current().executeScript("PF('manageCategorieDialog2').show();");
//    }
//    
//    public void removeGarantieByListe() {
//        ListeGarantie.remove(garantieSave);
//        this.updateTableGarantie();
//        PrimeFaces.current().ajax().update("form1_1");
//        this.reverseListe();
//        
//    }
//    
//    public void onRowEdit(RowEditEvent<OrclassElt_Categorie_Compagnie> event) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        FacesMessage msg;
//        OrclassElt_Categorie_Compagnie elt = event.getObject();
//        if (elt.getRisque() == null) {
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "risque.non.definir", null, myLoc);
//            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "element category edit", String.valueOf(event.getObject().getIdCategories().getCode()) + "-" + msgdetail);
//            
//        } else if (elt.getMin() == 0 || elt.getMax() == 0 || elt.getMin_garantie() == 0) {
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "caracteris.cat.non.definir", null, myLoc);
//            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "element category edit", String.valueOf(event.getObject().getIdCategories().getCode()) + "-" + msgdetail);
//            
//        } else {
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + event.getObject().getIdCategories().getLibelle(), null, myLoc);
//            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "element category edit", String.valueOf(event.getObject().getIdCategories().getCode()) + "-" + msgdetail);
//            
//            this.addEltCategorie(event.getObject());
//        }
//        
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//    
//    public void onRowCancel(RowEditEvent<OrclassElt_Categorie_Compagnie> event) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + event.getObject().getIdCategories().getLibelle(), null, myLoc);
//        FacesMessage msg = new FacesMessage("element category cancel", String.valueOf(event.getObject().getIdCategories().getCode()) + "-" + msgdetail);
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//    
//    public String updateGarantieByCategorie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "garantie", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (garantieSave != null && garantieSave.getId() != null) {
//                
//                garantieSave.setDateModification(new Date());
//                
//                garantieDao.update(garantieSave);
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "garantie", exception.Error.FATAL_ERROR + "", new Object[]{"garantie"});
//            logger.error("Erreur Survenu", th);
//        }
////        for (OrclassGarantie ga : ListeGarantie) {
////            if (ga.getIdRefGaranties().getId() != null && garantieSave.getIdRefGaranties().getId().equals(ga.getIdRefGaranties().getId())) {
////                int index = ListeGarantie.indexOf(ga);
////                if (index > 0) {
////                    ListeGarantie.set(index, garantieSave);
////                }
////            }
////        }
//        ListeGarantie = garantieDao.getallGarantieByCategorieByCompagnie(entreprise, categoriesSelect);
//        garantieSave = new OrclassGarantie();
//        garantieSave.setIdCategories(categoriesSelect);
//        ListeGarantie.add(garantieSave);
//        this.updateTableGarantie();
//        this.reverseListe();
//
////        this.removeGarantieByListe();
//        return null;
//    }
//    
//    public String deleteGarantieCategorie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "garantie", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        OracleConnection con = new OracleConnection();
//        try {
//            
//            if (garantieSave != null && garantieSave.getId() != null) {
//                
//                garantieDao.delete(garantieSave);
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.delete.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "FATAL ERROR"));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DELETE_ERROR + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "garantie", exception.Error.FATAL_ERROR + "", new Object[]{"garantie"});
//            logger.error("Erreur Survenu", th);
//        }
//        ListeGarantie = garantieDao.getallGarantieByCategorieByCompagnie(entreprise, categoriesSelect);
////        ListeGarantie.remove(garantieSave);
//        garantieSave = new OrclassGarantie();
//        garantieSave.setIdCategories(categoriesSelect);
//        ListeGarantie.add(garantieSave);
//        this.updateTableGarantie();
//        this.reverseListe();
//        return null;
//    }
//    
//    public void onCellEdit(CellEditEvent event) {
//        Object oldValue = event.getOldValue();
//        Object newValue = event.getNewValue();
//        
//        if (newValue != null && !newValue.equals(oldValue)) {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        }
//    }
//    
//    public String addGarantieByCategorie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "garantie", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (garantieSave != null && garantieSave.getIdRefGaranties().getId() != null && (categoriesSelect != null && categoriesSelect.getIdCategorie() != null)) {
//                if (garantieSave.getNatureGarantie() == null || garantieSave.getModeCalcul() == null) {
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE NATURE OR CALCULATION MODE  VALUE IS EMPTY PLEASE MAKE A CHOICE ", "TRY AGAINST"));
//                    return null;
//                }
//                garantieSave.setIdEntreprise(entreprise);
//                garantieSave.setIdCategories(categoriesSelect);
//                garantieSave.setDateCreation(new Date());
//                garantieSave.setGlobalCompagnie(Boolean.FALSE);
//                garantieDao.create(garantieSave);
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//                ListeGarantie = garantieDao.getallGarantieByCategorieByCompagnie(entreprise, categoriesSelect);
//                garantieSave = new OrclassGarantie();
//                garantieSave.setIdCategories(categoriesSelect);
//                ListeGarantie.add(garantieSave);
//                
//                this.updateTableGarantie();
////                PrimeFaces.current().ajax().update(":form1_1");
//                this.reverseListe();
//                
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "garantie", exception.Error.FATAL_ERROR + "", new Object[]{"garantie"});
//            logger.error("Erreur Survenu", th);
//        }
////        for (OrclassGarantie ga : ListeGarantie) {
////            if (ga.getIdRefGaranties().getId() != null && garantieSave.getIdRefGaranties().getId().equals(ga.getIdRefGaranties().getId())) {
////                int index = ListeGarantie.indexOf(ga);
////                if (index > 0) {
////                    ListeGarantie.set(index, garantieSave);
////                }
////            }
////        }
//        return null;
//    }
//    
//    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
//        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
////        if (LangUtils.isBlank(filterText)) {
////            return true;
////        }
////        int filterInt = getInteger(filterText);
//
//        OrclassGarantie gar = (OrclassGarantie) value;
//        if (gar.getId() == null) {
//            return true;
//        }
//        return gar.getIdRefGaranties().getCode().toLowerCase().contains(filterText)
//                || gar.getIdRefGaranties().getLibelle().toLowerCase().contains(filterText);
//    }
//    
//    public String deleteGarantie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "rubrique.categorie", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (garantieSave != null && garantieSave.getId() != null) {
//                
//                garantieDao.delete(garantieSave);
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "rubrique.categorie", exception.Error.FATAL_ERROR + "", new Object[]{"rubrique.categorie"});
//            logger.error("Erreur Survenu", th);
//        }
//        
//        ListeGarantie = garantieDao.getallGarantieByCategorieByCompagnie(entreprise, categoriesSelect);
//        garantieSave = new OrclassGarantie();
//        garantieSave.setIdCategories(categoriesSelect);
//        ListeGarantie.add(garantieSave);
//        this.updateGarantieByCategorie();
//        this.reverseListe();
//        
//        return null;
//    }
//    
//    public String valueObjectByLibelleAutresClasse(OrclassClasses cl) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        try {
//            if (cl != null && cl.getLibelle().equals(LibelleClasse.autres)) {
//                return cl.getLibelleAutre();
//            } else if (cl != null) {
//                
//                return LocaleHelper.getLocaleString(RecupBundle.FichierBundle, cl.getLibelle().toString(), null, myLoc);
//            }
//        } catch (Exception e) {
//            return "";
//        }
//        return "";
//        
//    }
//    
//    public String valueObjectByTypeGarentie(OrclasseType_Garantie cl) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        try {
//            if (cl != null && cl.getLibelle().equals(TypeGarantie.autres)) {
//                return cl.getLibelleAutre();
//            } else if (cl != null) {
//                
//                return LocaleHelper.getLocaleString(RecupBundle.FichierBundle, cl.getLibelle().toString(), null, myLoc);
//            }
//        } catch (Exception e) {
//            return "";
//        }
//        return "";
//        
//    }
//    
//    public String deleteCategories() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "categorie", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        OracleConnection con = new OracleConnection();
//        try {
//            if (categories != null && categories.getIdCategorie() != null) {
//
////               categoriesDao.delete(categories);
//                con.deleteCategories(categories);
//                lisCategories.remove(categories);
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "categorie", exception.Error.FATAL_ERROR + "", new Object[]{"categorie"});
//            logger.error("Erreur Survenu", th);
//        }
////       lisClass.remove(classes);
//        this.reset();
//        PrimeFaces.current().ajax().update(":form_tab");
//        return null;
//    }
//    
//    public String updateCategorie() {
//        String success = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        //Locale myLoc =new Locale("fr");
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "categorie", null, myLoc)};
//        String[] detail = {entete[0], "Parametres"};
//        
//        try {
//            if ((categories.getCategoriesVehicule() == null || categories.getCategoriesVehicule().intValue() == 0) && categories.getIdBranche().getLibelle().equals(LibelleBranche.automobile)) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("PLEASE CHECK VALUE", "Valeur Catégorie Véhicule n est pas correct..."));
//                return null;
//            }
//            
//            if (categories != null && categories.getIdCategorie() != null) {
//                lisCategories.remove(categories);
//                categories.setDateModification(new Date());
//                categories.setCodePrincipal(new BigInteger(categories.getCode().trim()));
//                categoriesDao.update(categories);
//                
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + exception.Success.OPERATION_SUCCESS.toString(), entete, myLoc);
//                
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
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
//        lisCategories.add(categories);
//        reset();
//        return null;
//        
//    }
//    
//    public List<SelectItem> getTypeGarantieForCreer() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        
//        for (TypeGarantie tp : TypeGarantie.values()) {
//            if (tp.equals(TypeGarantie.autres)) {
//                
//                items.add(new SelectItem(tp, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, tp.name(), null, myLoc)));
//                
//            } else {
//                if (type_GarantieDao.findEntityHavingValue("libelle", tp) == null) {
//                    
//                    items.add(new SelectItem(tp, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, tp.name(), null, myLoc)));
//                    System.out.println("categories :" + tp.name() + "valeur:" + LocaleHelper.getLocaleString(RecupBundle.FichierBundle, tp.name(), null, myLoc));
//                }
//            }
//            
//        }
//        return items;
//    }
//    
//    public List<SelectItem> getModeApplicationSante() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        for (ModeApplicationSante m : ModeApplicationSante.values()) {
//            items.add(new SelectItem(m, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, m.name(), null, myLoc)));
//        }
//        
//        return items;
//        
//    }
//    
//    public List<SelectItem> getTypeLibelleCategoriesForCreer() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        if (categories != null && categories.getIdCategorie() != null) {
//            if (categories.getLibelle().equals(LibelleCategorie.autres)) {
//                
//                items.add(new SelectItem(categories.getLibelle(), LocaleHelper.getLocaleString(RecupBundle.FichierBundle, categories.getLibelle().name(), null, myLoc)));
////                System.out.println("categories :"+cat.name() +"valeur:"+LocaleHelper.getLocaleString(RecupBundle.FichierBundle, cat.name(), null, myLoc));
//            } else {
//                
//                items.add(new SelectItem(categories.getLibelle(), LocaleHelper.getLocaleString(RecupBundle.FichierBundle, categories.getLibelle().name(), null, myLoc)));
//                
//            }
//            
//        } else {
//            for (LibelleCategorie cat : LibelleCategorie.values()) {
//                if (cat.equals(LibelleCategorie.autres)) {
//                    
//                    items.add(new SelectItem(cat, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, cat.name(), null, myLoc)));
//                    System.out.println("categories :" + cat.name() + "valeur:" + LocaleHelper.getLocaleString(RecupBundle.FichierBundle, cat.name(), null, myLoc));
//                } else {
//                    if (categoriesDao.findEntityHavingValue("libelle", cat) == null) {
//                        
//                        items.add(new SelectItem(cat, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, cat.name(), null, myLoc)));
//                        System.out.println("categories :" + cat.name() + "valeur:" + LocaleHelper.getLocaleString(RecupBundle.FichierBundle, cat.name(), null, myLoc));
//                    }
//                }
//                
//            }
//            
//        }
//        return items;
//        
//    }
//    
//    public List<SelectItem> getQualiteRisqueForCreer() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        for (LibelleRisque r : LibelleRisque.values()) {
//            items.add(new SelectItem(r, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, r.name(), null, myLoc)));
//        }
//        return items;
//    }
//    
//    public List<SelectItem> getNatureGarantieForCreer() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        for (NatureGarantie n : NatureGarantie.values()) {
//            items.add(new SelectItem(n, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, n.name(), null, myLoc)));
//        }
//        return items;
//    }
//    
//    public List<SelectItem> getModeCalculGarantieForCreer() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        for (ModeCalcul m : ModeCalcul.values()) {
//            items.add(new SelectItem(m, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, m.name(), null, myLoc)));
//        }
//        return items;
//    }
//    
//    public String valueObjectByLibelleAutres(OrclassBranches cl) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        if (cl != null && cl.getLibelle().equals(LibelleBranche.autres)) {
//            return cl.getLibelleAutre();
//        }
//        if (cl != null) {
//            return LocaleHelper.getLocaleString(RecupBundle.FichierBundle, cl.getLibelle().toString(), null, myLoc);
//        }
//        return "";
//    }
//    
//    public String valueObjectByLibelleAutres(OrclassCategories cl) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        if (cl != null && cl.getLibelle().equals(LibelleCategorie.autres)) {
//            return cl.getLibelleAutre();
//        }
//        if (cl != null) {
//            return LocaleHelper.getLocaleString(RecupBundle.FichierBundle, cl.getLibelle().toString(), null, myLoc);
//        }
//        return "";
//    }
//    
//    public Boolean chargLibelleBranche() {
//        try {
//            if (categories.getLibelle() != null) {
//                if (categories.getLibelle().equals(LibelleCategorie.autres)) {
//                    return Boolean.TRUE;
//                }
//            }
//        } catch (Exception e) {
//        }
//        
//        return Boolean.FALSE;
//    }
//    
//    public Boolean choixByBrancheIsAuto() {
//        
//        if (categories.getIdBranche().getLibelle() != null && categories.getIdBranche().getLibelle().equals(LibelleBranche.automobile)) {
//            
//            return Boolean.TRUE;
//            
//        }
//        
//        return Boolean.FALSE;
//    }
//    
//    public void showDetails(OrclassCategories item) {
//        if (categories == null || categories.getIdCategorie() == null) {
//            this.setCategories(item);
//        }
//        if (categoriesSelect != null && categoriesSelect.getIdCategorie() != null) {
//            this.setCategories(categoriesSelect);
//        }
//        this.setClasses(categories.getIdBranche().getIdClasse());
//        lisCategories.add(categories);
//        if (categories.getIdBranche().getLibelle() != null && categories.getIdBranche().getLibelle().equals(LibelleBranche.automobile)) {
//            if (categories.getCategoriesVehicule() == null) {
//                categories.setCategoriesVehicule(0);
//            }
//        }
//        
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
//    public List<OrclassCategories> getFilterCategories() {
//        return filterCategories;
//    }
//    
//    public void setFilterCategories(List<OrclassCategories> filterCategories) {
//        this.filterCategories = filterCategories;
//    }
//    
//    public Collection<OrclassBranches> getLisBranche() {
//        return lisBranche;
//    }
//    
//    public void setLisBranche(Collection<OrclassBranches> lisBranche) {
//        this.lisBranche = lisBranche;
//    }
//    
//    public OrclassClasses getClasses() {
//        return classes;
//    }
//    
//    public void setClasses(OrclassClasses classes) {
//        this.classes = classes;
//    }
//    
//    public Collection<OrclassElt_Categorie_Compagnie> getLisEts_CategoriesCompagnies() {
////        if (!lisEts_CategoriesCompagnies.isEmpty()) {
////            ComparatorChain chain = new ComparatorChain();
////            chain.addComparator(OrclassElt_Categorie_Compagnie.ByCodeASC);
////            Collections.sort((List<OrclassElt_Categorie_Compagnie>) lisEts_CategoriesCompagnies, chain);
////        }
//        return lisEts_CategoriesCompagnies;
//    }
//    
//    public void setLisEts_CategoriesCompagnies(Collection<OrclassElt_Categorie_Compagnie> lisEts_CategoriesCompagnies) {
//        this.lisEts_CategoriesCompagnies = (List<OrclassElt_Categorie_Compagnie>) lisEts_CategoriesCompagnies;
//    }
//    
//    public Collection<OrclassRegistreSinistre> getListOrclassRegistreSinistre() {
//        return listOrclassRegistreSinistre;
//    }
//    
//    public void setListOrclassRegistreSinistre(Collection<OrclassRegistreSinistre> listOrclassRegistreSinistre) {
//        this.listOrclassRegistreSinistre = listOrclassRegistreSinistre;
//    }
//    
//    public Collection<OrclassRegistreProduction> getListOrclassRegistreProductione() {
//        return listOrclassRegistreProductione;
//    }
//    
//    public void setListOrclassRegistreProductione(Collection<OrclassRegistreProduction> listOrclassRegistreProductione) {
//        this.listOrclassRegistreProductione = listOrclassRegistreProductione;
//    }
//    
//    public List<OrclassElt_Categorie_Compagnie> getFilterElts() {
//        return filterElts;
//    }
//    
//    public void setFilterElts(List<OrclassElt_Categorie_Compagnie> filterElts) {
//        this.filterElts = filterElts;
//    }
//    
//    public List<OrclassRegistreSinistre> getFilterRSinistre() {
//        return filterRSinistre;
//    }
//    
//    public void setFilterRSinistre(List<OrclassRegistreSinistre> filterRSinistre) {
//        this.filterRSinistre = filterRSinistre;
//    }
//    
//    public List<OrclassRegistreProduction> getFilterRProduction() {
//        return filterRProduction;
//    }
//    
//    public void setFilterRProduction(List<OrclassRegistreProduction> filterRProduction) {
//        this.filterRProduction = filterRProduction;
//    }
//    
//    public List<OrclassGarantie> getListeGarantie() {
//        return ListeGarantie;
//    }
//    
//    public void setListeGarantie(List<OrclassGarantie> ListeGarantie) {
//        this.ListeGarantie = ListeGarantie;
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
//    public List<OrclasseType_Garantie> getListeType_Garanties() {
//        return ListeType_Garanties;
//    }
//    
//    public void setListeType_Garanties(List<OrclasseType_Garantie> ListeType_Garanties) {
//        this.ListeType_Garanties = ListeType_Garanties;
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
//    public List<OrclassGarantie> getFilterGarantie() {
//        return filterGarantie;
//    }
//    
//    public void setFilterGarantie(List<OrclassGarantie> filterGarantie) {
//        this.filterGarantie = filterGarantie;
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
//    public OrclassGarantie getGarantieSave() {
//        return garantieSave;
//    }
//    
//    public void setGarantieSave(OrclassGarantie garantieSave) {
//        this.garantieSave = garantieSave;
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
//    public Boolean accessCreeCategorieGarantie() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        
//        if (menu2 != null && menu2.getIdMenu() != null && module2 != null && module2.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.garantie_assure.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);
//            
//            return serviceAccess.accessAction(user, action, menu2);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public Boolean accessAjouterCategorieGarantie() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu2 != null && menu2.getIdMenu() != null && module2 != null && module2.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.garantie_assure.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.ajouter.name(), fon);
//            
//            return serviceAccess.accessAction(user, action, menu2);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public Boolean accessModifierCategorieGarantie() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu2 != null && menu2.getIdMenu() != null && module2 != null && module2.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.garantie_assure.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
//            return serviceAccess.accessAction(user, action, menu2);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public Boolean accessSupprimer() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu2 != null && menu2.getIdMenu() != null && module2 != null && module2.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.garantie_assure.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer.name(), fon);
//            return serviceAccess.accessAction(user, action, menu2);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public Boolean accessAfficherList() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu2 != null && menu2.getIdMenu() != null && module2 != null && module2.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.garantie_assure.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.afficher_liste.name(), fon);
//            return serviceAccess.accessAction(user, action, menu2);
//        }
//        return Boolean.FALSE;
//    }
//    
//    public Boolean getAjouterLibelleAutre() {
//        return ajouterLibelleAutre;
//    }
//    
//    public void setAjouterLibelleAutre(Boolean ajouterLibelleAutre) {
//        this.ajouterLibelleAutre = ajouterLibelleAutre;
//    }
//    
//    public OrclassRefGaranties getRefGarantiesAdd() {
//        return refGarantiesAdd;
//    }
//    
//    public void setRefGarantiesAdd(OrclassRefGaranties refGarantiesAdd) {
//        this.refGarantiesAdd = refGarantiesAdd;
//    }
//    
//}
