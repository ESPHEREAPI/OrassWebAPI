///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import dao.OrclassCategoriesDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassRefTimbreGradueDao;
//import dao.OrclassTimbreDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleParametrage;
//import enums.LibelleFrais;
//import java.io.IOException;
//import java.io.Serializable;
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
//import javax.faces.model.SelectItem;
//import javax.inject.Named;
//import javax.faces.view.ViewScoped;
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.ConstraintViolationException;
//import modele.OrclassActions;
//import modele.OrclassCategories;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassRefTimbreGradue;
//import modele.OrclassTimbre;
//import modele.OrclassUtilisateurs;
//import org.primefaces.PrimeFaces;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import parametrage.ModuleMenu;
//import utils.GlobalFonctions;
//import utils.LocaleHelper;
//import utils.RecupBundle;
//
///**
// *
// * @author JIATOU FRANCK
// */
//@Named(value = "timbreGradueController")
//@ViewScoped
//public class TimbreGradueController implements Serializable {
//
//    private static final Logger logger = LoggerFactory.getLogger(TimbreGradueController.class);
//
//    /**
//     * Creates a new instance of TimbreGradueController
//     */
//    @EJB
//    OrclassCategoriesDao categoriesDao;
//    OrclassCategories categories;
//    @EJB
//    OrclassRefTimbreGradueDao refTimbreGradueDao;
//    OrclassRefTimbreGradue refTimbreGradue;
//    OrclassRefTimbreGradue refTimbreGradueAdd;
//    @EJB
//    OrclassTimbreDao timbreDao;
//    OrclassTimbre timbreGradue;
//
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    IDroitAcces serviceAccess;
//
//    private List<OrclassCategories> lisCategories = new ArrayList<>();
//    private List<OrclassRefTimbreGradue> listeRefTimbreGradue = new ArrayList<>();
//    private List<OrclassTimbre> listeTimbreGradue = new ArrayList<>();
//    private List<OrclassTimbre> filterTimbreGradue = new ArrayList<>();
//    private List<OrclassTimbre> filterCategories = new ArrayList<>();
//
//    String summary = "";
//    String msgdetail = "";
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//    OrclassEntreprises entreprise;
//    private Date effet;
//
//    public TimbreGradueController() {
//        refTimbreGradue = new OrclassRefTimbreGradue();
//        categories = new OrclassCategories();
//        timbreGradue = new OrclassTimbre();
//        refTimbreGradueAdd = new OrclassRefTimbreGradue();
//
//    }
//
//    public List<SelectItem> getLibelleFrais() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (LibelleFrais n : LibelleFrais.values()) {
//            items.add(new SelectItem(n, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, n.name(), null, myLoc)));
//
//        }
//        return items;
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
////        lisRubrique = rubriqueDao.liteRubriqueByCompagnie(entreprise);
//        menu = menusDao.findEntityHavingValue("code", ModuleMenu.mTimbreGradue);
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        lisCategories = categoriesDao.listAllCategoriesShowAllCompagnie();
//        lisCategories.addAll(categoriesDao.listAllCategorieByCompagnie(entreprise));
//        listeRefTimbreGradue = (List<OrclassRefTimbreGradue>) refTimbreGradueDao.findAll();
//        this.updateTableCategories();
//        this.updateTableTimbreGradue();
//
//    }
//
//    public void chargeRubriqueByCategories(OrclassCategories item) {
//        if ((categories == null || categories.getIdCategorie() == null) && item != null && item.getIdCategorie() == null) {
//            this.setCategories(item);
//            this.chargeTimbreByREfTimbreANdAutres();
//        }
//    }
//
//    public void updateTableCategories() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2_2:idtableCategorie");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('tableCategorie').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public void updateTableTimbreGradue() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1_1:idtimbreTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('timbreTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
//        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
////        if (LangUtils.isBlank(filterText)) {
////            return true;
////        }
////        int filterInt = getInteger(filterText);
//
//        OrclassTimbre t = (OrclassTimbre) value;
//        if (t.getId() == null) {
//            return true;
//        }
//        return t.getPuissance_min().toString().toLowerCase().contains(filterText)
//                || t.getPuissance_max().toString().toLowerCase().contains(filterText);
//    }
//
//    public void reverseListe() {
//
//        List<OrclassTimbre> result = new ArrayList<>();
//        for (int i = listeTimbreGradue.size() - 1; i >= 0; i--) {
//            result.add(listeTimbreGradue.get(i));
//        }
//
//        this.setListeTimbreGradue(result);
//
//    }
//
//    public void reload() throws IOException {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
//    }
//
//    public void chargeTimbreByREfTimbreANdAutres() {
//
//        if (categories != null && categories.getIdCategorie() != null && refTimbreGradue != null && refTimbreGradue.getId() != null && effet != null) {
//
//            listeTimbreGradue = timbreDao.listeTimbreByRefAndDateEffet(entreprise, refTimbreGradue, effet, categories);
//        } else if (refTimbreGradue != null && refTimbreGradue.getId() != null && effet != null) {
//           
//                listeTimbreGradue = timbreDao.listeTimbreByRefAndDateEffet(entreprise, refTimbreGradue, effet);
//         
//            if (listeTimbreGradue.isEmpty()) {
//                timbreGradue = new OrclassTimbre();
//                listeTimbreGradue.add(timbreGradue);
//            } else {
//                timbreGradue = new OrclassTimbre();
//                listeTimbreGradue.add(timbreGradue);
//                this.reverseListe();
//            }
//            this.updateTableTimbreGradue();
//        }
//    }
//    
//        public void chargeTimbreByREfTimbre() {
//
//        if (categories != null && categories.getIdCategorie() != null && refTimbreGradue != null ) {
//
//            listeTimbreGradue = timbreDao.listeTimbreByRef(entreprise, refTimbreGradue, categories);
//        } else if (refTimbreGradue != null && refTimbreGradue.getId() != null) {
//           
//                listeTimbreGradue = timbreDao.listeTimbreByRef(entreprise, refTimbreGradue);
//         
////            if (listeTimbreGradue.isEmpty()) {
////                timbreGradue = new OrclassTimbre();
////                listeTimbreGradue.add(timbreGradue);
////            } else {
////                timbreGradue = new OrclassTimbre();
////                listeTimbreGradue.add(timbreGradue);
////                this.reverseListe();
////            }
//
//            this.updateTableTimbreGradue();
//        }
//    }
//    
//
//    public String addRefTimbreGraduee() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        //Locale myLoc =new Locale("fr");
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "timbre.gradue", null, myLoc)};
//
//        try {
//            //recherche si le classe existe"
//            if (refTimbreGradueAdd != null && refTimbreGradueAdd.getLibelleFrais() == null) {
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "VALUE  IS NULL", "PLEASE SELECT VALUE"));
//                PrimeFaces.current().executeScript("PF('TaxeDialog').show()");
//                return null;
//            }
//            if (refTimbreGradueDao.findEntityHavingValue("code", refTimbreGradueAdd.getCode()) == null) {
//
//                refTimbreGradueDao.create(refTimbreGradueAdd);
//
//                String[] detail = {entete[0], refTimbreGradueAdd.getLibelle()};
//                //ctx.getApplication().getMessageBundle()
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//            } else {
//                String[] detail = {entete[0], refTimbreGradueAdd.getLibelle()};
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.doublons", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.Errdoublons", detail, myLoc);
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//
//            }
//        } catch (ConstraintViolationException E) {
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.error", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, msgdetail + E.getMessage() + " " + E.getCause()));
//            throw E;
//        } catch (Throwable th) {
//            //ecrire dans le fichier de log  
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.error", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));
//
//        }
//
//        this.setRefTimbreGradue(refTimbreGradueAdd);
//        this.listeRefTimbreGradue.add(refTimbreGradueAdd);
//        if (effet != null && refTimbreGradue != null && refTimbreGradue.getId() != null) {
//            timbreGradue = new OrclassTimbre();
//            if (listeTimbreGradue.isEmpty()) {
//                listeTimbreGradue.add(timbreGradue);
//            } else {
//                listeTimbreGradue.add(timbreGradue);
//                this.reverseListe();
//            }
//            this.updateTableTimbreGradue();
//
//        }
//        PrimeFaces.current().executeScript("PF('TaxeDialog').hide()");
//        return null;
//    }
//
//    public String addTimbreGraduee() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "timbre.gradue", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        OrclassTimbre timbre = null;
//        String nomComplet = user.getNom();
//        if (user.getPrenom() != null) {
//            nomComplet += user.getPrenom();
//        }
//        timbreGradue.setDate_effet(effet);
//        try {
//            if (timbreGradue != null && (timbreGradue.getPuissance_min().intValue() == 0 || timbreGradue.getPuissance_max().intValue() == 0)) {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "THE VALUE IS EMPTY OR NULL", "PLEASE WRITE VALUE OF POWER "));
//                return null;
//            }
//            if (timbreGradue != null && (timbreGradue.getPrime_mini().intValue() == 0 || timbreGradue.getPrime_max().intValue() == 0)) {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "THE VALUE IS EMPTY OR NULL", "PLEASE WRITE VALUE OF PRIME "));
//                return null;
//            }
//            if (timbreGradue != null && (timbreGradue.getTaux_tg_cp_fg().intValue() == 0 && timbreGradue.getValeur_tg_cp_fg().intValue() == 0)) {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "THE VALUE IS EMPTY OR NULL", "PLEASE WRITE VALUE OR TAUX"));
//                return null;
//            }
//
//            if (categories != null && categories.getIdCategorie() != null && timbreGradue != null) {
//                timbre = timbreDao.finKey(entreprise, refTimbreGradue, timbreGradue.getDate_effet(), categories, timbreGradue.getPuissance_min(), timbreGradue.getPuissance_max());
//
//            } else {
//
//                timbre = timbreDao.finKey(entreprise, refTimbreGradue, timbreGradue.getDate_effet(), timbreGradue.getPuissance_min(), timbreGradue.getPuissance_max());
//            }
//            if (timbre == null) {
//
//                if (categories != null && categories.getIdCategorie() != null) {
//                    timbreGradue.setIdCategories(categories);
//                }
//
//                timbreGradue.setIdRefTimbreGradue(refTimbreGradue);
//                timbreGradue.setDate_saisie(new Date());
//                timbreGradue.setSaisir_par(nomComplet);
//                timbreGradue.setIdEntreprises(entreprise);
//                timbreDao.create(timbreGradue);
//                listeTimbreGradue = timbreDao.listeTimbreByRefAndDateEffet(entreprise, refTimbreGradue, timbreGradue.getDate_effet());
//                timbreGradue = new OrclassTimbre();
//                listeTimbreGradue.add(timbreGradue);
//                this.reverseListe();
//                this.updateTableTimbreGradue();
//
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "OPERATION NOT FOUND", "THE VALUE IS EXIST PLEASE CHANGE DATE"));
//                return null;
//
//            }
//
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "timbre.gradue", exception.Error.FATAL_ERROR + "", new Object[]{"timbre.gradue"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        return null;
//    }
//
//    public String updateTimbreGraduee() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "timbre.gradue", null, myLoc)};
//        String nomComplet = user.getNom();
//        if (user.getPrenom() != null) {
//            nomComplet += user.getPrenom();
//        }
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (timbreGradue != null && timbreGradue.getId() != null) {
//
//                timbreGradue.setDate_Modifier(new Date());
//                timbreGradue.setModifier_par(nomComplet);
//                timbreDao.update(timbreGradue);
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//                listeTimbreGradue = timbreDao.listeTimbreByRefAndDateEffet(entreprise, refTimbreGradue, effet);
//                timbreGradue = new OrclassTimbre();
//                listeTimbreGradue.add(timbreGradue);
//                this.reverseListe();
//                this.updateTableTimbreGradue();
//
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "timbre.gradue", exception.Error.FATAL_ERROR + "", new Object[]{"timbre.gradue"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        return null;
//    }
//
//    public String deleteTimbreGradue() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "timbre.gradue", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (timbreGradue != null && timbreGradue.getId() != null) {
//
//                timbreDao.delete(timbreGradue);
//                listeTimbreGradue = timbreDao.listeTimbreByRefAndDateEffet(entreprise, refTimbreGradue, effet);
//                timbreGradue = new OrclassTimbre();
//                listeTimbreGradue.add(timbreGradue);
//                this.reverseListe();
//                this.updateTableTimbreGradue();
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
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "timbre.gradue", exception.Error.FATAL_ERROR + "", new Object[]{"timbre.gradue"});
//            logger.error("Erreur Survenu", th);
//        }
//
//        return null;
//    }
//
//    public void showTimbreGradue(OrclassTimbre item) {
//
//        this.setTimbreGradue(item);
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
//    public OrclassRefTimbreGradue getRefTimbreGradue() {
//        return refTimbreGradue;
//    }
//
//    public void setRefTimbreGradue(OrclassRefTimbreGradue refTimbreGradue) {
//        this.refTimbreGradue = refTimbreGradue;
//    }
//
//    public List<OrclassCategories> getLisCategories() {
//        return lisCategories;
//    }
//
//    public void setLisCategories(List<OrclassCategories> lisCategories) {
//        this.lisCategories = lisCategories;
//    }
//
//    public List<OrclassRefTimbreGradue> getListeRefTimbreGradue() {
//        return listeRefTimbreGradue;
//    }
//
//    public void setListeRefTimbreGradue(List<OrclassRefTimbreGradue> listeRefTimbreGradue) {
//        this.listeRefTimbreGradue = listeRefTimbreGradue;
//    }
//
//    public List<OrclassTimbre> getListeTimbreGradue() {
//        return listeTimbreGradue;
//    }
//
//    public void setListeTimbreGradue(List<OrclassTimbre> listeTimbreGradue) {
//        this.listeTimbreGradue = listeTimbreGradue;
//    }
//
//    public List<OrclassTimbre> getFilterTimbreGradue() {
//        return filterTimbreGradue;
//    }
//
//    public void setFilterTimbreGradue(List<OrclassTimbre> filterTimbreGradue) {
//        this.filterTimbreGradue = filterTimbreGradue;
//    }
//
//    public OrclassTimbre getTimbreGradue() {
//        return timbreGradue;
//    }
//
//    public void setTimbreGradue(OrclassTimbre timbreGradue) {
//        this.timbreGradue = timbreGradue;
//    }
//
//    public Boolean accessModifier() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.timbre_gradue.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.timbre_gradue.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean accessAfficheListe() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.timbre_gradue.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.afficher_liste.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.rubrique_assure.name());
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
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.rubrique_assure.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);
//
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public OrclassRefTimbreGradue getRefTimbreGradueAdd() {
//        return refTimbreGradueAdd;
//    }
//
//    public void setRefTimbreGradueAdd(OrclassRefTimbreGradue refTimbreGradueAdd) {
//        this.refTimbreGradueAdd = refTimbreGradueAdd;
//    }
//
//    public Date getEffet() {
//        return effet;
//    }
//
//    public void setEffet(Date effet) {
//        this.effet = effet;
//    }
//
//    public List<OrclassTimbre> getFilterCategories() {
//        return filterCategories;
//    }
//
//    public void setFilterCategories(List<OrclassTimbre> filterCategories) {
//        this.filterCategories = filterCategories;
//    }
//
//}
