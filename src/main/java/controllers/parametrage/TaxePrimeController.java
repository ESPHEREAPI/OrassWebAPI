///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import static Entreprise.EntrepriseServices.logger;
//import dao.OrclassCategoriesDao;
//import dao.OrclassGarantieDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassRegimeTaxeDao;
//import dao.OrclassTaxePrimeDao;
//import dao.OrclassTypeTaxeDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.BaseCalculTaxePrime;
//import enums.BaseTaxePrime;
//import enums.FonctionnaliteModuleParametrage;
//import enums.LibelleCategorie;
//import java.io.IOException;
//import java.io.Serializable;
//import java.math.BigDecimal;
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
//import modele.OrclassActions;
//import modele.OrclassCategories;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassGarantie;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassRegimeTaxe;
//import modele.OrclassTaxePrime;
//import modele.OrclassTypeTaxe;
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
//@Named(value = "taxePrimeController")
//@ViewScoped
//public class TaxePrimeController implements Serializable {
//
//    /**
//     * Creates a instance of TaxePrimeController
//     */
//    @EJB
//    OrclassTaxePrimeDao taxePrimeDao;
//    OrclassTaxePrime taxePrime;
//    @EJB
//    OrclassCategoriesDao categoriesDao;
//    OrclassCategories categories;
//    @EJB
//    OrclassRegimeTaxeDao regimeTaxeDao;
//    OrclassRegimeTaxe regimeTaxe;
//    @EJB
//    OrclassTypeTaxeDao typeTaxeDao;
//    OrclassTypeTaxe typeTaxe;
//    @EJB
//    OrclassGarantieDao garantieDao;
//    OrclassGarantie garantie;
//
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    IDroitAcces serviceAccess;
//
//    private List<OrclassCategories> listeCategories = new ArrayList<>();
//    private List<OrclassGarantie> listeGarantie = new ArrayList<>();
//    private List<OrclassTypeTaxe> listeTypeTaxe = new ArrayList<>();
//    private List<OrclassRegimeTaxe> listeRegimeTaxe = new ArrayList<>();
//    private List<OrclassCategories> filterCategories = new ArrayList<>();
//    private List<OrclassRegimeTaxe> filterRegimeTaxe = new ArrayList<>();
//    private List<OrclassGarantie> filterGarantie = new ArrayList<>();
//    private List<OrclassTypeTaxe> filterTypeTaxe = new ArrayList<>();
//    private List<OrclassTaxePrime> listeTaxePrime = new ArrayList<>();
//    private List<OrclassTaxePrime> filterTaxePrime = new ArrayList<>();
//
//    String summary = "";
//    String msgdetail = "";
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//    OrclassEntreprises entreprise;
//    Date date_effet;
//
//    public TaxePrimeController() {
//        taxePrime = new OrclassTaxePrime();
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
//        menu = menusDao.findEntityHavingValue("code", ModuleMenu.mdsp_tarif);
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//
//        listeRegimeTaxe = (List<OrclassRegimeTaxe>) regimeTaxeDao.findAll();
//        listeTypeTaxe = (List<OrclassTypeTaxe>) typeTaxeDao.findAll();
//        listeCategories = categoriesDao.getAllCategorieByEntreprise(entreprise);
//        listeCategories.addAll(categoriesDao.listAllCategoriesShowAllCompagnie());
//
//        this.updateTableTaxePrime();
//        this.updateTableTaxe();
//
//    }
//
//    public void updateTableTaxePrime() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:idTableTaxePrime");
//        if (table == null) {
//            return;
//        }
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('tableTaxePrime').clearFilters();");
//
//    }
//
//    public void updateTableTaxe() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:idtaxePrimeTable");
//        if (table == null) {
//            return;
//        }
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('taxePrimeTable').clearFilters();");
//
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
//    /*
//    lister les taxes primes pour une categories choisir
//     */
//    public void listeTaxePrimeByCategoriesByRegimeTaxeByDateEFFEt() {
//        if (categories != null && categories.getIdCategorie() != null && regimeTaxe != null && regimeTaxe.getId() != null && date_effet != null) {
//            listeTaxePrime = taxePrimeDao.listeTaxePrimeByCategories(entreprise, categories, regimeTaxe, date_effet);
//            listeGarantie = garantieDao.getallGarantieByCategorieByCompagnie(entreprise, categories);
//            if (listeTaxePrime.isEmpty()) {
//                taxePrime = new OrclassTaxePrime();
//                listeTaxePrime.add(taxePrime);
//            } else {
//
//                taxePrime = new OrclassTaxePrime();
//                listeTaxePrime.add(taxePrime);
//                this.reverseListeTaxePrime();
//            }
//
//            this.updateTableTaxePrime();
//        }
//
//    }
//    
//       public void listeTaxePrimeByCategoriesByRegimeTaxe() {
//        if (categories != null && categories.getIdCategorie() != null && regimeTaxe != null && regimeTaxe.getId() != null) {
//            listeTaxePrime = taxePrimeDao.listeTaxePrimeByCategories(entreprise, categories, regimeTaxe);
//            listeGarantie = garantieDao.getallGarantieByCategorieByCompagnie(entreprise, categories);
////            if (listeTaxePrime.isEmpty()) {
////                taxePrime = new OrclassTaxePrime();
////                listeTaxePrime.add(taxePrime);
////            } else {
////
////                taxePrime = new OrclassTaxePrime();
////                listeTaxePrime.add(taxePrime);
////                this.reverseListeTaxePrime();
////            }
//
//            this.updateTableTaxePrime();
//        }
//
//    }
//
//    public void afficheListeTypeTaxe() {
//        if (categories != null && categories.getIdCategorie() != null && regimeTaxe != null && regimeTaxe.getId() != null && date_effet != null) {
//            PrimeFaces.current().executeScript("PF('taxeDialog').show()");
//        }
//    }
//
//    /*
//    charger les typestaxe dans la liste taxe prime
//     */
//    public void chargeTaxe() {
//        if (categories != null && categories.getIdCategorie() != null && regimeTaxe != null && regimeTaxe.getId() != null && date_effet != null && typeTaxe != null && typeTaxe.getIdTypeTaxe() != null) {
//            taxePrime = listeTaxePrime.get(0);
//            taxePrime.setIdCategories(categories);
//            taxePrime.setDate_effet(date_effet);
//            taxePrime.setIdEntreprise(entreprise);
//            taxePrime.setIdRegimeTaxe(regimeTaxe);
//            taxePrime.setIdTypeTaxe(typeTaxe);
//            listeTaxePrime.set(0, taxePrime);
//            this.updateTableTaxePrime();
//            PrimeFaces.current().executeScript("PF('taxeDialog').hide()");
//        }
//
//    }
//
//    public void reverseListeTaxePrime() {
//
//        List<OrclassTaxePrime> result = new ArrayList<>();
//        for (int i = listeTaxePrime.size() - 1; i >= 0; i--) {
//            result.add(listeTaxePrime.get(i));
//        }
//
//        this.setListeTaxePrime(result);
//
//    }
//
//    public String addTaxePrime() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "taxeprime", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if ((taxePrime != null && taxePrime.getBaseCalculTaxePrime() == null) || (taxePrime != null && taxePrime.getBaseTaxePrime() == null)) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE VALUE IS NULL", "PLEASE SELECT THE  VALUE"));
//                return null;
//            }
//            if (taxePrime != null && Objects.equals(taxePrime.getForfaitaire(), Boolean.TRUE)) {
//                if (taxePrime.getMontant_forfaitaire() == null || taxePrime.getMontant_forfaitaire().intValue() == 0) {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE VALUE NOT CORRECT ", "PLEASE FIXE THE VALUE"));
//                    taxePrime.setTaux(BigDecimal.ZERO);
//                    return null;
//                }
//            }
//            if (taxePrime != null && taxePrime.getId() == null) {
//                taxePrime.setIdGarantie(garantie);
//                if (taxePrime.getIdGarantie() != null && taxePrime.getIdGarantie().getId() != null) {
//
//                    if (taxePrimeDao.finkey(taxePrime.getIdEntreprise(), categories, regimeTaxe, taxePrime.getIdTypeTaxe(), date_effet, taxePrime.getIdGarantie()) == null) {
//                        taxePrime.setDate_creation(new Date());
//                        taxePrime.setSaisir_par(user.getNom() + " " + user.getPrenom());
//                        taxePrimeDao.create(taxePrime);
//                    }
//                } else {
//
//                    if (taxePrimeDao.finkey(taxePrime.getIdEntreprise(), categories, regimeTaxe, taxePrime.getIdTypeTaxe(), date_effet) == null) {
//                        taxePrime.setDate_creation(new Date());
//                        taxePrime.setSaisir_par(user.getNom() + " " + user.getPrenom() != null ? user.getPrenom() : "");
//                        taxePrimeDao.create(taxePrime);
//                    }
//                }
//
//                listeTaxePrime = taxePrimeDao.listeTaxePrimeByCategories(entreprise, categories, regimeTaxe, date_effet);
//                taxePrime = new OrclassTaxePrime();
//                listeTaxePrime.add(taxePrime);
//                this.reverseListeTaxePrime();
//                this.updateTableTaxePrime();
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "condition", exception.Error.FATAL_ERROR + "", new Object[]{"Taxe Prime"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        return null;
//    }
//
//    public String updateTaxePrime() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "taxeprime", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if ((taxePrime != null && taxePrime.getBaseCalculTaxePrime() == null) || (taxePrime != null && taxePrime.getBaseTaxePrime() == null)) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE VALUE IS NULL", "PLEASE SELECT THE  VALUE"));
//                return null;
//            }
//            if (taxePrime != null && Objects.equals(taxePrime.getForfaitaire(), Boolean.TRUE)) {
//                if (taxePrime.getMontant_forfaitaire() == null || taxePrime.getMontant_forfaitaire().intValue() == 0) {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE VALUE NOT CORRECT ", "PLEASE FIXE THE VALUE"));
//                    taxePrime.setTaux(BigDecimal.ZERO);
//                    return null;
//                }
//            }
//            if (taxePrime != null && taxePrime.getId() != null) {
//                if (garantie != null && garantie.getId() != null) {
//                    taxePrime.setIdGarantie(garantie);
//                }
//                taxePrimeDao.update(taxePrime);
//                listeTaxePrime = taxePrimeDao.listeTaxePrimeByCategories(entreprise, categories, regimeTaxe, date_effet);
//                taxePrime = new OrclassTaxePrime();
//                listeTaxePrime.add(taxePrime);
//                this.reverseListeTaxePrime();
//                this.updateTableTaxePrime();
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "taxeprime", exception.Error.FATAL_ERROR + "", new Object[]{"taxe prime"});
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
//    public String deleteTaxePrime() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "taxeprime", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (taxePrime != null && taxePrime.getId() != null) {
//
//                taxePrimeDao.delete(taxePrime);
//                listeTaxePrime = taxePrimeDao.listeTaxePrimeByCategories(entreprise, categories, regimeTaxe, date_effet);
//                taxePrime = new OrclassTaxePrime();
//                listeTaxePrime.add(taxePrime);
//                this.reverseListeTaxePrime();
//                this.updateTableTaxePrime();
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "taxeprime", exception.Error.FATAL_ERROR + "", new Object[]{"taxe prime"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        return null;
//    }
//
//    public List<SelectItem> getBasecalculsPrime() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (BaseCalculTaxePrime b : BaseCalculTaxePrime.values()) {
//
//            items.add(new SelectItem(b, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, b.name(), null, myLoc)));
//        }
//
//        return items;
//    }
//
//    public List<SelectItem> getBaseTaxePrimes() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (BaseTaxePrime bt : BaseTaxePrime.values()) {
//
//            items.add(new SelectItem(bt, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, bt.name(), null, myLoc)));
//        }
//
//        return items;
//    }
//
//    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
//        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
//
//        OrclassTaxePrime tp = (OrclassTaxePrime) value;
//        if (tp.getId() == null) {
//            return true;
//        }
//        return tp.getIdTypeTaxe().getCode().toLowerCase().contains(filterText)
//                || tp.getIdTypeTaxe().getLibelle().toLowerCase().contains(filterText);
//    }
//
//    public Boolean accessCreer() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.taxeprime.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.taxeprime.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.taxeprime.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer.name(), fon);
//            System.out.println("accees supprimer :" + serviceAccess.accessAction(user, action, menu));
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public OrclassTaxePrime getTaxePrime() {
//        return taxePrime;
//    }
//
//    public void setTaxePrime(OrclassTaxePrime taxePrime) {
//        this.taxePrime = taxePrime;
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
//    public OrclassTypeTaxe getTypeTaxe() {
//        return typeTaxe;
//    }
//
//    public void setTypeTaxe(OrclassTypeTaxe typeTaxe) {
//        this.typeTaxe = typeTaxe;
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
//    public List<OrclassCategories> getListeCategories() {
//        return listeCategories;
//    }
//
//    public void setListeCategories(List<OrclassCategories> listeCategories) {
//        this.listeCategories = listeCategories;
//    }
//
//    public List<OrclassGarantie> getListeGarantie() {
//        return listeGarantie;
//    }
//
//    public void setListeGarantie(List<OrclassGarantie> listeGarantie) {
//        this.listeGarantie = listeGarantie;
//    }
//
//    public List<OrclassTypeTaxe> getListeTypeTaxe() {
//        return listeTypeTaxe;
//    }
//
//    public void setListeTypeTaxe(List<OrclassTypeTaxe> listeTypeTaxe) {
//        this.listeTypeTaxe = listeTypeTaxe;
//    }
//
//    public List<OrclassRegimeTaxe> getListeRegimeTaxe() {
//        return listeRegimeTaxe;
//    }
//
//    public void setListeRegimeTaxe(List<OrclassRegimeTaxe> listeRegimeTaxe) {
//        this.listeRegimeTaxe = listeRegimeTaxe;
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
//    public List<OrclassRegimeTaxe> getFilterRegimeTaxe() {
//        return filterRegimeTaxe;
//    }
//
//    public void setFilterRegimeTaxe(List<OrclassRegimeTaxe> filterRegimeTaxe) {
//        this.filterRegimeTaxe = filterRegimeTaxe;
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
//    public List<OrclassTypeTaxe> getFilterTypeTaxe() {
//        return filterTypeTaxe;
//    }
//
//    public void setFilterTypeTaxe(List<OrclassTypeTaxe> filterTypeTaxe) {
//        this.filterTypeTaxe = filterTypeTaxe;
//    }
//
//    public List<OrclassTaxePrime> getListeTaxePrime() {
//        return listeTaxePrime;
//    }
//
//    public void setListeTaxePrime(List<OrclassTaxePrime> listeTaxePrime) {
//        this.listeTaxePrime = listeTaxePrime;
//    }
//
//    public List<OrclassTaxePrime> getFilterTaxePrime() {
//        return filterTaxePrime;
//    }
//
//    public void setFilterTaxePrime(List<OrclassTaxePrime> filterTaxePrime) {
//        this.filterTaxePrime = filterTaxePrime;
//    }
//
//    public OrclassRegimeTaxe getRegimeTaxe() {
//        return regimeTaxe;
//    }
//
//    public void setRegimeTaxe(OrclassRegimeTaxe regimeTaxe) {
//        this.regimeTaxe = regimeTaxe;
//    }
//
//    public Date getDate_effet() {
//        return date_effet;
//    }
//
//    public void setDate_effet(Date date_effet) {
//        this.date_effet = date_effet;
//    }
//
//}
